
package eprecise.efiscal4j.nfse.domain.adapters;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.nfse.domain.NFSe;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeLegalEntityDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeNaturalPersonDocuments;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeWithIssHeld;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeWithIssHeldElotechData;
import eprecise.efiscal4j.nfse.domain.specificData.NFSeElotechData;
import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationRequestData;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeUF;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnpj;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCpf;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.elotech.cancel.ElotechCancellationCode;
import eprecise.efiscal4j.nfse.tc.elotech.cancel.ElotechNFSeIdentifier;
import eprecise.efiscal4j.nfse.tc.elotech.cancel.ElotechNfseCancelRequest;
import eprecise.efiscal4j.nfse.tc.elotech.lot.ElotechLotRps;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechServiceIntermediary;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechServiceProvider;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechServiceProvider.ElotechServiceProviderIdentifier;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechServiceTaker;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechStatementProvisionService;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechTaxIncentive;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.rps.ElotechRps;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechIssRequirement;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechIssWithheld;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechService;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechServiceItem;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechServiceItemTaxable;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechServiceValues;
import eprecise.efiscal4j.nfse.tc.elotech.person.address.ElotechNFSeAddress;
import eprecise.efiscal4j.nfse.tc.elotech.services.ElotechApplicant;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.cancel.ElotechNfseDispatchCancel;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsType;


public class ElotechNFSeDomainAdapter implements NFSeDomainAdapter {

    private static final DecimalFormat NFSE_TWO_DECIMALS_FORMAT = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));

    public static final DateFormat NFSE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final NFSe nfse;

    public ElotechNFSeDomainAdapter(final NFSeDomainAdapter.Builder builder) {
        this.nfse = builder.getNfse();
    }

    @Override
    public NFSeRequest toDispatch() {
        //@formatter:off
        return new ElotechLotRpsDispatchSync.Builder()
                .withApplicant(this.buildApplicant())
                .withLotRps(new ElotechLotRps.Builder()
                        .withLotNumber(this.nfse.getSerie().getLotNumber())
                        .withCnp(new CommonsNFSeCnpj.Builder().withCnpj(this.nfse.getEmitter().getDocuments().getCnp()).build())
                        .withMunicipalRegistration(Optional.ofNullable(this.nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).map(this::nullIfEmpty).orElse(null))
                        .withRpsQuantity(1).withStatementProvisionService(Arrays.asList(this.buildStatementProvisionService()))
                        .build())
                .build();
        //@formatter:on
    }

    @Override
    public NFSeRequest toDispatchCancel(final NFSeCancellationRequestData cancellationRequestData) {
        return new ElotechNfseDispatchCancel.Builder().withApplicant(this.buildApplicant())
                .withCancelRequest(new ElotechNfseCancelRequest.Builder().withInfo(new ElotechNfseCancelRequest.ElotechNfseCancelRequestInfo.Builder()
                        .withIdentifier(new ElotechNFSeIdentifier.Builder().withNumber(cancellationRequestData.getNfseNumber())
                                .withCityCode(this.nfse.getEmitter().getAddress().getCity().getIbgeCode()).withCnp(this.buildCnp(this.nfse.getEmitter().getDocuments()))
                                .withMunicipalRegistration(Optional.ofNullable(this.nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance)
                                        .map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).map(this::nullIfEmpty).orElse(null))
                                .withNumber(cancellationRequestData.getNfseNumber()).build())
                        .withAccessKey(cancellationRequestData.getAccessKey()).withCancellationCode(Optional.ofNullable(cancellationRequestData.getCancellationCode())
                                .filter(ElotechCancellationCode.class::isInstance).map(ElotechCancellationCode.class::cast).orElse(null))
                        .build()).build())
                .build();
    }

    @Override
    public NFSeRequest toDispatchConsult(final String protocol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NFSeRequest toDispatchConsultState(final String protocol) {
        throw new UnsupportedOperationException();
    }

    private ElotechApplicant buildApplicant() {
        //@formatter:off
            return new ElotechApplicant.Builder()
                        .withCnp(new CommonsNFSeCnpj.Builder().withCnpj(this.nfse.getEmitter().getDocuments().getCnp()).build())
                        .withMunicipalRegistration(Optional.ofNullable(this.nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).map(this::nullIfEmpty).orElse(null))
                        .withPassword(Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeElotechData.class::isInstance).map(NFSeElotechData.class::cast).map(NFSeElotechData::getTransmissionPassword).orElse(null))
                        .withHomologation(Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeElotechData.class::isInstance).map(NFSeElotechData.class::cast).map(NFSeElotechData::isHomologation).orElse(false))
                        .build();

        //@formatter:on
    }

    private ElotechStatementProvisionService buildStatementProvisionService() {
        //@formatter:off
        return new ElotechStatementProvisionService.Builder()
                .withInfo(new ElotechStatementProvisionService.Info.Builder()
                        .withCompetence(NFSE_DATE_FORMAT.format(this.nfse.getEmission()))
                        .withRps(this.buildRps())
                        .withService(this.buildService())
                        .withServiceIntermediary(this.buildServiceIntermediary())
                        .withServiceProviderIdentifier(this.buildServiceProviderIdentifier())
                        .withServiceTaker(this.buildServiceTaker())
                        .withSpecialTaxationRegime(Optional.ofNullable(this.nfse.getEmitter().getSpecialTaxationRegime()).filter(ElotechSpecialTaxationRegime.class::isInstance).map(ElotechSpecialTaxationRegime.class::cast).orElse(null))
                        .withTaxIncentive(Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeElotechData.class::isInstance).map(NFSeElotechData.class::cast).map(NFSeElotechData::isTaxIncentive).map(ti-> ti ? ElotechTaxIncentive.YES : ElotechTaxIncentive.NO).orElse(null))
                        .build())
                .build();
        //@formatter:on
    }

    private ElotechRps buildRps() {
        //@formatter:off
        return new ElotechRps.Builder()
        .withIdentifier(new CommonsRpsIdentifier.Builder()
                .withType(CommonsRpsType.PROVISIONAL_SERVICE_RECEIPT)
                .withSerie(this.nfse.getSerie().getSerie())
                .withNumber(this.nfse.getSerie().getRpsNumber())
                .build())
        .withStatus(CommonsRpsStatus.NORMAL)
        .withEmissionDate(new SimpleDateFormat("yyyy-MM-dd").format(this.nfse.getEmission()))
        .build();
        //@formatter:on
    }

    private ElotechService buildService() {
        //@formatter:off
        final ElotechService.Builder builder = new ElotechService.Builder()
                .withServiceValues(this.buildServiceValues())
                .withIssWithheld(this.nfse.getIssHeld() instanceof NFSeWithIssHeld ? ElotechIssWithheld.YES : ElotechIssWithheld.NO)
                .withDiscrimination(this.formatNfseString(this.nfse.getService().getDiscrimination(),2000))
                .withCityCode(this.nfse.getService().getCityService().getIbgeCode())
                .withIssRequirement(Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeElotechData.class::isInstance).map(NFSeElotechData.class::cast).map(NFSeElotechData::getIssRequirement).orElse(null))
                .withCityIncidenceCode(this.nfse.getEmitter().getAddress().getCity().getIbgeCode())
                .withServiceItems(this.buildServiceItems());
        //@formatter:on

        Optional.ofNullable(this.nfse.getIssHeld()).filter(NFSeWithIssHeld.class::isInstance).map(NFSeWithIssHeld.class::cast).map(NFSeWithIssHeld::getSpecificData)
                .filter(NFSeWithIssHeldElotechData.class::isInstance).map(NFSeWithIssHeldElotechData.class::cast).map(NFSeWithIssHeldElotechData::getResponsibleRetention).ifPresent(rr -> {
                    builder.withResponsibleRetention(rr);
                });

        Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeElotechData.class::isInstance).map(NFSeElotechData.class::cast)
                .filter(ed -> ed.getIssRequirement().equals(ElotechIssRequirement.SUSPENDED_BY_JUDICIAL_DECISION)).map(NFSeElotechData::getJudicialProcessNumber).ifPresent(judicialProcessNumber -> {
                    builder.withProcessNumber(judicialProcessNumber);
                });

        return builder.build();

    }

    private ElotechServiceValues buildServiceValues() {
        //@formatter:off
        return new ElotechServiceValues.Builder()
                .withServiceValue(this.formatNFSeValue(this.nfse.getService().getNetValue()))
                .withDeductionValue(this.formatNFSeValue(this.nfse.getService().getDeduction()))
                .withPisAliquot(this.formatNFSeAliquot(this.nfse.getTax().getPisAliquot()))
                .withPisWithheld(this.nfse.getTax().isPisWithheld() ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO)
                .withPisValue(this.formatNFSeValue(this.nfse.getTax().getPisValue()))
                .withCofinsAliquot(this.formatNFSeAliquot(this.nfse.getTax().getCofinsAliquot()))
                .withCofinsWithheld(this.nfse.getTax().isCofinsWithheld() ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO)
                .withCofinsValue(this.formatNFSeValue(this.nfse.getTax().getCofinsValue()))
                .withInssAliquot(this.formatNFSeAliquot(this.nfse.getTax().getInssAliquot()))
                .withInssWithheld(this.nfse.getTax().isInssWithheld() ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO)
                .withInssValue(this.formatNFSeValue(this.nfse.getTax().getInssValue()))
                .withIrAliquot(this.formatNFSeAliquot(this.nfse.getTax().getIrAliquot()))
                .withIrWithheld(this.nfse.getTax().isIrWithheld() ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO)
                .withIrValue(this.formatNFSeValue(this.nfse.getTax().getIrValue()))
                .withCsllAliquot(this.formatNFSeAliquot(this.nfse.getTax().getCsllAliquot()))
                .withCsllWithheld(this.nfse.getTax().isCsllWithheld() ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO)
                .withCsllValue(this.formatNFSeValue(this.nfse.getTax().getCsllValue()))
                .withCppAliquot(this.formatNFSeAliquot(this.nfse.getTax().getCppAliquot()))
                .withCppWithheld(this.nfse.getTax().isCppWithheld() ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO)
                .withCppValue(this.formatNFSeValue(this.nfse.getTax().getCppValue()))
                .withOtherRetentionsValue(this.formatNFSeValue(this.nfse.getTax().getOtherRetentionsValue()))
                .withIssAliquot(this.formatNFSeAliquot(this.nfse.getTax().getIssAliquot()))
//                .withIssValue(formatNFSeValue(nfse.getTax().getIssValue()))
                .withDiscountUnconditionedValue(this.formatNFSeValue(this.nfse.getService().getDiscount().getUnconditionedValue()))
                .withDiscountConditionedValue(this.formatNFSeValue(this.nfse.getService().getDiscount().getConditionedValue()))
                .build();
        //@formatter:on
    }

    private Collection<ElotechServiceItem> buildServiceItems() {
        //@formatter:off
        final Collection<ElotechServiceItem> items = new HashSet<>();

            items.add(new ElotechServiceItem.Builder()
                    .withItemServiceList(this.nfse.getService().getNationalServiceCode().replaceAll("\\.", ""))
                    .withCnaeCode(this.nfse.getService().getCnaeCode())
                    .withDescription(this.formatNfseString(this.nfse.getService().getName(), 20))
                    .withTaxable(ElotechServiceItemTaxable.YES) //TODO REVER
                    .withQuantity(this.formatNFSeValue(this.nfse.getService().getAmount()))
                    .withUnitaryValue(this.formatNFSeValue(this.nfse.getService().getUnitaryValue()))
                    .withDiscountValue(this.formatNFSeValue(this.nfse.getService().getDiscount().getTotal()))
                    .withNetValue(this.formatNFSeValue(this.nfse.getService().getNetValue()))
                    .build());

        return items;
        //@formatter:on
    }

    private ElotechServiceProviderIdentifier buildServiceProviderIdentifier() {
        //@formatter:off
        return new ElotechServiceProvider.ElotechServiceProviderIdentifier.Builder()
                .withCnp(this.buildCnp(this.nfse.getEmitter().getDocuments()))
                .withMunicipalRegistration(Optional.ofNullable(this.nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).map(this::nullIfEmpty).orElse(null))
                .build();
        //@formatter:on
    }

    private ElotechServiceTaker buildServiceTaker() {
        if (this.nfse.getTaker() == null) {
            return null;
        }

        //@formatter:off
        return new ElotechServiceTaker.Builder()
        .withIdentifier(new ElotechServiceTaker.ElotechServiceTakerIdentifier.Builder()
                .withCnp(this.buildCnp(this.nfse.getTaker().getDocuments()))
                .withMunicipalRegistration(Optional.ofNullable(this.nfse.getTaker().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).map(this::nullIfEmpty).orElse(null))
                .build())
        .withSocialName(this.formatNfseString(this.nfse.getTaker().getName(), 150))
        .withAddress(Optional.ofNullable(this.nfse.getTaker().getAddress())
                .map(this::buildNFSeAddress).orElse(new ElotechNFSeAddress()))
        .build();
        //@formatter:on
    }

    private ElotechServiceIntermediary buildServiceIntermediary() {
        if (this.nfse.getIntermediary() == null) {
            return null;
        }

        //@formatter:off
        return new ElotechServiceIntermediary.Builder()
        .withIdentifier(new ElotechServiceIntermediary.ElotechServiceIntermediaryIdentifier.Builder()
                .withCnp(this.buildCnp(this.nfse.getIntermediary().getDocuments()))
                .build())
        .withSocialName(this.formatNfseString(this.nfse.getIntermediary().getName(), 150))
        .build();
        //@formatter:on
    }

    private CommonsNFSeCnp buildCnp(final NFSeDocuments documents) {
        if (documents instanceof NFSeLegalEntityDocuments) {
            if (documents.getCnp() != null) {
                return new CommonsNFSeCnpj.Builder().withCnpj(documents.getCnp()).build();
            }
        } else if (documents instanceof NFSeNaturalPersonDocuments) {
            if (documents.getCnp() != null) {
                return new CommonsNFSeCpf.Builder().withCpf(documents.getCnp()).build();
            }
        }
        return null;
    }

    private ElotechNFSeAddress buildNFSeAddress(final NFSeAddress address) {
        if (address == null) {
            return null;
        }

        //@formatter:off
            return new ElotechNFSeAddress.Builder()
            .withAddress(this.formatNfseString(address.getStreet(), 125))
            .withNumber(this.formatNfseString(address.getNumber(), 10))
            .withDistrict(this.formatNfseString(address.getDistrict(), 60))
            .withCityCode(Optional.ofNullable(address.getCity()).map(a->a.getIbgeCode()).orElse(null))
            .withUf(Optional.ofNullable(address.getCity()).map(c -> CommonsNFSeUF.findByAcronym(c.getUf().getAcronym())).orElse(null))
            .withCep(address.getZipCode())
            .build();
        //@formatter:on

    }

    private String formatNfseString(final String input, final int size) {
        return Optional.ofNullable(StringUtils.upperCase(StringUtils.stripAccents(this.abbreviate(this.nullIfEmpty(input), size)))).map(string -> {
            return string.replaceAll("\n", "  ").replaceAll("\r", "  ").replace("\t", "  ");
        }).orElse(null);
    }

    private String abbreviate(final String input, final int size) {
        if ((input != null) && !input.isEmpty()) {
            if (size >= 4) {
                return StringUtils.abbreviate(input, size);
            } else if (input.length() > size) {
                return input.substring(0, size);
            }
        }
        return input;

    }

    private String nullIfEmpty(final String v) {
        return StringUtils.isEmpty(v) ? null : v;
    }

    private String formatNFSeValue(final BigDecimal value) {
        if (value == null) {
            return null;
        } else {
            return NFSE_TWO_DECIMALS_FORMAT.format(value);
        }
    }

    private String formatNFSeAliquot(final BigDecimal value) {
        if (value == null) {
            return null;
        } else {
            return NFSE_TWO_DECIMALS_FORMAT.format(value);
        }

    }

}

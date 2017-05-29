
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

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.nfse.domain.NFSe;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeLegalEntityDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeNaturalPersonDocuments;
import eprecise.efiscal4j.nfse.domain.service.emitter.specificData.NFSeServiceEmitterElotechData;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeWithIssHeld;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeWithIssHeldElotechData;
import eprecise.efiscal4j.nfse.domain.specificData.NFSeElotechData;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeUF;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnpj;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCpf;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.elotech.lot.ElotechLotRps;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechServiceIntermediary;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechServiceProvider;
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
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsType;


public class ElotechNFSeDomainAdapter implements NFSeDomainAdapter {

    private static final DecimalFormat NFSE_TWO_DECIMALS_FORMAT = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));

    public static final DateFormat NFSE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final NFSe nfse;

    public ElotechNFSeDomainAdapter(final NFSeDomainAdapter.Builder builder) {
        nfse = builder.getNfse();
    }

    @Override
    public TransmissibleBodyImpl toTransmissible() {
        return new ElotechLotRpsDispatchSync.Builder().withApplicant(buildApplicant()).withLotRps(
                new ElotechLotRps.Builder().withLotNumber(nfse.getSerie().getLotNumber()).withRpsQuantity(1).withStatementProvisionService(Arrays.asList(buildStatementProvisionService())).build())
                .build();
    }

    private ElotechApplicant buildApplicant() {
        //@formatter:off
            return new ElotechApplicant.Builder()
                        .withCnp(new CommonsNFSeCnpj.Builder().withCnpj(nfse.getEmitter().getDocuments().getCnp()).build())
                        .withMunicipalRegistration(Optional.ofNullable(nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                        .withPassword(Optional.ofNullable(nfse.getEmitter().getSpecificData()).filter(NFSeServiceEmitterElotechData.class::isInstance).map(NFSeServiceEmitterElotechData.class::cast).map(NFSeServiceEmitterElotechData::getPassword).orElse(null))
                        .withHomologation(Optional.ofNullable(nfse.getSpecificData()).filter(NFSeElotechData.class::isInstance).map(NFSeElotechData.class::cast).map(NFSeElotechData::isHomologation).orElse(false))
                        .build();
        
        //@formatter:on
    }

    private ElotechStatementProvisionService buildStatementProvisionService() {
        //@formatter:off
        return new ElotechStatementProvisionService.Builder()
                .withInfo(new ElotechStatementProvisionService.Info.Builder()
                        .withCompetence(NFSE_DATE_FORMAT.format(nfse.getEmission()))
                        .withRps(buildRps())
                        .withService(buildService())
                        .withServiceIntermediary(buildServiceIntermediary())
                        .withServiceProvider(buildServiceProvider())
                        .withServiceTaker(buildServiceTaker())
                        .withSpecialTaxationRegime(Optional.ofNullable(nfse.getSpecialTaxationRegime()).filter(ElotechSpecialTaxationRegime.class::isInstance).map(ElotechSpecialTaxationRegime.class::cast).orElse(null))
                        .withTaxIncentive(Optional.ofNullable(nfse.getSpecificData()).filter(NFSeElotechData.class::isInstance).map(NFSeElotechData.class::cast).map(NFSeElotechData::isTaxIncentive).map(ti-> ti ? ElotechTaxIncentive.YES : ElotechTaxIncentive.NO).orElse(null))
                        .build())
                .build();
        //@formatter:on
    }

    private ElotechRps buildRps() {
        //@formatter:off
        return new ElotechRps.Builder()
        .withIdentifier(new CommonsRpsIdentifier.Builder()
                .withType(CommonsRpsType.PROVISIONAL_SERVICE_RECEIPT)
                .withSerie(nfse.getSerie().getSerie())
                .withNumber(nfse.getSerie().getRpsNumber())
                .build())
        .withStatus(CommonsRpsStatus.NORMAL)
        .withEmissionDate(new SimpleDateFormat("yyyy-MM-dd").format(nfse.getEmission()))
        .build();
        //@formatter:on
    }

    private ElotechService buildService() {
        //@formatter:off
        final ElotechService.Builder builder = new ElotechService.Builder()
                .withServiceValues(buildServiceValues())
                .withIssWithheld(nfse.getIssHeld() instanceof NFSeWithIssHeld ? ElotechIssWithheld.YES : ElotechIssWithheld.NO)
                .withDiscrimination(nfse.getService().getDiscrimination())
                .withCityCode(nfse.getService().getCityService().getIbgeCode())
                .withIssRequirement(Optional.ofNullable(nfse.getSpecificData()).filter(NFSeElotechData.class::isInstance).map(NFSeElotechData.class::cast).map(NFSeElotechData::getIssRequirement).orElse(null))
                .withCityIncidenceCode(nfse.getEmitter().getAddress().getCity().getIbgeCode())
                .withServiceItems(buildServiceItems());
        //@formatter:on

        Optional.ofNullable(nfse.getIssHeld()).filter(NFSeWithIssHeld.class::isInstance).map(NFSeWithIssHeld.class::cast).map(NFSeWithIssHeld::getSpecificData)
                .filter(NFSeWithIssHeldElotechData.class::isInstance).map(NFSeWithIssHeldElotechData.class::cast).map(NFSeWithIssHeldElotechData::getResponsibleRetention).ifPresent(rr -> {
                    builder.withResponsibleRetention(rr);
                });

        Optional.ofNullable(nfse.getSpecificData()).filter(NFSeElotechData.class::isInstance).map(NFSeElotechData.class::cast)
                .filter(ed -> ed.getIssRequirement().equals(ElotechIssRequirement.SUSPENDED_BY_JUDICIAL_DECISION)).map(NFSeElotechData::getJudicialProcessNumber).ifPresent(judicialProcessNumber -> {
                    builder.withProcessNumber(judicialProcessNumber);
                });

        return builder.build();

    }

    private ElotechServiceValues buildServiceValues() {
        //@formatter:off
        return new ElotechServiceValues.Builder()
                .withServiceValue(formatNFSeValue(nfse.getService().getServiceValue()))
                .withDeductionValue(formatNFSeValue(nfse.getService().getDeduction()))
                .withPisValue(formatNFSeValue(nfse.getTax().getPisValue()))
                .withCofinsValue(formatNFSeValue(nfse.getTax().getCofinsValue()))
                .withInssValue(formatNFSeValue(nfse.getTax().getInssValue()))
                .withIrValue(formatNFSeValue(nfse.getTax().getIrValue()))
                .withCsllValue(formatNFSeValue(nfse.getTax().getCsllValue()))
                .withOtherRetentionsValue(formatNFSeValue(nfse.getTax().getOtherRetentionsValue()))
                .withIssAliquot(formatNFSeAliquot(nfse.getTax().getIssAliquot()))
                .withDiscountUnconditionedValue(formatNFSeValue(nfse.getService().getDiscount()))
                .build();
        //@formatter:on
    }

    private Collection<ElotechServiceItem> buildServiceItems() {
        //@formatter:off
        final Collection<ElotechServiceItem> items = new HashSet<>();

            items.add(new ElotechServiceItem.Builder()
                    .withItemServiceList(nfse.getService().getNationalServiceCode())
                    .withCnaeCode(nfse.getService().getCnaeCode())
                    .withDescription(nfse.getService().getName())
                    .withTaxable(ElotechServiceItemTaxable.YES) //TODO REVER
                    .withQuantity(formatNFSeValue(nfse.getService().getAmount()))
                    .withUnitaryValue(formatNFSeValue(nfse.getService().getUnitaryValue()))
                    .withDiscountValue(formatNFSeValue(nfse.getService().getDiscount()))
                    .withNetValue(formatNFSeValue(nfse.getService().getServiceValue()))
                    .build());

        return items;
        //@formatter:on
    }

    private ElotechServiceProvider buildServiceProvider() {
        //@formatter:off
        return new ElotechServiceProvider.Builder()
        .withIdentifier(new ElotechServiceProvider.ElotechServiceProviderIdentifier.Builder()
                .withCnp(buildCnp(nfse.getEmitter().getDocuments()))
                .withMunicipalRegistration(Optional.ofNullable(nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                .build())
        .withSocialName(nfse.getEmitter().getName())
        .withAddress(Optional.ofNullable(nfse.getEmitter().getAddress())
                .map(this::buildNFSeAddress).orElse(new ElotechNFSeAddress()))
        .build();
        //@formatter:on
    }

    private ElotechServiceTaker buildServiceTaker() {
        if (nfse.getTaker() == null) {
            return null;
        }

        //@formatter:off
        return new ElotechServiceTaker.Builder()
        .withIdentifier(new ElotechServiceTaker.ElotechServiceTakerIdentifier.Builder()
                .withCnp(buildCnp(nfse.getTaker().getDocuments()))
                .build())
        .withSocialName(nfse.getTaker().getName())
        .withAddress(Optional.ofNullable(nfse.getTaker().getAddress())
                .map(this::buildNFSeAddress).orElse(new ElotechNFSeAddress()))
        .build();
        //@formatter:on
    }

    private ElotechServiceIntermediary buildServiceIntermediary() {
        if (nfse.getIntermediary() == null) {
            return null;
        }

        //@formatter:off
        return new ElotechServiceIntermediary.Builder()
        .withIdentifier(new ElotechServiceIntermediary.ElotechServiceIntermediaryIdentifier.Builder()
                .withCnp(buildCnp(nfse.getIntermediary().getDocuments()))
                .build())
        .withSocialName(nfse.getIntermediary().getName())
        .build();
        //@formatter:on
    }

    private CommonsNFSeCnp buildCnp(final NFSeDocuments documents) {
        if (documents instanceof NFSeLegalEntityDocuments) {
            return new CommonsNFSeCnpj.Builder().withCnpj(documents.getCnp()).build();
        } else if (documents instanceof NFSeNaturalPersonDocuments) {
            return new CommonsNFSeCpf.Builder().withCpf(documents.getCnp()).build();
        } else {
            return null;
        }
    }

    private ElotechNFSeAddress buildNFSeAddress(final NFSeAddress address) {
        //@formatter:off
            return new ElotechNFSeAddress.Builder()
            .withAddress(address.getStreet())
            .withNumber(address.getNumber())
            .withDistrict(address.getDistrict())
            .withCityCode(address.getCity().getIbgeCode().toString())
            .withCityName(address.getCity().getName())
            .withUf(CommonsNFSeUF.findByAcronym(address.getCity().getUf().getAcronym()))
            .withCep(address.getZipCode())
            .build();
        //@formatter:on

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

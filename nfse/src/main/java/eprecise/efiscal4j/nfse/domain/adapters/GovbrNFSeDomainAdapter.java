
package eprecise.efiscal4j.nfse.domain.adapters;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfse.domain.NFSe;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.domain.person.contact.NFSeContact;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeLegalEntityDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeNaturalPersonDocuments;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeWithIssHeld;
import eprecise.efiscal4j.nfse.domain.specificData.NFSeGovbrData;
import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationRequestData;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeAddress;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeUF;
import eprecise.efiscal4j.nfse.tc.commons.person.contacts.CommonsNFSeContact;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnpj;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCpf;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.GovbrNFSeIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.cancel.GovbrCancellationCode;
import eprecise.efiscal4j.nfse.tc.govbr.cancel.GovbrNfseCancelRequest;
import eprecise.efiscal4j.nfse.tc.govbr.lot.GovbrLotRps;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrRps;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrServiceIntermediaryIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrServiceProvider.GovbrServiceProviderIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrServiceTaker;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.service.GovbrService;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.service.GovbrValues;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.GovbrLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.cancel.GovbrNfseDispatchCancel;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.consult.GovbrLotRpsDispatchConsult;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.consult.state.GovbrLotRpsDispatchConsultState;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsType;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


public class GovbrNFSeDomainAdapter implements NFSeDomainAdapter {

    private static final DecimalFormat NFSE_TWO_DECIMALS_FORMAT = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));

    public static final DateFormat NFSE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final DateFormat NFSE_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private final NFSe nfse;

    private final Optional<Certificate> certificate;

    public GovbrNFSeDomainAdapter(final NFSeDomainAdapter.Builder builder) {
        this.nfse = builder.getNfse();
        this.certificate = Optional.ofNullable(builder.getCertificate());
    }

    @Override
    public NFSeRequest toDispatchCancel(final NFSeCancellationRequestData cancellationRequestData) {

        final eprecise.efiscal4j.nfse.tc.govbr.cancel.GovbrNfseCancelRequest.Builder nfseCancelRequestBuilder = new GovbrNfseCancelRequest.Builder()
                .withInfo(new GovbrNfseCancelRequest.GovbrNfseCancelRequestInfo.Builder()
                        .withIdentifier(new GovbrNFSeIdentifier.Builder().withCityCode(this.nfse.getEmitter().getAddress().getCity().getIbgeCode())
                                .withCnpj(this.nfse.getEmitter().getDocuments().getCnp())
                                .withMunicipalRegistration(Optional.ofNullable(this.nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance)
                                        .map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                                .withNumber(cancellationRequestData.getNfseNumber()).build())
                        .withCancellationCode(
                                Optional.ofNullable(cancellationRequestData.getCancellationCode()).filter(GovbrCancellationCode.class::isInstance).map(GovbrCancellationCode.class::cast).orElse(null))
                        .build());

        try {
            return new GovbrNfseDispatchCancel.Builder()
                    .withCancelRequest(this.certificate.isPresent() ? nfseCancelRequestBuilder.build(new DefaultSigner(this.certificate.get())) : nfseCancelRequestBuilder.build()).build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NFSeRequest toDispatchConsultState(final String protocol) {
        return new GovbrLotRpsDispatchConsultState.Builder().withProtocol(protocol).withServiceProviderIdentifier(this.buildServiceProviderIdentifier()).build();
    }

    @Override
    public NFSeRequest toDispatchConsult(final String protocol) {
        return new GovbrLotRpsDispatchConsult.Builder().withProtocol(protocol).withServiceProviderIdentifier(this.buildServiceProviderIdentifier()).build();
    }

    @Override
    public NFSeRequest toDispatch() {
        try {
            final GovbrLotRpsDispatchAsync.Builder lotRpsDispatchBuilder = new GovbrLotRpsDispatchAsync.Builder()
                    .withLotRps(new GovbrLotRps.Builder().withLotNumber(this.nfse.getSerie().getLotNumber()).withRpsQuantity(1).withCnpj(this.nfse.getEmitter().getDocuments().getCnp())
                            .withMunicipalRegistration(Optional.ofNullable(this.nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance)
                                    .map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                            .withRpsList(Arrays.asList(this.buildRps())).build());
            if (this.certificate.isPresent()) {
                return lotRpsDispatchBuilder.build(new DefaultSigner(this.certificate.get()));
            } else {
                return lotRpsDispatchBuilder.build();
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private GovbrRps buildRps() {
        //@formatter:off
        return new GovbrRps.Builder()
                .withInfo(new GovbrRps.Info.Builder()
                        .withIdentifier(new CommonsRpsIdentifier.Builder()
                                .withType(CommonsRpsType.PROVISIONAL_SERVICE_RECEIPT)
                                .withSerie(this.nfse.getSerie().getSerie())
                                .withNumber(this.nfse.getSerie().getRpsNumber())
                                .build())
                        .withEmissionDate(NFSE_DATETIME_FORMAT.format(this.nfse.getEmission()))
                        .withNatureOperation(Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeGovbrData.class::isInstance).map(NFSeGovbrData.class::cast).map(NFSeGovbrData::getNatureOperation).orElse(null))
                        .withSpecialTaxationRegime(Optional.ofNullable(this.nfse.getEmitter().getSpecialTaxationRegime()).filter(GovbrSpecialTaxationRegime.class::isInstance).map(GovbrSpecialTaxationRegime.class::cast).orElse(null))
                        .withSimpleNational(Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeGovbrData.class::isInstance).map(NFSeGovbrData.class::cast).map(NFSeGovbrData::getSimpleNational).map(sn->sn ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO).orElse(null))
                        .withCulturalPromoter(Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeGovbrData.class::isInstance).map(NFSeGovbrData.class::cast).map(NFSeGovbrData::getCulturalPromoter).map(sn->sn ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO).orElse(null))
                        .withStatus(CommonsRpsStatus.NORMAL)
                        .withService(this.buildService())
                        .withServiceProviderIdentifier(this.buildServiceProviderIdentifier())
                        .withServiceTaker(this.buildServiceTaker())
                        .withServiceIntermediaryIdentifier(this.buildServiceIntermediaryIdentifier())
                        .build()).build();


        //@formatter:on
    }

    private GovbrService buildService() {
        //@formatter:off
        final GovbrService.Builder builder = new GovbrService.Builder()
                .withServiceValues(this.buildServiceValues())
                .withItemServiceList(this.nfse.getService().getNationalServiceCode())
                .withCnaeCode(this.nfse.getService().getCnaeCode())
                .withMunicipalTaxCode(this.nfse.getService().getCnaeCode()) //TODO REVER
                .withDiscrimination(Optional.ofNullable(this.nfse.getService()).map(s->s.getDiscrimination()).map(StringUtils::stripAccents).orElse(null))
                .withCityCode(this.nfse.getService().getCityService().getIbgeCode());

        return builder.build();

    }

    private GovbrValues buildServiceValues() {
        //@formatter:off
        return new GovbrValues.Builder()
                .withServiceValue(this.formatNFSeValue(this.nfse.getService().getGrossValue()))
                .withDeductionValue(this.formatNFSeValue(this.nfse.getService().getDeduction()))
                .withPisValue(this.formatNFSeValue(this.nfse.getTax().getPisValue()))
                .withCofinsValue(this.formatNFSeValue(this.nfse.getTax().getCofinsValue()))
                .withInssValue(this.formatNFSeValue(this.nfse.getTax().getInssValue()))
                .withIrValue(this.formatNFSeValue(this.nfse.getTax().getIrValue()))
                .withCsllValue(this.formatNFSeValue(this.nfse.getTax().getCsllValue()))
                .withIssWithheld(this.nfse.getIssHeld() instanceof NFSeWithIssHeld ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO)
                .withIssValue(this.formatNFSeValue(this.nfse.getTax().getIssValue()))
                .withIssWithhelValue(this.formatNFSeValue(this.nfse.getTax().getIssRetentionValue()))
                .withOtherRetentionsValue(this.formatNFSeValue(this.nfse.getTax().getOtherRetentionsValue()))
                .withBcValue(this.formatNFSeValue(this.nfse.getTax().getBcValue()))
                .withIssAliquot(this.formatNFSeAliquot(this.nfse.getTax().getIssAliquot()))
                .withNetValue(this.formatNFSeValue(this.nfse.getService().getNetValue().add(this.nfse.getTax().getTotal())))
                .withDiscountUnconditionedValue(this.formatNFSeValue(this.nfse.getService().getDiscount()))
                .build();
        //@formatter:on
    }

    private GovbrServiceProviderIdentifier buildServiceProviderIdentifier() {
        //@formatter:off
        return new GovbrServiceProviderIdentifier.Builder()
                .withCnpj(this.nfse.getEmitter().getDocuments().getCnp())
                .withMunicipalRegistration(Optional.ofNullable(this.nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                .build();
        //@formatter:on
    }

    private GovbrServiceTaker buildServiceTaker() {
        if (this.nfse.getTaker() == null) {
            return null;
        }

        //@formatter:off
        return new GovbrServiceTaker.Builder()
        .withIdentifier(new GovbrServiceTaker.GovbrServiceTakerIdentifier.Builder()
                .withCnp(this.buildCnp(this.nfse.getTaker().getDocuments()))
                .withMunicipalRegistration(Optional.ofNullable(this.nfse.getTaker().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                .build())
        .withSocialName(this.nfse.getTaker().getName())
        .withAddress(Optional.ofNullable(this.nfse.getTaker().getAddress())
                .map(this::buildNFSeAddress).orElse(new CommonsNFSeAddress()))
        .withContact(this.buildNFSeContacts(this.nfse.getTaker().getContact()))
        .build();
        //@formatter:on
    }

    private GovbrServiceIntermediaryIdentifier buildServiceIntermediaryIdentifier() {
        if (this.nfse.getIntermediary() == null) {
            return null;
        }

        //@formatter:off
        return new GovbrServiceIntermediaryIdentifier.Builder()
                .withSocialName(this.nfse.getIntermediary().getName())
                .withCnp(this.buildCnp(this.nfse.getIntermediary().getDocuments()))
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

    private CommonsNFSeContact buildNFSeContacts(final NFSeContact contact) {
        return Optional.ofNullable(contact)
                .map(c -> new CommonsNFSeContact.Builder().withEmail(c.getEmail())
                        .withPhone(Optional.ofNullable(c.getPhone()).map(str -> str.replaceAll("[^\\d.]", "")).map(str -> str.substring(0, str.length() < 11 ? str.length() : 11)).orElse(null))
                        .build())
                .orElse(null);
    }

    private CommonsNFSeAddress buildNFSeAddress(final NFSeAddress address) {
        if (address == null) {
            return null;
        }
        //@formatter:off
            return new CommonsNFSeAddress.Builder()
            .withAddress(address.getStreet())
            .withNumber(address.getNumber())
            .withDistrict(address.getDistrict())
            .withCityCode(Optional.ofNullable(address.getCity()).map(a->a.getIbgeCode()).orElse(null))
            .withUf(Optional.ofNullable(address.getCity()).map(c -> CommonsNFSeUF.findByAcronym(c.getUf().getAcronym())).orElse(null))
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

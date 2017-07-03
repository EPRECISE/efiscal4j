
package eprecise.efiscal4j.nfse.domain.adapters;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

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
        nfse = builder.getNfse();
        certificate = Optional.ofNullable(builder.getCertificate());
    }

    @Override
    public NFSeRequest toDispatchCancel(final NFSeCancellationRequestData cancellationRequestData) {

        final eprecise.efiscal4j.nfse.tc.govbr.cancel.GovbrNfseCancelRequest.Builder nfseCancelRequestBuilder = new GovbrNfseCancelRequest.Builder().withInfo(new GovbrNfseCancelRequest.Info.Builder()
                .withIdentifier(new GovbrNFSeIdentifier.Builder().withCityCode(nfse.getEmitter().getAddress().getCity().getIbgeCode()).withCnpj(nfse.getEmitter().getDocuments().getCnp())
                        .withMunicipalRegistration(Optional.ofNullable(nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast)
                                .map(NFSeLegalEntityDocuments::getIm).orElse(null))
                        .withNumber(cancellationRequestData.getNfseNumber()).build())
                .withCancellationCode(
                        Optional.ofNullable(cancellationRequestData.getCancellationCode()).filter(GovbrCancellationCode.class::isInstance).map(GovbrCancellationCode.class::cast).orElse(null))
                .build());

        try {
            return new GovbrNfseDispatchCancel.Builder()
                    .withCancelRequest(certificate.isPresent() ? nfseCancelRequestBuilder.build(new DefaultSigner(certificate.get())) : nfseCancelRequestBuilder.build()).build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NFSeRequest toDispatchConsultState(final String protocol) {
        return new GovbrLotRpsDispatchConsultState.Builder().withProtocol(protocol).withServiceProviderIdentifier(buildServiceProviderIdentifier()).build();
    }

    @Override
    public NFSeRequest toDispatchConsult(final String protocol) {
        return new GovbrLotRpsDispatchConsult.Builder().withProtocol(protocol).withServiceProviderIdentifier(buildServiceProviderIdentifier()).build();
    }

    @Override
    public NFSeRequest toDispatch() {
        try {
            final GovbrLotRpsDispatchAsync.Builder lotRpsDispatchBuilder = new GovbrLotRpsDispatchAsync.Builder()
                    .withLotRps(new GovbrLotRps.Builder().withLotNumber(nfse.getSerie().getLotNumber()).withRpsQuantity(1)
                            .withCnpj(nfse.getEmitter().getDocuments().getCnp()).withMunicipalRegistration(Optional.ofNullable(nfse.getEmitter().getDocuments())
                                    .filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                            .withRpsList(Arrays.asList(buildRps())).build());
            if (certificate.isPresent()) {
                return lotRpsDispatchBuilder.build(new DefaultSigner(certificate.get()));
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
                                .withSerie(nfse.getSerie().getSerie())
                                .withNumber(nfse.getSerie().getRpsNumber())
                                .build())
                        .withEmissionDate(NFSE_DATETIME_FORMAT.format(nfse.getEmission()))
                        .withNatureOperation(Optional.ofNullable(nfse.getSpecificData()).filter(NFSeGovbrData.class::isInstance).map(NFSeGovbrData.class::cast).map(NFSeGovbrData::getNatureOperation).orElse(null))
                        .withSpecialTaxationRegime(Optional.ofNullable(nfse.getEmitter().getSpecialTaxationRegime()).filter(GovbrSpecialTaxationRegime.class::isInstance).map(GovbrSpecialTaxationRegime.class::cast).orElse(null))
                        .withSimpleNational(Optional.ofNullable(nfse.getSpecificData()).filter(NFSeGovbrData.class::isInstance).map(NFSeGovbrData.class::cast).map(NFSeGovbrData::getSimpleNational).map(sn->sn ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO).orElse(null))
                        .withCulturalPromoter(Optional.ofNullable(nfse.getSpecificData()).filter(NFSeGovbrData.class::isInstance).map(NFSeGovbrData.class::cast).map(NFSeGovbrData::getCulturalPromoter).map(sn->sn ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO).orElse(null))
                        .withStatus(CommonsRpsStatus.NORMAL)
                        .withService(buildService())
                        .withServiceProviderIdentifier(buildServiceProviderIdentifier())
                        .withServiceTaker(buildServiceTaker())
                        .withServiceIntermediaryIdentifier(buildServiceIntermediaryIdentifier())
                        .build()).build();


        //@formatter:on
    }

    private GovbrService buildService() {
        //@formatter:off
        final GovbrService.Builder builder = new GovbrService.Builder()
                .withServiceValues(buildServiceValues())
                .withItemServiceList(nfse.getService().getNationalServiceCode())
                .withCnaeCode(nfse.getService().getCnaeCode())
                .withMunicipalTaxCode(nfse.getService().getCnaeCode()) //TODO REVER
                .withDiscrimination(nfse.getService().getDiscrimination())
                .withCityCode(nfse.getService().getCityService().getIbgeCode());

        return builder.build();

    }

    private GovbrValues buildServiceValues() {
        //@formatter:off
        return new GovbrValues.Builder()
                .withServiceValue(formatNFSeValue(nfse.getService().getServiceValue()))
                .withDeductionValue(formatNFSeValue(nfse.getService().getDeduction()))
                .withPisValue(formatNFSeValue(nfse.getTax().getPisValue()))
                .withCofinsValue(formatNFSeValue(nfse.getTax().getCofinsValue()))
                .withInssValue(formatNFSeValue(nfse.getTax().getInssValue()))
                .withIrValue(formatNFSeValue(nfse.getTax().getIrValue()))
                .withCsllValue(formatNFSeValue(nfse.getTax().getCsllValue()))
                .withIssWithheld(nfse.getIssHeld() instanceof NFSeWithIssHeld ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO)
                .withIssValue(formatNFSeValue(nfse.getTax().getIssValue()))
                .withIssWithhelValue(formatNFSeValue(nfse.getTax().getIssRetentionValue()))
                .withOtherRetentionsValue(formatNFSeValue(nfse.getTax().getOtherRetentionsValue()))
                .withBcValue(formatNFSeValue(nfse.getTax().getBcValue()))
                .withIssAliquot(formatNFSeAliquot(nfse.getTax().getIssAliquot()))
                .withNetValue(formatNFSeValue(nfse.getNetValue()))
                .withDiscountUnconditionedValue(formatNFSeValue(nfse.getService().getDiscount()))
                .build();
        //@formatter:on
    }

    private GovbrServiceProviderIdentifier buildServiceProviderIdentifier() {
        //@formatter:off
        return new GovbrServiceProviderIdentifier.Builder()
                .withCnpj(nfse.getEmitter().getDocuments().getCnp())
                .withMunicipalRegistration(Optional.ofNullable(nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                .build();
        //@formatter:on
    }

    private GovbrServiceTaker buildServiceTaker() {
        if (nfse.getTaker() == null) {
            return null;
        }

        //@formatter:off
        return new GovbrServiceTaker.Builder()
        .withIdentifier(new GovbrServiceTaker.GovbrServiceTakerIdentifier.Builder()
                .withCnp(buildCnp(nfse.getTaker().getDocuments()))
                .withMunicipalRegistration(Optional.ofNullable(nfse.getTaker().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                .build())
        .withSocialName(nfse.getTaker().getName())
        .withAddress(Optional.ofNullable(nfse.getTaker().getAddress())
                .map(this::buildNFSeAddress).orElse(new CommonsNFSeAddress()))
        .withContact(buildNFSeContacts(nfse.getTaker().getContact()))
        .build();
        //@formatter:on
    }

    private GovbrServiceIntermediaryIdentifier buildServiceIntermediaryIdentifier() {
        if (nfse.getIntermediary() == null) {
            return null;
        }

        //@formatter:off
        return new GovbrServiceIntermediaryIdentifier.Builder()
                .withSocialName(nfse.getIntermediary().getName())
                .withCnp(buildCnp(nfse.getIntermediary().getDocuments()))
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

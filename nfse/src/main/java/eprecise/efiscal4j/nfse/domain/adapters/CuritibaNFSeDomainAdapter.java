
package eprecise.efiscal4j.nfse.domain.adapters;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import eprecise.efiscal4j.nfse.domain.person.address.NFSeCity;
import eprecise.efiscal4j.nfse.domain.person.contact.NFSeContact;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeLegalEntityDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeNaturalPersonDocuments;
import eprecise.efiscal4j.nfse.domain.service.NFSeService;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeWithIssHeld;
import eprecise.efiscal4j.nfse.domain.specificData.curitiba.NFSeCuritibaData;
import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationRequestData;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeAddress;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeUF;
import eprecise.efiscal4j.nfse.tc.commons.person.contacts.CommonsNFSeContact;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnpj;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCpf;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.curitiba.CuritibaNFSeIdentifier;
import eprecise.efiscal4j.nfse.tc.curitiba.cancel.CuritibaCancellationCode;
import eprecise.efiscal4j.nfse.tc.curitiba.cancel.CuritibaNfseCancelRequest;
import eprecise.efiscal4j.nfse.tc.curitiba.intermediary.CuritibaServiceIntermediaryIdentifier;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.CuritibaLotRps;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.CuritibaRps;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.CuritibaSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.services.CuritibaService;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.services.CuritibaValues;
import eprecise.efiscal4j.nfse.tc.curitiba.provider.CuritibaServiceProviderIdentifier;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.CuritibaLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.cancel.CuritibaNfseDispatchCancel;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.CuritibaLotRpsDispatchConsult;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.state.CuritibaLotRpsDispatchConsultState;
import eprecise.efiscal4j.nfse.tc.curitiba.taker.CuritibaServiceTaker;
import eprecise.efiscal4j.nfse.tc.curitiba.taker.CuritibaServiceTakerIdentifier;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsType;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


public class CuritibaNFSeDomainAdapter implements NFSeDomainAdapter {

    private static final DecimalFormat NFSE_TWO_DECIMALS_FORMAT = new DecimalFormat("##0.0000", new DecimalFormatSymbols(Locale.ENGLISH));

    public static final DateFormat NFSE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final DateFormat NFSE_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private final NFSe nfse;

    private final Optional<Certificate> certificate;

    public CuritibaNFSeDomainAdapter(final NFSeDomainAdapter.Builder builder) {
        this.nfse = builder.getNfse();
        this.certificate = Optional.ofNullable(builder.getCertificate());
    }

    @Override
    public NFSeRequest toDispatchCancel(final NFSeCancellationRequestData cancellationRequestData) {

        final eprecise.efiscal4j.nfse.tc.curitiba.cancel.CuritibaNfseCancelRequest.Builder nfseCancelRequestBuilder = new CuritibaNfseCancelRequest.Builder()
                .withInfo(CuritibaNfseCancelRequest.CuritibaNfseCancelRequestInfo.builder()
                        .identifier(CuritibaNFSeIdentifier.builder()
                                .cityCode(this.nfse.getEmitter().getAddress().getCity().getIbgeCode())
                                .cnpj(this.nfse.getEmitter().getDocuments().getCnp())
                                .municipalRegistration(Optional.ofNullable(this.nfse.getEmitter().getDocuments())
                                        .filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast)
                                        .map(NFSeLegalEntityDocuments::getIm).orElse(null))
                                .number(cancellationRequestData.getNfseNumber()).build())
                        .cancellationCode(Optional.ofNullable(cancellationRequestData.getCancellationCode())
                                .filter(CuritibaCancellationCode.class::isInstance).map(CuritibaCancellationCode.class::cast).orElse(null))
                        .build());

        try {
            return new CuritibaNfseDispatchCancel.Builder().withCancelRequest(
                    this.certificate.isPresent() ? nfseCancelRequestBuilder.build(new DefaultSigner(this.certificate.get()))
                            : nfseCancelRequestBuilder.build())
                    .build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NFSeRequest toDispatchConsultState(final String protocol) {
        return new CuritibaLotRpsDispatchConsultState.Builder().withProtocol(protocol)
                .withServiceProviderIdentifier(this.buildServiceProviderIdentifier()).build();
    }

    @Override
    public NFSeRequest toDispatchConsult(final String protocol) {
        return new CuritibaLotRpsDispatchConsult.Builder().withProtocol(protocol)
                .withServiceProviderIdentifier(this.buildServiceProviderIdentifier()).build();
    }

    @Override
    public NFSeRequest toDispatch() {
        try {
            final CuritibaLotRpsDispatchAsync.Builder lotRpsDispatchBuilder = new CuritibaLotRpsDispatchAsync.Builder()
                    .withLotRps(CuritibaLotRps.builder().lotNumber(this.nfse.getSerie().getLotNumber()).quantity(1)
                            .cnpj(this.nfse.getEmitter().getDocuments().getCnp())
                            .municipalRegistration(Optional.ofNullable(this.nfse.getEmitter().getDocuments())
                                    .filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast)
                                    .map(NFSeLegalEntityDocuments::getIm).orElse(null))
                            .rpsList(Arrays.asList(this.buildRps())).build());
            if (this.certificate.isPresent()) {
                return lotRpsDispatchBuilder.build(new DefaultSigner(this.certificate.get()));
            } else {
                return lotRpsDispatchBuilder.build();
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CuritibaRps buildRps() {
        //@formatter:off
        return CuritibaRps.builder()
                .info(CuritibaRps.CuritibaRpsInfo.builder()
                        .identifier(new CommonsRpsIdentifier.Builder()
                                .withType(CommonsRpsType.PROVISIONAL_SERVICE_RECEIPT)
                                .withSerie(this.nfse.getSerie().getSerie())
                                .withNumber(this.nfse.getSerie().getRpsNumber())
                                .build())
                        .emissionDate(NFSE_DATETIME_FORMAT.format(this.nfse.getEmission()))
                        .natureOperation(Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeCuritibaData.class::isInstance).map(NFSeCuritibaData.class::cast).map(NFSeCuritibaData::getNatureOperation).orElse(null))
                        .specialTaxationRegime(Optional.ofNullable(this.nfse.getEmitter().getSpecialTaxationRegime()).filter(CuritibaSpecialTaxationRegime.class::isInstance).map(CuritibaSpecialTaxationRegime.class::cast).orElse(null))
                        .nationalSimple(Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeCuritibaData.class::isInstance).map(NFSeCuritibaData.class::cast).map(NFSeCuritibaData::getSimpleNational).map(sn->sn ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO).orElse(null))
                        .culturalPromoter(Optional.ofNullable(this.nfse.getSpecificData()).filter(NFSeCuritibaData.class::isInstance).map(NFSeCuritibaData.class::cast).map(NFSeCuritibaData::getCulturalPromoter).map(sn->sn ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO).orElse(null))
                        .status(CommonsRpsStatus.NORMAL)
                        .service(this.buildService())
                        .serviceProviderIdentifier(this.buildServiceProviderIdentifier())
                        .serviceTaker(this.buildServiceTaker())
                        .serviceIntermediary(this.buildServiceIntermediaryIdentifier())
                        .build()).build();


        //@formatter:on
    }

    private CuritibaService buildService() {
        //@formatter:off
        return CuritibaService.builder()
                .values(this.buildServiceValues())
                .itemServiceList(this.nfse.getService().getNationalServiceCode())
                .cnaeCode(this.nfse.getService().getCnaeCode())
                .assessmentCityCode(this.nfse.getService().getCnaeCode()) //TODO REVER
                .discrimination(Optional.ofNullable(this.nfse.getService()).map(NFSeService::getDiscrimination).map(StringUtils::stripAccents).orElse(null))
                .cityCode(this.nfse.getService().getCityService().getIbgeCode()).build();

    }

    private CuritibaValues buildServiceValues() {
        //@formatter:off
        return CuritibaValues.builder()
                .serviceValue(this.formatNFSeValue(this.nfse.getService().getGrossValue()))
                .deductionValue(this.formatNFSeValue(this.nfse.getService().getDeduction()))
                .pisValue(this.formatNFSeValue(this.nfse.getTax().getPisValue()))
                .cofinsValue(this.formatNFSeValue(this.nfse.getTax().getCofinsValue()))
                .inssValue(this.formatNFSeValue(this.nfse.getTax().getInssValue()))
                .irValue(this.formatNFSeValue(this.nfse.getTax().getIrValue()))
                .csllValue(this.formatNFSeValue(this.nfse.getTax().getCsllValue()))
                .issWithHeld(this.nfse.getIssHeld() instanceof NFSeWithIssHeld ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO)
                .issValue(this.formatNFSeValue(this.nfse.getTax().getIssValue()))
                .issWithHeldValue(this.formatNFSeValue(this.nfse.getTax().getIssRetentionValue()))
                .otherRetentionsValue(this.formatNFSeValue(this.nfse.getTax().getOtherRetentionsValue()))
                .bcValue(this.formatNFSeValue(this.nfse.getTax().getBcValue()))
                .issAliquot(this.formatNFSeAliquot(this.nfse.getTax().getIssAliquot()))
                .nfseNetValue(this.formatNFSeValue(this.nfse.getService().getNetValue().add(this.nfse.getTax().getTotal())))
                .discountUnconditionedValue(this.formatNFSeValue(this.nfse.getService().getDiscount().getUnconditionedValue()))
                .discountConditionedValue(this.formatNFSeValue(this.nfse.getService().getDiscount().getConditionedValue()))
                .build();
        //@formatter:on
    }

    private CuritibaServiceProviderIdentifier buildServiceProviderIdentifier() {
        //@formatter:off
        return CuritibaServiceProviderIdentifier.builder()
                .cnpj(Optional.ofNullable(this.nfse.getEmitter().getDocuments().getCnp()).orElse(null))
                .municipalRegistration(Optional.ofNullable(this.nfse.getEmitter().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                .build();
        //@formatter:on
    }

    private CuritibaServiceTaker buildServiceTaker() {
        if (this.nfse.getTaker() == null) {
            return null;
        }

        //@formatter:off
        return CuritibaServiceTaker.builder()
        .identifier(CuritibaServiceTakerIdentifier.builder()
                .cnp(this.buildCnp(this.nfse.getTaker().getDocuments()))
                .municipalRegistration(Optional.ofNullable(this.nfse.getTaker().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
                .build())
        .socialName(this.nfse.getTaker().getName())
        .address(Optional.ofNullable(this.nfse.getTaker().getAddress())
                .map(this::buildNFSeAddress).orElse(new CommonsNFSeAddress()))
        .contact(this.buildNFSeContacts(this.nfse.getTaker().getContact()))
        .build();
        //@formatter:on
    }

    private CuritibaServiceIntermediaryIdentifier buildServiceIntermediaryIdentifier() {
        if (this.nfse.getIntermediary() == null) {
            return null;
        }

        //@formatter:off
        return CuritibaServiceIntermediaryIdentifier.builder()
                .socialName(this.nfse.getIntermediary().getName())
                .cnp(this.buildCnp(this.nfse.getIntermediary().getDocuments()))
                .municipalRegistration(Optional.ofNullable(this.nfse.getIntermediary().getDocuments()).filter(NFSeLegalEntityDocuments.class::isInstance).map(NFSeLegalEntityDocuments.class::cast).map(NFSeLegalEntityDocuments::getIm).orElse(null))
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
        return Optional
                .ofNullable(contact).map(
                        c -> new CommonsNFSeContact.Builder().withEmail(c.getEmail())
                                .withPhone(Optional.ofNullable(c.getPhone()).map(str -> str.replaceAll("[^\\d.]", ""))
                                        .map(str -> str.substring(0, str.length() < 11 ? str.length() : 11)).orElse(null))
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
            .withCityCode(Optional.ofNullable(address.getCity()).map(NFSeCity::getIbgeCode).orElse(null))
            .withUf(Optional.ofNullable(address.getCity()).map(c -> CommonsNFSeUF.findByAcronym(c.getUf().getAcronym())).orElse(null))
            .withCep(address.getZipCode())
            .build();
        //@formatter:on

    }

    private String formatNfseString(final String input, final int size) {
        return Optional.ofNullable(StringUtils.upperCase(StringUtils.stripAccents(this.abbreviate(this.nullIfEmpty(input), size))))
                .map(string -> string.replaceAll("\n", "  ").replaceAll("\r", "  ").replace("\t", "  ")).orElse(null);
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
            return NFSE_TWO_DECIMALS_FORMAT.format(value.setScale(4).divide(new BigDecimal(100), RoundingMode.HALF_UP));
        }

    }

}

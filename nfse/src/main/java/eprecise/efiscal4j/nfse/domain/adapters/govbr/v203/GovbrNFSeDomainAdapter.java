
package eprecise.efiscal4j.nfse.domain.adapters.govbr.v203;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.nfse.domain.NFSe;
import eprecise.efiscal4j.nfse.domain.adapters.NFSeDomainAdapter;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeCity;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeUF;
import eprecise.efiscal4j.nfse.domain.person.contact.NFSeContact;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeLegalEntityDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeNaturalPersonDocuments;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeWithIssHeld;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeWithIssHeldSpecificData;
import eprecise.efiscal4j.nfse.domain.specificData.govbr.v203.NFSeGovbrData;
import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationRequestData;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeUF;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnpj;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCpf;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.v203.GovbrNFSeIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.v203.cancel.GovbrCancellationCode;
import eprecise.efiscal4j.nfse.tc.govbr.v203.cancel.GovbrNFSeCancelRequest;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.GovbrLotRps;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.GovbrIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.GovbrServiceIntermediary;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.GovbrServiceTaker;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.GovbrSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.GovbrStatementProvisionService;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.rps.GovbrRps;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.services.GovbrIssRequirement;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.services.GovbrService;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.services.GovbrValues;
import eprecise.efiscal4j.nfse.tc.govbr.v203.person.address.GovbrNFSeAddress;
import eprecise.efiscal4j.nfse.tc.govbr.v203.person.contact.GovbrNFSeContact;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.GovbrLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel.GovbrNFSeDispatchCancel;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsType;


public class GovbrNFSeDomainAdapter implements NFSeDomainAdapter {

    private static final DecimalFormat NFSE_TWO_DECIMALS_FORMAT = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));

    public static final DateFormat NFSE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final NFSe nfse;

    public GovbrNFSeDomainAdapter(final NFSeDomainAdapter.Builder builder) {
        this.nfse = builder.getNfse();
    }

    @Override
    public NFSeRequest toDispatch() {
      //@formatter:off
        return GovbrLotRpsDispatchSync.builder()
                .rpsLot(GovbrLotRps.builder()
                        .lotNumber(this.nfse.getSerie().getLotNumber())
                        .cnp(this.buildCnp(this.nfse.getEmitter().getDocuments())) 
                        .municipalRegistration(this.buildMunicipalRegistration(this.nfse.getEmitter().getDocuments()))
                        .quantity(1)
                        .statementProvisionServices(Arrays.asList(this.buildStatementProvisionService()))
                        .build())
                .build();
      //@formatter:on
    }

    @Override
    public NFSeRequest toDispatchCancel(NFSeCancellationRequestData cancellationRequestData) {
        //@formatter:off
        return GovbrNFSeDispatchCancel.builder()
                .request(GovbrNFSeCancelRequest.builder()
                        .info(GovbrNFSeCancelRequest.GovbrNFSeCancelRequestInfo.builder()
                                .nfseIdentifier(GovbrNFSeIdentifier.builder()
                                        .lotNumber(cancellationRequestData.getNfseNumber())
                                        .cnp(this.buildCnp(this.nfse.getEmitter().getDocuments()))
                                        .municipalRegistration(this.buildMunicipalRegistration(this.nfse.getEmitter().getDocuments()))
                                        .ibgeCode(this.nfse.getEmitter().getAddress().getCity().getIbgeCode())
                                        .build())
                                .cancellationCode(Optional.ofNullable(cancellationRequestData.getCancellationCode())
                                        .filter(GovbrCancellationCode.class::isInstance)
                                        .map(GovbrCancellationCode.class::cast)
                                        .orElse(null))
                                .build())
                        .build())
                .build();
        //@formatter:on
    }

    private GovbrStatementProvisionService buildStatementProvisionService() {
        //@formatter:off
        return GovbrStatementProvisionService.builder()
                .info(GovbrStatementProvisionService.GovbrStatementProvisionServiceInfo.builder()
                        .competence(NFSE_DATE_FORMAT.format(this.nfse.getEmission()))
                        .rps(this.buildRps())
                        .service(this.buildService())
                        .serviceProviderIdentifier(this.buildServiceProviderIdentifier())
                        .serviceTaker(this.buildServiceTaker())
                        .serviceIntermediary(this.buildServiceIntermediary())
                        .specialTaxationRegime(Optional.ofNullable(this.nfse.getEmitter().getSpecialTaxationRegime())
                                .filter(GovbrSpecialTaxationRegime.class::isInstance)
                                .map(GovbrSpecialTaxationRegime.class::cast)
                                .orElse(null))
                        .taxIncentive(Optional.ofNullable(this.nfse.getSpecificData())
                                .filter(NFSeGovbrData.class::isInstance)
                                .map(NFSeGovbrData.class::cast)
                                .map(NFSeGovbrData::isTaxIncentive)
                                .map(this::formatNFSeBoolean)
                                .orElse(null))
                        .nationalSimple(Optional.ofNullable(this.nfse.getSpecificData())
                                .filter(NFSeGovbrData.class::isInstance)
                                .map(NFSeGovbrData.class::cast)
                                .map(NFSeGovbrData::isNationalSimple)
                                .map(this::formatNFSeBoolean)
                                .orElse(null))
                        .build())
                .build();
        //@formatter:on
    }

    private GovbrRps buildRps() {
      //@formatter:off
        return GovbrRps.builder()
                .identifier(new CommonsRpsIdentifier.Builder()
                        .withType(CommonsRpsType.PROVISIONAL_SERVICE_RECEIPT)
                        .withSerie(this.nfse.getSerie().getSerie())
                        .withNumber(this.nfse.getSerie().getRpsNumber())
                        .build())
                .emissionDate(NFSE_DATE_FORMAT.format(this.nfse.getEmission()))
                .status(CommonsRpsStatus.NORMAL)
                .build();
      //@formatter:on
    }

    private GovbrService buildService() {
        //@formatter:off
          return GovbrService.builder()
                  .values(this.buildServiceValues())
                  .issWithHeld(this.formatNFSeBoolean(this.nfse.getIssHeld() instanceof NFSeWithIssHeld))
                  .responsibleRetention(Optional.ofNullable(this.nfse.getIssHeld())
                          .filter(NFSeWithIssHeld.class::isInstance)
                          .map(NFSeWithIssHeld.class::cast)
                          .map(NFSeWithIssHeld::getSpecificData)
                          .map(NFSeWithIssHeldSpecificData::getResponsibleRetention)
                          .orElse(null))
                  .itemServiceList(StringUtils.leftPad(this.nfse.getService().getNationalServiceCode(), 5, "0"))
                  .cnaeCode(this.nfse.getService().getCnaeCode())
                  .assessmentCityCode(this.nfse.getService().getCnaeCode())
                  .discrimination(this.formatNfseString(this.nfse.getService().getDiscrimination(), 1000))
                  .cityCode(this.nfse.getService().getCityService().getIbgeCode())
                  .issRequirement(Optional.ofNullable(this.nfse.getSpecificData())
                          .filter(NFSeGovbrData.class::isInstance)
                          .map(NFSeGovbrData.class::cast)
                          .map(NFSeGovbrData::getIssRequirement)
                          .orElse(null))
                  .cityIncidenceCode(this.nfse.getEmitter().getAddress().getCity().getIbgeCode())
                  .processNumber(Optional.ofNullable(this.nfse.getSpecificData())
                          .filter(NFSeGovbrData.class::isInstance)
                          .map(NFSeGovbrData.class::cast)
                          .filter(specialData -> specialData.getIssRequirement().equals(GovbrIssRequirement.SUSPENDED_BY_JUDICIAL_DECISION))
                          .map(NFSeGovbrData::getProcessNumber)
                          .orElse(null))
                  .build();
        //@formatter:on
    }

    private GovbrValues buildServiceValues() {
        //@formatter:off
          return GovbrValues.builder()
                  .serviceValue(this.formatNFSeValue(this.nfse.getService().getNetValue()))
                  .deductionValue(this.formatNFSeValue(this.nfse.getService().getDeduction()))
                  .pisValue(this.formatNFSeValue(this.nfse.getTax().getPisValue()))
                  .cofinsValue(this.formatNFSeValue(this.nfse.getTax().getCofinsValue()))
                  .inssValue(this.formatNFSeValue(this.nfse.getTax().getInssValue()))
                  .irValue(this.formatNFSeValue(this.nfse.getTax().getIrValue()))
                  .csllValue(this.formatNFSeValue(this.nfse.getTax().getCsllValue()))
                  .otherRetentionsValue(this.formatNFSeValue(this.nfse.getTax().getOtherRetentionsValue()))
                  .totalTaxValue(this.formatNFSeValue(this.nfse.getTax().getTotal())) //TODO VERIFICAR
                  .issValue(this.formatNFSeValue(this.nfse.getTax().getIssValue()))
                  .issAliquot(this.formatNFSeValue(this.nfse.getTax().getIssAliquot()))
                  .discountUnconditionedValue(this.formatNFSeValue(this.nfse.getService().getDiscount()))
                  .discountConditionedValue(this.formatNFSeValue(BigDecimal.ZERO)) //TODO VERIFICAR
                  .build();
        //@formatter:on
    }

    private GovbrIdentifier buildServiceProviderIdentifier() {
      //@formatter:off
        return GovbrIdentifier.builder()
                .cnp(this.buildCnp(this.nfse.getEmitter().getDocuments()))
                .municipalRegistration(this.buildMunicipalRegistration(this.nfse.getEmitter().getDocuments()))
                .build();
      //@formatter:on
    }

    private GovbrServiceTaker buildServiceTaker() {
        if (this.nfse.getTaker() == null) {
            return null;
        }

        //@formatter:off
          return GovbrServiceTaker.builder()
                  .identifier(GovbrIdentifier.builder()
                          .cnp(this.buildCnp(this.nfse.getTaker().getDocuments()))
                          .municipalRegistration(this.buildMunicipalRegistration(this.nfse.getTaker().getDocuments()))
                          .build())
                  .socialName(this.formatNfseString(this.nfse.getTaker().getName(), 150))
                  .address(Optional.ofNullable(this.nfse.getTaker().getAddress())
                          .map(this::buildNFSeAddress)
                          .orElse(new GovbrNFSeAddress()))
                  .contact(this.buildNFSeContact(this.nfse.getTaker().getContact()))
                  .build();
        //@formatter:on
    }

    private GovbrServiceIntermediary buildServiceIntermediary() {
        if (this.nfse.getIntermediary() == null) {
            return null;
        }

        //@formatter:off
          return GovbrServiceIntermediary.builder()
                  .identifier(GovbrIdentifier.builder()
                          .cnp(this.buildCnp(this.nfse.getIntermediary().getDocuments()))
                          .municipalRegistration(this.buildMunicipalRegistration(this.nfse.getIntermediary().getDocuments()))
                          .build())
                  .socialName(this.formatNfseString(this.nfse.getIntermediary().getName(), 150))
                  .cityCode(Optional.ofNullable(this.nfse.getIntermediary().getAddress())
                          .map(NFSeAddress::getCity).map(NFSeCity::getIbgeCode)
                          .orElse(null))
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

    private String buildMunicipalRegistration(final NFSeDocuments documents) {
        //@formatter:off
        return Optional.ofNullable(documents)
                .filter(NFSeLegalEntityDocuments.class::isInstance)
                .map(NFSeLegalEntityDocuments.class::cast)
                .map(NFSeLegalEntityDocuments::getIm)
                .map(this::nullIfEmpty)
                .orElse(null);
        //@formatter:on
    }

    private GovbrNFSeAddress buildNFSeAddress(final NFSeAddress address) {
        if (address == null) {
            return null;
        }
        //@formatter:off
        return GovbrNFSeAddress.builder()
                .address(this.formatNfseString(address.getStreet(), 125))
                .number(this.formatNfseString(address.getNumber(), 10))
                .complement(this.formatNfseString(address.getDetails(), 60))
                .district(this.formatNfseString(address.getDistrict(), 60))
                .cityCode(Optional.ofNullable(address.getCity())
                        .map(NFSeCity::getIbgeCode)
                        .orElse(null))
                .uf(Optional.ofNullable(address.getCity())
                        .map(NFSeCity::getUf)
                        .map(NFSeUF::getAcronym)
                        .map(CommonsNFSeUF::findByAcronym)
                        .orElse(null))
                .cep(address.getZipCode())
                .build();
        //@formatter:on
    }

    private GovbrNFSeContact buildNFSeContact(final NFSeContact contact) {
        if (contact == null) {
            return null;
        }

        //@formatter:off
        return GovbrNFSeContact.builder()
                .phone(this.formatNfseString(contact.getPhone(), 20))
                .email(this.formatNfseString(contact.getEmail(), 80))
                .build();
        //@formatter:on
    }

    @Override
    public NFSeRequest toDispatchConsult(String protocol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NFSeRequest toDispatchConsultState(String protocol) {
        throw new UnsupportedOperationException();
    }

    private String nullIfEmpty(final String v) {
        return StringUtils.isEmpty(v) ? null : v;
    }

    private String formatNfseString(final String input, final int size) {
        return Optional.ofNullable(StringUtils.upperCase(StringUtils.stripAccents(this.abbreviate(this.nullIfEmpty(input), size))))
                .map(string -> {
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

    private String formatNFSeValue(final BigDecimal value) {
        if (value == null) {
            return null;
        } else {
            return NFSE_TWO_DECIMALS_FORMAT.format(value);
        }
    }

    private CommonsNFSeBoolean formatNFSeBoolean(final boolean bool) {
        return bool ? CommonsNFSeBoolean.YES : CommonsNFSeBoolean.NO;
    }

}

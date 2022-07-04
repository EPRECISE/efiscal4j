
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

import eprecise.efiscal4j.nfse.domain.NFSe;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeCity;
import eprecise.efiscal4j.nfse.domain.person.contact.NFSeContact;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeLegalEntityDocuments;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeNaturalPersonDocuments;
import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationRequestData;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeAddress;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeUF;
import eprecise.efiscal4j.nfse.tc.commons.person.contacts.CommonsNFSeContact;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnpj;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCpf;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.goiania.lot.GoianiaLotRps;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.GoianiaIdentifier;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.GoianiaServiceIntermediary;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.GoianiaServiceTaker;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.GoianiaSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.GoianiaStatementProvisionService;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.rps.GoianiaRps;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.services.GoianiaService;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.services.GoianiaValues;
import eprecise.efiscal4j.nfse.tc.goiania.services.dispatch.GoianiaLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsType;


public class GoianiaNFSeDomainAdapter implements NFSeDomainAdapter {

    private static final DecimalFormat NFSE_TWO_DECIMALS_FORMAT = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));

    public static final DateFormat NFSE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final NFSe nfse;

    public GoianiaNFSeDomainAdapter(final NFSeDomainAdapter.Builder builder) {
        this.nfse = builder.getNfse();
    }

    @Override
    public NFSeRequest toDispatch() {
      //@formatter:off
        return GoianiaLotRpsDispatchSync.builder()
                .rpsLot(GoianiaLotRps.builder()
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
    public NFSeRequest toDispatchCancel(final NFSeCancellationRequestData cancellationRequestData) {
        throw new UnsupportedOperationException("Goiania não suporta cancelamento de NFSe via webservices");
    }

    private GoianiaStatementProvisionService buildStatementProvisionService() {
        //@formatter:off
        return GoianiaStatementProvisionService.builder()
                .info(GoianiaStatementProvisionService.GoianiaStatementProvisionServiceInfo.builder()
                        .rps(this.buildRps())
                        .service(this.buildService())
                        .serviceProviderIdentifier(this.buildServiceProviderIdentifier())
                        .serviceTaker(this.buildServiceTaker())
                        .serviceIntermediary(this.buildServiceIntermediary())
                        .specialTaxationRegime(Optional.ofNullable(this.nfse.getEmitter().getSpecialTaxationRegime())
                                .filter(GoianiaSpecialTaxationRegime.class::isInstance)
                                .map(GoianiaSpecialTaxationRegime.class::cast)
                                .orElse(null))
                        .build())
                .build();
        //@formatter:on
    }

    private GoianiaRps buildRps() {
      //@formatter:off
        return GoianiaRps.builder()
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

    private GoianiaService buildService() {
        //@formatter:off
          return GoianiaService.builder()
                  .values(this.buildServiceValues())
                  .assessmentCityCode(this.nfse.getService().getCnaeCode())
                  .discrimination(this.formatNfseString(this.nfse.getService().getDiscrimination(), 1000))
                  .cityCode(this.nfse.getService().getCityService().getIbgeCode())
                  .build();
        //@formatter:on
    }

    private GoianiaValues buildServiceValues() {
        //@formatter:off
          return GoianiaValues.builder()
                  .serviceValue(this.formatNFSeValue(this.nfse.getService().getGrossValue()))
                  .deductionValue(this.formatNFSeValue(this.nfse.getService().getDeduction()))
                  .pisValue(this.formatNFSeValue(this.nfse.getTax().getPisValue()))
                  .cofinsValue(this.formatNFSeValue(this.nfse.getTax().getCofinsValue()))
                  .inssValue(this.formatNFSeValue(this.nfse.getTax().getInssValue()))
                  .irValue(this.formatNFSeValue(this.nfse.getTax().getIrValue()))
                  .csllValue(this.formatNFSeValue(this.nfse.getTax().getCsllValue()))
                  .otherRetentionsValue(this.formatNFSeValue(this.nfse.getTax().getOtherRetentionsValue()))
                  .issAliquot(this.formatNFSeValue(this.nfse.getTax().getIssAliquot()))
                  .discountUnconditionedValue(this.formatNFSeValue(this.nfse.getService().getDiscount().getUnconditionedValue()))
                  .build();
        //@formatter:on
    }

    private GoianiaIdentifier buildServiceProviderIdentifier() {
      //@formatter:off
        return GoianiaIdentifier.builder()
                .cnp(this.buildCnp(this.nfse.getEmitter().getDocuments()))
                .municipalRegistration(this.buildMunicipalRegistration(this.nfse.getEmitter().getDocuments()))
                .build();
      //@formatter:on
    }

    private GoianiaServiceTaker buildServiceTaker() {
        if (this.nfse.getTaker() == null) {
            return null;
        }

        //@formatter:off
          return GoianiaServiceTaker.builder()
                  .identifier(GoianiaIdentifier.builder()
                          .cnp(this.buildCnp(this.nfse.getTaker().getDocuments()))
                          .municipalRegistration(this.buildMunicipalRegistration(this.nfse.getTaker().getDocuments()))
                          .build())
                  .socialName(this.formatNfseString(this.nfse.getTaker().getName(), 150))
                  .address(Optional.ofNullable(this.nfse.getTaker().getAddress())
                          .map(this::buildNFSeAddress)
                          .orElse(null))
                  .contact(this.buildNFSeContacts(this.nfse.getTaker().getContact()))
                  .build();
        //@formatter:on
    }

    private GoianiaServiceIntermediary buildServiceIntermediary() {
        if (this.nfse.getIntermediary() == null) {
            return null;
        }

        //@formatter:off
          return GoianiaServiceIntermediary.builder()
                  .identifier(GoianiaIdentifier.builder()
                          .cnp(this.buildCnp(this.nfse.getIntermediary().getDocuments()))
                          .municipalRegistration(this.buildMunicipalRegistration(this.nfse.getIntermediary().getDocuments()))
                          .build())
                  .socialName(this.formatNfseString(this.nfse.getIntermediary().getName(), 150))
                  .build();
        //@formatter:on
    }

    private CommonsNFSeCnp buildCnp(final NFSeDocuments documents) {
        if (documents instanceof NFSeLegalEntityDocuments) {
            if (documents.getCnp() != null) {
                return new CommonsNFSeCnpj.Builder().withCnpj(documents.getCnp()).build();
            }
        } else if (documents instanceof NFSeNaturalPersonDocuments) {
        	return new CommonsNFSeCpf.Builder().withCpf(Optional.ofNullable(documents.getCnp()).orElse("00000000000")).build();
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

    private CommonsNFSeContact buildNFSeContacts(final NFSeContact contact) {
        return Optional
                .ofNullable(contact).map(
                        c -> new CommonsNFSeContact.Builder().withEmail(c.getEmail())
                                .withPhone(Optional.ofNullable(c.getPhone()).map(str -> str.replaceAll("[^\\d.]", ""))
                                        .map(str -> str.substring(0, str.length() < 11 ? str.length() : 11)).orElse(null))
                                .build())
                .orElse(null);
    }

    @Override
    public NFSeRequest toDispatchConsult(final String protocol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NFSeRequest toDispatchConsultState(final String protocol) {
        throw new UnsupportedOperationException();
    }

    private String nullIfEmpty(final String v) {
        return StringUtils.isEmpty(v) ? null : v;
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

    private String formatNFSeValue(final BigDecimal value) {
        if (value == null) {
            return null;
        } else {
            return NFSE_TWO_DECIMALS_FORMAT.format(value);
        }
    }

}

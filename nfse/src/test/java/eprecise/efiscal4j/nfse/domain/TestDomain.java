
package eprecise.efiscal4j.nfse.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.nfse.NFSeTestParams;
import eprecise.efiscal4j.nfse.domain.adapters.NFSeDomainAdapter;
import eprecise.efiscal4j.nfse.domain.adapters.NFSeDomainAdapter.NFSeAdapter;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeCity;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeUF;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeLegalEntityDocuments;
import eprecise.efiscal4j.nfse.domain.serie.NFSeSerie;
import eprecise.efiscal4j.nfse.domain.service.NFSeService;
import eprecise.efiscal4j.nfse.domain.service.emitter.NFSeServiceEmitter;
import eprecise.efiscal4j.nfse.domain.service.taker.NFSeServiceTaker;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeWithoutIssHeld;
import eprecise.efiscal4j.nfse.domain.specialTaxationRegime.NFSeSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.domain.specificData.NFSeElotechData;
import eprecise.efiscal4j.nfse.domain.specificData.NFSeSpecificData;
import eprecise.efiscal4j.nfse.domain.specificData.curitiba.NFSeCuritibaData;
import eprecise.efiscal4j.nfse.domain.specificData.govbr.v100.NFSeGovbrData;
import eprecise.efiscal4j.nfse.domain.tax.NFSeTax;
import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationRequestData;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.CuritibaNatureOperation;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.CuritibaLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechIssRequirement;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.goiania.services.dispatch.GoianiaLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.govbr.v100.lot.rps.GovbrNatureOperation;
import eprecise.efiscal4j.nfse.tc.govbr.v100.lot.rps.GovbrSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.GovbrLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.govbr.v203.cancel.GovbrCancellationCode;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.services.GovbrIssRequirement;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.GovbrLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel.GovbrNFSeDispatchCancel;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel;


public class TestDomain {

    private final Logger logger = LoggerFactory.getLogger(TestDomain.class);

    private FiscalDocumentValidator validator;

    private final Certificate keyCertificate;


    public TestDomain() {
        try {
            this.keyCertificate = this.getKeyCertificate();
        } catch (final Exception ex) {
            this.getLogger().error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public TestDomain(final String xsdPath) {
        this();
        this.setXsdPath(xsdPath);
    }

    public void setXsdPath(final String xsdPath) {
        try {
            this.validator = new FiscalDocumentValidator(this.getClass().getResource(xsdPath));
        } catch (final IOException ex) {
            this.getLogger().error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public NFSe buildNFSe(final NFSeCity city, final String lotNumber) throws Exception {
      //@formatter:off
        try{
            final NFSeAdapter adapter = Optional.ofNullable(NFSeAdapter.findAdapterBy(city.getIbgeCode())).orElseThrow(UnsupportedOperationException::new);

            final NFSe nfse = new NFSe.Builder()
                    .withSerie(new NFSeSerie.Builder()
                            .withSerie("TESTE")
                            .withLotNumber(lotNumber)
                            .withRpsNumber("1")
                            .build())
                    .withEmissionDate(new Date())
                    .withEmitter(new NFSeServiceEmitter.Builder()
                            .withName("Teste Nome Fantasia")
                            .withDocuments(new NFSeLegalEntityDocuments.Builder()
                                    .withCorporateName("Teste Razão Social")
                                    .withCnpj(this.getEmitterCnpj())
                                    .withIm(this.getEmitterIm())
                                    .withIe(this.getEmitterIe())
                                    .build())
                            .withAddress(new NFSeAddress.Builder()
                                    .withStreet("Rua xyz")
                                    .withDistrict("Bairro xyz")
                                    .withNumber("123")
                                    .withDetails("complemento")
                                    .withCity(city)
                                    .withZipCode("84010000")
                                    .build())
                            .withSpecialTaxationRegime(this.buildNFSeSpecialTaxationRegime(adapter))
                            .build())
                    .withTaker(new NFSeServiceTaker.Builder()
                            .withName("Teste Nome Fantasia")
                            .withDocuments(new NFSeLegalEntityDocuments.Builder()
                                    .withCorporateName("Teste Razão Social")
                                    .withCnpj(this.getReceiverLegalEntityCnpj())
                                    .build())
                            .withAddress(new NFSeAddress.Builder()
                                    .withStreet("Rua abc")
                                    .withDistrict("Bairro abc")
                                    .withNumber("456")
                                    .withCity(new NFSeCity.Builder()
                                            .withName("Ponta Grossa")
                                            .withUf(NFSeUF.PR)
                                            .withIbgeCode("4119905")
                                            .build())
                                    .withZipCode("84015000")
                                    .build())
                            .build())
                    .withIssHeld(new NFSeWithoutIssHeld())
                    .withService(new NFSeService.Builder()
                            .withName("Serviço xyz")
                            .withCnaeCode("007020400")
                            .withNationalServiceCode("4.23")
                            .withCityService(city)
                            .withDiscrimination("Teste de discriminação de serviço")
                            .withUnitaryValue(new BigDecimal("10.00"))
                            .withAmount(BigDecimal.ONE)
                            .withDiscount(BigDecimal.ZERO)
                            .build())
                    .withTax(new NFSeTax.Builder()
                            .withBcValue(new BigDecimal("10.00"))
                            .withPisAliquot(BigDecimal.ZERO)
                            .withPisValue(BigDecimal.ZERO)
                            .withCofinsAliquot(BigDecimal.ZERO)
                            .withCofinsValue(BigDecimal.ZERO)
                            .withInssAliquot(BigDecimal.ZERO)
                            .withInssValue(BigDecimal.ZERO)
                            .withIrAliquot(BigDecimal.ZERO)
                            .withIrValue(BigDecimal.ZERO)
                            .withCsllAliquot(BigDecimal.ZERO)
                            .withCsllValue(BigDecimal.ZERO)
                            .withOtherRetentionsValue(BigDecimal.ZERO)
                            .withIssAliquot(new BigDecimal("2.00"))
                            .withIssValue(new BigDecimal("0.2"))
                            .build())
                    .withSpecificData(this.buildNFSeSpecificData(adapter))
                    .build();

            return nfse;
        } catch(final Exception e){
            throw new RuntimeException(e);
        }
      //@formatter:on
    }

    private NFSeSpecificData buildNFSeSpecificData(final NFSeAdapter adapter) {
      //@formatter:off
        if(adapter.equals(NFSeAdapter.ELOTECH)){
            return new NFSeElotechData.Builder()
                    .withTransmissionPassword(Optional.ofNullable(this.getEmitterPassword()).orElse("12345"))
                    .withHomologation(true)
                    .withIssRequirement(ElotechIssRequirement.REQUIRED)
                    .withTaxIncentive(false)
                    .build();
        } else if(adapter.equals(NFSeAdapter.GOVBR_v100)){
            return new NFSeGovbrData.Builder()
                    .withCulturalPromoter(false)
                    .withNatureOperation(GovbrNatureOperation.MUNICIPAL_TAXATION)
                    .withSimpleNational(true)
                    .build();
        } else if(adapter.equals(NFSeAdapter.GOVBR_v203)) {
            return eprecise.efiscal4j.nfse.domain.specificData.govbr.v203.NFSeGovbrData.builder()
            .nationalSimple(false)
            .taxIncentive(false)
            .issRequirement(GovbrIssRequirement.REQUIRED)
            .build();
        } else if(adapter.equals(NFSeAdapter.CURITIBA)) {
            return new NFSeCuritibaData.Builder()
                    .withCulturalPromoter(false)
                    .withNatureOperation(CuritibaNatureOperation.MUNICIPAL_TAXATION)
                    .withSimpleNational(true)
                    .build();
        }
        return null;
      //@formatter:on
    }

    private NFSeSpecialTaxationRegime buildNFSeSpecialTaxationRegime(final NFSeAdapter adapter) {
        if (adapter.equals(NFSeAdapter.ELOTECH)) {
            return ElotechSpecialTaxationRegime.MUNICIPAL_MICRO_ENTERPRISE;
        } else if (adapter.equals(NFSeAdapter.GOVBR_v100)) {
            return GovbrSpecialTaxationRegime.MUNICIPAL_MICRO_ENTERPRISE;
        } else if (adapter.equals(NFSeAdapter.GOVBR_v203)) {
            return null;
        }
        return null;
    }

    public ElotechLotRpsDispatchSync buildElotechLotRpsDispatch() throws Exception {
        final NFSeCity city = new NFSeCity.Builder().withName("Ponta Grossa").withUf(NFSeUF.PR).withIbgeCode("4119905").build();
        final NFSeDomainAdapter domainAdapter = new NFSeDomainAdapter.Builder().withNFSe(this.buildNFSe(city, "10")).build();
        return Optional.ofNullable(domainAdapter.toDispatch()).filter(ElotechLotRpsDispatchSync.class::isInstance)
                .map(ElotechLotRpsDispatchSync.class::cast).orElseThrow(IllegalStateException::new);
    }

    public CuritibaLotRpsDispatchAsync buildCuritibaLotRpsDispatch() throws Exception {
        final NFSeCity city = new NFSeCity.Builder().withName("Curitiba").withUf(NFSeUF.PR).withIbgeCode("4106902").build();
        final NFSeDomainAdapter domainAdapter = new NFSeDomainAdapter.Builder().withCertificate(this.getCertificate()).withNFSe(this.buildNFSe(city, "2")).build();
        return Optional.ofNullable(domainAdapter.toDispatch()).filter(CuritibaLotRpsDispatchAsync.class::isInstance)
                .map(CuritibaLotRpsDispatchAsync.class::cast).orElseThrow(IllegalStateException::new);
    }

    public GovbrLotRpsDispatchAsync buildGovbrV100LotRpsDispatch() throws Exception {
        final NFSeCity city = new NFSeCity.Builder().withName("Pato Branco").withUf(NFSeUF.PR).withIbgeCode("4118501").build();
        final NFSeDomainAdapter domainAdapter = new NFSeDomainAdapter.Builder().withCertificate(this.getCertificate()).withNFSe(this.buildNFSe(city, "10")).build();
        return Optional.ofNullable(domainAdapter.toDispatch()).filter(GovbrLotRpsDispatchAsync.class::isInstance)
                .map(GovbrLotRpsDispatchAsync.class::cast).orElseThrow(IllegalStateException::new);
    }

    public GovbrLotRpsDispatchSync buildGovbrV203LotRpsDispatch() throws Exception {
        final NFSeCity city = new NFSeCity.Builder().withName("Pato Branco").withUf(NFSeUF.PR).withIbgeCode("4118501").build();
        final NFSeDomainAdapter domainAdapter = new NFSeDomainAdapter.Builder().withNFSe(this.buildNFSe(city, "1005")).build();

        return Optional.ofNullable(domainAdapter.toDispatch()).filter(GovbrLotRpsDispatchSync.class::isInstance)
                .map(GovbrLotRpsDispatchSync.class::cast).orElseThrow(IllegalStateException::new);
    }

    public GovbrNFSeDispatchCancel buildGovbrV203CancelDispatch() throws Exception {
        final NFSeCity city = new NFSeCity.Builder().withName("Pato Branco").withUf(NFSeUF.PR).withIbgeCode("4118501").build();
        final String nfseNumber = "201800000002393";
        final String lotNumber = "1005";

        final NFSeDomainAdapter domainAdapter = new NFSeDomainAdapter.Builder().withNFSe(this.buildNFSe(city, lotNumber)).build();
        return Optional
                .ofNullable(domainAdapter.toDispatchCancel(new NFSeCancellationRequestData.Builder().withNfseNumber(nfseNumber)
                        .withCancellationCode(GovbrCancellationCode.SERVICE_NOT_PERFORMED).build()))
                .filter(GovbrNFSeDispatchCancel.class::isInstance).map(GovbrNFSeDispatchCancel.class::cast)
                .orElseThrow(IllegalArgumentException::new);
    }
    
    public GoianiaLotRpsDispatchSync buildGoianiaLotRpsDispatch() throws Exception {
        final NFSeCity city = new NFSeCity.Builder().withName("Goiania").withUf(NFSeUF.GO).withIbgeCode("5208707").build();
        final NFSeDomainAdapter domainAdapter = new NFSeDomainAdapter.Builder().withCertificate(this.getCertificate()).withNFSe(this.buildNFSe(city, "1")).build();

        return Optional.ofNullable(domainAdapter.toDispatch()).filter(GoianiaLotRpsDispatchSync.class::isInstance)
                .map(GoianiaLotRpsDispatchSync.class::cast).orElseThrow(IllegalStateException::new);
    }

    public TransmissionChannel geTransmissionChannel(final NFSeTransmissor transmissor) {
        return transmissor.getTransmissionChannel(this.keyCertificate);
    }

    public String getEmitterCnpj() {
        return NFSeTestParams.getEmitterCnpj().orElse("00000000000000");
    }

    public String getEmitterPassword() {
        return NFSeTestParams.getEmitterPassword().orElse("123456");
    }

    public String getEmitterIm() {
        return NFSeTestParams.getEmitterIm().orElse("123456");
    }

    public String getEmitterIe() {
        return NFSeTestParams.getEmitterIe().orElse("0000000000");
    }

    public String getReceiverLegalEntityCorporateName() {
        return NFSeTestParams.getReceiverCorporateName().orElse("Razão Social da Empresa");
    }

    public String getReceiverLegalEntityCnpj() {
        return NFSeTestParams.getReceiverCnpj().orElse("00000000000000");
    }

    public String getReceiverLegalEntityIe() {
        return NFSeTestParams.getReceiverIe().orElse("0000000000");
    }

    public String getReceiverNaturalPersonCpf() {
        return NFSeTestParams.getReceiverCpf().orElse("70179153056");
    }

    public Certificate getKeyCertificate() {

        final Optional<String> certificatePath = NFSeTestParams.getCertificatePath();
        final Optional<String> certificatePin = NFSeTestParams.getCertificatePin();
        if (certificatePath.isPresent() && certificatePin.isPresent()) {
            return new Certificate(() -> new FileInputStream(certificatePath.get()), certificatePin.get());
        } else {
            return new Certificate(() -> NFSeTestParams.class.getResourceAsStream("/eprecise/efiscal4j/nfe/certificate/Teste.pfx"), "1234");
        }
    }

    public Logger getLogger() {
        return this.logger;
    }

    public FiscalDocumentValidator getValidator() {
        return this.validator;
    }

    public Certificate getCertificate() {
        return this.keyCertificate;
    }

}

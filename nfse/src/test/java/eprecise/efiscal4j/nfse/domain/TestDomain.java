
package eprecise.efiscal4j.nfse.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
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
import eprecise.efiscal4j.nfse.domain.specificData.NFSeGovbrData;
import eprecise.efiscal4j.nfse.domain.specificData.NFSeSpecificData;
import eprecise.efiscal4j.nfse.domain.tax.NFSeTax;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechIssRequirement;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrNatureOperation;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.GovbrLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel;


public class TestDomain {

    private static final String EMITTER_CNPJ_PROPERTY = "eprecise.efiscal4j.nfe.emitter.cnpj";

    private static final String EMITTER_IM_PROPERTY = "eprecise.efiscal4j.nfe.emitter.im";

    private static final String EMITTER_IE_PROPERTY = "eprecise.efiscal4j.nfe.emitter.ie";

    private static final String EMITTER_PASSWORD_PROPERTY = "eprecise.efiscal4j.nfe.emitter.password";

    private static final String RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.cnpj";

    private static final String CERTIFICATE_PIN_PROPERTY = "eprecise.efiscal4j.commons.certificate.pin";

    private static final String CERTIFICATE_PATH_PROPERTY = "eprecise.efiscal4j.commons.certificate.path";

    private final Logger logger = LoggerFactory.getLogger(TestDomain.class);

    private FiscalDocumentValidator validator;

    private final String emitterCnpj;

    private final String emitterIM;

    private final String emitterIE;

    private final String emitterPassword;

    private final String receiverLegalEntityCnpj;

    private final Certificate keyCertificate;

    public TestDomain() {
        try {
            emitterCnpj = System.getProperty(TestDomain.EMITTER_CNPJ_PROPERTY);
            emitterIM = System.getProperty(TestDomain.EMITTER_IM_PROPERTY);
            emitterIE = System.getProperty(TestDomain.EMITTER_IE_PROPERTY);
            emitterPassword = System.getProperty(TestDomain.EMITTER_PASSWORD_PROPERTY);
            receiverLegalEntityCnpj = System.getProperty(TestDomain.RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY);
            final String certificatePath = System.getProperty(TestDomain.CERTIFICATE_PATH_PROPERTY);
            final String certificatePin = System.getProperty(TestDomain.CERTIFICATE_PIN_PROPERTY);
            if (StringUtils.isEmpty(certificatePath) || StringUtils.isEmpty(certificatePin)) {
                keyCertificate = null;
            } else {
                keyCertificate = new Certificate(() -> new FileInputStream(certificatePath), certificatePin);
            }
        } catch (final Exception ex) {
            getLogger().error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public TestDomain(final String xsdPath) {
        this();
        setXsdPath(xsdPath);
    }

    public void setXsdPath(final String xsdPath) {
        try {
            validator = new FiscalDocumentValidator(this.getClass().getResource(xsdPath));
        } catch (final IOException ex) {
            getLogger().error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public NFSe buildNFSe(final NFSeCity city) throws Exception {
      //@formatter:off
        try{
            final NFSeAdapter adapter = Optional.ofNullable(NFSeAdapter.findAdapterBy(city.getIbgeCode())).orElseThrow(UnsupportedOperationException::new);

            final NFSe nfse = new NFSe.Builder()
                    .withSerie(new NFSeSerie.Builder()
                            .withSerie("T1")
                            .withLotNumber("10")
                            .withRpsNumber("21")
                            .build())
                    .withEmissionDate(new Date())
                    .withEmitter(new NFSeServiceEmitter.Builder()
                            .withName("Teste Nome Fantasia")
                            .withDocuments(new NFSeLegalEntityDocuments.Builder()
                                    .withCorporateName("Teste Razão Social")
                                    .withCnpj(Optional.ofNullable(emitterCnpj).orElse("14445087000115"))
                                    .withIm(Optional.ofNullable(emitterIM).orElse("00083700"))
                                    .withIe(Optional.ofNullable(emitterIE).orElse("ISENTO"))
                                    .build())
                            .withAddress(new NFSeAddress.Builder()
                                    .withStreet("Rua xyz")
                                    .withDistrict("Bairro xyz")
                                    .withNumber("123")
                                    .withDetails("complemento")
                                    .withCity(city)
                                    .withZipCode("84010000")
                                    .build())
                            .withSpecialTaxationRegime(buildNFSeSpecialTaxationRegime(adapter))
                            .build())
                    .withTaker(new NFSeServiceTaker.Builder()
                            .withName("Teste Nome Fantasia")
                            .withDocuments(new NFSeLegalEntityDocuments.Builder()
                                    .withCorporateName("Teste Razão Social")
                                    .withCnpj(Optional.ofNullable(receiverLegalEntityCnpj).orElse("43147165000101"))
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
                            .withCnaeCode("6550200")
                            .withNationalServiceCode("4.23")
                            .withCityService(city)
                            .withDiscrimination("Teste de discriminação de serviço")
                            .withUnitaryValue(new BigDecimal("10.00"))
                            .withAmount(BigDecimal.ONE)
                            .withDiscount(BigDecimal.ZERO)
                            .withServiceValue(new BigDecimal("10.00"))
                            .build())
                    .withTax(new NFSeTax.Builder()
                            .withBcValue(new BigDecimal("10.00"))
                            .withIssAliquot(new BigDecimal("3.00"))
                            .withIssValue(new BigDecimal("0.3"))
                            .build())
                    .withSpecificData(buildNFSeSpecificData(adapter))
                    .withNetValue(new BigDecimal("10.00"))
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
                    .withTransmissionPassword(Optional.ofNullable(emitterPassword).orElse("12345"))
                    .withHomologation(true)
                    .withIssRequirement(ElotechIssRequirement.REQUIRED)
                    .withTaxIncentive(false)
                    .build();
        } else if(adapter.equals(NFSeAdapter.GOVBR)){
            return new NFSeGovbrData.Builder()
                    .withCulturalPromoter(false)
                    .withNatureOperation(GovbrNatureOperation.MUNICIPAL_TAXATION)
                    .withSimpleNational(true)
                    .build();
        }
        return null;
      //@formatter:on
    }

    private NFSeSpecialTaxationRegime buildNFSeSpecialTaxationRegime(final NFSeAdapter adapter) {
        if (adapter.equals(NFSeAdapter.ELOTECH)) {
            return ElotechSpecialTaxationRegime.MUNICIPAL_MICRO_ENTERPRISE;
        } else if (adapter.equals(NFSeAdapter.GOVBR)) {
            return GovbrSpecialTaxationRegime.MUNICIPAL_MICRO_ENTERPRISE;
        }
        return null;
    }

    public ElotechLotRpsDispatchSync buildElotechLotRpsDispatch() throws Exception {
        final NFSeCity city = new NFSeCity.Builder().withName("Ponta Grossa").withUf(NFSeUF.PR).withIbgeCode("4119905").build();
        final NFSeDomainAdapter domainAdapter = new NFSeDomainAdapter.Builder().withNFSe(buildNFSe(city)).withCertificate(keyCertificate).build();
        return Optional.ofNullable(domainAdapter.toDispatch()).filter(ElotechLotRpsDispatchSync.class::isInstance).map(ElotechLotRpsDispatchSync.class::cast).orElseThrow(IllegalStateException::new);
    }

    public GovbrLotRpsDispatchAsync buildGovbrLotRpsDispatch() throws Exception {
        final NFSeCity city = new NFSeCity.Builder().withName("Pato Branco").withUf(NFSeUF.PR).withIbgeCode("4118501").build();
        final NFSeDomainAdapter domainAdapter = new NFSeDomainAdapter.Builder().withNFSe(buildNFSe(city)).build();
        return Optional.ofNullable(domainAdapter.toDispatch()).filter(GovbrLotRpsDispatchAsync.class::isInstance).map(GovbrLotRpsDispatchAsync.class::cast).orElseThrow(IllegalStateException::new);
    }

    public TransmissionChannel geTransmissionChannel(final NFSeTransmissor transmissor) {
        return transmissor.getTransmissionChannel(keyCertificate);
    }

    public Logger getLogger() {
        return logger;
    }

    public FiscalDocumentValidator getValidator() {
        return validator;
    }

    public Certificate getCertificate() {
        return keyCertificate;
    }

}

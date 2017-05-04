
package eprecise.efiscal4j.nfse.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.nfse.LotRps;
import eprecise.efiscal4j.nfse.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.person.address.NFSeUF;
import eprecise.efiscal4j.nfse.person.documents.NFSeCnpj;
import eprecise.efiscal4j.nfse.sharing.Applicant;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatch;
import eprecise.efiscal4j.nfse.statements.ServiceProvider;
import eprecise.efiscal4j.nfse.statements.ServiceTaker;
import eprecise.efiscal4j.nfse.statements.StatementProvisionService;
import eprecise.efiscal4j.nfse.statements.TaxIncentive;
import eprecise.efiscal4j.nfse.statements.rps.Rps;
import eprecise.efiscal4j.nfse.statements.rps.RpsIdentifier;
import eprecise.efiscal4j.nfse.statements.rps.RpsStatus;
import eprecise.efiscal4j.nfse.statements.rps.RpsType;
import eprecise.efiscal4j.nfse.statements.services.IssRequirement;
import eprecise.efiscal4j.nfse.statements.services.IssWithheld;
import eprecise.efiscal4j.nfse.statements.services.Service;
import eprecise.efiscal4j.nfse.statements.services.ServiceItem;
import eprecise.efiscal4j.nfse.statements.services.ServiceItemTaxable;
import eprecise.efiscal4j.nfse.statements.services.ServiceValues;
import eprecise.efiscal4j.nfse.transmission.ElotechTransmissionChannel;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPBody;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPHeader;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.oasis.OasisSigner;


public class TestDomain {

    private static final String EMITTER_CNPJ_PROPERTY = "eprecise.efiscal4j.nfe.emitter.cnpj";

    private static final String EMITTER_IM_PROPERTY = "eprecise.efiscal4j.nfe.emitter.im";

    private static final String EMITTER_PASSWORD_PROPERTY = "eprecise.efiscal4j.nfe.emitter.password";

    private static final String RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.cnpj";

    private static final String CERTIFICATE_PIN_PROPERTY = "eprecise.efiscal4j.commons.certificate.pin";

    private static final String CERTIFICATE_PATH_PROPERTY = "eprecise.efiscal4j.commons.certificate.path";

    private static final String CERTIFICATE_NOT_PRESENT_MESSAGE = "Certificado ou pin não estão presente";

    private final Logger logger = LoggerFactory.getLogger(TestDomain.class);

    private FiscalDocumentValidator validator;

    private final Signer signer;

    private final ElotechTransmissionChannel transmissionChannel;

    private final String emitterCnpj;

    private final String emitterIM;

    private final String emitterPassword;

    private final String receiverLegalEntityCnpj;

    public TestDomain() {
        try {
            emitterCnpj = System.getProperty(TestDomain.EMITTER_CNPJ_PROPERTY);
            emitterIM = System.getProperty(TestDomain.EMITTER_IM_PROPERTY);
            emitterPassword = System.getProperty(TestDomain.EMITTER_PASSWORD_PROPERTY);
            receiverLegalEntityCnpj = System.getProperty(TestDomain.RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY);
            final String certificatePath = System.getProperty(TestDomain.CERTIFICATE_PATH_PROPERTY);
            final String certificatePin = System.getProperty(TestDomain.CERTIFICATE_PIN_PROPERTY);
            if (StringUtils.isEmpty(certificatePath) || StringUtils.isEmpty(certificatePin)) {
                signer = null;
                transmissionChannel = null;
            } else {
                final Certificate keyCertificate = new Certificate(() -> new FileInputStream(certificatePath), certificatePin);
                signer = new OasisSigner(keyCertificate);
                transmissionChannel = new ElotechTransmissionChannel(keyCertificate);
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

    private boolean containsCertificate() {
        return (signer != null) && (transmissionChannel != null);
    }

    private void assertCertificate() {
        if (!containsCertificate()) {
            throw new IllegalStateException(TestDomain.CERTIFICATE_NOT_PRESENT_MESSAGE);
        }
    }

    public void setXsdPath(final String xsdPath) {
        try {
            validator = new FiscalDocumentValidator(this.getClass().getResource(xsdPath));
        } catch (final IOException ex) {
            getLogger().error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public SOAPEnvelope buildSOAPEnvelope() throws Exception {
        try {
            return new SOAPEnvelope.Builder().withSoapHeader(new SOAPHeader.Builder().build()).withSoapBody(new SOAPBody.Builder().withTransmissibleBody(buildLotRpsDispatch()).build()).build(signer);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public LotRpsDispatch buildLotRpsDispatch() throws Exception {
        //@formatter:off
        try {
            final LotRpsDispatch lotRpsDispatch = new LotRpsDispatch.Builder()
                    .withApplicant(new Applicant.Builder()
                            .withCnp(new NFSeCnpj.Builder().withCnpj(Optional.ofNullable(emitterCnpj).orElse("14445087000115")).build())
                            .withMunicipalRegistration(Optional.ofNullable(emitterIM).orElse("00083700"))
                            .withPassword(Optional.ofNullable(emitterPassword).orElse("abcdef"))
                            .withHomologation(true)
                            .build())
                    .withLotRps(
                    new LotRps.Builder().withLotNumber("5").withRpsQuantity(1).withStatementProvisionService(
                    Arrays.asList(new StatementProvisionService.Builder()
                            .withInfo(new StatementProvisionService.Info.Builder()
                                    .withCompetence(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                                    .withRps(new Rps.Builder()
                                            .withIdentifier(new RpsIdentifier.Builder()
                                                    .withType(RpsType.PROVISIONAL_SERVICE_RECEIPT)
                                                    .withSerie("E")
                                                    .withNumber("5")
                                                    .build())
                                            .withStatus(RpsStatus.NORMAL)
                                            .withEmissionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                                            .build())
                                    .withService(new Service.Builder()
                                            .withServiceValues(new ServiceValues.Builder()
                                                    .withServiceValue("10.00")
                                                    .withDeductionValue("0.00")
                                                    .withPisValue("0.00")
                                                    .withCofinsValue("0.00")
                                                    .withInssValue("0.00")
                                                    .withIrValue("0.00")
                                                    .withCsllValue("0.00")
                                                    .withOtherRetentionsValue("0.00")
//                                                    .withIssValue("57.37")
                                                    .withIssAliquot("3")
                                                    .withDiscountUnconditionedValue("0.00")
                                                    .withDiscountConditionedValue("0.00")
                                                    .build())
                                            .withIssWithheld(IssWithheld.NO)
                                            .withDiscrimination("Teste discriminação")
                                            .withCityCode("4119905")
                                            .withIssRequirement(IssRequirement.REQUIRED)
                                            .withCityIncidenceCode("4119905")
                                            .withServiceItems(Arrays.asList(new ServiceItem.Builder()
                                                    .withItemServiceList("106")
                                                    .withCnaeCode("6204000")
                                                    .withDescription("Teste descrição")
                                                    .withTaxable(ServiceItemTaxable.YES)
                                                    .withQuantity("1")
                                                    .withUnitaryValue("10.00")
                                                    .withDiscountValue("0.00")
                                                    .withNetValue("10.00")
                                                    .build()))
                                            .build())
                                    .withServiceProvider(new ServiceProvider.Builder()
                                            .withIdentifier(new ServiceProvider.ServiceProviderIdentifier.Builder()
                                                    .withCnp(new NFSeCnpj.Builder().withCnpj(Optional.ofNullable(emitterCnpj).orElse("14445087000115")).build())
                                                    .withMunicipalRegistration(Optional.ofNullable(emitterIM).orElse("00083700"))
                                                    .build())
                                            .withSocialName("Teste Razão Social")
                                            .withAddress(new NFSeAddress.Builder()
                                                    .withAddress("Rua xyz")
                                                    .withNumber("123")
                                                    .withDistrict("Centro")
                                                    .withCityCode("4119905")
                                                    .withCityName("PONTA GROSSA")
                                                    .withUf(NFSeUF.PR)
                                                    .withCep("84010000")
                                                    .build())
                                            .build())
                                    .withServiceTaker(new ServiceTaker.Builder()
                                            .withIdentifier(new ServiceTaker.ServiceTakerIdentifier.Builder()
                                                    .withCnp(new NFSeCnpj.Builder().withCnpj(Optional.ofNullable(receiverLegalEntityCnpj).orElse("76591569000130")).build())
                                                    .build())
                                            .withSocialName("Razão Social Tomador")
                                            .withAddress(new NFSeAddress.Builder()
                                                    .withAddress("Rua xyz")
                                                    .withNumber("123")
                                                    .withDistrict("Centro")
                                                    .withCityCode("4119905")
                                                    .withCityName("PONTA GROSSA")
                                                    .withUf(NFSeUF.PR)
                                                    .withCep("84010000")
                                                    .build())
                                            .build())
//                                    .withSpecialTaxationRegime(SpecialTaxationRegime.ME_EPP)
                                    .withTaxIncentive(TaxIncentive.NO)
                                    .build()).build())).build())
                    .build();
            return lotRpsDispatch;
        } catch (final Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //@formatter:on
    }

    public Logger getLogger() {
        return logger;
    }

    public FiscalDocumentValidator getValidator() {
        return validator;
    }

    public Signer getSigner() {
        assertCertificate();
        return signer;
    }

    public ElotechTransmissionChannel getTransmissionChannel() {
        assertCertificate();
        return transmissionChannel;
    }

}

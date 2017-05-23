
package eprecise.efiscal4j.nfse.elotech.domain;

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
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeUF;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnpj;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.elotech.lot.ElotechLotRps;
import eprecise.efiscal4j.nfse.tc.elotech.person.address.ElotechNFSeAddress;
import eprecise.efiscal4j.nfse.tc.elotech.services.ElotechApplicant;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.elotech.statements.ElotechServiceProvider;
import eprecise.efiscal4j.nfse.tc.elotech.statements.ElotechServiceTaker;
import eprecise.efiscal4j.nfse.tc.elotech.statements.ElotechStatementProvisionService;
import eprecise.efiscal4j.nfse.tc.elotech.statements.ElotechTaxIncentive;
import eprecise.efiscal4j.nfse.tc.elotech.statements.rps.ElotechRps;
import eprecise.efiscal4j.nfse.tc.elotech.statements.services.ElotechIssRequirement;
import eprecise.efiscal4j.nfse.tc.elotech.statements.services.ElotechIssWithheld;
import eprecise.efiscal4j.nfse.tc.elotech.statements.services.ElotechService;
import eprecise.efiscal4j.nfse.tc.elotech.statements.services.ElotechServiceItem;
import eprecise.efiscal4j.nfse.tc.elotech.statements.services.ElotechServiceItemTaxable;
import eprecise.efiscal4j.nfse.tc.elotech.statements.services.ElotechServiceValues;
import eprecise.efiscal4j.nfse.transmission.elotech.ElotechTransmissionChannel;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.SOAPBody;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.SOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.SOAPHeader;
import eprecise.efiscal4j.nfse.ts.commons.rps.RpsStatus;
import eprecise.efiscal4j.nfse.ts.commons.rps.RpsType;
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
        return signer != null && transmissionChannel != null;
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

    public ElotechLotRpsDispatchSync buildLotRpsDispatch() throws Exception {
        //@formatter:off
        try {
            final ElotechLotRpsDispatchSync lotRpsDispatch = new ElotechLotRpsDispatchSync.Builder()
                    .withApplicant(new ElotechApplicant.Builder()
                            .withCnp(new CommonsNFSeCnpj.Builder().withCnpj(Optional.ofNullable(emitterCnpj).orElse("14445087000115")).build())
                            .withMunicipalRegistration(Optional.ofNullable(emitterIM).orElse("00083700"))
                            .withPassword(Optional.ofNullable(emitterPassword).orElse("abcdef"))
                            .withHomologation(true)
                            .build())
                    .withLotRps(
                    new ElotechLotRps.Builder().withLotNumber("5").withRpsQuantity(1).withStatementProvisionService(
                    Arrays.asList(new ElotechStatementProvisionService.Builder()
                            .withInfo(new ElotechStatementProvisionService.Info.Builder()
                                    .withCompetence(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                                    .withRps(new ElotechRps.Builder()
                                            .withIdentifier(new CommonsRpsIdentifier.Builder()
                                                    .withType(RpsType.PROVISIONAL_SERVICE_RECEIPT)
                                                    .withSerie("E")
                                                    .withNumber("5")
                                                    .build())
                                            .withStatus(RpsStatus.NORMAL)
                                            .withEmissionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                                            .build())
                                    .withService(new ElotechService.Builder()
                                            .withServiceValues(new ElotechServiceValues.Builder()
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
                                            .withIssWithheld(ElotechIssWithheld.NO)
                                            .withDiscrimination("Teste discriminação")
                                            .withCityCode("4119905")
                                            .withIssRequirement(ElotechIssRequirement.REQUIRED)
                                            .withCityIncidenceCode("4119905")
                                            .withServiceItems(Arrays.asList(new ElotechServiceItem.Builder()
                                                    .withItemServiceList("106")
                                                    .withCnaeCode("6204000")
                                                    .withDescription("Teste descrição")
                                                    .withTaxable(ElotechServiceItemTaxable.YES)
                                                    .withQuantity("1")
                                                    .withUnitaryValue("10.00")
                                                    .withDiscountValue("0.00")
                                                    .withNetValue("10.00")
                                                    .build()))
                                            .build())
                                    .withServiceProvider(new ElotechServiceProvider.Builder()
                                            .withIdentifier(new ElotechServiceProvider.ServiceProviderIdentifier.Builder()
                                                    .withCnp(new CommonsNFSeCnpj.Builder().withCnpj(Optional.ofNullable(emitterCnpj).orElse("14445087000115")).build())
                                                    .withMunicipalRegistration(Optional.ofNullable(emitterIM).orElse("00083700"))
                                                    .build())
                                            .withSocialName("Teste Razão Social")
                                            .withAddress(new ElotechNFSeAddress.Builder()
                                                    .withAddress("Rua xyz")
                                                    .withNumber("123")
                                                    .withDistrict("Centro")
                                                    .withCityCode("4119905")
                                                    .withUf(CommonsNFSeUF.PR)
                                                    .withCep("84010000")
                                                    .build())
                                            .build())
                                    .withServiceTaker(new ElotechServiceTaker.Builder()
                                            .withIdentifier(new ElotechServiceTaker.ServiceTakerIdentifier.Builder()
                                                    .withCnp(new CommonsNFSeCnpj.Builder().withCnpj(Optional.ofNullable(receiverLegalEntityCnpj).orElse("76591569000130")).build())
                                                    .build())
                                            .withSocialName("Razão Social Tomador")
                                            .withAddress(new ElotechNFSeAddress.Builder()
                                                    .withAddress("Rua xyz")
                                                    .withNumber("123")
                                                    .withDistrict("Centro")
                                                    .withCityCode("4119905")
                                                    .withUf(CommonsNFSeUF.PR)
                                                    .withCep("84010000")
                                                    .build())
                                            .build())
//                                    .withSpecialTaxationRegime(SpecialTaxationRegime.ME_EPP)
                                    .withTaxIncentive(ElotechTaxIncentive.NO)
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

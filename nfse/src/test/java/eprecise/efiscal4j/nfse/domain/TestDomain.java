
package eprecise.efiscal4j.nfse.domain;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.nfse.LotRps;
import eprecise.efiscal4j.nfse.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.person.address.NFSeUF;
import eprecise.efiscal4j.nfse.person.documents.NFSeCnpj;
import eprecise.efiscal4j.nfse.sharing.Applicant;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatch;
import eprecise.efiscal4j.nfse.statements.ServiceProvider;
import eprecise.efiscal4j.nfse.statements.ServiceTaker;
import eprecise.efiscal4j.nfse.statements.SpecialTaxationRegime;
import eprecise.efiscal4j.nfse.statements.StatementProvisionService;
import eprecise.efiscal4j.nfse.statements.TaxIncentive;
import eprecise.efiscal4j.nfse.statements.rps.Rps;
import eprecise.efiscal4j.nfse.statements.rps.RpsIdentifier;
import eprecise.efiscal4j.nfse.statements.rps.RpsStatus;
import eprecise.efiscal4j.nfse.statements.rps.RpsType;
import eprecise.efiscal4j.nfse.statements.services.IssRequirement;
import eprecise.efiscal4j.nfse.statements.services.IssWithheld;
import eprecise.efiscal4j.nfse.statements.services.ResponsibleRetention;
import eprecise.efiscal4j.nfse.statements.services.Service;
import eprecise.efiscal4j.nfse.statements.services.ServiceItem;
import eprecise.efiscal4j.nfse.statements.services.ServiceItemTaxable;
import eprecise.efiscal4j.nfse.statements.services.ServiceValues;
import eprecise.efiscal4j.nfse.transmission.SOAPBody;
import eprecise.efiscal4j.nfse.transmission.SOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.SOAPHeader;


public class TestDomain {

    private final Logger logger = LoggerFactory.getLogger(TestDomain.class);

    private FiscalDocumentValidator validator;

    public TestDomain() {

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

    public SOAPEnvelope buildSOAPEnvelope() throws Exception {
        try {
            return new SOAPEnvelope.Builder().withSoapHeader(new SOAPHeader()).withSoapBody(new SOAPBody.Builder().withTransmissibleBody(buildLotRpsDispatch()).build()).build();
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
                            .withCnp(new NFSeCnpj.Builder().withCnpj("14445087000115").build())
                            .withMunicipalRegistration("00083700")
                            .withPassword("abcdef")
                            .withHomologation(true)
                            .build())
                    .withLotRps(
                    new LotRps.Builder().withLotNumber("1000").withRpsQuantity(1).withStatementProvisionService(
                    Arrays.asList(new StatementProvisionService.Builder()
                            .withInfo(new StatementProvisionService.Info.Builder()
                                    .withCompetence("2017-01-20")
                                    .withRps(new Rps.Builder()
                                            .withIdentifier(new RpsIdentifier.Builder()
                                                    .withType(RpsType.PROVISIONAL_SERVICE_RECEIPT)
                                                    .withSerie("E")
                                                    .withNumber("410")
                                                    .build())
                                            .withStatus(RpsStatus.NORMAL)
                                            .withEmissionDate("2017-03-20")
                                            .build())
                                    .withService(new Service.Builder()
                                            .withServiceValues(new ServiceValues.Builder()
                                                    .withServiceValue("2868.41")
                                                    .withDeductionValue("0.00")
                                                    .withPisValue("0.00")
                                                    .withCofinsValue("0.00")
                                                    .withInssValue("0.00")
                                                    .withIrValue("0.00")
                                                    .withCsllValue("0.00")
                                                    .withOtherRetentionsValue("0.00")
                                                    .withIssValue("57.37")
                                                    .withIssAliquot("2")
                                                    .withDiscountUnconditionedValue("0.00")
                                                    .withDiscountConditionedValue("0.00")
                                                    .build())
                                            .withIssWithheld(IssWithheld.NO)
                                            .withResponsibleRetention(ResponsibleRetention.TAKER)
                                            .withDiscrimination("Teste discriminação")
                                            .withCityCode("4119905")
                                            .withIssRequirement(IssRequirement.REQUIRED)
                                            .withCityIncidenceCode("4119905")
                                            .withServiceItems(Arrays.asList(new ServiceItem.Builder()
                                                    .withItemServiceList("1702")
                                                    .withCnaeCode("8211300")
                                                    .withDescription("Teste descrição")
                                                    .withTaxable(ServiceItemTaxable.YES)
                                                    .withQuantity("1")
                                                    .withUnitaryValue("2868.41")
                                                    .withDiscountValue("0.00")
                                                    .withNetValue("2868.41")
                                                    .build()))
                                            .build())
                                    .withServiceProvider(new ServiceProvider.Builder()
                                            .withIdentifier(new ServiceProvider.ServiceProviderIdentifier.Builder()
                                                    .withCnp(new NFSeCnpj.Builder().withCnpj("14445087000115").build())
                                                    .withMunicipalRegistration("00083700")
                                                    .build())
                                            .withSocialName("Razão Social Prestador")
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
                                                    .withCnp(new NFSeCnpj.Builder().withCnpj("76591569000130").build())
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
                                    .withSpecialTaxationRegime(SpecialTaxationRegime.MUNICIPAL_MICRO_ENTERPRISE)
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

}

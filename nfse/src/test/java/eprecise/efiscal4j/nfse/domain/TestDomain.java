
package eprecise.efiscal4j.nfse.domain;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.nfse.LotRps;
import eprecise.efiscal4j.nfse.statements.ServiceProvider;
import eprecise.efiscal4j.nfse.statements.ServiceTaker;
import eprecise.efiscal4j.nfse.statements.StatementProvisionService;
import eprecise.efiscal4j.nfse.statements.TaxIncentive;
import eprecise.efiscal4j.nfse.statements.rps.Rps;
import eprecise.efiscal4j.nfse.statements.services.IssRequirement;
import eprecise.efiscal4j.nfse.statements.services.IssWithheld;
import eprecise.efiscal4j.nfse.statements.services.ResponsibleRetention;
import eprecise.efiscal4j.nfse.statements.services.Service;
import eprecise.efiscal4j.nfse.statements.services.ServiceItem;
import eprecise.efiscal4j.nfse.statements.services.ServiceValues;


public class TestDomain {

    private final Logger logger = LoggerFactory.getLogger(TestDomain.class);

    private FiscalDocumentValidator validator;

    public TestDomain() {

    }

    public TestDomain(final String xsdPath) {
        this();
        this.setXsdPath(xsdPath);
    }

    private boolean containsCertificate() {
        return false;
    }

    public void setXsdPath(final String xsdPath) {
        try {
            this.validator = new FiscalDocumentValidator(this.getClass().getResource(xsdPath));
        } catch (final IOException ex) {
            this.getLogger().error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public LotRps buildLotRps() throws Exception {
        //@formatter:off
        try {
            final LotRps lotRps = new LotRps.Builder().withLotNumber("1000").withRpsQuantity(1).withStatementProvisionService(
                    Arrays.asList(new StatementProvisionService.Builder()
                            .withInfo(new StatementProvisionService.Info.Builder()
                                    .withCompetence("2017-01-01")
                                    .withRps(new Rps())
                                    .withService(new Service.Builder()
                                            .withServiceValues(new ServiceValues.Builder()
                                                    .withServiceValue("13")
                                                    .withDeductionValue("0")
                                                    .withPisValue("0")
                                                    .withCofinsValue("0")
                                                    .withInssValue("0")
                                                    .withIrValue("0")
                                                    .withCsllValue("0")
                                                    .withOtherRetentionsValue("0")
                                                    .withDiscountUnconditionedValue("0")
                                                    .withDiscountConditionedValue("0")
                                                    .build())
                                            .withIssWithheld(IssWithheld.NO)
                                            .withResponsibleRetention(ResponsibleRetention.NO)
                                            .withDiscrimination("Teste discriminação")
                                            .withCountryCode("5555")
                                            .withIssRequirement(IssRequirement.NO_INCIDENCE)
                                            .withCityIncidenceCode("1100015")
                                            .withProcessNumber("406540")
                                            .withCityCode("1100015")
                                            .withServiceItems(Arrays.asList(new ServiceItem()))
                                            .build())
                                    .withServiceProvider(new ServiceProvider())
                                    .withServiceTaker(new ServiceTaker())
                                    .withTaxIncentive(TaxIncentive.YES)
                                    .build()).build())).build();
            return lotRps;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        //@formatter:on
    }

    public Logger getLogger() {
        return this.logger;
    }

    public FiscalDocumentValidator getValidator() {
        return this.validator;
    }

}

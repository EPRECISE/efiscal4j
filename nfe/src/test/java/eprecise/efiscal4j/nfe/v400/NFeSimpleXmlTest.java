
package eprecise.efiscal4j.nfe.v400;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator.ValidationResult;
import eprecise.efiscal4j.nfe.v400.additionalinfo.AdditionalInfo;
import eprecise.efiscal4j.nfe.v400.address.Address;
import eprecise.efiscal4j.nfe.v400.address.City;
import eprecise.efiscal4j.nfe.v400.address.Country;
import eprecise.efiscal4j.nfe.v400.charging.Duplicate;
import eprecise.efiscal4j.nfe.v400.charging.Invoice;
import eprecise.efiscal4j.nfe.v400.charging.NFeCharging;
import eprecise.efiscal4j.nfe.v400.payment.NFePayment;
import eprecise.efiscal4j.nfe.v400.payment.NFePaymentDetail;
import eprecise.efiscal4j.nfe.v400.payment.PaymentMethod;
import eprecise.efiscal4j.nfe.v400.person.Emitter;
import eprecise.efiscal4j.nfe.v400.person.Receiver;
import eprecise.efiscal4j.nfe.v400.sharing.NFeDispatchResponseMethod;
import eprecise.efiscal4j.nfe.v400.tax.Tax;
import eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS;
import eprecise.efiscal4j.nfe.v400.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.v400.tax.icms.ProductOrigin;
import eprecise.efiscal4j.nfe.v400.tax.pis.PIS;
import eprecise.efiscal4j.nfe.v400.total.ICMSTotal;
import eprecise.efiscal4j.nfe.v400.total.NFeTotal;
import eprecise.efiscal4j.nfe.v400.transmission.TransmissionChannel;
import eprecise.efiscal4j.nfe.v400.transport.NFeTransport;
import eprecise.efiscal4j.nfe.v400.transport.ShippingModality;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


public class NFeSimpleXmlTest {
    
    private final Certificate keyCertificate = new Certificate(() -> new FileInputStream(NFeTestParams.getCertificatePath()), NFeTestParams.getCertificatePin());

    @Test
    public void simpleXmlGenerator() throws Exception {
        final String xml = new FiscalDocumentSerializer<>(buildNFe()).serialize();
        Assert.assertNotEquals(xml, "");
    }

    @Test
    public void validateByXsd() throws Exception {
        final FiscalDocumentValidator validator = new FiscalDocumentValidator(this.getClass().getResource(NFe.XSD));
        final String xml = new FiscalDocumentSerializer<>(buildNFe()).serialize();
        final ValidationResult validate = validator.validate(xml);
        Assert.assertTrue(validate.getError(), validate.isValid());
    }
    
    @Test
    public void transmitAuthorization() throws Exception {
        final TransmissionChannel transmissionChannel = new TransmissionChannel(keyCertificate);
        final TransmissionResult transmissionResult = transmissionChannel.transmitAuthorization(this.buildNFe());
        final NFeDispatchResponseMethod returnMethod = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(), NFeDispatchResponseMethod.class).deserialize();

    }

    public NFe buildNFe() throws Exception {
    // @formatter:off
            final DefaultSigner signer = new DefaultSigner(keyCertificate);
            return new NFe.Builder()
                .withNFeInfo(new NFeInfo.Builder()
                        .withNFeIdentification(new NFeIdentification.Builder()
                                .withApplicationVersion("1.00")
                                .withDanfePrintFormat(DANFEPrintFormat.DANFE_PAISAGEM)
                                .withDestinationOperationIdentifier(DestinationOperationIdentifier.INTERNA)
                                .withEmissionDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()))
                                .withFinalCustomerOperation(FinalCustomerOperation.CONSUMIDOR_FINAL)
                                .withFiscalDocumentModel(FiscalDocumentModel.NFE)
                                .withFiscalDocumentNumber("1")
                                .withFiscalDocumentSeries("1")
                                .withFiscalDocumentType(FiscalDocumentType.SAIDA)
                                .withNFeCode(String.format("%08d", new Random().nextInt(100000000)))
                                .withNFeFinality(NFeFinality.NORMAL)
                                .withNFeTransmissionMethod(NFeTransmissionMethod.NORMAL)
                                .withNFeTransmissionProcess(NFeTransmissionProcess.APLICATIVO_CONTRIBUINTE)
                                .withOperationType("Venda de mercadoria adquirida ou recebida de terceiros")
                                .withPaymentMethod(PaymentMethodIndicator.PAGAMENTO_A_VISTA)
                                .withPurchaserPresenceIndicator(PurchaserPresenceIndicator.OPERACAO_PRESENCIAL)
                                .withTaxableEventCityIbgeCode("4104659")
                                .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                .withUFIbgeCode(UF.PR)
                                .build())
                        .withEmitter(new Emitter.Builder()
                                .asLegalEntity()
                                .withCnpj(NFeTestParams.getEmitterCnpj())
                                .withCorporateName("RAZÃO SOCIAL EMPRESA")
                                .withCrt(CRT.SIMPLES_NACIONAL)
                                .withFancyName("NOME FANTASIA DA EMPRESA")
                                .withStateRegistration(NFeTestParams.getEmitterIe())
                                .withAdress(new Address.Builder()
                                        .withStreet("Rua xyz")
                                        .withNumber("Sem Número")
                                        .withDistrict("Centro")
                                        .withCep("84145000")
                                        .withCity(new City.Builder()
                                                .withCountry(new Country.Builder()
                                                        .withIbgeCode("1058")
                                                        .withDescription("Brasil")
                                                        .build())
                                                .withIbgeCode("4119905")
                                                .withDescription("Ponta Grossa")
                                                .withUF(UF.PR)
                                                .build())
                                        .build())
                                .build())
                        .withReceiver(new Receiver.Builder()
                                .asLegalEntity()
                                .withCnpj(NFeTestParams.getReceiverCnpj())
                                .withCorporateName("RAZAO SOCIAL DESTINATÁRIO")
                                .withAdress(new Address.Builder()
                                        .withStreet("Rua xyz")
                                        .withNumber("Sem Número")
                                        .withDistrict("Centro")
                                        .withCep("83203270")
                                        .withCity(new City.Builder()
                                                .withCountry(new Country.Builder()
                                                        .withIbgeCode("1058")
                                                        .withDescription("Brasil")
                                                        .build())
                                                .withIbgeCode("4118204")
                                                .withDescription("Paranaguá")
                                                .withUF(UF.PR)
                                                .build())
                                      .build())
                                .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.ISENTO)
                                .build())
                        .withNFeDetail(Arrays.asList(new NFeDetail.Builder()
                                .withItemOrder("1")
                                .withNFeItem(new NFeItem.Builder()
                                        .withCFOP(CFOP.CFOP_5102)
                                        .withComercialQuantity("1")
                                        .withComercialUnit("UN")
                                        .withComercialUnitaryValue("10.00")
                                        .withGlobalTradeItemNumber("123456789012")
                                        .withItemCode("999")
                                        .withItemDescription("Produto de teste")
                                        .withItemGrossValue("10.00")
                                        .withItemValueComprisesTotal(ItemValueComprisesTotal.COMPOE_TOTAL)
                                        .withNCM("99999999")
                                        .withCest("0000001")
                                        .withTaxableQuantity("1")
                                        .withTaxableUnit("UN")
                                        .withTaxableUnitGlobalTradeItemNumber("123456789012")
                                        .withTaxationUnitaryValue("10.00")
                                        .build())
                                .withTax(new Tax.Builder()
                                        .withIcms(new ICMS.Builder()
                                                .fromCode(ICMS.CSOSN_103)
                                                .withOrigin(ProductOrigin.NACIONAL)
                                                .build())
                                        .withPis(new PIS.Builder()
                                                .fromCode(PIS.CST_07)
                                                .build())
                                        .withCofins(new COFINS.Builder()
                                                .fromCode(COFINS.CST_07)
                                                .build())
                                        .build())
                                .build()))
                        .withNFeTotal(new NFeTotal.Builder()
                                .withICMSTotal(new ICMSTotal.Builder()
                                        .withDiscountTotalValue("0")
                                        .withICMSCalculationBasis("0")
                                        .withICMSSTCalculationBasis("0")
                                        .withICMSSTTotalValue("0")
                                        .withICMSTotalDesoneration("0")
                                        .withICMSTotalValue("0")
                                        .withReceiverUfFCPTotalValue("1.00")
                                        .withReceiverUfIcmsShareTotalValue("0.20")
                                        .withEmitterUfIcmsShareTotalValue("0.10")
                                        .withInsuranceTotalValue("0")
                                        .withItemsTotalValue("10.00")
                                        .withNFeTotalValue("15.00")
                                        .withOtherIncidentalCostsTotalValue("0")
                                        .withShippingTotalValue("0")
                                        .withTaxTotalValue("0")
                                        .withReturnedIpiTotalValue("0")
                                        .withFcpTotalValue("0")
                                        .withPISTotalValue("0")
                                        .withCOFINSTotalValue("0")
                                        .withFcpStRetainedTotalValue("0")
                                        .withFcpStTotalValue("0")
                                        .withIITotalValue("0")
                                        .withIPITotalValue("0")
                                        .build())
                                .build())
                        .withNFeTransport(new NFeTransport.Builder()
                                .withShippingModality(ShippingModality.SEM_OCORRENCIA_TRANSPORTE)
                                .build())
                        .withNFeCharging(new NFeCharging.Builder()
                                .withInvoice(new Invoice.Builder()
                                        .withNumber("C33")
                                        .withOriginalValue("10.00")
                                        .withDiscountValue("3.00")
                                        .withNumber("7.00")
                                        .build())
                                .withDuplicates(Arrays.asList(new Duplicate.Builder()
                                        .withNumber("1")
                                        .withDueDate("2014-12-07")
                                        .withValue("10")
                                        .build()))
                                .build())
                        .withNFePayment(new NFePayment.Builder()
                                .withNFePaymentDetails(Arrays.asList(new NFePaymentDetail.Builder()
                                        .withPaymentMethod(PaymentMethod.DINHEIRO)
                                        .withPaymentValue("15.00")
                                        .build()))
                                .build())
                        .withAdditionalInfo(new AdditionalInfo.Builder()
                                .withAdditionalInfoFisco("Informação de uso do fisco")
                                .withComplementaryInfo("Informação complementar do contribuinte")
                                .build())
                                 .build())
                .build(signer);
         // @formatter:on
    }

}

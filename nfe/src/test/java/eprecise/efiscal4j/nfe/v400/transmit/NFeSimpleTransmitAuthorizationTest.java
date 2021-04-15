
package eprecise.efiscal4j.nfe.v400.transmit;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import eprecise.efiscal4j.commons.domain.CFOP;
import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.NFeTestParams;
import eprecise.efiscal4j.nfe.v400.CRT;
import eprecise.efiscal4j.nfe.v400.DANFEPrintFormat;
import eprecise.efiscal4j.nfe.v400.DestinationOperationIdentifier;
import eprecise.efiscal4j.nfe.v400.FinalCustomerOperation;
import eprecise.efiscal4j.nfe.v400.FiscalDocumentType;
import eprecise.efiscal4j.nfe.v400.ItemValueComprisesTotal;
import eprecise.efiscal4j.nfe.v400.NFe;
import eprecise.efiscal4j.nfe.v400.NFeDetail;
import eprecise.efiscal4j.nfe.v400.NFeFinality;
import eprecise.efiscal4j.nfe.v400.NFeIdentification;
import eprecise.efiscal4j.nfe.v400.NFeInfo;
import eprecise.efiscal4j.nfe.v400.NFeItem;
import eprecise.efiscal4j.nfe.v400.NFeTransmissionMethod;
import eprecise.efiscal4j.nfe.v400.NFeTransmissionProcess;
import eprecise.efiscal4j.nfe.v400.PurchaserPresenceIndicator;
import eprecise.efiscal4j.nfe.v400.StateRegistrationReceiverIndicator;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;
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
import eprecise.efiscal4j.nfe.v400.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.v400.sharing.NFeDispatchResponseMethod;
import eprecise.efiscal4j.nfe.v400.sharing.SynchronousProcessing;
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


/**
 * Teste de transmissão de autorização de NF-e NFeAutorizacao
 *
 * @author Fernando Glizt
 *
 */
public class NFeSimpleTransmitAuthorizationTest {

    @Test
    public void transmitAuthorization() throws Exception {
     // @formatter:off
        Assume.assumeFalse(!NFeTestParams.getCertificatePath().isPresent()
                || !NFeTestParams.getCertificatePin().isPresent()
                || !NFeTestParams.getEmitterCnpj().isPresent()
                || !NFeTestParams.getEmitterIe().isPresent()
                || !NFeTestParams.getReceiverCnpj().isPresent());
     // @formatter:true

        final Certificate keyCertificate = new Certificate(() -> new FileInputStream(NFeTestParams.getCertificatePath().get()), NFeTestParams.getCertificatePin().get());
        final TransmissionChannel transmissionChannel = new TransmissionChannel(keyCertificate);
        final TransmissionResult transmissionResult = transmissionChannel.transmitAuthorization(this.buildNFeDispatch(keyCertificate));
        final NFeDispatchResponseMethod returnMethod = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(), NFeDispatchResponseMethod.class).deserialize();
        final String returnXml = new FiscalDocumentSerializer<>(returnMethod).serialize();
        Assert.assertNotEquals(returnXml, "");
        System.out.println(returnXml);
    }

    public NFeDispatch buildNFeDispatch(final Certificate keyCertificate) throws Exception {
    // @formatter:off
            final DefaultSigner signer = new DefaultSigner(keyCertificate);

            final Integer serie = 10;
            final Integer number = 1;
            final Date emission = new Date();
            final UF uf = UF.RJ;
            final String cep = "";


            final String nfeCode = String.format("%08d", new Random().nextInt(100000000));
            final String accessKey = this.buildAccessKey(emission, serie, number, nfeCode);
            final int checksum = this.buildChecksum(accessKey);
            final String nfeId = this.buildNFeId(accessKey, String.valueOf(checksum));
            return new NFeDispatch.Builder()
                    .withBatchId("1")
                    .withSynchronousProcessing(SynchronousProcessing.SINCRONO)
                    .withNFes(Arrays.asList(new NFe.Builder()
                        .withNFeInfo(new NFeInfo.Builder()
                                .withId(nfeId)
                                .withNFeIdentification(new NFeIdentification.Builder()
                                        .withApplicationVersion("1.00")
                                        .withDanfePrintFormat(DANFEPrintFormat.DANFE_PAISAGEM)
                                        .withDestinationOperationIdentifier(DestinationOperationIdentifier.INTERNA)
                                        .withEmissionDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(emission))
                                        .withFinalCustomerOperation(FinalCustomerOperation.CONSUMIDOR_FINAL)
                                        .withFiscalDocumentModel(FiscalDocumentModel.NFE)
                                        .withFiscalDocumentNumber(number.toString())
                                        .withFiscalDocumentSeries(serie.toString())
                                        .withFiscalDocumentType(FiscalDocumentType.SAIDA)
                                        .withNFeCode(nfeCode)
                                        .withNFeFinality(NFeFinality.NORMAL)
                                        .withNFeTransmissionMethod(NFeTransmissionMethod.NORMAL)
                                        .withNFeTransmissionProcess(NFeTransmissionProcess.APLICATIVO_CONTRIBUINTE)
                                        .withOperationType("Venda de mercadoria adquirida ou recebida de terceiros")
                                        .withPurchaserPresenceIndicator(PurchaserPresenceIndicator.OPERACAO_PRESENCIAL)
                                        .withTaxableEventCityIbgeCode("3300605")
                                        .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                        .withUFIbgeCode(UF.PR)
                                        .withChecksum(String.valueOf(checksum))
                                        .build())
                                .withEmitter(new Emitter.Builder()
                                        .asLegalEntity()
                                        .withCnpj(NFeTestParams.getEmitterCnpj().get())
                                        .withCorporateName("RAZÃO SOCIAL")
                                        .withCrt(CRT.SIMPLES_NACIONAL)
                                        .withFancyName("NOME FANTASIA DA EMPRESA")
                                        .withStateRegistration(NFeTestParams.getEmitterIe().get())
                                        .withAdress(new Address.Builder()
                                                .withStreet("Rua xyz")
                                                .withNumber("Sem Número")
                                                .withDistrict("Centro")
                                                .withCep("28360000")
                                                .withCity(new City.Builder()
                                                        .withCountry(new Country.Builder()
                                                                .withIbgeCode("1058")
                                                                .withDescription("Brasil")
                                                                .build())
                                                        .withIbgeCode("3300605")
                                                        .withDescription("Bom Jesus do Itabapoana")
                                                        .withUF(UF.RJ)
                                                        .build())
                                                .build())
                                        .build())
                                .withReceiver(new Receiver.Builder()
                                        .asLegalEntity()
                                        .withCnpj(NFeTestParams.getReceiverCnpj().get())
                                        .withCorporateName("Tt Burger Alimentos LTDA")
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
                                                .withNCM("63090010")
                                                .withCest("2806000")
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
                                                .withReceiverUfFCPTotalValue("0.00")
                                                .withReceiverUfIcmsShareTotalValue("0.00")
                                                .withEmitterUfIcmsShareTotalValue("0.00")
                                                .withInsuranceTotalValue("0")
                                                .withItemsTotalValue("10.00")
                                                .withNFeTotalValue("10.00")
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
                                                .withNetValue("10.00")
                                                .withNumber("7.00")
                                                .build())
                                        .withDuplicates(Arrays.asList(new Duplicate.Builder()
                                                .withNumber("1")
                                                .withDueDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                                                .withValue("10.00")
                                                .build()))
                                        .build())
                                .withNFePayment(new NFePayment.Builder()
                                        .withNFePaymentDetails(Arrays.asList(new NFePaymentDetail.Builder()
                                                .withPaymentMethod(PaymentMethod.DINHEIRO)
                                                .withPaymentValue("10.00")
                                                .build()))
                                        .build())
                                .withAdditionalInfo(new AdditionalInfo.Builder()
                                        .withAdditionalInfoFisco("Informação de uso do fisco")
                                        .withComplementaryInfo("Informação complementar do contribuinte")
                                        .build())
                                         .build())
                        .build(signer)))
                    .build();
         // @formatter:on
    }

    private String buildNFeId(final String accessKey, final String checkSum) {
        final StringBuilder accessKeyBuilder = new StringBuilder(accessKey);
        accessKeyBuilder.append(checkSum);
        accessKeyBuilder.insert(0, "NFe");
        return accessKeyBuilder.toString();
    }

    private String buildAccessKey(final Date emissionDate, final Integer serie, final Integer number, final String code) {
        final StringBuilder accessKey = new StringBuilder();

        accessKey.append(UF.RJ.getIbgeCode());
        accessKey.append(new SimpleDateFormat("yy").format(emissionDate));
        accessKey.append(new SimpleDateFormat("MM").format(emissionDate));
        accessKey.append(NFeTestParams.getEmitterCnpj().get());
        accessKey.append(FiscalDocumentModel.NFE.getValue());
        accessKey.append(new DecimalFormat("000").format(serie));
        accessKey.append(new DecimalFormat("000000000").format(number));
        accessKey.append(NFeTransmissionMethod.NORMAL.getValue());
        accessKey.append(code);

        return accessKey.toString();

    }

    private int buildChecksum(final String key) {
        int total = 0;
        int weight = 2;

        for (int i = 0; i < key.length(); i++) {
            total += (key.charAt((key.length() - 1) - i) - '0') * weight;
            weight++;
            if (weight == 10) {
                weight = 2;
            }
        }

        final int remainder = total % 11;
        return ((remainder == 0) || (remainder == 1)) ? 0 : (11 - remainder);
    }
}


package eprecise.efiscal4j.nfe.domain;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.NotImplementedException;
import org.junit.Assert;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.nfe.FiscalDocumentType;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequest;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequestType;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeResponse;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeResponseStatus;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeSchemas;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDfeDocument;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryNSU;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryByAccessKey;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryNSU;
import eprecise.efiscal4j.nfe.sharing.EventProtocol;
import eprecise.efiscal4j.nfe.sharing.EventType;
import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;
import eprecise.efiscal4j.nfe.sharing.ProcessingStatusProtocol;
import eprecise.efiscal4j.nfe.sharing.ProcessingStatusProtocolInfo;
import eprecise.efiscal4j.nfe.summaries.NFeEventSummary;
import eprecise.efiscal4j.nfe.summaries.ProcessedNFeStatus;
import eprecise.efiscal4j.nfe.summaries.ProcessedNFeSummary;


public class NFeDeliveryDFeDomain {

    private final TransmissionEnvironment TRANSMISSION_ENVIRONMENT = TransmissionEnvironment.HOMOLOGACAO;

    public NFeDeliveryDFeRequest buildRequest(NFeDeliveryDFeRequestType type) {
        return new NFeDeliveryDFeRequest.Builder().withAuthorUf(UF.PR).withEnviroment(this.TRANSMISSION_ENVIRONMENT).withCnpj("14241297000191").withType(type).build();
    }

    public NFeDeliveryDFeRequest buildQueryAccesKeyRequest() {
        return this.buildRequest(new NFeQueryByAccessKey.Builder().withAccessKey(TestDomain.randomFixedSizeNumber(44)).build());
    }

    public NFeDeliveryDFeRequest buildQueryByNsuRequest() {
        // return this.buildRequest(new NFeQueryNSU.Builder().withNsu(TestDomain.randomFixedSizeNumber(15)).build());
        return this.buildRequest(new NFeQueryNSU.Builder().withNsu("000000000000005").build());
    }

    public NFeDeliveryDFeRequest buildDeliveryNsuRequest() {
        return this.buildRequest(new NFeDeliveryNSU.Builder().withLastNsu("000000000000000").build());
    }

    public NFeDeliveryDFeResponse buildResponse() throws Exception {
        //@formatter:off
        return new NFeDeliveryDFeResponse.Builder()
                .withEnviroment(this.TRANSMISSION_ENVIRONMENT)
                .withAppVersion("1")
                .withStatusCode(NFeDeliveryDFeResponseStatus.LOT_PROCESSED)
                .withStatusDescription("Cod 1")
                .withResponse(ZonedDateTime.now())
                .withLastNsu(1)
                .withMaxNsu(10)
                .withDocument(this.getProcessedNFeSummary())
                .withDocument(this.getProcessedNFeDocument())
                .withDocument(this.getNFeEventSummaryDocument())
//                .withDocument(this.getEventProtocolDocument())
                .build();
        //@formatter:on
    }

    private NFeDeliveryDfeDocument getProcessedNFeSummary() {
        //@formatter:off
        final ProcessedNFeSummary nfeSummary = new ProcessedNFeSummary.Builder()
                .withAccessKey("01234567890123456789012345678901234567891234")
                .withAuthorizationDateTime(LocalDateTime.of(2017, 2, 1, 1, 0)
                .atZone(ZoneId.systemDefault()))
                .withCnpj("92387160000153")
                .withDigestValue("dmFpIGJhc2UgNjQ=")
                .withEmissionDateTime(LocalDateTime.of(2017, 1, 1, 1, 0).atZone(ZoneId.systemDefault()))
                .withEventProtocolNumber(1000)
                .withFiscalDocumentType(FiscalDocumentType.SAIDA)
                .withName("Meu nome")
                .withNfeStatus(ProcessedNFeStatus.AUTHORIZED)
                .withNfeTotalValue("10.20")
                .withStateRegistration("ISENTO")
                .build();

        return new NFeDeliveryDfeDocument.Builder().withContent(nfeSummary).withNsu(2L).withSchema(NFeDeliveryDFeSchemas.RES_NFE).build();
        //@formatter:on
    }

    private NFe getNfe() throws IOException, URISyntaxException {
        final URL xmlUrl = this.getClass().getResource("/eprecise/efiscal4j/nfe/in/xml/nfe/001.xml");

        final NFe nfe = new FiscalDocumentDeserializer<NFe>(xmlUrl, NFe.class).considering(NFe.getValidationConsideringClasses()).deserialize();
        Assert.assertNotNull(nfe);
        try {
            ValidationBuilder.from(nfe).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            throw e;
        }

        return nfe;
    }

    private NFeDeliveryDfeDocument getProcessedNFeDocument() throws Exception {
        //@formatter:off
        final ProcessingStatusProtocol status = new ProcessingStatusProtocol.Builder()
                .withProcessingStatusProtocolInfo(new ProcessingStatusProtocolInfo.Builder()
                .withTransmissionEnvironment(this.TRANSMISSION_ENVIRONMENT)
                .withApplicationVersion("RS20110816085649")
                .withAcessKey("43110899999090910199550110118160951007055470")
                .withProcessingDateTime("2013-02-06T14:51:19-02:00")
                .withProtocolNumber("143110000000289")
                .withDigestValue("c1wZvqlmu38VP0WzYtbannOjCC0=")
                .withStatusCode("100")
                .withStatusDescription("Autorizado o uso da NF-e")
                .withId("ID143110000000289")
                .build()).build();
        
        final NFe nfe = this.getNfe(); 
        
        final ProcessedNFe processedNfe = new ProcessedNFe.Builder().withNfe(nfe).withProcessingStatusProtocol(status)
                .build();

        return new NFeDeliveryDfeDocument.Builder().withContent(processedNfe).withNsu(4L).withSchema(NFeDeliveryDFeSchemas.PROC_NFE).build();
        //@formatter:on
    }

    private NFeDeliveryDfeDocument getNFeEventSummaryDocument() {
        //@formatter:off
        final NFeEventSummary nfeEventSummary = new NFeEventSummary.Builder()
                .withAccessKey("01234567890123456789012345678901234567895678")
                .withAuthorizationDateTime(LocalDateTime.of(2017, 2, 1, 1, 0).atZone(ZoneId.systemDefault()))
                .withCnpj("92387160000153")
                .withEventProtocolNumber(1001)
                .withEventDateTime(LocalDateTime.of(2017, 1, 1, 1, 0).atZone(ZoneId.systemDefault()))
                .withEventDescription("Minha descrição de evento")
                .withEventSequence("12")
                .withEventType(EventType.CONFIRMACAO_OPERACAO)
                .withIbgeOrgan(IBGEOrgan.PR).build();

        return new NFeDeliveryDfeDocument.Builder().withContent(nfeEventSummary).withNsu(4L).withSchema(NFeDeliveryDFeSchemas.RES_EVENT).build();
        //@formatter:on
    }

    private NFeDeliveryDfeDocument getEventProtocolDocument() {
        final EventProtocol eventProtocol = new EventProtocol.Builder().withEvent(null).withEventResponse(null).build();

        new NFeDeliveryDfeDocument.Builder().withContent(eventProtocol).withNsu(5L).withSchema(NFeDeliveryDFeSchemas.PROC_EVENTO_NFE).build();

        throw new NotImplementedException();
    }

    public NFeDeliveryDFeResponse buildQueryAccesKeyResponse() throws Exception {
        return this.buildResponse();
    }

    public NFeDeliveryDFeResponse buildQueryByNsuResponse() throws Exception {
        return this.buildResponse();
    }

    public NFeDeliveryDFeResponse buildDeliveryNsuResponse() throws Exception {
        return this.buildResponse();
    }
}

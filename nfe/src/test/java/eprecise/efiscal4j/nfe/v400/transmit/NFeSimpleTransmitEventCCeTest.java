
package eprecise.efiscal4j.nfe.v400.transmit;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.NFeTestParams;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v400.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.v400.sharing.Event;
import eprecise.efiscal4j.nfe.v400.sharing.EventDetailCCe;
import eprecise.efiscal4j.nfe.v400.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.v400.sharing.EventDispatchResponseMethod;
import eprecise.efiscal4j.nfe.v400.sharing.EventInfo;
import eprecise.efiscal4j.nfe.v400.sharing.EventType;
import eprecise.efiscal4j.nfe.v400.transmission.TransmissionChannel;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


/**
 * Teste de evento de cancelamento de NF-e RecepcaoEvento
 * 
 * @author Fernando Glizt
 * 
 */
public class NFeSimpleTransmitEventCCeTest {

    @Test
    public void transmitNFeStatusSearch() throws Exception {
        Assume.assumeFalse(!NFeTestParams.getCertificatePath().isPresent() || !NFeTestParams.getCertificatePin().isPresent() || !NFeTestParams.getEmitterCnpj().isPresent());
        final Certificate keyCertificate = new Certificate(() -> new FileInputStream(NFeTestParams.getCertificatePath().get()), NFeTestParams.getCertificatePin().get());
        final TransmissionChannel transmissionChannel = new TransmissionChannel(keyCertificate);
        final TransmissionResult transmissionResult = transmissionChannel.transmitEventReceptionCCe(this.buildEventCCe(keyCertificate), FiscalDocumentModel.NFE);
        final EventDispatchResponseMethod eventDispatchResponseMethod = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(), EventDispatchResponseMethod.class).deserialize();
        final String returnXml = new FiscalDocumentSerializer<>(eventDispatchResponseMethod).serialize();
        Assert.assertNotEquals(returnXml, "");
        System.out.println(returnXml);
    }

    public EventDispatch buildEventCCe(final Certificate keyCertificate) throws Exception {
      //@formatter:off
        final DefaultSigner signer = new DefaultSigner(keyCertificate);
        return new EventDispatch.Builder()
                .withBatchId("1")
                .withEvents(Arrays.asList(new Event.Builder()
                        .withEventInfo(new EventInfo.Builder()
                                .withIbgeOrgan(IBGEOrgan.PR)
                                .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                .withAuthorCnpj(NFeTestParams.getEmitterCnpj().get())
                                .withAcessKey("41180504229224000120551000000000021024491660")
                                .withEventDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()))
                                .withEventType(EventType.CCE)
                                .withEventSeqNumber("1")
                                .withEventVersion(FiscalDocumentVersion.VERSION_1_00.getValue())
                                .withEventDetail(new EventDetailCCe.Builder()
                                        .withCorrection("Correção teste de Carta de Correção")
                                        .build())
                                .build())
                        .build(signer)))
                .build();
        //@formatter:on
    }
}

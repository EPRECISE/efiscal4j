
package eprecise.efiscal4j.nfe.v400.transmit;

import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.v400.NFeTestParams;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v400.sharing.NFeStatusSearch;
import eprecise.efiscal4j.nfe.v400.sharing.NFeStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.v400.transmission.TransmissionChannel;

/**
 * Teste de consulta de status de nf-e
 * NFeConsultaProtocolo
 * 
 * @author Fernando Glizt
 * 
 */
public class NFeSimpleTransmitStatusSearchTest {

    @Test
    public void transmitNFeStatusSearch() throws Exception {
        Assume.assumeFalse(!NFeTestParams.getCertificatePath().isPresent() || !NFeTestParams.getCertificatePin().isPresent());
        final Certificate keyCertificate = new Certificate(() -> new FileInputStream(NFeTestParams.getCertificatePath().get()), NFeTestParams.getCertificatePin().get());
        final TransmissionChannel transmissionChannel = new TransmissionChannel(keyCertificate);
        final TransmissionResult transmissionResult = transmissionChannel.transmitNFeStatusSearch(this.buildNFeStatusSearch(), FiscalDocumentModel.NFE, UF.PR);
        final NFeStatusSearchResponseMethod nfeStatusSearchResponseMethod = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(), NFeStatusSearchResponseMethod.class).deserialize();
        final String returnXml = new FiscalDocumentSerializer<>(nfeStatusSearchResponseMethod).serialize();
        Assert.assertNotEquals(returnXml, "");
        System.out.println(returnXml);
    }

    public NFeStatusSearch buildNFeStatusSearch() throws Exception {
        //@formatter:off
        return new NFeStatusSearch.Builder()
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                     .withAcessKey("41180504229224000120551000000000021024491660")                                                                                      
                     .build();
        //@formatter:on
    }
}

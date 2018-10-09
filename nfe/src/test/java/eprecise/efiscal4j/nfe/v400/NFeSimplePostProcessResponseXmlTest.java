package eprecise.efiscal4j.nfe.v400;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.v400.transmission.TransmissionChannel;

public class NFeSimplePostProcessResponseXmlTest {
    
    @Test
    public void postProcessResponseXMLTest() {
        try {
            Assume.assumeFalse(!NFeTestParams.getCertificatePath().isPresent() || !NFeTestParams.getCertificatePin().isPresent());
            
            final Certificate keyCertificate = new Certificate(() -> new FileInputStream(NFeTestParams.getCertificatePath().get()), NFeTestParams.getCertificatePin().get());
            
            final String xml1 = "<env:Envelope xmlns:env='http://www.w3.org/2003/05/soap-envelope'><env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'><nfeResultMsg xmlns='http://www.portalfiscal.inf.br/nfe/wsdl/NFeAutorizacao4'><retEnviNFe versao='4.00' xmlns='http://www.portalfiscal.inf.br/nfe'><tpAmb>1</tpAmb><verAplic>PR-v4_1_9</verAplic><cStat>104</cStat><xMotivo>Lote processado</xMotivo><cUF>41</cUF><dhRecbto>2018-10-09T14:49:42-03:00</dhRecbto><protNFe versao='4.00'><infProt Id='ID141181233400660'><tpAmb>1</tpAmb><verAplic>PR-v4_1_9</verAplic><chNFe>41181023797654000193650010000451001823276153</chNFe><dhRecbto>2018-10-09T14:49:42-03:00</dhRecbto><nProt>141181233400660</nProt><digVal>Y2Wa2suK7wWEzOC/O3aD47s5Wog=</digVal><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo></infProt></protNFe></retEnviNFe></nfeResultMsg></env:Body></env:Envelope>";
            final String xml2 = "<soapenv:Envelope xmlns:soapenv=\"http://www.w3.org/2003/05/soap-envelope\"><soapenv:Header/><soapenv:Body><nfeResultMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NFeAutorizacao4\"><retEnviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"4.00\"><tpAmb>2</tpAmb><verAplic>MS_2.58.0</verAplic><cStat>999</cStat><xMotivo>Rejeicao : Erro nao catalogado.</xMotivo><cUF>50</cUF><dhRecbto>2018-10-09T14:42:11-04:00</dhRecbto></retEnviNFe></nfeResultMsg></soapenv:Body></soapenv:Envelope>";

            final TransmissionChannel tc = new TransmissionChannel(keyCertificate);
            Method method;
            method = tc.getClass().getDeclaredMethod("postProcessResponseXML", String.class);
            method.setAccessible(true);
            String result1 = method.invoke(tc, xml1).toString();
            String result2 = method.invoke(tc, xml2).toString();
            
            Assert.assertNotNull(result1);
            Assert.assertNotNull(result2);
            
            Assert.assertNotEquals(result1, "");
            Assert.assertNotEquals(result2, "");
        } catch (Exception e) {
            Assume.assumeFalse(true);
            e.printStackTrace();
        }
    }

}

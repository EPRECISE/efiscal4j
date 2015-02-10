package eprecise.efiscal4j.cte;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

public class CTeParserXML {
    
    @Test
    public void xmlParser() throws JAXBException {
	final URL xml = this.getClass().getResource("/eprecise/efiscal4j/cte/test_cte.xml");
	final JAXBContext jaxbContext = JAXBContext.newInstance(CTeInfo.class);
	final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	final CTeInfo cTeInfo = (CTeInfo) unmarshaller.unmarshal(xml);
	Assert.assertTrue(cTeInfo != null);
	Assert.assertTrue(cTeInfo.getEmitter().getCnpj() != null);
	Assert.assertEquals("69305329000167", cTeInfo.getEmitter().getCnpj());
    }
}

package eprecise.efiscal4j.commons.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

public class FiscalDocumentDeserializer {
    
    private final String xmlContent;
    
    public FiscalDocumentDeserializer(String xml) {
	this.xmlContent = xml;
    }
    
    public FiscalDocumentDeserializer(InputStream xml) throws IOException {
	this(IOUtils.toString(xml));
    }
    
    public FiscalDocumentDeserializer(URL xml) throws IOException {
	this(IOUtils.toString(xml.openStream()));
    }
    
    public FiscalDocumentDeserializer(File xml) throws IOException {
	this(IOUtils.toString(new FileInputStream(xml)));
    }
    
    public <T> T deserialize(Class<T> entityClass) {
	try {
	    final JAXBContext jaxbContext = JAXBContext.newInstance(entityClass);
	    final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	    return entityClass.cast(unmarshaller.unmarshal(new StringReader(this.getPreparedXML())));
	} catch (final JAXBException e) {
	    throw new RuntimeException(e);
	}
    }
    
    private String getPreparedXML() {
	return this.xmlContent.replace("xmlns=\"http://www.portalfiscal.inf.br/cte\"", "");
    }
    
}


package eprecise.efiscal4j.commons.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;

import org.apache.commons.io.IOUtils;


public class FiscalDocumentDeserializer<T> {

    private final String xmlContent;

    private final Class<T> mainClass;

    private final List<Class<?>> toConsider = new ArrayList<>();

    public FiscalDocumentDeserializer(String xml, Class<T> mainClass) {
        this.xmlContent = xml;
        this.mainClass = mainClass;
        this.toConsider.add(this.mainClass);
    }

    public FiscalDocumentDeserializer(InputStream xml, Class<T> mainClass) throws IOException {
        this(IOUtils.toString(xml), mainClass);
    }

    public FiscalDocumentDeserializer(URL xml, Class<T> mainClass) throws IOException {
        this(IOUtils.toString(xml.openStream()), mainClass);
    }

    public FiscalDocumentDeserializer(File xml, Class<T> mainClass) throws IOException {
        this(IOUtils.toString(new FileInputStream(xml)), mainClass);
    }

    public FiscalDocumentDeserializer<T> considering(Class<?>... classes) {
        this.toConsider.addAll(Arrays.asList(classes));
        return this;
    }

    public FiscalDocumentDeserializer<T> considering(List<Class<?>> classes) {
        this.toConsider.addAll(classes);
        return this;
    }

    public T deserialize() {
        final Class<?>[] considering = new Class<?>[this.toConsider.size()];
        this.toConsider.toArray(considering);
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(considering);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setEventHandler(new DefaultValidationEventHandler());
            return this.mainClass.cast(unmarshaller.unmarshal(new StringReader(this.getPreparedXML())));
        } catch (final JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPreparedXML() {
        return this.xmlContent.replace("xmlns=\"http://www.portalfiscal.inf.br/cte\"", "").replace("xmlns=\"http://www.portalfiscal.inf.br/nfe\"", "")
                .replace("xmlns=\"http://www.w3.org/2000/09/xmldsig#\"", "");
    }
}

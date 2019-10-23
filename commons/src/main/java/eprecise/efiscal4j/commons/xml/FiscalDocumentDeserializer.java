
package eprecise.efiscal4j.commons.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;

import org.apache.commons.io.IOUtils;


public class FiscalDocumentDeserializer<T> {

    private final String xmlContent;

    private final Class<T> mainClass;

    private boolean stopOnError = true;

    private final List<Class<?>> toConsider = new ArrayList<>();

    private Optional<FiscalDocumentXmlAdapter> adapter = Optional.empty();

    public FiscalDocumentDeserializer(final String xml, final Class<T> mainClass) {
        this.xmlContent = xml;
        this.mainClass = mainClass;
        this.toConsider.add(this.mainClass);
    }

    public FiscalDocumentDeserializer(final InputStream xml, final Class<T> mainClass) throws IOException {
        this(IOUtils.toString(xml), mainClass);
    }

    public FiscalDocumentDeserializer(final URL xml, final Class<T> mainClass) throws IOException {
        this(IOUtils.toString(xml.openStream()), mainClass);
    }

    public FiscalDocumentDeserializer(final File xml, final Class<T> mainClass) throws IOException {
        this(IOUtils.toString(new FileInputStream(xml)), mainClass);
    }

    public FiscalDocumentDeserializer<T> considering(final Class<?>... classes) {
        this.toConsider.addAll(Arrays.asList(classes));
        return this;
    }

    public FiscalDocumentDeserializer<T> considering(final List<Class<?>> classes) {
        this.toConsider.addAll(classes);
        return this;
    }

    public FiscalDocumentDeserializer<T> stoppingOnError() {
        this.stopOnError = true;
        return this;
    }

    public FiscalDocumentDeserializer<T> notStoppingOnError() {
        this.stopOnError = false;
        return this;
    }

    public FiscalDocumentDeserializer<T> withAdapter(final FiscalDocumentXmlAdapter adapter) {
        this.adapter = Optional.ofNullable(adapter);
        return this;
    }

    public T deserialize() {
        final Class<?>[] considering = new Class<?>[this.toConsider.size()];
        this.toConsider.toArray(considering);
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(considering);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            if (this.stopOnError) {
                unmarshaller.setEventHandler(new DefaultValidationEventHandler());
            }
            return this.mainClass.cast(unmarshaller.unmarshal(new StringReader(this.getPreparedXML())));
        } catch (final JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPreparedXML() {
        final Collection<String> toRemove = new HashSet<>();
        toRemove.add("xmlns=\"http://www.portalfiscal.inf.br/cte\"");
        toRemove.add("xmlns=\"http://www.portalfiscal.inf.br/nfe\"");
        toRemove.add("xmlns=\"http://www.w3.org/2000/09/xmldsig#\"");
        toRemove.add("xmlns=\"http://shad.elotech.com.br/schemas/iss/nfse_v1_2.xsd\"");
        toRemove.add("xmlns=\"http://shad.elotech.com.br/schemas/iss/nfse_v2_03.xsd\"");
        toRemove.add("xmlns=\"http://www.abrasf.org.br/ABRASF/arquivos/nfse.xsd\"");
        toRemove.add("xmlns=\"http://www.abrasf.org.br/nfse.xsd\"");
        String xml = this.xmlContent;

        for (final String str : toRemove) {
            xml = xml.replace(str, "");
        }

        if (adapter.isPresent()) {
            return adapter.get().process(xml);
        }

        return xml;
    }
}

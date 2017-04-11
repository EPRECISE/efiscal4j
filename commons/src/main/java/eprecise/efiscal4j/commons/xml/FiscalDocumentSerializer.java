
package eprecise.efiscal4j.commons.xml;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class FiscalDocumentSerializer<T> {

    private final T entity;

    private final List<Class<?>> toConsider = new ArrayList<>();

    private Optional<FiscalDocumentXmlAdapter> adapter = Optional.empty();

    public FiscalDocumentSerializer(final T entity) {
        this.entity = entity;
        this.toConsider.add(this.entity.getClass());
    }

    public FiscalDocumentSerializer<T> considering(final Class<?>... classes) {
        this.toConsider.addAll(Arrays.asList(classes));
        return this;
    }

    public FiscalDocumentSerializer<T> withAdapter(final FiscalDocumentXmlAdapter adapter) {
        this.adapter = Optional.ofNullable(adapter);
        return this;
    }

    public FiscalDocumentSerializer<T> considering(final List<Class<?>> classes) {
        this.toConsider.addAll(classes);
        return this;
    }

    public String serialize() {
        final Class<?>[] considering = new Class<?>[this.toConsider.size()];
        this.toConsider.toArray(considering);
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(considering);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            // marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new PreferredMapper());

            final StringWriter out = new StringWriter();
            marshaller.marshal(this.entity, out);
            if (adapter.isPresent()) {
                return adapter.get().process(out.toString());
            }
            return out.toString();
        } catch (final JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}

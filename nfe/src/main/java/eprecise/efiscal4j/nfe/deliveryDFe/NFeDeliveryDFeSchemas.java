
package eprecise.efiscal4j.nfe.deliveryDFe;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.nfe.sharing.EventProtocol;
import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;
import eprecise.efiscal4j.nfe.summaries.NFeEventSummary;
import eprecise.efiscal4j.nfe.summaries.ProcessedNFeSummary;


public enum NFeDeliveryDFeSchemas {
                                  RES_NFE("resNFe_v1.00.xsd", ProcessedNFeSummary.class),
                                  PROC_NFE("procNFe_v3.10.xsd", ProcessedNFe.class),
                                  RES_EVENT("resEvento_1.00.xsd", NFeEventSummary.class),
                                  PROC_EVENTO_NFE("procEventoNFe_v1.00.xsd", EventProtocol.class);

    private final String schema;

    private final Class<?> mappedClazz;

    NFeDeliveryDFeSchemas(String schema, Class<?> mappedClazz) {
        this.schema = schema;
        this.mappedClazz = mappedClazz;
    }

    public String get() {
        return this.schema;
    }

    public Class<?> getMappedClazz() {
        return this.mappedClazz;
    }

    public Object unmarshallContent(String content) {
        if (!StringUtils.isNotEmpty(content)) {
            return null;
        }

        try {
            final JAXBContext context = JAXBContext.newInstance(this.mappedClazz);
            return context.createUnmarshaller().unmarshal(new StringReader(content));
        } catch (final JAXBException e) {
            throw new IllegalArgumentException();
        }
    }

    public String marshallContent(Object object) {
        if (object == null) {
            return null;
        }

        if (!NFeDeliveryDFeSchemas.getFromClazz(object.getClass()).isPresent()) {
            throw new IllegalClassForNFeDeliveryDfeSchema();
        }

        try {
            final StringWriter writer = new StringWriter();
            final JAXBContext context = JAXBContext.newInstance(this.mappedClazz);
            context.createMarshaller().marshal(object, writer);
            return writer.getBuffer().toString();
        } catch (final JAXBException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Optional<NFeDeliveryDFeSchemas> getFromClazz(Class<?> clazz) {
        return Stream.of(NFeDeliveryDFeSchemas.values()).filter(s -> s.mappedClazz.equals(clazz)).findFirst();
    }

    public static Optional<NFeDeliveryDFeSchemas> getFromSchema(String schema) {
        return Stream.of(NFeDeliveryDFeSchemas.values()).filter(s -> s.schema.equals(schema)).findFirst();
    }

}

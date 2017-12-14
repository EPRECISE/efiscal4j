
package eprecise.efiscal4j.nfe.deliveryDFe.response;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;


public enum NFeDeliveryDfeSchema {
                                  RES_NFE("resNFe_v1.00.xsd", NFeDeliveryDFeProcessedNfeSummary.class),
                                  PROC_NFE("procNFe_v3.10.xsd", NFeDeliveryDFeProcessedNfe.class),
                                  RES_EVENT("resEvento_1.00.xsd", NFeDeliveryDFeNFeEventSummary.class),
                                  PROC_EVENTO_NFE("procEventoNFe_v1.00.xsd", NFeDeliveryDFeEventProtocol.class);

    private final String schema;

    private final Class<? extends NFeDeliveryDfeDocumentContent> mappedClazz;

    NFeDeliveryDfeSchema(String schema, Class<? extends NFeDeliveryDfeDocumentContent> mappedClazz) {
        this.schema = schema;
        this.mappedClazz = mappedClazz;
    }

    public String get() {
        return this.schema;
    }

    public Class<?> getMappedClazz() {
        return this.mappedClazz;
    }

    public NFeDeliveryDfeDocumentContent unmarshallContent(String content) {
        if (!StringUtils.isNotEmpty(content)) {
            return null;
        }

        try {
            final JAXBContext context = JAXBContext.newInstance(this.mappedClazz);
            return this.mappedClazz.cast(context.createUnmarshaller().unmarshal(new StringReader(content)));
        } catch (final JAXBException e) {
            throw new IllegalArgumentException();
        }
    }

    public <T extends NFeDeliveryDfeDocumentContent> String marshallContent(T object) {
        if (object == null) {
            return null;
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

    public static Optional<NFeDeliveryDfeSchema> getFromSchema(String schema) {
        return Stream.of(NFeDeliveryDfeSchema.values()).filter(s -> s.schema.equals(schema)).findFirst();
    }

}


package eprecise.efiscal4j.nfe.v400.deliveryDFe;

import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.v400.sharing.EventProtocol;
import eprecise.efiscal4j.nfe.v400.sharing.ProcessedNFe;
import eprecise.efiscal4j.nfe.v400.summaries.NFeEventSummary;
import eprecise.efiscal4j.nfe.v400.summaries.ProcessedNFeSummary;


public enum NFeDeliveryDFeSchemas {
                                   RES_NFE("resNFe_v1.01.xsd", ProcessedNFeSummary.class),
                                   PROC_NFE("procNFe_v3.10.xsd", ProcessedNFe.class),
                                   RES_EVENT("resNFe_v1.01.xsd", NFeEventSummary.class),
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
        if (StringUtils.isEmpty(content == null ? null : content.trim())) {
            return null;
        }
        return new FiscalDocumentDeserializer<>(content, this.mappedClazz).notStoppingOnError().deserialize();
    }

    public String marshallContent(Object object) {
        if (object == null) {
            return null;
        }
        if (!NFeDeliveryDFeSchemas.getFromClazz(object.getClass()).isPresent()) {
            throw new IllegalClassForNFeDeliveryDfeSchema();
        }
        return new FiscalDocumentSerializer<>(object).considering(this.mappedClazz).serialize();
    }

    public static Optional<NFeDeliveryDFeSchemas> getFromClazz(Class<?> clazz) {
        return Stream.of(NFeDeliveryDFeSchemas.values()).filter(s -> s.mappedClazz.equals(clazz)).findFirst();
    }

    public static Optional<NFeDeliveryDFeSchemas> getFromSchema(String schema) {
        return Stream.of(NFeDeliveryDFeSchemas.values()).filter(s -> s.schema.equals(schema)).findFirst();
    }

}

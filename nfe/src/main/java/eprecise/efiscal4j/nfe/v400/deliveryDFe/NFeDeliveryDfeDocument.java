
package eprecise.efiscal4j.nfe.v400.deliveryDFe;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeDeliveryDFeDocumentContent;
import eprecise.efiscal4j.nfe.v400.types.NFeDeliveryDFeNSU;
import eprecise.efiscal4j.nfe.v400.types.NFeDeliveryDFeSchema;


public class NFeDeliveryDfeDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlTransient final NFeDeliveryDFeDocumentContent.Converter contentConverter = new NFeDeliveryDFeDocumentContent.Converter();

    private @XmlTransient final NFeDeliveryDFeNSU.Converter nsuConverter = new NFeDeliveryDFeNSU.Converter();

    private @XmlValue @NotNull @NFeDeliveryDFeDocumentContent final String content;

    private @XmlAttribute(name = "NSU") @NotNull @Size(max = 15) @NFeDeliveryDFeNSU final String nsu;

    private @XmlAttribute(name = "schema") @NotNull @NFeDeliveryDFeSchema final String schema;

    public static class Builder {

        private Object content;

        private long nsu;

        private NFeDeliveryDFeSchemas schema;

        /**
         * Informação resumida ou documento fiscal eletrônico de interesse da pessoa ou empresa. O conteúdo desta tag estará compactado no padrão gZip. O tipo do campo é base64Binary.
         */
        public Builder withContent(Object content) {
            if (content == null || !NFeDeliveryDFeSchemas.getFromClazz(content.getClass()).isPresent()) {
                throw new IllegalClassForNFeDeliveryDfeSchema();
            }

            this.content = content;
            return this;
        }

        /**
         * NSU do documento fiscal
         */
        public Builder withNsu(long nsu) {
            this.nsu = nsu;
            return this;
        }

        /**
         * Identificação do Schema XML que será utilizado para validar o XML existente no conteúdo da tag docZip. Vai identificar o tipo do documento e sua versão. Exemplos: resNFe_v1.00.xsd,
         * procNFe_v3.10.xsd, resEvento_1.00.xsd, procEventoNFe_v1.00.xsd
         */
        public Builder withSchema(NFeDeliveryDFeSchemas schema) {
            this.schema = schema;
            return this;
        }

        public NFeDeliveryDfeDocument build() {
            final NFeDeliveryDfeDocument entity = new NFeDeliveryDfeDocument(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeDeliveryDfeDocument() {
        this.content = null;
        this.nsu = null;
        this.schema = null;
    }

    private NFeDeliveryDfeDocument(Builder builder) {
        this.content = this.contentConverter.serialize(builder.schema.marshallContent(builder.content));
        this.nsu = this.nsuConverter.serialize(builder.nsu);
        this.schema = builder.schema.get();
    }

    public Object getContent() {
        final String rawContent = this.getRawContent();
        if (rawContent == null) {
            return null;
        }
        return NFeDeliveryDFeSchemas.getFromSchema(this.schema).map(s -> s.unmarshallContent(rawContent)).orElse(null);
    }

    public String getRawContent() {
        return this.content == null ? null : this.contentConverter.parse(this.content);
    }

    public long getNsu() {
        return this.nsuConverter.parse(this.nsu);
    }

    public NFeDeliveryDFeSchemas getSchema() {
        return NFeDeliveryDFeSchemas.getFromSchema(this.schema).orElse(null);
    }

}

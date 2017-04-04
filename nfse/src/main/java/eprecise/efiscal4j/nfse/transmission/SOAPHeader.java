
package eprecise.efiscal4j.nfse.transmission;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;


@XmlRootElement(name = "Header")
@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPHeader extends Assignable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static class Builder {

        public SOAPHeader build() {
            final SOAPHeader entity = new SOAPHeader(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        public SOAPHeader build(final Signer signer) throws Exception {
            SOAPHeader entity = new SOAPHeader(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (SOAPHeader) signer.sign(entity);
            return entity;
        }
    }

    public SOAPHeader() {

    }

    public SOAPHeader(final Builder builder) {
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, SOAPHeader.class).deserialize();
    }

    @Override
    public String getRootTagName() {
        return "Header";
    }

    @Override
    public String getAssignableTagName() {
        return "Header";
    }

    @Override
    public String getIdAttributeTagName() {
        return "Id";
    }

}

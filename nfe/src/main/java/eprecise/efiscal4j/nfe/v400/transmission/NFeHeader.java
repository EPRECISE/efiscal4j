
package eprecise.efiscal4j.nfe.v400.transmission;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    public @XmlTransient static final String BASE_XMLNS = "http://www.portalfiscal.inf.br/nfe/wsdl/";

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns;

    public static class Builder {

        private String xmlns;

        /**
         * 
         * @param xmlns
         * @return
         */
        public Builder withXmlns(String xmlns) {
            this.xmlns = xmlns;
            return this;
        }


        public NFeHeader build() {
            final NFeHeader entity = new NFeHeader(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeHeader() {
        this.xmlns = null;
    }

    public NFeHeader(Builder builder) {
        this.xmlns = builder.xmlns;
    }

    public String getXmlns() {
        return this.xmlns;
    }

}


package eprecise.efiscal4j.nfe;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Tipo Nota Fiscal Eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "NFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFe implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "infNFe") @NotNull final NFeInfo nFeInfo;

    public static class Builder {

        private NFeInfo nFeInfo;

        /**
         * @see NFeInfo
         * 
         * @param nFeInfo
         * @return
         */
        public Builder withNFeInfo(NFeInfo nFeInfo) {
            this.nFeInfo = nFeInfo;
            return this;
        }

        public NFe build() {
            final NFe entity = new NFe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFe() {
        this.nFeInfo = null;
    }

    public NFe(Builder builder) {
        this.nFeInfo = builder.nFeInfo;
    }

    public NFeInfo getNFeInfo() {
        return this.nFeInfo;
    }
}

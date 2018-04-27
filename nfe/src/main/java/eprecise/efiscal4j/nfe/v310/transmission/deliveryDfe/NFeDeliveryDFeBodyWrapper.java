
package eprecise.efiscal4j.nfe.v310.transmission.deliveryDfe;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.transmission.NFeBody;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDeliveryDFeBodyWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns = "http://www.portalfiscal.inf.br/nfe/wsdl/NFeDistribuicaoDFe";

    private @XmlElement(name = "nfeDadosMsg") NFeBody nFeBody;

    public static class Builder {

        private NFeBody nFeBody;

        /**
         * 
         * @param nfeDadosMsg
         * @return
         */
        public Builder withNfeBody(NFeBody nFeBody) {
            this.nFeBody = nFeBody;
            return this;
        }

        public NFeDeliveryDFeBodyWrapper build() {
            final NFeDeliveryDFeBodyWrapper entity = new NFeDeliveryDFeBodyWrapper(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeDeliveryDFeBodyWrapper() {

    }

    private NFeDeliveryDFeBodyWrapper(Builder builder) {
        this.nFeBody = builder.nFeBody;
    }

}

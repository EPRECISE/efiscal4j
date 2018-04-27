
package eprecise.efiscal4j.nfe.v400.transmission.deliveryDfe;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDeliveryDFeSoapBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nfeDistDFeInteresse") NFeDeliveryDFeBodyWrapper wrapper;

    public static class Builder {

        private NFeDeliveryDFeBodyWrapper wrapper;

        /**
         * 
         * @param nfeDistDFeInteresse
         * @return
         */
        public Builder withNfeBodyWrapper(NFeDeliveryDFeBodyWrapper wrapper) {
            this.wrapper = wrapper;
            return this;
        }

        public NFeDeliveryDFeSoapBody build() {
            final NFeDeliveryDFeSoapBody entity = new NFeDeliveryDFeSoapBody(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeDeliveryDFeSoapBody() {

    }

    private NFeDeliveryDFeSoapBody(Builder builder) {
        this.wrapper = builder.wrapper;
    }

}

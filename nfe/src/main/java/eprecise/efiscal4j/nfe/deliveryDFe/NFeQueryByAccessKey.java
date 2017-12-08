
package eprecise.efiscal4j.nfe.deliveryDFe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeAccessKey;


public class NFeQueryByAccessKey implements NFeDeliveryDFeRequestType {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "chNFe") @NotNull @Size(max = 44) @NFeAccessKey final String accessKey;

    public static class Builder {

        private String accessKey;

        public Builder withAccessKey(String accessKey) {
            this.accessKey = accessKey;
            return this;
        }

        public NFeQueryByAccessKey build() {
            final NFeQueryByAccessKey entity = new NFeQueryByAccessKey(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeQueryByAccessKey() {
        this.accessKey = null;
    }

    private NFeQueryByAccessKey(Builder builder) {
        this.accessKey = builder.accessKey;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

}

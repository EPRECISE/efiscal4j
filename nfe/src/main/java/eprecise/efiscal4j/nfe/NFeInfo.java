
package eprecise.efiscal4j.nfe;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "ide") @NotNull final NFeIdentification nFeIdentification;

    private @XmlElement(name = "emit") @NotNull final Emitter emitter;

    private @XmlElement(name = "dest") @NotNull final Receiver receiver;

    public static class Builder {

        private NFeIdentification nFeIdentification;

        private Emitter emitter;

        private Receiver receiver;

        public Builder withNFeIdentification(NFeIdentification nFeIdentification) {
            this.nFeIdentification = nFeIdentification;
            return this;
        }

        public Builder withEmitter(Emitter emitter) {
            this.emitter = emitter;
            return this;
        }

        public Builder withReceiver(Receiver receiver) {
            this.receiver = receiver;
            return this;
        }

        public NFeInfo build() {
            NFeInfo entity = new NFeInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeInfo() {
        this.nFeIdentification = null;
        this.emitter = null;
        this.receiver = null;
    }

    public NFeInfo(Builder builder) {
        this.nFeIdentification = builder.nFeIdentification;
        this.emitter = builder.emitter;
        this.receiver = builder.receiver;
    }

}

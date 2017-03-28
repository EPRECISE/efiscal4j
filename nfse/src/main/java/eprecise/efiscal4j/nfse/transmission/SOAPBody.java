
package eprecise.efiscal4j.nfse.transmission;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatch;


@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPBody implements Serializable {

    private static final long serialVersionUID = 1L;

  //@formatter:off
    @XmlElementRefs({
        @XmlElementRef(name = "EnviarLoteRpsSincronoEnvio", type=LotRpsDispatch.class)
    })
    private final TransmissibleBodyImpl transmissibleBody;

    public static class Builder {

        private TransmissibleBodyImpl transmissibleBody;

        /**
         *
         * @param transmissibleBody
         * @return
         */
        public Builder withTransmissibleBody(final TransmissibleBodyImpl transmissibleBody) {
            this.transmissibleBody = transmissibleBody;
            return this;
        }

        public SOAPBody build() {
            final SOAPBody entity = new SOAPBody(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public SOAPBody() {
        transmissibleBody = null;
    }

    public SOAPBody(final Builder builder) {
        transmissibleBody = builder.transmissibleBody;
    }


    public TransmissibleBodyImpl getTransmissibleBody() {
        return transmissibleBody;
    }

}

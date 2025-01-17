
package eprecise.efiscal4j.nfse.transmission.elotech.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.cancel.ElotechNfseDispatchCancel;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechSOAPBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id", namespace = OasisNamespacesPrefixMapper.WSU_URI) String id;

  //@formatter:off
    @XmlElementRefs({
        @XmlElementRef(name = "EnviarLoteRpsSincronoEnvio", type=ElotechLotRpsDispatchSync.class),
        @XmlElementRef(name = "CancelarNfseEnvio", type=ElotechNfseDispatchCancel.class)
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

        public ElotechSOAPBody build() {
            final ElotechSOAPBody entity = new ElotechSOAPBody(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechSOAPBody() {
        transmissibleBody = null;
    }

    public ElotechSOAPBody(final Builder builder) {
        transmissibleBody = builder.transmissibleBody;
    }


    public TransmissibleBodyImpl getTransmissibleBody() {
        return transmissibleBody;
    }

}


package eprecise.efiscal4j.nfse.tc.govbr.services.dispatch;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.govbr.lot.GovbrLotRps;


@XmlRootElement(name = "EnviarLoteRpsEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrLotRpsDispatchAsync extends TransmissibleBodyImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @NotNull @XmlElement(name = "LoteRps") GovbrLotRps lotRps;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsEnvio");

    public static class Builder {

        private GovbrLotRps lotRps;

        /**
         * @param lotRps
         * @return
         */
        public Builder withLotRps(final GovbrLotRps lotRps) {
            this.lotRps = lotRps;
            return this;
        }

        public GovbrLotRpsDispatchAsync build() throws Exception {
            final GovbrLotRpsDispatchAsync entity = new GovbrLotRpsDispatchAsync(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrLotRpsDispatchAsync() {
        lotRps = null;
    }

    public GovbrLotRpsDispatchAsync(final Builder builder) {
        lotRps = builder.lotRps;
    }

    public GovbrLotRps getLotRps() {
        return lotRps;
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return qName;
    }

}

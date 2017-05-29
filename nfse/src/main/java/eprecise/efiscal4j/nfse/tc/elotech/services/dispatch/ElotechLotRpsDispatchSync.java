
package eprecise.efiscal4j.nfse.tc.elotech.services.dispatch;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.elotech.lot.ElotechLotRps;
import eprecise.efiscal4j.nfse.tc.elotech.services.ElotechApplicant;


@XmlRootElement(name = "EnviarLoteRpsSincronoEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechLotRpsDispatchSync extends TransmissibleBodyImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/elotech/nfse_v1_2.xsd";

    private final @XmlAttribute(name = "xmlns") String xmlns = "http://shad.elotech.com.br/schemas/iss/nfse_v1_2.xsd";

    private final @NotNull @XmlElement(name = "IdentificacaoRequerente") ElotechApplicant applicant;

    private final @NotNull @XmlElement(name = "LoteRps") ElotechLotRps lotRps;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsSincronoEnvio");

    public static class Builder {

        private ElotechApplicant applicant;

        private ElotechLotRps lotRps;

        /**
         * @param applicant
         * @return
         */
        public Builder withApplicant(final ElotechApplicant applicant) {
            this.applicant = applicant;
            return this;
        }

        /**
         * @param lotRps
         * @return
         */
        public Builder withLotRps(final ElotechLotRps lotRps) {
            this.lotRps = lotRps;
            return this;
        }

        /**
         * @param xmlns
         * @return
         */
        public Builder withXmlns(final String xmlns) {
            return this;
        }

        public ElotechLotRpsDispatchSync build() {
            final ElotechLotRpsDispatchSync entity = new ElotechLotRpsDispatchSync(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechLotRpsDispatchSync() {
        applicant = null;
        lotRps = null;
    }

    public ElotechLotRpsDispatchSync(final Builder builder) {
        applicant = builder.applicant;
        lotRps = builder.lotRps;
    }

    public String getXmlns() {
        return xmlns;
    }

    public ElotechApplicant getApplicant() {
        return applicant;
    }

    public ElotechLotRps getLotRps() {
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


package eprecise.efiscal4j.nfse.sharing;

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
import eprecise.efiscal4j.nfse.LotRps;


@XmlRootElement(name = "EnviarLoteRpsSincronoEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
public class LotRpsDispatch extends TransmissibleBodyImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/nfse_v1_2.xsd";

    private @XmlAttribute(name = "xmlns") String xmlns;

    private final @NotNull @XmlElement(name = "IdentificacaoRequerente") Applicant applicant;

    private final @NotNull @XmlElement(name = "LoteRps") LotRps lotRps;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsSincronoEnvio");

    public static class Builder {

        private Applicant applicant;

        private LotRps lotRps;

        private String xmlns;

        /**
         * @param applicant
         * @return
         */
        public Builder withApplicant(final Applicant applicant) {
            this.applicant = applicant;
            return this;
        }

        /**
         * @param lotRps
         * @return
         */
        public Builder withLotRps(final LotRps lotRps) {
            this.lotRps = lotRps;
            return this;
        }

        /**
         * @param xmlns
         * @return
         */
        public Builder withXmlns(final String xmlns) {
            this.xmlns = xmlns;
            return this;
        }

        public LotRpsDispatch build() throws Exception {
            final LotRpsDispatch entity = new LotRpsDispatch(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public LotRpsDispatch() {
        applicant = null;
        lotRps = null;
        xmlns = null;
    }

    public LotRpsDispatch(final Builder builder) {
        applicant = builder.applicant;
        lotRps = builder.lotRps;
        xmlns = builder.xmlns;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(final String xmlns) {
        this.xmlns = xmlns;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public LotRps getLotRps() {
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

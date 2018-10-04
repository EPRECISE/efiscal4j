
package eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlRootElement(name = "EnviarLoteRpsSincrono", namespace = "http://tempuri.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrReceiptSyncLotRps implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsSincrono");

    private @XmlElement(name = "xmlEnvio", namespace = "http://tempuri.org/") final GovbrXmlRequest xmlRequest;

    public static class Builder {

        private GovbrXmlRequest xmlRequest;

        /**
         *
         * @param xmlRequest
         * @return
         */
        public Builder withXmlRequest(final GovbrXmlRequest xmlRequest) {
            this.xmlRequest = xmlRequest;
            return this;
        }

        public GovbrReceiptSyncLotRps build() {
            final GovbrReceiptSyncLotRps entity = new GovbrReceiptSyncLotRps(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrReceiptSyncLotRps() {
        xmlRequest = null;
    }

    public GovbrReceiptSyncLotRps(final Builder builder) {
        xmlRequest = builder.xmlRequest;
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return qName;
    }

    public GovbrXmlRequest getXmlRequest() {
        return xmlRequest;
    }

}
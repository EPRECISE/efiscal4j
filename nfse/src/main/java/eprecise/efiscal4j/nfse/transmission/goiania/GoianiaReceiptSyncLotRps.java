
package eprecise.efiscal4j.nfse.transmission.goiania;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlRootElement(name = "GerarNfse", namespace = "http://nfse.goiania.go.gov.br/ws/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaReceiptSyncLotRps implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsSincrono");

    private @XmlElement(name = "ArquivoXML", namespace = "http://nfse.goiania.go.gov.br/ws/") final GoianiaXmlRequest xmlRequest;

    public static class Builder {

        private GoianiaXmlRequest xmlRequest;

        /**
         *
         * @param xmlRequest
         * @return
         */
        public Builder withXmlRequest(final GoianiaXmlRequest xmlRequest) {
            this.xmlRequest = xmlRequest;
            return this;
        }

        public GoianiaReceiptSyncLotRps build() {
            final GoianiaReceiptSyncLotRps entity = new GoianiaReceiptSyncLotRps(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GoianiaReceiptSyncLotRps() {
        xmlRequest = null;
    }

    public GoianiaReceiptSyncLotRps(final Builder builder) {
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

    public GoianiaXmlRequest getXmlRequest() {
        return xmlRequest;
    }

}

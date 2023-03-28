
package eprecise.efiscal4j.nfse.transmission.curitiba.envelope;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlRootElement(name = "RecepcionarXml", namespace = "https://www.e-governeapps2.com.br/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaReceiptXml implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    private @XmlTransient QName qName = new QName("RecepcionarXml");

    private @XmlElement(name = "metodo", namespace = "https://www.e-governeapps2.com.br/") final String method;

    private @XmlElement(name = "xml", namespace = "https://www.e-governeapps2.com.br/") final CuritibaXmlRequest xmlRequest;

    public static class Builder {

        private String method;

        private CuritibaXmlRequest xmlRequest;

        /**
         *
         * @param method
         * @return
         */
        public Builder withMethod(final String method) {
            this.method = method;
            return this;
        }

        /**
         *
         * @param xmlRequest
         * @return
         */
        public Builder withXmlRequest(final CuritibaXmlRequest xmlRequest) {
            this.xmlRequest = xmlRequest;
            return this;
        }

        public CuritibaReceiptXml build() {
            final CuritibaReceiptXml entity = new CuritibaReceiptXml(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CuritibaReceiptXml() {
        method = null;
        xmlRequest = null;
    }

    public CuritibaReceiptXml(final Builder builder) {
        method = builder.method;
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

    public String getMethod() {
        return method;
    }

    public CuritibaXmlRequest getXmlRequest() {
        return xmlRequest;
    }

}


package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.transmission.ObjectFactory;


/**
 * Método retornado após consumo do WS de situação da NF-e
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.NFE_CONS_NFE_RESULT, namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta3")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeStatusSearchResponseMethod extends Receivable implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns;

    private @XmlElement(name = ObjectFactory.RET_CONS_SIT_NFE) @NotNull final NFeStatusSearchResponse nfeStatusSearchResponse;

    private @XmlTransient QName qName = new QName(ObjectFactory.NFE_CONS_NFE_RESULT);

    public static class Builder {

        private String xmlns;

        private NFeStatusSearchResponse nfeStatusSearchResponse;

        /**
         * 
         * @param xmlns
         * @return
         */
        public Builder withXmlns(String xmlns) {
            this.xmlns = xmlns;
            return this;
        }

        /**
         * 
         * @param nfeStatusSearchResponse
         * @return
         */
        public Builder withNFeStatusSearchResponse(NFeStatusSearchResponse nfeStatusSearchResponse) {
            this.nfeStatusSearchResponse = nfeStatusSearchResponse;
            return this;
        }

        public NFeStatusSearchResponseMethod build() {
            final NFeStatusSearchResponseMethod entity = new NFeStatusSearchResponseMethod(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeStatusSearchResponseMethod() {
        this.xmlns = null;
        this.nfeStatusSearchResponse = null;
    }

    public NFeStatusSearchResponseMethod(Builder builder) {
        this.xmlns = builder.xmlns;
        this.nfeStatusSearchResponse = builder.nfeStatusSearchResponse;
    }

    @Override
    public void setQName(QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

    public NFeStatusSearchResponse getNFeStatusSearchResponse() {
        return this.nfeStatusSearchResponse;
    }
}

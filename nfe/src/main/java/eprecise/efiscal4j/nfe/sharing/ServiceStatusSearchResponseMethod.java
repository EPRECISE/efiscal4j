
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.transmission.ObjectFactory;


/**
 * Método retornado após consumo do WS de status do serviço
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.NFE_STAT_SERV_RESULT, namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico3")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceStatusSearchResponseMethod extends Receivable implements Serializable {

    private static final long serialVersionUID = 1L;

    // private @XmlAttribute(name = "xmlns") @NotNull final String xmlns;

    private @XmlElement(name = ObjectFactory.RET_CONS_STAT_SERV) @NotNull final ServiceStatusSearchResponse serviceStatusSearchResponse;

    private @XmlTransient QName qName = new QName(ObjectFactory.NFE_STAT_SERV_RESULT);

    public static class Builder {

        private String xmlns;

        private ServiceStatusSearchResponse serviceStatusSearchResponse;

        /**
         * 
         * @param serviceStatusSearchResponse
         * @return
         */
        public Builder withXmlns(String xmlns) {
            this.xmlns = xmlns;
            return this;
        }

        /**
         * 
         * @param serviceStatusSearchResponse
         * @return
         */
        public Builder withServiceStatusSearchResponse(ServiceStatusSearchResponse serviceStatusSearchResponse) {
            this.serviceStatusSearchResponse = serviceStatusSearchResponse;
            return this;
        }

        public ServiceStatusSearchResponseMethod build() {
            final ServiceStatusSearchResponseMethod entity = new ServiceStatusSearchResponseMethod(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ServiceStatusSearchResponseMethod() {
        // this.xmlns = null;
        this.serviceStatusSearchResponse = null;
    }

    public ServiceStatusSearchResponseMethod(Builder builder) {
        // this.xmlns = builder.xmlns;
        this.serviceStatusSearchResponse = builder.serviceStatusSearchResponse;
    }

    @Override
    public void setQName(QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

}

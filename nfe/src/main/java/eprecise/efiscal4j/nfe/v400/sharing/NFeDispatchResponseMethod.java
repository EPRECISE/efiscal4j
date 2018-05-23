
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v400.transmission.Receivable;


/**
 * Método retornado após consumo do WS de autorização
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.NFE_RESULT_MSG, namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NFeAutorizacao4")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDispatchResponseMethod extends Receivable implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private @XmlElement(name = ObjectFactory.RET_ENVI_NFE) @NotNull final NFeDispatchResponse nFeDispatchResponse;

    public static class Builder {

        private NFeDispatchResponse nFeDispatchResponse;

        /**
         * 
         * @param nFeDispatchResponse
         * @return
         */
        public Builder withNfeDispatchResponse(NFeDispatchResponse nFeDispatchResponse) {
            this.nFeDispatchResponse = nFeDispatchResponse;
            return this;
        }

        public NFeDispatchResponseMethod build() {
            final NFeDispatchResponseMethod entity = new NFeDispatchResponseMethod(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeDispatchResponseMethod() {
        this.nFeDispatchResponse = null;
    }

    public NFeDispatchResponseMethod(Builder builder) {
        this.nFeDispatchResponse = builder.nFeDispatchResponse;
    }

    public NFeDispatchResponse getnFeDispatchResponse() {
        return this.nFeDispatchResponse;
    }

}

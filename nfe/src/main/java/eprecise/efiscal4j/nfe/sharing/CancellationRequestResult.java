
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeVersion;


/**
 * Tipo retorno Pedido de Cancelamento da Nota Fiscal Eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CancellationRequestResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final String version = NFeVersion.NFE_VERSION;

    private @XmlElement(name = "infCanc") @NotNull @Valid final CancellationRequestResultInfo cancellationRequestResultInfo;

    public static class Builder {

        private CancellationRequestResultInfo cancellationRequestResultInfo;

        /**
         * @see CancellationRequestResultInfo
         * @param cancellationRequestResultInfo
         * @return
         */
        public Builder withCancellationRequestResultInfo(CancellationRequestResultInfo cancellationRequestResultInfo) {
            this.cancellationRequestResultInfo = cancellationRequestResultInfo;
            return this;
        }

        public CancellationRequestResult build() {
            final CancellationRequestResult entity = new CancellationRequestResult(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CancellationRequestResult() {
        this.cancellationRequestResultInfo = null;
    }

    public CancellationRequestResult(Builder builder) {
        this.cancellationRequestResultInfo = builder.cancellationRequestResultInfo;
    }
}

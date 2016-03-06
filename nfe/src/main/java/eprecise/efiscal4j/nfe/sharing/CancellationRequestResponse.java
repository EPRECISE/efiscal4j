
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Tipo retorno Pedido de Cancelamento da Nota Fiscal Eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CancellationRequestResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlElement(name = "infCanc") @NotNull @Valid final CancellationRequestResponseInfo cancellationRequestResponseInfo;

    public static class Builder {

        private CancellationRequestResponseInfo cancellationRequestResponseInfo;

        /**
         * @see CancellationRequestResponseInfo
         * @param cancellationRequestResponseInfo
         * @return
         */
        public Builder withCancellationRequestResponseInfo(CancellationRequestResponseInfo cancellationRequestResponseInfo) {
            this.cancellationRequestResponseInfo = cancellationRequestResponseInfo;
            return this;
        }

        public CancellationRequestResponse build() {
            final CancellationRequestResponse entity = new CancellationRequestResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CancellationRequestResponse() {
        this.cancellationRequestResponseInfo = null;
    }

    public CancellationRequestResponse(Builder builder) {
        this.cancellationRequestResponseInfo = builder.cancellationRequestResponseInfo;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public CancellationRequestResponseInfo getCancellationRequestResponseInfo() {
        return this.cancellationRequestResponseInfo;
    }
}

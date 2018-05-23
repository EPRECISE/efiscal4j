
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Pedido de inutilização de númeração de NF-e processado
 *
 * @author Fernando C Glizt
 *
 */
@XmlRootElement(name = "ProcInutNFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessedNFeNumberDisable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/v400/xsd/procInutNFe_v4.00.xsd";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_4_00;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "inutNFe") @NotNull @Valid final NFeNumberDisableDispatch nfeNumberDisable;

    private @XmlElement(name = "retInutNFe") @NotNull @Valid final NFeNumberDisableResponse nfeNumberDisableResponse;

    public static class Builder {

        private NFeNumberDisableDispatch nfeNumberDisable;

        private NFeNumberDisableResponse nfeNumberDisableResponse;

        /**
         *
         * @param nfeNumberDisable
         * @return
         */
        public Builder withNFeNumberDisable(final NFeNumberDisableDispatch nfeNumberDisable) {
            this.nfeNumberDisable = nfeNumberDisable;
            return this;
        }

        /**
         * @see ProcessingStatusProtocol
         * @param nfeNumberDisableResponse
         * @return
         */
        public Builder withNFeNumberDisableResponse(final NFeNumberDisableResponse nfeNumberDisableResponse) {
            this.nfeNumberDisableResponse = nfeNumberDisableResponse;
            return this;
        }

        public ProcessedNFeNumberDisable build() {
            final ProcessedNFeNumberDisable entity = new ProcessedNFeNumberDisable(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ProcessedNFeNumberDisable() {
        this.nfeNumberDisable = null;
        this.nfeNumberDisableResponse = null;
    }

    public ProcessedNFeNumberDisable(final Builder builder) {
        this.nfeNumberDisable = builder.nfeNumberDisable;
        this.nfeNumberDisableResponse = builder.nfeNumberDisableResponse;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public NFeNumberDisableDispatch getNfeNumberDisable() {
        return this.nfeNumberDisable;
    }

    public NFeNumberDisableResponse getNfeNumberDisableResponse() {
        return this.nfeNumberDisableResponse;
    }
}

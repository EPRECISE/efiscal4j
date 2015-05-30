
package eprecise.efiscal4j.nfe.sharing;

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
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.types.NFeAccessKey;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Tipo Pedido de Consulta da Situação Atual da Nota Fiscal Eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "consSitNFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeStatusSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    public static String XSD = "/eprecise/efiscal4j/nfe/consSitNFe_v3.10.xsd";

    private @XmlAttribute(name = "versao") @NotNull final String version = FiscalDocumentVersion.NFE_VERSION;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "xServ") @NotNull @NFeString final String requestedService = "CONSULTAR";

    private @XmlElement(name = "chNFe") @NotNull @NFeAccessKey final String acessKey;

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private String acessKey;

        /**
         * @see TransmissionEnvironment
         * @param transmissionEnvironment
         * @return
         */
        public Builder withTransmissionEnvironment(TransmissionEnvironment transmissionEnvironment) {
            this.transmissionEnvironment = transmissionEnvironment;
            return this;
        }

        /**
         * @see NFeAccessKey
         * @param acessKey
         * @return
         */
        public Builder withAcessKey(String acessKey) {
            this.acessKey = acessKey;
            return this;
        }

        public NFeStatusSearch build() {
            final NFeStatusSearch entity = new NFeStatusSearch(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeStatusSearch() {
        this.transmissionEnvironment = null;
        this.acessKey = null;
    }

    public NFeStatusSearch(Builder builder) {
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.acessKey = builder.acessKey;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public String getRequestedService() {
        return this.requestedService;
    }

    public String getAcessKey() {
        return this.acessKey;
    }
}

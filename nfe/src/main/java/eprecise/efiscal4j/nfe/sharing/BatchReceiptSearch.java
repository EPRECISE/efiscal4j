
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.types.NFeVersion;


/**
 * Tipo Pedido de Consulta do Recibo do Lote de Notas Fiscais Eletrônicas
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "consReciNFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class BatchReceiptSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final String version = NFeVersion.NFE_VERSION;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "nRec") @NotNull @Size(max = 15) @Pattern(regexp = "[0-9]{15}") final String receiptNumber;

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private String receiptNumber;

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
         * Número do Recibo
         * 
         * @param receiptNumber
         * @return
         */
        public Builder withReceiptNumber(String receiptNumber) {
            this.receiptNumber = receiptNumber;
            return this;
        }

        public BatchReceiptSearch build() {
            final BatchReceiptSearch entity = new BatchReceiptSearch(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public BatchReceiptSearch() {
        this.transmissionEnvironment = null;
        this.receiptNumber = null;
    }

    public BatchReceiptSearch(Builder builder) {
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.receiptNumber = builder.receiptNumber;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public String getReceiptNumber() {
        return this.receiptNumber;
    }
}


package eprecise.efiscal4j.nfe.v310.sharing;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;


/**
 * Tipo Pedido de Consulta do Recibo do Lote de Notas Fiscais Eletrônicas
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.CONS_RECI_NFE)
@XmlAccessorType(XmlAccessType.FIELD)
public class BatchReceiptSearch implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/v310/xsd/consReciNFe_v3.10.xsd";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "nRec") @NotNull @Size(max = 15) @Pattern(regexp = "[0-9]{15}") final String receiptNumber;

    private @XmlTransient QName qName = new QName(ObjectFactory.CONS_RECI_NFE);

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private String receiptNumber;

        /**
         * @see TransmissionEnvironment
         * @param transmissionEnvironment
         * @return
         */
        public Builder withTransmissionEnvironment(final TransmissionEnvironment transmissionEnvironment) {
            this.transmissionEnvironment = transmissionEnvironment;
            return this;
        }

        /**
         * Número do Recibo
         * 
         * @param receiptNumber
         * @return
         */
        public Builder withReceiptNumber(final String receiptNumber) {
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
        transmissionEnvironment = null;
        receiptNumber = null;
    }

    public BatchReceiptSearch(final Builder builder) {
        transmissionEnvironment = builder.transmissionEnvironment;
        receiptNumber = builder.receiptNumber;
    }

    public FiscalDocumentVersion getVersion() {
        return version;
    }

    public String getXmlns() {
        return xmlns;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return transmissionEnvironment;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return qName;
    }
}

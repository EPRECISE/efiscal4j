
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocument.ReceiptedAsync;
import eprecise.efiscal4j.nfe.v400.sharing.adapters.processedFiscalDocument.ReceiptedAsyncFiscalDocumentAdapter;
import eprecise.efiscal4j.nfe.version.ReceiptedAsyncNFeVersion;


/**
 * Dados do Recibo do Lote
 *
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BatchReceipt implements ReceiptedAsyncNFeVersion, Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nRec") @NotNull @Size(max = 15) @Pattern(regexp = "[0-9]{15}") final String receiptNumber;

    private @XmlElement(name = "tMed") @NotNull @Pattern(regexp = "[0-9]{1,4}") final String averageTime;

    public static class Builder {

        private String receiptNumber;

        private String averageTime;

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

        /**
         * Tempo médio de resposta do serviço (em segundos) dos últimos 5 minutos
         *
         * @param averageTime
         * @return
         */
        public Builder withAverageTime(final String averageTime) {
            this.averageTime = averageTime;
            return this;
        }

        public BatchReceipt build() {
            final BatchReceipt entity = new BatchReceipt(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public BatchReceipt() {
        this.receiptNumber = null;
        this.averageTime = null;
    }

    public BatchReceipt(final NFeDispatchResponseMethod dispatchResponse) {
        this.receiptNumber = dispatchResponse.getnFeDispatchResponse().getBatchReceipt().getReceiptNumber();
        this.averageTime = dispatchResponse.getnFeDispatchResponse().getBatchReceipt().getAverageTime();
    }

    public BatchReceipt(final Builder builder) {
        this.receiptNumber = builder.receiptNumber;
        this.averageTime = builder.averageTime;
    }

    public String getReceiptNumber() {
        return this.receiptNumber;
    }

    public String getAverageTime() {
        return this.averageTime;
    }

    @Override
    public ReceiptedAsync buildReceiptedAsyncNFe(final FiscalDocument fiscalDocument) {
        return new ReceiptedAsyncFiscalDocumentAdapter(this, fiscalDocument).buildReceiptedAsyncNFe();
    }

}

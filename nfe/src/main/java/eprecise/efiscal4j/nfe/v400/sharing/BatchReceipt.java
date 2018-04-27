
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Dados do Recibo do Lote
 * 
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BatchReceipt implements Serializable {

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
        public Builder withReceiptNumber(String receiptNumber) {
            this.receiptNumber = receiptNumber;
            return this;
        }

        /**
         * Tempo médio de resposta do serviço (em segundos) dos últimos 5 minutos
         * 
         * @param averageTime
         * @return
         */
        public Builder withAverageTime(String averageTime) {
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

    public BatchReceipt(Builder builder) {
        this.receiptNumber = builder.receiptNumber;
        this.averageTime = builder.averageTime;
    }

    public String getReceiptNumber() {
        return this.receiptNumber;
    }

    public String getAverageTime() {
        return this.averageTime;
    }

}

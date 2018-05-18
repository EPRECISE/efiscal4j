/**
 * 
 */

package eprecise.efiscal4j.nfe.v400.payment;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Dados de Pagamento. Obrigatório apenas para (NFC-e) NT 2012/004
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFePayment implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "detPag") @NotNull @Size(min = 1, max = 100) final List<NFePaymentDetail> paymentDetails;

    private @XmlElement(name = "vTroco") @NFeDecimal1302 final String changeValue;

    public static class Builder {

        private List<NFePaymentDetail> paymentDetails;

        private String changeValue;

        /**
         * @see NFePaymentDetail
         * @param paymentDetails
         * @return
         */
        public Builder withNFePaymentDetails(List<NFePaymentDetail> paymentDetails) {
            this.paymentDetails = paymentDetails;
            return this;
        }

        /**
         * Valor do troco
         * 
         * @param value
         * @return
         */
        public Builder withChangeValue(String changeValue) {
            this.changeValue = changeValue;
            return this;
        }

        public NFePayment build() {
            final NFePayment entity = new NFePayment(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFePayment() {
        this.paymentDetails = null;
        this.changeValue = null;

    }

    public NFePayment(Builder builder) {
        this.paymentDetails = builder.paymentDetails;
        this.changeValue = builder.changeValue;
    }

    public List<NFePaymentDetail> getPaymentDetails() {
        return paymentDetails;
    }

    public String getChangeValue() {
        return changeValue;
    }

}

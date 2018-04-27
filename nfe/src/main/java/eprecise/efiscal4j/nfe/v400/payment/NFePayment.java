/**
 * 
 */

package eprecise.efiscal4j.nfe.v400.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
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

    private @XmlElement(name = "tPag") @NotNull final PaymentMethod paymentMethod;

    private @XmlElement(name = "vPag") @NotNull @NFeDecimal1302 final String paymentValue;

    private @XmlElement(name = "card") CardSet cardSet;

    public static class Builder {

        private PaymentMethod paymentMethod;

        private String paymentValue;

        private CardSet cardSet;

        /**
         * @see PaymentMethod
         * @param paymentMethod
         * @return
         */
        public Builder withPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        /**
         * Valor do Pagamento
         * 
         * @param value
         * @return
         */
        public Builder withPaymentValue(String paymentValue) {
            this.paymentValue = paymentValue;
            return this;
        }

        /**
         * @see CardSet
         * @param cardSet
         * @return
         */
        public Builder withCardSet(CardSet cardSet) {
            this.cardSet = cardSet;
            return this;
        }

        public NFePayment build() {
            final NFePayment entity = new NFePayment(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFePayment() {
        this.paymentMethod = null;
        this.paymentValue = null;

    }

    public NFePayment(Builder builder) {
        this.paymentMethod = builder.paymentMethod;
        this.paymentValue = builder.paymentValue;
        this.cardSet = builder.cardSet;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public String getPaymentValue() {
        return this.paymentValue;
    }

    public CardSet getCardSet() {
        return this.cardSet;
    }

}

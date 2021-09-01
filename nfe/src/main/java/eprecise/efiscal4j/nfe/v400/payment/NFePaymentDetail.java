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
public class NFePaymentDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "tPag") @NotNull final PaymentMethod paymentMethod;

    private @XmlElement(name = "xPag") final String paymentMethodDescription;

    private @XmlElement(name = "vPag") @NotNull @NFeDecimal1302 final String paymentValue;

    private @XmlElement(name = "card") CardSet cardSet;

    public static class Builder {

        private PaymentMethod paymentMethod;

        private String paymentMethodDescription;

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
         * Descrição do Meio de Pagamento
         *
         * @param value
         * @return
         */
        public Builder withPaymentMethodDescription(String paymentMethodDescription) {
            this.paymentMethodDescription = paymentMethodDescription;
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

        public NFePaymentDetail build() {
            final NFePaymentDetail entity = new NFePaymentDetail(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFePaymentDetail() {
        this.paymentMethod = null;
        this.paymentMethodDescription = null;
        this.paymentValue = null;
    }

    public NFePaymentDetail(Builder builder) {
        this.paymentMethod = builder.paymentMethod;
        this.paymentMethodDescription = builder.paymentMethodDescription;
        this.paymentValue = builder.paymentValue;
        this.cardSet = builder.cardSet;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public String getPaymentMethodDescription() {
        return this.paymentMethodDescription;
    }

    public String getPaymentValue() {
        return this.paymentValue;
    }

    public CardSet getCardSet() {
        return this.cardSet;
    }

}

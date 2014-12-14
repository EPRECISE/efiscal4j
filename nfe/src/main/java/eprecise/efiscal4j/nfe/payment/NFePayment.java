/**
 * 
 */

package eprecise.efiscal4j.nfe.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


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

	private @XmlElement(name = "vPag") @NFeDecimal1302 @NotNull final String paymentValue;

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
			NFePayment entity = new NFePayment(this);
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

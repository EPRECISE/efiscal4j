
package eprecise.efiscal4j.nfe.tax;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal0302Max100;
import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Imposto Devolvido
 * 
 * @author Felipe Bueno
 * 
 */
public class ReturnedTax implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "pDevol") @NotNull @NFeDecimal0302Max100 final String returnedProductPerc;

	private @XmlElement(name = "IPI") @NotNull final ReturnedIPI returnedIPI;

	public static class Builder {

		private String returnedProductPerc;

		private ReturnedIPI returnedIPI;

		/**
		 * Percentual de mercadoria devolvida
		 * 
		 * @param returnedProductPerc
		 * @return
		 */
		public Builder withReturnedProductPerc(String returnedProductPerc) {
			this.returnedProductPerc = returnedProductPerc;
			return this;
		}

		/**
		 * 
		 * @see ReturnedIPI
		 * @param returnedIPI
		 * @return
		 */
		public Builder withReturnedIPI(ReturnedIPI returnedIPI) {
			this.returnedIPI = returnedIPI;
			return this;
		}

		public ReturnedTax build() {
			ReturnedTax entity = new ReturnedTax(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}

	}

	public ReturnedTax() {
		this.returnedProductPerc = null;
		this.returnedIPI = null;

	}

	public ReturnedTax(Builder builder) {
		this.returnedProductPerc = builder.returnedProductPerc;
		this.returnedIPI = builder.returnedIPI;
	}

	public String getReturnedProductPerc() {
		return this.returnedProductPerc;
	}

	public ReturnedIPI getReturnedIPI() {
		return this.returnedIPI;
	}

}

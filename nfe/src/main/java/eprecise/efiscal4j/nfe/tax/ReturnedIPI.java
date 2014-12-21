
package eprecise.efiscal4j.nfe.tax;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Informação de IPI devolvido
 * 
 * @author Felipe Bueno
 * 
 */
public class ReturnedIPI implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "vIPIDevol") @NotNull @NFeDecimal1302 final String returnedIPIValue;

	public static class Builder {

		private String returnedIPIValue;

		/**
		 * Valor do IPI devolvido
		 * 
		 * @param returnedIPIValue
		 * @return
		 */
		public Builder withReturnedIPIValue(String returnedIPIValue) {
			this.returnedIPIValue = returnedIPIValue;
			return this;
		}

		public ReturnedIPI build() {
			ReturnedIPI entity = new ReturnedIPI(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}

	}

	public ReturnedIPI() {
		this.returnedIPIValue = null;

	}

	public ReturnedIPI(Builder builder) {
		this.returnedIPIValue = builder.returnedIPIValue;
	}

	public String getReturnedIPIValue() {
		return this.returnedIPIValue;
	}

}

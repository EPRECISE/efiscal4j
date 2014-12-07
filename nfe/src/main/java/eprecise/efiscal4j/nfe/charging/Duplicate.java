
package eprecise.efiscal4j.nfe.charging;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDate;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302Optional;
import eprecise.efiscal4j.nfe.types.NFeString;
import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Dados das duplicatas NT 2011/004
 * 
 * @author Felipe Bueno
 * 
 */
public class Duplicate implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "nDup") @Size(min = 1, max = 60) @NFeString String number;

	private @XmlElement(name = "dVenc") @Size(min = 1, max = 60) @NFeDate String dueDate;

	private @XmlElement(name = "vDup") @NotNull @NFeDecimal1302Optional final String value;

	public static class Builder {

		private String number;

		private String dueDate;

		private String value;

		/**
		 * Número da duplicata
		 * 
		 * @param number
		 * @return
		 */
		public Builder withNumber(String number) {
			this.number = number;
			return this;
		}

		/**
		 * Data de vencimento da duplicata (AAAA-MM-DD)
		 * 
		 * @param dueDate
		 * @return
		 */
		public Builder withDueDate(String dueDate) {
			this.dueDate = dueDate;
			return this;
		}

		/**
		 * Valor da duplicata
		 * 
		 * @param value
		 * @return
		 */
		public Builder withValue(String value) {
			this.value = value;
			return this;
		}

		public Duplicate build() {
			Duplicate entity = new Duplicate(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public Duplicate() {
		this.value = null;
	}

	public Duplicate(Builder builder) {
		this.number = builder.number;
		this.dueDate = builder.dueDate;
		this.value = builder.value;
	}

	public String getNumber() {
		return this.number;
	}

	public String getDueDate() {
		return this.dueDate;
	}

	public String getValue() {
		return this.value;
	}

}

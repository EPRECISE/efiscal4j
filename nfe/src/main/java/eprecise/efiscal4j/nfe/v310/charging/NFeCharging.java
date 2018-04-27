
package eprecise.efiscal4j.nfe.v310.charging;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Dados da cobrança da NF-e
 * 
 * @author Felipe Bueno
 * 
 */
public class NFeCharging implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "fat") Invoice invoice;

	private @XmlElement(name = "dup") @Size(max = 120) List<Duplicate> duplicates;

	public static class Builder {

		private Invoice invoice;

		private List<Duplicate> duplicates;

		/**
		 * @see Invoice
		 * 
		 * @param invoice
		 * @return
		 */
		public Builder withInvoice(Invoice invoice) {
			this.invoice = invoice;
			return this;
		}

		/**
		 * List of Duplicate
		 * 
		 * @param duplicates
		 * @return
		 */
		public Builder withDuplicates(List<Duplicate> duplicates) {
			this.duplicates = duplicates;
			return this;
		}

		public NFeCharging build() {
			NFeCharging entity = new NFeCharging(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public NFeCharging() {
	}

	public NFeCharging(Builder builder) {
		this.invoice = builder.invoice;
		this.duplicates = builder.duplicates;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public List<Duplicate> getDuplicates() {
		return this.duplicates;
	}

}

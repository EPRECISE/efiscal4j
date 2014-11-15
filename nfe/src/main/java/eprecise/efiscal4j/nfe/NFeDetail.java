
package eprecise.efiscal4j.nfe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.tax.Tax;
import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Dados dos detalhes da NF-e
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDetail {

	private @XmlAttribute(name = "nItem") @NotNull @Pattern(regexp = "[1-9]{1}[0-9]{0,1}|[1-8]{1}[0-9]{2}|[9]{1}[0-8]{1}[0-9]{1}|[9]{1}[9]{1}[0]{1}") final String itemOrder;

	private @XmlElement(name = "prod") @NotNull final NFeItem nFeItem;

	private @XmlElement(name = "imposto") @NotNull final Tax tax;

	public static class Builder {

		private String itemOrder;

		private NFeItem nFeItem;

		private Tax tax;

		/**
		 * Número do item da NF-e
		 */
		public Builder withItemOrder(String itemOrder) {
			this.itemOrder = itemOrder;
			return this;
		}

		public Builder withNFeItem(NFeItem nFeItem) {
			this.nFeItem = nFeItem;
			return this;
		}

		public Builder withTax(Tax tax) {
			this.tax = tax;
			return this;
		}

		public NFeDetail build() {
			NFeDetail entity = new NFeDetail(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public NFeDetail() {
		this.itemOrder = null;
		this.nFeItem = null;
		this.tax = null;
	}

	public NFeDetail(Builder builder) {
		this.itemOrder = builder.itemOrder;
		this.nFeItem = builder.nFeItem;
		this.tax = builder.tax;
	}

}


package eprecise.efiscal4j.nfe.total;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.FederalTaxRetention;


/**
 * Dados dos totais da NF-e
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class NFeTotal {

	private @XmlElement(name = "ICMSTot") @NotNull final ICMSTotal icmsTotal;

	private @XmlElement(name = "ISSQNTot") ISSQNTotal issqnTotal;

	private @XmlElement(name = "retTrib") FederalTaxRetention federalTaxRetention;

	public static class Builder {

		private ICMSTotal icmsTotal;

		private ISSQNTotal issqnTotal;

		private FederalTaxRetention federalTaxRetention;

		public Builder withICMSTotal(ICMSTotal icmsTotal) {
			this.icmsTotal = icmsTotal;
			return this;
		}

		public Builder withISSQNTotal(ISSQNTotal issqnTotal) {
			this.issqnTotal = issqnTotal;
			return this;
		}

		public Builder withFederalTaxRetention(FederalTaxRetention federalTaxRetention) {
			this.federalTaxRetention = federalTaxRetention;
			return this;
		}

		public NFeTotal build() {
			NFeTotal entity = new NFeTotal(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public NFeTotal() {
		this.icmsTotal = null;
	}

	public NFeTotal(Builder builder) {
		this.icmsTotal = builder.icmsTotal;
		this.issqnTotal = builder.issqnTotal;
		this.federalTaxRetention = builder.federalTaxRetention;
	}
}

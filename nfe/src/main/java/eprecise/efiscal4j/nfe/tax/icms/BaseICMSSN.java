
package eprecise.efiscal4j.nfe.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;


abstract class BaseICMSSN extends ICMS {

	private static final long serialVersionUID = 1L;

	static abstract class Builder {

		abstract BaseICMSSN build();
	}

	private @XmlElement(name = "CSOSN") @NotNull final String csosn;

	protected BaseICMSSN(ProductOrigin origin, String csosn) {
		super(origin);
		this.csosn = csosn;
	}

	public String getCSOSN() {
		return this.csosn;
	}

}

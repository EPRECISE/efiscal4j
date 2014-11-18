
package eprecise.efiscal4j.nfe.tax;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import eprecise.efiscal4j.nfe.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Tributos incidentes nos produtos ou serviços da NF-e
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Tax {

    // @formatter:off
	private @XmlElements(value = { 
			   @XmlElement(name = "ICMS", type = ICMS.class), 
			   @XmlElement(name = "IPI", type = IPI.class), 
			   @XmlElement(name = "II", type = II.class),
			   @XmlElement(name = "ISSQN", type = ISSQN.class) }) @NotNull @Valid final MainTax mainTax;
	// @formatter:on

    public static class Builder {

        private MainTax mainTax;

        public Builder withMainTax(MainTax mainTax) {
            this.mainTax = mainTax;
            return this;
        }

        public Tax build() {
            Tax entity = new Tax(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Tax() {
        this.mainTax = null;
    }

    public Tax(Builder builder) {
        this.mainTax = builder.mainTax;
    }

}

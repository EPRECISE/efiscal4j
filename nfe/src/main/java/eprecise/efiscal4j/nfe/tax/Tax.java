
package eprecise.efiscal4j.nfe.tax;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.nfe.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.tax.icms.ICMSAdapter;
import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Tributos incidentes nos produtos ou serviços da NF-e
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Tax {

    private @XmlElement(name = "ICMS") @XmlJavaTypeAdapter(ICMSAdapter.class) @Valid final ICMS icms;

    private @XmlElement(name = "IPI") @Valid final IPI ipi;

    private @XmlElement(name = "II") @Valid final II ii;

    private @XmlElement(name = "ISSQN") @Valid final ISSQN issqn;

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
        this.icms = null;
        this.ipi = null;
        this.ii = null;
        this.issqn = null;
    }

    public Tax(Builder builder) {
        this.icms = (ICMS) (builder.mainTax instanceof ICMS ? builder.mainTax : null);
        this.ipi = (IPI) (builder.mainTax instanceof IPI ? builder.mainTax : null);
        this.ii = (II) (builder.mainTax instanceof II ? builder.mainTax : null);
        this.issqn = (ISSQN) (builder.mainTax instanceof ISSQN ? builder.mainTax : null);
    }
}

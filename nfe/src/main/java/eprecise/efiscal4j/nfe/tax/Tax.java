
package eprecise.efiscal4j.nfe.tax;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.tax.cofins.COFINS;
import eprecise.efiscal4j.nfe.tax.cofins.COFINSAdapter;
import eprecise.efiscal4j.nfe.tax.cofins.COFINSST;
import eprecise.efiscal4j.nfe.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.tax.icms.ICMSAdapter;
import eprecise.efiscal4j.nfe.tax.ipi.IPI;
import eprecise.efiscal4j.nfe.tax.ipi.IPIAdapter;
import eprecise.efiscal4j.nfe.tax.pis.PIS;
import eprecise.efiscal4j.nfe.tax.pis.PISAdapter;
import eprecise.efiscal4j.nfe.tax.pis.PISST;


/**
 * Tributos incidentes nos produtos ou serviços da NF-e
 * 
 * @author Felipe Bueno
 * 
 */
// TODO Finalizar imposto ISSQN
@XmlAccessorType(XmlAccessType.FIELD)
public class Tax {

    private @XmlElement(name = "ICMS") @XmlJavaTypeAdapter(ICMSAdapter.class) @Valid final ICMS icms;

    // private @XmlElement(name = "ISSQN") @Valid final ISSQN issqn;

    private @XmlElement(name = "IPI") @XmlJavaTypeAdapter(IPIAdapter.class) @Valid final IPI ipi;

    private @XmlElement(name = "II") @Valid final II ii;

    private @XmlElement(name = "PIS") @XmlJavaTypeAdapter(PISAdapter.class) @Valid final PIS pis;

    private @XmlElement(name = "PISST") @Valid final PISST pisSt;

    private @XmlElement(name = "COFINS") @XmlJavaTypeAdapter(COFINSAdapter.class) @Valid final COFINS cofins;

    private @XmlElement(name = "COFINSST") @Valid final COFINSST cofinsSt;

    public static class Builder {

        private ICMS icms;

        // private ISSQN issqn;

        private IPI ipi;

        private II ii;

        private PIS pis;

        private PISST pisSt;

        private COFINS cofins;

        private COFINSST cofinsSt;

        public Builder withIcms(ICMS icms) {
            this.icms = icms;
            return this;
        }

        // public Builder withIssqn(ISSQN issqn) {
        // this.issqn = issqn;
        // return this;
        // }

        public Builder withIpi(IPI ipi) {
            this.ipi = ipi;
            return this;
        }

        public Builder withIi(II ii) {
            this.ii = ii;
            return this;
        }

        public Builder withPis(PIS pis) {
            this.pis = pis;
            return this;
        }

        public Builder withPisSt(PISST pisSt) {
            this.pisSt = pisSt;
            return this;
        }

        public Builder withCofins(COFINS cofins) {
            this.cofins = cofins;
            return this;
        }

        public Builder withCofinsSt(COFINSST cofinsSt) {
            this.cofinsSt = cofinsSt;
            return this;
        }

        public Tax build() {
            final Tax entity = new Tax(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Tax() {
        this.icms = null;
        // this.issqn = null;
        this.ipi = null;
        this.ii = null;
        this.pis = null;
        this.pisSt = null;
        this.cofins = null;
        this.cofinsSt = null;
    }

    protected Tax(Builder builder) {
        this.icms = builder.icms;
        // this.issqn = builder.issqn;
        this.ipi = builder.ipi;
        this.ii = builder.ii;
        this.pis = builder.pis;
        this.pisSt = builder.pisSt;
        this.cofins = builder.cofins;
        this.cofinsSt = builder.cofinsSt;
    }

    public ICMS getIcms() {
        return this.icms;
    }

    public PIS getPis() {
        return this.pis;
    }

    public PISST getPisSt() {
        return this.pisSt;
    }

    public COFINS getCofins() {
        return this.cofins;
    }

    public COFINSST getCofinsSt() {
        return this.cofinsSt;
    }

    public IPI getIpi() {
        return this.ipi;
    }

    public II getIi() {
        return this.ii;
    }
}


package eprecise.efiscal4j.nfe.tax;

import java.io.Serializable;

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
import eprecise.efiscal4j.nfe.tax.ii.II;
import eprecise.efiscal4j.nfe.tax.ipi.IPI;
import eprecise.efiscal4j.nfe.tax.ipi.IPIAdapter;
import eprecise.efiscal4j.nfe.tax.pis.PIS;
import eprecise.efiscal4j.nfe.tax.pis.PISAdapter;
import eprecise.efiscal4j.nfe.tax.pis.PISST;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Tributos incidentes nos produtos ou serviços da NF-e
 *
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Tax implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "ICMS") @XmlJavaTypeAdapter(ICMSAdapter.class) @Valid final ICMS icms;

    // TODO Finalizar imposto ISSQN
    // private @XmlElement(name = "ISSQN") @Valid final ISSQN issqn;

    private @XmlElement(name = "IPI") @XmlJavaTypeAdapter(IPIAdapter.class) @Valid final IPI ipi;

    private @XmlElement(name = "II") @Valid final II ii;

    private @XmlElement(name = "PIS") @XmlJavaTypeAdapter(PISAdapter.class) @Valid final PIS pis;

    private @XmlElement(name = "PISST") @Valid final PISST pisSt;

    private @XmlElement(name = "COFINS") @XmlJavaTypeAdapter(COFINSAdapter.class) @Valid final COFINS cofins;

    private @XmlElement(name = "COFINSST") @Valid final COFINSST cofinsSt;

    private @XmlElement(name = "vTotTrib") @NFeDecimal1302 final String taxTotalValue;

    public static class Builder {

        private ICMS icms;

        // private ISSQN issqn;

        private IPI ipi;

        private II ii;

        private PIS pis;

        private PISST pisSt;

        private COFINS cofins;

        private COFINSST cofinsSt;

        private String taxTotalValue;

        public Builder withIcms(final ICMS icms) {
            this.icms = icms;
            return this;
        }

        // public Builder withIssqn(ISSQN issqn) {
        // this.issqn = issqn;
        // return this;
        // }

        public Builder withIpi(final IPI ipi) {
            this.ipi = ipi;
            return this;
        }

        public Builder withIi(final II ii) {
            this.ii = ii;
            return this;
        }

        public Builder withPis(final PIS pis) {
            this.pis = pis;
            return this;
        }

        public Builder withPisSt(final PISST pisSt) {
            this.pisSt = pisSt;
            return this;
        }

        public Builder withCofins(final COFINS cofins) {
            this.cofins = cofins;
            return this;
        }

        public Builder withCofinsSt(final COFINSST cofinsSt) {
            this.cofinsSt = cofinsSt;
            return this;
        }

        public Builder withTaxTotalValue(final String taxTotalValue) {
            this.taxTotalValue = taxTotalValue;
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
        this.taxTotalValue = null;
    }

    protected Tax(final Builder builder) {
        this.icms = builder.icms;
        // this.issqn = builder.issqn;
        this.ipi = builder.ipi;
        this.ii = builder.ii;
        this.pis = builder.pis;
        this.pisSt = builder.pisSt;
        this.cofins = builder.cofins;
        this.cofinsSt = builder.cofinsSt;
        this.taxTotalValue = builder.taxTotalValue;
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

    public String getTaxTotalValue() {
        return taxTotalValue;
    }
}

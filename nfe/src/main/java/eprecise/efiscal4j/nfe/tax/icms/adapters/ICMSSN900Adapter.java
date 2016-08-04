
package eprecise.efiscal4j.nfe.tax.icms.adapters;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.nfe.tax.icms.BCModality;
import eprecise.efiscal4j.nfe.tax.icms.BCModalityST;
import eprecise.efiscal4j.nfe.tax.icms.ICMSSN900;
import eprecise.efiscal4j.nfe.tax.icms.ProductOrigin;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


public class ICMSSN900Adapter extends XmlAdapter<ICMSSN900Adapter.AdaptedICMSSN900, ICMSSN900> {

    @Override
    public ICMSSN900 unmarshal(final AdaptedICMSSN900 adaptedICMSSN900) throws Exception {

      //@formatter:off
        return new ICMSSN900.Builder()
                    .withBcModality(adaptedICMSSN900.getBcModality())
                    .withBcModalityST(adaptedICMSSN900.getBcModalitySt())
                    .withBcReductionPercent(adaptedICMSSN900.getBcReductionPercent())
                    .withBcReductionStPercent(adaptedICMSSN900.getBcReductionStPercent())
                    .withBcValue(adaptedICMSSN900.getBcValue())
                    .withBcValueST(adaptedICMSSN900.getBcValueST())
                    .withCreditSnAliquot(adaptedICMSSN900.getCreditSnAliquot())
                    .withCreditSnIcmsValue(adaptedICMSSN900.getCreditSnIcmsValue())
                    .withIcmsAliquot(adaptedICMSSN900.getIcmsAliquot())
                    .withIcmsStAliquot(adaptedICMSSN900.getIcmsStAliquot())
                    .withIcmsStValue(adaptedICMSSN900.getIcmsStValue())
                    .withIcmsValue(adaptedICMSSN900.getIcmsValue())
                    .withOrigin(adaptedICMSSN900.getOrigin())
                    .withValueMarginAddedStPercent(adaptedICMSSN900.getValueMarginAddedStPercent()).build();
        //@formatter:on
    }

    @Override
    public AdaptedICMSSN900 marshal(final ICMSSN900 icmsSn900) throws Exception {
        if (icmsSn900 == null) {
            return null;
        }

        final AdaptedICMSSN900 adaptedICMSSN900 = new AdaptedICMSSN900(icmsSn900.getOrigin(), icmsSn900.getBcModality(), icmsSn900.getBcValue(), icmsSn900.getBcReductionPercent(),
                icmsSn900.getIcmsAliquot(), icmsSn900.getIcmsValue(), icmsSn900.getBcModalitySt(), icmsSn900.getValueMarginAddedStPercent(), icmsSn900.getBcReductionStPercent(),
                icmsSn900.getBcValueST(), icmsSn900.getIcmsStAliquot(), icmsSn900.getIcmsStValue(), icmsSn900.getCreditSnAliquot(), icmsSn900.getCreditSnIcmsValue());

        return adaptedICMSSN900;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    protected static class AdaptedICMSSN900 implements Serializable {

        private static final long serialVersionUID = 1L;

        private @XmlElement(name = "orig") @NotNull final ProductOrigin origin;

        private @XmlElement(name = "modBC") final BCModality bcModality;

        private @XmlElement(name = "vBC") @NFeDecimal1302 final String bcValue;

        private @XmlElement(name = "pRedBC") @NFeDecimal0302a04Optional final String bcReductionPercent;

        private @XmlElement(name = "pICMS") @NFeDecimal0302a04 final String icmsAliquot;

        private @XmlElement(name = "vICMS") @NFeDecimal1302 final String icmsValue;

        private @XmlElement(name = "modBCST") final BCModalityST bcModalitySt;

        private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Optional final String valueMarginAddedStPercent;

        private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Optional final String bcReductionStPercent;

        private @XmlElement(name = "vBCST") @NFeDecimal1302 final String bcValueST;

        private @XmlElement(name = "pICMSST") @NFeDecimal0302a04 final String icmsStAliquot;

        private @XmlElement(name = "vICMSST") @NFeDecimal1302 final String icmsStValue;

        private @XmlElement(name = "pCredSN") @NFeDecimal0302a04 final String creditSnAliquot;

        private @XmlElement(name = "vCredICMSSN") @NFeDecimal1302 final String creditSnIcmsValue;

        public AdaptedICMSSN900() {
            this.origin = null;
            this.bcModality = null;
            this.bcReductionPercent = null;
            this.bcValue = null;
            this.icmsAliquot = null;
            this.icmsValue = null;
            this.bcModalitySt = null;
            this.valueMarginAddedStPercent = null;
            this.bcReductionStPercent = null;
            this.bcValueST = null;
            this.icmsStAliquot = null;
            this.icmsStValue = null;
            this.creditSnAliquot = null;
            this.creditSnIcmsValue = null;
        }

        public AdaptedICMSSN900(final ProductOrigin origin, final BCModality bcModality, final String bcValue, final String bcReductionPercent, final String icmsAliquot, final String icmsValue,
                final BCModalityST bcModalitySt, final String valueMarginAddedStPercent, final String bcReductionStPercent, final String bcValueST, final String icmsStAliquot,
                final String icmsStValue, final String creditSnAliquot, final String creditSnIcmsValue) {
            super();
            this.origin = origin;
            this.bcModality = bcModality;
            this.bcValue = bcValue;
            this.bcReductionPercent = bcReductionPercent;
            this.icmsAliquot = icmsAliquot;
            this.icmsValue = icmsValue;
            this.bcModalitySt = bcModalitySt;
            this.valueMarginAddedStPercent = valueMarginAddedStPercent;
            this.bcReductionStPercent = bcReductionStPercent;
            this.bcValueST = bcValueST;
            this.icmsStAliquot = icmsStAliquot;
            this.icmsStValue = icmsStValue;
            this.creditSnAliquot = creditSnAliquot;
            this.creditSnIcmsValue = creditSnIcmsValue;
        }

        public ProductOrigin getOrigin() {
            return origin;
        }

        public BCModality getBcModality() {
            return bcModality;
        }

        public String getBcValue() {
            return bcValue;
        }

        public String getBcReductionPercent() {
            return bcReductionPercent;
        }

        public String getIcmsAliquot() {
            return icmsAliquot;
        }

        public String getIcmsValue() {
            return icmsValue;
        }

        public BCModalityST getBcModalitySt() {
            return bcModalitySt;
        }

        public String getValueMarginAddedStPercent() {
            return valueMarginAddedStPercent;
        }

        public String getBcReductionStPercent() {
            return bcReductionStPercent;
        }

        public String getBcValueST() {
            return bcValueST;
        }

        public String getIcmsStAliquot() {
            return icmsStAliquot;
        }

        public String getIcmsStValue() {
            return icmsStValue;
        }

        public String getCreditSnAliquot() {
            return creditSnAliquot;
        }

        public String getCreditSnIcmsValue() {
            return creditSnIcmsValue;
        }

    }

}

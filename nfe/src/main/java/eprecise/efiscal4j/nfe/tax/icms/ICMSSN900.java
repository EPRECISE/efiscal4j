
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302Optional;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL, CRT=1 – Simples Nacional e CSOSN=900 - Outros
 *
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN900 extends BaseICMSSN implements IcmsWithValue, IcmsWithST {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modBC") final BCModality bcModality;

    private @XmlElement(name = "vBC") @NFeDecimal1302Optional final String bcValue;

    private @XmlElement(name = "pRedBC") @NFeDecimal0302a04Optional final String bcReductionPercent;

    private @XmlElement(name = "pICMS") @NFeDecimal0302a04Optional final String icmsAliquot;

    private @XmlElement(name = "vICMS") @NFeDecimal1302Optional final String icmsValue;

    private @XmlElement(name = "modBCST") final BCModalityST bcModalitySt;

    private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Optional final String valueMarginAddedStPercent;

    private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Optional final String bcReductionStPercent;

    private @XmlElement(name = "vBCST") @NFeDecimal1302Optional final String bcValueST;

    private @XmlElement(name = "pICMSST") @NFeDecimal0302a04Optional final String icmsStAliquot;

    private @XmlElement(name = "vICMSST") @NFeDecimal1302Optional final String icmsStValue;

    private @XmlElement(name = "pCredSN") @NFeDecimal0302a04Optional final String creditSnAliquot;

    private @XmlElement(name = "vCredICMSSN") @NFeDecimal1302Optional final String creditSnIcmsValue;

    public static class Builder extends BaseICMSSN.Builder implements ICMSBuilder {

        private BCModality bcModality;

        private String bcReductionPercent;

        private String bcValue;

        private String icmsAliquot;

        private String icmsValue;

        private BCModalityST bcModalitySt;

        private String valueMarginAddedStPercent;

        private String bcReductionStPercent;

        private String bcValueST;

        private String icmsStAliquot;

        private String icmsStValue;

        private String creditSnAliquot;

        private String creditSnIcmsValue;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMSSN900.Builder) super.withOrigin(origin);
        }

        /**
         * @see BCModality
         */
        public Builder withBcModality(final BCModality bcModality) {
            this.bcModality = bcModality;
            return this;
        }

        /**
         * Percentual de redução da BC
         *
         * @param bcReductionPercent
         * @return
         */
        public Builder withBcReductionPercent(final String bcReductionPercent) {
            this.bcReductionPercent = bcReductionPercent;
            return this;
        }

        /**
         * Valor da BC do ICMS
         */
        public Builder withBcValue(final String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        /**
         * Alíquota do ICMS
         */
        public Builder withIcmsAliquot(final String icmsAliquot) {
            this.icmsAliquot = icmsAliquot;
            return this;
        }

        /**
         * Valor do ICMS
         */
        public Builder withIcmsValue(final String icmsValue) {
            this.icmsValue = icmsValue;
            return this;
        }

        /**
         * Percentual da Margem de Valor Adicionado ICMS ST
         */
        public Builder withValueMarginAddedStPercent(final String valueMarginAddedStPercent) {
            this.valueMarginAddedStPercent = valueMarginAddedStPercent;
            return this;
        }

        /**
         * Percentual de redução da BC ICMS ST
         */
        public Builder withBcReductionStPercent(final String bcReductionStPercent) {
            this.bcReductionStPercent = bcReductionStPercent;
            return this;
        }

        /**
         *
         * @see BCModalityST
         */
        public Builder withBcModalityST(final BCModalityST bcModalityST) {
            this.bcModalitySt = bcModalityST;
            return this;
        }

        /**
         * Valor da BC do ICMS ST
         */
        public Builder withBcValueST(final String bcValueST) {
            this.bcValueST = bcValueST;
            return this;
        }

        /**
         * Alíquota do ICMS ST
         */
        public Builder withIcmsStAliquot(final String icmsStAliquot) {
            this.icmsStAliquot = icmsStAliquot;
            return this;
        }

        /**
         * Valor do ICMS ST
         */
        public Builder withIcmsStValue(final String icmsStValue) {
            this.icmsStValue = icmsStValue;
            return this;
        }

        /**
         * Alíquota aplicável de cálculo do crédito (Simples Nacional). (v2.0)
         *
         * @param creditSnAliquot
         * @return
         */
        public Builder withCreditSnAliquot(final String creditSnAliquot) {
            this.creditSnAliquot = creditSnAliquot;
            return this;
        }

        /**
         * Valor crédito do ICMS que pode ser aproveitado nos termos do art. 23 da LC 123 (Simples Nacional) (v2.0)
         *
         * @param creditSnIcmsValue
         * @return
         */
        public Builder withCreditSnIcmsValue(final String creditSnIcmsValue) {
            this.creditSnIcmsValue = creditSnIcmsValue;
            return this;
        }

        @Override
        public ICMSSN900 build() {
            return new ICMSSN900(this);
        }

    }

    public ICMSSN900() {
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

    protected ICMSSN900(final ICMSSN900.Builder builder) {
        super(builder.origin, "900");
        this.bcModality = builder.bcModality;
        this.bcReductionPercent = builder.bcReductionPercent;
        this.bcValue = builder.bcValue;
        this.icmsAliquot = builder.icmsAliquot;
        this.icmsValue = builder.icmsValue;
        this.bcModalitySt = builder.bcModalitySt;
        this.valueMarginAddedStPercent = builder.valueMarginAddedStPercent;
        this.bcReductionStPercent = builder.bcReductionStPercent;
        this.bcValueST = builder.bcValueST;
        this.icmsStAliquot = builder.icmsStAliquot;
        this.icmsStValue = builder.icmsStValue;
        this.creditSnAliquot = builder.creditSnAliquot;
        this.creditSnIcmsValue = builder.creditSnIcmsValue;
    }

    public BCModality getBcModality() {
        return this.bcModality;
    }

    public String getBcValue() {
        return this.bcValue;
    }

    public String getBcReductionPercent() {
        return this.bcReductionPercent;
    }

    public String getIcmsAliquot() {
        return this.icmsAliquot;
    }

    @Override
    public String getIcmsValue() {
        return this.icmsValue;
    }

    public BCModalityST getBcModalitySt() {
        return this.bcModalitySt;
    }

    public String getValueMarginAddedStPercent() {
        return this.valueMarginAddedStPercent;
    }

    public String getBcReductionStPercent() {
        return this.bcReductionStPercent;
    }

    public String getBcValueST() {
        return this.bcValueST;
    }

    public String getIcmsStAliquot() {
        return this.icmsStAliquot;
    }

    @Override
    public String getIcmsStValue() {
        return this.icmsStValue;
    }

    public String getCreditSnAliquot() {
        return this.creditSnAliquot;
    }

    public String getCreditSnIcmsValue() {
        return this.creditSnIcmsValue;
    }

}

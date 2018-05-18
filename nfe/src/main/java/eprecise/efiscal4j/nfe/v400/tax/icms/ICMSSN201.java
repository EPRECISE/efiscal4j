
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.tax.icms.ICMS10.Builder;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=201 - Tributada pelo Simples Nacional com permissão de crédito e com cobrança do ICMS por Substituição Tributária
 *
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN201 extends BaseICMSSN implements IcmsWithST {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modBCST") @NotNull final BCModalityST bcModalitySt;

    private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Optional final String valueMarginAddedStPercent;

    private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Optional final String bcReductionStPercent;

    private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String bcValueST;

    private @XmlElement(name = "pICMSST") @NotNull @NFeDecimal0302a04 final String icmsStAliquot;

    private @XmlElement(name = "vICMSST") @NotNull @NFeDecimal1302 final String icmsStValue;

    private @XmlElement(name = "vBCFCPST") @NFeDecimal1302 final String bcFcpValueST;

    private @XmlElement(name = "pFCPST") @NFeDecimal0302a04Optional final String fcpStAliquot;

    private @XmlElement(name = "vFCPST") @NFeDecimal1302 final String fcpStValue;

    private @XmlElement(name = "pCredSN") @NotNull @NFeDecimal0302a04 final String creditSnAliquot;

    private @XmlElement(name = "vCredICMSSN") @NotNull @NFeDecimal1302 final String creditSnIcmsValue;

    public static class Builder extends BaseICMSSN.Builder implements ICMSBuilder {

        private BCModalityST bcModalitySt;

        private String valueMarginAddedStPercent;

        private String bcReductionStPercent;

        private String bcValueST;

        private String icmsStAliquot;

        private String icmsStValue;

        private String bcFcpValueST;

        private String fcpStAliquot;

        private String fcpStValue;

        private String creditSnAliquot;

        private String creditSnIcmsValue;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMSSN201.Builder) super.withOrigin(origin);
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
         * Valor da Base de cálculo do FCP retido por substituicao tributaria
         * 
         * @param bcFcpValueST
         */
        public Builder withBcFcpValueST(final String bcFcpValueST) {
            this.bcFcpValueST = bcFcpValueST;
            return this;
        }

        /**
         * Percentual de FCP retido por substituição tributária
         *
         * @param fcpStAliquot
         * @return
         */
        public Builder withFcpStAliquot(final String fcpStAliquot) {
            this.fcpStAliquot = fcpStAliquot;
            return this;
        }

        /**
         * Valor do FCP retido por substituição tributária
         *
         * @param fcpStValue
         * @return
         */
        public Builder withFcpStValue(final String fcpStValue) {
            this.fcpStValue = fcpStValue;
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
        public ICMSSN201 build() {
            return new ICMSSN201(this);
        }

    }

    public ICMSSN201() {
        this.bcModalitySt = null;
        this.valueMarginAddedStPercent = null;
        this.bcReductionStPercent = null;
        this.bcValueST = null;
        this.icmsStAliquot = null;
        this.icmsStValue = null;
        this.bcFcpValueST = null;
        this.fcpStAliquot = null;
        this.fcpStValue = null;
        this.creditSnAliquot = null;
        this.creditSnIcmsValue = null;
    }

    protected ICMSSN201(final ICMSSN201.Builder builder) {
        super(builder.origin, "201");
        this.bcModalitySt = builder.bcModalitySt;
        this.valueMarginAddedStPercent = builder.valueMarginAddedStPercent;
        this.bcReductionStPercent = builder.bcReductionStPercent;
        this.bcValueST = builder.bcValueST;
        this.icmsStAliquot = builder.icmsStAliquot;
        this.icmsStValue = builder.icmsStValue;
        this.bcFcpValueST = builder.bcFcpValueST;
        this.fcpStAliquot = builder.fcpStAliquot;
        this.fcpStValue = builder.fcpStValue;
        this.creditSnAliquot = builder.creditSnAliquot;
        this.creditSnIcmsValue = builder.creditSnIcmsValue;
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

    public String getBcFcpValueST() {
        return bcFcpValueST;
    }

    public String getFcpStAliquot() {
        return fcpStAliquot;
    }

    public String getFcpStValue() {
        return fcpStValue;
    }

    public String getCreditSnAliquot() {
        return creditSnAliquot;
    }

    public String getCreditSnIcmsValue() {
        return creditSnIcmsValue;
    }

}

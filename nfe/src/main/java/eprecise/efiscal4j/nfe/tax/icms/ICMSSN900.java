
package eprecise.efiscal4j.nfe.tax.icms;

import java.util.Arrays;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.nfe.tax.icms.adapters.ICMSSN900Adapter;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL, CRT=1 – Simples Nacional e CSOSN=900 - Outros
 *
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 * @author Fernando Glizt
 */
@XmlJavaTypeAdapter(ICMSSN900Adapter.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSSN900 extends BaseICMSSN implements IcmsWithValue, IcmsWithST {

    private static final long serialVersionUID = 1L;

    private final ICMSSN900FieldGroup1 fieldGroup1;

    private final ICMSSN900FieldGroup2 fieldGroup2;

    private final ICMSSN900FieldGroup3 fieldGroup3;

    static class ICMSSN900FieldGroup1 {

        private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

        private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

        private @XmlElement(name = "pRedBC") @NFeDecimal0302a04Optional final String bcReductionPercent;

        private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

        private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;

        public ICMSSN900FieldGroup1() {
            this.bcModality = null;
            this.bcReductionPercent = null;
            this.bcValue = null;
            this.icmsAliquot = null;
            this.icmsValue = null;
        }

        protected ICMSSN900FieldGroup1(final ICMSSN900.Builder builder) {
            this.bcModality = builder.bcModality;
            this.bcReductionPercent = builder.bcReductionPercent;
            this.bcValue = builder.bcValue;
            this.icmsAliquot = builder.icmsAliquot;
            this.icmsValue = builder.icmsValue;
        }
    }

    static class ICMSSN900FieldGroup2 {

        private @XmlElement(name = "modBCST") @NotNull final BCModalityST bcModalitySt;

        private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Optional final String valueMarginAddedStPercent;

        private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Optional final String bcReductionStPercent;

        private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String bcValueST;

        private @XmlElement(name = "pICMSST") @NotNull @NFeDecimal0302a04 final String icmsStAliquot;

        private @XmlElement(name = "vICMSST") @NotNull @NFeDecimal1302 final String icmsStValue;

        public ICMSSN900FieldGroup2() {
            this.bcModalitySt = null;
            this.valueMarginAddedStPercent = null;
            this.bcReductionStPercent = null;
            this.bcValueST = null;
            this.icmsStAliquot = null;
            this.icmsStValue = null;
        }

        protected ICMSSN900FieldGroup2(final ICMSSN900.Builder builder) {
            this.bcModalitySt = builder.bcModalitySt;
            this.valueMarginAddedStPercent = builder.valueMarginAddedStPercent;
            this.bcReductionStPercent = builder.bcReductionStPercent;
            this.bcValueST = builder.bcValueST;
            this.icmsStAliquot = builder.icmsStAliquot;
            this.icmsStValue = builder.icmsStValue;
        }

    }

    static class ICMSSN900FieldGroup3 {

        private @XmlElement(name = "pCredSN") @NotNull @NFeDecimal0302a04 final String creditSnAliquot;

        private @XmlElement(name = "vCredICMSSN") @NotNull @NFeDecimal1302 final String creditSnIcmsValue;

        public ICMSSN900FieldGroup3() {
            this.creditSnAliquot = null;
            this.creditSnIcmsValue = null;
        }

        protected ICMSSN900FieldGroup3(final ICMSSN900.Builder builder) {
            this.creditSnAliquot = builder.creditSnAliquot;
            this.creditSnIcmsValue = builder.creditSnIcmsValue;
        }

    }

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
        this.fieldGroup1 = null;
        this.fieldGroup2 = null;
        this.fieldGroup3 = null;
    }

    protected ICMSSN900(final ICMSSN900.Builder builder) {
        super(builder.origin, "900");

        if (!Arrays.asList(builder.bcModality, builder.bcReductionPercent, builder.bcValue, builder.icmsAliquot, builder.icmsValue)
                .containsAll(Arrays.asList(new Object[] { null, null, null, null, null }))) {
            this.fieldGroup1 = new ICMSSN900FieldGroup1(builder);
        } else {
            this.fieldGroup1 = null;
        }

        if (!Arrays.asList(builder.bcModalitySt, builder.valueMarginAddedStPercent, builder.bcReductionStPercent, builder.bcValueST, builder.icmsStAliquot, builder.icmsStValue)
                .containsAll(Arrays.asList(new Object[] { null, null, null, null, null, null }))) {
            this.fieldGroup2 = new ICMSSN900FieldGroup2(builder);
        } else {
            this.fieldGroup2 = null;
        }

        if (!Arrays.asList(builder.creditSnAliquot, builder.creditSnIcmsValue).containsAll(Arrays.asList(new Object[] { null, null }))) {
            this.fieldGroup3 = new ICMSSN900FieldGroup3(builder);
        } else {
            this.fieldGroup3 = null;
        }
    }

    public BCModality getBcModality() {
        return this.fieldGroup1 != null ? this.fieldGroup1.bcModality : null;
    }

    public String getBcValue() {
        return this.fieldGroup1 != null ? this.fieldGroup1.bcValue : null;
    }

    public String getBcReductionPercent() {
        return this.fieldGroup1 != null ? this.fieldGroup1.bcReductionPercent : null;
    }

    public String getIcmsAliquot() {
        return this.fieldGroup1 != null ? this.fieldGroup1.icmsAliquot : null;
    }

    @Override
    public String getIcmsValue() {
        return this.fieldGroup1 != null ? this.fieldGroup1.icmsValue : null;
    }

    public BCModalityST getBcModalitySt() {
        return this.fieldGroup2 != null ? this.fieldGroup2.bcModalitySt : null;
    }

    public String getValueMarginAddedStPercent() {
        return this.fieldGroup2 != null ? this.fieldGroup2.valueMarginAddedStPercent : null;
    }

    public String getBcReductionStPercent() {
        return this.fieldGroup2 != null ? this.fieldGroup2.bcReductionStPercent : null;
    }

    public String getBcValueST() {
        return this.fieldGroup2 != null ? this.fieldGroup2.bcValueST : null;
    }

    public String getIcmsStAliquot() {
        return this.fieldGroup2 != null ? this.fieldGroup2.icmsStAliquot : null;
    }

    @Override
    public String getIcmsStValue() {
        return this.fieldGroup2 != null ? this.fieldGroup2.icmsStValue : null;
    }

    public String getCreditSnAliquot() {
        return this.fieldGroup3 != null ? this.fieldGroup3.creditSnAliquot : null;
    }

    public String getCreditSnIcmsValue() {
        return this.fieldGroup3 != null ? this.fieldGroup3.creditSnIcmsValue : null;
    }

}

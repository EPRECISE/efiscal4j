
package eprecise.efiscal4j.nfe.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.address.UF;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Classe base para os ICMS de Partilha com CST 10 e 90
 * 
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
abstract class BaseICMSPart extends BaseICMS {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pRedBC") @NFeDecimal0302a04Optional final String bcReductionPercent;

    private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

    private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;

    private @XmlElement(name = "modBCST") @NotNull final BCModalityST bcModalitySt;

    private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Optional final String valueMarginAddedStPercent;

    private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Optional final String bcReductionStPercent;

    private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String bcValueST;

    private @XmlElement(name = "pICMSST") @NotNull @NFeDecimal0302a04 final String icmsStAliquot;

    private @XmlElement(name = "vICMSST") @NotNull @NFeDecimal1302 final String icmsStValue;

    private @XmlElement(name = "pBCOp") @NotNull @NFeDecimal0302a04Optional final String selfOperationBCPerc;

    private @XmlElement(name = "UFST") final String ufST;

    static abstract class Builder extends BaseICMS.Builder {

        private BCModality bcModality;

        private String bcValue;

        private String bcReductionPercent;

        private String icmsAliquot;

        private String icmsValue;

        private BCModalityST bcModalitySt;

        private String valueMarginAddedStPercent;

        private String bcReductionStPercent;

        private String bcValueST;

        private String icmsStAliquot;

        private String icmsStValue;

        private String selfOperationBCPerc;

        private String ufST;

        /**
         * @see BCModality
         */
        public Builder withBcModality(BCModality bcModality) {
            this.bcModality = bcModality;
            return this;
        }

        /**
         * Percentual de redução da BC
         * 
         * @param bcReductionPercent
         * @return
         */
        public Builder withBcReductionPercent(String bcReductionPercent) {
            this.bcReductionPercent = bcReductionPercent;
            return this;
        }

        /**
         * Valor da BC do ICMS
         */
        public Builder withBcValue(String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        /**
         * Alíquota do ICMS
         */
        public Builder withIcmsAliquot(String icmsAliquot) {
            this.icmsAliquot = icmsAliquot;
            return this;
        }

        /**
         * Valor do ICMS
         */
        public Builder withIcmsValue(String icmsValue) {
            this.icmsValue = icmsValue;
            return this;
        }

        /**
         * 
         * @see BCModalityST
         */
        public Builder withBcModalityST(BCModalityST bcModalityST) {
            this.bcModalitySt = bcModalityST;
            return this;
        }

        /**
         * Percentual da Margem de Valor Adicionado ICMS ST
         */
        public Builder withValueMarginAddedStPercent(String valueMarginAddedStPercent) {
            this.valueMarginAddedStPercent = valueMarginAddedStPercent;
            return this;
        }

        /**
         * Percentual de redução da BC ICMS ST
         */
        public Builder withBcReductionStPercent(String bcReductionStPercent) {
            this.bcReductionStPercent = bcReductionStPercent;
            return this;
        }

        /**
         * Valor da BC do ICMS ST
         */
        public Builder withBcValueST(String bcValueST) {
            this.bcValueST = bcValueST;
            return this;
        }

        /**
         * Alíquota do ICMS ST
         */
        public Builder withIcmsStAliquot(String icmsStAliquot) {
            this.icmsStAliquot = icmsStAliquot;
            return this;
        }

        /**
         * Valor do ICMS ST
         */
        public Builder withIcmsStValue(String icmsStValue) {
            this.icmsStValue = icmsStValue;
            return this;
        }

        /**
         * Percentual para determinação do valor da Base de Cálculo da operação própria.
         * 
         * @param selfOperationBCPerc
         * @return
         */
        public Builder withSelfOperationBCPerc(String selfOperationBCPerc) {
            this.selfOperationBCPerc = selfOperationBCPerc;
            return this;
        }

        /**
         * Sigla da UF para qual é devido o ICMS ST da operação.
         * 
         * @see UF
         * @param ufST
         * @return
         */
        public Builder withUfST(UF ufST) {
            this.ufST = ufST.getAcronym();
            return this;
        }

        @Override
        abstract BaseICMSPart build();
    }

    protected BaseICMSPart() {
        super(null, null);
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
        this.selfOperationBCPerc = null;
        this.ufST = null;
    }

    protected BaseICMSPart(Builder builder, String cst) {
        super(builder.origin, cst);
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
        this.selfOperationBCPerc = builder.selfOperationBCPerc;
        this.ufST = builder.ufST;
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

    public String getIcmsStValue() {
        return this.icmsStValue;
    }

    public String getSelfOperationBCPerc() {
        return this.selfOperationBCPerc;
    }

    public UF getUfST() {
        return UF.findByAcronym(this.ufST);
    }
}
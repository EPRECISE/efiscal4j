
package eprecise.efiscal4j.nfe.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04Opc;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 10 - Tributada e com cobrança do ICMS por substituição tributária
 * 
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS10 extends BaseICMS {

    /**
     * Modalidade de determinação da BC do ICMS:
     * 
     * 0 - Margem Valor Agregado (%);
     * 
     * 1 - Pauta (valor);
     * 
     * 2 - Preço Tabelado Máximo (valor);
     * 
     * 3 - Valor da Operação.
     */
    private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

    /**
     * Valor da BC do ICMS
     */
    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

    /**
     * Alíquota do ICMS
     */
    private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

    /**
     * Valor do ICMS
     */
    private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;

    /**
     * Modalidade de determinação da BC do ICMS ST:
     * 
     * 0 – Preço tabelado ou máximo sugerido;
     * 
     * 1 - Lista Negativa (valor);
     * 
     * 2 - Lista Positiva (valor);
     * 
     * 3 - Lista Neutra (valor);
     * 
     * 4 - Margem Valor Agregado (%);
     * 
     * 5 - Pauta (valor);
     */
    private @XmlElement(name = "modBCST") @NotNull final BCModalityST bcModalitySt;

    /**
     * Percentual da Margem de Valor Adicionado ICMS ST
     */
    private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Opc final String valueMarginAddedStPercent;

    /**
     * Percentual de redução da BC ICMS ST
     */
    private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Opc final String bcReductionStPercent;

    /**
     * Valor da BC do ICMS ST
     */
    private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String bcValueST;

    /**
     * Alíquota do ICMS ST
     */
    private @XmlElement(name = "pICMSST") @NotNull @NFeDecimal0302a04 final String icmsStAliquot;

    /**
     * Valor do ICMS ST
     */
    private @XmlElement(name = "vICMSST") @NotNull @NFeDecimal1302 final String icmsStValue;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private ProductOrigin origin;

        private BCModality bcModality;

        private String bcValue;

        private String icmsAliquot;

        private String icmsValue;

        private BCModalityST bcModalitySt;

        private String valueMarginAddedStPercent;

        private String bcReductionStPercent;

        private String bcValueST;

        private String icmsStAliquot;

        private String icmsStValue;

        public Builder withOrigin(ProductOrigin origin) {
            this.origin = origin;
            return this;
        }

        public Builder withBcModality(BCModality bcModality) {
            this.bcModality = bcModality;
            return this;
        }

        public Builder withBcValue(String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        public Builder withIcmsAliquot(String icmsAliquot) {
            this.icmsAliquot = icmsAliquot;
            return this;
        }

        public Builder withIcmsValue(String icmsValue) {
            this.icmsValue = icmsValue;
            return this;
        }

        public Builder withValueMarginAddedStPercent(String valueMarginAddedStPercent) {
            this.valueMarginAddedStPercent = valueMarginAddedStPercent;
            return this;
        }

        public Builder withBcReductionStPercent(String bcReductionStPercent) {
            this.bcReductionStPercent = bcReductionStPercent;
            return this;
        }

        public Builder withBcModalityST(BCModalityST bcModalityST) {
            this.bcModalitySt = bcModalityST;
            return this;
        }

        public Builder withBcValueST(String bcValueST) {
            this.bcValueST = bcValueST;
            return this;
        }

        public Builder withIcmsStAliquot(String icmsStAliquot) {
            this.icmsStAliquot = icmsStAliquot;
            return this;
        }

        public Builder withIcmsStValue(String icmsStValue) {
            this.icmsStValue = icmsStValue;
            return this;
        }

        @Override
        public ICMS10 build() {
            return new ICMS10(this);
        }

    }

    protected ICMS10(ICMS10.Builder builder) {
        super(builder.origin, "10");
        this.bcModality = builder.bcModality;
        this.bcValue = builder.bcValue;
        this.icmsAliquot = builder.icmsAliquot;
        this.icmsValue = builder.icmsValue;
        this.bcModalitySt = builder.bcModalitySt;
        this.valueMarginAddedStPercent = builder.valueMarginAddedStPercent;
        this.bcReductionStPercent = builder.bcReductionStPercent;
        this.bcValueST = builder.bcValueST;
        this.icmsStAliquot = builder.icmsStAliquot;
        this.icmsStValue = builder.icmsStValue;
    }
}


package eprecise.efiscal4j.nfe.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 00 - Tributada integralmente
 * 
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS00 extends BaseICMS {

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

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private ProductOrigin origin;

        private BCModality bcModality;

        private String bcValue;

        private String icmsAliquot;

        private String icmsValue;

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

        @Override
        public ICMS00 build() {
            return new ICMS00(this);
        }
    }

    protected ICMS00(ICMS00.Builder builder) {
        super(builder.origin, "00");
        this.bcModality = builder.bcModality;
        this.bcValue = builder.bcValue;
        this.icmsAliquot = builder.icmsAliquot;
        this.icmsValue = builder.icmsValue;
    }
}

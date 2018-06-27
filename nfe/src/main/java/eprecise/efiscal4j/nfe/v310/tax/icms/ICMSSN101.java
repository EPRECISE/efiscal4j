
package eprecise.efiscal4j.nfe.v310.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v310.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1302;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=101 - Tributada pelo Simples Nacional com permissão de crédito.
 * 
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSSN101 extends BaseICMSSN {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "pCredSN") @NotNull @NFeDecimal0302a04 final String creditSnAliquot;

    private @XmlElement(name = "vCredICMSSN") @NotNull @NFeDecimal1302 final String creditSnIcmsValue;

    public static class Builder extends BaseICMSSN.Builder implements ICMSBuilder {

        private String creditSnAliquot;

        private String creditSnIcmsValue;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMSSN101.Builder) super.withOrigin(origin);
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
        public ICMSSN101 build() {
            return new ICMSSN101(this);
        }

    }

    public ICMSSN101() {
        this.creditSnAliquot = null;
        this.creditSnIcmsValue = null;
    }

    protected ICMSSN101(final ICMSSN101.Builder builder) {
        super(builder.origin, "101");
        this.creditSnAliquot = builder.creditSnAliquot;
        this.creditSnIcmsValue = builder.creditSnIcmsValue;
    }

    public String getCreditSnAliquot() {
        return this.creditSnAliquot;
    }

    public String getCreditSnIcmsValue() {
        return this.creditSnIcmsValue;
    }

}

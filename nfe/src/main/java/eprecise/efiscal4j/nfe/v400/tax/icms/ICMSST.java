
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Grupo de informação do ICMSST devido para a UF de destino, nas operações interestaduais de produtos que tiveram retenção antecipada de ICMS por ST na UF do remetente. Repasse via Substituto
 * Tributário.
 * 
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSST extends BaseICMS {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBCSTRet") @NotNull @NFeDecimal1302 final String bcRetainedValueST;

    private @XmlElement(name = "vICMSSTRet") @NotNull @NFeDecimal1302 final String icmsRetainedValueST;

    private @XmlElement(name = "vBCSTDest") @NotNull @NFeDecimal1302 final String bcIcmsStDestination;

    private @XmlElement(name = "vICMSSTDest") @NotNull @NFeDecimal1302 final String icmsStDestination;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private String bcRetainedValueST;

        private String icmsRetainedValueST;

        private String bcIcmsStDestination;

        private String icmsStDestination;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMSST.Builder) super.withOrigin(origin);
        }

        /**
         * Informar o valor da BC do ICMS ST retido na UF remetente
         * 
         * @param bcRetainedValueST
         * @return
         */
        public Builder withBcRetainedValueST(final String bcRetainedValueST) {
            this.bcRetainedValueST = bcRetainedValueST;
            return this;
        }

        /**
         * Informar o valor do ICMS ST retido na UF remetente (iv2.0))
         * 
         * @param icmsRetainedValueST
         * @return
         */
        public Builder withIcmsRetainedValueST(final String icmsRetainedValueST) {
            this.icmsRetainedValueST = icmsRetainedValueST;
            return this;
        }

        /**
         * Informar o valor da BC do ICMS ST da UF destino
         * 
         * @param bcIcmsStDestination
         * @return
         */
        public Builder withBcIcmsStDestination(final String bcIcmsStDestination) {
            this.bcIcmsStDestination = bcIcmsStDestination;
            return this;
        }

        /**
         * Informar o valor do ICMS ST da UF destino (v2.0
         * 
         * @param icmsStDestination
         * @return
         */
        public Builder withIcmsStDestination(final String icmsStDestination) {
            this.icmsStDestination = icmsStDestination;
            return this;
        }

        @Override
        public ICMSST build() {
            return new ICMSST(this);
        }

    }

    public ICMSST() {
        this.bcRetainedValueST = null;
        this.icmsRetainedValueST = null;
        this.bcIcmsStDestination = null;
        this.icmsStDestination = null;
    }

    protected ICMSST(final ICMSST.Builder builder) {
        super(builder.origin, "41");
        this.bcRetainedValueST = builder.bcRetainedValueST;
        this.icmsRetainedValueST = builder.icmsRetainedValueST;
        this.bcIcmsStDestination = builder.bcIcmsStDestination;
        this.icmsStDestination = builder.icmsStDestination;
    }

    public String getBcRetainedValueST() {
        return this.bcRetainedValueST;
    }

    public String getIcmsRetainedValueST() {
        return this.icmsRetainedValueST;
    }

    public String getBcIcmsStDestination() {
        return this.bcIcmsStDestination;
    }

    public String getIcmsStDestination() {
        return this.icmsStDestination;
    }

}

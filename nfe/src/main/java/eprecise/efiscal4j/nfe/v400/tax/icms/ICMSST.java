
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04Optional;
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

    private @XmlElement(name = "pST") @NFeDecimal0302a04Optional final String endConsumerSupportedAliquot;

    private @XmlElement(name = "vICMSSubstituto") @NFeDecimal1302 final String icmsSubstituteValue;

    private @XmlElement(name = "vICMSSTRet") @NotNull @NFeDecimal1302 final String icmsRetainedValueST;

    private @XmlElement(name = "vBCFCPSTRet") @NFeDecimal1302 final String bcFcpRetainedValueST;

    private @XmlElement(name = "pFCPSTRet") @NFeDecimal0302a04Optional final String fcpRetainedAliquotST;

    private @XmlElement(name = "vFCPSTRet") @NFeDecimal1302 final String fcpRetainedValueST;

    private @XmlElement(name = "vBCSTDest") @NotNull @NFeDecimal1302 final String bcIcmsStDestination;

    private @XmlElement(name = "vICMSSTDest") @NotNull @NFeDecimal1302 final String icmsStDestination;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private String bcRetainedValueST;

        private String endConsumerSupportedAliquot;

        private String icmsSubstituteValue;

        private String icmsRetainedValueST;

        private String bcFcpRetainedValueST;

        private String fcpRetainedAliquotST;

        private String fcpRetainedValueST;

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
         * Aliquota suportada pelo consumidor final
         *
         * @param endConsumerSupportedAliquot
         * @return
         */
        public Builder withEndConsumerSupportedAliquot(final String endConsumerSupportedAliquot) {
            this.endConsumerSupportedAliquot = endConsumerSupportedAliquot;
            return this;
        }

        /**
         * Valor do ICMS Próprio do Substituto cobrado em operação anterior
         *
         * @param icmsSubstituteValue
         * @return
         */
        public Builder withIcmsSubstituteValue(final String icmsSubstituteValue) {
            this.icmsSubstituteValue = icmsSubstituteValue;
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
         * Valor da Base de cálculo do FCP retido anteriormente por ST
         *
         * @param bcFcpRetainedValueST
         */
        public Builder withBcFcpRetainedValueST(final String bcFcpRetainedValueST) {
            this.bcFcpRetainedValueST = bcFcpRetainedValueST;
            return this;
        }

        /**
         * Percentual de FCP retido anteriormente por substituição tributária
         *
         * @param fcpRetainedAliquotST
         * @return
         */
        public Builder withFcpRetainedAliquotST(final String fcpRetainedAliquotST) {
            this.fcpRetainedAliquotST = fcpRetainedAliquotST;
            return this;
        }

        /**
         * Valor do FCP retido por substituição tributária
         *
         * @param fcpRetainedValueST
         * @return
         */
        public Builder withFcpRetainedValueST(final String fcpRetainedValueST) {
            this.fcpRetainedValueST = fcpRetainedValueST;
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
        this.endConsumerSupportedAliquot = null;
        this.icmsSubstituteValue = null;
        this.icmsRetainedValueST = null;
        this.bcFcpRetainedValueST = null;
        this.fcpRetainedAliquotST = null;
        this.fcpRetainedValueST = null;
        this.bcIcmsStDestination = null;
        this.icmsStDestination = null;
    }

    protected ICMSST(final ICMSST.Builder builder) {
        super(builder.origin, "41");
        this.bcRetainedValueST = builder.bcRetainedValueST;
        this.endConsumerSupportedAliquot = builder.endConsumerSupportedAliquot;
        this.icmsSubstituteValue = builder.icmsSubstituteValue;
        this.icmsRetainedValueST = builder.icmsRetainedValueST;
        this.bcFcpRetainedValueST = builder.bcFcpRetainedValueST;
        this.fcpRetainedAliquotST = builder.fcpRetainedAliquotST;
        this.fcpRetainedValueST = builder.fcpRetainedValueST;
        this.bcIcmsStDestination = builder.bcIcmsStDestination;
        this.icmsStDestination = builder.icmsStDestination;
    }

    public String getBcRetainedValueST() {
        return this.bcRetainedValueST;
    }

    public String getEndConsumerSupportedAliquot() {
        return this.endConsumerSupportedAliquot;
    }

    public String getIcmsSubstituteValue() {
        return this.icmsSubstituteValue;
    }

    public String getIcmsRetainedValueST() {
        return this.icmsRetainedValueST;
    }

    public String getBcFcpRetainedValueST() {
        return this.bcFcpRetainedValueST;
    }

    public String getFcpRetainedAliquotST() {
        return this.fcpRetainedAliquotST;
    }

    public String getFcpRetainedValueST() {
        return this.fcpRetainedValueST;
    }

    public String getBcIcmsStDestination() {
        return this.bcIcmsStDestination;
    }

    public String getIcmsStDestination() {
        return this.icmsStDestination;
    }

}

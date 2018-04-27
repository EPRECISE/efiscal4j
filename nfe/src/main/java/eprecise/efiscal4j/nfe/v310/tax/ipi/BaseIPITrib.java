
package eprecise.efiscal4j.nfe.v310.tax.ipi;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v310.tax.ipi.validation.BaseIPITribStandard;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1204;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1204Variable;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1302;


/**
 * Classe base para os IPI com CST tributados (00, 49, 50 e 99)
 * 
 * @see BaseIPI
 * @see IPI
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BaseIPITrib extends BaseIPI implements BaseIPITribStandard {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBC") @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pIPI") @NFeDecimal0302a04 final String ipiAliquot;

    private @XmlElement(name = "qUnid") @NFeDecimal1204Variable final String unityQuantity;

    private @XmlElement(name = "vUnid") @NFeDecimal1204 final String unityValue;

    private @XmlElement(name = "vIPI") @NotNull @NFeDecimal1302 final String ipiValue;

    static abstract class Builder extends BaseIPI.Builder {

        private String bcValue;

        private String ipiAliquot;

        private String unityQuantity;

        private String unityValue;

        private String ipiValue;

        /**
         * Valor da BC do IPI
         * 
         * @param bcValue
         * @return
         */
        public Builder withBcValue(final String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        /**
         * Alíquota do IPI (em percentual)
         * 
         * @param ipiAliquot
         * @return
         */
        public Builder withIpiAliquot(final String ipiAliquot) {
            this.ipiAliquot = ipiAliquot;
            return this;
        }

        /**
         * Quantidade total na unidade padrão para tributação
         * 
         * @param unityQuantity
         * @return
         */
        public Builder withUnityQuantity(final String unityQuantity) {
            this.unityQuantity = unityQuantity;
            return this;
        }

        /**
         * Valor por Unidade Tributável. Informar o valor do imposto Pauta por unidade de medida.
         * 
         * @param unityValue
         * @return
         */
        public Builder withUnityValue(final String unityValue) {
            this.unityValue = unityValue;
            return this;
        }

        /**
         * Valor do IPI
         * 
         * @param ipiValue
         * @return
         */
        public Builder withIpiValue(final String ipiValue) {
            this.ipiValue = ipiValue;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiFrameworkClass(final String ipiFrameworkClass) {
            return (BaseIPITrib.Builder) super.withIpiFrameworkClass(ipiFrameworkClass);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(final String producerCNPJ) {
            return (BaseIPITrib.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(final String ipiSealCode) {
            return (BaseIPITrib.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(final String ipiSealQuantity) {
            return (BaseIPITrib.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(final String legalFramework) {
            return (BaseIPITrib.Builder) super.withLegalFramework(legalFramework);
        }

        @Override
        abstract BaseIPITrib build();
    }

    public BaseIPITrib() {
        this.bcValue = null;
        this.ipiAliquot = null;
        this.unityQuantity = null;
        this.unityValue = null;
        this.ipiValue = null;
    }

    protected BaseIPITrib(final Builder builder, final String cst) {
        super(builder, cst);
        this.bcValue = builder.bcValue;
        this.ipiAliquot = builder.ipiAliquot;
        this.unityQuantity = builder.unityQuantity;
        this.unityValue = builder.unityValue;
        this.ipiValue = builder.ipiValue;
    }

    @Override
    public String getBcValue() {
        return this.bcValue;
    }

    @Override
    public String getIpiAliquot() {
        return this.ipiAliquot;
    }

    @Override
    public String getUnityQuantity() {
        return this.unityQuantity;
    }

    @Override
    public String getUnityValue() {
        return this.unityValue;
    }

    @Override
    public String getIpiValue() {
        return this.ipiValue;
    }
}

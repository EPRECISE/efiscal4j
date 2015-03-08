
package eprecise.efiscal4j.nfe.tax.ipi;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * @see IPI
 * @author Felipe Bueno
 *
 */
abstract class BaseIPI extends IPI {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "CST") @NotNull final String cst;

    static abstract class Builder {

        private String ipiFrameworkClass;

        private String producerCNPJ;

        private String ipiSealCode;

        private String ipiSealQuantity;

        private String legalFramework;

        /**
         * Classe de Enquadramento do IPI para Cigarros e Bebidas
         * 
         * @param ipiFrameworkClass
         * @return
         */
        public Builder withIpiFrameworkClass(String ipiFrameworkClass) {
            this.ipiFrameworkClass = ipiFrameworkClass;
            return this;
        }

        /**
         * CNPJ do produtor da mercadoria, quando diferente do emitente. Somente para os casos de exportação direta ou indireta.
         * 
         * @param producerCNPJ
         * @return
         */
        public Builder withProducerCNPJ(String producerCNPJ) {
            this.producerCNPJ = producerCNPJ;
            return this;
        }

        /**
         * Código do selo de controle do IPI
         * 
         * @param ipiSealCode
         * @return
         */
        public Builder withIpiSealCode(String ipiSealCode) {
            this.ipiSealCode = ipiSealCode;
            return this;
        }

        /**
         * Quantidade de selo de controle do IPI
         * 
         * @param ipiSealQuantity
         * @return
         */
        public Builder withIpiSealQuantity(String ipiSealQuantity) {
            this.ipiSealQuantity = ipiSealQuantity;
            return this;
        }

        /**
         * Código de Enquadramento Legal do IPI (tabela a ser criada pela RFB)
         * 
         * @param legalFramework
         * @return
         */
        public Builder withLegalFramework(String legalFramework) {
            this.legalFramework = legalFramework;
            return this;
        }

        abstract BaseIPI build();
    }

    protected BaseIPI() {
        super();
        this.cst = null;
    }

    protected BaseIPI(Builder builder, String cst) {
        super();
        this.cst = cst;
        this.ipiFrameworkClass = builder.ipiFrameworkClass;
        this.producerCNPJ = builder.producerCNPJ;
        this.ipiSealCode = builder.ipiSealCode;
        this.ipiSealQuantity = builder.ipiSealQuantity;
        this.legalFramework = builder.legalFramework;
    }

    public String getCST() {
        return this.cst;
    }

}


package eprecise.efiscal4j.nfe.v310.refdocuments;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeAccessKey;


/**
 * Grupo de infromações da NF referenciada
 * 
 * @author Felipe Bueno
 *
 */

public class ReferencedDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "refNFe") @NFeAccessKey final String referencedNFeAccessKey;

    private @XmlElement(name = "refNF") final ReferencedNF referencedNF;

    private @XmlElement(name = "refNFP") final ProducerReferencedNF producerReferencedNF;

    private @XmlElement(name = "refCTe") @NFeAccessKey final String referencedCTeAccessKey;

    private @XmlElement(name = "refECF") final ReferencedECF referencedECF;

    public static class Builder {

        private String referencedNFeAccessKey;

        private ReferencedNF referencedNF;

        private ProducerReferencedNF producerReferencedNF;

        private String referencedCTeAccessKey;

        private ReferencedECF referencedECF;

        /**
         * NF-e referenciadas
         * 
         * @param referencedNFeAcessKey
         * @return
         */
        public Builder withReferencedNFeAccessKey(String referencedNFeAcessKey) {
            this.referencedNFeAccessKey = referencedNFeAcessKey;
            return this;
        }

        /**
         * @see ReferencedNF
         * @param referencedNF
         * @return
         */
        public Builder withReferencedNF(ReferencedNF referencedNF) {
            this.referencedNF = referencedNF;
            return this;
        }

        /**
         * 
         * @param producerReferencedNF
         * @return
         */
        public Builder withProducerReferencedNF(ProducerReferencedNF producerReferencedNF) {
            this.producerReferencedNF = producerReferencedNF;
            return this;
        }

        /**
         * CT-e referenciado emitido anteriormente, vinculado à NF-e atual
         * 
         * @param referencedCTeAccessKey
         * @return
         */
        public Builder withReferencedCTeAccessKey(String referencedCTeAccessKey) {
            this.referencedCTeAccessKey = referencedCTeAccessKey;
            return this;
        }

        /**
         * @see ReferencedECF
         * @param referencedECF
         * @return
         */
        public Builder withReferencedECF(ReferencedECF referencedECF) {
            this.referencedECF = referencedECF;
            return this;
        }

        public ReferencedDocuments build() {
            final ReferencedDocuments entity = new ReferencedDocuments(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ReferencedDocuments() {
        this.referencedNFeAccessKey = null;
        this.referencedNF = null;
        this.producerReferencedNF = null;
        this.referencedCTeAccessKey = null;
        this.referencedECF = null;
    }

    public ReferencedDocuments(Builder builder) {
        this.referencedNFeAccessKey = builder.referencedNFeAccessKey;
        this.referencedNF = builder.referencedNF;
        this.producerReferencedNF = builder.producerReferencedNF;
        this.referencedCTeAccessKey = builder.referencedCTeAccessKey;
        this.referencedECF = builder.referencedECF;
    }

    public String getReferencedNFeAccessKey() {
        return this.referencedNFeAccessKey;
    }

    public ReferencedNF getReferencedNF() {
        return this.referencedNF;
    }

    public ProducerReferencedNF getProducerReferencedNF() {
        return this.producerReferencedNF;
    }

    public String getReferencedCTeAccessKey() {
        return this.referencedCTeAccessKey;
    }

    public ReferencedECF getReferencedECF() {
        return this.referencedECF;
    }
}

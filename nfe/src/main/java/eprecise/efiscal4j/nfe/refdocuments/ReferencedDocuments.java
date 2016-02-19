
package eprecise.efiscal4j.nfe.refdocuments;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Grupo de infromações da NF referenciada
 * 
 * @author Felipe Bueno
 *
 */

public class ReferencedDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ReferencedNFe referencedNFe;

    private @XmlElement(name = "refNF") final ReferencedNF referencedNF;

    private @XmlElement(name = "refNFP") final ProducerReferencedNF producerReferencedNF;

    private final ReferencedCTe referencedCTe;

    private @XmlElement(name = "refECF") final ReferencedECF referencedECF;

    public static class Builder {

        private ReferencedNFe referencedNFe;

        private ReferencedNF referencedNF;

        private ProducerReferencedNF producerReferencedNF;

        private ReferencedCTe referencedCTe;

        private ReferencedECF referencedECF;

        /**
         * @see ReferencedNFe
         * @param referencedNFe
         * @return
         */
        public Builder withReferencedNFe(ReferencedNFe referencedNFe) {
            this.referencedNFe = referencedNFe;
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
        public Builder withproducerReferencedNF(ProducerReferencedNF producerReferencedNF) {
            this.producerReferencedNF = producerReferencedNF;
            return this;
        }

        /**
         * @see ReferencedCTe
         * @param referencedCTe
         * @return
         */
        public Builder withReferencedCTe(ReferencedCTe referencedCTe) {
            this.referencedCTe = referencedCTe;
            return this;
        }

        /**
         * @see ReferencedECF
         * @param referencedECF
         * @return
         */
        public Builder withreferencedECF(ReferencedECF referencedECF) {
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
        this.referencedNFe = null;
        this.referencedNF = null;
        this.producerReferencedNF = null;
        this.referencedCTe = null;
        this.referencedECF = null;
    }

    public ReferencedDocuments(Builder builder) {
        this.referencedNFe = builder.referencedNFe;
        this.referencedNF = builder.referencedNF;
        this.producerReferencedNF = builder.producerReferencedNF;
        this.referencedCTe = builder.referencedCTe;
        this.referencedECF = builder.referencedECF;
    }

    public ReferencedNFe getReferencedNFe() {
        return this.referencedNFe;
    }

    public ReferencedNF getReferencedNF() {
        return this.referencedNF;
    }

    public ProducerReferencedNF getProducerReferencedNF() {
        return this.producerReferencedNF;
    }

    public ReferencedCTe getReferencedCTe() {
        return this.referencedCTe;
    }

    public ReferencedECF getReferencedECF() {
        return this.referencedECF;
    }
}


package eprecise.efiscal4j.nfse.tc.elotech.statements.services;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechDeductionData {

    private final @XmlElement(name = "TipoDeducao") @NotNull ElotechDeductionType type;

    private final @NotNull CommonsNFSeCnp cnp;

    private final @XmlElement(name = "NumeroNotaFiscalReferencia") @NFSeNonNegativeInteger @Size(max = 15) String nfReferenceNumber;

    private final @XmlElement(name = "ValorTotalNotaFiscal") @NFSeValue String nfTotalValue;

    private final @XmlElement(name = "PercentualADeduzir") @NFSeValue String deductionPercent;

    private final @XmlElement(name = "ValorADeduzir") @NFSeValue String deductionValue;

    public static class Builder {

        private ElotechDeductionType type;

        private CommonsNFSeCnp cnp;

        private String nfReferenceNumber;

        private String nfTotalValue;

        private String deductionPercent;

        private String deductionValue;

        /**
         * @param type
         * @return
         */
        public Builder withType(final ElotechDeductionType type) {
            this.type = type;
            return this;
        }

        /**
         * @param cnp
         * @return
         */
        public Builder withCnp(final CommonsNFSeCnp cnp) {
            this.cnp = cnp;
            return this;
        }

        /**
         * @param nfReferenceNumber
         * @return
         */
        public Builder withNfReferenceNumber(final String nfReferenceNumber) {
            this.nfReferenceNumber = nfReferenceNumber;
            return this;
        }

        /**
         * @param nfTotalValue
         * @return
         */
        public Builder withNfTotalValue(final String nfTotalValue) {
            this.nfTotalValue = nfTotalValue;
            return this;
        }

        /**
         * @param deductionPercent
         * @return
         */
        public Builder withDeductionPercent(final String deductionPercent) {
            this.deductionPercent = deductionPercent;
            return this;
        }

        /**
         * @param deductionValue
         * @return
         */
        public Builder withDeductionValue(final String deductionValue) {
            this.deductionValue = deductionValue;
            return this;
        }

        public ElotechDeductionData build() throws Exception {
            final ElotechDeductionData entity = new ElotechDeductionData(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechDeductionData() {
        this.type = null;
        this.cnp = null;
        this.nfReferenceNumber = null;
        this.nfTotalValue = null;
        this.deductionPercent = null;
        this.deductionValue = null;
    }

    public ElotechDeductionData(final Builder builder) {
        this.type = builder.type;
        this.cnp = builder.cnp;
        this.nfReferenceNumber = builder.nfReferenceNumber;
        this.nfTotalValue = builder.nfTotalValue;
        this.deductionPercent = builder.deductionPercent;
        this.deductionValue = builder.deductionValue;
    }

    public ElotechDeductionType getType() {
        return type;
    }

    public CommonsNFSeCnp getCnp() {
        return cnp;
    }

    public String getNfReferenceNumber() {
        return nfReferenceNumber;
    }

    public String getNfTotalValue() {
        return nfTotalValue;
    }

    public String getDeductionPercent() {
        return deductionPercent;
    }

    public String getDeductionValue() {
        return deductionValue;
    }

}

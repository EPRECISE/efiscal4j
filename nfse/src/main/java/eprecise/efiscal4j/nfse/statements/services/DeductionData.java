
package eprecise.efiscal4j.nfse.statements.services;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.person.documents.NFSeCnp;
import eprecise.efiscal4j.nfse.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.types.NFSeValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class DeductionData {

    private final @XmlElement(name = "TipoDeducao") @NotNull DeductionType type;

    private final @NotNull NFSeCnp cnp;

    private final @XmlElement(name = "NumeroNotaFiscalReferencia") @NFSeNonNegativeInteger @Size(max = 15) String nfReferenceNumber;

    private final @XmlElement(name = "ValorTotalNotaFiscal") @NFSeValue String nfTotalValue;

    private final @XmlElement(name = "PercentualADeduzir") @NFSeValue String deductionPercent;

    private final @XmlElement(name = "ValorADeduzir") @NFSeValue String deductionValue;

    public static class Builder {

        private DeductionType type;

        private NFSeCnp cnp;

        private String nfReferenceNumber;

        private String nfTotalValue;

        private String deductionPercent;

        private String deductionValue;

        /**
         * @param type
         * @return
         */
        public Builder withType(final DeductionType type) {
            this.type = type;
            return this;
        }

        /**
         * @param cnp
         * @return
         */
        public Builder withCnp(final NFSeCnp cnp) {
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

        public DeductionData build() throws Exception {
            final DeductionData entity = new DeductionData(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public DeductionData() {
        this.type = null;
        this.cnp = null;
        this.nfReferenceNumber = null;
        this.nfTotalValue = null;
        this.deductionPercent = null;
        this.deductionValue = null;
    }

    public DeductionData(final Builder builder) {
        this.type = builder.type;
        this.cnp = builder.cnp;
        this.nfReferenceNumber = builder.nfReferenceNumber;
        this.nfTotalValue = builder.nfTotalValue;
        this.deductionPercent = builder.deductionPercent;
        this.deductionValue = builder.deductionValue;
    }

    public DeductionType getType() {
        return type;
    }

    public NFSeCnp getCnp() {
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

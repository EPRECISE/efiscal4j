
package eprecise.efiscal4j.nfse;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.statements.StatementProvisionService;
import eprecise.efiscal4j.nfse.types.NFSeAccessKey;
import eprecise.efiscal4j.nfse.types.NFSeDate;
import eprecise.efiscal4j.nfse.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.types.NFSeValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFSe implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "InfNfse") @NotNull NFSeInfo info;

    public static class Builder {

        private NFSeInfo info;

        /**
         * @param info
         * @return
         */
        public Builder withInfo(final NFSeInfo info) {
            this.info = info;
            return this;
        }

        public NFSe build() throws Exception {
            final NFSe entity = new NFSe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSe() {
        info = null;
    }

    public NFSe(final Builder builder) {
        info = builder.info;
    }

    public NFSeInfo getInfo() {
        return info;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class NFSeInfo {

        private final @NotNull @XmlElement(name = "Numero") @NFSeNonNegativeInteger @Size(max = 15) String number;

        private final @NotNull @XmlElement(name = "CodigoVerificacao") @Size(min = 1, max = 9) String verificationCode;

        private final @NotNull @XmlElement(name = "DataEmissao") @NFSeDate String emissionDate;

        private final @XmlElement(name = "NfseSubstituida") @NFSeNonNegativeInteger @Size(max = 15) String nfseSubstitutedNumber;

        private final @XmlElement(name = "OutrasInformacoes") @Size(min = 1, max = 255) String otherInformation;

        private final @NotNull @XmlElement(name = "ValoresNfse") NFSeValues nfseValues;

        private final @XmlElement(name = "ValorCredito") @NFSeValue String creditValue;

        private final @NotNull @XmlElement(name = "OrgaoGerador") @NFSeValue GeneratorOrgan generatorOrgan;

        private final @XmlElement(name = "DeclaracaoPrestacaoServico") StatementProvisionService statementProvisionService;

        private final @NotNull @XmlElement(name = "ChaveAcesso") @NFSeAccessKey String accessKey;

        public static class Builder {

            private String number;

            private String verificationCode;

            private String emissionDate;

            private String nfseSubstitutedNumber;

            private String otherInformation;

            private NFSeValues nfseValues;

            private String creditValue;

            private GeneratorOrgan generatorOrgan;

            private StatementProvisionService statementProvisionService;

            private String accessKey;

            /**
             * @param number
             * @return
             */
            public Builder withNumber(final String number) {
                this.number = number;
                return this;
            }

            /**
             * @param verificationCode
             * @return
             */
            public Builder withVerificationCode(final String verificationCode) {
                this.verificationCode = verificationCode;
                return this;
            }

            /**
             * @param emissionDate
             * @return
             */
            public Builder withEmissionDate(final String emissionDate) {
                this.emissionDate = emissionDate;
                return this;
            }

            /**
             * @param nfseSubstitutedNumber
             * @return
             */
            public Builder withNfseSubstitutedNumber(final String nfseSubstitutedNumber) {
                this.nfseSubstitutedNumber = nfseSubstitutedNumber;
                return this;
            }

            /**
             * @param otherInformation
             * @return
             */
            public Builder withOtherInformation(final String otherInformation) {
                this.otherInformation = otherInformation;
                return this;
            }

            /**
             * @param nfseValues
             * @return
             */
            public Builder withNfseValues(final NFSeValues nfseValues) {
                this.nfseValues = nfseValues;
                return this;
            }

            /**
             * @param creditValue
             * @return
             */
            public Builder withCreditValue(final String creditValue) {
                this.creditValue = creditValue;
                return this;
            }

            /**
             * @param generatorOrgan
             * @return
             */
            public Builder withGeneratorOrgan(final GeneratorOrgan generatorOrgan) {
                this.generatorOrgan = generatorOrgan;
                return this;
            }

            /**
             * @param statementProvisionService
             * @return
             */
            public Builder withStatementProvisionService(final StatementProvisionService statementProvisionService) {
                this.statementProvisionService = statementProvisionService;
                return this;
            }

            /**
             * @param accessKey
             * @return
             */
            public Builder withAccessKey(final String accessKey) {
                this.accessKey = accessKey;
                return this;
            }

            public NFSeInfo build() throws Exception {
                final NFSeInfo entity = new NFSeInfo(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public NFSeInfo() {

            number = null;
            verificationCode = null;
            emissionDate = null;
            nfseSubstitutedNumber = null;
            otherInformation = null;
            nfseValues = null;
            creditValue = null;
            generatorOrgan = null;
            statementProvisionService = null;
            accessKey = null;
        }

        public NFSeInfo(final Builder builder) {
            number = builder.number;
            verificationCode = builder.verificationCode;
            emissionDate = builder.emissionDate;
            nfseSubstitutedNumber = builder.nfseSubstitutedNumber;
            otherInformation = builder.otherInformation;
            nfseValues = builder.nfseValues;
            creditValue = builder.creditValue;
            generatorOrgan = builder.generatorOrgan;
            statementProvisionService = builder.statementProvisionService;
            accessKey = builder.accessKey;
        }

        public String getNumber() {
            return number;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public String getEmissionDate() {
            return emissionDate;
        }

        public String getNfseSubstitutedNumber() {
            return nfseSubstitutedNumber;
        }

        public String getOtherInformation() {
            return otherInformation;
        }

        public NFSeValues getNfseValues() {
            return nfseValues;
        }

        public String getCreditValue() {
            return creditValue;
        }

        public GeneratorOrgan getGeneratorOrgan() {
            return generatorOrgan;
        }

        public StatementProvisionService getStatementProvisionService() {
            return statementProvisionService;
        }

        public String getAccessKey() {
            return accessKey;
        }

    }

}
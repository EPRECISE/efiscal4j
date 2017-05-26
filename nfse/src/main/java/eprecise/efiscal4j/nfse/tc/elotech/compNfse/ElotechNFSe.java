
package eprecise.efiscal4j.nfse.tc.elotech.compNfse;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.comp.ProcessedNFSe;
import eprecise.efiscal4j.nfse.domain.comp.rps.RpsIdentifier;
import eprecise.efiscal4j.nfse.tc.commons.compNfse.CommonsGeneratorOrgan;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechStatementProvisionService;
import eprecise.efiscal4j.nfse.transmission.elotech.ElotechNFSeAdapter;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeValue;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeAccessKey;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;


@XmlRootElement(name = "Nfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechNFSe implements ProcessedNFSe {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "InfNfse") @NotNull ElotechNFSeInfo info;

    public static class Builder {

        private ElotechNFSeInfo info;

        /**
         * @param info
         * @return
         */
        public Builder withInfo(final ElotechNFSeInfo info) {
            this.info = info;
            return this;
        }

        public ElotechNFSe build() throws Exception {
            final ElotechNFSe entity = new ElotechNFSe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechNFSe() {
        info = null;
    }

    public ElotechNFSe(final Builder builder) {
        info = builder.info;
    }

    public ElotechNFSeInfo getInfo() {
        return info;
    }

    @Override
    public String getNumber() {
        return Optional.ofNullable(info).map(i -> i.getNumber()).orElse(null);
    }

    @Override
    public String getVerificationCode() {
        return Optional.ofNullable(info).map(i -> i.getVerificationCode()).orElse(null);
    }

    @Override
    public Date getEmissionDate() {
        return Optional.ofNullable(info).map(i -> i.getEmissionDate()).map(t -> {
            try {
                return ElotechNFSeAdapter.NFSE_DATE_FORMAT.parse(t);
            } catch (final ParseException e) {
                throw new RuntimeException(e);
            }
        }).orElse(null);
    }

    @Override
    public RpsIdentifier getRpsIdentifier() {
        return Optional.ofNullable(info).map(i -> i.getStatementProvisionService().getInfo().getRps().getIdentifier()).orElse(null);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ElotechNFSeInfo {

        private final @NotNull @XmlElement(name = "Numero") @NFSeNonNegativeInteger @Size(max = 15) String number;

        private final @NotNull @XmlElement(name = "CodigoVerificacao") @Size(min = 1, max = 9) String verificationCode;

        private final @NotNull @XmlElement(name = "DataEmissao") @NFSeDate String emissionDate;

        private final @XmlElement(name = "NfseSubstituida") @NFSeNonNegativeInteger @Size(max = 15) String nfseSubstitutedNumber;

        private final @XmlElement(name = "OutrasInformacoes") @Size(min = 1, max = 255) String otherInformation;

        private final @NotNull @XmlElement(name = "ValoresNfse") ElotechNFSeValues nfseValues;

        private final @XmlElement(name = "ValorCredito") @NFSeValue String creditValue;

        private final @NotNull @XmlElement(name = "OrgaoGerador") CommonsGeneratorOrgan generatorOrgan;

        private final @XmlElement(name = "DeclaracaoPrestacaoServico") ElotechStatementProvisionService statementProvisionService;

        private final @NotNull @XmlElement(name = "ChaveAcesso") @NFSeAccessKey String accessKey;

        public static class Builder {

            private String number;

            private String verificationCode;

            private String emissionDate;

            private String nfseSubstitutedNumber;

            private String otherInformation;

            private ElotechNFSeValues nfseValues;

            private String creditValue;

            private CommonsGeneratorOrgan generatorOrgan;

            private ElotechStatementProvisionService statementProvisionService;

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
            public Builder withNfseValues(final ElotechNFSeValues nfseValues) {
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
            public Builder withGeneratorOrgan(final CommonsGeneratorOrgan generatorOrgan) {
                this.generatorOrgan = generatorOrgan;
                return this;
            }

            /**
             * @param statementProvisionService
             * @return
             */
            public Builder withStatementProvisionService(final ElotechStatementProvisionService statementProvisionService) {
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

            public ElotechNFSeInfo build() throws Exception {
                final ElotechNFSeInfo entity = new ElotechNFSeInfo(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public ElotechNFSeInfo() {

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

        public ElotechNFSeInfo(final Builder builder) {
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

        public ElotechNFSeValues getNfseValues() {
            return nfseValues;
        }

        public String getCreditValue() {
            return creditValue;
        }

        public CommonsGeneratorOrgan getGeneratorOrgan() {
            return generatorOrgan;
        }

        public ElotechStatementProvisionService getStatementProvisionService() {
            return statementProvisionService;
        }

        public String getAccessKey() {
            return accessKey;
        }

    }

}

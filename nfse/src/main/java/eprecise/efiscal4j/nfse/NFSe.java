
package eprecise.efiscal4j.nfse;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.statements.StatementProvisionService;
import eprecise.efiscal4j.nfse.types.NFSeDate;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFSe implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "InfNfse") @NotNull Info info;

    public static class Builder {

        private Info info;

        /**
         * @param info
         * @return
         */
        public Builder withInfo(final Info info) {
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

    public Info getInfo() {
        return info;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Info {

        private final @NotNull @XmlElement(name = "Numero") String number;

        private final @NotNull @XmlElement(name = "CodigoVerificacao") String verificationCode;

        private final @NotNull @XmlElement(name = "DataEmissao") @NFSeDate String emissionDate;

        private final @XmlElement(name = "OutrasInformacoes") String otherInformation;

        private final @NotNull @XmlElement(name = "ValoresNfse") NFSeValues nfseValues;

        private final @XmlElement(name = "DeclaracaoPrestacaoServico") StatementProvisionService statementProvisionService;

        public static class Builder {

            private String number;

            private String verificationCode;

            private String emissionDate;

            private String otherInformation;

            private NFSeValues nfseValues;

            private StatementProvisionService statementProvisionService;

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
             * @param statementProvisionService
             * @return
             */
            public Builder withStatementProvisionService(final StatementProvisionService statementProvisionService) {
                this.statementProvisionService = statementProvisionService;
                return this;
            }

            public Info build() throws Exception {
                final Info entity = new Info(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public Info() {
            number = null;
            verificationCode = null;
            emissionDate = null;
            otherInformation = null;
            nfseValues = null;
            statementProvisionService = null;
        }

        public Info(final Builder builder) {
            number = builder.number;
            verificationCode = builder.verificationCode;
            emissionDate = builder.emissionDate;
            otherInformation = builder.otherInformation;
            nfseValues = builder.nfseValues;
            statementProvisionService = builder.statementProvisionService;
        }
    }

}

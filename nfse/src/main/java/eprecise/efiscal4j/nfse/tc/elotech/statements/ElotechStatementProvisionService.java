
package eprecise.efiscal4j.nfse.tc.elotech.statements;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.elotech.statements.rps.ElotechRps;
import eprecise.efiscal4j.nfse.tc.elotech.statements.services.ElotechService;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeDate;


/**
 * Declaração de Prestação de Serviço
 *
 * @author Fernando C Glizt
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechStatementProvisionService {

    private final @XmlElement(name = "InfDeclaracaoPrestacaoServico") @NotNull Info info;

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

        public ElotechStatementProvisionService build() throws Exception {
            final ElotechStatementProvisionService entity = new ElotechStatementProvisionService(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechStatementProvisionService() {
        info = null;
    }

    public ElotechStatementProvisionService(final Builder builder) {
        info = builder.info;
    }

    public Info getInfo() {
        return info;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Info {

        private final @XmlElement(name = "Rps") @NotNull ElotechRps rps;

        private final @XmlElement(name = "Competencia") @NotNull @NFSeDate String competence;

        private final @XmlElement(name = "Servico") @NotNull ElotechService service;

        private final @XmlElement(name = "DadosPrestador") @NotNull ElotechServiceProvider serviceProvider;

        private final @XmlElement(name = "Tomador") ElotechServiceTaker serviceTaker;

        private final @XmlElement(name = "Intermediario") ElotechServiceIntermediary serviceIntermediary;

        private final @XmlElement(name = "ConstrucaoCivil") ElotechServiceConstruction construction;

        private final @XmlElement(name = "RegimeEspecialTributacao") ElotechSpecialTaxationRegime specialTaxationRegime;

        private final @XmlElement(name = "IncentivoFiscal") @NotNull ElotechTaxIncentive taxIncentive;

        public static class Builder {

            private ElotechRps rps;

            private String competence;

            private ElotechService service;

            private ElotechServiceProvider serviceProvider;

            private ElotechServiceTaker serviceTaker;

            private ElotechServiceIntermediary serviceIntermediary;

            private ElotechServiceConstruction construction;

            private ElotechSpecialTaxationRegime specialTaxationRegime;

            private ElotechTaxIncentive taxIncentive;

            /**
             * @param rps
             * @return
             */
            public Builder withRps(final ElotechRps rps) {
                this.rps = rps;
                return this;
            }

            /**
             * @param competence
             * @return
             */
            public Builder withCompetence(final String competence) {
                this.competence = competence;
                return this;
            }

            /**
             * @param service
             * @return
             */
            public Builder withService(final ElotechService service) {
                this.service = service;
                return this;
            }

            /**
             * @param serviceProvider
             * @return
             */
            public Builder withServiceProvider(final ElotechServiceProvider serviceProvider) {
                this.serviceProvider = serviceProvider;
                return this;
            }

            /**
             * @param serviceTaker
             * @return
             */
            public Builder withServiceTaker(final ElotechServiceTaker serviceTaker) {
                this.serviceTaker = serviceTaker;
                return this;
            }

            /**
             * @param serviceIntermediary
             * @return
             */
            public Builder withServiceIntermediary(final ElotechServiceIntermediary serviceIntermediary) {
                this.serviceIntermediary = serviceIntermediary;
                return this;
            }

            /**
             * @param construction
             * @return
             */
            public Builder withConstruction(final ElotechServiceConstruction construction) {
                this.construction = construction;
                return this;
            }

            /**
             * @param specialTaxationRegime
             * @return
             */
            public Builder withSpecialTaxationRegime(final ElotechSpecialTaxationRegime specialTaxationRegime) {
                this.specialTaxationRegime = specialTaxationRegime;
                return this;
            }

            /**
             * @param taxIncentive
             * @return
             */
            public Builder withTaxIncentive(final ElotechTaxIncentive taxIncentive) {
                this.taxIncentive = taxIncentive;
                return this;
            }

            public Info build() throws Exception {
                final Info entity = new Info(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public Info() {
            rps = null;
            competence = null;
            service = null;
            serviceProvider = null;
            serviceTaker = null;
            serviceIntermediary = null;
            construction = null;
            specialTaxationRegime = null;
            taxIncentive = null;
        }

        public Info(final Builder builder) {
            rps = builder.rps;
            competence = builder.competence;
            service = builder.service;
            serviceProvider = builder.serviceProvider;
            serviceTaker = builder.serviceTaker;
            serviceIntermediary = builder.serviceIntermediary;
            construction = builder.construction;
            specialTaxationRegime = builder.specialTaxationRegime;
            taxIncentive = builder.taxIncentive;
        }

        public ElotechRps getRps() {
            return rps;
        }

        public String getCompetence() {
            return competence;
        }

        public ElotechService getService() {
            return service;
        }

        public ElotechServiceProvider getServiceProvider() {
            return serviceProvider;
        }

        public ElotechServiceTaker getServiceTaker() {
            return serviceTaker;
        }

        public ElotechServiceIntermediary getServiceIntermediary() {
            return serviceIntermediary;
        }

        public ElotechServiceConstruction getConstruction() {
            return construction;
        }

        public ElotechSpecialTaxationRegime getSpecialTaxationRegime() {
            return specialTaxationRegime;
        }

        public ElotechTaxIncentive getTaxIncentive() {
            return taxIncentive;
        }
    }

}


package eprecise.efiscal4j.nfse.tc.elotech.statements;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.elotech.statements.rps.Rps;
import eprecise.efiscal4j.nfse.tc.elotech.statements.services.Service;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeDate;


/**
 * Declaração de Prestação de Serviço
 *
 * @author Fernando C Glizt
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StatementProvisionService {

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

        public StatementProvisionService build() throws Exception {
            final StatementProvisionService entity = new StatementProvisionService(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public StatementProvisionService() {
        info = null;
    }

    public StatementProvisionService(final Builder builder) {
        info = builder.info;
    }

    public Info getInfo() {
        return info;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Info {

        private final @XmlElement(name = "Rps") @NotNull Rps rps;

        private final @XmlElement(name = "Competencia") @NotNull @NFSeDate String competence;

        private final @XmlElement(name = "Servico") @NotNull Service service;

        private final @XmlElement(name = "DadosPrestador") @NotNull ServiceProvider serviceProvider;

        private final @XmlElement(name = "Tomador") ServiceTaker serviceTaker;

        private final @XmlElement(name = "Intermediario") ServiceIntermediary serviceIntermediary;

        private final @XmlElement(name = "ConstrucaoCivil") ServiceConstruction construction;

        private final @XmlElement(name = "RegimeEspecialTributacao") SpecialTaxationRegime specialTaxationRegime;

        private final @XmlElement(name = "IncentivoFiscal") @NotNull TaxIncentive taxIncentive;

        public static class Builder {

            private Rps rps;

            private String competence;

            private Service service;

            private ServiceProvider serviceProvider;

            private ServiceTaker serviceTaker;

            private ServiceIntermediary serviceIntermediary;

            private ServiceConstruction construction;

            private SpecialTaxationRegime specialTaxationRegime;

            private TaxIncentive taxIncentive;

            /**
             * @param rps
             * @return
             */
            public Builder withRps(final Rps rps) {
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
            public Builder withService(final Service service) {
                this.service = service;
                return this;
            }

            /**
             * @param serviceProvider
             * @return
             */
            public Builder withServiceProvider(final ServiceProvider serviceProvider) {
                this.serviceProvider = serviceProvider;
                return this;
            }

            /**
             * @param serviceTaker
             * @return
             */
            public Builder withServiceTaker(final ServiceTaker serviceTaker) {
                this.serviceTaker = serviceTaker;
                return this;
            }

            /**
             * @param serviceIntermediary
             * @return
             */
            public Builder withServiceIntermediary(final ServiceIntermediary serviceIntermediary) {
                this.serviceIntermediary = serviceIntermediary;
                return this;
            }

            /**
             * @param construction
             * @return
             */
            public Builder withConstruction(final ServiceConstruction construction) {
                this.construction = construction;
                return this;
            }

            /**
             * @param specialTaxationRegime
             * @return
             */
            public Builder withSpecialTaxationRegime(final SpecialTaxationRegime specialTaxationRegime) {
                this.specialTaxationRegime = specialTaxationRegime;
                return this;
            }

            /**
             * @param taxIncentive
             * @return
             */
            public Builder withTaxIncentive(final TaxIncentive taxIncentive) {
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

        public Rps getRps() {
            return rps;
        }

        public String getCompetence() {
            return competence;
        }

        public Service getService() {
            return service;
        }

        public ServiceProvider getServiceProvider() {
            return serviceProvider;
        }

        public ServiceTaker getServiceTaker() {
            return serviceTaker;
        }

        public ServiceIntermediary getServiceIntermediary() {
            return serviceIntermediary;
        }

        public ServiceConstruction getConstruction() {
            return construction;
        }

        public SpecialTaxationRegime getSpecialTaxationRegime() {
            return specialTaxationRegime;
        }

        public TaxIncentive getTaxIncentive() {
            return taxIncentive;
        }
    }

}

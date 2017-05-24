
package eprecise.efiscal4j.nfse.tc.govbr.lot.rps;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechServiceConstruction;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechServiceIntermediary;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechServiceProvider;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechServiceTaker;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechTaxIncentive;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.rps.ElotechRps;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechService;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.service.GovbrService;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeDate;


@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrRps {

    private final @XmlElement(name = "InfRps") @NotNull Info info;

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

        public GovbrRps build() throws Exception {
            final GovbrRps entity = new GovbrRps(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrRps() {
        info = null;
    }

    public GovbrRps(final Builder builder) {
        info = builder.info;
    }

    public Info getInfo() {
        return info;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Info {

        private final @XmlElement(name = "IdentificacaoRps") @NotNull CommonsRpsIdentifier identifier;

        private final @XmlElement(name = "DataEmissao") @NotNull @NFSeDate String emissionDate;

        private final @XmlElement(name = "NaturezaOperacao") @NotNull GovbrNatureOperation natureOperation;

        private final @XmlElement(name = "RegimeEspecialTributacao") ElotechSpecialTaxationRegime specialTaxationRegime;

        private final @XmlElement(name = "OptanteSimplesNacional") @NotNull CommonsNFSeBoolean simpleNational;

        private final @XmlElement(name = "IncentivadorCultural") @NotNull CommonsNFSeBoolean culturalPromoter;

        private final @XmlElement(name = "Status") @NotNull CommonsRpsStatus status;

        private final @XmlElement(name = "RpsSubstituido") CommonsRpsIdentifier rpsSubstituted;

        private final @XmlElement(name = "Servico") @NotNull GovbrService service;

        private final @XmlElement(name = "Prestador") @NotNull GovbrServiceProvider.GovbrServiceProviderIdentifier serviceProvider;

        private final @XmlElement(name = "Tomador") GovbrServiceTaker serviceTaker;

        private final @XmlElement(name = "Intermediario") GovbrServiceIntermediaryIdentifier serviceIntermediary;

        private final @XmlElement(name = "ConstrucaoCivil") ElotechServiceConstruction construction;

        public static class Builder {

            private String competence;

            private ElotechService service;

            private ElotechServiceProvider serviceProvider;

            private ElotechServiceTaker serviceTaker;

            private ElotechServiceIntermediary serviceIntermediary;

            private ElotechServiceConstruction construction;

            private ElotechSpecialTaxationRegime specialTaxationRegime;

            private ElotechTaxIncentive taxIncentive;

            /**
             * @param identifier
             * @return
             */
            public Builder withIdentifier(final CommonsRpsIdentifier identifier) {
                return this;
            }

            /**
             * @param emissionDate
             * @return
             */
            public Builder withEmissionDate(final String emissionDate) {
                return this;
            }

            /**
             * @param status
             * @return
             */
            public Builder withStatus(final CommonsRpsStatus status) {
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

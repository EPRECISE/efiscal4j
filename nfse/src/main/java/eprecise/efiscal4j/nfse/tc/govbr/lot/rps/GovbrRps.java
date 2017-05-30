
package eprecise.efiscal4j.nfse.tc.govbr.lot.rps;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.service.GovbrService;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;


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

        public GovbrRps build() {
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

        private final @XmlElement(name = "DataEmissao") @NotNull @NFSeDateTimeUTC String emissionDate;

        private final @XmlElement(name = "NaturezaOperacao") @NotNull GovbrNatureOperation natureOperation;

        private final @XmlElement(name = "RegimeEspecialTributacao") GovbrSpecialTaxationRegime specialTaxationRegime;

        private final @XmlElement(name = "OptanteSimplesNacional") @NotNull CommonsNFSeBoolean simpleNational;

        private final @XmlElement(name = "IncentivadorCultural") @NotNull CommonsNFSeBoolean culturalPromoter;

        private final @XmlElement(name = "Status") @NotNull CommonsRpsStatus status;

        private final @XmlElement(name = "RpsSubstituido") CommonsRpsIdentifier rpsSubstituted;

        private final @XmlElement(name = "Servico") @NotNull GovbrService service;

        private final @XmlElement(name = "Prestador") @NotNull GovbrServiceProvider.GovbrServiceProviderIdentifier serviceProviderIdentifier;

        private final @XmlElement(name = "Tomador") @NotNull GovbrServiceTaker serviceTaker;

        private final @XmlElement(name = "Intermediario") GovbrServiceIntermediaryIdentifier serviceIntermediaryIdentifier;

        private final @XmlElement(name = "ConstrucaoCivil") GovbrServiceConstruction construction;

        public static class Builder {

            private CommonsRpsIdentifier identifier;

            private String emissionDate;

            private GovbrNatureOperation natureOperation;

            private GovbrSpecialTaxationRegime specialTaxationRegime;

            private CommonsNFSeBoolean simpleNational;

            private CommonsNFSeBoolean culturalPromoter;

            private CommonsRpsStatus status;

            private CommonsRpsIdentifier rpsSubstituted;

            private GovbrService service;

            private GovbrServiceProvider.GovbrServiceProviderIdentifier serviceProviderIdentifier;

            private GovbrServiceTaker serviceTaker;

            private GovbrServiceIntermediaryIdentifier serviceIntermediaryIdentifier;

            private GovbrServiceConstruction construction;

            /**
             * @param identifier
             * @return
             */
            public Builder withIdentifier(final CommonsRpsIdentifier identifier) {
                this.identifier = identifier;
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
             * @param natureOperation
             * @return
             */
            public Builder withNatureOperation(final GovbrNatureOperation natureOperation) {
                this.natureOperation = natureOperation;
                return this;
            }

            /**
             * @param specialTaxationRegime
             * @return
             */
            public Builder withSpecialTaxationRegime(final GovbrSpecialTaxationRegime specialTaxationRegime) {
                this.specialTaxationRegime = specialTaxationRegime;
                return this;
            }

            /**
             * @param simpleNational
             * @return
             */
            public Builder withSimpleNational(final CommonsNFSeBoolean simpleNational) {
                this.simpleNational = simpleNational;
                return this;
            }

            /**
             * @param culturalPromoter
             * @return
             */
            public Builder withCulturalPromoter(final CommonsNFSeBoolean culturalPromoter) {
                this.culturalPromoter = culturalPromoter;
                return this;
            }

            /**
             * @param status
             * @return
             */
            public Builder withStatus(final CommonsRpsStatus status) {
                this.status = status;
                return this;
            }

            /**
             * @param rpsSubstituted
             * @return
             */
            public Builder withRpsSubstituted(final CommonsRpsIdentifier rpsSubstituted) {
                this.rpsSubstituted = rpsSubstituted;
                return this;
            }

            /**
             * @param service
             * @return
             */
            public Builder withService(final GovbrService service) {
                this.service = service;
                return this;
            }

            /**
             * @param serviceProviderIdentifier
             * @return
             */
            public Builder withServiceProviderIdentifier(final GovbrServiceProvider.GovbrServiceProviderIdentifier serviceProviderIdentifier) {
                this.serviceProviderIdentifier = serviceProviderIdentifier;
                return this;
            }

            /**
             * @param serviceTaker
             * @return
             */
            public Builder withServiceTaker(final GovbrServiceTaker serviceTaker) {
                this.serviceTaker = serviceTaker;
                return this;
            }

            /**
             * @param serviceIntermediaryIdentifier
             * @return
             */
            public Builder withServiceIntermediaryIdentifier(final GovbrServiceIntermediaryIdentifier serviceIntermediaryIdentifier) {
                this.serviceIntermediaryIdentifier = serviceIntermediaryIdentifier;
                return this;
            }

            /**
             * @param construction
             * @return
             */
            public Builder withConstruction(final GovbrServiceConstruction construction) {
                this.construction = construction;
                return this;
            }

            public Info build() {
                final Info entity = new Info(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public Info() {
            identifier = null;
            emissionDate = null;
            natureOperation = null;
            specialTaxationRegime = null;
            simpleNational = null;
            culturalPromoter = null;
            status = null;
            rpsSubstituted = null;
            service = null;
            serviceProviderIdentifier = null;
            serviceTaker = null;
            serviceIntermediaryIdentifier = null;
            construction = null;
        }

        public Info(final Builder builder) {
            identifier = builder.identifier;
            emissionDate = builder.emissionDate;
            natureOperation = builder.natureOperation;
            specialTaxationRegime = builder.specialTaxationRegime;
            simpleNational = builder.simpleNational;
            culturalPromoter = builder.culturalPromoter;
            status = builder.status;
            rpsSubstituted = builder.rpsSubstituted;
            service = builder.service;
            serviceProviderIdentifier = builder.serviceProviderIdentifier;
            serviceTaker = builder.serviceTaker;
            serviceIntermediaryIdentifier = builder.serviceIntermediaryIdentifier;
            construction = builder.construction;
        }

        public CommonsRpsIdentifier getIdentifier() {
            return identifier;
        }

        public String getEmissionDate() {
            return emissionDate;
        }

        public GovbrNatureOperation getNatureOperation() {
            return natureOperation;
        }

        public GovbrSpecialTaxationRegime getSpecialTaxationRegime() {
            return specialTaxationRegime;
        }

        public CommonsNFSeBoolean getSimpleNational() {
            return simpleNational;
        }

        public CommonsNFSeBoolean getCulturalPromoter() {
            return culturalPromoter;
        }

        public CommonsRpsStatus getStatus() {
            return status;
        }

        public CommonsRpsIdentifier getRpsSubstituted() {
            return rpsSubstituted;
        }

        public GovbrService getService() {
            return service;
        }

        public GovbrServiceProvider.GovbrServiceProviderIdentifier getServiceProviderIdentifier() {
            return serviceProviderIdentifier;
        }

        public GovbrServiceTaker getServiceTaker() {
            return serviceTaker;
        }

        public GovbrServiceIntermediaryIdentifier getServiceIntermediaryIdentifier() {
            return serviceIntermediaryIdentifier;
        }

        public GovbrServiceConstruction getConstruction() {
            return construction;
        }

    }

}

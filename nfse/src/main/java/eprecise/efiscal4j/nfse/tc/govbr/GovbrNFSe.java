
package eprecise.efiscal4j.nfse.tc.govbr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.adapters.GovbrNFSeDomainAdapter;
import eprecise.efiscal4j.nfse.domain.comp.ProcessedNFSe;
import eprecise.efiscal4j.nfse.domain.comp.rps.RpsIdentifier;
import eprecise.efiscal4j.nfse.tc.commons.compNfse.CommonsGeneratorOrgan;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrNatureOperation;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrServiceConstruction;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrServiceIntermediaryIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrServiceProvider;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrServiceTaker;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.service.GovbrService;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeValue;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;


@XmlRootElement(name = "Nfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSe extends ProcessedNFSe {

    private static final long serialVersionUID = 1L;
    
    public static final DateFormat EMISSION_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private final @XmlElement(name = "InfNfse") @NotNull GovbrNFSeInfo info;
    
    public static class Builder {

        private GovbrNFSeInfo info;

        /**
         * @param info
         * @return
         */
        public Builder withInfo(final GovbrNFSeInfo info) {
            this.info = info;
            return this;
        }

        public GovbrNFSe build() throws Exception {
            final GovbrNFSe entity = new GovbrNFSe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrNFSe() {
        info = null;
    }

    public GovbrNFSe(final Builder builder) {
        info = builder.info;
    }

    public GovbrNFSeInfo getInfo() {
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
    public Optional<String> getAccessKey() {
        return Optional.empty();
    }

    @Override
    public Date getEmissionDate() {
        return Optional.ofNullable(info).map(i -> i.getEmissionDate()).map(t -> {
            try {
                return GovbrNFSeDomainAdapter.NFSE_DATETIME_FORMAT.parse(t);
            } catch (final ParseException e) {
                try {
                    return EMISSION_DATETIME_FORMAT.parse(t);
                } catch (ParseException e1) {
                    throw new RuntimeException(e1);
                }
            }
        }).orElse(null);
    }

    @Override
    public RpsIdentifier getRpsIdentifier() {
        return Optional.ofNullable(info).map(i -> i.getRpsIdentifier()).orElse(null);
    }

    @Override
    public CommonsGeneratorOrgan getGeneratorOrgan() {
        return Optional.ofNullable(info).map(i -> i.getGeneratorOrgan()).orElse(null);
    }

    @Override
    public String getProviderIm() {
        return Optional.ofNullable(info).map(i -> i.getServiceProvider().getIdentifier().getMunicipalRegistration()).orElse(null);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GovbrNFSeInfo {

        private final @NotNull @XmlElement(name = "Numero") @NFSeNonNegativeInteger @Size(max = 15) String number;

        private final @NotNull @XmlElement(name = "CodigoVerificacao") @Size(min = 1, max = 9) String verificationCode;

        private final @NotNull @XmlElement(name = "DataEmissao") @NFSeDateTimeUTC String emissionDate;

        private final @XmlElement(name = "IdentificacaoRps") @NotNull CommonsRpsIdentifier rpsIdentifier;

        private final @NotNull @XmlElement(name = "DataEmissaoRps") @NFSeDate String rpsEmissionDate;

        private final @XmlElement(name = "NaturezaOperacao") @NotNull GovbrNatureOperation natureOperation;

        private final @XmlElement(name = "RegimeEspecialTributacao") GovbrSpecialTaxationRegime specialTaxationRegime;

        private final @XmlElement(name = "OptanteSimplesNacional") @NotNull CommonsNFSeBoolean simpleNational;

        private final @XmlElement(name = "IncentivadorCultural") @NotNull CommonsNFSeBoolean culturalPromoter;

        private final @NotNull @XmlElement(name = "Competencia") @NFSeDateTimeUTC String competence;

        private final @XmlElement(name = "NfseSubstituida") @NFSeNonNegativeInteger @Size(max = 15) String nfseSubstitutedNumber;

        private final @XmlElement(name = "OutrasInformacoes") @Size(min = 1, max = 255) String otherInformation;

        private final @NotNull @XmlElement(name = "Servico") GovbrService service;

        private final @XmlElement(name = "ValorCredito") @NFSeValue String creditValue;

        private final @XmlElement(name = "PrestadorServico") @NotNull GovbrServiceProvider serviceProvider;

        private final @XmlElement(name = "TomadorServico") @NotNull GovbrServiceTaker serviceTaker;

        private final @XmlElement(name = "IntermediarioServico") GovbrServiceIntermediaryIdentifier serviceIntermediaryIdentifier;

        private final @NotNull @XmlElement(name = "OrgaoGerador") CommonsGeneratorOrgan generatorOrgan;

        private final @XmlElement(name = "ConstrucaoCivil") GovbrServiceConstruction construction;

        public static class Builder {

            private String number;

            private String verificationCode;

            private String emissionDate;

            private CommonsRpsIdentifier rpsIdentifier;

            private String rpsEmissionDate;

            private GovbrNatureOperation natureOperation;

            private GovbrSpecialTaxationRegime specialTaxationRegime;

            private CommonsNFSeBoolean simpleNational;

            private CommonsNFSeBoolean culturalPromoter;

            private String competence;

            private String nfseSubstitutedNumber;

            private String otherInformation;

            private GovbrService service;

            private String creditValue;

            private GovbrServiceProvider serviceProvider;

            private GovbrServiceTaker serviceTaker;

            private GovbrServiceIntermediaryIdentifier serviceIntermediaryIdentifier;

            private CommonsGeneratorOrgan generatorOrgan;

            private GovbrServiceConstruction construction;

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
             * @param rpsIdentifier
             * @return
             */
            public Builder withRpsIdentifier(final CommonsRpsIdentifier rpsIdentifier) {
                this.rpsIdentifier = rpsIdentifier;
                return this;
            }

            /**
             * @param rpsEmissionDate
             * @return
             */
            public Builder withRpsEmissionDate(final String rpsEmissionDate) {
                this.rpsEmissionDate = rpsEmissionDate;
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
             * @param competence
             * @return
             */
            public Builder withCompetence(final String competence) {
                this.competence = competence;
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
             * @param service
             * @return
             */
            public Builder withService(final GovbrService service) {
                this.service = service;
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
             * @param serviceProvider
             * @return
             */
            public Builder withServiceProvider(final GovbrServiceProvider serviceProvider) {
                this.serviceProvider = serviceProvider;
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
             * @param generatorOrgan
             * @return
             */
            public Builder withGeneratorOrgan(final CommonsGeneratorOrgan generatorOrgan) {
                this.generatorOrgan = generatorOrgan;
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

            public GovbrNFSeInfo build() throws Exception {
                final GovbrNFSeInfo entity = new GovbrNFSeInfo(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public GovbrNFSeInfo() {

            number = null;
            verificationCode = null;
            emissionDate = null;
            rpsIdentifier = null;
            rpsEmissionDate = null;
            natureOperation = null;
            specialTaxationRegime = null;
            simpleNational = null;
            culturalPromoter = null;
            competence = null;
            nfseSubstitutedNumber = null;
            otherInformation = null;
            service = null;
            creditValue = null;
            serviceProvider = null;
            serviceTaker = null;
            serviceIntermediaryIdentifier = null;
            generatorOrgan = null;
            construction = null;
        }

        public GovbrNFSeInfo(final Builder builder) {
            number = builder.number;
            verificationCode = builder.verificationCode;
            emissionDate = builder.emissionDate;
            rpsIdentifier = builder.rpsIdentifier;
            rpsEmissionDate = builder.rpsEmissionDate;
            natureOperation = builder.natureOperation;
            specialTaxationRegime = builder.specialTaxationRegime;
            simpleNational = builder.simpleNational;
            culturalPromoter = builder.culturalPromoter;
            competence = builder.competence;
            nfseSubstitutedNumber = builder.nfseSubstitutedNumber;
            otherInformation = builder.otherInformation;
            service = builder.service;
            creditValue = builder.creditValue;
            serviceProvider = builder.serviceProvider;
            serviceTaker = builder.serviceTaker;
            serviceIntermediaryIdentifier = builder.serviceIntermediaryIdentifier;
            generatorOrgan = builder.generatorOrgan;
            construction = builder.construction;
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

        public CommonsRpsIdentifier getRpsIdentifier() {
            return rpsIdentifier;
        }

        public String getRpsEmissionDate() {
            return rpsEmissionDate;
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

        public String getCompetence() {
            return competence;
        }

        public String getNfseSubstitutedNumber() {
            return nfseSubstitutedNumber;
        }

        public String getOtherInformation() {
            return otherInformation;
        }

        public GovbrService getService() {
            return service;
        }

        public String getCreditValue() {
            return creditValue;
        }

        public GovbrServiceProvider getServiceProvider() {
            return serviceProvider;
        }

        public GovbrServiceTaker getServiceTaker() {
            return serviceTaker;
        }

        public GovbrServiceIntermediaryIdentifier getServiceIntermediaryIdentifier() {
            return serviceIntermediaryIdentifier;
        }

        public CommonsGeneratorOrgan getGeneratorOrgan() {
            return generatorOrgan;
        }

        public GovbrServiceConstruction getConstruction() {
            return construction;
        }

    }

}

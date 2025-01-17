
package eprecise.efiscal4j.nfe.v310.person;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.CRT;
import eprecise.efiscal4j.nfe.v310.adapter.EmitterAdapter;
import eprecise.efiscal4j.nfe.v310.address.Address;
import eprecise.efiscal4j.nfe.v310.types.NFeString;
import eprecise.efiscal4j.nfe.v310.validation.CepEmitterValidation;


/**
 * Identificação do emitente
 *
 * @author Felipe Bueno
 *
 */
@CepEmitterValidation
@XmlJavaTypeAdapter(EmitterAdapter.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class Emitter implements Serializable {

    private static final long serialVersionUID = 1L;

    private @NotNull @Valid final AbstractDocuments documents;

    private @XmlElement(name = "xFant") @Size(min = 1, max = 60) @NFeString final String fancyName;

    private @XmlElement(name = "enderEmit") @NotNull final Address adress;

    private @XmlElement(name = "IE") @NotNull @Size(max = 14) @Pattern(regexp = "[0-9]{2,14}|ISENTO") final String stateRegistration;

    private @XmlElement(name = "IEST") @Size(max = 14) @Pattern(regexp = "[0-9]{2,14}") final String stateRegistrationST;

    private @XmlElement(name = "IM") @Size(min = 1, max = 15) @NFeString final String municipalRegistration;

    private @XmlElement(name = "CNAE") @Pattern(regexp = "[0-9]{7}") final String cnae;

    private @XmlElement(name = "CRT") @NotNull final CRT crt;

    public static class Builder {

        private AbstractDocuments documents;

        private String fancyName;

        private Address adress;

        private String stateRegistration;

        private String stateRegistrationST;

        private String municipalRegistration;

        private String cnae;

        private CRT crt;

        public Builder withFancyName(final String fancyName) {
            this.fancyName = Optional.ofNullable(fancyName).map(String::trim).orElse(null);
            return this;
        }

        public Builder withAdress(final Address adress) {
            this.adress = adress;
            return this;
        }

        public Builder withStateRegistration(final String stateRegistration) {
            this.stateRegistration = stateRegistration;
            Optional.ofNullable(this.documents).ifPresent(d -> d.setStateRegistration(stateRegistration));
            return this;
        }

        public Builder withStateRegistrationST(final String stateRegistrationST) {
            this.stateRegistrationST = stateRegistrationST;
            return this;
        }

        public Builder withMunicipalRegistration(final String municipalRegistration) {
            this.municipalRegistration = municipalRegistration;
            return this;
        }

        public Builder withCnae(final String cnae) {
            this.cnae = cnae;
            return this;
        }

        public Builder withCrt(final CRT crt) {
            this.crt = crt;
            return this;
        }

        public NaturalPersonBuilder asNaturalPerson() {
            return new NaturalPersonBuilder(this);
        }

        public LegalEntityBuilder asLegalEntity() {
            return new LegalEntityBuilder(this);
        }

        public Emitter build() {
            final Emitter entity = new Emitter(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        protected AbstractDocuments getDocuments() {
            return this.documents;
        }
    }

    public static class LegalEntityBuilder extends Builder {

        protected LegalEntityBuilder(final Builder builder) {
            super.documents = new LegalEntityDocuments();
        }

        public LegalEntityBuilder withCorporateName(final String corporateName) {
            this.getDocuments().setCorporateName(corporateName);
            return this;
        }

        public LegalEntityBuilder withCnpj(final String cnpj) {
            this.getDocuments().setCnpj(cnpj);
            return this;
        }

        @Override
        public LegalEntityBuilder withStateRegistration(final String stateRegistration) {
            return (LegalEntityBuilder) super.withStateRegistration(stateRegistration);
        }

        @Override
        public LegalEntityBuilder withMunicipalRegistration(final String municipalRegistration) {
            return (LegalEntityBuilder) super.withMunicipalRegistration(municipalRegistration);
        }

        @Override
        public LegalEntityBuilder asLegalEntity() {
            throw new UnsupportedOperationException();
        }

        @Override
        public NaturalPersonBuilder asNaturalPerson() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Emitter build() {
            final Emitter entity = new Emitter(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        @Override
        protected LegalEntityDocuments getDocuments() {
            return (LegalEntityDocuments) super.documents;
        }
    }

    public static class NaturalPersonBuilder extends Builder {

        protected NaturalPersonBuilder(final Builder builder) {
            super.documents = new NaturalPersonDocuments();
        }

        public NaturalPersonBuilder withCpf(final String cpf) {
            this.getDocuments().setCpf(cpf);
            return this;
        }

        public NaturalPersonBuilder withName(final String name) {
            this.getDocuments().setName(name);
            return this;
        }

        @Override
        public NaturalPersonBuilder withStateRegistration(final String stateRegistration) {
            return (NaturalPersonBuilder) super.withStateRegistration(stateRegistration);
        }

        @Override
        public NaturalPersonBuilder withMunicipalRegistration(final String municipalRegistration) {
            return (NaturalPersonBuilder) super.withMunicipalRegistration(municipalRegistration);
        }

        @Override
        public LegalEntityBuilder asLegalEntity() {
            throw new UnsupportedOperationException();
        }

        @Override
        public NaturalPersonBuilder asNaturalPerson() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Emitter build() {
            final Emitter entity = new Emitter(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        @Override
        protected NaturalPersonDocuments getDocuments() {
            return (NaturalPersonDocuments) super.documents;
        }
    }

    public Emitter() {
        this.documents = null;
        this.fancyName = null;
        this.adress = null;
        this.stateRegistration = null;
        this.stateRegistrationST = null;
        this.municipalRegistration = null;
        this.crt = null;
        this.cnae = null;
    }

    public Emitter(final Builder builder) {
        this.documents = builder.documents;
        this.fancyName = builder.fancyName;
        this.adress = builder.adress;
        this.stateRegistration = builder.stateRegistration;
        this.stateRegistrationST = builder.stateRegistrationST;
        this.municipalRegistration = builder.municipalRegistration;
        this.crt = builder.crt;
        this.cnae = builder.cnae;
    }

    public AbstractDocuments getDocuments() {
        return this.documents;
    }

    public Address getAdress() {
        return this.adress;
    }

    public String getFancyName() {
        return this.fancyName;
    }

    public String getStateRegistration() {
        return this.stateRegistration;
    }

    public String getStateRegistrationST() {
        return this.stateRegistrationST;
    }

    public String getMunicipalRegistration() {
        return this.municipalRegistration;
    }

    public CRT getCrt() {
        return this.crt;
    }

    public String getCnae() {
        return this.cnae;
    }

}


package eprecise.efiscal4j.nfe.person;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.StateRegistrationReceiverIndicator;
import eprecise.efiscal4j.nfe.adapter.ReceiverAdapter;
import eprecise.efiscal4j.nfe.address.Address;
import eprecise.efiscal4j.nfe.types.NFeString;
import eprecise.efiscal4j.nfe.validation.StateRegistrationValidation;


/**
 * Identificação do Destinatário
 * 
 * @author Felipe Bueno
 * 
 */
@StateRegistrationValidation
@XmlJavaTypeAdapter(ReceiverAdapter.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class Receiver implements Serializable {

    private static final long serialVersionUID = 1L;

    private @NotNull @Valid final AbstractDocuments documents;

    private @XmlElement(name = "enderDest") Address adress;

    private @XmlElement(name = "indIEDest") @NotNull final StateRegistrationReceiverIndicator stateRegistrationReceiverIndicator;

    private @XmlElement(name = "IM") @Size(min = 1, max = 15) @NFeString String municipalRegistration;

    private @XmlElement(name = "email") @Size(min = 1, max = 60) @NFeString String email;

    public static class Builder {

        private AbstractDocuments documents;

        private Address adress;

        private StateRegistrationReceiverIndicator stateRegistrationReceiverIndicator;

        private String municipalRegistration;

        private String email;

        public Builder withAdress(Address adress) {
            this.adress = adress;
            return this;
        }

        protected Builder withStateRegistration(String stateRegistration) {
            this.documents.setStateRegistration(stateRegistration);
            return this;
        }

        protected Builder withMunicipalRegistration(String municipalRegistration) {
            this.municipalRegistration = municipalRegistration;
            return this;
        }

        public Builder withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator stateRegistrationReceiverIndicator) {
            this.stateRegistrationReceiverIndicator = stateRegistrationReceiverIndicator;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public NaturalPersonBuilder asNaturalPerson() {
            return new NaturalPersonBuilder(this);
        }

        public LegalEntityBuilder asLegalEntity() {
            return new LegalEntityBuilder(this);
        }

        public ForeignPersonBuilder asForeignPerson() {
            return new ForeignPersonBuilder(this);
        }

        public Receiver build() {
            final Receiver entity = new Receiver(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        protected AbstractDocuments getDocuments() {
            return this.documents;
        }
    }

    public static class LegalEntityBuilder extends Builder {

        protected LegalEntityBuilder(Builder builder) {
            super.documents = new LegalEntityDocuments();
        }

        public LegalEntityBuilder withCorporateName(String corporateName) {
            this.getDocuments().setCorporateName(corporateName);
            return this;
        }

        public LegalEntityBuilder withCnpj(String cnpj) {
            this.getDocuments().setCnpj(cnpj);
            return this;
        }

        @Override
        public LegalEntityBuilder withStateRegistration(String stateRegistration) {
            return (LegalEntityBuilder) super.withStateRegistration(stateRegistration);
        }

        @Override
        public LegalEntityBuilder withMunicipalRegistration(String municipalRegistration) {
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
        public ForeignPersonBuilder asForeignPerson() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Receiver build() {
            final Receiver entity = new Receiver(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        @Override
        protected LegalEntityDocuments getDocuments() {
            return (LegalEntityDocuments) super.documents;
        }
    }

    public static class NaturalPersonBuilder extends Builder {

        protected NaturalPersonBuilder(Builder builder) {
            super.documents = new NaturalPersonDocuments();
        }

        public NaturalPersonBuilder withCpf(String cpf) {
            this.getDocuments().setCpf(cpf);
            return this;
        }

        public NaturalPersonBuilder withName(String name) {
            this.getDocuments().setName(name);
            return this;
        }

        @Override
        public NaturalPersonBuilder withStateRegistration(String stateRegistration) {
            return (NaturalPersonBuilder) super.withStateRegistration(stateRegistration);
        }

        @Override
        public NaturalPersonBuilder withMunicipalRegistration(String municipalRegistration) {
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
        public ForeignPersonBuilder asForeignPerson() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Receiver build() {
            final Receiver entity = new Receiver(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        @Override
        protected NaturalPersonDocuments getDocuments() {
            return (NaturalPersonDocuments) super.documents;
        }
    }

    public static class ForeignPersonBuilder extends Builder {

        protected ForeignPersonBuilder(Builder builder) {
            super.documents = new ForeignPersonDocuments();
        }

        public ForeignPersonBuilder withForeignId(String foreignId) {
            this.getDocuments().setForeignId(foreignId);
            return this;
        }

        public ForeignPersonBuilder withCorporateName(String corporateName) {
            this.getDocuments().setCorporateName(corporateName);
            return this;
        }

        @Override
        public ForeignPersonBuilder withStateRegistration(String stateRegistration) {
            return (ForeignPersonBuilder) super.withStateRegistration(stateRegistration);
        }

        @Override
        public ForeignPersonBuilder withMunicipalRegistration(String municipalRegistration) {
            throw new UnsupportedOperationException();
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
        public ForeignPersonBuilder asForeignPerson() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Receiver build() {
            final Receiver entity = new Receiver(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        @Override
        protected ForeignPersonDocuments getDocuments() {
            return (ForeignPersonDocuments) super.documents;
        }
    }

    public Receiver() {
        this.documents = null;
        this.stateRegistrationReceiverIndicator = null;
    }

    public Receiver(Builder builder) {
        this.documents = builder.documents;
        this.adress = builder.adress;
        this.stateRegistrationReceiverIndicator = builder.stateRegistrationReceiverIndicator;
        this.municipalRegistration = builder.municipalRegistration;
        this.email = builder.email;
    }

    public AbstractDocuments getDocuments() {
        return this.documents;
    }

    public Address getAdress() {
        return this.adress;
    }

    public StateRegistrationReceiverIndicator getStateRegistrationReceiverIndicator() {
        return this.stateRegistrationReceiverIndicator;
    }

    public String getMunicipalRegistration() {
        return this.municipalRegistration;
    }

    public String getEmail() {
        return this.email;
    }

}


package eprecise.efiscal4j.nfe.v310.transport;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.address.City;
import eprecise.efiscal4j.nfe.v310.person.AbstractDocuments;
import eprecise.efiscal4j.nfe.v310.person.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.v310.person.NaturalPersonDocuments;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Dados do transportador
 * 
 * @author Felipe Bueno
 * 
 */
@XmlJavaTypeAdapter(ConveyorAdapter.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class Conveyor implements Serializable {

	private static final long serialVersionUID = 1L;

	private @NotNull @Valid final AbstractDocuments documents;

	private @XmlElement(name = "xEnder") @Size(min = 1, max = 60) @NFeString final String fullAddress;

	private @NotNull final City city;

	public static class Builder {

		private AbstractDocuments documents;

		private String fullAddress;

		private City city;

		protected AbstractDocuments getDocuments() {
			return this.documents;
		}

		public Builder withFullAddress(String fullAddress) {
			this.fullAddress = fullAddress;
			return this;
		}

		public Builder withCity(City city) {
			this.city = city;
			return this;
		}

		protected Builder withStateRegistration(String stateRegistration) {
			this.documents.setStateRegistration(stateRegistration);
			return this;
		}

		public NaturalPersonBuilder asNaturalPerson() {
			return new NaturalPersonBuilder(this);
		}

		public LegalEntityBuilder asLegalEntity() {
			return new LegalEntityBuilder(this);
		}

		public Conveyor build() {
			Conveyor entity = new Conveyor(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
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
		public LegalEntityBuilder asLegalEntity() {
			throw new UnsupportedOperationException();
		}

		@Override
		public NaturalPersonBuilder asNaturalPerson() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Conveyor build() {
			Conveyor entity = new Conveyor(this);
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
		public LegalEntityBuilder asLegalEntity() {
			throw new UnsupportedOperationException();
		}

		@Override
		public NaturalPersonBuilder asNaturalPerson() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Conveyor build() {
			Conveyor entity = new Conveyor(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}

		@Override
		protected NaturalPersonDocuments getDocuments() {
			return (NaturalPersonDocuments) super.documents;
		}
	}

	public Conveyor() {
		this.documents = null;
		this.fullAddress = null;
		this.city = null;
	}

	public Conveyor(Builder builder) {
		this.documents = builder.documents;
		this.fullAddress = builder.fullAddress;
		this.city = builder.city;
	}

	public AbstractDocuments getDocuments() {
		return this.documents;
	}

	public String getFullAddress() {
		return this.fullAddress;
	}

	public City getCity() {
		return this.city;
	}

}

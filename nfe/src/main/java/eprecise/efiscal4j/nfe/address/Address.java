
package eprecise.efiscal4j.nfe.address;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Dados de endereço
 * 
 * @author Felipe Bueno
 * 
 */
@XmlJavaTypeAdapter(AdressAdapter.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "xLgr") @NotNull @Size(min = 2, max = 60) final String street;

	private @XmlElement(name = "nro") @NotNull @Size(min = 1, max = 60) final String number;

	private @XmlElement(name = "xCpl") @Size(min = 1, max = 60) String complement;

	private @XmlElement(name = "xBairro") @NotNull @Size(min = 2, max = 60) final String district;

	private @XmlElement(name = "CEP") @NotNull @Pattern(regexp = "[0-9]{8}") final String cep;

	private @NotNull final City city;

	private @XmlElement(name = "fone") @Pattern(regexp = "[0-9]{6,14}") String phone;

	public static class Builder {

		private String street;

		private String number;

		private String complement;

		private String district;

		private String cep;

		private City city;

		private String phone;

		public Builder withStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder withNumber(String number) {
			this.number = number;
			return this;
		}

		public Builder withComplement(String complement) {
			this.complement = complement;
			return this;
		}

		public Builder withDistrict(String district) {
			this.district = district;
			return this;
		}

		public Builder withCep(String cep) {
			this.cep = cep;
			return this;
		}

		public Builder withCity(City city) {
			this.city = city;
			return this;
		}

		/**
		 * Telefone, preencher com Código DDD + número do telefone , nas operações com exterior é permtido informar o código do país + código da localidade + número do telefone
		 * 
		 * @param phone
		 * @return
		 */
		public Builder withPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Address build() {
			Address entity = new Address(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	Address() {
		this.street = null;
		this.number = null;
		this.district = null;
		this.cep = null;
		this.city = null;
	}

	private Address(Builder builder) {
		this.street = builder.street;
		this.number = builder.number;
		this.complement = builder.complement;
		this.district = builder.district;
		this.cep = builder.cep;
		this.city = builder.city;
		this.phone = builder.phone;
	}

	public City getCity() {
		return this.city;
	}

	public String getStreet() {
		return this.street;
	}

	public String getDistrict() {
		return this.district;
	}

	public String getNumber() {
		return this.number;
	}

	public String getComplement() {
		return this.complement;
	}

	public String getCep() {
		return this.cep;
	}

	public String getPhone() {
		return this.phone;
	}
}

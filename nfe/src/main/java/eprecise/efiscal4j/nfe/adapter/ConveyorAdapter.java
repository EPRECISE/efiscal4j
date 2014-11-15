
package eprecise.efiscal4j.nfe.adapter;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.nfe.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.NaturalPersonDocuments;
import eprecise.efiscal4j.nfe.address.City;
import eprecise.efiscal4j.nfe.address.UF;
import eprecise.efiscal4j.nfe.transport.Conveyor;


public class ConveyorAdapter extends XmlAdapter<ConveyorAdapter.AdaptedConveyor, Conveyor> {

	@Override
	public Conveyor unmarshal(AdaptedConveyor adaptedConveyor) throws Exception {
		Conveyor conveyor;

		//@formatter:off
        if (!adaptedConveyor.getAdaptedCpf().isEmpty()) {
            conveyor = new Conveyor.Builder()
                      .asNaturalPerson()
                      .withCpf(adaptedConveyor.getAdaptedCpf())    
                      .withName(adaptedConveyor.getAdaptedName())
                      .withStateRegistration(adaptedConveyor.getAdaptedStateRegistration())
                      .withFullAddress(adaptedConveyor.getAdaptedFullAddress())
                      .withCity(new City.Builder()
                               .withIbgeCode("0")
                               .withDescription(adaptedConveyor.getAdaptedCityDescription())
                               .withUF(adaptedConveyor.getAdaptedUF())
                               .build())
                      .build();                           
        }else{
            conveyor = new Conveyor.Builder()
                      .asLegalEntity()
                      .withCnpj(adaptedConveyor.getAdaptedCnpj())
                      .withCorporateName(adaptedConveyor.getAdaptedName())
                      .withStateRegistration(adaptedConveyor.getAdaptedStateRegistration())                                                                                                                                 
                      .withFullAddress(adaptedConveyor.getAdaptedFullAddress())
                      .withCity(new City.Builder()
                               .withIbgeCode("1234567")
                               .withDescription(adaptedConveyor.getAdaptedCityDescription())
                               .withUF(adaptedConveyor.getAdaptedUF())
                               .build())
                      .build();                      
        }

        //@formatter:on
		return conveyor;
	}

	@Override
	public AdaptedConveyor marshal(Conveyor conveyor) throws Exception {
		//@formatter:off
		AdaptedConveyor adaptedConveyor = null;
       
        adaptedConveyor = new AdaptedConveyor(conveyor.getDocuments().getStateRegistration()                                                                   
                ,conveyor.getFullAddress()
                ,conveyor.getCity().getDescription()
                ,conveyor.getCity().getUf());
        
        if (conveyor.getDocuments() instanceof NaturalPersonDocuments) {
            adaptedConveyor.setAdaptedCpf(((NaturalPersonDocuments)conveyor.getDocuments()).getCpf());
            adaptedConveyor.setAdaptedName(((NaturalPersonDocuments)conveyor.getDocuments()).getName());
        } else if (conveyor.getDocuments() instanceof LegalEntityDocuments) {
            adaptedConveyor.setAdaptedCnpj(((LegalEntityDocuments)conveyor.getDocuments()).getCnpj());
            adaptedConveyor.setAdaptedName(((LegalEntityDocuments)conveyor.getDocuments()).getCorporateName());
        }        
        //@formatter:on       

		return adaptedConveyor;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	protected static class AdaptedConveyor {

		private @XmlElement(name = "CNPJ") String cnpj;

		private @XmlElement(name = "CPF") String cpf;

		private @XmlElement(name = "xNome") String name;

		private @XmlElement(name = "IE") String stateRegistration;

		private @XmlElement(name = "xEnder") @Size(min = 1, max = 60) String fullAddress;

		private @XmlElement(name = "xMun") @Size(min = 1, max = 60) String cityDescription;

		private @XmlElement(name = "UF") UF uf;

		public AdaptedConveyor(String stateRegistration, String fullAddress, String cityDescription, UF uf) {
			this.stateRegistration = stateRegistration;
			this.fullAddress = fullAddress;
			this.cityDescription = cityDescription;
			this.uf = uf;
		}

		public String getAdaptedName() {
			return this.name;
		}

		public String getAdaptedCnpj() {
			return this.cnpj;
		}

		public String getAdaptedCpf() {
			return this.cpf;
		}

		public String getAdaptedStateRegistration() {
			return this.stateRegistration;
		}

		public String getAdaptedFullAddress() {
			return this.fullAddress;
		}

		public String getAdaptedCityDescription() {
			return this.cityDescription;
		}

		public UF getAdaptedUF() {
			return this.uf;
		}

		public void setAdaptedName(String name) {
			this.name = name;
		}

		public void setAdaptedCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

		public void setAdaptedCpf(String cpf) {
			this.cpf = cpf;
		}

		public void setAdaptedStateRegistration(String stateRegistration) {
			this.stateRegistration = stateRegistration;
		}

		public void setAdaptedFullAddress(String fullAddress) {
			this.fullAddress = fullAddress;
		}

		public void setAdaptedCityDescription(String cityDescription) {
			this.cityDescription = cityDescription;
		}

		public void setAdaptedUF(UF uf) {
			this.uf = uf;
		}
	}
}

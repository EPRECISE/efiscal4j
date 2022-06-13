
package eprecise.efiscal4j.nfe.v400.transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.v400.address.City;
import eprecise.efiscal4j.nfe.v400.person.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.v400.person.NaturalPersonDocuments;


public class ConveyorAdapter extends XmlAdapter<ConveyorAdapter.AdaptedConveyor, Conveyor> {

    @Override
    public Conveyor unmarshal(final AdaptedConveyor adaptedConveyor) throws Exception {
        Conveyor conveyor = null;
        try {

            //@formatter:off
            if (adaptedConveyor.getAdaptedCpf() != null) {
                conveyor = new Conveyor.Builder()
                          .asNaturalPerson()
                          .withCpf(adaptedConveyor.getAdaptedCpf())    
                          .withName(adaptedConveyor.getAdaptedName())
                          .withStateRegistration(adaptedConveyor.getAdaptedStateRegistration())
                          .withFullAddress(adaptedConveyor.getAdaptedFullAddress())
                          .withCity(adaptedConveyor.hasCityAndUf() ? new City.Builder()
                                   .withIbgeCode("0000000")
                                   .withDescription(adaptedConveyor.getAdaptedCityDescription())
                                   .withUF(UF.findByAcronym(adaptedConveyor.getAdaptedUF()))
                                   .build() : null)
                          .build();                           
            }else{
                conveyor = new Conveyor.Builder()
                          .asLegalEntity()
                          .withCnpj(adaptedConveyor.getAdaptedCnpj())
                          .withCorporateName(adaptedConveyor.getAdaptedName())
                          .withStateRegistration(adaptedConveyor.getAdaptedStateRegistration())                                                                                                                                 
                          .withFullAddress(adaptedConveyor.getAdaptedFullAddress())
                          .withCity(adaptedConveyor.hasCityAndUf() ? new City.Builder()
                                   .withIbgeCode("0000000")
                                   .withDescription(adaptedConveyor.getAdaptedCityDescription())
                                   .withUF(UF.findByAcronym(adaptedConveyor.getAdaptedUF()))
                                   .build() : null)
                          .build();                      
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
   

        //@formatter:on
        return conveyor;
    }

    @Override
    public AdaptedConveyor marshal(final Conveyor conveyor) throws Exception {
        //@formatter:off
		AdaptedConveyor adaptedConveyor = null;       	
		
		if(conveyor == null){
		    return null;		    
		}
		
        adaptedConveyor = new AdaptedConveyor(
                (conveyor.getDocuments() != null ? conveyor.getDocuments().getStateRegistration() : null)                                                                   
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

        private @XmlElement(name = "xEnder") String fullAddress;

        private @XmlElement(name = "xMun") String cityDescription;

        private @XmlElement(name = "UF") String uf;

        public AdaptedConveyor() {
            this.stateRegistration = null;
            this.fullAddress = null;
            this.cityDescription = null;
            this.uf = null;
        }

        public AdaptedConveyor(final String stateRegistration, final String fullAddress, final String cityDescription, final UF uf) {
            this.stateRegistration = stateRegistration;
            this.fullAddress = fullAddress;
            this.cityDescription = cityDescription;
            this.uf = uf != null ? uf.getAcronym() : null;
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

        public String getAdaptedUF() {
            return this.uf;
        }
        
        public boolean hasCityAndUf() {
        	return !StringUtils.isEmpty(this.cityDescription) && !StringUtils.isEmpty(this.uf);
        }

        public void setAdaptedName(final String name) {
            this.name = name;
        }

        public void setAdaptedCnpj(final String cnpj) {
            this.cnpj = cnpj;
        }

        public void setAdaptedCpf(final String cpf) {
            this.cpf = cpf;
        }

        public void setAdaptedStateRegistration(final String stateRegistration) {
            this.stateRegistration = stateRegistration;
        }

        public void setAdaptedFullAddress(final String fullAddress) {
            this.fullAddress = fullAddress;
        }

        public void setAdaptedCityDescription(final String cityDescription) {
            this.cityDescription = cityDescription;
        }

        public void setAdaptedUF(final UF uf) {
            this.uf = uf != null ? uf.getAcronym() : null;
        }
    }
}

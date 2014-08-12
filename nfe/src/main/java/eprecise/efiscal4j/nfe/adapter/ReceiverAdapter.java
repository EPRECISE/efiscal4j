
package eprecise.efiscal4j.nfe.adapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.nfe.Adress;
import eprecise.efiscal4j.nfe.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.NaturalPersonDocuments;
import eprecise.efiscal4j.nfe.Receiver;
import eprecise.efiscal4j.nfe.StateRegistrationReceiverIndicator;


public class ReceiverAdapter extends XmlAdapter<ReceiverAdapter.AdaptedReceiver, Receiver> {

    @Override
    public Receiver unmarshal(AdaptedReceiver adaptedReceiver) throws Exception {
        Receiver receiver;

        //@formatter:off
        if (!adaptedReceiver.getAdaptedCpf().isEmpty()) {
            receiver = new Receiver.Builder()
                           .asNaturalPerson()
                           .withCpf(adaptedReceiver.getAdaptedCpf())    
                           .withName(adaptedReceiver.getAdaptedName())
                           .withStateRegistration(adaptedReceiver.getAdaptedStateRegistration())
                           .withMunicipalRegistration(adaptedReceiver.getAdaptedMunicipalRegistration())
                           .withStateRegistrationReceiverIndicator(adaptedReceiver.getAdaptedStateRegistrationReceiverIndicator())
                           .withEmail(adaptedReceiver.getAdaptedEmail())
                           .withAdress(adaptedReceiver.getAdaptedAdress())
                           .build();                           
        }else{
            receiver = new Receiver.Builder()
                           .asLegalEntity()
                           .withCnpj(adaptedReceiver.getAdaptedCnpj())
                           .withCorporateName(adaptedReceiver.getAdaptedName())
                           .withStateRegistration(adaptedReceiver.getAdaptedStateRegistration())
                           .withMunicipalRegistration(adaptedReceiver.getAdaptedMunicipalRegistration())                                                    
                           .withStateRegistrationReceiverIndicator(adaptedReceiver.getAdaptedStateRegistrationReceiverIndicator())
                           .withEmail(adaptedReceiver.getAdaptedEmail())
                           .withAdress(adaptedReceiver.getAdaptedAdress())
                           .build();                      
        }

        //@formatter:on
        return receiver;
    }

    @Override
    public AdaptedReceiver marshal(Receiver receiver) throws Exception {
        //@formatter:off
        AdaptedReceiver adaptedReceiver = null;
       
        adaptedReceiver = new AdaptedReceiver(receiver.getDocuments().getStateRegistration()                                                   
                ,receiver.getDocuments().getMunicipalRegistration()
                ,receiver.getAdress()
                ,receiver.getStateRegistrationReceiverIndicator()
                ,receiver.getEmail());
        
        if (receiver.getDocuments() instanceof NaturalPersonDocuments) {
            adaptedReceiver.setAdaptedCpf(((NaturalPersonDocuments)receiver.getDocuments()).getCpf());
            adaptedReceiver.setAdaptedName(((NaturalPersonDocuments)receiver.getDocuments()).getName());
        } else if (receiver.getDocuments() instanceof LegalEntityDocuments) {
            adaptedReceiver.setAdaptedCnpj(((LegalEntityDocuments)receiver.getDocuments()).getCnpj());
            adaptedReceiver.setAdaptedName(((LegalEntityDocuments)receiver.getDocuments()).getCorporateName());
        }        
        //@formatter:on       

        return adaptedReceiver;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    protected static class AdaptedReceiver {

        private @XmlElement(name = "CNPJ") String cnpj;

        private @XmlElement(name = "CPF") String cpf;

        private @XmlElement(name = "xNome") String name;

        private @XmlElement(name = "IE") final String stateRegistration;

        private @XmlElement(name = "IM") final String municipalRegistration;

        private @XmlElement(name = "enderDest") final Adress adress;

        private @XmlElement(name = "indIEDest") final StateRegistrationReceiverIndicator stateRegistrationReceiverIndicator;

        private @XmlElement(name = "email") final String email;

        public AdaptedReceiver(String stateRegistration, String municipalRegistration, Adress adress, StateRegistrationReceiverIndicator stateRegistrationReceiverIndicator, String email) {
            this.stateRegistration = stateRegistration;
            this.municipalRegistration = municipalRegistration;
            this.adress = adress;
            this.stateRegistrationReceiverIndicator = stateRegistrationReceiverIndicator;
            this.email = email;
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

        public String getAdaptedMunicipalRegistration() {
            return this.municipalRegistration;
        }

        public Adress getAdaptedAdress() {
            return this.adress;
        }

        public StateRegistrationReceiverIndicator getAdaptedStateRegistrationReceiverIndicator() {
            return this.stateRegistrationReceiverIndicator;
        }

        public String getAdaptedEmail() {
            return this.email;
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

    }

}

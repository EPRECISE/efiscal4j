
package eprecise.efiscal4j.nfe.v310.adapter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.nfe.v310.StateRegistrationReceiverIndicator;
import eprecise.efiscal4j.nfe.v310.address.Address;
import eprecise.efiscal4j.nfe.v310.person.ForeignPersonDocuments;
import eprecise.efiscal4j.nfe.v310.person.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.v310.person.NaturalPersonDocuments;
import eprecise.efiscal4j.nfe.v310.person.Receiver;


public class ReceiverAdapter extends XmlAdapter<ReceiverAdapter.AdaptedReceiver, Receiver> {

    @Override
    public Receiver unmarshal(AdaptedReceiver adaptedReceiver) throws Exception {
        if (adaptedReceiver == null) {
            return null;
        }

        Receiver receiver;

        //@formatter:off
        if (adaptedReceiver.getAdaptedCpf() != null) {
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
        }else if(adaptedReceiver.getAdaptedCnpj() != null){
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
        }else if(adaptedReceiver.getAdaptedForeignId() != null){
            receiver = new Receiver.Builder()
            .asForeignPerson()
            .withForeignId(adaptedReceiver.getAdaptedForeignId())
            .withCorporateName(adaptedReceiver.getAdaptedName())
            .withStateRegistration(adaptedReceiver.getAdaptedStateRegistration())            
            .withStateRegistrationReceiverIndicator(adaptedReceiver.getAdaptedStateRegistrationReceiverIndicator())
            .withEmail(adaptedReceiver.getAdaptedEmail())
            .withAdress(adaptedReceiver.getAdaptedAdress())
            .build();
        }else{
            throw new UnsupportedOperationException();            
        }
        //@formatter:on

        return receiver;
    }

    @Override
    public AdaptedReceiver marshal(Receiver receiver) throws Exception {
        if (receiver == null) {
            return null;
        }

        AdaptedReceiver adaptedReceiver = null;
        //@formatter:off
        adaptedReceiver = new AdaptedReceiver(receiver.getDocuments().getStateRegistration()
                ,receiver.getMunicipalRegistration()
                ,receiver.getAdress()
                ,receiver.getStateRegistrationReceiverIndicator()
                ,receiver.getEmail());

        if (receiver.getDocuments() instanceof NaturalPersonDocuments) {
            adaptedReceiver.setAdaptedCpf(((NaturalPersonDocuments)receiver.getDocuments()).getCpf());
            adaptedReceiver.setAdaptedName(((NaturalPersonDocuments)receiver.getDocuments()).getName());
        } else if (receiver.getDocuments() instanceof LegalEntityDocuments) {
            adaptedReceiver.setAdaptedCnpj(((LegalEntityDocuments)receiver.getDocuments()).getCnpj());
            adaptedReceiver.setAdaptedName(((LegalEntityDocuments)receiver.getDocuments()).getCorporateName());
        } else if (receiver.getDocuments() instanceof ForeignPersonDocuments) {
            adaptedReceiver.setAdaptedForeignId(((ForeignPersonDocuments)receiver.getDocuments()).getForeignId());
            adaptedReceiver.setAdaptedName(((ForeignPersonDocuments)receiver.getDocuments()).getCorporateName());
        }else{
            throw new UnsupportedOperationException();            
        }        
        //@formatter:on

        return adaptedReceiver;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = { "cnpj", "cpf", "foreignId", "name", "adress", "stateRegistrationReceiverIndicator", "stateRegistration", "municipalRegistration", "email" })
    protected static class AdaptedReceiver implements Serializable {

        private static final long serialVersionUID = 1L;

        private @XmlElement(name = "CNPJ") String cnpj;

        private @XmlElement(name = "CPF") String cpf;

        private @XmlElement(name = "idEstrangeiro") String foreignId;

        private @XmlElement(name = "xNome") String name;

        private @XmlElement(name = "enderDest") final Address adress;

        private @XmlElement(name = "indIEDest") final StateRegistrationReceiverIndicator stateRegistrationReceiverIndicator;

        private @XmlElement(name = "IE") final String stateRegistration;

        private @XmlElement(name = "IM") final String municipalRegistration;

        private @XmlElement(name = "email") final String email;

        public AdaptedReceiver() {
            this.stateRegistration = null;
            this.municipalRegistration = null;
            this.adress = null;
            this.stateRegistrationReceiverIndicator = null;
            this.email = null;
        }

        public AdaptedReceiver(String stateRegistration, String municipalRegistration, Address adress, StateRegistrationReceiverIndicator stateRegistrationReceiverIndicator, String email) {
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

        public String getAdaptedForeignId() {
            return this.foreignId;
        }

        public String getAdaptedStateRegistration() {
            return this.stateRegistration;
        }

        public String getAdaptedMunicipalRegistration() {
            return this.municipalRegistration;
        }

        public Address getAdaptedAdress() {
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

        public void setAdaptedForeignId(String foreignId) {
            this.foreignId = foreignId;
        }

    }

}

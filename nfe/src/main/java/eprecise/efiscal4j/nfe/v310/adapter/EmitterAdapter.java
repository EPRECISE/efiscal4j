
package eprecise.efiscal4j.nfe.v310.adapter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.nfe.v310.CRT;
import eprecise.efiscal4j.nfe.v310.address.Address;
import eprecise.efiscal4j.nfe.v310.person.Emitter;
import eprecise.efiscal4j.nfe.v310.person.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.v310.person.NaturalPersonDocuments;


public class EmitterAdapter extends XmlAdapter<EmitterAdapter.AdaptedEmitter, Emitter> {

    @Override
    public Emitter unmarshal(final AdaptedEmitter adaptedEmitter) throws Exception {
        Emitter emitter;

        //@formatter:off
        if (adaptedEmitter.getAdaptedCpf() != null) {
            emitter = new Emitter.Builder()
                           .asNaturalPerson()
                           .withCpf(adaptedEmitter.getAdaptedCpf())
                           .withName(adaptedEmitter.getAdaptedName())
                           .withFancyName(adaptedEmitter.getAdaptedFancyName())
                           .withStateRegistration(adaptedEmitter.getAdaptedStateRegistration())
                           .withStateRegistrationST(adaptedEmitter.getAdaptedStateRegistration())
                           .withMunicipalRegistration(adaptedEmitter.getAdaptedMunicipalRegistration())
                           .withCnae(adaptedEmitter.getCnae())
                           .withCrt(adaptedEmitter.getCrt())
                           .withAdress(adaptedEmitter.getAdaptedAdress())
                           .build();
        }else{
            emitter = new Emitter.Builder()
                           .asLegalEntity()
                           .withCnpj(adaptedEmitter.getAdaptedCnpj())
                           .withCorporateName(adaptedEmitter.getAdaptedName())
                           .withFancyName(adaptedEmitter.getAdaptedFancyName())
                           .withStateRegistration(adaptedEmitter.getAdaptedStateRegistration())
                           .withStateRegistrationST(adaptedEmitter.getAdaptedStateRegistrationST())
                           .withMunicipalRegistration(adaptedEmitter.getAdaptedMunicipalRegistration())
                           .withCnae(adaptedEmitter.getCnae())
                           .withCrt(adaptedEmitter.getCrt())
                           .withAdress(adaptedEmitter.getAdaptedAdress())
                           .build();
        }

        //@formatter:on
        return emitter;
    }

    @Override
    public AdaptedEmitter marshal(final Emitter emitter) throws Exception {
        //@formatter:off
        AdaptedEmitter adaptedEmitter = null;

        adaptedEmitter = new AdaptedEmitter(emitter.getFancyName()
                                           ,emitter.getStateRegistration()
                                           ,emitter.getStateRegistrationST()
                                           ,emitter.getMunicipalRegistration()
                                           ,emitter.getAdress()
                                           ,emitter.getCnae()
                                           ,emitter.getCrt());

        if (emitter.getDocuments() instanceof NaturalPersonDocuments) {
            adaptedEmitter.setAdaptedCpf(((NaturalPersonDocuments)emitter.getDocuments()).getCpf());
            adaptedEmitter.setAdaptedName(((NaturalPersonDocuments)emitter.getDocuments()).getName());
        } else if (emitter.getDocuments() instanceof LegalEntityDocuments) {
            adaptedEmitter.setAdaptedCnpj(((LegalEntityDocuments)emitter.getDocuments()).getCnpj());
            adaptedEmitter.setAdaptedName(((LegalEntityDocuments)emitter.getDocuments()).getCorporateName());
        }
        //@formatter:on

        return adaptedEmitter;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = { "cnpj", "cpf", "name", "fancyName", "adress", "stateRegistration", "stateRegistrationST", "municipalRegistration", "cnae", "crt" })
    protected static class AdaptedEmitter implements Serializable {

        private static final long serialVersionUID = 1L;

        private @XmlElement(name = "CNPJ") String cnpj;

        private @XmlElement(name = "CPF") String cpf;

        private @XmlElement(name = "xNome") String name;

        private @XmlElement(name = "xFant") final String fancyName;

        private @XmlElement(name = "enderEmit") final Address adress;

        private @XmlElement(name = "IE") final String stateRegistration;

        private @XmlElement(name = "IEST") final String stateRegistrationST;

        private @XmlElement(name = "IM") final String municipalRegistration;

        private @XmlElement(name = "CNAE") final String cnae;

        private @XmlElement(name = "CRT") final CRT crt;

        public AdaptedEmitter() {
            this.fancyName = null;
            this.stateRegistration = null;
            this.municipalRegistration = null;
            this.stateRegistrationST = null;
            this.adress = null;
            this.cnae = null;
            this.crt = null;
        }

        public AdaptedEmitter(final String fancyName, final String stateRegistration, final String stateRegistrationST, final String municipalRegistration, final Address adress, final String cnae,
                final CRT crt) {
            this.fancyName = fancyName;
            this.stateRegistration = stateRegistration;
            this.stateRegistrationST = stateRegistrationST;
            this.municipalRegistration = municipalRegistration;
            this.adress = adress;
            this.cnae = cnae;
            this.crt = crt;
        }

        public String getAdaptedCnpj() {
            return this.cnpj;
        }

        public String getAdaptedCpf() {
            return this.cpf;
        }

        public String getAdaptedName() {
            return this.name;
        }

        public String getAdaptedFancyName() {
            return this.fancyName;
        }

        public String getAdaptedStateRegistration() {
            return this.stateRegistration;
        }

        public String getAdaptedStateRegistrationST() {
            return this.stateRegistrationST;
        }

        public String getAdaptedMunicipalRegistration() {
            return this.municipalRegistration;
        }

        public Address getAdaptedAdress() {
            return this.adress;
        }

        public String getCnae() {
            return this.cnae;
        }

        public CRT getCrt() {
            return this.crt;
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

    }
}

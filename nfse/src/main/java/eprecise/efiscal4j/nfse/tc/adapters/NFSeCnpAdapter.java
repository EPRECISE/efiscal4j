
package eprecise.efiscal4j.nfse.tc.adapters;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnpj;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCpf;


public class NFSeCnpAdapter extends XmlAdapter<NFSeCnpAdapter.AdaptedCnp, CommonsNFSeCnp> {

    @Override
    public CommonsNFSeCnp unmarshal(final AdaptedCnp adaptedCnp) throws Exception {
        if (adaptedCnp == null) {
            return null;
        }
        //@formatter:off
        
        if(adaptedCnp.getAdaptedCnpj() != null){
            return new CommonsNFSeCnpj.Builder().withCnpj(adaptedCnp.getAdaptedCnpj()).build();
        } else if(adaptedCnp.getAdaptedCpf() != null){
            return new CommonsNFSeCpf.Builder().withCpf(adaptedCnp.getAdaptedCpf()).build();
        } else {
            throw new IllegalStateException();
        }
        
        //@formatter:on
    }

    @Override
    public AdaptedCnp marshal(final CommonsNFSeCnp cnp) throws Exception {
        if (cnp == null) {
            return null;
        }

        final AdaptedCnp adaptedCnp = new AdaptedCnp();
        //@formatter:off
            if(cnp instanceof CommonsNFSeCnpj){
                adaptedCnp.setAdaptedCnpj(cnp.getCnp());
            } else if(cnp instanceof CommonsNFSeCpf){
                adaptedCnp.setAdaptedCpf(cnp.getCnp());
            } else {
                throw new IllegalStateException();
            }
        //@formatter:on

        return adaptedCnp;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = { "cnpj", "cpf" })
    protected static class AdaptedCnp implements Serializable {

        private static final long serialVersionUID = 1L;

        private @XmlElement(name = "Cnpj") String cnpj;

        private @XmlElement(name = "Cpf") String cpf;

        public AdaptedCnp() {
        }

        public String getAdaptedCnpj() {
            return cnpj;
        }

        public String getAdaptedCpf() {
            return cpf;
        }

        public void setAdaptedCnpj(final String cnpj) {
            this.cnpj = cnpj;
        }

        public void setAdaptedCpf(final String cpf) {
            this.cpf = cpf;
        }
    }

}

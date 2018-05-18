
package eprecise.efiscal4j.nfe.v400.places;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class PlaceCnpAdapter extends XmlAdapter<PlaceCnpAdapter.AdaptedCnp, PlaceCnp> {

    @Override
    public PlaceCnp unmarshal(final AdaptedCnp adaptedCnp) throws Exception {
        if (adaptedCnp == null) {
            return null;
        }
        //@formatter:off
        
        if(adaptedCnp.getAdaptedCnpj() != null){
            return new PlaceCnpj.Builder().withCnpj(adaptedCnp.getAdaptedCnpj()).build();
        } else if(adaptedCnp.getAdaptedCpf() != null){
            return new PlaceCpf.Builder().withCpf(adaptedCnp.getAdaptedCpf()).build();
        } else {
            throw new IllegalStateException();
        }
        
        //@formatter:on
    }

    @Override
    public AdaptedCnp marshal(final PlaceCnp cnp) throws Exception {
        if (cnp == null) {
            return null;
        }

        final AdaptedCnp adaptedCnp = new AdaptedCnp();
        //@formatter:off
            if(cnp instanceof PlaceCnpj){
                adaptedCnp.setAdaptedCnpj(cnp.getCnp());
            } else if(cnp instanceof PlaceCpf){
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

        private @XmlElement(name = "CNPJ") String cnpj;

        private @XmlElement(name = "CPF") String cpf;

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

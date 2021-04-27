
package eprecise.efiscal4j.nfe.v400.autXml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class NFeAutXmlAdapter extends XmlAdapter<NFeAutXmlAdapter.AdaptedNFeAutXml, NFeAutXml> {

    @Override
    public NFeAutXml unmarshal(final AdaptedNFeAutXml adaptedNFeAutXml) throws Exception {
        if (adaptedNFeAutXml == null) {
            return null;
        }
        //@formatter:off

        if(adaptedNFeAutXml.getAdaptedCnpj() != null){
            return new NFeAutXmlCnpj(adaptedNFeAutXml.getAdaptedCnpj());
        } else if(adaptedNFeAutXml.getAdaptedCpf() != null){
            return new NFeAutXmlCpf(adaptedNFeAutXml.getAdaptedCpf());
        } else {
            throw new IllegalStateException();
        }

        //@formatter:on
    }

    @Override
    public AdaptedNFeAutXml marshal(final NFeAutXml nfeAutXml) throws Exception {
        if (nfeAutXml == null) {
            return null;
        }

        final AdaptedNFeAutXml adaptedNFeAutXml = new AdaptedNFeAutXml();
        //@formatter:off
            if(nfeAutXml instanceof NFeAutXmlCnpj){
                adaptedNFeAutXml.setAdaptedCnpj(nfeAutXml.getCnp());
            } else if(nfeAutXml instanceof NFeAutXmlCpf){
                adaptedNFeAutXml.setAdaptedCpf(nfeAutXml.getCnp());
            } else {
                throw new IllegalStateException();
            }
        //@formatter:on

        return adaptedNFeAutXml;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = { "cnpj", "cpf" })
    protected static class AdaptedNFeAutXml implements Serializable {

        private static final long serialVersionUID = 1L;

        private @XmlElement(name = "CNPJ") String cnpj;

        private @XmlElement(name = "CPF") String cpf;

        public AdaptedNFeAutXml() {
        }

        public String getAdaptedCnpj() {
            return this.cnpj;
        }

        public String getAdaptedCpf() {
            return this.cpf;
        }

        public void setAdaptedCnpj(final String cnpj) {
            this.cnpj = cnpj;
        }

        public void setAdaptedCpf(final String cpf) {
            this.cpf = cpf;
        }
    }

}

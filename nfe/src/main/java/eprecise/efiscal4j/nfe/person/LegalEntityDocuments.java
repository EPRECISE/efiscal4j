
package eprecise.efiscal4j.nfe.person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.nfe.types.NFeCNPJ;


@XmlAccessorType(XmlAccessType.FIELD)
public class LegalEntityDocuments extends AbstractDocuments {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "CNPJ") @NotNull @CNPJ(formatted = false) @Size(max = 14) @NFeCNPJ String cnpj;

    public LegalEntityDocuments() {
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCorporateName() {
        return this.getAbstractName();
    }

    public void setCorporateName(String corporateName) {
        this.setAbstractName(corporateName);
    }

    @Override
    public String getCnpjCpf() {
        return this.getCnpj();
    }

    @Override
    public String toString() {
        return "LegalEntityDocuments [corporateName=" + this.getCorporateName() + ", cnpj=" + this.getCnpj() + ", stateRegistration=" + this.getStateRegistration() + "]";
    }

}

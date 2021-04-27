
package eprecise.efiscal4j.nfe.v400.autXml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CPF;
import eprecise.efiscal4j.nfe.v400.types.NFeCPF;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFeAutXmlCpf extends NFeAutXml {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "CPF") @NotNull @Size(max = 11) @NFeCPF @CPF(formatted = false) String cpf;

    public NFeAutXmlCpf() {
        this.cpf = null;
    }

    public NFeAutXmlCpf(final String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    @Override
    public String getCnp() {
        return this.cpf;
    }
}

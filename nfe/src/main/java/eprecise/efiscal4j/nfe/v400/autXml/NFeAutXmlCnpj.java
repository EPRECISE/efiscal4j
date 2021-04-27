
package eprecise.efiscal4j.nfe.v400.autXml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.nfe.v400.types.NFeCNPJ;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFeAutXmlCnpj extends NFeAutXml {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "CNPJ") @NotNull @CNPJ(formatted = false) @Size(max = 14) @NFeCNPJ String cnpj;

    public NFeAutXmlCnpj() {
        this.cnpj = null;
    }

    public NFeAutXmlCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    @Override
    public String getCnp() {
        return this.cnpj;
    }
}


package eprecise.efiscal4j.nfe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CPF;


@XmlAccessorType(XmlAccessType.FIELD)
public class NaturalPersonDocuments extends AbstractDocuments {

	private @XmlElement(name = "CPF") @NotNull @Size(max = 11) @Pattern(regexp = "[0-9]{11}") @CPF(formatted = false) String cpf;

	public NaturalPersonDocuments() {
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return this.getAbstractName();
	}

	public void setName(String name) {
		this.setAbstractName(name);
	}

	@Override
	public String toString() {
		return "NaturalPersonDocuments [cpf=" + this.cpf + ", name=" + this.getName() + ", stateRegistration=" + this.getStateRegistration() + "]";
	}

}

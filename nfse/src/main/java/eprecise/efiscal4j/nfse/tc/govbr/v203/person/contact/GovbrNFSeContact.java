
package eprecise.efiscal4j.nfse.tc.govbr.v203.person.contact;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeContact implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @XmlElement(name = "Telefone") @Size(
            min = 1, max = 20, message = "Contato - telefone: tamanho informado deve estar entre 1 e 20 caracteres.") String phone;

    private @Getter final @XmlElement(name = "Email") @Size(
            min = 1, max = 80, message = "Contato - e-mail: tamanho informado deve estar entre 1 e 80 caracteres.") String email;

}

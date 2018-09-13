
package eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.tc.govbr.v203.person.address.GovbrNFSeAddress;
import eprecise.efiscal4j.nfse.tc.govbr.v203.person.contact.GovbrNFSeContact;
import lombok.Builder;
import lombok.Getter;


@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrServiceTaker implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "IdentificacaoTomador") GovbrIdentifier identifier;

    private @Getter final @NotNull @XmlElement(name = "NifTomador") @Size(min = 0, max = 40) String nifTaker;

    private @Getter final @XmlElement(name = "RazaoSocial") @Size(min = 1, max = 150) String socialName;

    private @Getter final @NotNull @XmlElement(name = "Endereco") GovbrNFSeAddress address;

    private @Getter final @XmlElement(name = "Contato") GovbrNFSeContact contact;

}

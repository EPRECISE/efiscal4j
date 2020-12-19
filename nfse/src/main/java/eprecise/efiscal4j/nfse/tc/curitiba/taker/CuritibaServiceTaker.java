
package eprecise.efiscal4j.nfse.tc.curitiba.taker;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeAddress;
import eprecise.efiscal4j.nfse.tc.commons.person.contacts.CommonsNFSeContact;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaServiceTaker implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @XmlElement(name = "IdentificacaoTomador") CuritibaServiceTakerIdentifier identifier;

    private @Getter final @NotNull @XmlElement(name = "RazaoSocial") @Size(min = 1, max = 115) String socialName;

    private @Getter final @XmlElement(name = "Endereco") CommonsNFSeAddress address;

    private @Getter final @XmlElement(name = "Contato") CommonsNFSeContact contact;

}
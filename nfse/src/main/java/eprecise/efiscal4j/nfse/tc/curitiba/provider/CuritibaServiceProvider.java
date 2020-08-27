package eprecise.efiscal4j.nfse.tc.curitiba.provider;

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
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaServiceProvider {
    
    private final @XmlElement(name = "IdentificacaoPrestador") @NotNull CuritibaServiceProviderIdentifier identifier;

    private final @XmlElement(name = "RazaoSocial") @NotNull @Size(min = 1, max = 115) String socialName;

    private final @XmlElement(name = "NomeFantasia") @Size(min = 1, max = 60) String fancyName;

    private final @XmlElement(name = "Endereco") @NotNull CommonsNFSeAddress address;

    private final @XmlElement(name = "Contato") CommonsNFSeContact contact;

}

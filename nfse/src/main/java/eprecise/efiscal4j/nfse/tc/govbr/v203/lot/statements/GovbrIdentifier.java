
package eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import lombok.Builder;
import lombok.Getter;


@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrIdentifier implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @XmlElement(name = "CpfCnpj") CommonsNFSeCnp cnp;

    private @Getter final @XmlElement(name = "InscricaoMunicipal") @Size(min = 1, max = 10) String municipalRegistration;

}

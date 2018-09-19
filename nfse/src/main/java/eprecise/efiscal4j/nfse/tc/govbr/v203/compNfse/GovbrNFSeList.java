
package eprecise.efiscal4j.nfse.tc.govbr.v203.compNfse;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeList {

    private @Getter final @NotNull @XmlElement(name = "CompNfse") Collection<GovbrCompNFSe> compNFSeList;

    private @Getter final @XmlElementWrapper(name = "ListaMensagemAlertaRetorno") @XmlElement(
            name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessage;

}


package eprecise.efiscal4j.nfse.tc.govbr.v203.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.ts.govbr.types.GovbrVersion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeCancel implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter @Builder.Default final @XmlAttribute(name = "versao") GovbrVersion version = GovbrVersion.VERSION_2_03;

    private @Getter final @NotNull @XmlElement(name = "Confirmacao") GovbrNFSeCancelConfirmation confirmation;

}

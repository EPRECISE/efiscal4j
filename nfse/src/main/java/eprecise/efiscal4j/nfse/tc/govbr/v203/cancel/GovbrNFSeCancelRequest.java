
package eprecise.efiscal4j.nfse.tc.govbr.v203.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.tc.govbr.v203.GovbrNFSeIdentifier;
import lombok.Builder;
import lombok.Getter;


@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeCancelRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "InfPedidoCancelamento") Info info;

    @Builder
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Info {

        private @Getter final @NotNull @XmlElement(name = "IdentificacaoNfse") GovbrNFSeIdentifier nfseIdentifier;

        private @Getter final @XmlElement(name = "CodigoCancelamento") GovbrCancellationCode cancellationCode;

    }

}

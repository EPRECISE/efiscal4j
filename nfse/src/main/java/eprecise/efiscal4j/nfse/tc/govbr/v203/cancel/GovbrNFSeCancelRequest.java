
package eprecise.efiscal4j.nfse.tc.govbr.v203.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfse.tc.govbr.v203.GovbrNFSeIdentifier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlRootElement(name = "Pedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeCancelRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "InfPedidoCancelamento") GovbrNFSeCancelRequestInfo info;

    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GovbrNFSeCancelRequestInfo {

        private @Getter final @NotNull @XmlElement(name = "IdentificacaoNfse") GovbrNFSeIdentifier nfseIdentifier;

        private @Getter final @XmlElement(name = "CodigoCancelamento") GovbrCancellationCode cancellationCode;

    }

}

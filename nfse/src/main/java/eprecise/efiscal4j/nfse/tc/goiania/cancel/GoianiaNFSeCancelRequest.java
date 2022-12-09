
package eprecise.efiscal4j.nfse.tc.goiania.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfse.tc.goiania.GoianiaNFSeIdentifier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlRootElement(name = "Pedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaNFSeCancelRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "InfPedidoCancelamento") GoianiaNFSeCancelRequestInfo info;

    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GoianiaNFSeCancelRequestInfo {

        private @Getter final @NotNull @XmlElement(name = "IdentificacaoNfse") GoianiaNFSeIdentifier nfseIdentifier;

        private @Getter final @XmlElement(name = "CodigoCancelamento") GoianiaCancellationCode cancellationCode;

    }

}

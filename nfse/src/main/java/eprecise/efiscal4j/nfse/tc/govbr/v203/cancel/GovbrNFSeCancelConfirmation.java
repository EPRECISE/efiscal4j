
package eprecise.efiscal4j.nfse.tc.govbr.v203.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;
import lombok.Builder;
import lombok.Getter;


@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeCancelConfirmation implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "Pedido") GovbrNFSeCancelRequest request;

    private @Getter final @NotNull @XmlElement(name = "DataHora") @NFSeDateTimeUTC String requestDate;

}

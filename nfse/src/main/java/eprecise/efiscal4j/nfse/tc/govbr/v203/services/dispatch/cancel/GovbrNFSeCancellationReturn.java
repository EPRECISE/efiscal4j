
package eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import eprecise.efiscal4j.nfse.tc.govbr.v203.cancel.GovbrNFSeCancel;
import lombok.Builder;
import lombok.Getter;


@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeCancellationReturn {

    private @Getter final @NotNull @XmlAttribute(name = "NfseCancelamento") GovbrNFSeCancel cancel;

}

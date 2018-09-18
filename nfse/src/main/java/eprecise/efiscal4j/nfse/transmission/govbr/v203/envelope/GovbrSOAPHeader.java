
package eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.ts.govbr.types.GovbrVersion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrSOAPHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "Versão") GovbrVersion version;

    private final @XmlElement(name = "versaoDados") GovbrVersion dataVersion;

}

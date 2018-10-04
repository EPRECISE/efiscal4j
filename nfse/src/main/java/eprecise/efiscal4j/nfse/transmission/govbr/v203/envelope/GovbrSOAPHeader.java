
package eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfse.ts.govbr.types.GovbrVersion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "cabecalho")
public class GovbrSOAPHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter @Builder.Default final @XmlAttribute(name = "xmlns") String xmlns = "http://www.abrasf.org.br/nfse.xsd";

    private final @XmlAttribute(name = "versao") GovbrVersion version;

    private final @XmlElement(name = "versaoDados") GovbrVersion dataVersion;


}

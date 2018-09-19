
package eprecise.efiscal4j.nfse.tc.govbr.v203;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.ts.govbr.types.NFSeAliquot;
import eprecise.efiscal4j.nfse.ts.govbr.types.NFSeValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeValues implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @XmlElement(name = "BaseCalculo") @NFSeValue String bcValue;

    private @Getter final @XmlElement(name = "Aliquota") @NFSeAliquot String issAliquot;

    private @Getter final @XmlElement(name = "ValorIss") @NFSeValue String issValue;

    private @Getter final @XmlElement(name = "ValorLiquidoNfse") @NFSeValue String issNetValue;

}

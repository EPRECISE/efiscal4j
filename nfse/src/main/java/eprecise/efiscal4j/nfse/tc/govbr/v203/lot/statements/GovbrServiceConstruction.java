
package eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrServiceConstruction {

    private @Getter final @XmlElement(name = "CodigoObra") @Size(min = 1, max = 30) String constructionCode;

    private @Getter final @XmlElement(name = "Art") @Size(min = 1, max = 30) String art;

}

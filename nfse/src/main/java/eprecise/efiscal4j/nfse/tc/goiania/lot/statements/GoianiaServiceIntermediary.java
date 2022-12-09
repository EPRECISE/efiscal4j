
package eprecise.efiscal4j.nfse.tc.goiania.lot.statements;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
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
public class GoianiaServiceIntermediary implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "IdentificacaoIntermediario") GoianiaIdentifier identifier;

    private @Getter final @NotNull @XmlElement(name = "RazaoSocial") @Size(min = 1, max = 150) String socialName;

}

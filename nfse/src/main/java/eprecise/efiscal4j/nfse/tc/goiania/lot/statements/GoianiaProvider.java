
package eprecise.efiscal4j.nfse.tc.goiania.lot.statements;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaProvider implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @XmlElement(name = "IdentificacaoPrestador") GoianiaIdentifier identifier;

}

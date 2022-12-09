
package eprecise.efiscal4j.nfse.tc.goiania.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaNFSeCancel implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "Confirmacao") GoianiaNFSeCancelConfirmation confirmation;
    
    private @Getter final @XmlAttribute(name = "versao") String version;

}

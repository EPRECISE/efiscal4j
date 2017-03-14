
package eprecise.efiscal4j.nfse.statements;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceProvider {

    private final @XmlElement(name = "Teste") @NotNull String test = "test";

}

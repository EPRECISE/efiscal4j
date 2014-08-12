
package eprecise.efiscal4j.nfe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFeIdentification {

    private @XmlElement(name = "cUF") @Size(min = 1, max = 60) final String fancyName;

    private @XmlElement(name = "cNF") @NotNull final Adress adress;

    private @XmlElement(name = "natOp") @NotNull @Size(max = 14) @Pattern(regexp = "[0-9]{2,14}|ISENTO") final String stateRegistration;

    private @XmlElement(name = "mod") @NotNull final CRT crt;

}

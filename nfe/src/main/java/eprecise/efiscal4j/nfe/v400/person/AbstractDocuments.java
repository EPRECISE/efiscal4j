
package eprecise.efiscal4j.nfe.v400.person;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Utilizada para generalizar a utilizacao de LegalEntityDocuments e NaturalPersonDocuments, pois o JAXB nao da suporte a utilizacao de interfaces (Documents)
 *
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "xNome") @Size(min = 2, max = 60) @NFeString String abstractName;

    private @XmlElement(name = "IE") @Size(max = 14) @Pattern(regexp = "ISENTO|[0-9]{2,14}") String stateRegistration;

    public String getAbstractName() {
        return this.abstractName;
    }

    public void setAbstractName(final String abstractName) {
        this.abstractName = Optional.ofNullable(abstractName).map(String::trim).orElse(null);
    }

    public String getStateRegistration() {
        return this.stateRegistration;
    }

    public void setStateRegistration(final String stateRegistration) {
        this.stateRegistration = stateRegistration;
    }

    public abstract String getCnpjCpf();

}


package eprecise.efiscal4j.nfe;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Utilizada para generalizar a utilizacao de LegalEntityDocuments e NaturalPersonDocuments, pois o JAXB nao da suporte a utilizacao de interfaces (Documents)
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "xNome") @NotNull @Size(min = 2, max = 60) @NFeString String abstractName;

    private @XmlElement(name = "IE") @Size(max = 14) @Pattern(regexp = "[0-9]{2,14}") String stateRegistration;

    protected String getAbstractName() {
        return this.abstractName;
    }

    protected void setAbstractName(String abstractName) {
        this.abstractName = abstractName.trim();
    }

    public String getStateRegistration() {
        return this.stateRegistration;
    }

    public void setStateRegistration(String stateRegistration) {
        this.stateRegistration = stateRegistration;
    }

    public abstract String getCnpjCpf();

}

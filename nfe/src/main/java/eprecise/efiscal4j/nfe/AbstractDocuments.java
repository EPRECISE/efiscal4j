
package eprecise.efiscal4j.nfe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * Utilizada para generalizar a utilização de LegalEntityDocuments e NaturalPersonDocuments, pois o JAXB não da suporte a utilização de interfaces (Documents)
 * 
 * @author felipe
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractDocuments {

    private @XmlElement(name = "xNome") @NotNull @Size(min = 2, max = 60) String abstractName;

    private @XmlElement(name = "IE") @Size(max = 14) @Pattern(regexp = "[0-9]{2,14}") String stateRegistration;

    private @XmlElement(name = "IM") @Size(min = 1, max = 15) String municipalRegistration;

    protected String getAbstractName() {
        return this.abstractName;
    }

    protected void setAbstractName(String abstractName) {
        this.abstractName = abstractName;
    }

    public String getStateRegistration() {
        return this.stateRegistration;
    }

    public String getMunicipalRegistration() {
        return this.municipalRegistration;
    }

    public void setStateRegistration(String stateRegistration) {
        this.stateRegistration = stateRegistration;
    }

    public void setMunicipalRegistration(String municipalRegistration) {
        this.municipalRegistration = municipalRegistration;
    }

}

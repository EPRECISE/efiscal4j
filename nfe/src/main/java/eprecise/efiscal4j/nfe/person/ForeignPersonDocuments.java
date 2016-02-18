
package eprecise.efiscal4j.nfe.person;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * Documentos de pessoa estrangeira
 * 
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ForeignPersonDocuments extends AbstractDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "idEstrangeiro") @Pattern(regexp = "([!-ÿ]{0}|[!-ÿ]{5,20})?") String foreignId;

    public ForeignPersonDocuments() {
    }

    public String getForeignId() {
        return this.foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    public String getCorporateName() {
        return this.getAbstractName();
    }

    public void setCorporateName(String corporateName) {
        this.setAbstractName(corporateName);
    }

    @Override
    public String getCnpjCpf() {
        return this.getForeignId();
    }

    @Override
    public String toString() {
        return "ForeignPersonDocuments [corporateName=" + this.getCorporateName() + ", foreignId=" + this.getForeignId() + "]";
    }

}

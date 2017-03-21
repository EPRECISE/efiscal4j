
package eprecise.efiscal4j.nfse.person.documents;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public abstract class NFSeCnp implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract String getCnp();

}

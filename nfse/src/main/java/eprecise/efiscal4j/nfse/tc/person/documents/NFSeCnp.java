
package eprecise.efiscal4j.nfse.tc.person.documents;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.nfse.adapters.NFSeCnpAdapter;


@XmlJavaTypeAdapter(NFSeCnpAdapter.class)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class NFSeCnp implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract String getCnp();

}

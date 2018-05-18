
package eprecise.efiscal4j.nfe.v400.places;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlJavaTypeAdapter(PlaceCnpAdapter.class)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class PlaceCnp implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract String getCnp();

}

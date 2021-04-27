package eprecise.efiscal4j.nfe.v400.autXml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Pessoas autorizadas para o download do XML da NF-e
 *
 */
@XmlJavaTypeAdapter(NFeAutXmlAdapter.class)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class NFeAutXml implements Serializable {

    private static final long serialVersionUID = 1L;

    abstract String getCnp();

}


package eprecise.efiscal4j.commons.domain.transmission;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;


/**
 * Identifica estruturas que podem ser transmitidas (adicionadas ao body da mensagem SOAP)
 * 
 * @author Felipe Bueno
 *
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
public abstract class TransmissibleBodyImpl {

    public abstract void setQName(QName qName);

    public abstract QName getQName();

}

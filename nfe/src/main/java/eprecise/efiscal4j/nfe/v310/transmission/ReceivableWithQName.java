
package eprecise.efiscal4j.nfe.v310.transmission;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;


/**
 * Identifica estruturas que podem ser recebidas (adicionadas ao body da mensagem SOAP de retorno)
 * 
 * 
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
public abstract class ReceivableWithQName extends Receivable{

    public abstract void setQName(QName qName);

    public abstract QName getQName();
}

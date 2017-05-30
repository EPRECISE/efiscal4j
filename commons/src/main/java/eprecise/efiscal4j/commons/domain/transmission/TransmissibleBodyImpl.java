
package eprecise.efiscal4j.commons.domain.transmission;

import java.io.Serializable;

import javax.xml.namespace.QName;


/**
 * Identifica estruturas que podem ser transmitidas (adicionadas ao body da mensagem SOAP)
 * 
 * @author Felipe Bueno
 *
 */

public interface TransmissibleBodyImpl extends Serializable {

    void setQName(QName qName);

    QName getQName();

}

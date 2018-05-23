
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfe.v400.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v400.transmission.Receivable;


/**
 * Método retornado após consumo do WS de inutilização de numeração da NFe
 *
 * @author Fernando C Glizt
 *
 */
@XmlRootElement(name = ObjectFactory.NFE_RESULT_MSG, namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NFeInutilizacao4")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeNumberDisableResponseMethod extends Receivable implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") @NotNull String xmlns;

    private @XmlElement(name = ObjectFactory.RET_INUT_NFE) @NotNull NFeNumberDisableResponse response;

    public NFeNumberDisableResponse getServiceStatusSearchResponse() {
        return this.response;
    }
}

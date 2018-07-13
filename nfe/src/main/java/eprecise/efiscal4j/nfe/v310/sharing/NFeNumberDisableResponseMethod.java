
package eprecise.efiscal4j.nfe.v310.sharing;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.nfe.transmission.response.NFeNumberDisableDispatchResponse;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v310.transmission.ReceivableWithQName;


/**
 * Método retornado após consumo do WS de inutilização de numeração da NFe
 *
 * @author Fernando C Glizt
 *
 */
@XmlRootElement(name = ObjectFactory.NFE_STAT_INUT_RESULT, namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao3")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeNumberDisableResponseMethod extends ReceivableWithQName implements NFeNumberDisableDispatchResponse {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") @NotNull String xmlns;

    private @XmlElement(name = ObjectFactory.RET_INUT_NFE) @NotNull NFeNumberDisableResponse response;

    private @XmlTransient QName qName = new QName(ObjectFactory.NFE_STAT_INUT_RESULT);

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

    public NFeNumberDisableResponse getServiceStatusSearchResponse() {
        return this.response;
    }
    
    public NFeNumberDisableResponse getResponse() {
		return response;
	}
}

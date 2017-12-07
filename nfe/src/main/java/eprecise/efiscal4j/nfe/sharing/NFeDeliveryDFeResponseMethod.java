
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.nfe.transmission.ObjectFactory;


/**
 * Método retornado após consumo do WS de inutilização de numeração da NFe
 *
 * @author Fernando C Glizt
 *
 */
@XmlRootElement(name = ObjectFactory.NFE_STAT_INUT_RESULT, namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NFeDistribuicaoDFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDeliveryDFeResponseMethod extends Receivable implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = ObjectFactory.RET_DIST_DFE_INT) @NotNull NFeNumberDisableResponse response;

    private @XmlTransient QName qName = new QName(ObjectFactory.NFE_STAT_INUT_RESULT);

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

}

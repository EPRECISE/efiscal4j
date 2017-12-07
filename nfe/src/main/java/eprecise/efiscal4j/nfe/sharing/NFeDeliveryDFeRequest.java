
package eprecise.efiscal4j.nfe.sharing;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.types.NFeCNPJ;


/**
 * Schema de pedido de distribuição de DF-e de interesse
 *
 * @author Clécius J. Martinkoski
 *
 */
@XmlRootElement(name = ObjectFactory.DIST_DFE_INT)
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDeliveryDFeRequest implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/xsd/deliveryDFe/distDFeInt_v1.01.xsd";

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "tpAmb") @NotNull TransmissionEnvironment enviroment;

    private @XmlElement(name = "cUFAutor") UF authorUf;

    private @XmlElement(name = "CNPJ") @NotNull @CNPJ(formatted = false) @Size(max = 14) @NFeCNPJ String cnpj; // Campo é uma xs:choice com CPF, não mapeado

    private @XmlTransient QName qName = new QName(ObjectFactory.DIST_DFE_INT);

    @Override
    public void setQName(QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.enviroment;
    }

    public UF getAuthorUf() {
        return this.authorUf;
    }

    public String getCnpj() {
        return this.cnpj;
    }

}

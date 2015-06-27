
package eprecise.efiscal4j.nfe.sharing.statussearch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.nfe.transmission.NFeHeader;


@XmlRegistry
public class ObjectFactory {

    private final static QName _NFeHeader_QNAME = new QName(NfeStatusServico3.DEFAULT_NAMESPACE, "nfeCabecMsg", "");

    private final static QName _NFeBody_QNAME = new QName(NfeStatusServico3.DEFAULT_NAMESPACE, "nfeDadosMsg", "");

    public ObjectFactory() {
    }

    public NfeStatusServicoNFResult createNfeStatusServicoNFResult() {
        return new NfeStatusServicoNFResult();
    }

    public NFeHeader createNfeCabecMsg() {
        return new NFeHeader();
    }

    // public NFeBody createNfeDadosMsg() {
    // return new NFeBody();
    // }

    @XmlElementDecl(namespace = NfeStatusServico3.DEFAULT_NAMESPACE, name = "nfeCabecMsg")
    public JAXBElement<NFeHeader> createNfeCabecMsg(NFeHeader value) {
        return new JAXBElement<NFeHeader>(_NFeHeader_QNAME, NFeHeader.class, null, value);
    }

    // @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico3", name = "nfeDadosMsg")
    // public JAXBElement<NFeBody> createNfeDadosMsg(NFeBody value) {
    // return new JAXBElement<NFeBody>(_NFeBody_QNAME, NFeBody.class, null, value);
    // }
}

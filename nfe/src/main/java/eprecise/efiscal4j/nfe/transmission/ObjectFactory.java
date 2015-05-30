
package eprecise.efiscal4j.nfe.transmission;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.nfe.sharing.BatchReceiptSearch;
import eprecise.efiscal4j.nfe.sharing.NFeDispatch;


@XmlRegistry
public class ObjectFactory {

    public static final String ENVI_NFE = "enviNFe";

    public static final String CONS_RECI_NFE = "consReciNFe";

    @XmlElementDecl(name = ENVI_NFE)
    public JAXBElement<NFeDispatch> createNFeDispatch(NFeDispatch transmissible) {
        return new JAXBElement<NFeDispatch>(new QName(ENVI_NFE), NFeDispatch.class, transmissible);
    }

    @XmlElementDecl(name = CONS_RECI_NFE)
    public JAXBElement<BatchReceiptSearch> createBatchReceiptSearch(BatchReceiptSearch transmissible) {
        return new JAXBElement<BatchReceiptSearch>(new QName(CONS_RECI_NFE), BatchReceiptSearch.class, transmissible);
    }

}


package eprecise.efiscal4j.nfe.v310.transmission;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class ReceivableAdapter extends XmlAdapter<JAXBElement<ReceivableWithQName>, ReceivableWithQName> {

    @Override
    public JAXBElement<ReceivableWithQName> marshal(ReceivableWithQName receivable) throws Exception {
        return new JAXBElement<ReceivableWithQName>(receivable.getQName(), ReceivableWithQName.class, receivable);
    }

    @Override
    public ReceivableWithQName unmarshal(JAXBElement<ReceivableWithQName> jaxbElement) throws Exception {
        final ReceivableWithQName receivable = jaxbElement.getValue();
        receivable.setQName(jaxbElement.getName());
        return receivable;
    }
}

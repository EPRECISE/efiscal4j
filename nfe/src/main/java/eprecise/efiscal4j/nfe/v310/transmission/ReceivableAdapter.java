
package eprecise.efiscal4j.nfe.v310.transmission;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class ReceivableAdapter extends XmlAdapter<JAXBElement<Receivable>, Receivable> {

    @Override
    public JAXBElement<Receivable> marshal(Receivable receivable) throws Exception {
        return new JAXBElement<Receivable>(receivable.getQName(), Receivable.class, receivable);
    }

    @Override
    public Receivable unmarshal(JAXBElement<Receivable> jaxbElement) throws Exception {
        final Receivable receivable = jaxbElement.getValue();
        receivable.setQName(jaxbElement.getName());
        return receivable;
    }
}

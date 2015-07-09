
package eprecise.efiscal4j.commons.domain.transmission;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class TransmissibleBodyImplAdapter extends XmlAdapter<JAXBElement<TransmissibleBodyImpl>, TransmissibleBodyImpl> {

    @Override
    public JAXBElement<TransmissibleBodyImpl> marshal(TransmissibleBodyImpl transmissible) throws Exception {
        return new JAXBElement<TransmissibleBodyImpl>(transmissible.getQName(), TransmissibleBodyImpl.class, transmissible);
    }

    @Override
    public TransmissibleBodyImpl unmarshal(JAXBElement<TransmissibleBodyImpl> jaxbElement) throws Exception {
        final TransmissibleBodyImpl transmissible = jaxbElement.getValue();
        transmissible.setQName(jaxbElement.getName());
        return transmissible;
    }

}

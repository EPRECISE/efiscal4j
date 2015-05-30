
package eprecise.efiscal4j.commons.domain.transmission;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class TransmissibleAdapter extends XmlAdapter<JAXBElement<Transmissible>, Transmissible> {

    @Override
    public JAXBElement<Transmissible> marshal(Transmissible transmissible) throws Exception {
        return new JAXBElement<Transmissible>(transmissible.getQName(), Transmissible.class, transmissible);
    }

    @Override
    public Transmissible unmarshal(JAXBElement<Transmissible> jaxbElement) throws Exception {
        final Transmissible transmissible = jaxbElement.getValue();
        transmissible.setQName(jaxbElement.getName());
        return transmissible;
    }

}


package eprecise.efiscal4j.nfe.v400.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * 
 * @author Felipe Bueno
 * 
 */
public class PISAdapter extends XmlAdapter<PISAdapter.AdaptedPIS, PIS> {

    @Override
    public PIS unmarshal(AdaptedPIS v) throws Exception {
        return v.getPis();
    }

    @Override
    public AdaptedPIS marshal(PIS v) throws Exception {
        return (v == null ? null : new AdaptedPIS(v));
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    protected static class AdaptedPIS {

        // @formatter:off
        @XmlElements(value = { 
                @XmlElement(name = "PISAliq", type = PIS01.class),
                @XmlElement(name = "PISAliq", type = PIS02.class),
                @XmlElement(name = "PISQtde", type = PIS03.class),
                @XmlElement(name = "PISNT", type = PIS04.class),
                @XmlElement(name = "PISNT", type = PIS05.class),
                @XmlElement(name = "PISNT", type = PIS06.class),
                @XmlElement(name = "PISNT", type = PIS07.class),
                @XmlElement(name = "PISNT", type = PIS08.class),
                @XmlElement(name = "PISNT", type = PIS09.class),
                @XmlElement(name = "PISOutr", type = PIS49.class),
                @XmlElement(name = "PISOutr", type = PIS50.class),
                @XmlElement(name = "PISOutr", type = PIS51.class),
                @XmlElement(name = "PISOutr", type = PIS52.class),
                @XmlElement(name = "PISOutr", type = PIS53.class),
                @XmlElement(name = "PISOutr", type = PIS54.class),
                @XmlElement(name = "PISOutr", type = PIS55.class),
                @XmlElement(name = "PISOutr", type = PIS56.class),
                @XmlElement(name = "PISOutr", type = PIS60.class),
                @XmlElement(name = "PISOutr", type = PIS61.class),
                @XmlElement(name = "PISOutr", type = PIS62.class),
                @XmlElement(name = "PISOutr", type = PIS63.class),
                @XmlElement(name = "PISOutr", type = PIS64.class),
                @XmlElement(name = "PISOutr", type = PIS65.class),
                @XmlElement(name = "PISOutr", type = PIS66.class),
                @XmlElement(name = "PISOutr", type = PIS67.class),
                @XmlElement(name = "PISOutr", type = PIS70.class),
                @XmlElement(name = "PISOutr", type = PIS71.class),
                @XmlElement(name = "PISOutr", type = PIS72.class),
                @XmlElement(name = "PISOutr", type = PIS73.class),
                @XmlElement(name = "PISOutr", type = PIS74.class),
                @XmlElement(name = "PISOutr", type = PIS75.class),
                @XmlElement(name = "PISOutr", type = PIS98.class),
                @XmlElement(name = "PISOutr", type = PIS99.class),
                }) 
        // @formatter:on
        private final PIS pis;

        public AdaptedPIS() {
            this.pis = null;
        }

        public AdaptedPIS(PIS pis) {
            this.pis = pis;
        }

        public PIS getPis() {
            return this.pis;
        }

    }
}


package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * 
 * @author Clécius J. Martinkoski
 * 
 */
public class ICMSAdapter extends XmlAdapter<ICMSAdapter.AdaptedICMS, ICMS> {

    @Override
    public ICMS unmarshal(AdaptedICMS v) throws Exception {
        return v.getIcms();
    }

    @Override
    public AdaptedICMS marshal(ICMS v) throws Exception {
        return new AdaptedICMS(v);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    protected static class AdaptedICMS {

        // @formatter:off
        @XmlElements(value = { 
                @XmlElement(name = "ICMS00", type = ICMS00.class),
                @XmlElement(name = "ICMS10", type = ICMS10.class),
                @XmlElement(name = "ICMS20", type = ICMS20.class),
                @XmlElement(name = "ICMS30", type = ICMS30.class),
                @XmlElement(name = "ICMS40", type = ICMS40.class),
                @XmlElement(name = "ICMS40", type = ICMS41.class),
                @XmlElement(name = "ICMS40", type = ICMS50.class),
                @XmlElement(name = "ICMS51", type = ICMS51.class),
                @XmlElement(name = "ICMS60", type = ICMS60.class),
                @XmlElement(name = "ICMS70", type = ICMS70.class),
                @XmlElement(name = "ICMS90", type = ICMS90.class),
                @XmlElement(name = "ICMSPart", type = ICMSPart10.class),
                @XmlElement(name = "ICMSPart", type = ICMSPart90.class),
                @XmlElement(name = "ICMSST", type = ICMSST.class),
                @XmlElement(name = "ICMSSN101", type = ICMSSN101.class),
                @XmlElement(name = "ICMSSN102", type = ICMSSN102.class),
                @XmlElement(name = "ICMSSN102", type = ICMSSN103.class),
                @XmlElement(name = "ICMSSN102", type = ICMSSN300.class),
                @XmlElement(name = "ICMSSN102", type = ICMSSN400.class),
                @XmlElement(name = "ICMSSN201", type = ICMSSN201.class),
                @XmlElement(name = "ICMSSN202", type = ICMSSN202.class),
                @XmlElement(name = "ICMSSN202", type = ICMSSN203.class),
                @XmlElement(name = "ICMSSN500", type = ICMSSN500.class),
                @XmlElement(name = "ICMSSN900", type = ICMSSN900.class),
                }) 
        // @formatter:on
        private final ICMS icms;

        public AdaptedICMS() {
            this.icms = null;
        }

        public AdaptedICMS(ICMS icms) {
            this.icms = icms;
        }

        public ICMS getIcms() {
            return this.icms;
        }

    }

}

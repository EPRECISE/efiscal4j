
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.nfe.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.tax.icms.ICMS00;


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
                @XmlElement(name = "ICMS10", type = ICMS10.class)
                }) 
        // @formatter:on
        private final ICMS icms;

        public AdaptedICMS(ICMS icms) {
            this.icms = icms;
        }

        public ICMS getIcms() {
            return this.icms;
        }

    }

}

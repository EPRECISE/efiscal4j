
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo ICMS 90 - Outras
 * 
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS90 extends BaseICMS {

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        @Override
        public ICMS90 build() {
            return new ICMS90();
        }

    }

    protected ICMS90() {
        super("90");
    }
}

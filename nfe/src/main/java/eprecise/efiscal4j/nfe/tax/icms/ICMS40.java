
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo ICMS 40 - Isenta
 * 
 * @see BaseICMS40
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS40 extends BaseICMS40 {

    public static class Builder extends BaseICMS40.Builder implements ICMSBuilder {

        @Override
        public ICMS40 build() {
            return new ICMS40();
        }

    }

    protected ICMS40() {
        super("40");
    }
}

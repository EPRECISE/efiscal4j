
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo ICMS 60 - ICMS cobrado anteriormente por substituição tributária
 * 
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS60 extends BaseICMS {

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        @Override
        public ICMS60 build() {
            return new ICMS60();
        }

    }

    protected ICMS60() {
        super("60");
    }
}

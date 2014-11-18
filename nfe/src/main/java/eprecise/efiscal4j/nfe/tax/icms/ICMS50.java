
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo ICMS 50 - Suspensão
 * 
 * @see BaseICMS40
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS50 extends BaseICMS40 {

    public static class Builder extends BaseICMS40.Builder implements ICMSBuilder {

        @Override
        public ICMS50 build() {
            return new ICMS50();
        }

    }

    protected ICMS50() {
        super("50");
    }
}

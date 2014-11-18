
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo ICMS 10 - Tributada e com cobrança do ICMS por substituição tributária
 * 
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS10 extends BaseICMS {

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        @Override
        public ICMS10 build() {
            return new ICMS10();
        }

    }

    protected ICMS10() {
        super("10");
    }
}

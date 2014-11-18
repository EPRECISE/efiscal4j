
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo ICMS 41 - Não Tributada
 * 
 * @see BaseICMS40
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS41 extends BaseICMS40 {

    public static class Builder extends BaseICMS40.Builder implements ICMSBuilder {

        @Override
        public ICMS41 build() {
            return new ICMS41();
        }

    }

    protected ICMS41() {
        super("41");
    }
}

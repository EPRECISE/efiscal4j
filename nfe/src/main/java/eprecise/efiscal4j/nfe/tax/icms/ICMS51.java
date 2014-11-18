
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributção pelo ICMS 51 - Diferimento
 * 
 * A exigência do preenchimento das informações do ICMS diferido fica à critério de cada UF.
 * 
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS51 extends BaseICMS {

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        @Override
        public ICMS51 build() {
            return new ICMS51();
        }

    }

    protected ICMS51() {
        super("51");
    }
}

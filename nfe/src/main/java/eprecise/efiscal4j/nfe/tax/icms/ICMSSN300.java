
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=300 - Imune.
 * 
 * @see BaseICMSSN
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN300 extends BaseICMSSN102 {

    public static class Builder extends BaseICMSSN102.Builder implements ICMSBuilder {

        @Override
        public ICMSSN300 build() {
            return new ICMSSN300();
        }

    }

    protected ICMSSN300() {
        super("300");
    }
}

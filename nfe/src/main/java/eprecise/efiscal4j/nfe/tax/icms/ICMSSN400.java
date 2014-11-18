
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=400 - Não tributada pelo Simples Nacional
 * 
 * @see BaseICMSSN
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN400 extends BaseICMSSN102 {

    public static class Builder extends BaseICMSSN102.Builder implements ICMSBuilder {

        @Override
        public ICMSSN400 build() {
            return new ICMSSN400();
        }

    }

    protected ICMSSN400() {
        super("400");
    }
}

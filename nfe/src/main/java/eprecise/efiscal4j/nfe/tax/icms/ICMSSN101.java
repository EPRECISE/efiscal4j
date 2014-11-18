
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=101 - Tributada pelo Simples Nacional com permissão de crédito.
 * 
 * @see BaseICMSSN
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN101 extends BaseICMSSN {

    public static class Builder extends BaseICMSSN.Builder implements ICMSBuilder {

        @Override
        public ICMSSN101 build() {
            return new ICMSSN101();
        }

    }

    protected ICMSSN101() {
        super("101");
    }
}

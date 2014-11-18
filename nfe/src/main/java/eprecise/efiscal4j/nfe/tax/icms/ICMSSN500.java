
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL,CRT=1 – Simples Nacional e CSOSN=500 - ICMS cobrado anteriormente por substituição tributária (substituído) ou por antecipação
 * 
 * @see BaseICMSSN
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN500 extends BaseICMSSN {

    public static class Builder extends BaseICMSSN.Builder implements ICMSBuilder {

        @Override
        public ICMSSN500 build() {
            return new ICMSSN500();
        }
    }

    protected ICMSSN500() {
        super("500");
    }
}

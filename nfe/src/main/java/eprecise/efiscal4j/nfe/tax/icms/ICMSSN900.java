
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL, CRT=1 – Simples Nacional e CSOSN=900 - Outros
 * 
 * @see BaseICMSSN
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN900 extends BaseICMSSN {

    public static class Builder extends BaseICMSSN.Builder implements ICMSBuilder {

        @Override
        public ICMSSN900 build() {
            return new ICMSSN900();
        }

    }

    protected ICMSSN900() {
        super("900");
    }
}

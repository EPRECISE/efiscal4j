
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=203 - Isenção do ICMS nos Simples Nacional para faixa de receita bruta e com cobrança do ICMS por Substituição Tributária
 * 
 * @see BaseICMSSN
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN203 extends BaseICMSSN202 {

    public static class Builder extends BaseICMSSN202.Builder implements ICMSBuilder {

        @Override
        public ICMSSN203 build() {
            return new ICMSSN203();
        }

    }

    protected ICMSSN203() {
        super("203");
    }
}


package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=202 - Tributada pelo Simples Nacional sem permissão de crédito e com cobrança do ICMS por Substituição Tributária
 * 
 * @see BaseICMSSN
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN202 extends BaseICMSSN202 {

    public static class Builder extends BaseICMSSN202.Builder implements ICMSBuilder {

        @Override
        public ICMSSN202 build() {
            return new ICMSSN202();
        }

    }

    protected ICMSSN202() {
        super("202");
    }
}

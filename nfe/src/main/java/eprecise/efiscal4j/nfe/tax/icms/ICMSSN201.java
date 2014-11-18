
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=201 - Tributada pelo Simples Nacional com permissão de crédito e com cobrança do ICMS por Substituição Tributária
 * 
 * @see BaseICMSSN
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN201 extends BaseICMSSN {

    public static class Builder extends BaseICMSSN.Builder implements ICMSBuilder {

        @Override
        public ICMSSN201 build() {
            return new ICMSSN201();
        }

    }

    protected ICMSSN201() {
        super("201");
    }
}

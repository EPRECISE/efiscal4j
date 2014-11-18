
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=103 - Isenção do ICMS no Simples Nacional para faixa de receita bruta.
 * 
 * @see BaseICMSSN
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN103 extends BaseICMSSN102 {

    public static class Builder extends BaseICMSSN102.Builder implements ICMSBuilder {

        @Override
        public ICMSSN103 build() {
            return new ICMSSN103();
        }

    }

    protected ICMSSN103() {
        super("103");
    }
}

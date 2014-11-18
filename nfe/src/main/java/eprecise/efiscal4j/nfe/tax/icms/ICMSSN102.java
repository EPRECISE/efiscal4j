
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=102 - Tributada pelo Simples Nacional sem permissão de crédito.
 * 
 * @see BaseICMSSN
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN102 extends BaseICMSSN102 {

    public static class Builder extends BaseICMSSN102.Builder implements ICMSBuilder {

        @Override
        public ICMSSN102 build() {
            return new ICMSSN102();
        }

    }

    protected ICMSSN102() {
        super("102");
    }
}


package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Grupo de informação do ICMSST devido para a UF de destino, nas operações interestaduais de produtos que tiveram retenção antecipada de ICMS por ST na UF do remetente. Repasse via Substituto
 * Tributário.
 * 
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSST extends BaseICMS {

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        @Override
        public ICMSST build() {
            return new ICMSST();
        }

    }

    protected ICMSST() {
        super("41");
    }
}

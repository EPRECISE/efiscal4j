
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Partilha do ICMS entre a UF de origem e UF de destino ou a UF definida na legislação Operação interestadual para consumidor final com partilha do ICMS devido na operação entre a UF de origem e a UF
 * do destinatário ou ou a UF definida na legislação. (Ex. UF da concessionária de entrega do veículos)
 * 
 * CST 90 - Outros.
 * 
 * @see BaseICMSPart
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSPart90 extends BaseICMSPart {

    public static class Builder extends BaseICMSPart.Builder implements ICMSBuilder {

        @Override
        public ICMSPart90 build() {
            return new ICMSPart90();
        }

    }

    protected ICMSPart90() {
        super("90");
    }
}

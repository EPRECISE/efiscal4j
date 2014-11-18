
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Partilha do ICMS entre a UF de origem e UF de destino ou a UF definida na legislação Operação interestadual para consumidor final com partilha do ICMS devido na operação entre a UF de origem e a UF
 * do destinatário ou ou a UF definida na legislação. (Ex. UF da concessionária de entrega do veículos)
 * 
 * CST 10 - Tributada e com cobrança do ICMS por substituição tributária
 * 
 * @see BaseICMSPart
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSPart10 extends BaseICMSPart {

    public static class Builder extends BaseICMSPart.Builder implements ICMSBuilder {

        @Override
        public ICMSPart10 build() {
            return new ICMSPart10();
        }

    }

    protected ICMSPart10() {
        super("10");
    }
}


package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 09 - Operação com suspensão da contribuição
 * 
 * @see BasePISNT
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS09 extends BasePISNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISNT.Builder implements PISBuilder {

        @Override
        public PIS09 build() {
            return new PIS09(this);
        }
    }

    protected PIS09() {
        super(null, null);
    }

    protected PIS09(PIS09.Builder builder) {
        super(builder, "09");
    }

}
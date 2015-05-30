
package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 04 - Operação Tributável - Tributação Monofásica - (Alíquota Zero)
 * 
 * @see BasePISNT
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS04 extends BasePISNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISNT.Builder implements PISBuilder {

        @Override
        public PIS04 build() {
            return new PIS04(this);
        }
    }

    protected PIS04() {
        super();
    }

    protected PIS04(PIS04.Builder builder) {
        super(builder, "04");
    }

}

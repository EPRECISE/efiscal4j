
package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 04 - Operação Tributável - Tributação Monofásica - (Alíquota Zero)
 * 
 * @see BaseCOFINSNT
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS04 extends BaseCOFINSNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSNT.Builder implements COFINSBuilder {

        @Override
        public COFINS04 build() {
            return new COFINS04(this);
        }
    }

    protected COFINS04() {
        super(null, null);
    }

    protected COFINS04(COFINS04.Builder builder) {
        super(builder, "04");
    }

}
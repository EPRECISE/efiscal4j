
package eprecise.efiscal4j.nfe.v310.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 06 - Operação Tributável - Alíquota Zero
 * 
 * @see BaseCOFINSNT
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS06 extends BaseCOFINSNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSNT.Builder implements COFINSBuilder {

        @Override
        public COFINS06 build() {
            return new COFINS06(this);
        }
    }

    public COFINS06() {
        super();
    }

    protected COFINS06(final COFINS06.Builder builder) {
        super(builder, "06");
    }

}

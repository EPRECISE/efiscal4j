
package eprecise.efiscal4j.nfe.v400.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 09 - Operação com suspensão da contribuição
 * 
 * @see BaseCOFINSNT
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS09 extends BaseCOFINSNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSNT.Builder implements COFINSBuilder {

        @Override
        public COFINS09 build() {
            return new COFINS09(this);
        }
    }

    public COFINS09() {
        super();
    }

    protected COFINS09(final COFINS09.Builder builder) {
        super(builder, "09");
    }

}

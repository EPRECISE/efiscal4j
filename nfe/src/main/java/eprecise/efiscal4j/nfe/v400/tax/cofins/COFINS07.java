
package eprecise.efiscal4j.nfe.v400.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 07 - Operação Isenta da contribuição
 * 
 * @see BaseCOFINSNT
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class COFINS07 extends BaseCOFINSNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSNT.Builder implements COFINSBuilder {

        @Override
        public COFINS07 build() {
            return new COFINS07(this);
        }
    }

    public COFINS07() {
        super();
    }

    protected COFINS07(final COFINS07.Builder builder) {
        super(builder, "07");
    }

}


package eprecise.efiscal4j.nfe.v310.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 08 - Operação Sem Incidência da contribuição
 * 
 * @see BaseCOFINSNT
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class COFINS08 extends BaseCOFINSNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSNT.Builder implements COFINSBuilder {

        @Override
        public COFINS08 build() {
            return new COFINS08(this);
        }
    }

    public COFINS08() {
        super();
    }

    protected COFINS08(final COFINS08.Builder builder) {
        super(builder, "08");
    }

}

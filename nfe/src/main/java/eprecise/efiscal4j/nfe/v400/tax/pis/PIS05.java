
package eprecise.efiscal4j.nfe.v400.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 05 - Operação Tributável (ST)
 * 
 * @see BasePISNT
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */

// TODO Verificar esse CST, pois aparentemente não deveria estar no grupo de não tributado, e sim deveria ser o CST do grupo PISST (Nele não existe CST atualmente)
@XmlAccessorType(XmlAccessType.FIELD)
public class PIS05 extends BasePISNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISNT.Builder implements PISBuilder {

        @Override
        public PIS05 build() {
            return new PIS05(this);
        }
    }

    public PIS05() {
    }

    protected PIS05(final PIS05.Builder builder) {
        super(builder, "05");
    }

}

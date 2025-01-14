
package eprecise.efiscal4j.nfe.v310.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 06 - Operação Tributável - Alíquota Zero
 * 
 * @see BasePISNT
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PIS06 extends BasePISNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISNT.Builder implements PISBuilder {

        @Override
        public PIS06 build() {
            return new PIS06(this);
        }
    }

    public PIS06() {
    }

    protected PIS06(final PIS06.Builder builder) {
        super(builder, "06");
    }

}


package eprecise.efiscal4j.nfe.v310.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 07 - Operação Isenta da contribuição
 * 
 * @see BasePISNT
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS07 extends BasePISNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISNT.Builder implements PISBuilder {

        @Override
        public PIS07 build() {
            return new PIS07(this);
        }
    }

    public PIS07() {
    }

    protected PIS07(final PIS07.Builder builder) {
        super(builder, "07");
    }

}

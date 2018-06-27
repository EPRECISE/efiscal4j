
package eprecise.efiscal4j.nfe.v310.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 08 - Operação Sem Incidência da contribuição
 * 
 * @see BasePISNT
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PIS08 extends BasePISNT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISNT.Builder implements PISBuilder {

        @Override
        public PIS08 build() {
            return new PIS08(this);
        }
    }

    public PIS08() {
    }

    protected PIS08(final PIS08.Builder builder) {
        super(builder, "08");
    }

}

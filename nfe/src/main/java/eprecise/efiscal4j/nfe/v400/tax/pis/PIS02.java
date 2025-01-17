
package eprecise.efiscal4j.nfe.v400.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 02 - Operação Tributável - Base de Calculo = Valor da Operação (Alíquota Diferenciada)
 * 
 * @see BasePISAliquot
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PIS02 extends BasePISAliquot {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISAliquot.Builder implements PISBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(final String bcValue) {
            return (PIS02.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(final String pisAliquot) {
            return (PIS02.Builder) super.withPisAliquot(pisAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(final String pisValue) {
            return (PIS02.Builder) super.withPisValue(pisValue);
        }

        @Override
        public PIS02 build() {
            return new PIS02(this);
        }
    }

    public PIS02() {
    }

    protected PIS02(final PIS02.Builder builder) {
        super(builder, "02");
    }

}

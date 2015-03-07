
package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 01 – Operação Tributável - Base de Cálculo = Valor da Operação Alíquota Normal (Cumulativo/Não Cumulativo)
 * 
 * @see BasePISAliquot
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS01 extends BasePISAliquot {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISAliquot.Builder implements PISBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (PIS01.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(String pisAliquot) {
            return (PIS01.Builder) super.withPisAliquot(pisAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(String pisValue) {
            return (PIS01.Builder) super.withPisValue(pisValue);
        }

        @Override
        public PIS01 build() {
            return new PIS01(this);
        }
    }

    protected PIS01() {
        super(null, null);
    }

    protected PIS01(PIS01.Builder builder) {
        super(builder, "01");
    }

}


package eprecise.efiscal4j.nfe.v310.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 01 – Operação Tributável - Base de Cálculo = Valor da Operação Alíquota Normal (Cumulativo/Não Cumulativo)
 * 
 * @see BaseCOFINSAliquot
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class COFINS01 extends BaseCOFINSAliquot {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSAliquot.Builder implements COFINSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(final String bcValue) {
            return (COFINS01.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsAliquot(final String cofinsAliquot) {
            return (COFINS01.Builder) super.withCofinsAliquot(cofinsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(final String cofinsValue) {
            return (COFINS01.Builder) super.withCofinsValue(cofinsValue);
        }

        @Override
        public COFINS01 build() {
            return new COFINS01(this);
        }
    }

    public COFINS01() {
        super();
    }

    protected COFINS01(final COFINS01.Builder builder) {
        super(builder, "01");
    }

}

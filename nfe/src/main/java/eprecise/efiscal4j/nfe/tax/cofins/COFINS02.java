
package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 02 - Operação Tributável - Base de Calculo = Valor da Operação (Alíquota Diferenciada)
 * 
 * @see BaseCOFINSAliquot
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS02 extends BaseCOFINSAliquot {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSAliquot.Builder implements COFINSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (COFINS02.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsAliquot(String cofinsAliquot) {
            return (COFINS02.Builder) super.withCofinsAliquot(cofinsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(String cofinsValue) {
            return (COFINS02.Builder) super.withCofinsValue(cofinsValue);
        }

        @Override
        public COFINS02 build() {
            return new COFINS02(this);
        }
    }

    protected COFINS02() {
        super(null, null);
    }

    protected COFINS02(COFINS02.Builder builder) {
        super(builder, "02");
    }

}

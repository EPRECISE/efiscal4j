package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Tributação pelo PIS 52 - Operação com Direito a Crédito - Vinculada Exclusivamente a Receita de Exportação
 * 
 * @see BasePISOther
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS52 extends BasePISOther{

    private static final long serialVersionUID = 1L;
    
    public static class Builder extends BasePISOther.Builder implements PISBuilder {   
              
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (PIS52.Builder) super.withBcValue(bcValue);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) { 
            return (PIS52.Builder) super.withProductAliquot(productAliquot);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (PIS52.Builder) super.withProductQuantity(productQuantity);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(String pisAliquot) {
            return (PIS52.Builder) super.withPisAliquot(pisAliquot);
        }               
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(String pisValue) {
            return (PIS52.Builder) super.withPisValue(pisValue);
        }
      
        @Override
        public PIS52 build() {
            return new PIS52(this);
        }
    }

    protected PIS52(PIS52.Builder builder) {
        super(builder, "52");
    }
    
}
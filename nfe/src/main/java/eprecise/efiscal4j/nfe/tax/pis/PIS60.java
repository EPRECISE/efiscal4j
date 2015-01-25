package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Tributação pelo PIS 60 - Crédito Presumido - Operação de Aquisição Vinculada Exclusivamente a Receita Tributada no Mercado Interno
 * 
 * @see BasePISOther
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS60 extends BasePISOther{

    private static final long serialVersionUID = 1L;
    
    public static class Builder extends BasePISOther.Builder implements PISBuilder {   
              
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (PIS60.Builder) super.withBcValue(bcValue);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) { 
            return (PIS60.Builder) super.withProductAliquot(productAliquot);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (PIS60.Builder) super.withProductQuantity(productQuantity);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(String pisAliquot) {
            return (PIS60.Builder) super.withPisAliquot(pisAliquot);
        }               
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(String pisValue) {
            return (PIS60.Builder) super.withPisValue(pisValue);
        }
      
        @Override
        public PIS60 build() {
            return new PIS60(this);
        }
    }

    protected PIS60(PIS60.Builder builder) {
        super(builder, "60");
    }
    
}
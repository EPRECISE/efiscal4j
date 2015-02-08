package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Tributação pelo COFINS 73 - Operação de Aquisição a Alíquota Zero
 * 
 * @see BaseCOFINSOther
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS73 extends BaseCOFINSOther{

    private static final long serialVersionUID = 1L;
    
    public static class Builder extends BaseCOFINSOther.Builder implements COFINSBuilder {   
              
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (COFINS73.Builder) super.withBcValue(bcValue);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) { 
            return (COFINS73.Builder) super.withProductAliquot(productAliquot);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (COFINS73.Builder) super.withProductQuantity(productQuantity);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsAliquot(String cofinsAliquot) {
            return (COFINS73.Builder) super.withCofinsAliquot(cofinsAliquot);
        }               
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(String cofinsValue) {
            return (COFINS73.Builder) super.withCofinsValue(cofinsValue);
        }
      
        @Override
        public COFINS73 build() {
            return new COFINS73(this);
        }
    }

    protected COFINS73(COFINS73.Builder builder) {
        super(builder, "73");
    }
    
}

package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Tributação pelo COFINS 67 - Crédito Presumido - Outras Operações
 * 
 * @see BaseCOFINSOther
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS67 extends BaseCOFINSOther{

    private static final long serialVersionUID = 1L;
    
    public static class Builder extends BaseCOFINSOther.Builder implements COFINSBuilder {   
              
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (COFINS67.Builder) super.withBcValue(bcValue);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) { 
            return (COFINS67.Builder) super.withProductAliquot(productAliquot);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (COFINS67.Builder) super.withProductQuantity(productQuantity);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsAliquot(String cofinsAliquot) {
            return (COFINS67.Builder) super.withCofinsAliquot(cofinsAliquot);
        }               
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(String cofinsValue) {
            return (COFINS67.Builder) super.withCofinsValue(cofinsValue);
        }
      
        @Override
        public COFINS67 build() {
            return new COFINS67(this);
        }
    }
    protected COFINS67() {
        super(null, null);
    }
    
    protected COFINS67(COFINS67.Builder builder) {
        super(builder, "67");
    }
    
}

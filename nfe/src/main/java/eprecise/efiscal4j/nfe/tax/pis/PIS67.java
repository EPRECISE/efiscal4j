package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Tributação pelo PIS 67 - Crédito Presumido - Outras Operações
 * 
 * @see BasePISOther
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS67 extends BasePISOther{

    private static final long serialVersionUID = 1L;
    
    public static class Builder extends BasePISOther.Builder implements PISBuilder {   
              
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (PIS67.Builder) super.withBcValue(bcValue);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) { 
            return (PIS67.Builder) super.withProductAliquot(productAliquot);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (PIS67.Builder) super.withProductQuantity(productQuantity);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(String pisAliquot) {
            return (PIS67.Builder) super.withPisAliquot(pisAliquot);
        }               
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(String pisValue) {
            return (PIS67.Builder) super.withPisValue(pisValue);
        }
      
        @Override
        public PIS67 build() {
            return new PIS67(this);
        }
    }

    protected PIS67(PIS67.Builder builder) {
        super(builder, "67");
    }
    
}
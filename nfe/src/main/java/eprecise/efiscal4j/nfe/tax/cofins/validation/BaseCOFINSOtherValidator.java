
package eprecise.efiscal4j.nfe.tax.cofins.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @see BaseCOFINSOtherValidation
 * 
 * @author Felipe Bueno
 * 
 */
public class BaseCOFINSOtherValidator implements ConstraintValidator<BaseCOFINSOtherValidation, BaseCOFINSOtherStandard> {

    @Override
    public void initialize(BaseCOFINSOtherValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(BaseCOFINSOtherStandard baseCofinsOtherStandard, ConstraintValidatorContext context) {
        //@formatter:off      
        if ((baseCofinsOtherStandard.getBcValue() == null && baseCofinsOtherStandard.getCofinsAliquot() != null)
            || (baseCofinsOtherStandard.getBcValue() != null && baseCofinsOtherStandard.getCofinsAliquot() == null)) { 
            return false; 
        }
        
        if ((baseCofinsOtherStandard.getProductQuantity() == null && baseCofinsOtherStandard.getProductAliquot() != null)
                || (baseCofinsOtherStandard.getProductQuantity() != null && baseCofinsOtherStandard.getProductAliquot() == null)) { 
            return false; 
        }
        
        if ((baseCofinsOtherStandard.getProductQuantity() == null 
                && baseCofinsOtherStandard.getProductAliquot() == null
                && baseCofinsOtherStandard.getBcValue() == null
                && baseCofinsOtherStandard.getCofinsAliquot() == null)
                || (baseCofinsOtherStandard.getProductQuantity() != null 
                && baseCofinsOtherStandard.getProductAliquot() != null
                && baseCofinsOtherStandard.getBcValue() != null
                && baseCofinsOtherStandard.getCofinsAliquot() != null)) { 
            return false; 
        }
        
        return true;
        //@formatter:on  
    }
}

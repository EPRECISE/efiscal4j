
package eprecise.efiscal4j.nfe.v400.tax.ipi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @see BaseIPITribValidation
 * 
 * @author Felipe Bueno
 * 
 */
public class BaseIPITribValidator implements ConstraintValidator<BaseIPITribValidation, BaseIPITribStandard> {

    @Override
    public void initialize(BaseIPITribValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(BaseIPITribStandard baseIpiTribStandard, ConstraintValidatorContext context) {
        //@formatter:off      
        if ((baseIpiTribStandard.getBcValue() == null && baseIpiTribStandard.getIpiAliquot() != null)
            || (baseIpiTribStandard.getBcValue() != null && baseIpiTribStandard.getIpiAliquot() == null)) { 
            return false; 
        }
        
        if ((baseIpiTribStandard.getUnityQuantity() == null && baseIpiTribStandard.getUnityValue() != null)
                || (baseIpiTribStandard.getUnityQuantity() != null && baseIpiTribStandard.getUnityValue() == null)) { 
            return false; 
        }
        
        if ((baseIpiTribStandard.getUnityQuantity() == null 
                && baseIpiTribStandard.getUnityValue() == null
                && baseIpiTribStandard.getBcValue() == null
                && baseIpiTribStandard.getIpiAliquot() == null)
                || (baseIpiTribStandard.getUnityValue() != null 
                && baseIpiTribStandard.getIpiAliquot() != null
                && baseIpiTribStandard.getBcValue() != null
                && baseIpiTribStandard.getIpiAliquot() != null)) { 
            return false; 
        }
        
        return true;
        //@formatter:on  
    }
}

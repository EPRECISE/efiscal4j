package eprecise.efiscal4j.nfe.tax.pis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validador do BeanValidation que verifica se todos os dados referentes a ICMS ST com retenção (Base de Cálculo e Valor) estão preenchidos
 * 
 * @author Felipe Bueno
 * 
 */
public class BasePISOtherValidator implements ConstraintValidator<BasePISOtherValidation, BasePISOtherStandard> {

    @Override
    public void initialize(BasePISOtherValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(BasePISOtherStandard basePisOtherStandard, ConstraintValidatorContext context) {
        //@formatter:off      
        if ((basePisOtherStandard.getBcValue() == null && basePisOtherStandard.getPisAliquot() != null)
            || (basePisOtherStandard.getBcValue() != null && basePisOtherStandard.getPisAliquot() == null)) { 
            return false; 
        }
        
        if ((basePisOtherStandard.getProductQuantity() == null && basePisOtherStandard.getProductAliquot() != null)
                || (basePisOtherStandard.getProductQuantity() != null && basePisOtherStandard.getProductAliquot() == null)) { 
            return false; 
        }
        
        if ((basePisOtherStandard.getProductQuantity() == null 
                && basePisOtherStandard.getProductAliquot() == null
                && basePisOtherStandard.getBcValue() == null
                && basePisOtherStandard.getPisAliquot() == null)
                || (basePisOtherStandard.getProductQuantity() != null 
                && basePisOtherStandard.getProductAliquot() != null
                && basePisOtherStandard.getBcValue() != null
                && basePisOtherStandard.getPisAliquot() != null)) { 
            return false; 
        }
        
        return true;
        //@formatter:on  
    }
}

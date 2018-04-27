
package eprecise.efiscal4j.nfe.v310.tax.icms.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Validador do BeanValidation que verifica se todos os dados referentes a ICMS ST com retenção (Base de Cálculo e Valor) estão preenchidos
 * 
 * @author Felipe Bueno
 * 
 */
public class ICMSSTRetainedValidator implements ConstraintValidator<ICMSSTRetainedValidation, ICMSSTRetained> {

	@Override
	public void initialize(ICMSSTRetainedValidation constraintAnnotation) {
	}

	@Override
	public boolean isValid(ICMSSTRetained icmsWithICMSSTRetained, ConstraintValidatorContext context) {
		//@formatter:off      
		if ((icmsWithICMSSTRetained.getBcRetainedValueST() == null && icmsWithICMSSTRetained.getIcmsRetainedValueST() != null)
			|| (icmsWithICMSSTRetained.getBcRetainedValueST() != null && icmsWithICMSSTRetained.getIcmsRetainedValueST() == null)) { 
			return false; 
		}
		return true;
		//@formatter:on  
	}
}

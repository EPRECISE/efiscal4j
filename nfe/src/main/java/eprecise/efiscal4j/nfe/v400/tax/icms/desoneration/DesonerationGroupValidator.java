
package eprecise.efiscal4j.nfe.v400.tax.icms.desoneration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Validador do BeanValidation que verifica se todos os dados do grupo de desoneração estão preenchidos, ou todos não estão (não permite preencher apenas alguns dados desse grupo)
 * 
 * @author Felipe Bueno
 * 
 */
public class DesonerationGroupValidator implements ConstraintValidator<DesonerationGroupValidation, DesonerationGroup> {

	@Override
	public void initialize(DesonerationGroupValidation constraintAnnotation) {
	}

	@Override
	public boolean isValid(DesonerationGroup icmsWithDesonerationGroup, ConstraintValidatorContext context) {
		//@formatter:off      
		if ((icmsWithDesonerationGroup.getIcmsDesonerationValue() == null && icmsWithDesonerationGroup.getIcmsDesonerationReason() != null)
			|| (icmsWithDesonerationGroup.getIcmsDesonerationValue() != null && icmsWithDesonerationGroup.getIcmsDesonerationReason() == null)) { 
			return false; 
		}
		return true;
		//@formatter:on  
	}
}

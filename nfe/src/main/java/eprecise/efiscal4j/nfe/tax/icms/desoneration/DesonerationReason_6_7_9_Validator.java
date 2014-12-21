
package eprecise.efiscal4j.nfe.tax.icms.desoneration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DesonerationReason_6_7_9_Validator implements ConstraintValidator<ICMSDesonerationReason_6_7_9_Validation, DesonerationGroup> {

	@Override
	public void initialize(ICMSDesonerationReason_6_7_9_Validation constraintAnnotation) {
	}

	@Override
	public boolean isValid(DesonerationGroup icmsWithDesonerationGroup, ConstraintValidatorContext context) {

		//@formatter:off
		if (icmsWithDesonerationGroup.getIcmsDesonerationReason() == null) { 
			return true; 
		}

		switch (icmsWithDesonerationGroup.getIcmsDesonerationReason()) {
		case UTILITARIO_MOTOCICLETAS_AREA_LIVRE_COMERCIO:
		case SUFRAMA:
		case OUTROS:		
			return true;
		default:
			return false;
		}	
		//@formatter:on
	}
}

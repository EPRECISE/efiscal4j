
package eprecise.efiscal4j.nfe.v400.tax.icms.desoneration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DesonerationReason_3_9_12_Validator implements ConstraintValidator<ICMSDesonerationReason_3_9_12_Validation, DesonerationGroup> {

	@Override
	public void initialize(ICMSDesonerationReason_3_9_12_Validation constraintAnnotation) {
	}

	@Override
	public boolean isValid(DesonerationGroup icmsWithDesonerationGroup, ConstraintValidatorContext context) {

		//@formatter:off
		if (icmsWithDesonerationGroup.getIcmsDesonerationReason() == null) { 
			return true; 
		}

		switch (icmsWithDesonerationGroup.getIcmsDesonerationReason()) {
		case PRODUTOR_AGROPECUARIO:
		case OUTROS:
		case FOMENTO_AGROPECUARIO:
			return true;
		default:
			return false;
		}	
		//@formatter:on
	}
}

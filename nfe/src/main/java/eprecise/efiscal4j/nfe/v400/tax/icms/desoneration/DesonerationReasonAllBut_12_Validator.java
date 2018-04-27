
package eprecise.efiscal4j.nfe.v400.tax.icms.desoneration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DesonerationReasonAllBut_12_Validator implements ConstraintValidator<ICMSDesonerationReasonAllBut_12_Validation, DesonerationGroup> {

	@Override
	public void initialize(ICMSDesonerationReasonAllBut_12_Validation constraintAnnotation) {
	}

	@Override
	public boolean isValid(DesonerationGroup icmsWithDesonerationGroup, ConstraintValidatorContext context) {

		//@formatter:off
		if (icmsWithDesonerationGroup.getIcmsDesonerationReason() == null) { 
			return true; 
		}

		switch (icmsWithDesonerationGroup.getIcmsDesonerationReason()) {		
		case FOMENTO_AGROPECUARIO:
			return false;
		default:
			return true;
		}	
		//@formatter:on
	}
}

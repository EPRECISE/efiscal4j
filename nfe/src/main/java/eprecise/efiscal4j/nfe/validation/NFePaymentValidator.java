
package eprecise.efiscal4j.nfe.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import eprecise.efiscal4j.nfe.FiscalDocumentModel;
import eprecise.efiscal4j.nfe.NFeInfo;


/**
 * Validador do BeanValidation que verifica se o NFePayment será obrigatório, caso o documento seja NFCE
 * 
 * @author Felipe Bueno
 * 
 */
public class NFePaymentValidator implements ConstraintValidator<NFePaymentValidation, NFeInfo> {

	@Override
	public void initialize(NFePaymentValidation constraintAnnotation) {
	}

	@Override
	public boolean isValid(NFeInfo nFeInfo, ConstraintValidatorContext context) {
		//@formatter:off      
		if (nFeInfo.getnFeIdentification().getFiscalDocumentModel().equals(FiscalDocumentModel.NFCE) 
				&& (nFeInfo.getnFePayments() == null || nFeInfo.getnFePayments().isEmpty())) { 
			return false; 
		}
		return true;
		//@formatter:on  
	}
}

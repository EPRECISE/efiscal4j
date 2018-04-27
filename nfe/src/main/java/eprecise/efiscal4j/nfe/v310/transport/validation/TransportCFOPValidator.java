
package eprecise.efiscal4j.nfe.v310.transport.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import eprecise.efiscal4j.nfe.v310.transport.TransportICMSRetention;


/**
 * Validador do BeanValidation que valida se o CFOP informado é de transporte
 * 
 * @author Felipe Bueno
 * 
 */
public class TransportCFOPValidator implements ConstraintValidator<TransportCFOPValidation, TransportICMSRetention> {

	@Override
	public void initialize(TransportCFOPValidation constraintAnnotation) {
	}

	@Override
	public boolean isValid(TransportICMSRetention transportICMSRetention, ConstraintValidatorContext context) {
		return transportICMSRetention.getCfop().isTransport();
	}
}

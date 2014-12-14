
package eprecise.efiscal4j.nfe.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import eprecise.efiscal4j.nfe.Receiver;
import eprecise.efiscal4j.nfe.StateRegistrationReceiverIndicator;


/**
 * Validador do BeanValidation que verifica se a stateRegistration será obrigatória para o Receiver
 * 
 * @author Felipe Bueno
 * 
 */
public class StateRegistrationValidator implements ConstraintValidator<StateRegistrationValidation, Receiver> {

	@Override
	public void initialize(StateRegistrationValidation constraintAnnotation) {
	}

	@Override
	public boolean isValid(Receiver receiver, ConstraintValidatorContext context) {
		//@formatter:off      
		if (receiver.getStateRegistrationReceiverIndicator().equals(StateRegistrationReceiverIndicator.CONTRIBUINTE_ICMS) && receiver.getDocuments().getStateRegistration() == null) { 
			return false; 
		}
		return true;
		//@formatter:on  
	}
}


package eprecise.efiscal4j.nfe.v400.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.v400.StateRegistrationReceiverIndicator;
import eprecise.efiscal4j.nfe.v400.address.City;
import eprecise.efiscal4j.nfe.v400.person.Receiver;


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
        if(Optional.ofNullable(receiver.getAdress()).map(r->r.getCity()).map(City::getUf).filter(UF.EX::equals).isPresent()) {
            return true;
        } else if ((receiver.getStateRegistrationReceiverIndicator() == null) 
                || (receiver.getStateRegistrationReceiverIndicator().equals(StateRegistrationReceiverIndicator.CONTRIBUINTE_ICMS) 
                        && receiver.getDocuments().getStateRegistration() == null)) { 
            return false; 
        }	        
	    
		return true;
		//@formatter:on  
    }
}

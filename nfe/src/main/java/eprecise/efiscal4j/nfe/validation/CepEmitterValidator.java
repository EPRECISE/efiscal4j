
package eprecise.efiscal4j.nfe.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import eprecise.efiscal4j.nfe.person.Emitter;


/**
 * Validador do BeanValidation que verifica se o CEP será obrigatório para o Emitter
 * 
 * @author Felipe Bueno
 * 
 */
public class CepEmitterValidator implements ConstraintValidator<CepEmitterValidation, Emitter> {

    @Override
    public void initialize(CepEmitterValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(Emitter emitter, ConstraintValidatorContext context) {
        if (emitter.getAdress().getCep() == null) {
            return false;
        }

        return true;
    }
}

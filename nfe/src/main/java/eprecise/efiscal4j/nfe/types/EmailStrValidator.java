
package eprecise.efiscal4j.nfe.types;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;


/**
 * Valida se o tamanho do campo numérico está no intervalo {min} e {max}
 * 
 * @author Fernando Glizt
 * 
 */
public class EmailStrValidator implements ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email email) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return EmailValidator.getInstance().isValid(email);
    }
}


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
    public void initialize(final Email email) {
    }

    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        if (email == null) {
            return true;
        }
        return EmailValidator.getInstance().isValid(email);
    }
}

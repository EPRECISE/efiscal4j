
package eprecise.efiscal4j.nfe.types;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Valida se o tamanho do campo numérico está no intervalo {min} e {max}
 * 
 * @author Fernando Glizt
 * 
 */
public class NumberSizeValidator implements ConstraintValidator<NumberSize, Number> {

    private NumberSize constraintAnnotation;

    @Override
    public void initialize(NumberSize constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;

    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext context) {
        return number == null || (number != null && number.toString().length() <= constraintAnnotation.max() && number.toString().length() >= constraintAnnotation.min());
    }
}

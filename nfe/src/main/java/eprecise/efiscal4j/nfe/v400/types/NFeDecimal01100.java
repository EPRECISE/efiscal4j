package eprecise.efiscal4j.nfe.v400.types;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

/**
 *
 * Tipo Decimal com até 2 dígitos inteiros ou igual à 100, sem casas decimais.
 *
 * @author Pedro H. Rodrigues
 *
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "^([1-9]\\d?|100)$")
public @interface NFeDecimal01100 {

    String message() default "Viola restrição - Tipo Decimal com até 2 dígitos inteiros ou igual à 100, sem casas decimais.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}


package eprecise.efiscal4j.nfe.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Valida se o mail está em formato válido
 * 
 * @author Fernando Glizto
 */
@Target({ FIELD, ANNOTATION_TYPE, })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailStrValidator.class)
@Documented
public @interface Email {

    String message() default "O e-mail é inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

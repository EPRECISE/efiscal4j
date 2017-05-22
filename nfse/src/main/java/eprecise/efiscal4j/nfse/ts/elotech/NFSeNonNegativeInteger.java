
package eprecise.efiscal4j.nfse.ts.elotech;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;


/**
 * nonNegativeInteger
 *
 * Tipo Inteiro com valores não negativos
 *
 * @author Fernando C Glizt
 *
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "^[0-9]\\d*$")
public @interface NFSeNonNegativeInteger {

    String message() default "Viola restrição - Tipo Inteiro com valor não negativo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

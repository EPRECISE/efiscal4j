
package eprecise.efiscal4j.nfse.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;


/**
 * nonNegativeInteger
 *
 * Tipo Inteiro com valor entre 1 e 999999
 *
 * @author Fernando C Glizt
 *
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Size(min = 1, max = 999999)
public @interface NFSeNonNegativeInteger {

    String message() default "Viola restrição - Tipo Inteiro com valor entre 1 e 999999";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

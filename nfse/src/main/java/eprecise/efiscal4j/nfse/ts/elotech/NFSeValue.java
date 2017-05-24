
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
 * tsValor
 *
 * Tipo Decimal com 13 inteiros e 2 decimais
 *
 * @author Fernando C Glizt
 *
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "^(\\d{1,13})(?:.\\d{1,2})?$")
public @interface NFSeValue {

    String message() default "Viola restrição - Tipo Decimal com 15 dígitos, sendo 13 de corpo e 2 decimais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

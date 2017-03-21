
package eprecise.efiscal4j.nfse.types;

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
 * tsValorUnitario
 * 
 * Tipo Decimal com 14 inteiros e 5 decimais
 * 
 * @author Fernando C Glizt
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "^(?:\\d{1,3}(?:\\.\\d{3}){0,4}|\\d{1,14})(?:,\\d{1,5})?$")
public @interface NFSeUnitaryValue {

    String message() default "Viola restrição - Tipo Decimal com 14 inteiros e 5 decimais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

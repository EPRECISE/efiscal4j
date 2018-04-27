
package eprecise.efiscal4j.nfe.v400.types;

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
 * TDec_1204
 * 
 * Tipo Decimal com 12 inteiros e 4 decimais
 * 
 * @author Felipe Bueno
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "0|0\\.[0-9]{1,4}|[1-9]{1}[0-9]{0,11}|[1-9]{1}[0-9]{0,11}(\\.[0-9]{4})?")
public @interface NFeDecimal1204 {

    String message() default "Viola restrição - Tipo Decimal com 12 inteiros e 4 decimais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

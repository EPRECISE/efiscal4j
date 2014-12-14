
package eprecise.efiscal4j.nfe.types;

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
 * TDec_0302a04
 * 
 * Tipo Decimal com até 3 dígitos inteiros, podendo ter de 2 até 4 decimais
 * 
 * @author Clécius J. Martinkoski
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "0|0\\.[0-9]{2,4}|[1-9]{1}[0-9]{0,2}(\\.[0-9]{2,4})?")
public @interface NFeDecimal0302a04 {

	String message() default "Viola restrição - Tipo Decimal com até 3 dígitos inteiros, podendo ter de 2 até 4 decimais";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

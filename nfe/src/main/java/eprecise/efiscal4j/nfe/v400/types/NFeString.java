
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
 * TString
 * 
 * Tipo string genérico
 * 
 * @author Felipe Bueno
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}")
public @interface NFeString {

	String message() default "Viola restrição - Tipo string genérico";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

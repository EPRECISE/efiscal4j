
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
 * TNF
 * 
 * Tipo Número do Documento Fiscal
 * 
 * @author Felipe Bueno
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "[1-9]{1}[0-9]{0,8}")
public @interface NFeFiscalDocumentNumber {

	String message() default "Viola restrição - Tipo Número do Documento Fiscal";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

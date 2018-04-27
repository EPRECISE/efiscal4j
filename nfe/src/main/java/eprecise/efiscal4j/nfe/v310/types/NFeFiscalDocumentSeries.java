
package eprecise.efiscal4j.nfe.v310.types;

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
 * TSerie
 * 
 * Tipo Série do Documento Fiscal
 * 
 * @author Felipe Bueno
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "0|[1-9]{1}[0-9]{0,2}")
public @interface NFeFiscalDocumentSeries {

	String message() default "Viola restrição - Tipo Série do Documento Fiscal";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}


package eprecise.efiscal4j.nfe.transport.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Valida se o CFOP informado é de transporte
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransportCFOPValidator.class)
@Documented
public @interface TransportCFOPValidation {

	String message() default "O CFOP informado não é de transporte.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

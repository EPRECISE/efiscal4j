
package eprecise.efiscal4j.nfe.transport.validations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Valida se o CFOP é de transporte
 * 
 * @author Fernando Glizt
 */
@Target({ FIELD, ANNOTATION_TYPE,  })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransportCFOPValidator.class)
@Documented
public @interface TransportCFOP {

	String message() default "O CFOP não é de transporte.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

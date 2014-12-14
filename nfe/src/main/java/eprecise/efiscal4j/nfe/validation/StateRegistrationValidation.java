
package eprecise.efiscal4j.nfe.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Valida se a inscrição estadual do destinatário será obrigatória
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StateRegistrationValidator.class)
@Documented
public @interface StateRegistrationValidation {

	String message() default "O campo stateRegistration é obrigatório caso o campo stateRegistrationReceiverIndicator seja = 1";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

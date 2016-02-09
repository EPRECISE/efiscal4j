
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
 * Valida se o CEP será obrigatório no endereço do Emitter
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CepEmitterValidator.class)
@Documented
public @interface CepEmitterValidation {

    String message() default "O campo cep é obrigatório para o endereço do emitente";

    Class<?>[]groups() default {};

    Class<? extends Payload>[]payload() default {};

}

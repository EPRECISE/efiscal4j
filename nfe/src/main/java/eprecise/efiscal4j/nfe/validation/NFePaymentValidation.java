
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
 * Valida se os dados de pagamento serão obrigatórios
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NFePaymentValidator.class)
@Documented
public @interface NFePaymentValidation {

	String message() default "O campo nFePayments é obrigatório caso o modelo do documento seja NFCE";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

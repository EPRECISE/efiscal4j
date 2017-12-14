
package eprecise.efiscal4j.nfe.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;


/**
 * 
 * 
 * Tipo Inscrição Estadual do Emitente // alterado EM 24/10/08 para aceitar ISENTO
 * 
 * 
 * @author Luan Bukowitz Beluzzo
 * 
 */

@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Max(14)
@Pattern(regexp = "[0-9]{2,14}|ISENTO")
public @interface NFeDeliveryDFeStateRegistration {

    String message() default "Viola restrição - Inscrição Estadual";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

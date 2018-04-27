
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
import javax.validation.constraints.Size;


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
@Size(max = 14)
@Pattern(regexp = "[0-9]{2,14}|ISENTO")
public @interface NFeDeliveryDFeStateRegistration {

    String message() default "Viola restrição - Inscrição Estadual";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

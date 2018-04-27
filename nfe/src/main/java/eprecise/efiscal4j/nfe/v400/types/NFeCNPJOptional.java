
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
import javax.validation.constraints.Size;


/**
 * TCnpjOpc
 * 
 * Tipo Número do CNPJ Opcional
 * 
 * @author Felipe Bueno
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Size(max = 14)
@Pattern(regexp = "[0-9]{0}|[0-9]{14}")
public @interface NFeCNPJOptional {

    String message() default "Viola restrição - Tipo Número do CNPJ Opcional";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

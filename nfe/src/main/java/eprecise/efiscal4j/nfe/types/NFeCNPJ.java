
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
import javax.validation.constraints.Size;


/**
 * TCnpj
 * 
 * Tipo Número do CNPJ
 * 
 * @author Felipe Bueno
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Size(max = 14)
@Pattern(regexp = "[0-9]{14}")
public @interface NFeCNPJ {

	String message() default "Viola restrição - Tipo Número do CNPJ";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}


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


/**
 * 
 * 
 * Conteúdo de Retorno de Documento de NFeDeliveryDFe
 * 
 * 
 * @author Luan Bukowitz Beluzzo
 * 
 */

@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "^[-A-Za-z0-9+=]{1,50}|=[^=]|={3,}$")
public @interface NFeDeliveryDFeBase64DocumentContent {

    String message() default "Viola restrição - Conteúdo com Base64";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

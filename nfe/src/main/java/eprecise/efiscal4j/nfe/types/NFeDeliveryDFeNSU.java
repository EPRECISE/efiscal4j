
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
 * TNSU
 * 
 * Tipo número sequencial único
 * 
 * 
 * @author Clécius J. Martinkoski
 * 
 */

@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "[0-9]{15}")
public @interface NFeDeliveryDFeNSU {

    String message() default "Viola restrição - Tipo de NSU";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}


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


/**
 * 
 * Placa de Veículo
 * 
 * @author Fernando C Glizt
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "[A-Z]{2,3}[0-9]{4}|[A-Z]{3,4}[0-9]{3}")
public @interface NFeLicensePlate {

    String message() default "Viola restrição - Placa do veículo inválida";

    Class<?>[]groups() default {};

    Class<? extends Payload>[]payload() default {};
}

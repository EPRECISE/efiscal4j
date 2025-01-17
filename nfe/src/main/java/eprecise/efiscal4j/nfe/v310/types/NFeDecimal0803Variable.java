
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
 * TDec_0803v
 *
 * Tipo Decimal com 8 inteiros, podendo ter de 1 até 3 decimais
 *
 * @author Fernando C Glizt
 *
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "0|0\\.[0-9]{3}|[1-9]{1}[0-9]{0,7}(\\.[0-9]{1,3})?")
public @interface NFeDecimal0803Variable {

    String message() default "Viola restrição - Tipo Decimal com 8 inteiros, podendo ter de 1 até 3 decimais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

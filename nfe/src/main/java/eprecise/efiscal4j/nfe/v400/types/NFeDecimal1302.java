
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


/**
 * TDec_1302
 *
 * Tipo Decimal com 15 dígitos, sendo 13 de corpo e 2 decimais
 *
 * @author Clécius J. Martinkoski
 *
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "0|0\\.[0-9]{2}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?")
public @interface NFeDecimal1302 {

    String message() default "Viola restrição - Tipo Decimal com 15 dígitos, sendo 13 de corpo e 2 decimais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

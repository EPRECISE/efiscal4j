
package eprecise.efiscal4j.nfse.ts.elotech;

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
 * tsAliquota
 * 
 * Tipo Decimal com 5 inteiros e 4 decimais
 * 
 * @author Clécius J. Martinkoski
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "^(\\d{1,5})(?:.\\d{1,4})?$")
public @interface NFSeAliquot {

    String message() default "Viola restrição - Tipo Decimal com até 5 dígitos inteiros, podendo ter até 4 decimais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


package eprecise.efiscal4j.nfse.ts.govbr.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;


@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "^(\\d{1,4})(?:.\\d{1,2})?$")
public @interface NFSeAliquot {

    String message() default "Viola restrição - Tipo Decimal com até 4 dígitos inteiros, podendo ter até 2 decimais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


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
@Pattern(regexp = "^(\\d{1,15})(?:.\\d{2})?$")
public @interface NFSeValue {

    String message() default "Viola restrição - Tipo Decimal com até 15 dígitos de corpo e com 2 dígitos decimais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

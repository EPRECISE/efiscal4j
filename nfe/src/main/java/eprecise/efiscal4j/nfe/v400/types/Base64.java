
package eprecise.efiscal4j.nfe.v400.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;


@Target({ FIELD, ANNOTATION_TYPE, TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "(([A-Za-z0-9+\\/]{4})*([A-Za-z0-9+\\/]{3}=|[A-Za-z0-9+\\/]{2}==)?){1}")
public @interface Base64 {

    String message() default "Viola restrição - Conteúdo com Base64";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

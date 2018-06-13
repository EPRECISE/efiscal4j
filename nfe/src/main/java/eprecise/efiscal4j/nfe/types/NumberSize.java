
package eprecise.efiscal4j.nfe.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Valida se o tamanho do campo numérico está no intervalo {min} e {max}
 * 
 * @author Fernando Glizt
 */
@Target({ FIELD, ANNOTATION_TYPE, })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberSizeValidator.class)
@Documented
public @interface NumberSize {

    String message() default "O tamanho não pode ser menor que {min} ou maior que {max}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public abstract int min() default (int) 0;

    public abstract long max() default (long) 999999999999999999l;

}

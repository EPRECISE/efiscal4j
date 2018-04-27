
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
@Pattern(regexp = "[0-9]{3}")
public @interface NFeStatus {

    String message() default "Viola restrição - Status";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Converter implements TypeConverter<String, Integer> {

        @Override
        public Integer parse(String source) {
            return Integer.parseInt(source);
        }

        @Override
        public String serialize(Integer data) {
            return data == null ? null : String.format("%03d", data);
        }

    }

}

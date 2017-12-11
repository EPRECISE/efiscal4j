
package eprecise.efiscal4j.nfe.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;


/**
 * TDateTimeUTC
 * 
 * Data e Hora, formato UTC (AAAA-MM-DDThh:mm:ssTZD, onde TZD = +hh:mm ou -hh:mm)
 * 
 * @author Felipe Bueno
 * 
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(
        regexp = "(((20(([02468][048])|([13579][26]))-02-29))|(20[0-9][0-9])-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))T(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d[\\-,\\+](0[0-9]|10|11|12):00")
public @interface NFeDateTimeUTC {

    String message() default "Viola restrição - Data e Hora, formato UTC (AAAA-MM-DDThh:mm:ssTZD, onde TZD = +hh:mm ou -hh:mm)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Converter implements TypeConverter<String, ZonedDateTime> {

        @Override
        public ZonedDateTime parse(String source) {
            return ZonedDateTime.parse(source);
        }

        @Override
        public String serialize(ZonedDateTime data) {
            return data == null ? null : data.withNano(0).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

    }
}

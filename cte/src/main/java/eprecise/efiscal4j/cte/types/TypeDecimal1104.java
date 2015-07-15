package eprecise.efiscal4j.cte.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

/**
 * T
 * 
 * Tipo Decimal com 15 dígitos, sendo 13 de corpo e 2 decimais
 * 
 * @author Carlos Gomes
 * 
 */

@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "0|0\\.[0-9]{4}|[1-9]{1}[0-9]{0,10}(\\.[0-9]{4})?")
public @interface TypeDecimal1104 {
    public static final DecimalFormat FORMAT = new DecimalFormat("##0.0000", new DecimalFormatSymbols(Locale.ENGLISH));
    
    String message() default "Viola restrição -";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}

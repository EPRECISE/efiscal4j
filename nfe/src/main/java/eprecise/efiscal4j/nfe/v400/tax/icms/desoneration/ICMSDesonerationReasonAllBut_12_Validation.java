
package eprecise.efiscal4j.nfe.v400.tax.icms.desoneration;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Verifica se o motivo da desoneração não é 12 - Fomento Agropecuário
 * 
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DesonerationReasonAllBut_12_Validator.class)
@Documented
public @interface ICMSDesonerationReasonAllBut_12_Validation {

	String message() default "O motivo da desoneração de ICMS precisa ser diferente de 12 – Fomento agropecuário";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

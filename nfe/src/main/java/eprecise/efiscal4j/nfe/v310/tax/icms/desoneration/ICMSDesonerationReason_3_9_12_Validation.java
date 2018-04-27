
package eprecise.efiscal4j.nfe.v310.tax.icms.desoneration;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Verifica se o motivo da desoneração é um dos seguintes: 3 – Produtor Agropecuário; 9 – Outros; 12-Fomento agropecuário;
 * 
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DesonerationReason_3_9_12_Validator.class)
@Documented
public @interface ICMSDesonerationReason_3_9_12_Validation {

	String message() default "O motivo da desoneração de ICMS precisa ser 3 – Produtor Agropecuário; 9 – Outros; 12-Fomento agropecuário";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

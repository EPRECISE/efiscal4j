
package eprecise.efiscal4j.nfe.tax.icms.desoneration;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Verifica se o motivo da desoneração é um dos seguintes: <li>
 * 6 – Utilitários e Motocicletas da Amazônia Ocidental e Áreas de Livre Comércio (Resolução 714/88 e 790/94 – CONTRAN e suas alterações); 7 – SUFRAMA; 9 – Outros;</li>
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DesonerationReason_6_7_9_Validator.class)
@Documented
public @interface ICMSDesonerationReason_6_7_9_Validation {

	String message() default "O motivo da desoneração de ICMS precisa ser 6 – Utilitários e Motocicletas da Amazônia Ocidental e Áreas de Livre Comércio (Resolução 714/88 e 790/94 – CONTRAN e suas alterações);"
			+ "7 – SUFRAMA;" + "9 – Outros;";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

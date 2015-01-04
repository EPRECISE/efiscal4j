
package eprecise.efiscal4j.nfe.tax.icms;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Verifica se os dados padrões do grupo ICMS ST estão preenchidos
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ICMSST90Validator.class)
@Documented
public @interface ICMSST90Validation {

	String message() default "Não é possível preencher apenas alguns campos referentes ao grupo de ICMS ST (modBCST, pMVAST, pRedBCST, vBCST, pICMSST, vICMSST). Preencha os campos básicos obrigatórios de ICMS ST ou não informe nenhum deles";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

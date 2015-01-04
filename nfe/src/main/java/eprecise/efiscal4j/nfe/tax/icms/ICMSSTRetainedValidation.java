
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
 * Verifica se todos os dados referentes a ICMS ST com retenção (Base de Cálculo e Valor) estão preenchidos
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ICMSSTRetainedValidator.class)
@Documented
public @interface ICMSSTRetainedValidation {

	String message() default "Não é possível preencher apenas alguns campos referentes à retenção de ICMS ST. Preencha a base de cálculo e o valor do ICMS ST retido (vBCSTRet, vICMSSTRet)  ou não informe nenhum deles";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

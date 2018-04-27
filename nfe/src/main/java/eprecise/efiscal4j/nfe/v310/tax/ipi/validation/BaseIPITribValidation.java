
package eprecise.efiscal4j.nfe.v310.tax.ipi.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Verifica se todos os dados de IPI padrões (vBC, pIPI) ou de unidade tributável (qUnid, vUnid) estão preenchidos
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BaseIPITribValidator.class)
@Documented
public @interface BaseIPITribValidation {

    String message() default "Não é possível preencher apenas alguns campos referentes ao grupo de CST padrão (vBC, pIPI) ou de unidade tributável (qUnid, vUnid). Preencha apenas dados do grupo padrão ou apenas do grupo de unidade tributável";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}


package eprecise.efiscal4j.nfe.tax.cofins.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Verifica se todos os dados referentes ao grupo de CST de quantidade (qBCProd, vAliqProd) ou de alíquota (vBC, pCOFINS) estão preenchidos
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BaseCOFINSOtherValidator.class)
@Documented
public @interface BaseCOFINSOtherValidation {

    String message() default "Não é possível preencher apenas alguns campos referentes ao grupo de CST de quantidade (qBCProd, vAliqProd) ou de alíquota (vBC, pCOFINS). Preencha apenas dados do grupo de quantidade ou apenas do grupo de alíquota";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

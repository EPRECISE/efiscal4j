
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
 * Verifica se todos os dados do grupo de desoneração estão preenchidos, ou todos não estão (não permite preencher apenas alguns dados desse grupo)
 * 
 * @author Felipe Bueno
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DesonerationGroupValidator.class)
@Documented
public @interface DesonerationGroupValidation {

	String message() default "Não é possível preencher apenas alguns dados do grupo de desoneração. Preencha o Motivo e o Valor da desoneração ou não informe nenhum deles";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

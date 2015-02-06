package eprecise.efiscal4j.cte.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.constraints.Pattern;

/**
 *
 * 
 * Tipo Inscrição Estadual do Destinatário
 * 
 * Informar a IE do tomador ou ISENTO se tomador é contribuinte do ICMS isento
 * de inscrição no cadastro de contribuintes do ICMS. Caso o tomador não seja
 * contribuinte do ICMS não informar o conteúdo
 * 
 * 
 * @author Carlos Gomes
 * 
 */

@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "[0-9]{0,14}|ISENTO")
public @interface StateRegistration {
    
}

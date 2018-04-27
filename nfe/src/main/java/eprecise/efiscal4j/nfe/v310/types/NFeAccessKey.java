
package eprecise.efiscal4j.nfe.v310.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;


/**
 * TChNFe
 * 
 * Chave de acesso da NF-e, composta por:
 * 
 * <ul>
 * <li>UF do emitente</li>
 * <li>AAMM da emissão da NF-e</li>
 * <li>CNPJ do emitente</li>
 * <li>Modelo da NF-e</li>
 * <li>Série da NF-e</li>
 * <li>Número da NF-e</li>
 * <li>Código Numérico</li>
 * <li>Dígito Verificador</li>
 * </ul>
 * 
 * @author Felipe Bueno
 * 
 */

@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "[0-9]{44}")
public @interface NFeAccessKey {

    String message() default "Viola restrição - Chave de acesso da NF-e";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

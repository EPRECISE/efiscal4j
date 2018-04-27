
package eprecise.efiscal4j.nfe.v400.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * 
 * 
 * Número do Protocolo do evento: 1 posição (1 – Secretaria de Fazenda Estadual 2 – Receita Federal) 2 - códiga da UF 2 posições ano 10 seqüencial no ano
 * 
 * 
 * @author Luan Bukowitz Beluzzo
 * 
 */

@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Size(max = 15)
@Pattern(regexp = "[0-9]{15}")
public @interface NFeDeliveryDFeEventProtocolNumber {

    String message() default "Viola restrição - Número do Protocolo do Evento";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Converter implements TypeConverter<String, Long> {

        @Override
        public Long parse(String source) {
            return Long.parseLong(source);
        }

        @Override
        public String serialize(Long data) {
            return data == null ? null : String.format("%015d", data);
        }

    }

}

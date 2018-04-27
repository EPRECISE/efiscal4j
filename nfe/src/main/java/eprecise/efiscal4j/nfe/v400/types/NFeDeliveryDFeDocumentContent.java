
package eprecise.efiscal4j.nfe.v400.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Base64;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.nfe.v400.utils.ByteArrayCompressor;
import eprecise.efiscal4j.nfe.v400.utils.ByteArrayDecompressor;


/**
 * 
 * 
 * Conteúdo de Retorno de Documento de NFeDeliveryDFe
 * 
 * 
 * @author Luan Bukowitz Beluzzo
 * 
 */

@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@eprecise.efiscal4j.nfe.v400.types.Base64
public @interface NFeDeliveryDFeDocumentContent {

    String message() default "Viola restrição - Conteúdo com Base64";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Converter implements TypeConverter<String, String> {

        @Override
        public String parse(String source) {
            if (!StringUtils.isNotEmpty(source)) {
                return null;
            }

            try {
                return new ByteArrayDecompressor().decompress(Base64.getDecoder().decode(source));
            } catch (final IOException e) {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public String serialize(String data) {
            if (!StringUtils.isNotEmpty(data)) {
                return null;
            }

            try {
                return new String(Base64.getEncoder().encode(new ByteArrayCompressor().compress(data.getBytes("UTF-8"))), "UTF-8");
            } catch (final IOException e) {
                throw new IllegalArgumentException();
            }
        }

    }

}

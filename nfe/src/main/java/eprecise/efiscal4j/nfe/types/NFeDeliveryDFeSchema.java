
package eprecise.efiscal4j.nfe.types;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import eprecise.efiscal4j.nfe.deliveryDFe.response.NFeDeliveryDFeSchemas;


@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "resNFe_v1.00.xsd|procNFe_v3.10.xsd|resEvento_1.00.xsd|procEventoNFe_v1.00.xsd")
public @interface NFeDeliveryDFeSchema {

    String message() default "Viola restrição - Schema";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Converter implements TypeConverter<String, Optional<NFeDeliveryDFeSchemas>> {

        @Override
        public Optional<NFeDeliveryDFeSchemas> parse(String source) {
            return NFeDeliveryDFeSchemas.getFromSchema(source);
        }

        @Override
        public String serialize(Optional<NFeDeliveryDFeSchemas> data) {
            return data.isPresent() ? data.get().get() : null;
        }

    }

}

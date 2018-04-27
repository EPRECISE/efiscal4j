
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


/**
 *
 * Texto com o QR-Code impresso no DANFE NFC-e
 *
 * @author Fernando C Glizt
 *
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Pattern(
        regexp = "((HTTPS?|https?)://.*\\?chNFe=[0-9]{44}&amp;nVersao=[0-9]{3}&amp;tpAmb=[1-2](&amp;cDest=([A-Za-z0-9.:+-/)(]{0}|[A-Za-z0-9.:+-/)(]{5,20})?)?&amp;dhEmi=[A-Fa-f0-9]{50}&amp;vNF=(0|0\\.[0-9]{2}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?)&amp;vICMS=(0|0\\.[0-9]{2}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?)&amp;digVal=[A-Fa-f0-9]{56}&amp;cIdToken=[0-9]{6}&amp;cHashQRCode=[A-Fa-f0-9]{40})")
public @interface NFCeQrCode {

    String message() default "Viola restrição - Tipo Decimal com 11 inteiros, podendo ter 4 decimais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

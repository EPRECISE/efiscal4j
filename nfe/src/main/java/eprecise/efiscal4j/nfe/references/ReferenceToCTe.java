
package eprecise.efiscal4j.nfe.references;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;


/**
 * CT-e referenciada
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class ReferenceToCTe implements DocumentReference {

    /**
     * Chave de acesso do CT-e emitido anteriormente, vinculada a NF-e atual
     * 
     * @param accessKey
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToCTe.accessKey.isNotNull}") @Pattern(
            regexp = "[0-9]{44}", message = "{eprecise.efiscal4j.nfe.references.referenceToCTe.accessKey.isNotAccessKey}") final String accessKey;

}

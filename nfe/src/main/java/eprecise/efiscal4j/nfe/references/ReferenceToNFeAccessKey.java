
package eprecise.efiscal4j.nfe.references;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;


/**
 * Infromações da NF-e referenciada por chave de acesso
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class ReferenceToNFeAccessKey implements ReferenceToNFe {

    /**
     * Chave de acesso da NF-e emitido anteriormente, vinculada a NF-e atual
     * 
     * @param accessKey
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNFeAccessKey.accessKey.isNotNull}") @Pattern(
            regexp = "[0-9]{44}", message = "{eprecise.efiscal4j.nfe.references.referenceToNFeAccessKey.accessKey.isNotAccessKey}") final String accessKey;

}

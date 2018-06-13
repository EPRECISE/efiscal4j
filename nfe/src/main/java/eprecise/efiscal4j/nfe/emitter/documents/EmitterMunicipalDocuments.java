
package eprecise.efiscal4j.nfe.emitter.documents;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;


/**
 * Documentos do Emitente com informações de interesse da prefeitura
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class EmitterMunicipalDocuments {

    /**
     * Inscrição Municipal
     * 
     * @param im
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.documents.municipal.im.isNotNull}") @Size(
            min = 1, max = 15, message = "{eprecise.efiscal4j.nfe.emitter.documents.municipal.im.isSize}") final String im;

    /**
     * CNAE Fiscal
     * 
     * @param cnae
     */
    private @Pattern(regexp = "[0-9]{7}", message = "{eprecise.efiscal4j.nfe.emitter.documents.municipal.cnae.isNotCnae}") final String cnae;

}

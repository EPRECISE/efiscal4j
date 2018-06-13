
package eprecise.efiscal4j.nfe.emitter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import eprecise.efiscal4j.nfe.emitter.address.EmitterAddress;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterDocuments;
import lombok.Builder;
import lombok.Getter;

/**
 * Emitente do documento fiscal
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class Emitter {

    /**
     * @see EmitterDocuments
     * @param documents
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.documents.isNotNull}") @Valid final EmitterDocuments documents;

    /**
     * @see CRT
     * @param crt
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.crt.isNotNull}") @Valid CRT crt;
    
    /**
     * @see EmitterAddress
     * @param address
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.address.isNotNull}") @Valid final EmitterAddress address;
    
    /**
     * Telefone, preencher com Código DDD + número do telefone
     * 
     * @param phone
     */
    private @Pattern(regexp = "[0-9]{6,14}", message = "{eprecise.efiscal4j.nfe.emitter.phone.isNotPhone}") final String phone;

}

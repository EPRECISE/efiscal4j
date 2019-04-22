
package eprecise.efiscal4j.nfe.technicalManager;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;


/**
 * Código de Segurança do Responsável Técnico
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class CSRT implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID do Código de Segurança do Responsável Técnico
     * 
     * @param id
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.technicalManager.csrt.id.isNotNull}") @Pattern(regexp = "[0-9]{2}", message = "{eprecise.efiscal4j.nfe.technicalManager.csrt.id.isNotCsrtId}") final String id;
    
    /**
     * Chave do Código de Segurança do Responsável Técnico
     * 
     * @param key
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.technicalManager.csrt.key.isNotNull}") final String key;

}

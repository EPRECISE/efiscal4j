
package eprecise.efiscal4j.nfe.export;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import eprecise.efiscal4j.commons.domain.adress.UF;
import lombok.Builder;
import lombok.Getter;


/**
 * Define informações de exportação
 *
 */
@Builder
@Getter
public class Export implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * UF de Embarque ou de transposição de fronteira
     * 
     * @param ufExitCountry
     */
    private final @NotNull(message = "{eprecise.efiscal4j.nfe.export.ufExitCountry.isNotNull}") UF ufExitCountry;

    /**
     * Local de Embarque ou de transposição de fronteira
     * 
     * @param exportLocation
     * @return
     */
    private final @NotNull(message = "{eprecise.efiscal4j.nfe.export.exportLocation.isNotNull}") @Size(min = 1, max = 60) String exportLocation;
    
    
    /**
     * Descrição do local de despacho
     * 
     * @param dispatchLocation
     * @return
     */
    private final @Size(min = 1, max = 60) String dispatchLocation;

}


package eprecise.efiscal4j.nfe.transport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;


/**
 * Lacre dos volumes transportados
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class VolumeSeal {

    /**
     * Número dos Lacres
     * 
     * @param sealNumber
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.transportedVolume.volumeSeal.sealNumber.isNotNull}") @Size(
            min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.transport.transportedVolume.volumeSeal.sealNumber.isSize}") final String sealNumber;

}


package eprecise.efiscal4j.nfe.transport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.validation.constraints.Size;

import eprecise.efiscal4j.nfe.types.NumberSize;
import lombok.Builder;
import lombok.Getter;


/**
 * Dados dos volumes transportados
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class TransportedVolume implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Quantidade de volumes transportados
     * 
     * @param volumeQuantity
     */
    private @NumberSize(min = 1, max = 15, message = "{eprecise.efiscal4j.nfe.transport.transportedVolume.volumeQuantity.isSize}") final Long volumeQuantity;

    /**
     * Espécie dos volumes transportados
     * 
     * @param volumeSpecies
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.transport.transportedVolume.volumeSpecies.isSize}") String volumeSpecies;

    /**
     * Marca dos volumes transportados
     * 
     * @param volumeTrademark
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.transport.transportedVolume.volumeTrademark.isSize}") String volumeTrademark;

    /**
     * Numeração dos volumes transportados
     * 
     * @param volumeNumbering
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.transport.transportedVolume.volumeNumbering.isSize}") String volumeNumbering;

    /**
     * Peso líquido (em kg)
     * 
     * @param netWeight
     */
    private final BigDecimal netWeight;

    /**
     * Peso bruto (em kg)
     * 
     * @param grossWeight
     */
    private final BigDecimal grossWeight;

    /**
     * @see VolumeSeal
     * 
     * @param seals
     */
    private final @Size(max = 5000, message = "{eprecise.efiscal4j.nfe.transport.transportedVolume.seals.isSize}") Collection<VolumeSeal> seals;

}

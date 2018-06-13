
package eprecise.efiscal4j.nfe.transport.mean;

import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;


/**
 * Dados de Vagão em Transporte de documento fiscal
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class WagonTransportMean implements TransportMean {

    /**
     * Identificação do vagão
     * 
     * @param identifier
     */
    private @Size(min = 1, max = 20, message = "{eprecise.efiscal4j.nfe.transport.mean.wagonTransportMean.identifier.isSize}") final String identifier;

}

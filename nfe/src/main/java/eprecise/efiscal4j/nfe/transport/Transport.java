
package eprecise.efiscal4j.nfe.transport;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import eprecise.efiscal4j.nfe.transport.conveyor.Conveyor;
import eprecise.efiscal4j.nfe.transport.mean.TransportMean;
import lombok.Builder;
import lombok.Getter;


/**
 * Dados dos transportes da NF-e
 * 
 * @author Fernando Glizt
 * 
 */

@Builder
@Getter
public class Transport {

    /**
     * @see ShippingModality
     * @param shippingModality
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.shippingModality.isNotNull}") @Valid final ShippingModality shippingModality;

    /**
     * @see Conveyor
     * @param conveyor
     */
    private @Valid final Conveyor conveyor;

    /**
     * @see TransportICMSRetention
     * @param transportICMSRetention
     */
    private @Valid final TransportICMSRetention icmsRetention;

    /**
     * @see TransportMean
     * @param transportMean
     */
    private @Valid final TransportMean transportMean;

    /**
     * @see TransportedVolume
     * @param transportedVolume
     */
    private @Size(max = 5000, message = "{eprecise.efiscal4j.nfe.transport.volumes.isSize}") @Valid Collection<TransportedVolume> volumes;

}


package eprecise.efiscal4j.nfe.transport.mean;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import eprecise.efiscal4j.nfe.transport.Vehicle;
import lombok.Builder;
import lombok.Getter;


/**
 * Dados de Veículo e Reboque em Transporte de documento fiscal
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class VehicleTowingTransportMean implements TransportMean{

    /**
     * Veículo do transporte
     * 
     * @see Vehicle
     * @param vehicle
     */
    private @Valid final Vehicle vehicle;

    /**
     * Reboque/Dolly (v2.0)
     * 
     * @see Vehicle
     * @param towing
     */
    private @Size(max = 5, message = "{eprecise.efiscal4j.nfe.transport.mean.vehicleTowingTransportMean.towing.isSize}") @Valid final Collection<Vehicle> towing;

}

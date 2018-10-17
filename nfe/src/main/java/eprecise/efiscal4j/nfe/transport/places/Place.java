package eprecise.efiscal4j.nfe.transport.places;

import javax.validation.constraints.NotNull;

import eprecise.efiscal4j.nfe.transport.places.address.PlaceAddress;
import eprecise.efiscal4j.nfe.transport.places.cnp.PlaceCnp;
import lombok.Builder;
import lombok.Getter;

/**
 * Dados do Local de Retirada ou Entrega
 *
 * @author Fernando C Glizt
 *
 */
@Builder
@Getter
public class Place {
    
    private final @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.cnp.isNotNull}") PlaceCnp cnp;
    
    private final @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.address.isNotNull}") PlaceAddress address;

}


package eprecise.efiscal4j.nfe.transport.places.address;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import eprecise.efiscal4j.commons.domain.adress.UF;
import lombok.Builder;
import lombok.Getter;

/**
 * Dados da cidade e UF do Local de retirada ou entrega
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class PlaceAddressCity {

    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.address.city.ibgeCode.isNotNull}") @Pattern(
            regexp = "[0-9]{7}", message = "{eprecise.efiscal4j.nfe.transport.place.address.city.ibgeCode.isNotIbgeCode}") final String ibgeCode;

    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.address.city.description.isNotNull}") @Size(
            min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.transport.place.address.city.description.isSize}") final String description;

    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.address.city.uf.isNotNull}") final UF uf;

}

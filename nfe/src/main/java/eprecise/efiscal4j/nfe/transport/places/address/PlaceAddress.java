
package eprecise.efiscal4j.nfe.transport.places.address;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;


/**
 * Dados de endereço do local de retirada ou entrega
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class PlaceAddress {


    /**
     * Logradouro
     * 
     * @param street
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.address.street.isNotNull}") @Size(
            min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.transport.place.address.street.isSize}") final String street;

    /**
     * Bairro
     * 
     * @param district
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.address.district.isNotNull}") @Size(
            min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.transport.place.address.district.isSize}") final String district;

    /**
     * @see PlaceAddressCity
     * @param city
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.address.city.isNotNull}") @Valid final PlaceAddressCity city;

    /**
     * Número
     * 
     * @param number
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.address.number.isNotNull}") @Size(
            min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.transport.place.address.number.isSize}") final String number;

    /**
     * Complemento
     * 
     * @param complement
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.transport.place.address.complement.isSize}") final String complement;

}

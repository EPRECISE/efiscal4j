
package eprecise.efiscal4j.nfe.emitter.address;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;


/**
 * Dados de endereço do Emitente
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class EmitterAddress {

    /**
     * CEP sem pontuação ou traços
     * 
     * @param cep
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.address.cep.isNotNull}") @Pattern(
            regexp = "[0-9]{8}", message = "{eprecise.efiscal4j.nfe.emitter.address.cep.isNotCep}") final String cep;

    /**
     * Logradouro
     * 
     * @param street
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.address.street.isNotNull}") @Size(
            min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.emitter.address.street.isSize}") final String street;

    /**
     * Bairro
     * 
     * @param district
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.address.district.isNotNull}") @Size(
            min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.emitter.address.district.isSize}") final String district;

    /**
     * @see EmitterAddressCity
     * @param city
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.address.city.isNotNull}") @Valid final EmitterAddressCity city;

    /**
     * Número
     * 
     * @param number
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.address.number.isNotNull}") @Size(
            min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.emitter.address.number.isSize}") final String number;

    /**
     * Complemento
     * 
     * @param complement
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.emitter.address.complement.isSize}") final String complement;

}

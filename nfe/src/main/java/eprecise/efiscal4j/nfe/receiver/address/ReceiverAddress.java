
package eprecise.efiscal4j.nfe.receiver.address;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;


/**
 * Dados de endereço do Destinatário
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class ReceiverAddress {

    /**
     * CEP sem pontuação ou traços
     * 
     * @param cep
     */
    private @Pattern(regexp = "[0-9]{8}", message = "{eprecise.efiscal4j.nfe.receiver.address.cep.isNotCep}") final String cep;

    /**
     * Logradouro
     * 
     * @param street
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.address.street.isNotNull}") @Size(
            min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.receiver.address.street.isSize}") final String street;

    /**
     * Bairro
     * 
     * @param district
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.address.district.isNotNull}") @Size(
            min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.receiver.address.district.isSize}") final String district;

    /**
     * @see ReceiverAddressCity
     * @param city
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.address.city.isNotNull}") @Valid final ReceiverAddressCity city;

    /**
     * Número
     * 
     * @param number
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.address.number.isNotNull}") @Size(
            min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.receiver.address.number.isSize}") final String number;

    /**
     * Complemento
     * 
     * @param complement
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.receiver.address.complement.isSize}") final String complement;

}

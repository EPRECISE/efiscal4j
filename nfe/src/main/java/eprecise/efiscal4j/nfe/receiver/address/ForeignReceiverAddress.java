
package eprecise.efiscal4j.nfe.receiver.address;

import javax.validation.constraints.NotNull;

import eprecise.efiscal4j.commons.domain.adress.Country;
import lombok.Builder;
import lombok.Getter;


/**
 * Dados de endereço do destinatário
 * 
 * @author Israel Andrade
 * 
 */
@Builder
@Getter
public class ForeignReceiverAddress implements ReceiverAddress {

    private final @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.address.country.isNotNull}") Country country;

    @Override
    public boolean isValid() {
        return true;
    }
}

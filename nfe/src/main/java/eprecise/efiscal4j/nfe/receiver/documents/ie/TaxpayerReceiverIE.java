
package eprecise.efiscal4j.nfe.receiver.documents.ie;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;


/**
 * Contribuiente com IE ativo
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class TaxpayerReceiverIE implements ReceiverIE {

    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.documents.ie.taxPayer.ie.isNotNull}") @Pattern(
            regexp = "[0-9]{2,14}", message = "{eprecise.efiscal4j.nfe.receiver.documents.ie.taxPayer.ie.isNotIe}") final String ie;

}

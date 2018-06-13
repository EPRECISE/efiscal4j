
package eprecise.efiscal4j.nfe.receiver.documents.ie;

import lombok.Builder;


/**
 * Não Contribuiente ICMS / sem inscrição estadual
 *
 * @author Fernando Glizt
 *
 */
@Builder
public class NonTaxpayerReceiverIE implements ReceiverIE {

    @Override
    public String getIe() {
        return null;
    }

}


package eprecise.efiscal4j.nfe.receiver.documents.ie;

import lombok.Builder;


/**
 * Contribuiente ISENTO de inscrição estadual
 *
 * @author Fernando Glizt
 *
 */
@Builder
public class FreeTaxpayerReceiverIE implements ReceiverIE {

    @Override
    public String getIe() {
        return "ISENTO";
    }

}


package eprecise.efiscal4j.nfse.transmission.response;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessageLot;


public interface NFSeResponse extends Serializable {

    default Collection<CommonsNFSeReturnMessage> getReturnMessageList() {
        return Collections.emptySet();
    }

    default Collection<CommonsNFSeReturnMessageLot> getReturnMessageLotList() {
        return Collections.emptySet();
    }

    String getAsXml();

}


package eprecise.efiscal4j.nfse.domain.comp;

import java.io.Serializable;
import java.util.Date;

import eprecise.efiscal4j.nfse.domain.comp.rps.RpsIdentifier;


public interface ProcessedNFSe extends Serializable {

    String getNumber();

    String getVerificationCode();

    Date getEmissionDate();

    RpsIdentifier getRpsIdentifier();

}


package eprecise.efiscal4j.nfse.tc.compNfse;

import java.io.Serializable;
import java.util.Date;

import eprecise.efiscal4j.nfse.tc.rps.RpsIdentifier;


public interface NFSe extends Serializable {

    String getNumber();

    String getVerificationCode();

    Date getEmissionDate();

    RpsIdentifier getRpsIdentifier();

}

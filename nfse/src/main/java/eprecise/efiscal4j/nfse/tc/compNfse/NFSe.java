
package eprecise.efiscal4j.nfse.tc.compNfse;

import java.io.Serializable;

import eprecise.efiscal4j.nfse.tc.rps.RpsIdentifier;


public interface NFSe extends Serializable {

    String getNumber();

    String getVerificationCode();

    String getEmissionDate();

    RpsIdentifier getRpsIdentifier();

}

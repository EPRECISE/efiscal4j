
package eprecise.efiscal4j.nfse.domain.comp;

import java.io.Serializable;
import java.util.Date;

import eprecise.efiscal4j.nfse.domain.comp.rps.RpsIdentifier;


public abstract class ProcessedNFSe implements Serializable {

    public abstract String getNumber();

    public abstract String getVerificationCode();

    public abstract Date getEmissionDate();

    public abstract RpsIdentifier getRpsIdentifier();

}

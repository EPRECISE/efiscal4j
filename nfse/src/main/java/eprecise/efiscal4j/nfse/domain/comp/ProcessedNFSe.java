
package eprecise.efiscal4j.nfse.domain.comp;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import eprecise.efiscal4j.nfse.domain.comp.rps.RpsIdentifier;
import eprecise.efiscal4j.nfse.tc.commons.compNfse.CommonsGeneratorOrgan;


public abstract class ProcessedNFSe implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract String getProviderCnp();

    public abstract String getProviderIm();

    public abstract String getNumber();

    public abstract String getVerificationCode();

    public abstract Optional<String> getAccessKey();

    public abstract Date getEmissionDate();

    public abstract RpsIdentifier getRpsIdentifier();

    public abstract CommonsGeneratorOrgan getGeneratorOrgan();

}

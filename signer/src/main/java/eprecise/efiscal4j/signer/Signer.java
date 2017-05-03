
package eprecise.efiscal4j.signer;

public interface Signer {

    Assignable sign(final Assignable assignable) throws Exception;

    public eprecise.efiscal4j.commons.utils.Certificate getKeyCertificate();

}

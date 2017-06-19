
package eprecise.efiscal4j.nfse.transmission.response;

import java.util.Optional;

import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;


public interface NFSeDispatchAutorizedResponse extends NFSeResponse {

    Optional<CompNFSe> getCompNFSe();

}

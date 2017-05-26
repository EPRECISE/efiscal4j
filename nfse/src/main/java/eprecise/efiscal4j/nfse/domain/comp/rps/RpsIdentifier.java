
package eprecise.efiscal4j.nfse.domain.comp.rps;

import java.io.Serializable;

import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsType;


public interface RpsIdentifier extends Serializable {

    String getNumber();

    String getSerie();

    CommonsRpsType getType();

}

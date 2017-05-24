
package eprecise.efiscal4j.nfse.tc.lot.rps;

import java.io.Serializable;

import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsType;


public interface RpsIdentifier extends Serializable {

    String getNumber();

    String getSerie();

    CommonsRpsType getType();

}

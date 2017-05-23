
package eprecise.efiscal4j.nfse.tc.rps;

import java.io.Serializable;

import eprecise.efiscal4j.nfse.ts.commons.rps.RpsType;


public interface RpsIdentifier extends Serializable {

    String getNumber();

    String getSerie();

    RpsType getType();

}

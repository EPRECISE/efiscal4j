
package eprecise.efiscal4j.nfse.domain.service.withheld;

import java.io.Serializable;

import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeResponsibleRetention;


public interface NFSeWithIssHeldSpecificData extends Serializable {

    CommonsNFSeResponsibleRetention getResponsibleRetention();

}

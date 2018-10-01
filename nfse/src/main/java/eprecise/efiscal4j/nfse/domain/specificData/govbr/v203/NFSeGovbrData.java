
package eprecise.efiscal4j.nfse.domain.specificData.govbr.v203;

import eprecise.efiscal4j.nfse.domain.specificData.NFSeSpecificData;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.services.GovbrIssRequirement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class NFSeGovbrData implements NFSeSpecificData {

    private static final long serialVersionUID = 1L;

    private @Getter final GovbrIssRequirement issRequirement;

    private @Getter final String processNumber;

    private @Getter final boolean nationalSimple;

    private @Getter final boolean taxIncentive;

}

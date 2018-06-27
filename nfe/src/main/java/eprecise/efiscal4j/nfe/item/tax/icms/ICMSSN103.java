
package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ICMSSN103 implements ICMS {

    private final ICMSCST cst = ICMSCST.CSOSN_103;

    private final ProductOrigin origin;

}


package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.sn.credit.CreditSnValue;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ICMSSN101 implements ICMS {

    private final ICMSCST cst = ICMSCST.CSOSN_101;

    private final ProductOrigin origin;

    private final CreditSnValue creditSn;

}

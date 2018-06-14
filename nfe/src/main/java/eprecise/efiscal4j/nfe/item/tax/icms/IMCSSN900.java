
package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.sn.credit.CreditSnValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcReductionPercent;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IMCSSN900 implements ICMS {

    private final ICMSCST cst = ICMSCST.CSOSN_900;

    private final ProductOrigin origin;

    private final IcmsWithBcReductionPercent icms;

    private final IcmsStWithBcReductionPercent icmsSt;

    private final FcpStWithBcValue fcpSt;

    private final CreditSnValue creditSn;

}


package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcReductionPercent;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IMCS30 implements ICMS {

    private final ICMSCST cst = ICMSCST.CST_30;

    private final ProductOrigin origin;

    private final IcmsStWithBcReductionPercent icmsSt;

    private final FcpStWithBcValue fcpSt;

    private final IcmsDesoneration desonaration;

}

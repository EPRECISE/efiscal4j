
package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcReductionPercent;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IMCS70 implements ICMS {

    private @Builder.Default final ICMSCST cst = ICMSCST.CST_70;

    private final ProductOrigin origin;

    private final IcmsWithBcReductionPercent icms;

    private final IcmsStWithBcReductionPercent icmsSt;

    private final FcpWithBcValue fcp;

    private final FcpStWithBcValue fcpSt;

    private final IcmsDesoneration desoneration;

}

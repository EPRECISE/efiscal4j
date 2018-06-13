
package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcReductionPercent;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IMCS20 implements ICMS {

    private @Builder.Default final ICMSCST cst = ICMSCST.CST_20;

    private final ProductOrigin origin;

    private final IcmsWithBcReductionPercent icms;

    private final FcpWithBcValue fcp;

    private final IcmsDesoneration desonaration;

}

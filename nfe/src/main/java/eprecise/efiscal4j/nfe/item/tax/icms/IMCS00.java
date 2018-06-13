
package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IMCS00 implements ICMS {

    private @Builder.Default final ICMSCST cst = ICMSCST.CST_00;

    private final ProductOrigin origin;

    private final IcmsWithBcValue icms;

    private final FcpValue fcp;

}

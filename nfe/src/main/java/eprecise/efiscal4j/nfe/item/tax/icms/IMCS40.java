
package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IMCS40 implements ICMS {

    private final ICMSCST cst = ICMSCST.CST_40;

    private final ProductOrigin origin;

    private final IcmsDesoneration desonaration;

}
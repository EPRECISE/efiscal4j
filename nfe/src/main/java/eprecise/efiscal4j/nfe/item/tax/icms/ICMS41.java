
package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration.IcmsDesonerationHolder;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ICMS41 implements ICMS, IcmsDesonerationHolder {

    private final ICMSCST cst = ICMSCST.CST_41;

    private final ProductOrigin origin;

    private final IcmsDesoneration desoneration;


}

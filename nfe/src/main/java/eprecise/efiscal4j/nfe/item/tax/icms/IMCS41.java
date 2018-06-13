
package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IMCS41 implements ICMS {

    private @Builder.Default final ICMSCST cst = ICMSCST.CST_41;

    private final ProductOrigin origin;

    private final IcmsDesoneration desonaration;

}


package eprecise.efiscal4j.nfe.item.tax.ipi;

import eprecise.efiscal4j.nfe.item.tax.ipi.cst.IPICST;
import eprecise.efiscal4j.nfe.item.tax.ipi.generalData.IPIGeneralData;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IPI03 implements IPI {

    private final IPICST cst = IPICST.CST_03;

    private final IPIGeneralData generalData;

}

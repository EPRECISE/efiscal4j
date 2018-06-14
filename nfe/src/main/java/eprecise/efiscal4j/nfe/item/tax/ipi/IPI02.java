
package eprecise.efiscal4j.nfe.item.tax.ipi;

import eprecise.efiscal4j.nfe.item.tax.ipi.cst.IPICST;
import eprecise.efiscal4j.nfe.item.tax.ipi.generalData.IPIGeneralData;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IPI02 implements IPI {

    private final IPICST cst = IPICST.CST_02;

    private final IPIGeneralData generalData;

}

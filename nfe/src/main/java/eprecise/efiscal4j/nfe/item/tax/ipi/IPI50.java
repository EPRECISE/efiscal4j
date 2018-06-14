
package eprecise.efiscal4j.nfe.item.tax.ipi;

import eprecise.efiscal4j.nfe.item.tax.ipi.cst.IPICST;
import eprecise.efiscal4j.nfe.item.tax.ipi.generalData.IPIGeneralData;
import eprecise.efiscal4j.nfe.v400.tax.ipi.value.IpiValue;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IPI50 implements IPI {

    private final IPICST cst = IPICST.CST_50;

    private final IPIGeneralData generalData;

    private final IpiValue value;

}

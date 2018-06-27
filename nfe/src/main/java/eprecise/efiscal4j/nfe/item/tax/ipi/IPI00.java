
package eprecise.efiscal4j.nfe.item.tax.ipi;

import eprecise.efiscal4j.nfe.item.tax.ipi.cst.IPICST;
import eprecise.efiscal4j.nfe.item.tax.ipi.generalData.IPIGeneralData;
import eprecise.efiscal4j.nfe.item.tax.ipi.value.IpiValue;
import eprecise.efiscal4j.nfe.item.tax.ipi.value.IpiValue.IpiValueHolder;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IPI00 implements IPI, IpiValueHolder {

    private final IPICST cst = IPICST.CST_00;

    private final IPIGeneralData generalData;

    private final IpiValue value;

}

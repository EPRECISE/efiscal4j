package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue.IcmsWithBcValueHolder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ICMS61 implements ICMS, IcmsWithBcValueHolder {

    private final ICMSCST cst = ICMSCST.CST_61;

    private final ProductOrigin origin;

    private final IcmsWithBcValue icms;

    @Override
    public IcmsWithBcValue getIcmsWithBcValue() {
        return this.icms;
    }
}
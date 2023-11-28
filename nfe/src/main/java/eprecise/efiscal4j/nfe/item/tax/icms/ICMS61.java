package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.singlephase.IcmsMonoRetWithValue;
import eprecise.efiscal4j.nfe.item.tax.icms.singlephase.IcmsMonoRetWithValue.IcmsMonoRetWithValueHolder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ICMS61 implements ICMS, IcmsMonoRetWithValueHolder {

    private final ICMSCST cst = ICMSCST.CST_61;

    private final ProductOrigin origin;

    private final IcmsMonoRetWithValue icms;

    @Override
    public IcmsMonoRetWithValue getIcmsMonoRetWithValue() {
        return this.icms;
    }
}
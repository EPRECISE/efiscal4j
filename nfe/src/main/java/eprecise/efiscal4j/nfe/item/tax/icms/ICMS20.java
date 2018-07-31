
package eprecise.efiscal4j.nfe.item.tax.icms;

import java.util.Optional;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration.IcmsDesonerationHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpValue;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpValue.IcmsWithFcpValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue.IcmsWithBcValueHolder;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ICMS20 implements ICMS, IcmsWithBcValueHolder, IcmsDesonerationHolder, IcmsWithFcpValueHolder {

    private final ICMSCST cst = ICMSCST.CST_20;

    private final ProductOrigin origin;

    private final IcmsWithBcReductionPercent icms;

    private final FcpWithBcValue fcp;

    private final IcmsDesoneration desoneration;

    @Override
    public IcmsWithBcValue getIcmsWithBcValue() {
        return Optional.ofNullable(this.icms).map(IcmsWithBcReductionPercent::getValue).orElse(null);
    }

    @Override
    public FcpValue getFcpValue() {
        return Optional.ofNullable(this.fcp).map(FcpWithBcValue::getValue).orElse(null);
    }

}

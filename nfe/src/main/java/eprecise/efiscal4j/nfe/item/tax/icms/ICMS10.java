
package eprecise.efiscal4j.nfe.item.tax.icms;

import java.util.Optional;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpValue;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpValue.IcmsWithFcpValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStValue.IcmsWithFcpStValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcValue.IcmsStWithBcValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue.IcmsWithBcValueHolder;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ICMS10 implements ICMS, IcmsWithBcValueHolder, IcmsStWithBcValueHolder, IcmsWithFcpValueHolder, IcmsWithFcpStValueHolder {

    private final ICMSCST cst = ICMSCST.CST_10;

    private final ProductOrigin origin;

    private final IcmsWithBcValue icms;

    private final IcmsStWithBcReductionPercent icmsSt;

    private final FcpWithBcValue fcp;

    private final FcpStWithBcValue fcpSt;

    @Override
    public IcmsWithBcValue getIcmsWithBcValue() {
        return this.icms;
    }

    @Override
    public IcmsStWithBcValue getIcmsStWithBcValue() {
        return Optional.ofNullable(this.icmsSt).map(IcmsStWithBcReductionPercent::getValue).orElse(null);
    }

    @Override
    public FcpStValue getFcpStValue() {
        return Optional.ofNullable(this.fcpSt).map(FcpStWithBcValue::getValue).orElse(null);
    }

    @Override
    public FcpValue getFcpValue() {
        return Optional.ofNullable(this.fcp).map(FcpWithBcValue::getValue).orElse(null);
    }

}

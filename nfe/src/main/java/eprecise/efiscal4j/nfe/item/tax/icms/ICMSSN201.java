
package eprecise.efiscal4j.nfe.item.tax.icms;

import java.util.Optional;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.sn.credit.CreditSnValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStValue.IcmsWithFcpStValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcValue.IcmsStWithBcValueHolder;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ICMSSN201 implements ICMS, IcmsStWithBcValueHolder, IcmsWithFcpStValueHolder {

    private final ICMSCST cst = ICMSCST.CSOSN_201;

    private final ProductOrigin origin;

    private final IcmsStWithBcReductionPercent icmsSt;

    private final FcpStWithBcValue fcpSt;

    private final CreditSnValue creditSn;

    @Override
    public IcmsStWithBcValue getIcmsStWithBcValue() {
        return Optional.ofNullable(this.icmsSt).map(IcmsStWithBcReductionPercent::getValue).orElse(null);
    }

    @Override
    public FcpStValue getFcpStValue() {
        return Optional.ofNullable(this.fcpSt).map(FcpStWithBcValue::getValue).orElse(null);
    }

}

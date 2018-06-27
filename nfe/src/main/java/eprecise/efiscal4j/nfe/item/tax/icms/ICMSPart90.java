
package eprecise.efiscal4j.nfe.item.tax.icms;

import java.math.BigDecimal;
import java.util.Optional;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcValue.IcmsStWithBcValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue.IcmsWithBcValueHolder;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ICMSPart90 implements ICMS, IcmsWithBcValueHolder, IcmsStWithBcValueHolder {

    private final ICMSCST cst = ICMSCST.PART_CST_90;

    private final ProductOrigin origin;

    private final IcmsWithBcReductionPercent icms;

    private final IcmsStWithBcReductionPercent icmsSt;

    private final BigDecimal selfOperationBcPercent;

    private final UF ufSt;

    @Override
    public IcmsWithBcValue getIcmsWithBcValue() {
        return Optional.ofNullable(this.icms).map(IcmsWithBcReductionPercent::getValue).orElse(null);
    }

    @Override
    public IcmsStWithBcValue getIcmsStWithBcValue() {
        return Optional.ofNullable(this.icmsSt).map(IcmsStWithBcReductionPercent::getValue).orElse(null);
    }

}

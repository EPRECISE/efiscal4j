
package eprecise.efiscal4j.nfe.item.tax.icms;

import java.math.BigDecimal;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcReductionPercent;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IMCSPart90 implements ICMS {

    private final ICMSCST cst = ICMSCST.PART_CST_90;

    private final ProductOrigin origin;

    private final IcmsWithBcReductionPercent icms;

    private final IcmsStWithBcReductionPercent icmsSt;

    private final BigDecimal selfOperationBcPercent;

    private final UF ufSt;

}

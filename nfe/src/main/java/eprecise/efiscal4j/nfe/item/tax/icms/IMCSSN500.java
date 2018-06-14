
package eprecise.efiscal4j.nfe.item.tax.icms;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.retained.FcpStRetainedValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.retained.IcmsStRetainedValue;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IMCSSN500 implements ICMS {

    private final ICMSCST cst = ICMSCST.CSOSN_500;

    private final ProductOrigin origin;

    private final IcmsStRetainedValue icmsStRetained;

    private final FcpStRetainedValue fcpStRetained;

    private final BigDecimal endConsumerSupportedAliquot;

}

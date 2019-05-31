
package eprecise.efiscal4j.nfe.item.tax.icms;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.retained.FcpStRetainedValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.retained.FcpStRetainedValue.IcmsWithFcpStRetainedValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.destination.IcmsStDestinationValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.retained.IcmsStRetainedValue;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ICMSST implements ICMS, IcmsWithFcpStRetainedValueHolder {

    private final ICMSCST cst = ICMSCST.ST_CST_41;

    private final ProductOrigin origin;

    private final IcmsStRetainedValue retainedSt;

    private final BigDecimal endConsumerSupportedAliquot;

    private final BigDecimal icmsSubstituteValue;

    private final FcpStRetainedValue fcpStRetained;

    private final IcmsStDestinationValue destinationSt;

    @Override
    public FcpStRetainedValue getFcpStRetainedValue() {
        return this.fcpStRetained;
    }

}

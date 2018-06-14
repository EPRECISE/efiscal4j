
package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.destination.IcmsStDestinationValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.retained.IcmsStRetainedValue;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IMCSST implements ICMS {

    private final ICMSCST cst = ICMSCST.ST_CST_41;

    private final ProductOrigin origin;

    private final IcmsStRetainedValue retainedSt;

    private final IcmsStDestinationValue destinationSt;

}

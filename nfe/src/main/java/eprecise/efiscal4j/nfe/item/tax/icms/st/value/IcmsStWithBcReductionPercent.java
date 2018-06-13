
package eprecise.efiscal4j.nfe.item.tax.icms.st.value;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsStWithBcReductionPercent {

    private final IcmsStWithBcValue icmsValue;

    private final BigDecimal bcReductionPercent;

}

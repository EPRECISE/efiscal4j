
package eprecise.efiscal4j.nfe.item.tax.icms.fcp.value;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class FcpWithBcValue {

    private final BigDecimal calculationBasis;

    private final FcpValue value;

}


package eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.retained;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class FcpStRetainedValue {

    private final BigDecimal calculationBasis;

    private final BigDecimal aliquot;

    private final BigDecimal value;

}

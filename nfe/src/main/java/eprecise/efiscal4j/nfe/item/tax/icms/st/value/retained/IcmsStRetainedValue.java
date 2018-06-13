
package eprecise.efiscal4j.nfe.item.tax.icms.st.value.retained;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsStRetainedValue {

    private final BigDecimal calculationBasis;

    private final BigDecimal value;

}

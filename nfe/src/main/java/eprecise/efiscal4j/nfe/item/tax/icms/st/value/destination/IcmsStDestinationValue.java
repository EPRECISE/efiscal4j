
package eprecise.efiscal4j.nfe.item.tax.icms.st.value.destination;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsStDestinationValue {

    private final BigDecimal calculationBasis;

    private final BigDecimal value;

}


package eprecise.efiscal4j.nfe.item.tax.icms.st.value;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc.IcmsStBc;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsStWithBcValue {

    private final IcmsStBc calculationBasis;

    private final IcmsStValue value;

}

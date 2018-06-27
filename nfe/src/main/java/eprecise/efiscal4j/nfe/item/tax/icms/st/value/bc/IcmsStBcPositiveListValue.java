
package eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsStBcPositiveListValue implements IcmsStBc {

    private final BigDecimal calculationBasis;

}

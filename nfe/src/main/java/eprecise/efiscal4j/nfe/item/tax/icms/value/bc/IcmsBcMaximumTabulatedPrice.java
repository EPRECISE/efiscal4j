
package eprecise.efiscal4j.nfe.item.tax.icms.value.bc;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsBcMaximumTabulatedPrice implements IcmsBc {

    private final BigDecimal calculationBasis;


}

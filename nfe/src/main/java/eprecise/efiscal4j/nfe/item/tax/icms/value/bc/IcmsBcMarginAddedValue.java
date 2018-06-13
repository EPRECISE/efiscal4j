
package eprecise.efiscal4j.nfe.item.tax.icms.value.bc;

import java.math.BigDecimal;

import lombok.Getter;

import lombok.Builder;


@Builder
@Getter
public class IcmsBcMarginAddedValue implements IcmsBc {
    
    private final BigDecimal marginAddedPercent;

    private final BigDecimal calculationBasis;

}

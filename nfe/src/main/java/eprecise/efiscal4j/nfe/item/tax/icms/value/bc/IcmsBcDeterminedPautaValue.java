
package eprecise.efiscal4j.nfe.item.tax.icms.value.bc;

import java.math.BigDecimal;

import lombok.Getter;

import lombok.Builder;


@Builder
@Getter
public class IcmsBcDeterminedPautaValue implements IcmsBc {

    private final BigDecimal calculationBasis;


}

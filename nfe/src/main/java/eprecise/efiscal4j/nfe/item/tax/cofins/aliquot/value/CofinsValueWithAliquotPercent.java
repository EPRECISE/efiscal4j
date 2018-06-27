
package eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.CofinsAliquotPercentWithBc;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CofinsValueWithAliquotPercent implements CofinsValue {

    private final CofinsAliquotPercentWithBc aliquot;

    private final BigDecimal value;

}


package eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.CofinsAliquotValueWithQuantity;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CofinsValueWithAliquotValue {

    private final CofinsAliquotValueWithQuantity aliquot;

    private final BigDecimal value;

}

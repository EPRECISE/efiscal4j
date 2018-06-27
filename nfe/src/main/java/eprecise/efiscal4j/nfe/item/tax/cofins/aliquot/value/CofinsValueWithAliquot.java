
package eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.CofinsAliquot;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CofinsValueWithAliquot implements CofinsValue{

    private final CofinsAliquot aliquot;

    private final BigDecimal value;

}

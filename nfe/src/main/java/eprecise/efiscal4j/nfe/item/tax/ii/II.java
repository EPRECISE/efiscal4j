
package eprecise.efiscal4j.nfe.item.tax.ii;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.ItemTax;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class II implements ItemTax {

    private final BigDecimal calculationBasis;

    private final BigDecimal customsCharge;

    private final BigDecimal value;

    private final BigDecimal iof;

}

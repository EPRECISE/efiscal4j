
package eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.PisAliquotPercentWithBc;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PisValueWithAliquotPercent implements PisValue {

    private final PisAliquotPercentWithBc aliquot;

    private final BigDecimal value;

}

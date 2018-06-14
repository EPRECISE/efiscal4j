
package eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.PisAliquotValueWithQuantity;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PisValueWithAliquotValue {

    private final PisAliquotValueWithQuantity aliquot;

    private final BigDecimal value;

}

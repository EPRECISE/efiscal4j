
package eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.PisAliquot;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PisValueWithAliquot implements PisValue {

    private final PisAliquot aliquot;

    private final BigDecimal value;

}

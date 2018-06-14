
package eprecise.efiscal4j.nfe.item.tax.pis.aliquot;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PisAliquotValueWithQuantity implements PisAliquot {

    private final BigDecimal quantity;

    private final BigDecimal aliquotValue;

}

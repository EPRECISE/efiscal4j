
package eprecise.efiscal4j.nfe.item.tax.cofins.aliquot;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CofinsAliquotValueWithQuantity implements CofinsAliquot {

    private final BigDecimal quantity;

    private final BigDecimal aliquotValue;

}

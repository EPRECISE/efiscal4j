
package eprecise.efiscal4j.nfe.item.tax.cofins.aliquot;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CofinsAliquotPercentWithBc implements CofinsAliquot {

    private final BigDecimal calculationBasis;

    private final BigDecimal aliquot;

}

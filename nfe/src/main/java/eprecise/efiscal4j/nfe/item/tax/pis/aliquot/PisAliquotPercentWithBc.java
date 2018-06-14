
package eprecise.efiscal4j.nfe.item.tax.pis.aliquot;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PisAliquotPercentWithBc implements PisAliquot{

    private final BigDecimal calculationBasis;

    private final BigDecimal aliquot;

}

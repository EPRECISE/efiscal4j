
package eprecise.efiscal4j.nfe.item.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ApproximateTax {

    private final @Builder.Default BigDecimal nationalTax = BigDecimal.ZERO;

    private final @Builder.Default BigDecimal stateTax = BigDecimal.ZERO;

    private final @Builder.Default BigDecimal importTax = BigDecimal.ZERO;

    private final @Builder.Default BigDecimal cityTax = BigDecimal.ZERO;

    public BigDecimal getTotal() {
        return this.nationalTax.add(this.stateTax).add(this.importTax).add(this.cityTax).setScale(2, RoundingMode.HALF_UP);
    }

}


package eprecise.efiscal4j.nfe.item.tax.ipi.value;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IpiValue {

    private final BigDecimal calculationBasis;

    private final BigDecimal aliquot;

    private final BigDecimal quantity;

    private final BigDecimal unitaryValue;

    private final BigDecimal value;

    public static interface IpiValueHolder {

        IpiValue getValue();

    }

}

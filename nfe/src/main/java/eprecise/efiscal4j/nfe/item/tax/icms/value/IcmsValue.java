
package eprecise.efiscal4j.nfe.item.tax.icms.value;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsValue {

    private final BigDecimal aliquot;

    private final BigDecimal value;

}

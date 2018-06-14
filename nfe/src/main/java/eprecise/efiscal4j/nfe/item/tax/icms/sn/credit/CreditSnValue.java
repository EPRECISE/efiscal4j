
package eprecise.efiscal4j.nfe.item.tax.icms.sn.credit;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CreditSnValue {

    private final BigDecimal aliquot;

    private final BigDecimal value;

}


package eprecise.efiscal4j.nfe.item.tax.icms.deferral;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsDeferral {

    private final BigDecimal percent;

    private final BigDecimal value;

}

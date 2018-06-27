
package eprecise.efiscal4j.nfe.item.tax.icms.desoneration;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsDesoneration {

    private final IcmsDesonerationReason reason;

    private final BigDecimal value;

    public static interface IcmsDesonerationHolder {

        IcmsDesoneration getDesoneration();

    }

}

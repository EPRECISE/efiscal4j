
package eprecise.efiscal4j.nfe.item.tax.icms.fcp.value;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class FcpValue {

    private final BigDecimal aliquot;

    private final BigDecimal value;

    public static interface IcmsWithFcpValueHolder {

        FcpValue getFcpValue();

    }

}

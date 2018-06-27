
package eprecise.efiscal4j.nfe.item.tax.icms.value;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.icms.value.bc.IcmsBc;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsWithBcValue {

    private final IcmsBc calculationBasis;

    private final BigDecimal aliquot;

    private final BigDecimal value;

    public static interface IcmsWithBcValueHolder {

        IcmsWithBcValue getIcmsWithBcValue();

    }

}

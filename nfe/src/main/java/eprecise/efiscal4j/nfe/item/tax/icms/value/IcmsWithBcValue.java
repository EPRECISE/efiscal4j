
package eprecise.efiscal4j.nfe.item.tax.icms.value;

import eprecise.efiscal4j.nfe.item.tax.icms.value.bc.IcmsBc;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsWithBcValue {

    private final IcmsBc calculationBasis;

    private final IcmsValue value;

}

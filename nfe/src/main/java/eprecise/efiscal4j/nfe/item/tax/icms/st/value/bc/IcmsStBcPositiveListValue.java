
package eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc;

import java.math.BigDecimal;

import lombok.Getter;

import lombok.Builder;


@Builder
@Getter
public class IcmsStBcPositiveListValue implements IcmsStBc {

    private final BigDecimal calculationBasis;

}

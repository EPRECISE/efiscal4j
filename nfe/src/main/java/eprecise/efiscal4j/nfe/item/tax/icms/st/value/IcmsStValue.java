
package eprecise.efiscal4j.nfe.item.tax.icms.st.value;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IcmsStValue {

    private final BigDecimal aliquot;

    private final BigDecimal value;

}

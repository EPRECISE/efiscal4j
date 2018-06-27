
package eprecise.efiscal4j.nfe.item.tax.ufReceiverIcms;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.ItemTax;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ICMSUFReceiver implements ItemTax {

    private final BigDecimal calculationBasis;

    private final BigDecimal aliquot;

    private final BigDecimal bcFcpValue;

    private final BigDecimal fcpAditionalAliquot;

    private final BigDecimal fcpValue;

    private final InterstateICMSUFAliquot interstateAliquot;

    private final BigDecimal sharePercentual;

    private final BigDecimal shareValue;

    private final BigDecimal emitterShareValue;

}

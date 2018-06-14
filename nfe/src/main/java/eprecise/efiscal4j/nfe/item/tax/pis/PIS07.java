
package eprecise.efiscal4j.nfe.item.tax.pis;

import eprecise.efiscal4j.nfe.item.tax.pis.cst.PISCST;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PIS07 implements PISNT {

    private final PISCST cst = PISCST.CST_07;

}

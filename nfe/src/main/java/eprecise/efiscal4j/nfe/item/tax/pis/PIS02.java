
package eprecise.efiscal4j.nfe.item.tax.pis;

import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValueWithAliquotPercent;
import eprecise.efiscal4j.nfe.item.tax.pis.cst.PISCST;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PIS02 implements PISTrib {

    private final PISCST cst = PISCST.CST_02;

    private final PisValueWithAliquotPercent pis;

}

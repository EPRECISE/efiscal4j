
package eprecise.efiscal4j.nfe.item.tax.pis;

import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValueWithAliquot;
import eprecise.efiscal4j.nfe.item.tax.pis.cst.PISCST;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PIS71 implements PISTrib {

    private final PISCST cst = PISCST.CST_71;

    private final PisValueWithAliquot pis;

}

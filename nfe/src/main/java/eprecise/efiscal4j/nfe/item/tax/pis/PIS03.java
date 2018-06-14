
package eprecise.efiscal4j.nfe.item.tax.pis;

import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValueWithAliquotValue;
import eprecise.efiscal4j.nfe.item.tax.pis.cst.PISCST;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PIS03 implements PIS {

    private final PISCST cst = PISCST.CST_03;

    private final PisValueWithAliquotValue pis;

}

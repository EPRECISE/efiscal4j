
package eprecise.efiscal4j.nfe.item.tax.cofins;

import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquot;
import eprecise.efiscal4j.nfe.item.tax.cofins.cst.COFINSCST;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class COFINS99 implements COFINS {

    private final COFINSCST cst = COFINSCST.CST_99;

    private final CofinsValueWithAliquot cofins;

}

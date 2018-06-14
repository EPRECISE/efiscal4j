
package eprecise.efiscal4j.nfe.item.tax.cofins;

import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquotPercent;
import eprecise.efiscal4j.nfe.item.tax.cofins.cst.COFINSCST;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class COFINS01 implements COFINS {

    private final COFINSCST cst = COFINSCST.CST_01;

    private final CofinsValueWithAliquotPercent cofins;

}

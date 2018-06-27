
package eprecise.efiscal4j.nfe.item.tax.cofins;

import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquotValue;
import eprecise.efiscal4j.nfe.item.tax.cofins.cst.COFINSCST;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class COFINS03 implements COFINSTrib {

    private final COFINSCST cst = COFINSCST.CST_03;

    private final CofinsValueWithAliquotValue cofins;

}


package eprecise.efiscal4j.nfe.item.tax.cofins;

import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquot;
import eprecise.efiscal4j.nfe.item.tax.cofins.cst.COFINSCST;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class COFINS66 implements COFINSTrib {

    private final COFINSCST cst = COFINSCST.CST_66;

    private final CofinsValueWithAliquot cofins;

}

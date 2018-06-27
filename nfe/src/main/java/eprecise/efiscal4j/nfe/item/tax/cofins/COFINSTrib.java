
package eprecise.efiscal4j.nfe.item.tax.cofins;

import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValue;
import eprecise.efiscal4j.nfe.item.tax.cofins.cst.COFINSCST;


public interface COFINSTrib extends COFINS {

    COFINSCST getCst();

    CofinsValue getCofins();

}

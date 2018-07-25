
package eprecise.efiscal4j.nfe.item.tax.pis;

import eprecise.efiscal4j.nfe.item.tax.ItemTax;
import eprecise.efiscal4j.nfe.item.tax.pis.cst.PISCST;


public interface PIS extends ItemTax {

    PISCST getCst();

}

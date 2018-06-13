
package eprecise.efiscal4j.nfe.item.tax.icms;

import eprecise.efiscal4j.nfe.item.tax.ItemTax;
import eprecise.efiscal4j.nfe.item.tax.icms.cst.ICMSCST;


public interface ICMS extends ItemTax {

    ICMSCST getCst();

    ProductOrigin getOrigin();

}

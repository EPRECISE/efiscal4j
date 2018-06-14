
package eprecise.efiscal4j.nfe.item.tax.ipi;

import eprecise.efiscal4j.nfe.item.tax.ItemTax;
import eprecise.efiscal4j.nfe.item.tax.ipi.cst.IPICST;


public interface IPI extends ItemTax {

    IPICST getCst();

}


package eprecise.efiscal4j.nfe.item.tax.pis;

import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValue;
import eprecise.efiscal4j.nfe.item.tax.pis.cst.PISCST;


public interface PISTrib extends PIS {

    PISCST getCst();

    PisValue getPis();

}

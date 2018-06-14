
package eprecise.efiscal4j.nfe.item.tax.pis.st;

import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquot;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class PISST {

    private final CofinsValueWithAliquot value;

}

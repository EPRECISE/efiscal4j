
package eprecise.efiscal4j.nfe.item.tax.cofins.st;

import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquot;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class COFINSST {

    private final CofinsValueWithAliquot value;

}

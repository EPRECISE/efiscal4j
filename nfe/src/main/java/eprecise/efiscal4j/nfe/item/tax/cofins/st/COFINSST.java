
package eprecise.efiscal4j.nfe.item.tax.cofins.st;

import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValueWithAliquot;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class COFINSST {

    private final PisValueWithAliquot value;

}

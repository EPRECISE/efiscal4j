
package eprecise.efiscal4j.nfe.item.tax.cofins;

import eprecise.efiscal4j.nfe.item.tax.cofins.cst.COFINSCST;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class COFINS04 implements COFINSNT {

    private final COFINSCST cst = COFINSCST.CST_04;

}

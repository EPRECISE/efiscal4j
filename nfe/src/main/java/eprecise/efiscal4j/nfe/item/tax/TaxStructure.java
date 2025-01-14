
package eprecise.efiscal4j.nfe.item.tax;

import java.util.Collection;

import eprecise.efiscal4j.commons.domain.CFOP;
import eprecise.efiscal4j.nfe.item.tax.scale.ScaleTaxIndicator;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class TaxStructure {

    private final String ncm;

    private final CFOP cfop;

    private final String cest;

    private final ScaleTaxIndicator scaleIndication;

    private final Collection<ItemTax> taxes;

    private final ApproximateTax approximateTax;

}

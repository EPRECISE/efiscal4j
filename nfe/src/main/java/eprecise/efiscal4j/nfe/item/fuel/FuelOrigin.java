package eprecise.efiscal4j.nfe.item.fuel;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.v400.tax.icms.ProductOrigin;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FuelOrigin {

    private final ProductOrigin importIndicator;

    private final UF originUf;

    private final String percentageOriginatedFromUf;

}

package eprecise.efiscal4j.nfe.item.fuel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FuelOrigin {

    private final String indImport;

    private final String cUFOrig;

    private final String pOrig;

}


package eprecise.efiscal4j.nfe.item.fuel;

import java.math.BigDecimal;
import java.util.List;

import eprecise.efiscal4j.commons.domain.adress.UF;
import lombok.Builder;
import lombok.Getter;


/**
 * Grupo do detalhamento de operações com combustíveis líquidos
 *
 * @author Fernando C Glizt
 *
 */

@Builder
@Getter
public class Fuel {

    private final String anpProductCode;

    private final String anpProductDescription;

    private final BigDecimal glpPercent;

    private final BigDecimal gnnPercent;

    private final BigDecimal gniPercent;

    private final BigDecimal startingValue;

    private final String codifCode;

    private final BigDecimal temperature;

    private final UF consumerUF;

    private final FuelCide cide;

    private final FuelClosing closing;

    private final List<FuelOrigin> origins;

}

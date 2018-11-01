
package eprecise.efiscal4j.nfe.item.fuel;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


/**
 * CIDE Combustíveis
 *
 * @author Fernando C Glizt
 *
 */

@Builder
@Getter
public class FuelCide {

    private final BigDecimal bcCideQuantity;

    private final BigDecimal cideAliquotValue;

    private final BigDecimal cideValue;

}


package eprecise.efiscal4j.nfe.item.fuel;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


/**
 * Informações do grupo de encerrante
 *
 * @author Fernando C Glizt
 *
 */

@Builder
@Getter
public class FuelClosing {

    private final String nozzleNumber;

    private final String pumpNumber;

    private final String tankNumber;

    private final BigDecimal closingBeginValue;

    private final BigDecimal closingEndValue;
    
}

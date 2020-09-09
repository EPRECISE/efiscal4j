
package eprecise.efiscal4j.nfe.item.export;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

/**
 * Exportação indireta
 *
 */

@Builder
@Getter
public class ItemIndirectExport {

    private final String exportRegistrationNumber;

    private final String accessKey;

    private final BigDecimal itemExportQuantity;

}

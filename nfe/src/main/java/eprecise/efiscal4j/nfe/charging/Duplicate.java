
package eprecise.efiscal4j.nfe.charging;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


/**
 * Dados da duplicata
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
@EqualsAndHashCode
public class Duplicate {

    /**
     * Número da duplicata
     * 
     * @param number
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.charging.duplicate.number.isSize}") final String number;

    /**
     * Data de vencimento da duplicata
     * 
     * @param due
     */
    private final Date due;

    /**
     * Valor da duplicata
     * 
     * @param value
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.charging.duplicate.value.isNotNull}") final BigDecimal value;

}

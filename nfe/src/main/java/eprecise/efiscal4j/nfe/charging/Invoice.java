
package eprecise.efiscal4j.nfe.charging;

import java.math.BigDecimal;

import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;


/**
 * Dados da fatura
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class Invoice {

    /**
     * Número da fatura
     * 
     * @param number
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.charging.invoice.number.isSize}") final String number;

    /**
     * Valor original da fatura
     * 
     * @param originalValue
     */
    private BigDecimal originalValue;

    /**
     * Valor do desconto da fatura
     * 
     * @param discountValue
     */
    private BigDecimal discountValue;

    /**
     * Valor líquido da fatura
     * 
     * @return netValue
     */
    public BigDecimal getNetValue() {
        return this.originalValue != null && this.discountValue != null ? this.originalValue.subtract(this.discountValue) : null;
    }

}

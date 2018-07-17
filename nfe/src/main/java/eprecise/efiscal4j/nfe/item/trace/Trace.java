
package eprecise.efiscal4j.nfe.item.trace;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;


/**
 * Grupo para permitir a que os itens da nota sejam rastreados.
 *
 * @author Fernando C Glizt
 *
 */

@lombok.Builder
@Getter
public class Trace {

    /**
     * Número do lote do produto
     *
     * @param batchNumber
     */
    private final String batchNumber;

    /**
     * Quantidade de produtos no lote
     *
     * @param batchQuantity
     */
    private final BigDecimal batchQuantity;

    /**
     * Data de Fabricação do produto
     *
     * @param manufacturing
     */
    private final Date manufacturing;

    /**
     * Data de validade do produto
     *
     * @param expiration
     */
    private final Date expiration;

    /**
     * Código de Agregação
     *
     * @param aggregationCode
     */
    private final String aggregationCode;
}


package eprecise.efiscal4j.nfe.item.medications;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class Medications {

    /**
     * Código de Produto da ANVISA. Utilizar o número do registro do produto da Câmara de Regulação do Mercado de Medicamento – CMED.
     *
     * @param anvisaProductCode
     */
    private final String anvisaProductCode;

    /**
     * Preço Máximo ao Consumidor
     *
     * @param maxPriceConsumers
     */
    private final BigDecimal maxPriceConsumers;

}

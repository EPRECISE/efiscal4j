
package eprecise.efiscal4j.nfe.payment;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import eprecise.efiscal4j.nfe.payment.cardSet.CardSet;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


/**
 * Detalhamento da forma de pagamento
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
@EqualsAndHashCode
public class PaymentDetail {

    /**
     * @see PaymentMethod
     * @param method
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.payment.method.isNotNull}") @Valid final PaymentMethod method;

    /**
     * Valor do Pagamento. Não informar quando a forma de pagamento for "Sem Pagamento", caso contrário deverá ser preenchida.
     * 
     * @param value
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.payment.value.isNotNull}") BigDecimal value;

    /**
     * @see CardSet
     * @param cardSet
     */
    private @Valid final CardSet cardSet;

}

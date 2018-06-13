
package eprecise.efiscal4j.nfe.payment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;


/**
 * Dados de Pagamento
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class Payment {

    /**
     * @see PaymentDetail
     * @param paymentDetails
     */
    private @Builder.Default @Size(min = 1, max = 100, message = "{eprecise.efiscal4j.nfe.payment.details.isSize}") @Valid final Collection<PaymentDetail> details = new HashSet<>();

    /**
     * Valor do Troco
     * 
     * @param changeValue
     */
    private BigDecimal changeValue;

}

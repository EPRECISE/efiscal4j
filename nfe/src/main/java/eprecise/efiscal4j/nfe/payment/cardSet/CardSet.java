/**
 * 
 */

package eprecise.efiscal4j.nfe.payment.cardSet;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.caelum.stella.bean.validation.CNPJ;
import lombok.Builder;
import lombok.Getter;


/**
 * Grupo de Cartões
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class CardSet {

    /**
     * @see CardSetIntegration
     * @param integration
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.payment.cardSet.integration.isNotNull}") @Valid final CardSetIntegration integration;

    /**
     * CNPJ da credenciadora de cartão de crédito/débito
     * 
     * @param cnpj
     */
    private @CNPJ(message = "{eprecise.efiscal4j.nfe.payment.cardSet.cnpj.isCnpj}") final String cnpj;

    /**
     * @see CardFlag
     * @param cardFlag
     */
    private @Valid final CardFlag cardFlag;

    /**
     * Número de autorização da operação cartão de crédito/débito
     * 
     * @param authorizationNumber
     */
    private @Size(min = 1, max = 20, message = "{eprecise.efiscal4j.nfe.payment.cardSet.authorizationNumber.isSize}") final String authorizationNumber;

}

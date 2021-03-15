
package eprecise.efiscal4j.nfe.broker;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.caelum.stella.bean.validation.CNPJ;
import lombok.Builder;
import lombok.Getter;


/**
 * Operação em site ou plataforma de terceiros (intermediadores/marketplace) com detalhes do grupo de Informações do Intermediador da Transação
 *
 */
@Builder
@Getter
public class WithBrokerOperation implements BrokerIndicator {

    private final @NotNull(message = "{eprecise.efiscal4j.nfe.broker.cnpj.isNotNull}") @CNPJ(message = "{eprecise.efiscal4j.nfe.broker.cnpj.isCnpj}") String cnpj;

    private final @NotNull(message = "{eprecise.efiscal4j.nfe.broker.identifier.isNotNull}") @Size(min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.broker.identifier.isSize}") String identifier;

}

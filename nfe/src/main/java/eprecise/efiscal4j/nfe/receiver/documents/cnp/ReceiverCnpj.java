
package eprecise.efiscal4j.nfe.receiver.documents.cnp;

import javax.validation.constraints.NotNull;

import br.com.caelum.stella.bean.validation.CNPJ;
import lombok.Builder;


/**
 * CNPJ do Destinatário
 *
 * @author Fernando Glizt
 *
 */
@Builder
public class ReceiverCnpj implements ReceiverCnp {

    /**
     * CNPJ sem pontuação
     * 
     * @param cnpj
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.documents.cnp.isNotNull}") @CNPJ(message = "{eprecise.efiscal4j.nfe.receiver.documents.cnp.cnpj.isCnpj}") final String cnpj;

    @Override
    public String getCnp() {
        return this.cnpj;
    }

}

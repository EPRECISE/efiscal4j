
package eprecise.efiscal4j.nfe.receiver.documents.cnp;

import javax.validation.constraints.NotNull;

import br.com.caelum.stella.bean.validation.CPF;
import lombok.Builder;


/**
 * CPF do Destinatário
 *
 * @author Fernando Glizt
 *
 */
@Builder
public class ReceiverCpf implements ReceiverCnp {

    /**
     * CPF sem pontuação
     * 
     * @param cpf
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.documents.cnp.isNotNull}") @CPF(message = "{eprecise.efiscal4j.nfe.receiver.documents.cnp.cpf.isCpf}") final String cpf;

    @Override
    public String getCnp() {
        return this.cpf;
    }

}


package eprecise.efiscal4j.nfe.transport.conveyor.cnp;

import javax.validation.constraints.NotNull;

import br.com.caelum.stella.bean.validation.CPF;
import lombok.Builder;


/**
 * Documento de CPF da Transportadora
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
public class ConveyorCpf implements ConveyorCnp {

    /**
     * CPF sem pontuação
     * 
     * @param cpf
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.conveyor.cnp.isNotNull}") @CPF(message = "{eprecise.efiscal4j.nfe.transport.conveyor.cnp.cpf.isCpf}") final String cpf;

    @Override
    public String getCnp() {
        return this.cpf;
    }

}


package eprecise.efiscal4j.nfe.transport.conveyor.cnp;

import javax.validation.constraints.NotNull;

import br.com.caelum.stella.bean.validation.CNPJ;
import lombok.Builder;


/**
 * Documento de CNPJ da Transportadora
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
public class ConveyorCnpj implements ConveyorCnp {

    /**
     * CNPJ sem pontuação
     * 
     * @param cpf
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.conveyor.cnp.isNotNull}") @CNPJ(message = "{eprecise.efiscal4j.nfe.transport.conveyor.cnp.cnpj.isCnpj}") final String cnpj;

    @Override
    public String getCnp() {
        return this.cnpj;
    }

}


package eprecise.efiscal4j.nfe.transport.places.cnp;

import javax.validation.constraints.NotNull;

import br.com.caelum.stella.bean.validation.CPF;
import lombok.Builder;


/**
 * Documento de CPF do Local de Retirada ou Entrega
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
public class PlaceCpf implements PlaceCnp {

    /**
     * CPF sem pontuação
     * 
     * @param cpf
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.cnp.isNotNull}") @CPF(message = "{eprecise.efiscal4j.nfe.transport.place.cnp.cpf.isCpf}") final String cpf;

    @Override
    public String getCnp() {
        return this.cpf;
    }

}


package eprecise.efiscal4j.nfe.transport.places.cnp;

import javax.validation.constraints.NotNull;

import br.com.caelum.stella.bean.validation.CNPJ;
import lombok.Builder;


/**
 * Documento de CNPJ do Local de Retirada ou Entrega
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
public class PlaceCnpj implements PlaceCnp {

    /**
     * CNPJ sem pontuação
     * 
     * @param cpf
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.place.cnp.isNotNull}") @CNPJ(message = "{eprecise.efiscal4j.nfe.transport.place.cnp.cnpj.isCnpj}") final String cnpj;

    @Override
    public String getCnp() {
        return this.cnpj;
    }

}


package eprecise.efiscal4j.nfe.receiver.documents.cnp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Builder;


/**
 * ID estrangeiro do Destinatário
 *
 * @author Fernando Glizt
 *
 */
@Builder
public class ReceiverForeignId implements ReceiverCnp {

    /**
     * ID estrangeiro
     * 
     * @param id
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.documents.cnp.isNotNull}") @Pattern(
            regexp = "([!-ÿ]{0}|[!-ÿ]{5,20})?", message = "{eprecise.efiscal4j.nfe.receiver.documents.cnp.foreignId.isNotForeignId}") final String id;

    @Override
    public String getCnp() {
        return this.id;
    }

}


package eprecise.efiscal4j.nfe.consumer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCnp;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class SimpleConsumer implements Consumer {

    /**
     * @see ReceiverCnp
     * @param cnp
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.documents.cnp.isNotNull}") @Valid final ReceiverCnp cnp;

    /**
     * Nome ou Razão Social
     * 
     * @param name
     */
    private @Size(min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.receiver.documents.name.isSize}") final String name;

}

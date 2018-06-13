
package eprecise.efiscal4j.nfe.receiver.documents;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCnp;
import eprecise.efiscal4j.nfe.receiver.documents.ie.ReceiverIE;
import lombok.Builder;
import lombok.Getter;


/**
 * Documentos do Destinatário
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class ReceiverDocuments {

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
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.documents.name.isNotNull}") @Size(
            min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.receiver.documents.name.isSize}") final String name;

    /**
     * @see ReceiverIE
     * @param ie
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.documents.ie.isNotNull}") @Valid final ReceiverIE ie;

    /**
     * Inscrição Municipal
     * 
     * @param im
     */
    private @Size(min = 1, max = 15, message = "{eprecise.efiscal4j.nfe.receiver.documents.im.isSize}") final String im;

    /**
     * Inscrição na SUFRAMA (Obrigatório nas operações com as áreas com benefícios de incentivos fiscais sob controle da SUFRAMA)
     * 
     * @param suframa
     */
    private @Pattern(regexp = "[0-9]{8,9}", message = "{eprecise.efiscal4j.nfe.receiver.documents.suframa.isNotSuframa}") final String suframa;

}

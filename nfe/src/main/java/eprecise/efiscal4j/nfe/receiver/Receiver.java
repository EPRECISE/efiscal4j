
package eprecise.efiscal4j.nfe.receiver;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import eprecise.efiscal4j.nfe.consumer.Consumer;
import eprecise.efiscal4j.nfe.receiver.address.ReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.documents.ReceiverDocuments;
import lombok.Builder;
import lombok.Getter;


/**
 * Destinatário do documento fiscal
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class Receiver implements Consumer {

    /**
     * @see ReceiverDocuments
     * @param documents
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.documents.isNotNull}") @Valid final ReceiverDocuments documents;

    /**
     * @see ReceiverAddress
     * @param address
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.address.isNotNull}") @Valid final ReceiverAddress address;

    /**
     * e-mail do destinatário
     * 
     * @param email
     */
    private @Email(message = "{eprecise.efiscal4j.nfe.receiver.email.isNotEmail}") final String email;

    /**
     * Telefone, preencher com Código DDD + número do telefone , nas operações com exterior é permtido informar o código do país + código da localidade + número do telefone
     * 
     * @param phone
     */
    private @Pattern(regexp = "[0-9]{6,14}", message = "{eprecise.efiscal4j.nfe.receiver.phone.isNotPhone}") final String phone;

}

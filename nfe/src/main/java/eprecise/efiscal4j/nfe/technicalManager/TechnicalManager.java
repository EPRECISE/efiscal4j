
package eprecise.efiscal4j.nfe.technicalManager;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.caelum.stella.bean.validation.CNPJ;
import lombok.Builder;
import lombok.Getter;


/**
 * Define informações do responsável técnico pelo sistema de emissão de DF-e
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class TechnicalManager implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * CNPJ do responsável técnico
     * 
     * @param cnpj
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.technicalManager.cnpj.isNotNull}") @CNPJ(message = "{eprecise.efiscal4j.nfe.technicalManager.cnpj.isCnpj}") final String cnpj;

    /**
     * Nome da pessoa a ser contatada na empresa desenvolvedora do sistema utilizado na emissão do documento fiscal eletrônico
     * 
     * @param contactName
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.technicalManager.contactName.isNotNull}") @Size(
            min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.technicalManager.contractName.isSize}") final String contactName;
    
    /**
     * E-mail da pessoa a ser contatada na empresa desenvolvedora do sistema
     * 
     * @param email
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.technicalManager.email.isNotNull}") @Size(
            min = 6, max = 60, message = "{eprecise.efiscal4j.nfe.technicalManager.email.isSize}") final String email;
    
    /**
     * Telefone da pessoa a ser contatada na empresa desenvolvedora do sistema
     * 
     * @param phone
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.technicalManager.phone.isNotNull}") @Pattern(regexp = "[0-9]{6,14}", message = "{eprecise.efiscal4j.nfe.technicalManager.phone.isNotPhone}") final String phone;
    
    
    /**
     * @see CSRT
     * @param csrt
     */
    private @Valid final CSRT csrt;

}

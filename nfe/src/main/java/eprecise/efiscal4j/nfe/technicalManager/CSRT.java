
package eprecise.efiscal4j.nfe.technicalManager;

import java.io.Serializable;
import java.util.Base64;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import lombok.Builder;
import lombok.Getter;


/**
 * Código de Segurança do Responsável Técnico
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class CSRT implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID do Código de Segurança do Responsável Técnico
     * 
     * @param id
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.technicalManager.csrt.id.isNotNull}") @Pattern(regexp = "[0-9]{2}", message = "{eprecise.efiscal4j.nfe.technicalManager.csrt.id.isNotCsrtId}") final String id;
    
    /**
     * Chave do Código de Segurança do Responsável Técnico
     * 
     * @param key
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.technicalManager.csrt.key.isNotNull}") final String key;
    
    public String getHash(final String accessKey) {
        if(this.id != null && this.key != null) {
            final StringBuilder csrtKeyWithAccessKey = new StringBuilder(this.key).append(accessKey);
            final byte[] sha1 = Hashing.sha1().hashString(csrtKeyWithAccessKey.toString(), Charsets.UTF_8).asBytes();
            final String base64 =  Base64.getEncoder().encodeToString(sha1);
            return base64;
        }
        return null;
    }

}

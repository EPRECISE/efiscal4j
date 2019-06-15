
package eprecise.efiscal4j.nfe.csc;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;


/**
 * Código de Segurança do Contribuinte
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class CSC implements Serializable {

    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "[0-9]{6}|", message = "{eprecise.efiscal4j.nfe.csc.identifier.isNotIdentifier}") private final String identifier;

    private final String cscValue;

}

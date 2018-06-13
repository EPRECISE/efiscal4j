
package eprecise.efiscal4j.nfe.charging;

import java.util.Collection;

import javax.validation.Valid;

import lombok.Builder;
import lombok.Getter;


/**
 * Dados da cobrança
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class Charging {

    /**
     * @see Invoice
     * @param invoice
     */
    private @Valid final Invoice invoice;

    /**
     * @see Duplicate
     * @param duplicates
     */
    private @Valid final Collection<Duplicate> duplicates;

}

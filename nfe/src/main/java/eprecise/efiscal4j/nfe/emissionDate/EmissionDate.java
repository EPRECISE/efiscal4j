
package eprecise.efiscal4j.nfe.emissionDate;

import java.io.Serializable;
import java.util.Date;


/**
 * Tipo de Data de Emissão do documento fiscal.
 * 
 * @see CurrentEmissionDate
 * @see CustomEmissionDate
 *
 * @author Fernando Glizt
 *
 */
public interface EmissionDate extends Serializable {

    /**
     * Data de Emissão do documento fiscal.
     * 
     * @return date
     *
     */
    Date getDate();

}


package eprecise.efiscal4j.nfe.entranceOrExitDate;

import java.io.Serializable;
import java.util.Date;


/**
 * Tipo de Data de Entrada ou saída da mercadoria
 * 
 * @see CurrentIODate
 * @see CustomIODate
 *
 * @author Fernando Glizt
 *
 */
public interface IODate extends Serializable {

    /**
     * Data de Entrada ou Saída da mercadoria
     * 
     * @return date
     *
     */
    Date getDate();

}

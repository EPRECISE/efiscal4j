
package eprecise.efiscal4j.nfe.entranceOrExitDate;

import java.util.Date;


/**
 * Data de entrada ou saída da mercadoria na NF-e com data corrente (obtida no momento da emissão)
 * 
 *
 * @author Fernando Glizt
 *
 */
public class CurrentIODate implements IODate {

    private static final long serialVersionUID = 1L;

    @Override
    public Date getDate() {
        return new Date();
    }

}

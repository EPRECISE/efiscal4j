
package eprecise.efiscal4j.nfe.emissionDate;

import java.util.Date;


/**
 * Data de emissão do documento fiscal com data corrente (obtida no momento da emissão)
 * 
 *
 * @author Fernando Glizt
 *
 */
public class CurrentEmissionDate implements EmissionDate {

    private static final long serialVersionUID = 1L;

    @Override
    public Date getDate() {
        return new Date();
    }

}

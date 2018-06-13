
package eprecise.efiscal4j.nfe.emissionDate;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Builder;


/**
 * Data de emissão do documento fiscal com data informada
 * 
 *
 * @author Fernando Glizt
 *
 */
@Builder
public class CustomEmissionDate implements EmissionDate {

    private static final long serialVersionUID = 1L;

    /**
     * Data de emissão informada do documento fiscal
     * 
     * @param custom
     */
    private final @NotNull(message = "{eprecise.efiscal4j.nfe.emissionDate.custom.isNotNull}") Date custom;

    @Override
    public Date getDate() {
        return custom;
    }

}


package eprecise.efiscal4j.nfe.entranceOrExitDate;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Builder;


/**
 * Data de entrada ou saída da mercadoria na NF-e com data informada
 * 
 *
 * @author Fernando Glizt
 *
 */
@Builder
public class CustomIODate implements IODate {

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

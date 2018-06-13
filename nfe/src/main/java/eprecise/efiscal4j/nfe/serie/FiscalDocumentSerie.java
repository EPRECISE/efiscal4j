
package eprecise.efiscal4j.nfe.serie;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;


/**
 * Define configurações do modelo do documento, numero da série e ambiente de transmissão
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class FiscalDocumentSerie implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Série do Documento Fiscal: série normal: 0-889 | Avulsa Fisco: 890-899 | SCAN: 900-999
     * 
     * @param number
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocumentSerie.serie.isNotNull}") @Min(value = 1, message = "{eprecise.efiscal4j.nfe.fiscalDocumentSerie.serie.isMinSize}") @Max(
            value = 999, message = "{eprecise.efiscal4j.nfe.fiscalDocumentSerie.serie.isMaxSize}") final Integer number;

    /**
     * @see TransmissionEnvironment
     * @param environment
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocumentSerie.environment.isNotNull}") final TransmissionEnvironment environment;

}

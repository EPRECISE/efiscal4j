
package eprecise.efiscal4j.nfe;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import eprecise.efiscal4j.nfe.charging.Charging;
import eprecise.efiscal4j.nfe.emissionDate.EmissionDate;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.transport.Transport;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Base da Nota Fiscal Eletrônica, utilizada tanto para NF-e quanto para NFC-e
 *
 * @author Fernando Glizt
 *
 */
@AllArgsConstructor
@Getter
public abstract class FiscalDocument {

    /**
     * @see FiscalDocumentSerie
     * @param serie
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.serie.isNotNull}") @Valid final FiscalDocumentSerie serie;

    /**
     * Número do Documento Fiscal
     * 
     * @param number
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.number.isNotNull}") @Min(value = 1, message = "{eprecise.efiscal4j.nfe.fiscalDocument.number.isMinSize}") @Max(
            value = 999999999, message = "{eprecise.efiscal4j.nfe.fiscalDocument.number.isMaxSize}") final Integer number;

    /**
     * @see EmissionDate
     * @param emission
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.emission.isNotNull}") @Valid final EmissionDate emission;

    /**
     * @see Emitter
     * @param emitter
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.emitter.isNotNull}") @Valid final Emitter emitter;

    /**
     * @see Item
     * @param items
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.items.isNotNull}") @Size(
            min = 1, max = 990, message = "{eprecise.efiscal4j.nfe.fiscalDocument.items.isSize}") @Valid final Collection<Item> items;

    /**
     * @see Charging
     * @param charging
     */
    private final @Valid Charging charging;

    /**
     * @see Payment
     * @param payment
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.payment.isNotNull}") @Valid final Payment payment;

    /**
     * @see Transport
     * @param transport
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.transport.isNotNull}") @Valid final Transport transport;

    /**
     * Informações complementares de interesse do Contribuinte
     * 
     * @param details
     */
    private @Size(min = 1, max = 5000, message = "{eprecise.efiscal4j.nfe.fiscalDocument.details.isSize}") final String details;

}

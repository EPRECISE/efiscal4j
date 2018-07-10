
package eprecise.efiscal4j.nfe;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.nfe.charging.Charging;
import eprecise.efiscal4j.nfe.emissionDate.CurrentEmissionDate;
import eprecise.efiscal4j.nfe.emissionDate.EmissionDate;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.entranceOrExitDate.CurrentIODate;
import eprecise.efiscal4j.nfe.entranceOrExitDate.IODate;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.receiver.Receiver;
import eprecise.efiscal4j.nfe.references.DocumentReference;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.transport.Transport;
import lombok.Builder;
import lombok.Getter;


@Getter
public class NFe extends FiscalDocument {

    /**
     * @see Receiver
     * @param receiver
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.receiver.isNotNull}") final @Valid Receiver receiver;

    /**
     * @see IODate
     * @param entranceOrExit
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.nfe.entranceOrExit.isNotNull}") @Valid final IODate entranceOrExit;

    /**
     * @see NFeFinality
     * @param finality
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.nfe.finality.isNotNull}") final NFeFinality finality;

    /**
     * @see FiscalDocumentType
     * @param type
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.nfe.type.isNotNull}") final FiscalDocumentType type;

    /**
     * Indica se a operação é com consumidor final
     * 
     * @param endConsumer
     *
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.nfe.endConsumer.isNotNull}") final Boolean endConsumer;

    /**
     * Descrição da Natureza da Operação
     * 
     * @param operationDescription
     *
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.nfe.operationDescription.isNotNull}") @Size(
            min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.nfe.operationDescription.isSize}") String operationDescription;

    /**
     * @see DocumentReference
     * @param documentReferences
     */
    private final @Valid Collection<DocumentReference> documentReferences;

    @Builder
    public NFe(FiscalDocumentSerie serie, Integer number, EmissionDate emission, Emitter emitter, Collection<Item> items, Charging charging, Payment payment, Transport transport, String details,
            Receiver receiver, IODate entranceOrExit, NFeFinality finality, FiscalDocumentType type, Boolean endConsumer, String operationDescription,
            Collection<DocumentReference> documentReferences) {
        super(serie, number, Optional.ofNullable(emission).orElse(new CurrentEmissionDate()), emitter, items, charging, payment, transport, details);
        this.receiver = receiver;
        this.entranceOrExit = Optional.ofNullable(entranceOrExit).orElse(new CurrentIODate());
        this.finality = Optional.ofNullable(finality).orElse(NFeFinality.NORMAL);
        this.type = Optional.ofNullable(type).orElse(FiscalDocumentType.OUT);
        this.endConsumer = endConsumer;
        this.operationDescription = operationDescription;
        this.documentReferences = documentReferences;
    }

	@Override
	public FiscalDocumentModel getModel() {
		return FiscalDocumentModel.NFE;
	}

    

}

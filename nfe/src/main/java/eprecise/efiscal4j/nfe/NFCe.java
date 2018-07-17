
package eprecise.efiscal4j.nfe;

import java.util.Collection;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.nfe.charging.Charging;
import eprecise.efiscal4j.nfe.consumer.Consumer;
import eprecise.efiscal4j.nfe.csc.CSC;
import eprecise.efiscal4j.nfe.emissionDate.EmissionDate;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.transport.Transport;
import lombok.Builder;
import lombok.Getter;


@Getter
public class NFCe extends FiscalDocument {

    /**
     * @see Consumer
     * @param consumer
     */
    private final Consumer consumer;
    
    /**
     * @see CSC
     * @param csc
     */
    private final CSC csc;
    
    

    @Builder
    public NFCe(FiscalDocumentSerie serie, Integer number, EmissionDate emission, Emitter emitter, Collection<Item> items, Charging charging, Payment payment, Transport transport, String details,
            Consumer consumer, final CSC csc) {
        super(serie, number, emission, emitter, items, charging, payment, transport, details);
        this.consumer = consumer;
        this.csc = csc;
    }

	@Override
	public FiscalDocumentModel getModel() {
		return FiscalDocumentModel.NFCE;
	}

}

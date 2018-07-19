
package eprecise.efiscal4j.nfe;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;

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
    public NFCe(final String code, final FiscalDocumentSerie serie, final Integer number, final EmissionDate emission, final Emitter emitter, final Collection<Item> items, final Charging charging,
            final Payment payment, final Transport transport, final String details, final Consumer consumer, final CSC csc) {
        super(Optional.ofNullable(code).orElse(String.format("%08d", new Random().nextInt(100000000))), serie, number, emission, emitter, items, charging, payment, transport, details);
        this.consumer = consumer;
        this.csc = csc;
    }

    @Override
    public FiscalDocumentModel getModel() {
        return FiscalDocumentModel.NFCE;
    }

    @Override
    public boolean isEndConsumer() {
        return true;
    }

}


package eprecise.efiscal4j.nfe;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.nfe.broker.BrokerIndicator;
import eprecise.efiscal4j.nfe.charging.Charging;
import eprecise.efiscal4j.nfe.cnpAccessXml.CnpAccessXml;
import eprecise.efiscal4j.nfe.consumer.Consumer;
import eprecise.efiscal4j.nfe.csc.CSC;
import eprecise.efiscal4j.nfe.emissionDate.EmissionDate;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.technicalManager.TechnicalManager;
import eprecise.efiscal4j.nfe.total.FiscalDocumentTotal;
import eprecise.efiscal4j.nfe.transport.Transport;
import eprecise.efiscal4j.nfe.v400.NFeTransmissionMethod;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;


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
    private @NotNull(message = "{eprecise.efiscal4j.nfe.nfce.csc.isNotNull}") final CSC csc;

    private NFeTransmissionMethod transmissionMethod;

    @Builder
    public NFCe(
            final String code,
            final FiscalDocumentSerie serie,
            final Integer number,
            final EmissionDate emission,
            final Emitter emitter,
            final List<Item> items,
            final Charging charging,
            final Payment payment,
            final Transport transport,
            final PresenceIndicator presenceIndicator,
            final BrokerIndicator brokerIndicator,
            final TechnicalManager technicalManager,
            final String details,
            final Consumer consumer,
            final CSC csc,
            final FiscalDocumentTotal.AddsValue totalAddsValue,
            final Collection<CnpAccessXml> cnpAccessXmls,
            final NFeTransmissionMethod transmissionMethod
    ) {
        super(
                Optional.ofNullable(code)
                        .orElse(
                                String.format(
                                        "%08d",
                                        new Random()
                                                .nextInt(100000000)
                                )
                        ),
                serie,
                number,
                emission,
                emitter,
                items,
                charging,
                payment,
                transport,
                presenceIndicator,
                brokerIndicator,
                technicalManager,
                details,
                totalAddsValue,
                cnpAccessXmls,
                transmissionMethod
        );
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

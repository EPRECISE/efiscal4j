
package eprecise.efiscal4j.nfe.v310.sharing.adapters.dispatchFromFiscalDocument;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocumentCCe;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterLegalEntityDocuments;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterNaturalPersonDocuments;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.v310.sharing.Event;
import eprecise.efiscal4j.nfe.v310.sharing.EventDetailCCe;
import eprecise.efiscal4j.nfe.v310.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.v310.sharing.EventInfo;
import eprecise.efiscal4j.nfe.v310.sharing.EventType;
import eprecise.efiscal4j.nfe.version.EventDispatchCCeVersion;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


public class EventDispatchCCeAdapter implements EventDispatchCCeVersion {

    private static final DateFormat NFE_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private final FiscalDocumentCCe fiscalDocumentCCe;

    private final Certificate certificate;

    public EventDispatchCCeAdapter(final FiscalDocumentCCe fiscalDocumentCCe, final Certificate certificate) {
        this.fiscalDocumentCCe = fiscalDocumentCCe;
        this.certificate = certificate;
    }

    @Override
    public EventDispatch buildEventDispatchCCe() {
     // @formatter:off 
        try {
            return new EventDispatch.Builder()
                    .withBatchId(Optional.ofNullable(this.fiscalDocumentCCe.getProcessedFiscalDocument().getDocument()).map(d -> d.getNumber().toString()).orElse(null))
                    .withEvents(Arrays.asList(new Event.Builder()
                            .withEventInfo(this.buildEventInfo())
                            .build(new DefaultSigner(this.certificate))).stream().collect(Collectors.toCollection(ArrayList::new)))
                    .build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
     // @formatter:on   
    }

    private EventInfo buildEventInfo() {
        // @formatter:off    
           final Optional<FiscalDocument> fiscalDocument = Optional.ofNullable(this.fiscalDocumentCCe.getProcessedFiscalDocument().getDocument());
           return new EventInfo.Builder()
                   .withIbgeOrgan(IBGEOrgan.findByAcronym(fiscalDocument.map(d -> d.getEmitter().getAddress().getCity().getUf().getAcronym()).orElse(null)))
                   .withTransmissionEnvironment(TransmissionEnvironment.findBy(fiscalDocument.map(d -> d.getSerie().getEnvironment().getValue()).orElse(null)).orElse(null))
                   .withAuthorCnpj(fiscalDocument.map(d -> d.getEmitter().getDocuments()).filter(EmitterLegalEntityDocuments.class::isInstance).map(led -> led.getCnp()).orElse(null))
                   .withAuthorCpf(fiscalDocument.map(d -> d.getEmitter().getDocuments()).filter(EmitterNaturalPersonDocuments.class::isInstance).map(npd -> npd.getCnp()).orElse(null))
                   .withAcessKey(this.fiscalDocumentCCe.getProcessedFiscalDocument().getAccessKey())
                   .withEventDateTime(EventDispatchCCeAdapter.NFE_DATETIME_FORMAT.format(new Date()))
                   .withEventType(EventType.CCE)
                   .withEventSeqNumber(String.valueOf(this.fiscalDocumentCCe.getEventSeqNumber()))
                   .withEventVersion(FiscalDocumentVersion.VERSION_1_00.getValue())
                   .withEventDetail(new EventDetailCCe.Builder()
                           .withCorrection(this.fiscalDocumentCCe.getCorrection())
                           .build())
                   .build();
        // @formatter:on    
    }
}

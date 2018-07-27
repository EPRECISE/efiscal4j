
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocumentCancel;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterLegalEntityDocuments;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterNaturalPersonDocuments;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v400.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.v400.sharing.Event;
import eprecise.efiscal4j.nfe.v400.sharing.EventDetailCancellation;
import eprecise.efiscal4j.nfe.v400.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.v400.sharing.EventInfo;
import eprecise.efiscal4j.nfe.v400.sharing.EventType;
import eprecise.efiscal4j.nfe.version.EventDispatchCancelVersion;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


public class EventDispatchCancelAdapter implements EventDispatchCancelVersion {

    private static final DateFormat NFE_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private static final String NFE_EVENT_SEQ_NUMBER = "1";

    private final FiscalDocumentCancel fiscalDocumentCancel;

    public EventDispatchCancelAdapter(FiscalDocumentCancel fiscalDocumentCancel) {
        this.fiscalDocumentCancel = fiscalDocumentCancel;
    }

    @Override
    public EventDispatch buildEventDispatchCancel() {
     // @formatter:off
        try {    
            return new EventDispatch.Builder()
                    .withBatchId(Optional.ofNullable(this.fiscalDocumentCancel.getProcessedFiscalDocument().getDocument()).map(d -> d.getNumber().toString()).orElse(null))
                    .withEvents(Arrays.asList(new Event.Builder()
                            .withEventInfo(this.buildEventInfo())
                            .build(new DefaultSigner(Optional.ofNullable(this.fiscalDocumentCancel.getProcessedFiscalDocument().getDocument()).map(d -> d.getEmitter().getCertificate()).orElse(null)))))
                    .build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
	 // @formatter:on	
    }

    private EventInfo buildEventInfo() {
     // @formatter:off    
        final Optional<FiscalDocument> fiscalDocument = Optional.ofNullable(this.fiscalDocumentCancel.getProcessedFiscalDocument().getDocument());
        return new EventInfo.Builder()
                .withIbgeOrgan(IBGEOrgan.findByAcronym(fiscalDocument.map(d -> d.getEmitter().getAddress().getCity().getUf().getAcronym()).orElse(null)))
                .withTransmissionEnvironment(TransmissionEnvironment.findBy(fiscalDocument.map(d -> d.getSerie().getEnvironment().getValue()).orElse(null)).orElse(null))
                .withAuthorCnpj(fiscalDocument.map(d -> d.getEmitter().getDocuments()).filter(EmitterLegalEntityDocuments.class::isInstance).map(led -> led.getCnp()).orElse(null))
                .withAuthorCpf(fiscalDocument.map(d -> d.getEmitter().getDocuments()).filter(EmitterNaturalPersonDocuments.class::isInstance).map(npd -> npd.getCnp()).orElse(null))
                .withAcessKey(this.fiscalDocumentCancel.getProcessedFiscalDocument().getAccessKey())
                .withEventDateTime(EventDispatchCancelAdapter.NFE_DATETIME_FORMAT.format(new Date()))
                .withEventType(EventType.CANC_NFE)
                .withEventSeqNumber(EventDispatchCancelAdapter.NFE_EVENT_SEQ_NUMBER)
                .withEventVersion(FiscalDocumentVersion.VERSION_1_00.getValue())
                .withEventDetail(new EventDetailCancellation.Builder()
                        .withJustification(this.fiscalDocumentCancel.getJustification())
                        .withProtocolNumber(this.fiscalDocumentCancel.getProcessedFiscalDocument().getProtocolNumber())
                        .build())
                .build();
     // @formatter:on    
    }
}

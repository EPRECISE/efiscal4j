
package eprecise.efiscal4j.nfe.v310.sharing.adapters.processedFiscalDocument;

import java.text.ParseException;
import java.util.Optional;

import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocument.Processed;
import eprecise.efiscal4j.nfe.FiscalDocumentCCe;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.v310.sharing.EventDetailCCe;
import eprecise.efiscal4j.nfe.v310.sharing.EventProtocol;
import eprecise.efiscal4j.nfe.v310.sharing.EventResponse;
import eprecise.efiscal4j.nfe.v310.sharing.EventResponseInfo;
import eprecise.efiscal4j.nfe.v310.types.NFeDateTimeUTC;


public class ProcessedFiscalDocumentCCeAdapter {

    private final EventProtocol eventProtocol;

    private final Processed processedFiscalDocument;

    public ProcessedFiscalDocumentCCeAdapter(final EventProtocol eventProtocol, final FiscalDocument.Processed processedFiscalDocument) {
        this.eventProtocol = eventProtocol;
        this.processedFiscalDocument = processedFiscalDocument;
    }

    public FiscalDocumentCCe.Processed buildProcessedFiscalDocumentCCe() {
     // @formatter:off
        return FiscalDocumentCCe.Processed.builder()
                .id(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getId).orElse(null))
                .date(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getEventRegisterDateTime).map(t -> {
                    try {
                        return NFeDateTimeUTC.dateFormat.parse(t);
                    } catch (final ParseException e) {
                        throw new RuntimeException(e);
                    }
                }).orElse(null))
                .protocolNumber(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getProtocolNumber).orElse(null))
                .details(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getEventDescription).orElse(null))
                .status(EventStatus.builder()
                        .statusCode(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getStatusCode).orElse(null))
                        .statusDescription(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getStatusDescription).orElse(null))
                        .build())
                .document(this.buildFiscalDocumentCCe())
                .processedEventVersion(this.eventProtocol)
                .build();
     // @formatter:on
    }

    private FiscalDocumentCCe buildFiscalDocumentCCe() {
     // @formatter:off
        return FiscalDocumentCCe.builder()
                .eventSeqNumber(Optional.ofNullable(this.eventProtocol.getEvent()).map(e -> e.getEventInfo()).map(ei -> ei.getEventSeqNumber()).map(Integer::parseInt).orElse(null))
                .correction(Optional.ofNullable(this.eventProtocol.getEvent()).map(e -> e.getEventInfo()).map(ei -> ei.getEventDetail()).filter(EventDetailCCe.class::isInstance).map(EventDetailCCe.class::cast).map(EventDetailCCe::getCorrection).orElse(null))
                .processedFiscalDocument(this.processedFiscalDocument)
                .build();
     // @formatter:on
    }

}

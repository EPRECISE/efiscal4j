
package eprecise.efiscal4j.nfe.v400.sharing.adapters.processedFiscalDocument;

import java.text.ParseException;
import java.util.Optional;

import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocumentCancel;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.v400.sharing.EventDetailCancellation;
import eprecise.efiscal4j.nfe.v400.sharing.EventProtocol;
import eprecise.efiscal4j.nfe.v400.sharing.EventResponse;
import eprecise.efiscal4j.nfe.v400.sharing.EventResponseInfo;
import eprecise.efiscal4j.nfe.v400.types.NFeDateTimeUTC;


public class ProcessedFiscalDocumentCancelAdapter {

    private final EventProtocol eventProtocol;

    private final FiscalDocument.Processed processedFiscalDocument;

    public ProcessedFiscalDocumentCancelAdapter(final EventProtocol eventProtocol, final FiscalDocument.Processed processedFiscalDocument) {
        this.eventProtocol = eventProtocol;
        this.processedFiscalDocument = processedFiscalDocument;
    }

    public FiscalDocumentCancel.Processed buildProcessedFiscalDocumentCancel() {
        // @formatter:off
           return FiscalDocumentCancel.Processed.builder()
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
                   .document(this.buildFiscalDocumentCancel())
                   .processedEventVersion(this.eventProtocol)
                   .build();
        // @formatter:on
    }

    private FiscalDocumentCancel buildFiscalDocumentCancel() {
        return FiscalDocumentCancel.builder().justification(Optional.ofNullable(this.eventProtocol.getEvent()).map(e -> e.getEventInfo()).map(ei -> ei.getEventDetail())
                .filter(EventDetailCancellation.class::isInstance).map(EventDetailCancellation.class::cast).map(EventDetailCancellation::getJustification).orElse(null))
                .processedFiscalDocument(this.processedFiscalDocument).build();
    }

}

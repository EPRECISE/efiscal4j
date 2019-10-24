
package eprecise.efiscal4j.nfe.v310.sharing.adapters.processedFiscalDocument;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocument.Processed;
import eprecise.efiscal4j.nfe.FiscalDocumentCCe;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.v310.sharing.*;
import eprecise.efiscal4j.nfe.v310.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.version.FiscalDocumentSupportedVersion;


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
            .date(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getEventRegisterDateTime).map(ProcessedFiscalDocumentCCeAdapter::convertToDate).orElse(null))
            .protocolNumber(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getProtocolNumber).orElse(null))
            .details(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getEventDescription).orElse(null))
            .status(EventStatus.builder()
                .statusCode(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getStatusCode).orElse(null))
                .statusDescription(Optional.ofNullable(this.eventProtocol.getEventResponse()).map(EventResponse::getEventResponseInfo).map(EventResponseInfo::getStatusDescription).orElse(null))
                .build())
            .document(this.buildFiscalDocumentCCe())
            .processedEventVersion(this.eventProtocol)
            .eventDate(Optional.ofNullable(this.eventProtocol.getEvent().getEventInfo().getEventDateTime()).map(ProcessedFiscalDocumentCCeAdapter::convertToDate).orElse(null))
            .eventCode(Optional.ofNullable(this.eventProtocol.getEventResponse().getEventResponseInfo().getEventType()).map(EventType::getCode).orElse(null))
            .eventDescription(Optional.ofNullable(this.eventProtocol.getEventResponse().getEventResponseInfo().getEventType()).map(EventType::getDescription).orElse(null))
            .ibgeOrgan(Optional.ofNullable(this.eventProtocol.getEventResponse().getEventResponseInfo().getIbgeOrgan()).map(IBGEOrgan::getDescription).orElse(null))
            .transmissionEnvironment(Optional.ofNullable(this.eventProtocol.getEventResponse().getEventResponseInfo().getTransmissionEnvironment()).map(TransmissionEnvironment::getDescription).orElse(null))
            .version(this.eventProtocol.getEventResponse().getVersion())
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

    private static Date convertToDate(String t) {
        try {
            return NFeDateTimeUTC.dateFormat.parse(t);
        } catch (final ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

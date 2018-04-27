
package eprecise.efiscal4j.nfe.v400.sharing;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * 
 * @author Felipe Bueno
 *
 */
public class EventDetailAdapter extends XmlAdapter<EventDetailAdapter.AdaptedEventDetail, EventDetail> {

    @Override
    public AdaptedEventDetail marshal(EventDetail eventDetail) throws Exception {
        if (eventDetail == null) {
            return null;
        }

        //@formatter:off
        if (eventDetail instanceof EventDetailCancellation) {
            final EventDetailCancellation eventDetCanc = (EventDetailCancellation) eventDetail;
            return new AdaptedEventDetail(
                    eventDetCanc.getVersion(), 
                    eventDetCanc.getEventDescription(), 
                    eventDetCanc.getProtocolNumber(),
                    eventDetCanc.getJustification(),
                    null,
                    null);
        }else if (eventDetail instanceof EventDetailCCe) {
            final EventDetailCCe eventDetCce = (EventDetailCCe) eventDetail;
            return new AdaptedEventDetail(
                    eventDetCce.getVersion(), 
                    eventDetCce.getEventDescription(), 
                    null,
                    null,
                    eventDetCce.getCorrection(),
                    eventDetCce.getTermsOfUse());
        }else if(eventDetail instanceof EventDetailRecipientManifestation){
            final EventDetailRecipientManifestation eventDetCanc = (EventDetailRecipientManifestation) eventDetail;
            return new AdaptedEventDetail(
                    eventDetCanc.getVersion(), 
                    eventDetCanc.getEventDescription(), 
                    null,
                    eventDetCanc.getJustification(),
                    null,
                    null);
        }else {
            throw new UnsupportedOperationException();
        }
        //@formatter:on        
    }

    @Override
    public EventDetail unmarshal(AdaptedEventDetail adaptedEventDetail) throws Exception {
        if (adaptedEventDetail == null) {
            return null;
        }

        //@formatter:off
        if (adaptedEventDetail.getProtocolNumber()!= null) {
            return new EventDetailCancellation.Builder().withProtocolNumber(adaptedEventDetail.getProtocolNumber())
                    .withJustification(adaptedEventDetail.getJustification()).build();            
        } else if(adaptedEventDetail.getCorrection() != null) {
            return new EventDetailCCe.Builder().withCorrection(adaptedEventDetail.getCorrection()).build();
        }else if(adaptedEventDetail.getEventDescription() != null){
            final EventType et = EventType.findByFullDescriptionWithNoAccents(adaptedEventDetail.getEventDescription());
            
            if(et != null) {
                return new EventDetailRecipientManifestation.Builder().withEventType(et).withJustification(adaptedEventDetail.justification)
                        .build();
            }
        }
        
        throw new UnsupportedOperationException();        
        //@formatter:on        
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    static class AdaptedEventDetail {

        private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version;

        private @XmlElement(name = "descEvento") @NotNull final String eventDescription;

        private @XmlElement(name = "nProt") @Size(max = 15) @Pattern(regexp = "[0-9]{15}") final String protocolNumber;

        private @XmlElement(name = "xJust") @NotNull @Size(min = 15, max = 255) @NFeString final String justification;

        private @XmlElement(name = "xCorrecao") @NotNull @Size(min = 15, max = 1000) @NFeString final String correction;

        private @XmlElement(name = "xCondUso") @NotNull final String termsOfUse;

        public AdaptedEventDetail() {
            this.version = null;
            this.eventDescription = null;
            this.protocolNumber = null;
            this.justification = null;
            this.correction = null;
            this.termsOfUse = null;
        }

        public AdaptedEventDetail(FiscalDocumentVersion version, String eventDescription, String protocolNumber, String justification, String correction, String termsOfUse) {
            this.version = version;
            this.eventDescription = eventDescription;
            this.protocolNumber = protocolNumber;
            this.justification = justification;
            this.correction = correction;
            this.termsOfUse = termsOfUse;
        }

        public FiscalDocumentVersion getVersion() {
            return this.version;
        }

        public String getEventDescription() {
            return this.eventDescription;
        }

        public String getProtocolNumber() {
            return this.protocolNumber;
        }

        public String getJustification() {
            return this.justification;
        }

        public String getCorrection() {
            return this.correction;
        }

        public String getTermsOfUse() {
            return this.termsOfUse;
        }

    }
}

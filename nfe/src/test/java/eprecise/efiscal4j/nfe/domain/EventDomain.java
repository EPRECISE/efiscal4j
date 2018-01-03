
package eprecise.efiscal4j.nfe.domain;

import java.util.ArrayList;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.sharing.Event;
import eprecise.efiscal4j.nfe.sharing.EventDetail;
import eprecise.efiscal4j.nfe.sharing.EventDetailCCe;
import eprecise.efiscal4j.nfe.sharing.EventDetailCancellation;
import eprecise.efiscal4j.nfe.sharing.EventDetailRecipientManifestation;
import eprecise.efiscal4j.nfe.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.sharing.EventInfo;
import eprecise.efiscal4j.nfe.sharing.EventType;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


class EventDomain {

    private static EventDomain internalEntity;

    private EventDomain() {
    }

    public static EventDomain getInstance() {
        if (EventDomain.internalEntity == null) {
            EventDomain.internalEntity = new EventDomain();
        }
        return EventDomain.internalEntity;
    }

    public EventDispatch buildEventDispatchCancellation(DefaultSigner signer) throws Exception {
        final ArrayList<Event> eventList = new ArrayList<>();
        //@formatter:off        
        eventList.add(new Event.Builder()
                            .withEventInfo(new EventInfo.Builder()                                                 
                                                 .withIbgeOrgan(IBGEOrgan.PR)
                                                 .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                 .withAuthorCnpj("04229224000120")
                                                 .withAcessKey("41150801219338000100550000000000021765232807")
                                                 .withEventDateTime("2015-08-29T09:56:43-03:00")
                                                 .withEventType(EventType.CANC_NFE)
                                                 .withEventSeqNumber("1")
                                                 .withEventVersion(FiscalDocumentVersion.VERSION_1_00.getValue())                                                       
                                                 .withEventDetail(new EventDetailCancellation.Builder()                                                                        
                                                                        .withProtocolNumber("141150000887513")
                                                                        .withJustification("Teste Teste Teste Teste")
                                                                        .build())
                                                 .build())
                            .build(signer));
        
        
        return new EventDispatch.Builder()
                     .withBatchId("1")
                     .withEvents(eventList)                                                                 
                     .build();
        //@formatter:on
    }

    public EventDispatch buildEventDispatchCCe(DefaultSigner signer) throws Exception {
        final ArrayList<Event> eventList = new ArrayList<>();
        //@formatter:off        
        eventList.add(new Event.Builder()
                            .withEventInfo(new EventInfo.Builder()                                                 
                                                 .withIbgeOrgan(IBGEOrgan.PR)
                                                 .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                 .withAuthorCnpj("14241297000191")
                                                 .withAcessKey("41150801219338000100550000000000021765232807")
                                                 .withEventDateTime("2015-08-29T09:56:43-03:00")
                                                 .withEventType(EventType.CCE)
                                                 .withEventSeqNumber("1")
                                                 .withEventVersion(FiscalDocumentVersion.VERSION_1_00.getValue())                                                       
                                                 .withEventDetail(new EventDetailCCe.Builder()                                                                        
                                                                        .withCorrection("Correção teste de Carta de Correção")
                                                                        .build())
                                                 .build())
                            .build(signer));
                
        return new EventDispatch.Builder()
                     .withBatchId("1")
                     .withEvents(eventList)                                                                 
                     .build();
        //@formatter:on
    }

    public EventDispatch buildRecipientAwarenessManifEventDispatch(DefaultSigner signer) throws Exception {
        return this.buildRecipientManifestationEventDispatch(signer, EventType.CIENCIA_OPERACAO, new EventDetailRecipientManifestation.Builder().withEventType(EventType.CIENCIA_OPERACAO).build());
    }

    public EventDispatch buildRecipientConfirmationManifEventDispatch(DefaultSigner signer) throws Exception {
        return this.buildRecipientManifestationEventDispatch(signer, EventType.CONFIRMACAO_OPERACAO,
                new EventDetailRecipientManifestation.Builder().withEventType(EventType.CONFIRMACAO_OPERACAO).build());
    }

    public EventDispatch buildRecipientUnawarenessManifEventDispatch(DefaultSigner signer) throws Exception {
        return this.buildRecipientManifestationEventDispatch(signer, EventType.DESCONHECIMENTO_OPERACAO,
                new EventDetailRecipientManifestation.Builder().withEventType(EventType.DESCONHECIMENTO_OPERACAO).build());
    }

    public EventDispatch buildRecipientDenialManifEventDispatch(DefaultSigner signer) throws Exception {
        return this.buildRecipientManifestationEventDispatch(signer, EventType.OPERACAO_NAO_REALIZADA,
                new EventDetailRecipientManifestation.Builder().withEventType(EventType.OPERACAO_NAO_REALIZADA).withJustification("I deny it.............").build());
    }

    private EventDispatch buildRecipientManifestationEventDispatch(DefaultSigner signer, EventType eventType, EventDetail detail) throws Exception {
        final ArrayList<Event> eventList = new ArrayList<>();
        // 04229224000120
        //@formatter:off        
        eventList.add(new Event.Builder()
                            .withEventInfo(new EventInfo.Builder()                                                 
                                                 .withIbgeOrgan(IBGEOrgan.PR)
                                                 .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                 .withAuthorCnpj("14241297000191")
                                                 .withAcessKey("41180104229224000120553330000001111575286021")
                                                 .withEventDateTime("2018-01-01T09:56:43-03:00")
                                                 .withEventType(eventType)
                                                 .withEventSeqNumber("1")
                                                 .withEventVersion(FiscalDocumentVersion.VERSION_1_00.getValue())                                                       
                                                 .withEventDetail(detail)
                                                 .build())
                            .build(signer));
                
        return new EventDispatch.Builder()
                     .withBatchId("1")
                     .withEvents(eventList)                                                                 
                     .build();
        //@formatter:on
    }
}

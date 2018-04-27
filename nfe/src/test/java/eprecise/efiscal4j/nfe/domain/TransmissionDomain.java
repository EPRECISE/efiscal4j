
package eprecise.efiscal4j.nfe.domain;

import java.util.ArrayList;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.v310.sharing.BatchReceiptSearch;
import eprecise.efiscal4j.nfe.v310.sharing.BatchReceiptSearchResponse;
import eprecise.efiscal4j.nfe.v310.sharing.CancellationRequestResponse;
import eprecise.efiscal4j.nfe.v310.sharing.CancellationRequestResponseInfo;
import eprecise.efiscal4j.nfe.v310.sharing.Event;
import eprecise.efiscal4j.nfe.v310.sharing.EventDetailCancellation;
import eprecise.efiscal4j.nfe.v310.sharing.EventInfo;
import eprecise.efiscal4j.nfe.v310.sharing.EventProtocol;
import eprecise.efiscal4j.nfe.v310.sharing.EventResponse;
import eprecise.efiscal4j.nfe.v310.sharing.EventResponseInfo;
import eprecise.efiscal4j.nfe.v310.sharing.EventType;
import eprecise.efiscal4j.nfe.v310.sharing.NFeStatusSearch;
import eprecise.efiscal4j.nfe.v310.sharing.NFeStatusSearchResponse;
import eprecise.efiscal4j.nfe.v310.sharing.ProcessingStatusProtocol;
import eprecise.efiscal4j.nfe.v310.sharing.ProcessingStatusProtocolInfo;
import eprecise.efiscal4j.nfe.v310.sharing.ServiceStatusSearch;
import eprecise.efiscal4j.nfe.v310.sharing.ServiceStatusSearchResponse;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


class TransmissionDomain {

    private static TransmissionDomain internalEntity;

    private TransmissionDomain() {
    }

    public static TransmissionDomain getInstance() {
        if (TransmissionDomain.internalEntity == null) {
            TransmissionDomain.internalEntity = new TransmissionDomain();
        }
        return TransmissionDomain.internalEntity;
    }

    public ArrayList<EventProtocol> buildEventProtocolList(DefaultSigner signer) throws Exception {
        final ArrayList<EventProtocol> eventProtocolList = new ArrayList<>();
        //@formatter:off       
        eventProtocolList.add(new EventProtocol.Builder()
                                    .withEvent(new Event.Builder()
                                                     .withEventInfo(new EventInfo.Builder()                                                                          
                                                                          .withIbgeOrgan(IBGEOrgan.AMB_NAC_90)
                                                                          .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                                          .withAuthorCpf("33462170279")
                                                                          .withAcessKey("43060659104422005704550990000070080007055470")
                                                                          .withEventDateTime("2013-02-06T14:51:15-02:00")
                                                                          .withEventType(EventType.OPERACAO_NAO_REALIZADA)
                                                                          .withEventSeqNumber("1")
                                                                          .withEventVersion("1.00")
                                                                          .withEventDetail(new EventDetailCancellation.Builder()                                                                                                 
                                                                                                 .withProtocolNumber("135120005426259")
                                                                                                 .withJustification("Teste de Cancelamento como Evento")
                                                                                                 .build())
                                                                          .build())
                                                     .build(signer))
                                    .withEventResponse(new EventResponse.Builder()
                                                             .withEventResponseInfo(new EventResponseInfo.Builder()
                                                                                          .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                                                          .withApplicationVersion("RS20100311145427")
                                                                                          .withIbgeOrgan(IBGEOrgan.AMB_NAC_90)
                                                                                          .withStatusCode("100")
                                                                                          .withStatusDescription("Autorizado o uso de NF-e")
                                                                                          .withAcessKey("43060659104422005704550990000070080007055470")
                                                                                          .withEventType(EventType.CIENCIA_OPERACAO)
                                                                                          .withEventDescription(EventType.CIENCIA_OPERACAO.getFullDescription())
                                                                                          .withEventRegisterDateTime("2013-02-06T14:51:15-02:00")                                                                                          
                                                                                          .build())
                                                             .build())                         
                                    .build());
        //@formatter:on                
        return eventProtocolList;
    }

    public ProcessingStatusProtocol buildProcessingStatusProtocol() throws Exception {
        //@formatter:off        
        return new ProcessingStatusProtocol.Builder()
                     .withProcessingStatusProtocolInfo(new ProcessingStatusProtocolInfo.Builder()
                                                             .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                             .withApplicationVersion("RS20100311145427")
                                                             .withAcessKey("43060659104422005704550990000070080007055470")
                                                             .withProcessingDateTime("2013-02-06T14:51:15-02:00")
                                                             .withProtocolNumber("243150000000001")
                                                             .withStatusCode("100")
                                                             .withStatusDescription("Autorizado o uso da NF-e")
                                                             .build())
                     .build();
        //@formatter:on        
    }

    public CancellationRequestResponse buildCancellationRequestResponse() throws Exception {
        //@formatter:off       
        return new CancellationRequestResponse.Builder()
                     .withCancellationRequestResponseInfo(new CancellationRequestResponseInfo.Builder()
                                                              .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                              .withApplicationVersion("RS20100311145427")
                                                              .withStatusCode("101")
                                                              .withStatusDescription("Cancelamento homologado com sucesso")
                                                              .withServiceUf(UF.RS)                                                                                                                           
                                                              .build())
                     .build();
        //@formatter:on                
    }

    public NFeStatusSearch buildNFeStatusSearch() throws Exception {
        //@formatter:off
        return new NFeStatusSearch.Builder()
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                     .withAcessKey("41160314241297000191550070000000211142463507")                                                                                      
                     .build();
        //@formatter:on
    }

    public NFeStatusSearchResponse buildNFeStatusSearchResponse(DefaultSigner signer) throws Exception {
        //@formatter:off
        return new NFeStatusSearchResponse.Builder()
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                     .withApplicationVersion("RS20100311145427")
                     .withStatusCode("217")
                     .withStatusDescription("Rejeicao: NF-e nao consta na base de dados da SEFAZ")
                     .withServiceUf(UF.RS)
                     .withReceptionDateTime("2013-02-06T14:51:09-02:00")
                     .withAcessKey("43060659104422005704550990000070080007055470")     
                     .withProcessingStatusProtocol(this.buildProcessingStatusProtocol())
                     .withCancellationRequestResponse(this.buildCancellationRequestResponse())
                     .withEventProtocols(this.buildEventProtocolList(signer))
                     .build();
        //@formatter:on
    }

    public BatchReceiptSearch buildBatchReceiptSearch() throws Exception {
        //@formatter:off
        return new BatchReceiptSearch.Builder()
                     
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)                           
                     .withReceiptNumber("411110212500268")
//                     .withReceiptNumber("411110212500418")                                          
                     .build();
        //@formatter:on
    }

    public BatchReceiptSearchResponse buildBatchReceiptSearchResponse() throws Exception {
        final ArrayList<ProcessingStatusProtocol> statusProtocolList = new ArrayList<>();
        //@formatter:off
        statusProtocolList.add(new ProcessingStatusProtocol.Builder()
                                     .withProcessingStatusProtocolInfo(new ProcessingStatusProtocolInfo.Builder()
                                                                   .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                                   .withApplicationVersion("RS20110816085649")
                                                                   .withAcessKey("43091299999090910199551140912140011007055475")
                                                                   .withProcessingDateTime("2013-02-06T14:51:09-02:00")                                                                   
                                                                   .withDigestValue("t9CNzgSoHYd0DyIN+N0ZAz1vN6Y=")
                                                                   .withStatusCode("233")
                                                                   .withStatusDescription("Rejeicao: IE do destinatario nao cadastrada")
                                                                   .withId("ID160820111004039060")
                                                                   .build())
                                     .build());
        
        statusProtocolList.add(new ProcessingStatusProtocol.Builder()
                                     .withProcessingStatusProtocolInfo(new ProcessingStatusProtocolInfo.Builder()
                                                                   .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                                   .withApplicationVersion("RS20110816085649")
                                                                   .withAcessKey("43110899999090910199550110118160951007055470")
                                                                   .withProcessingDateTime("2013-02-06T14:51:19-02:00")
                                                                   .withProtocolNumber("143110000000289")
                                                                   .withDigestValue("c1wZvqlmu38VP0WzYtbannOjCC0=")
                                                                   .withStatusCode("100")
                                                                   .withStatusDescription("Autorizado o uso da NF-e")
                                                                   .withId("ID143110000000289")
                                                                   .build())
        .build());        
        
        return new BatchReceiptSearchResponse.Builder()
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                     .withApplicationVersion("RS20110816085649")
                     .withReceiptNumber("431000001271650")
                     .withStatusCode("104")
                     .withStatusDescription("Lote processado")
                     .withServiceUf(UF.RS)
                     .withReceptionDateTime("2013-02-06T14:51:09-02:00")
                     .withProcessingStatusProtocols(statusProtocolList)                                                                  
                     .build();
        //@formatter:on
    }

    public ServiceStatusSearch buildServiceStatusSearch() throws Exception {
        //@formatter:off
        return new ServiceStatusSearch.Builder()
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)                           
                     .withServiceUf(UF.PR)                                                                  
                     .build();
        //@formatter:on
    }

    public ServiceStatusSearchResponse buildServiceStatusSearchResponse() throws Exception {
        //@formatter:off
        return new ServiceStatusSearchResponse.Builder()
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                     .withApplicationVersion("RS20100311142157")
                     .withStatusCode("107")
                     .withStatusDescription("Servico em Operacao")
                     .withServiceUf(UF.PR)                      
                     .withReceptionDateTime("2010-03-11T14:48:06-02:00")
                     .withAverageTime("1")
                     .build();
        //@formatter:on
    }

}


package eprecise.efiscal4j.nfe.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import eprecise.efiscal4j.commons.domain.CFOP;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.NFeTestParams;
import eprecise.efiscal4j.nfe.PresenceIndicator;
import eprecise.efiscal4j.nfe.broker.WithoutBrokerOperation;
import eprecise.efiscal4j.nfe.emitter.CRT;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddress;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddressCity;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterDocuments;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterLegalEntityDocuments;
import eprecise.efiscal4j.nfe.item.DefaultUnity;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.item.tax.ItemTax;
import eprecise.efiscal4j.nfe.item.tax.TaxStructure;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS07;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN103;
import eprecise.efiscal4j.nfe.item.tax.icms.ProductOrigin;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS07;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.payment.PaymentDetail;
import eprecise.efiscal4j.nfe.payment.PaymentMethod;
import eprecise.efiscal4j.nfe.receiver.Receiver;
import eprecise.efiscal4j.nfe.receiver.address.BrazillianReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ReceiverAddressCity;
import eprecise.efiscal4j.nfe.receiver.documents.ReceiverDocuments;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCpf;
import eprecise.efiscal4j.nfe.receiver.documents.ie.NonTaxpayerReceiverIE;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.serie.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.transport.ShippingModality;
import eprecise.efiscal4j.nfe.transport.Transport;


public class NFeDomainTest {

    private static final Integer serie = 100;

    private static final Integer number = 1;

    public NFe buildNFe() {
        return this.getDefaultNFeBuilder().build();
    }

    public NFe.NFeBuilder getDefaultNFeBuilder() {
        // @formatter:off
            return NFe.builder()
                .technicalManager(this.buildTechnicalManager())
                .receiver(this.buildReceiver())
                .brokerIndicator(new WithoutBrokerOperation())
                .presenceIndicator(PresenceIndicator.PRESENCIAL_OPERATION)
                .endConsumer(true)
                .operationDescription("VENDA")
                .serie(this.buildSerie())
                .number(NFeDomainTest.number)
                .transport(Transport.builder().shippingModality(ShippingModality.WITHOUT_TRANSPORT).build())
                .emitter(this.buildEmitter())
                .items(this.buildItems())
                .payment(this.buildPayment())
                .details("NFE EMITIDA COMO TESTE - SEM VALOR FISCAL");
        // @formatter:on
    }

    private Payment buildPayment() {
        return Payment.builder()
                .details(Arrays.asList(PaymentDetail.builder().method(PaymentMethod.DINHEIRO).value(new BigDecimal("0.01")).build()))
                .build();
    }

    private List<Item> buildItems() {
        return Arrays.asList(
                Item.builder()
                    .code("12345")
                    .name("NFE EMITIDA COMO TESTE - SEM VALOR FISCAL")
                    .globalTradeItemNumber("")
                    .unity(DefaultUnity.UNIDADE)
                    .quantity(BigDecimal.ONE)
                    .unitaryValue(new BigDecimal("0.01"))
                    .taxStructure(TaxStructure.builder()
                            .ncm("85185000")
                            .cfop(CFOP.CFOP_5102)
                            .taxes(this.buildItemTaxes())
                            .build())
                    .build()
        );
    }

    private Collection<ItemTax> buildItemTaxes() {
        // @formatter:off
           final ICMS icms = ICMSSN103.builder().origin(ProductOrigin.NATIONAL).build();
           final PIS pis = PIS07.builder().build();
           final COFINS cofins = COFINS07.builder().build();
           return Stream.of(icms, pis, cofins).collect(Collectors.toSet());
        // @formatter:on
    }

    private Emitter buildEmitter() {
        return Emitter.builder().documents(this.buildEmitterDocuments()).crt(CRT.SIMPLE_NATIONAL).address(this.buildEmitterAddress()).build();
    }

    private EmitterAddress buildEmitterAddress() {
    // @formatter:off
      return EmitterAddress.builder()
              .cep("40020070")
              .street("Rua Ruy Barbosa")
              .district("Centro")
              .city(EmitterAddressCity.builder().ibgeCode("2927408").description("Salvador").uf(UF.BA).build()).number("100").build();
    // @formatter:on
    }

    private EmitterDocuments buildEmitterDocuments() {
    // @formatter:off
       return EmitterLegalEntityDocuments.builder()
              .name("Nome emitente teste")
              .ie(NFeTestParams.getEmitterIe().orElse(""))
              .cnpj(NFeTestParams.getEmitterCnpj().orElse("14241297000191"))
              .fancyName("Nome emitente teste")
              .build();
    // @formatter:on
    }

    private eprecise.efiscal4j.nfe.technicalManager.TechnicalManager buildTechnicalManager() {
        return eprecise.efiscal4j.nfe.technicalManager.TechnicalManager.builder().contactName("Fernando").cnpj("14241297000191").email("contato@e-precise.com.br").phone("1146376515").build();
    }

    private Receiver buildReceiver() {
        return Receiver.builder().documents(this.buildReceiverDocuments()).address(this.buildReceiverAddress()).build();
    }

    private ReceiverDocuments buildReceiverDocuments() {
        return ReceiverDocuments.builder().cnp(ReceiverCpf.builder().cpf("06642962974").build()).name("Nome do destinatário").ie(NonTaxpayerReceiverIE.builder().build()).build();
    }

    private ReceiverAddress buildReceiverAddress() {
        return BrazillianReceiverAddress.builder().cep("40020070").street("Rua Ruy Barbosa").district("Centro")
                .city(ReceiverAddressCity.builder().ibgeCode("2927408").description("Salvador").uf(UF.BA).build()).number("100").build();
    }

    private FiscalDocumentSerie buildSerie() {
        return FiscalDocumentSerie.builder().number(NFeDomainTest.serie).environment(TransmissionEnvironment.HOMOLOGATION).build();
    }

}

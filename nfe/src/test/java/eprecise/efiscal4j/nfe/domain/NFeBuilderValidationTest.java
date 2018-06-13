
package eprecise.efiscal4j.nfe.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

import eprecise.efiscal4j.commons.domain.CFOP;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.utils.ValidationBuilder.ValidationResult;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.charging.Charging;
import eprecise.efiscal4j.nfe.charging.Duplicate;
import eprecise.efiscal4j.nfe.charging.Invoice;
import eprecise.efiscal4j.nfe.emissionDate.CustomEmissionDate;
import eprecise.efiscal4j.nfe.emitter.CRT;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddress;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddressCity;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterLegalEntityDocuments;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.payment.PaymentDetail;
import eprecise.efiscal4j.nfe.payment.PaymentMethod;
import eprecise.efiscal4j.nfe.payment.cardSet.CardFlag;
import eprecise.efiscal4j.nfe.payment.cardSet.CardSet;
import eprecise.efiscal4j.nfe.payment.cardSet.CardSetIntegration;
import eprecise.efiscal4j.nfe.receiver.Receiver;
import eprecise.efiscal4j.nfe.receiver.address.ReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ReceiverAddressCity;
import eprecise.efiscal4j.nfe.receiver.documents.ReceiverDocuments;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCnpj;
import eprecise.efiscal4j.nfe.receiver.documents.ie.TaxpayerReceiverIE;
import eprecise.efiscal4j.nfe.references.ReferenceToNFeAccessKey;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.serie.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.transport.ShippingModality;
import eprecise.efiscal4j.nfe.transport.Transport;
import eprecise.efiscal4j.nfe.transport.TransportICMSRetention;
import eprecise.efiscal4j.nfe.transport.TransportedVolume;
import eprecise.efiscal4j.nfe.transport.Vehicle;
import eprecise.efiscal4j.nfe.transport.conveyor.Conveyor;
import eprecise.efiscal4j.nfe.transport.conveyor.cnp.ConveyorCnpj;
import eprecise.efiscal4j.nfe.transport.mean.VehicleTowingTransportMean;


/**
 * Teste de geração e validação de domínio de NF-e
 * 
 * @author Fernando Glizt
 * 
 */
public class NFeBuilderValidationTest {

    @Test
    public void validateNFeBuilder() {
        final NFe nfe = buildNFe();
        final ValidationResult<NFe> validate = ValidationBuilder.from(nfe).validate();
        validate.getViolations().forEach(cv -> System.out.println(cv.getMessage()));
        validate.throwIfViolate();
        System.out.println(nfe.toString());
    }

    private NFe buildNFe() {
     // @formatter:off
        return NFe.builder()
                .serie(FiscalDocumentSerie.builder()
                        .environment(TransmissionEnvironment.HOMOLOGATION)
                        .number(1)
                        .build())
                .number(1)
                .emission(CustomEmissionDate.builder()
                        .custom(new Date())
                        .build())
                .emitter(Emitter.builder()
                        .documents(EmitterLegalEntityDocuments.builder()
                                .cnpj("14241297000191")
                                .name("E-PRECISE SOLUÇÕES E CONSULTORIA EM WEB LTDA - ME")
                                .fancyName("E-PRECISE")
                                .ie("ISENTO")
                                .build())
                        .crt(CRT.SIMPLE_NATIONAL)
                        .address(EmitterAddress.builder()
                                .cep("84010000")
                                .street("Rua xyz")
                                .district("Centro")
                                .number("001")
                                .city(EmitterAddressCity.builder()
                                        .ibgeCode("1234567")
                                        .description("Cidade")
                                        .uf(UF.PR)
                                        .build())
                                .build())
                        .build())
                .receiver(Receiver.builder()
                        .documents(ReceiverDocuments.builder()
                                .name("Nome distinatário")
                                .cnp(ReceiverCnpj.builder()
                                        .cnpj("66339059000117")
                                        .build())
                                .ie(TaxpayerReceiverIE.builder()
                                        .ie("12345678")
                                        .build())
                                .build())
                        .address(ReceiverAddress.builder()
                                .cep("84010000")
                                .district("Centro")
                                .number("1000")
                                .city(ReceiverAddressCity.builder()
                                        .description("Curitiba")
                                        .ibgeCode("1234567")
                                        .uf(UF.PR)
                                        .build())
                                .street("Rua abc")
                                .build())
                        .build())
                .operationDescription("Venda")
                .endConsumer(true)
                .charging(Charging.builder()
                        .invoice(Invoice.builder()
                                .number("123")
                                .originalValue(new BigDecimal("10.00"))
                                .discountValue(BigDecimal.ZERO)
                                .build())
                        .duplicates(Arrays.asList(Duplicate.builder()
                                .number("123")
                                .due(new Date())
                                .value(new BigDecimal("10.00"))
                                .build()))
                        .build())
                .payment(Payment.builder()
                        .details(Arrays.asList(PaymentDetail.builder()
                                .method(PaymentMethod.CARTAO_CREDITO)
                                .value(new BigDecimal("10.00"))
                                .cardSet(CardSet.builder()
                                        .integration(CardSetIntegration.NON_INTEGRATED)
                                        .cnpj("14241297000191")
                                        .cardFlag(CardFlag.MASTERCARD)
                                        .authorizationNumber("123456789")
                                        .build())
                                .build()))
                        .build())
                .transport(Transport.builder()
                        .shippingModality(ShippingModality.POR_CONTA_TERCEIROS)
                        .conveyor(Conveyor.builder()
                                .cnp(ConveyorCnpj.builder()
                                        .cnpj("14241297000191")
                                        .build())
                                .name("Transportadora teste")
                                .ie("ISENTO")
                                .fullAddress("Rua xyz, teste")
                                .cityName("Nome Cidade")
                                .uf(UF.PR)
                                .build())
                        .transportMean(VehicleTowingTransportMean.builder()
                                .vehicle(Vehicle.builder()
                                        .uf(UF.PR)
                                        .licensePlate("AAA0000")
                                        .build())
                                .towing(Arrays.asList(Vehicle.builder()
                                        .uf(UF.PR)
                                        .licensePlate("AAA0001")
                                        .rntc("teste rntc")
                                        .build()))
                                .build())
                        .icmsRetention(TransportICMSRetention.builder()
                                .serviceValue(new BigDecimal("10"))
                                .retentionCalculationBasis(new BigDecimal("10"))
                                .retentionAliquot(new BigDecimal("2"))
                                .genFactIbgeCode("1234567")
                                .cfop(CFOP.CFOP_5351)
                                .build())
                        .volumes(Arrays.asList(TransportedVolume.builder()
                                .volumeQuantity(999999999999l)
                                .build()))
                        .build())
                .details("Teste")
                .documentReferences(Arrays.asList(ReferenceToNFeAccessKey.builder().
                        accessKey("41180627679694000191550010000005141480062829")
                        .build()))
                .build();
     // @formatter:on
    }

}

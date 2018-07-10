
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.bouncycastle.asn1.x509.qualified.MonetaryValue;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocumentType;
import eprecise.efiscal4j.nfe.NFCe;
import eprecise.efiscal4j.nfe.NFeFinality;
import eprecise.efiscal4j.nfe.consumer.Consumer;
import eprecise.efiscal4j.nfe.receiver.Receiver;
import eprecise.efiscal4j.nfe.receiver.address.BrazillianReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ForeignReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ReceiverAddress;
import eprecise.efiscal4j.nfe.v400.DANFEPrintFormat;
import eprecise.efiscal4j.nfe.v400.DestinationOperationIdentifier;
import eprecise.efiscal4j.nfe.v400.FinalCustomerOperation;
import eprecise.efiscal4j.nfe.v400.NFe;
import eprecise.efiscal4j.nfe.v400.NFeIdentification;
import eprecise.efiscal4j.nfe.v400.NFeInfo;
import eprecise.efiscal4j.nfe.v400.NFeTransmissionProcess;
import eprecise.efiscal4j.nfe.v400.PurchaserPresenceIndicator;
import eprecise.efiscal4j.nfe.v400.additionalinfo.AdditionalInfo;
import eprecise.efiscal4j.nfe.v400.additionalinfo.CustomizedObservation;
import eprecise.efiscal4j.nfe.v400.nfce.CSC;
import eprecise.efiscal4j.nfe.v400.payment.CardSet;
import eprecise.efiscal4j.nfe.v400.payment.NFePayment;
import eprecise.efiscal4j.nfe.v400.payment.PaymentIntegrationType;
import eprecise.efiscal4j.nfe.v400.payment.PaymentMethod;
import eprecise.efiscal4j.nfe.v400.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.version.NFeDispatchAdapterVersion;


public class DispatchFromFiscalDocumentAdapter implements NFeDispatchAdapterVersion{

    private static final String APP_VERSION = "2.0.0";

    private static final DateFormat NFE_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private final FiscalDocument fiscalDocument;

    public DispatchFromFiscalDocumentAdapter(FiscalDocument fiscalDocument) {
        this.fiscalDocument = fiscalDocument;
    }

    public NFeDispatch buildNFeDispatch() {
     // @formatter:off
        try {
            return new NFeDispatch.Builder()
                    .withBatchId(null)
                    .withNFes(this.buildNFes())
                    .withSynchronousProcessing(null)
                    .build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
     // @formatter:on
    }

    private List<NFe> buildNFes() throws Exception {
     // @formatter:off
        if(this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe) {
            return Arrays.asList(this.buildNFe());
        } else if(this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFCe) {
            return Arrays.asList(this.buildNFCe());
        }
        return null;//TODO avaliar retorno padrão
     // @formatter:on
    }

    private NFe buildNFe() throws Exception {
     // @formatter:off
        return new NFe.Builder()
                .withCSC(null)
                .withNFeInfo(this.buildNFeInfo())
                .withNFeSuplementaryInfo(null)
                .build(null);
     // @formatter:on
    }

    private NFe buildNFCe() throws Exception {
     // @formatter:off
        return null;
     // @formatter:on
    }

    private CSC buildCSC() { // TODO CSC
        return new CSC(this.fiscalDocument.getSerie().getEnvironment().getDescription(), String.valueOf(this.fiscalDocument.getSerie().getEnvironment().getValue()));
    }

    private NFeInfo buildNFeInfo() {
     //@formatter:off
        new NFeInfo.Builder()
                  .withNFeIdentification(this.buildNFeIdentification())
                  .withEmitter(this.getParsedEmitter())
                  .withReceiver(this.getParsedReceiver())
                  .withNFeDetail(this.nfe.getItens().stream().map(new DetailParser()::parse).collect(Collectors.toList()))
                  .withNFeTotal(this.getParsedTotal(this.nfe.getTotal()))
                  .withNFeTransport(this.getParsedTransport(this.nfe.getTransport()))
                  .withNFePayments(this.nfe.getPayments()
                                           .stream()
                                           .map(p->{
                                               final NFePayment.Builder paymentBuilder = new NFePayment.Builder()
                                                                                       .withPaymentMethod(p.getMethod())
                                                                                       .withPaymentValue(Optional.ofNullable(p.getValue()).map(MonetaryValue::get).map(this::formatNFeDecimal1302).orElse(null));
                                               if(PaymentMethod.CARTAO_CREDITO.equals(p.getMethod()) || PaymentMethod.CARTAO_DEBITO.equals(p.getMethod())){
                                                   paymentBuilder.withCardSet(new CardSet.Builder().withPaymentIntegrationType(PaymentIntegrationType.NAO_INTEGRADO).build());
                                               }
                                               return paymentBuilder.build();
                                           })
                                           .collect(Collectors.toList()))
                  .build();
        if(!this.nfe.getSerie().getModel().equals(FiscalDocumentSeriesModel.NFCE)){
            builder.withNFeCharging(this.getNFeCharging(this.nfe.getCharging()));
        }

        final AdditionalInfo.Builder additionalInfoBuilder = new AdditionalInfo.Builder();
        if(this.nfe.getTotal().getIcmsTotal().getTaxTotalValue() != null){
            additionalInfoBuilder.withTaxpayerObservations(Arrays.asList(new CustomizedObservation.Builder().withField("LEI DA TRANSPARENCIA").withText("Valor aproximado dos tributos: "+Optional.ofNullable(this.nfe.getTotal().getIcmsTotal().getTaxTotalValue().getTotal()).map(Object::toString).orElse("")).build()));
        }
        Optional.ofNullable(this.nfe.getDetails()).filter(d->!d.isEmpty()).ifPresent(d->additionalInfoBuilder.withComplementaryInfo(this.formatNFeString(d,5000)));
        builder.withAdditionalInfo(additionalInfoBuilder.build());


        return builder.build();
        //@formatter:on
    }

    private NFeIdentification buildNFeIdentification() {
     //@formatter:off
        final NFeIdentification.Builder builder = new NFeIdentification.Builder();
        
        builder
                  .withApplicationVersion(DispatchFromFiscalDocumentAdapter.APP_VERSION)
                  .withDanfePrintFormat(this.buildDANFEPrintFormat())
                  .withDestinationOperationIdentifier(this.buildDestinationOperationIdentifier(this.buildReceiver()))
                  .withEmissionDateTime(DispatchFromFiscalDocumentAdapter.NFE_DATETIME_FORMAT.format(this.fiscalDocument.getEmission().getDate()))
                  .withFinalCustomerOperation(this.isEndConsumer() ? FinalCustomerOperation.CONSUMIDOR_FINAL : FinalCustomerOperation.NAO)
                  .withFiscalDocumentModel(Optional.ofNullable(this.fiscalDocument.getModel()).filter(FiscalDocumentSeriesModel::hasValue).map(fdsm -> FiscalDocumentModel.findByCode(fdsm.getValue())).orElse(null))
                  .withFiscalDocumentSeries(serie.getSeries())
                  .withFiscalDocumentNumber(String.valueOf(this.number))
                  .withFiscalDocumentType(Optional.ofNullable(this.nfe).filter(eprecise.sgv.server.fiscal.nfes.NFe.class::isInstance).map(eprecise.sgv.server.fiscal.nfes.NFe.class::cast).map(eprecise.sgv.server.fiscal.nfes.NFe::getFiscalDocumentType).orElse(FiscalDocumentType.SAIDA))
                  .withNFeCode(String.format("%08d", new Random().nextInt(100000000)))
                  .withNFeFinality(this.nfe instanceof NFCe ? NFeFinality.NORMAL: ((eprecise.sgv.server.fiscal.nfes.NFe)this.nfe).getFinality())
                  .withNFeTransmissionMethod(this.method)
                  .withNFeTransmissionProcess(NFeTransmissionProcess.APLICATIVO_CONTRIBUINTE)
                  .withOperationType(this.getOperationTypeDescriptor())
                  .withPurchaserPresenceIndicator(PurchaserPresenceIndicator.OPERACAO_PRESENCIAL) //TODO
                  .withTaxableEventCityIbgeCode(Optional.ofNullable(this.nfe.getEmitter().getFiscalPerson().getAddress()).filter(BrazilianAddress.class::isInstance).map(BrazilianAddress.class::cast).map(ba->ba.getZipCode().getCity().getIbgeCode().toString()).orElse("9999999"))
                  .withTransmissionEnvironment(serie.getTransmissionEnvironment())
                  .withUFIbgeCode(Optional.ofNullable(this.nfe.getEmitter().getFiscalPerson().getAddress()).filter(BrazilianAddress.class::isInstance).map(BrazilianAddress.class::cast).map(ba->UF.findByAcronym(ba.getZipCode().getCity().getUf().getAcronym())).orElse(UF.EX))
                  .withReferencedDocuments(this.getReferencedDocuments());
        
        Optional.ofNullable(this.nfe).filter(eprecise.sgv.server.fiscal.nfes.NFe.class::isInstance).map(eprecise.sgv.server.fiscal.nfes.NFe.class::cast).ifPresent(nf->{
            builder.withEntranceOrExitDateTime(Efiscal4JNFeAdapter.NFE_DATETIME_FORMAT.format(nf.getEntranceOrExit().getDate()));
        });
        return builder.build();
      //@formatter:on
    }

    private DANFEPrintFormat buildDANFEPrintFormat() {
     //@formatter:off
        if(this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFCe) {
            return DANFEPrintFormat.DANFE_NFCE;
        }
        
        return DANFEPrintFormat.DANFE_PAISAGEM;
     //@formatter:on
    }

    private DestinationOperationIdentifier buildDestinationOperationIdentifier(Receiver receiver) {
        if ((receiver == null) || ((receiver != null) && !this.isAddressValid(receiver.getAddress()))) {
            return DestinationOperationIdentifier.INTERNA;
        } else if (Optional.ofNullable(receiver.getAddress()).filter(BrazillianReceiverAddress.class::isInstance).map(BrazillianReceiverAddress.class::cast)
                .filter(bra -> bra.getCity().getUf().equals(this.fiscalDocument.getEmitter().getAddress().getCity().getUf())).isPresent()) {
            return DestinationOperationIdentifier.INTERNA;
        }
        if (receiver.getAddress() instanceof ForeignReceiverAddress) {
            return DestinationOperationIdentifier.EXTERIOR;
        } else {
            return DestinationOperationIdentifier.INTERESTADUAL;
        }
    }

    private boolean isAddressValid(ReceiverAddress address) {
        return address.isValid();
    }

    private Receiver buildReceiver() {
        if (this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe) {
            return ((eprecise.efiscal4j.nfe.NFe) this.fiscalDocument).getReceiver();
        } else if (this.fiscalDocument instanceof NFCe) {
            final Consumer consumer = ((NFCe) this.fiscalDocument).getConsumer();
            if (consumer instanceof Receiver) {
                return (Receiver) consumer;
            }
        }
        return null;
    }

    public boolean isEndConsumer() {
        if (this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe) {
            return ((eprecise.efiscal4j.nfe.NFe) this.fiscalDocument).getEndConsumer();
        } else if (this.fiscalDocument instanceof NFCe) {
            return true;
        }

        return false;
    }
}

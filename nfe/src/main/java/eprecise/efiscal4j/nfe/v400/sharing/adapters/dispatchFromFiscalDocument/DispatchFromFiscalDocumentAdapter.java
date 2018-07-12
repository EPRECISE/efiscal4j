
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocumentType;
import eprecise.efiscal4j.nfe.NFCe;
import eprecise.efiscal4j.nfe.consumer.Consumer;
import eprecise.efiscal4j.nfe.receiver.Receiver;
import eprecise.efiscal4j.nfe.receiver.address.BrazillianReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ForeignReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ReceiverAddress;
import eprecise.efiscal4j.nfe.references.DocumentReference;
import eprecise.efiscal4j.nfe.references.ReferenceToCTe;
import eprecise.efiscal4j.nfe.references.ReferenceToECF;
import eprecise.efiscal4j.nfe.references.ReferenceToECF.ReferencedECFModel;
import eprecise.efiscal4j.nfe.references.ReferenceToNF;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.NfpCnpj;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.NfpCpf;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.ProducerReferencedNFModel;
import eprecise.efiscal4j.nfe.references.ReferenceToNFe;
import eprecise.efiscal4j.nfe.serie.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v400.DANFEPrintFormat;
import eprecise.efiscal4j.nfe.v400.DestinationOperationIdentifier;
import eprecise.efiscal4j.nfe.v400.FinalCustomerOperation;
import eprecise.efiscal4j.nfe.v400.NFe;
import eprecise.efiscal4j.nfe.v400.NFeIdentification;
import eprecise.efiscal4j.nfe.v400.NFeInfo;
import eprecise.efiscal4j.nfe.v400.NFeTransmissionMethod;
import eprecise.efiscal4j.nfe.v400.NFeTransmissionProcess;
import eprecise.efiscal4j.nfe.v400.PurchaserPresenceIndicator;
import eprecise.efiscal4j.nfe.v400.nfce.CSC;
import eprecise.efiscal4j.nfe.v400.refdocuments.ProducerReferencedNF;
import eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedDocuments;
import eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedECF;
import eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedECF.ReferecedECFModel;
import eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedNF;
import eprecise.efiscal4j.nfe.v400.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.version.NFeDispatchAdapterVersion;


public class DispatchFromFiscalDocumentAdapter implements NFeDispatchAdapterVersion {

    private static final String APP_VERSION = "2.0.0";

    private static final DateFormat NFE_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private static final DateTimeFormatter NFE_YEAR_MONTH_FORMAT = DateTimeFormatter.ofPattern("yyMM");

    private static final String NFE_CODE_FORMAT = "%08d";

    private static final String IBGE_CODE_DEFAULT = "9999999";

    private static final String DEFAULT_OPERATION_TYPE = "VENDA DE MERCADORIAS";

    private final FiscalDocument fiscalDocument;

    public DispatchFromFiscalDocumentAdapter(FiscalDocument fiscalDocument) {
        this.fiscalDocument = fiscalDocument;
    }

    @Override
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
//        new NFeInfo.Builder()
//                  .withNFeIdentification(this.buildNFeIdentification())
//                  .withEmitter(this.getParsedEmitter())
//                  .withReceiver(this.getParsedReceiver())
//                  .withNFeDetail(this.nfe.getItens().stream().map(new DetailParser()::parse).collect(Collectors.toList()))
//                  .withNFeTotal(this.getParsedTotal(this.nfe.getTotal()))
//                  .withNFeTransport(this.getParsedTransport(this.nfe.getTransport()))
//                  .withNFePayments(this.nfe.getPayments()
//                                           .stream()
//                                           .map(p->{
//                                               final NFePayment.Builder paymentBuilder = new NFePayment.Builder()
//                                                                                       .withPaymentMethod(p.getMethod())
//                                                                                       .withPaymentValue(Optional.ofNullable(p.getValue()).map(MonetaryValue::get).map(this::formatNFeDecimal1302).orElse(null));
//                                               if(PaymentMethod.CARTAO_CREDITO.equals(p.getMethod()) || PaymentMethod.CARTAO_DEBITO.equals(p.getMethod())){
//                                                   paymentBuilder.withCardSet(new CardSet.Builder().withPaymentIntegrationType(PaymentIntegrationType.NAO_INTEGRADO).build());
//                                               }
//                                               return paymentBuilder.build();
//                                           })
//                                           .collect(Collectors.toList()))
//                  .build();
//        if(!this.nfe.getSerie().getModel().equals(FiscalDocumentSeriesModel.NFCE)){
//            builder.withNFeCharging(this.getNFeCharging(this.nfe.getCharging()));
//        }
//
//        final AdditionalInfo.Builder additionalInfoBuilder = new AdditionalInfo.Builder();
//        if(this.nfe.getTotal().getIcmsTotal().getTaxTotalValue() != null){
//            additionalInfoBuilder.withTaxpayerObservations(Arrays.asList(new CustomizedObservation.Builder().withField("LEI DA TRANSPARENCIA").withText("Valor aproximado dos tributos: "+Optional.ofNullable(this.nfe.getTotal().getIcmsTotal().getTaxTotalValue().getTotal()).map(Object::toString).orElse("")).build()));
//        }
//        Optional.ofNullable(this.nfe.getDetails()).filter(d->!d.isEmpty()).ifPresent(d->additionalInfoBuilder.withComplementaryInfo(this.formatNFeString(d,5000)));
//        builder.withAdditionalInfo(additionalInfoBuilder.build());


        return null;//builder.build();
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
            .withFiscalDocumentModel(this.fiscalDocument.getModel())
            .withFiscalDocumentSeries(this.fiscalDocument.getSerie().getNumber().toString())
            .withFiscalDocumentNumber(this.fiscalDocument.getNumber().toString())
            .withFiscalDocumentType(this.buildFiscalDocumentType())
            .withNFeCode(String.format(DispatchFromFiscalDocumentAdapter.NFE_CODE_FORMAT, new Random().nextInt(100000000)))
            .withNFeFinality(this.buildNFeFinality())
            .withNFeTransmissionMethod(NFeTransmissionMethod.NORMAL)
            .withNFeTransmissionProcess(NFeTransmissionProcess.APLICATIVO_CONTRIBUINTE)
            .withOperationType(this.buildOperationTypeDescriptor())
            .withPurchaserPresenceIndicator(PurchaserPresenceIndicator.OPERACAO_PRESENCIAL)
            .withTaxableEventCityIbgeCode(Optional.ofNullable(this.fiscalDocument.getEmitter().getAddress()).map(ba -> ba.getCity().getIbgeCode().toString()).orElse(DispatchFromFiscalDocumentAdapter.IBGE_CODE_DEFAULT)) //TODO Revisar
            .withTransmissionEnvironment(this.buildTransmissionEnvironment())
            .withUFIbgeCode(Optional.ofNullable(this.fiscalDocument.getEmitter().getAddress()).map(ba -> UF.findByAcronym(ba.getCity().getUf().getAcronym())).orElse(UF.EX)) //TODO Revisar
            .withReferencedDocuments(this.buildReferencedDocuments());
        
        Optional.ofNullable(this.fiscalDocument).filter(eprecise.efiscal4j.nfe.NFe.class::isInstance).map(eprecise.efiscal4j.nfe.NFe.class::cast).ifPresent(nf->{
            builder.withEntranceOrExitDateTime(DispatchFromFiscalDocumentAdapter.NFE_DATETIME_FORMAT.format(nf.getEntranceOrExit().getDate()));
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

    public eprecise.efiscal4j.nfe.v400.FiscalDocumentType buildFiscalDocumentType() {
        if (this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe && FiscalDocumentType.IN.equals(((eprecise.efiscal4j.nfe.NFe) this.fiscalDocument).getType())) {
            return eprecise.efiscal4j.nfe.v400.FiscalDocumentType.ENTRADA;
        }
        return eprecise.efiscal4j.nfe.v400.FiscalDocumentType.SAIDA;
    }

    public eprecise.efiscal4j.nfe.v400.NFeFinality buildNFeFinality() {
        if (this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe) {
            switch (((eprecise.efiscal4j.nfe.NFe) this.fiscalDocument).getFinality()) {
            case COMPLEMENTARY:
                return eprecise.efiscal4j.nfe.v400.NFeFinality.COMPLEMENTAR;
            case ADJUSTMENT:
                return eprecise.efiscal4j.nfe.v400.NFeFinality.AJUSTE;
            case RETURN:
                return eprecise.efiscal4j.nfe.v400.NFeFinality.DEVOLUCAO_RETORNO;
            default:
                return eprecise.efiscal4j.nfe.v400.NFeFinality.NORMAL;
            }
        }
        return eprecise.efiscal4j.nfe.v400.NFeFinality.NORMAL;
    }

    private String buildOperationTypeDescriptor() {
        if (this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe) {
            return ((eprecise.efiscal4j.nfe.NFe) this.fiscalDocument).getOperationDescription();
        }

        return DispatchFromFiscalDocumentAdapter.DEFAULT_OPERATION_TYPE;
    }

    public eprecise.efiscal4j.nfe.v400.TransmissionEnvironment buildTransmissionEnvironment() {
        if (TransmissionEnvironment.PRODUCTION.equals(this.fiscalDocument.getSerie().getEnvironment())) {
            return eprecise.efiscal4j.nfe.v400.TransmissionEnvironment.PRODUCAO;
        }
        return eprecise.efiscal4j.nfe.v400.TransmissionEnvironment.HOMOLOGACAO;
    }

    private List<ReferencedDocuments> buildReferencedDocuments() {
        if (this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe) {
            return ((eprecise.efiscal4j.nfe.NFe) this.fiscalDocument).getDocumentReferences().stream().map(this::toReferencedDocument).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private ReferencedDocuments toReferencedDocument(final DocumentReference ref) {
        final ReferencedDocuments.Builder builder = new ReferencedDocuments.Builder();
        if (ref instanceof ReferenceToNFe) {
            return builder.withReferencedNFeAccessKey(((ReferenceToNFe) ref).getAccessKey()).build();
        } else if (ref instanceof ReferenceToCTe) {
            return builder.withReferencedCTeAccessKey(((ReferenceToCTe) ref).getAccessKey()).build();
        } else if (ref instanceof ReferenceToNF) {
            final ReferenceToNF toNF = (ReferenceToNF) ref;
            //@formatter:off
            return builder.withReferencedNF(new ReferencedNF.Builder()
                                                            .withSeries(toNF.getSeries())
                                                            .withNumber(String.valueOf(toNF.getNumber()))
                                                            .withEmitterUf(UF.findByAcronym(toNF.getUf().getAcronym()))
                                                            .withEmitterCnpj(toNF.getCnpj())
                                                            .withEmissionDate(toNF.getMonth().format(DispatchFromFiscalDocumentAdapter.NFE_YEAR_MONTH_FORMAT))
                                                            .build()
                                           ).build();

            //@formatter:on
        } else if (ref instanceof ReferenceToNFP) {
            final ReferenceToNFP toNFP = (ReferenceToNFP) ref;
            //@formatter:off
             ProducerReferencedNF.Builder refNfpBuilder = new ProducerReferencedNF.Builder()
                                                            .withSeries(toNFP.getSeries())
                                                            .withNumber(String.valueOf(toNFP.getNumber()))
                                                            .withEmitterUf(UF.findByAcronym(toNFP.getUf().getAcronym()))
                                                            .withModel(this.buildProducerReferencedNFModel(toNFP.getModel()))
                                                            .withEmissionYearMonth(toNFP.getMonth().format(DispatchFromFiscalDocumentAdapter.NFE_YEAR_MONTH_FORMAT))
                                                            .withStateRegistration(toNFP.getIe());
            //@formatter:on
            if (toNFP.getCnp() instanceof NfpCpf) {
                refNfpBuilder = refNfpBuilder.withEmitterCpf(toNFP.getCnp().getCnp());
            } else if (toNFP.getCnp() instanceof NfpCnpj) {
                refNfpBuilder = refNfpBuilder.withEmitterCnpj(toNFP.getCnp().getCnp());
            }
            return builder.withProducerReferencedNF(refNfpBuilder.build()).build();
        } else if (ref instanceof ReferenceToECF) {
            final ReferenceToECF toECF = (ReferenceToECF) ref;
            //@formatter:off
            return builder.withReferencedECF(new ReferencedECF.Builder()
                                                              .withModel(this.buildReferecedECFModel(toECF.getModel()))
                                                              .withEcfNumber(toECF.getEcfNumber())
                                                              .withCooNumber(toECF.getCooNumber())
                                                              .build()
                                            ).build();
            //@formatter:on
        } else {
            throw new IllegalArgumentException(ref + " não é um tipo válido"); // TODO revisar
        }
    }

    public eprecise.efiscal4j.nfe.v400.refdocuments.ProducerReferencedNF.ProducerReferencedNFModel buildProducerReferencedNFModel(ProducerReferencedNFModel model) {
        if (ProducerReferencedNFModel.PRODUCER_NF.equals(model)) {
            return eprecise.efiscal4j.nfe.v400.refdocuments.ProducerReferencedNF.ProducerReferencedNFModel.PRODUCER_NF;
        }
        return eprecise.efiscal4j.nfe.v400.refdocuments.ProducerReferencedNF.ProducerReferencedNFModel.SPARE_NF;
    }

    public ReferecedECFModel buildReferecedECFModel(ReferencedECFModel model) {// TODO nome da classe errado
        if (ReferencedECFModel.NON_ECF.equals(model)) {
            return ReferecedECFModel.NAO_ECF;
        } else if (ReferencedECFModel.ECF_PDV.equals(model)) {
            return ReferecedECFModel.ECF_PDV;
        }
        return ReferecedECFModel.ECF;
    }
}

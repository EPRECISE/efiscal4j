
package eprecise.efiscal4j.nfe.v400.sharing.adapters.processedFiscalDocument;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocumentType;
import eprecise.efiscal4j.nfe.NFCe;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.NFeFinality;
import eprecise.efiscal4j.nfe.charging.Charging;
import eprecise.efiscal4j.nfe.charging.Duplicate;
import eprecise.efiscal4j.nfe.charging.Invoice;
import eprecise.efiscal4j.nfe.consumer.Consumer;
import eprecise.efiscal4j.nfe.consumer.SimpleConsumer;
import eprecise.efiscal4j.nfe.emissionDate.CustomEmissionDate;
import eprecise.efiscal4j.nfe.emissionDate.EmissionDate;
import eprecise.efiscal4j.nfe.emitter.CRT;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddress;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddressCity;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterDocuments;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterLegalEntityDocuments;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterMunicipalDocuments;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterNaturalPersonDocuments;
import eprecise.efiscal4j.nfe.entranceOrExitDate.CustomIODate;
import eprecise.efiscal4j.nfe.entranceOrExitDate.IODate;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.item.DefaultUnity;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.item.Item.ItemEan;
import eprecise.efiscal4j.nfe.item.Item.ItemQuantity;
import eprecise.efiscal4j.nfe.item.Item.ItemUnitaryValue;
import eprecise.efiscal4j.nfe.item.Item.ItemUnity;
import eprecise.efiscal4j.nfe.item.medications.Medications;
import eprecise.efiscal4j.nfe.item.tax.ItemTax;
import eprecise.efiscal4j.nfe.item.tax.TaxStructure;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS01;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS02;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS03;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS04;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS05;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS06;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS07;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS08;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS09;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS49;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS50;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS51;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS52;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS53;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS54;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS55;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS56;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS60;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS61;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS62;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS63;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS64;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS65;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS66;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS67;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS70;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS71;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS72;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS73;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS74;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS75;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS98;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINS99;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.CofinsAliquotPercentWithBc;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.CofinsAliquotValueWithQuantity;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquot;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquotPercent;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquotValue;
import eprecise.efiscal4j.nfe.item.tax.cofins.st.COFINSST;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS00;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS10;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS20;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS30;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS40;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS41;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS50;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS51;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS60;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS70;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS90;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSPart10;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSPart90;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN101;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN102;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN103;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN201;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN202;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN203;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN300;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN400;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN500;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN900;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMSST;
import eprecise.efiscal4j.nfe.item.tax.icms.ProductOrigin;
import eprecise.efiscal4j.nfe.item.tax.icms.deferral.IcmsDeferral;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesonerationReason;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpValue;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.sn.credit.CreditSnValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.retained.FcpStRetainedValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc.IcmsStBc;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc.IcmsStBcDeterminedPautaValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc.IcmsStBcMarginAddedValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc.IcmsStBcMaximumTabulatedOrSuggestedPrice;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc.IcmsStBcNegativeListValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc.IcmsStBcNeutralListValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc.IcmsStBcPositiveListValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.destination.IcmsStDestinationValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.retained.IcmsStRetainedValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.bc.IcmsBc;
import eprecise.efiscal4j.nfe.item.tax.icms.value.bc.IcmsBcDeterminedPautaValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.bc.IcmsBcMarginAddedValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.bc.IcmsBcMaximumTabulatedPrice;
import eprecise.efiscal4j.nfe.item.tax.icms.value.bc.IcmsBcOperationValue;
import eprecise.efiscal4j.nfe.item.tax.ii.II;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI00;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI01;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI02;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI03;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI04;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI05;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI49;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI50;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI51;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI52;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI53;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI54;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI55;
import eprecise.efiscal4j.nfe.item.tax.ipi.IPI99;
import eprecise.efiscal4j.nfe.item.tax.ipi.generalData.IPIGeneralData;
import eprecise.efiscal4j.nfe.item.tax.ipi.value.IpiValue;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS01;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS02;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS03;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS04;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS05;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS06;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS07;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS08;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS09;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS49;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS50;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS51;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS52;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS53;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS54;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS55;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS56;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS60;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS61;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS62;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS63;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS64;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS65;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS66;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS67;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS70;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS71;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS72;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS73;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS74;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS75;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS98;
import eprecise.efiscal4j.nfe.item.tax.pis.PIS99;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.PisAliquotPercentWithBc;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.PisAliquotValueWithQuantity;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValueWithAliquot;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValueWithAliquotPercent;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValueWithAliquotValue;
import eprecise.efiscal4j.nfe.item.tax.pis.st.PISST;
import eprecise.efiscal4j.nfe.item.tax.ufReceiverIcms.ICMSUFReceiver;
import eprecise.efiscal4j.nfe.item.tax.ufReceiverIcms.InterstateICMSUFAliquot;
import eprecise.efiscal4j.nfe.item.trace.Trace;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.payment.Payment.PaymentBuilder;
import eprecise.efiscal4j.nfe.payment.PaymentDetail;
import eprecise.efiscal4j.nfe.payment.PaymentMethod;
import eprecise.efiscal4j.nfe.payment.cardSet.CardFlag;
import eprecise.efiscal4j.nfe.payment.cardSet.CardSet;
import eprecise.efiscal4j.nfe.payment.cardSet.CardSetIntegration;
import eprecise.efiscal4j.nfe.receiver.Receiver;
import eprecise.efiscal4j.nfe.receiver.address.BrazillianReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ReceiverAddressCity;
import eprecise.efiscal4j.nfe.receiver.documents.ReceiverDocuments;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCnp;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCnpj;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCpf;
import eprecise.efiscal4j.nfe.receiver.documents.ie.FreeTaxpayerReceiverIE;
import eprecise.efiscal4j.nfe.receiver.documents.ie.NonTaxpayerReceiverIE;
import eprecise.efiscal4j.nfe.receiver.documents.ie.ReceiverIE;
import eprecise.efiscal4j.nfe.receiver.documents.ie.TaxpayerReceiverIE;
import eprecise.efiscal4j.nfe.references.DocumentReference;
import eprecise.efiscal4j.nfe.references.ReferenceToCTe;
import eprecise.efiscal4j.nfe.references.ReferenceToECF;
import eprecise.efiscal4j.nfe.references.ReferenceToECF.ReferencedECFModel;
import eprecise.efiscal4j.nfe.references.ReferenceToNF;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.NfpCnp;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.NfpCnpj;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.NfpCpf;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.ProducerReferencedNFModel;
import eprecise.efiscal4j.nfe.references.ReferenceToNFeAccessKey;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.serie.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.transport.ShippingModality;
import eprecise.efiscal4j.nfe.transport.Transport;
import eprecise.efiscal4j.nfe.transport.TransportICMSRetention;
import eprecise.efiscal4j.nfe.transport.TransportedVolume;
import eprecise.efiscal4j.nfe.transport.Vehicle;
import eprecise.efiscal4j.nfe.transport.VolumeSeal;
import eprecise.efiscal4j.nfe.transport.conveyor.Conveyor;
import eprecise.efiscal4j.nfe.transport.conveyor.cnp.ConveyorCnpj;
import eprecise.efiscal4j.nfe.transport.conveyor.cnp.ConveyorCpf;
import eprecise.efiscal4j.nfe.transport.mean.FerryTransportMean;
import eprecise.efiscal4j.nfe.transport.mean.TransportMean;
import eprecise.efiscal4j.nfe.transport.mean.VehicleTowingTransportMean;
import eprecise.efiscal4j.nfe.transport.mean.VehicleTowingTransportMean.VehicleTowingTransportMeanBuilder;
import eprecise.efiscal4j.nfe.transport.mean.WagonTransportMean;
import eprecise.efiscal4j.nfe.v400.NFeDetail;
import eprecise.efiscal4j.nfe.v400.person.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.v400.person.NaturalPersonDocuments;
import eprecise.efiscal4j.nfe.v400.sharing.ProcessedNFe;
import eprecise.efiscal4j.nfe.v400.sharing.ProcessingStatusProtocol;
import eprecise.efiscal4j.nfe.v400.tax.icms.BCModality;
import eprecise.efiscal4j.nfe.v400.tax.icms.BCModalityST;
import eprecise.efiscal4j.nfe.v400.transport.NFeTransport;
import eprecise.efiscal4j.nfe.v400.types.NFeDate;
import eprecise.efiscal4j.nfe.v400.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.version.FiscalDocumentSupportedVersion;
import eprecise.efiscal4j.nfe.version.ProcessedFiscalDocumentAdapterVersion;


public class ProcessedFiscalDocumentAdapter implements ProcessedFiscalDocumentAdapterVersion {

    private final ProcessedNFe processedNFe;

    public ProcessedFiscalDocumentAdapter(final ProcessedNFe processedNFe) {
        this.processedNFe = processedNFe;
    }

    public ProcessedFiscalDocumentAdapter(final eprecise.efiscal4j.nfe.v400.NFe nfe, final ProcessingStatusProtocol processingStatusProtocol) {
        this.processedNFe = new ProcessedNFe.Builder().withNfe(nfe).withProcessingStatusProtocol(processingStatusProtocol).build();
    }

    @Override
    public FiscalDocument.Processed buildProcessedFiscalDocument() {
     // @formatter:off
        try {
            return FiscalDocument.Processed.builder()
                .id(Optional.ofNullable(this.processedNFe.getProcessingStatusProtocol()).map(psp -> psp.getProcessingStatusProtocolInfo()).map(pspi -> pspi.getId()).orElse(null))
                .version(FiscalDocumentSupportedVersion.VERSION_4_00)
                .applicationVersion(Optional.ofNullable(this.processedNFe.getProcessingStatusProtocol()).map(psp -> psp.getProcessingStatusProtocolInfo()).map(pspi -> pspi.getApplicationVersion()).orElse(null))
                .accessKey(Optional.ofNullable(this.processedNFe.getProcessingStatusProtocol()).map(psp -> psp.getProcessingStatusProtocolInfo()).map(pspi -> pspi.getAcessKey()).orElse(null))
                .processing(Optional.ofNullable(this.processedNFe.getProcessingStatusProtocol()).map(psp -> psp.getProcessingStatusProtocolInfo()).map(pspi -> pspi.getProcessingDateTime()).map(t -> {
                    try {
                        return NFeDateTimeUTC.dateFormat.parse(t);
                    } catch (final ParseException e) {
                        throw new RuntimeException(e);
                    }
                }).orElse(null))
                .protocolNumber(Optional.ofNullable(this.processedNFe.getProcessingStatusProtocol()).map(psp -> psp.getProcessingStatusProtocolInfo()).map(pspi -> pspi.getProtocolNumber()).orElse(null))
                .digestValue(Optional.ofNullable(this.processedNFe.getProcessingStatusProtocol()).map(psp -> psp.getProcessingStatusProtocolInfo()).map(pspi -> pspi.getDigestValue()).orElse(null))
                .status(EventStatus.builder()
                		.statusCode(Optional.ofNullable(this.processedNFe.getProcessingStatusProtocol()).map(psp -> psp.getProcessingStatusProtocolInfo()).map(pspi -> pspi.getStatusCode()).orElse(null))
                        .statusDescription(Optional.ofNullable(this.processedNFe.getProcessingStatusProtocol()).map(psp -> psp.getProcessingStatusProtocolInfo()).map(pspi -> pspi.getStatusDescription()).orElse(null))
                		.build())
                .document(this.buildFiscalDocument())
                .processedVersion(this.processedNFe)
                .build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
     // @formatter:on
    }

    private FiscalDocument buildFiscalDocument() throws NumberFormatException, ParseException {
        if (this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getFiscalDocumentModel().equals(FiscalDocumentModel.NFE)) {
         // @formatter:off
            return NFe.builder()
                    .receiver(this.buildReceiver())
                    .entranceOrExit(this.buildEntranceOrExit())
                    .finality(Optional.ofNullable(this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getnFeFinality()).map(nfeFinality -> NFeFinality.findByCode(nfeFinality.getValue())).orElse(null))
                    .type(Optional.ofNullable(this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getFiscalDocumentType()).map(nfeFiscalDocumentType -> FiscalDocumentType.findByCode(nfeFiscalDocumentType.getType())).orElse(null))
                    .endConsumer(this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getFinalCustomerOperation().isFinal())
                    .operationDescription(this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getOperationType())
                    .documentReferences(this.buildDocumentReferences())
                    .serie(this.buildSerie())
                    .number(Integer.valueOf(this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getFiscalDocumentNumber()))
                    .emission(this.buildEmissionDate())
                    .emitter(this.buildEmitter())
                    .items(this.buildItems())
                    .charging(this.buildCharging())
                    .payment(this.buildPayment())
                    .transport(this.buildTransport())
                    .details(this.processedNFe.getNfe().getNFeInfo().getAdditionalInfo().getComplementaryInfo())
                    .build();
         // @formatter:on
        } else if (this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getFiscalDocumentModel().equals(FiscalDocumentModel.NFCE)) {
         // @formatter:off
            return NFCe.builder()
                    .consumer(this.buildConsumer())
                    .serie(this.buildSerie())
                    .number(Integer.valueOf(this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getFiscalDocumentNumber()))
                    .emission(this.buildEmissionDate())
                    .emitter(this.buildEmitter())
                    .items(this.buildItems())
                    .charging(this.buildCharging())
                    .payment(this.buildPayment())
                    .transport(this.buildTransport())
                    .details(this.processedNFe.getNfe().getNFeInfo().getAdditionalInfo().getComplementaryInfo())
                    .build();
         // @formatter:on
        }
        return null;
    }

    private Transport buildTransport() {
     // @formatter:off
        final NFeTransport nfeTransport = this.processedNFe.getNfe().getNFeInfo().getnFeTransport();
        return Transport.builder()
                .shippingModality(Optional.ofNullable(nfeTransport.getShippingModality()).map(nfeShippingModality -> ShippingModality.findByCode(nfeShippingModality.getValue())).orElse(null))
                .conveyor(this.buildConveyor())
                .icmsRetention(this.buildTransportIcmsRetention())
                .transportMean(this.buildTransportMean())
                .volumes(this.buildTransportVolumes())
                .build();
     // @formatter:on
    }

    private Collection<TransportedVolume> buildTransportVolumes() {
     // @formatter:off
        final Collection<eprecise.efiscal4j.nfe.v400.transport.TransportedVolume> nfeTransportVolumes = this.processedNFe.getNfe().getNFeInfo().getnFeTransport().getTransportedVolume();
        if((nfeTransportVolumes != null) && !nfeTransportVolumes.isEmpty()) {
            return nfeTransportVolumes.stream().map(nfeVolume -> TransportedVolume.builder()
                    .volumeQuantity(this.toLong(nfeVolume.getVolumeQuantity()))
                    .volumeSpecies(nfeVolume.getVolumeSpecies())
                    .volumeTrademark(nfeVolume.getVolumeTrademark())
                    .volumeNumbering(nfeVolume.getVolumeNumbering())
                    .netWeight(this.toBigDecimal(nfeVolume.getNetWeight()))
                    .grossWeight(this.toBigDecimal(nfeVolume.getGrossWeight()))
                    .seals((nfeVolume.getSeals() != null) && !nfeVolume.getSeals().isEmpty() ? nfeVolume.getSeals().stream().map(vs->VolumeSeal.builder()
                            .sealNumber(vs.getSealNumber())
                            .build()).collect(Collectors.toList()) : null)
                    .build()).collect(Collectors.toList());
        }
     // @formatter:on
        return null;
    }

    private TransportMean buildTransportMean() {
     // @formatter:off
        final NFeTransport nfeTransport = this.processedNFe.getNfe().getNFeInfo().getnFeTransport();
        final eprecise.efiscal4j.nfe.v400.transport.Vehicle nfeVehicle = this.processedNFe.getNfe().getNFeInfo().getnFeTransport().getVehicle();
        final List<eprecise.efiscal4j.nfe.v400.transport.Vehicle> nfeTowing = this.processedNFe.getNfe().getNFeInfo().getnFeTransport().getTowing();
        
        if((nfeVehicle != null) || ((nfeTowing != null) && !nfeTowing.isEmpty())) {
            final VehicleTowingTransportMeanBuilder builder = VehicleTowingTransportMean.builder();
            
            if(nfeVehicle != null) {
                builder.vehicle(Vehicle.builder()
                        .licensePlate(nfeVehicle.getLicensePlate())
                        .uf(nfeVehicle.getUf())
                        .rntc(nfeVehicle.getRntc())
                        .build());
            }
            
            if(((nfeTowing != null) && !nfeTowing.isEmpty())) {
                builder.towing(nfeTowing.stream().map(nfeTowingVehicle -> Vehicle.builder()
                        .licensePlate(nfeTowingVehicle.getLicensePlate())
                        .uf(nfeTowingVehicle.getUf())
                        .rntc(nfeTowingVehicle.getRntc())
                        .build()).collect(Collectors.toList()));
            }
            
            return builder.build();
        } else if(!StringUtils.isEmpty(nfeTransport.getWagon())) {
            return WagonTransportMean.builder().identifier(nfeTransport.getWagon()).build();
        } else if(!StringUtils.isEmpty(nfeTransport.getFerry())) {
            return FerryTransportMean.builder().identifier(nfeTransport.getFerry()).build();
        }
     // @formatter:on

        return null;
    }

    private TransportICMSRetention buildTransportIcmsRetention() {
        final eprecise.efiscal4j.nfe.v400.transport.TransportICMSRetention nfeTransportICMSRetention = this.processedNFe.getNfe().getNFeInfo().getnFeTransport().getTransportICMSRetention();
     // @formatter:off
        if(nfeTransportICMSRetention != null) {
            return TransportICMSRetention.builder()
                    .serviceValue(this.toBigDecimal(nfeTransportICMSRetention.getServiceValue()))
                    .retentionCalculationBasis(this.toBigDecimal(nfeTransportICMSRetention.getRetentionCalculationBasis()))
                    .retentionAliquot(this.toBigDecimal(nfeTransportICMSRetention.getRetentionAliquot()))
                    .retentionValue(this.toBigDecimal(nfeTransportICMSRetention.getRetentionValue()))
                    .cfop(nfeTransportICMSRetention.getCfop())
                    .genFactIbgeCode(nfeTransportICMSRetention.getGenFactIbgeCode())
                    .build();
        }
        
        return null;
     // @formatter:on
    }

    private Conveyor buildConveyor() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.transport.Conveyor nfeConveyor = this.processedNFe.getNfe().getNFeInfo().getnFeTransport().getConveyor();

        if(nfeConveyor != null) {
            return Conveyor.builder()
                    .cnp(nfeConveyor.getDocuments() instanceof LegalEntityDocuments 
                            ? ConveyorCnpj.builder().cnpj(nfeConveyor.getDocuments().getCnpjCpf()).build()
                            : nfeConveyor.getDocuments() instanceof NaturalPersonDocuments
                                ? ConveyorCpf.builder().cpf(nfeConveyor.getDocuments().getCnpjCpf()).build()
                                : null)
                    .ie(nfeConveyor.getDocuments() instanceof LegalEntityDocuments 
                            ? ((LegalEntityDocuments) nfeConveyor.getDocuments()).getStateRegistration()
                            : null)
                    .fullAddress(nfeConveyor.getFullAddress())
                    .cityName(Optional.ofNullable(nfeConveyor.getCity()).map(c->c.getDescription()).orElse(null))
                    .uf(Optional.ofNullable(nfeConveyor.getCity()).map(c->c.getUf()).orElse(null))
                    .build();
            }
        
        return null;
     // @formatter:on
    }

    private Payment buildPayment() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.payment.NFePayment nfePayment = this.processedNFe.getNfe().getNFeInfo().getnFePayment();
        if(nfePayment != null) {
            final PaymentBuilder builder = Payment.builder();
            if((nfePayment.getPaymentDetails() != null) && !nfePayment.getPaymentDetails().isEmpty()) {
                return builder
                        .details(nfePayment.getPaymentDetails().stream().map(p-> PaymentDetail.builder()
                                .method(Optional.ofNullable(p.getPaymentMethod()).map(nfePaymentMethod -> PaymentMethod.findByCode(nfePaymentMethod.getValue())).orElse(PaymentMethod.OUTROS))
                                .value(this.toBigDecimal(p.getPaymentValue()))
                                .cardSet(p.getCardSet() != null ? CardSet.builder()
                                        .integration(Optional.ofNullable(p.getCardSet().getPaymentIntegrationType()).map(nfePaymentIntegrationType -> CardSetIntegration.findByCode(nfePaymentIntegrationType.getValue())).orElse(null))
                                        .cnpj(p.getCardSet().getCnpj())
                                        .cardFlag(Optional.ofNullable(p.getCardSet().getCardFlag()).map(nfeCardFlag -> CardFlag.findByCode(nfeCardFlag.getValue())).orElse(null))
                                        .authorizationNumber(p.getCardSet().getAuthorizationNumber())
                                        .build() : null)
                                .build()).collect(Collectors.toList()))
                        .build();
            }
            if(!StringUtils.isEmpty(nfePayment.getChangeValue())) {
                builder.changeValue(this.toBigDecimal(nfePayment.getChangeValue()));
            }
            return builder.build();
        }
        
     // @formatter:on
        return null;
    }

    private Charging buildCharging() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.charging.NFeCharging nfeCharging = this.processedNFe.getNfe().getNFeInfo().getnFeCharging();
        if(nfeCharging != null) {
            return Charging.builder()
                    .invoice(this.buildChargingInvoice())
                    .duplicates(this.buildChargingDuplicates())
                    .build();
        }
     // @formatter:on
        return null;
    }

    private Collection<Duplicate> buildChargingDuplicates() {
     // @formatter:off
        final Collection<eprecise.efiscal4j.nfe.v400.charging.Duplicate> nfeDuplicates = this.processedNFe.getNfe().getNFeInfo().getnFeCharging().getDuplicates();
        if((nfeDuplicates != null) && !nfeDuplicates.isEmpty()) {
            return nfeDuplicates.stream().map(nfeDuplicate-> Duplicate.builder()
                    .number(nfeDuplicate.getNumber())
                    .due(Optional.ofNullable(nfeDuplicate.getDueDate()).map(t -> {
                        try {
                            return NFeDate.dateFormat.parse(t);
                        } catch (final ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }).orElse(null))
                    .value(this.toBigDecimal(nfeDuplicate.getValue()))
                    .build()).collect(Collectors.toList());
        }
     // @formatter:on
        return null;
    }

    private Invoice buildChargingInvoice() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.charging.Invoice nfeInvoice = this.processedNFe.getNfe().getNFeInfo().getnFeCharging().getInvoice();
        if(nfeInvoice != null) {
            Invoice.builder()
            .number(nfeInvoice.getNumber())
            .originalValue(this.toBigDecimal(nfeInvoice.getOriginalValue()))
            .discountValue(this.toBigDecimal(nfeInvoice.getDiscountValue()))
            .build();
        }
     // @formatter:on
        return null;
    }

    private Emitter buildEmitter() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.person.Emitter nfeEmitter = this.processedNFe.getNfe().getNFeInfo().getEmitter();
        return Emitter.builder()
                .documents(this.buildEmitterDocuments())
                .crt(Optional.ofNullable(nfeEmitter.getCrt()).map(nfeCrt -> CRT.findByCode(nfeCrt.getValue())).orElse(null))
                .address(this.buildEmitterAddress())
                .phone(nfeEmitter.getAdress() != null ? nfeEmitter.getAdress().getPhone() : null)
                .build();
     // @formatter:on
    }

    private EmitterAddress buildEmitterAddress() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.address.Address nfeEmitterAddress = this.processedNFe.getNfe().getNFeInfo().getEmitter().getAdress();
        if(nfeEmitterAddress != null) {
            return EmitterAddress.builder()
                    .cep(nfeEmitterAddress.getCep())
                    .street(nfeEmitterAddress.getStreet())
                    .district(nfeEmitterAddress.getDistrict())
                    .city(nfeEmitterAddress.getCity() != null ? EmitterAddressCity.builder()
                            .ibgeCode(nfeEmitterAddress.getCity().getIbgeCode())
                            .description(nfeEmitterAddress.getCity().getDescription())
                            .uf(nfeEmitterAddress.getCity().getUf())
                            .build() : null)
                    .number(nfeEmitterAddress.getNumber())
                    .complement(nfeEmitterAddress.getComplement())
                    .build();
        }
     // @formatter:on
        return null;
    }

    private EmitterDocuments buildEmitterDocuments() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.person.Emitter nfeEmitter = this.processedNFe.getNfe().getNFeInfo().getEmitter();
        final eprecise.efiscal4j.nfe.v400.person.AbstractDocuments nfeEmitterDocuments = this.processedNFe.getNfe().getNFeInfo().getEmitter().getDocuments();
        if(nfeEmitterDocuments instanceof eprecise.efiscal4j.nfe.v400.person.LegalEntityDocuments) {
            final eprecise.efiscal4j.nfe.v400.person.LegalEntityDocuments nfeEmitterLegalEntityDocuments = (eprecise.efiscal4j.nfe.v400.person.LegalEntityDocuments) nfeEmitterDocuments;
            return EmitterLegalEntityDocuments.builder()
                    .name(nfeEmitterDocuments.getAbstractName())
                    .ie(nfeEmitter.getStateRegistration())
                    .ieSt(nfeEmitter.getStateRegistrationST())
                    .municipalDocuments(nfeEmitter.getMunicipalRegistration() != null ? EmitterMunicipalDocuments.builder()
                            .im(nfeEmitter.getMunicipalRegistration())
                            .cnae(nfeEmitter.getCnae())
                            .build() : null)
                    .cnpj(nfeEmitterLegalEntityDocuments.getCnpj())
                    .fancyName(nfeEmitter.getFancyName())
                    .build();
        } else if(nfeEmitterDocuments instanceof eprecise.efiscal4j.nfe.v400.person.NaturalPersonDocuments) {
            final eprecise.efiscal4j.nfe.v400.person.NaturalPersonDocuments nfeEmitterNaturalPersonDocuments = (eprecise.efiscal4j.nfe.v400.person.NaturalPersonDocuments) nfeEmitterDocuments;
            return EmitterNaturalPersonDocuments.builder()
                    .name(nfeEmitterDocuments.getAbstractName())
                    .ie(nfeEmitter.getStateRegistration())
                    .ieSt(nfeEmitter.getStateRegistrationST())
                    .municipalDocuments(nfeEmitter.getMunicipalRegistration() != null ? EmitterMunicipalDocuments.builder()
                            .im(nfeEmitter.getMunicipalRegistration())
                            .cnae(nfeEmitter.getCnae())
                            .build() : null)
                    .cpf(nfeEmitterNaturalPersonDocuments.getCpf())
                    .build();
        }
     // @formatter:on
        return null;
    }

    private Receiver buildReceiver() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.person.Receiver nfeReceiver = this.processedNFe.getNfe().getNFeInfo().getReceiver();
        if(nfeReceiver != null) {
            return Receiver.builder()
                    .documents(this.buildReceiverDocuments())
                    .address(this.buildReceiverAddress())
                    .email(nfeReceiver.getEmail())
                    .phone(nfeReceiver.getAdress() != null ? nfeReceiver.getAdress().getPhone() : null)
                    .build();
        }
     // @formatter:on
        return null;
    }

    private ReceiverAddress buildReceiverAddress() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.address.Address nfeReceiverAddress = this.processedNFe.getNfe().getNFeInfo().getReceiver().getAdress();
        if(nfeReceiverAddress != null) {
            return BrazillianReceiverAddress.builder()
                    .cep(nfeReceiverAddress.getCep())
                    .street(nfeReceiverAddress.getStreet())
                    .district(nfeReceiverAddress.getDistrict())
                    .city(nfeReceiverAddress.getCity() != null ? ReceiverAddressCity.builder()
                            .ibgeCode(nfeReceiverAddress.getCity().getIbgeCode())
                            .description(nfeReceiverAddress.getCity().getDescription())
                            .uf(nfeReceiverAddress.getCity().getUf())
                            .build() : null)
                    .number(nfeReceiverAddress.getNumber())
                    .complement(nfeReceiverAddress.getComplement())
                    .build();
        }
     // @formatter:on
        return null;
    }

    private ReceiverDocuments buildReceiverDocuments() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.person.Receiver nfeReceiver = this.processedNFe.getNfe().getNFeInfo().getReceiver();
        final eprecise.efiscal4j.nfe.v400.person.AbstractDocuments nfeReceiverDocuments = this.processedNFe.getNfe().getNFeInfo().getReceiver().getDocuments();
        
        if(nfeReceiverDocuments != null) {
            final ReceiverCnp cnp = this.buildReceiverCnp();
            
            return ReceiverDocuments.builder()
                    .cnp(cnp)
                    .name(nfeReceiverDocuments.getAbstractName())
                    .ie(this.buildReceiverIE())
                    .im(nfeReceiver.getMunicipalRegistration())
                    .build();
        }
        
     // @formatter:on
        return null;
    }

    private ReceiverCnp buildReceiverCnp() {
        final eprecise.efiscal4j.nfe.v400.person.AbstractDocuments nfeReceiverDocuments = this.processedNFe.getNfe().getNFeInfo().getReceiver().getDocuments();

        if (nfeReceiverDocuments instanceof eprecise.efiscal4j.nfe.v400.person.LegalEntityDocuments) {
            return ReceiverCnpj.builder().cnpj(nfeReceiverDocuments.getCnpjCpf()).build();
        } else if (nfeReceiverDocuments instanceof eprecise.efiscal4j.nfe.v400.person.NaturalPersonDocuments) {
            return ReceiverCpf.builder().cpf(nfeReceiverDocuments.getCnpjCpf()).build();
        }
        return null;
    }

    private ReceiverIE buildReceiverIE() {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.person.Receiver nfeReceiver = this.processedNFe.getNfe().getNFeInfo().getReceiver();
        final eprecise.efiscal4j.nfe.v400.person.AbstractDocuments nfeReceiverDocuments = this.processedNFe.getNfe().getNFeInfo().getReceiver().getDocuments();
        if (nfeReceiver.getStateRegistrationReceiverIndicator() != null) {
            switch(nfeReceiver.getStateRegistrationReceiverIndicator()) {
                case CONTRIBUINTE_ICMS: return TaxpayerReceiverIE.builder().ie(nfeReceiverDocuments.getStateRegistration()).build();
                case ISENTO: return FreeTaxpayerReceiverIE.builder().build();
                case NAO_CONTRIBUINTE: return NonTaxpayerReceiverIE.builder().build();
            }
        }
     // @formatter:on
        return null;
    }

    private EmissionDate buildEmissionDate() {
     // @formatter:off
        return CustomEmissionDate.builder()
                .custom(Optional.ofNullable(this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getEmissionDateTime()).map(t -> {
                    try {
                        return NFeDateTimeUTC.dateFormat.parse(t);
                    } catch (final ParseException e) {
                        throw new RuntimeException(e);
                    }
                }).orElse(null))
                .build();
     // @formatter:on
    }

    private FiscalDocumentSerie buildSerie() {
     // @formatter:off
        return FiscalDocumentSerie.builder()
                .number(Optional.ofNullable(this.processedNFe.getNfe().getNFeInfo().getnFeIdentification()).map(id -> id.getFiscalDocumentSeries()).map(Integer::parseInt).orElse(null))
                .environment(Optional.ofNullable(this.processedNFe.getNfe().getNFeInfo().getnFeIdentification()).map(id -> id.getTransmissionEnvironment()).map(t -> TransmissionEnvironment.findBy(t.getValue()).orElse(null)).orElse(null))
                .build();
     // @formatter:on
    }

    private Consumer buildConsumer() {
        final Receiver receiver = this.buildReceiver();
        if (receiver != null) {
            if (ValidationBuilder.from(receiver).validate().getViolations().isEmpty()) {
                return receiver;
            } else {
                return Optional.ofNullable(this.buildReceiverCnp()).map(cnp -> SimpleConsumer.builder().cnp(cnp).build()).orElse(null);
            }
        }
        return null;
    }

    private IODate buildEntranceOrExit() {
     // @formatter:off
        return CustomIODate.builder()
                .custom(Optional.ofNullable(this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getEntranceOrExitDateTime()).map(t -> {
                    try {
                        return NFeDateTimeUTC.dateFormat.parse(t);
                    } catch (final ParseException e) {
                        throw new RuntimeException(e);
                    }
                }).orElse(null))
                .build();
     // @formatter:on
    }

    private Collection<DocumentReference> buildDocumentReferences() {
     // @formatter:off
        final Collection<eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedDocuments> nfeReferencedDocuments = this.processedNFe.getNfe().getNFeInfo().getnFeIdentification().getReferencedDocuments();
        if((nfeReferencedDocuments != null) && !nfeReferencedDocuments.isEmpty()) {
            return nfeReferencedDocuments.stream().map(rd -> {
                if(!StringUtils.isEmpty(rd.getReferencedNFeAccessKey())) {
                    return ReferenceToNFeAccessKey.builder().accessKey(rd.getReferencedNFeAccessKey()).build();
                } else if(rd.getReferencedNF() != null) {
                    return ReferenceToNF.builder()
                            .series(rd.getReferencedNF().getSeries())
                            .number(Optional.ofNullable(rd.getReferencedNF().getNumber()).map(Long::parseLong).orElse(null))
                            .uf(rd.getReferencedNF().getEmitterUf())
                            .cnpj(rd.getReferencedNF().getEmitterCnpj())
                            .month(Optional.ofNullable(rd.getReferencedNF().getEmissionYearMonth()).map(ym -> YearMonth.parse(ym, DateTimeFormatter.ofPattern("yyMM"))).orElse(null))
                            .build();
                } else if(!StringUtils.isEmpty(rd.getReferencedCTeAccessKey())) {
                    return ReferenceToCTe.builder().accessKey(rd.getReferencedCTeAccessKey()).build();
                } else if(rd.getReferencedECF() != null) {
                    return ReferenceToECF.builder()
                            .model(Optional.ofNullable(rd.getReferencedECF().getModel()).map(m -> ReferencedECFModel.findBy(m.getValue()).orElse(null)).orElse(null))
                            .ecfNumber(rd.getReferencedECF().getEcfNumber())
                            .cooNumber(rd.getReferencedECF().getCooNumber())
                            .build();
                } else if(rd.getProducerReferencedNF() != null) {
                    return ReferenceToNFP.builder()
                            .series(rd.getProducerReferencedNF().getSeries())
                            .number(Optional.ofNullable(rd.getProducerReferencedNF().getNumber()).map(Long::parseLong).orElse(null))
                            .uf(rd.getProducerReferencedNF().getEmitterUf())
                            .cnp(Optional.ofNullable(rd.getProducerReferencedNF().getEmitterCnpj()).filter(cnpj -> !StringUtils.isEmpty(cnpj)).map(cnpj -> (NfpCnp) NfpCnpj.builder().cnpj(cnpj).build()).orElseGet(()-> 
                                Optional.ofNullable(rd.getProducerReferencedNF().getEmitterCpf()).filter(cpf -> !StringUtils.isEmpty(cpf)).map(cpf -> (NfpCnp) NfpCpf.builder().cpf(cpf).build()).orElseGet(()-> null)))
                            .month(Optional.ofNullable(rd.getProducerReferencedNF().getEmissionYearMonth()).map(ym -> YearMonth.parse(ym, DateTimeFormatter.ofPattern("yyMM"))).orElse(null))
                            .ie(rd.getProducerReferencedNF().getStateRegistration())
                            .model(Optional.ofNullable(rd.getProducerReferencedNF().getModel()).map(m -> ProducerReferencedNFModel.findBy(m.getValue()).orElse(null)).orElse(null))
                            .build();
                }
                return null;
            }).collect(Collectors.toSet());
        }
     // @formatter:on
        return null;
    }

    private List<Item> buildItems() {
        // @formatter:off
            final Collection<eprecise.efiscal4j.nfe.v400.NFeDetail> nfeDetails = this.processedNFe.getNfe().getNFeInfo().getnFeDetails();
            if((nfeDetails != null) && !nfeDetails.isEmpty()) {
                return nfeDetails.stream().map(nfeDetail -> {
                   return Item.builder()
                           .code(nfeDetail.getnFeItem().getItemCode())
                           .name(nfeDetail.getnFeItem().getItemDescription())
                           .globalTradeItemNumber(ItemEan.builder()
                                   .globalTradeItemNumber(nfeDetail.getnFeItem().getGlobalTradeItemNumber())
                                   .taxableGlobalTradeItemNumber(nfeDetail.getnFeItem().getTaxableUnitGlobalTradeItemNumber())
                                   .build())
                           .unity(ItemUnity.builder()
                                   .comercialUnity(Optional.ofNullable(nfeDetail.getnFeItem().getComercialUnit()).map(cu -> DefaultUnity.findByAcronym(cu).orElse(null)).orElse(null))
                                   .taxableUnity(Optional.ofNullable(nfeDetail.getnFeItem().getTaxableUnit()).map(tu -> DefaultUnity.findByAcronym(tu).orElse(null)).orElse(null))
                                   .build())
                           .quantity(ItemQuantity.builder()
                                   .comercialQuantity(this.toBigDecimal(nfeDetail.getnFeItem().getComercialQuantity()))
                                   .taxableQuantity(this.toBigDecimal(nfeDetail.getnFeItem().getTaxableQuantity()))
                                   .build())
                           .unitaryValue(ItemUnitaryValue.builder()
                                   .comercialUnitaryValue(this.toBigDecimal(nfeDetail.getnFeItem().getComercialUnitaryValue()))
                                   .taxableUnitaryValue(this.toBigDecimal(nfeDetail.getnFeItem().getTaxationUnitaryValue()))
                                   .build())
                           .discount(this.toBigDecimal(nfeDetail.getnFeItem().getDiscountValue()))
                           .freight(this.toBigDecimal(nfeDetail.getnFeItem().getFreightValue()))
                           .insurance(this.toBigDecimal(nfeDetail.getnFeItem().getInsuranceValue()))
                           .othersValue(this.toBigDecimal(nfeDetail.getnFeItem().getOthersValue()))
                           .taxStructure(TaxStructure.builder()
                                   .ncm(nfeDetail.getnFeItem().getNcm())
                                   .cfop(nfeDetail.getnFeItem().getCfop())
                                   .cest(nfeDetail.getnFeItem().getCest())
                                   .taxes(this.buildItemTaxes(nfeDetail.getTax()))
                                   .build())
                           .additionalInfo(nfeDetail.getAdditionalProductInfo())
                           .importDeclarations(null)
                           .medications(this.buildMedications(nfeDetail))
                           .traces(this.buildTraces(nfeDetail))
                           .build();
                }).collect(Collectors.toList());
            }
        // @formatter:on
        return null;
    }

    private Collection<Trace> buildTraces(final NFeDetail nfeDetail) {
     // @formatter:off
        final Collection<eprecise.efiscal4j.nfe.v400.Trace> traces = nfeDetail.getnFeItem().getTraces();
        if((traces != null) && !traces.isEmpty()) {
            return traces.stream().map(tr -> {
                return Trace.builder()
                        .batchNumber(tr.getBatchNumber())
                        .batchQuantity(this.toBigDecimal(tr.getBatchQuantity()))
                        .manufacturing(Optional.ofNullable(tr.getManufacturingDate()).map(t -> {
                            try {
                                return NFeDate.dateFormat.parse(t);
                            } catch (final ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }).orElse(null))
                        .expiration(Optional.ofNullable(tr.getExpirationDate()).map(t -> {
                            try {
                                return NFeDate.dateFormat.parse(t);
                            } catch (final ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }).orElse(null))
                        .aggregationCode(tr.getAggregationCode())
                        .build();
            }).collect(Collectors.toList());
        }
     // @formatter:on
        return null;
    }

    private Medications buildMedications(final NFeDetail nfeDetail) {
     // @formatter:off
        final eprecise.efiscal4j.nfe.v400.Medications medications = nfeDetail.getnFeItem().getMedications();
        if(medications != null) {
            return Medications.builder()
                    .anvisaProductCode(medications.getAnvisaProductCode())
                    .maxPriceConsumers(this.toBigDecimal(medications.getMaxPriceConsumers()))
                    .build();
        }
     // @formatter:on
        return null;
    }

    private Collection<ItemTax> buildItemTaxes(final eprecise.efiscal4j.nfe.v400.tax.Tax nfeItemTax) {
       // @formatter:off
        
        final ICMS icms = this.buildIcms(nfeItemTax.getIcms());
        final PIS pis = this.buildPis(nfeItemTax.getPis());
        final PISST pisSt = this.buildPisSt(nfeItemTax.getPisSt());
        final COFINS cofins = this.buildCofins(nfeItemTax.getCofins());
        final COFINSST cofinsSt = this.buildCofinsSt(nfeItemTax.getCofinsSt());
        final IPI ipi = this.buildIpi(nfeItemTax.getIpi());
        final II ii = this.buildII(nfeItemTax.getIi());
        final ICMSUFReceiver icmsUfReceiver = this.buildIcmsUfReceiver(nfeItemTax.getIcmsUfReceiver());
        
        return Stream.of(icms, pis, pisSt, cofins, cofinsSt, ipi, ii, icmsUfReceiver)
                .filter(ItemTax.class::isInstance).map(ItemTax.class::cast).collect(Collectors.toSet());
       // @formatter:on
    }

    private ICMSUFReceiver buildIcmsUfReceiver(final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSUFReceiver nfeIcmsUfReceiver) {
        // @formatter:off
        if(nfeIcmsUfReceiver != null) {
            return ICMSUFReceiver.builder()
                    .calculationBasis(this.toBigDecimal(nfeIcmsUfReceiver.getReceiverUfBcValue()))
                    .aliquot(this.toBigDecimal(nfeIcmsUfReceiver.getReceiverUfIcmsAliquot()))
                    .bcFcpValue(this.toBigDecimal(nfeIcmsUfReceiver.getReceiverUfBcFcpValue()))
                    .fcpAditionalAliquot(this.toBigDecimal(nfeIcmsUfReceiver.getReceiverUfFCPPercentual()))
                    .fcpValue(this.toBigDecimal(nfeIcmsUfReceiver.getReceiverUfFCPValue()))
                    .interstateAliquot(Optional.ofNullable(nfeIcmsUfReceiver.getInterstateIcmsUfAliquot()).map(i -> InterstateICMSUFAliquot.findByCode(i.getValue())).orElse(null))
                    .sharePercentual(this.toBigDecimal(nfeIcmsUfReceiver.getReceiverUfSharePercentual()))
                    .shareValue(this.toBigDecimal(nfeIcmsUfReceiver.getReceiverUfIcmsShareValue()))
                    .emitterShareValue(this.toBigDecimal(nfeIcmsUfReceiver.getEmitterUfIcmsShareValue()))
                    .build();
        }
        // @formatter:on
        return null;
    }

    private II buildII(final eprecise.efiscal4j.nfe.v400.tax.ii.II nfeIi) {
        // @formatter:off
        if(nfeIi != null) {
            return II.builder()
                    .calculationBasis(this.toBigDecimal(nfeIi.getBcValue()))
                    .customsCharge(this.toBigDecimal(nfeIi.getCustomsCharge()))
                    .value(this.toBigDecimal(nfeIi.getIiValue()))
                    .iof(this.toBigDecimal(nfeIi.getIofValue()))
                    .build();
        }
        // @formatter:on
        return null;
    }

    private IPI buildIpi(final eprecise.efiscal4j.nfe.v400.tax.ipi.IPI nfeIpi) {
        // @formatter:off
        if(nfeIpi != null) {
            switch(nfeIpi.getClass().getSimpleName()) {
                case "IPI00": {
                    final eprecise.efiscal4j.nfe.v400.tax.ipi.IPI00 nfeIpi00 = (eprecise.efiscal4j.nfe.v400.tax.ipi.IPI00) nfeIpi;
                    return IPI00.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .value(this.buildIpiValue(nfeIpi00))
                            .build();
                }
                case "IPI01": {
                    return IPI01.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .build();
                }
                case "IPI02": {
                    return IPI02.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .build();
                }
                case "IPI03": {
                    return IPI03.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .build();
                }
                case "IPI04": {
                    return IPI04.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .build();
                }
                case "IPI05": {
                    return IPI05.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .build();
                }
                case "IPI49": {
                    final eprecise.efiscal4j.nfe.v400.tax.ipi.IPI49 nfeIpi49 = (eprecise.efiscal4j.nfe.v400.tax.ipi.IPI49) nfeIpi;
                    return IPI49.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .value(this.buildIpiValue(nfeIpi49))
                            .build();
                }
                case "IPI50": {
                    final eprecise.efiscal4j.nfe.v400.tax.ipi.IPI50 nfeIpi50 = (eprecise.efiscal4j.nfe.v400.tax.ipi.IPI50) nfeIpi;
                    return IPI50.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .value(this.buildIpiValue(nfeIpi50))
                            .build();
                }
                case "IPI51": {
                    return IPI51.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .build();
                }
                case "IPI52": {
                    return IPI52.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .build();
                }
                case "IPI53": {
                    return IPI53.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .build();
                }
                case "IPI54": {
                    return IPI54.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .build();
                }
                case "IPI55": {
                    return IPI55.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .build();
                }
                case "IPI99": {
                    final eprecise.efiscal4j.nfe.v400.tax.ipi.IPI99 nfeIpi99 = (eprecise.efiscal4j.nfe.v400.tax.ipi.IPI99) nfeIpi;
                    return IPI99.builder()
                            .generalData(this.buildIpiGeneralData(nfeIpi))
                            .value(this.buildIpiValue(nfeIpi99))
                            .build();
                }
            }
        }
        // @formatter:on
        return null;
    }

    private IpiValue buildIpiValue(final eprecise.efiscal4j.nfe.v400.tax.ipi.BaseIPITrib nfeIpiTrib) {
     // @formatter:off
        if(nfeIpiTrib != null) {
            return IpiValue.builder()
                    .calculationBasis(this.toBigDecimal(nfeIpiTrib.getBcValue()))
                    .aliquot(this.toBigDecimal(nfeIpiTrib.getIpiAliquot()))
                    .quantity(this.toBigDecimal(nfeIpiTrib.getUnityQuantity()))
                    .unitaryValue(this.toBigDecimal(nfeIpiTrib.getUnityValue()))
                    .value(this.toBigDecimal(nfeIpiTrib.getIpiValue()))
                    .build();
        }
     // @formatter:on
        return null;
    }

    private IPIGeneralData buildIpiGeneralData(final eprecise.efiscal4j.nfe.v400.tax.ipi.IPI nfeIpi) {
     // @formatter:off
        if(nfeIpi != null) {
            return IPIGeneralData.builder()
                    .producerCnpj(nfeIpi.getProducerCNPJ())
                    .ipiSealCode(nfeIpi.getIpiSealCode())
                    .ipiSealQuantity(nfeIpi.getIpiSealQuantity())
                    .legalFramework(nfeIpi.getLegalFramework())
                    .build();
        }
     // @formatter:on
        return null;
    }

    private COFINSST buildCofinsSt(final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINSST nfeCofinsSt) {
        // @formatter:off
        if(nfeCofinsSt != null) {
            return COFINSST.builder()
                    .value(this.buildCofinsValueWithAliquot(nfeCofinsSt))
                    .build();
        }
        // @formatter:on
        return null;
    }

    private COFINS buildCofins(final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS nfeCofins) {
        // @formatter:off
        if(nfeCofins != null) {
            switch(nfeCofins.getClass().getSimpleName()){
                case "COFINS01": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS01 nfeCofins01 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS01) nfeCofins;
                    return COFINS01.builder()
                            .cofins(CofinsValueWithAliquotPercent.builder()
                                    .aliquot(CofinsAliquotPercentWithBc.builder()
                                            .calculationBasis(this.toBigDecimal(nfeCofins01.getBcValue()))
                                            .aliquot(this.toBigDecimal(nfeCofins01.getCofinsAliquot()))
                                            .build())
                                    .value(this.toBigDecimal(nfeCofins01.getCofinsValue()))
                                    .build())
                            .build();
                }
                case "COFINS02": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS02 nfeCofins02 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS02) nfeCofins;
                    return COFINS02.builder()
                            .cofins(CofinsValueWithAliquotPercent.builder()
                                    .aliquot(CofinsAliquotPercentWithBc.builder()
                                            .calculationBasis(this.toBigDecimal(nfeCofins02.getBcValue()))
                                            .aliquot(this.toBigDecimal(nfeCofins02.getCofinsAliquot()))
                                            .build())
                                    .value(this.toBigDecimal(nfeCofins02.getCofinsValue()))
                                    .build())
                            .build();
                }
                case "COFINS03": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS03 nfeCofins03 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS03) nfeCofins;
                    return COFINS03.builder()
                            .cofins(CofinsValueWithAliquotValue.builder()
                                    .aliquot(CofinsAliquotValueWithQuantity.builder()
                                            .quantity(this.toBigDecimal(nfeCofins03.getProductQuantity()))
                                            .aliquotValue(this.toBigDecimal(nfeCofins03.getProductAliquot()))
                                            .build())
                                    .value(this.toBigDecimal(nfeCofins03.getCofinsValue()))
                                    .build())
                            .build();
                }
                case "COFINS04": {
                    return COFINS04.builder().build();
                }
                case "COFINS05": {
                    return COFINS05.builder().build();
                }
                case "COFINS06": {
                    return COFINS06.builder().build();
                }
                case "COFINS07": {
                    return COFINS07.builder().build();
                }
                case "COFINS08": {
                    return COFINS08.builder().build();
                }
                case "COFINS09": {
                    return COFINS09.builder().build();
                }
                case "COFINS49": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS49 nfeCofins49 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS49) nfeCofins;
                    return COFINS49.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins49))
                            .build();
                }
                case "COFINS50": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS50 nfeCofins50 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS50) nfeCofins;
                    return COFINS50.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins50))
                            .build();
                }
                case "COFINS51": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS51 nfeCofins51 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS51) nfeCofins;
                    return COFINS51.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins51))
                            .build();
                }
                case "COFINS52": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS52 nfeCofins52 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS52) nfeCofins;
                    return COFINS52.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins52))
                            .build();
                }
                case "COFINS53": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS53 nfeCofins53 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS53) nfeCofins;
                    return COFINS53.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins53))
                            .build();
                }
                case "COFINS54": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS54 nfeCofins54 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS54) nfeCofins;
                    return COFINS54.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins54))
                            .build();
                }
                case "COFINS55": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS55 nfeCofins55 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS55) nfeCofins;
                    return COFINS55.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins55))
                            .build();
                }
                case "COFINS56": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS56 nfeCofins56 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS56) nfeCofins;
                    return COFINS56.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins56))
                            .build();
                }
                case "COFINS60": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS60 nfeCofins60 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS60) nfeCofins;
                    return COFINS60.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins60))
                            .build();
                }
                case "COFINS61": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS61 nfeCofins61 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS61) nfeCofins;
                    return COFINS61.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins61))
                            .build();
                }
                case "COFINS62": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS62 nfeCofins62 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS62) nfeCofins;
                    return COFINS62.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins62))
                            .build();
                }
                case "COFINS63": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS63 nfeCofins63 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS63) nfeCofins;
                    return COFINS63.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins63))
                            .build();
                }
                case "COFINS64": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS64 nfeCofins64 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS64) nfeCofins;
                    return COFINS64.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins64))
                            .build();
                }
                case "COFINS65": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS65 nfeCofins65 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS65) nfeCofins;
                    return COFINS65.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins65))
                            .build();
                }
                case "COFINS66": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS66 nfeCofins66 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS66) nfeCofins;
                    return COFINS66.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins66))
                            .build();
                }
                case "COFINS67": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS67 nfeCofins67 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS67) nfeCofins;
                    return COFINS67.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins67))
                            .build();
                }
                case "COFINS70": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS70 nfeCofins70 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS70) nfeCofins;
                    return COFINS70.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins70))
                            .build();
                }
                case "COFINS71": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS71 nfeCofins71 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS71) nfeCofins;
                    return COFINS71.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins71))
                            .build();
                }
                case "COFINS72": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS72 nfeCofins72 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS72) nfeCofins;
                    return COFINS72.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins72))
                            .build();
                }
                case "COFINS73": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS73 nfeCofins73 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS73) nfeCofins;
                    return COFINS73.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins73))
                            .build();
                }
                case "COFINS74": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS74 nfeCofins74 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS74) nfeCofins;
                    return COFINS74.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins74))
                            .build();
                }
                case "COFINS75": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS75 nfeCofins75 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS75) nfeCofins;
                    return COFINS75.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins75))
                            .build();
                }
                case "COFINS98": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS98 nfeCofins98 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS98) nfeCofins;
                    return COFINS98.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins98))
                            .build();
                }
                case "COFINS99": {
                    final eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS99 nfeCofins99 = (eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS99) nfeCofins;
                    return COFINS99.builder()
                            .cofins(this.buildCofinsValueWithAliquot(nfeCofins99))
                            .build();
                }
                
            }
        }
        // @formatter:on
        return null;
    }

    private CofinsValueWithAliquot buildCofinsValueWithAliquot(final eprecise.efiscal4j.nfe.v400.tax.cofins.validation.BaseCOFINSOtherStandard nfeCofinsOther) {
     // @formatter:off
        if(nfeCofinsOther != null) {
            if(!StringUtils.isEmpty(nfeCofinsOther.getBcValue()) && !StringUtils.isEmpty(nfeCofinsOther.getCofinsAliquot())) {
                return CofinsValueWithAliquot.builder()
                        .aliquot(CofinsAliquotPercentWithBc.builder()
                                .calculationBasis(this.toBigDecimal(nfeCofinsOther.getBcValue()))
                                .aliquot(this.toBigDecimal(nfeCofinsOther.getCofinsAliquot()))
                                .build())
                        .value(this.toBigDecimal(nfeCofinsOther.getCofinsValue()))
                        .build();
            } else if(!StringUtils.isEmpty(nfeCofinsOther.getProductQuantity()) && !StringUtils.isEmpty(nfeCofinsOther.getProductAliquot())) {
                return CofinsValueWithAliquot.builder()
                        .aliquot(CofinsAliquotValueWithQuantity.builder()
                                .aliquotValue(this.toBigDecimal(nfeCofinsOther.getProductAliquot()))
                                .quantity(this.toBigDecimal(nfeCofinsOther.getProductQuantity()))
                                .build())
                        .value(this.toBigDecimal(nfeCofinsOther.getCofinsValue()))
                        .build();
            }
        }
        return null;
     // @formatter:on
    }

    private PISST buildPisSt(final eprecise.efiscal4j.nfe.v400.tax.pis.PISST nfePisSt) {
        // @formatter:off
        if(nfePisSt != null) {
            return PISST.builder()
                    .value(this.buildPisValueWithAliquot(nfePisSt))
                    .build();
        }
        // @formatter:on
        return null;
    }

    private PIS buildPis(final eprecise.efiscal4j.nfe.v400.tax.pis.PIS nfePis) {
        // @formatter:off
        if(nfePis != null) {
            switch(nfePis.getClass().getSimpleName()){
                case "PIS01": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS01 nfePis01 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS01) nfePis;
                    return PIS01.builder()
                            .pis(PisValueWithAliquotPercent.builder()
                                    .aliquot(PisAliquotPercentWithBc.builder()
                                            .calculationBasis(this.toBigDecimal(nfePis01.getBcValue()))
                                            .aliquot(this.toBigDecimal(nfePis01.getPisAliquot()))
                                            .build())
                                    .value(this.toBigDecimal(nfePis01.getPisValue()))
                                    .build())
                            .build();
                }
                case "PIS02": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS02 nfePis02 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS02) nfePis;
                    return PIS02.builder()
                            .pis(PisValueWithAliquotPercent.builder()
                                    .aliquot(PisAliquotPercentWithBc.builder()
                                            .calculationBasis(this.toBigDecimal(nfePis02.getBcValue()))
                                            .aliquot(this.toBigDecimal(nfePis02.getPisAliquot()))
                                            .build())
                                    .value(this.toBigDecimal(nfePis02.getPisValue()))
                                    .build())
                            .build();
                }
                case "PIS03": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS03 nfePis03 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS03) nfePis;
                    return PIS03.builder()
                            .pis(PisValueWithAliquotValue.builder()
                                    .aliquot(PisAliquotValueWithQuantity.builder()
                                            .quantity(this.toBigDecimal(nfePis03.getProductQuantity()))
                                            .aliquotValue(this.toBigDecimal(nfePis03.getProductAliquot()))
                                            .build())
                                    .value(this.toBigDecimal(nfePis03.getPisValue()))
                                    .build())
                            .build();
                }
                case "PIS04": {
                    return PIS04.builder().build();
                }
                case "PIS05": {
                    return PIS05.builder().build();
                }
                case "PIS06": {
                    return PIS06.builder().build();
                }
                case "PIS07": {
                    return PIS07.builder().build();
                }
                case "PIS08": {
                    return PIS08.builder().build();
                }
                case "PIS09": {
                    return PIS09.builder().build();
                }
                case "PIS49": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS49 nfePis49 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS49) nfePis;
                    return PIS49.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis49))
                            .build();
                }
                case "PIS50": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS50 nfePis50 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS50) nfePis;
                    return PIS50.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis50))
                            .build();
                }
                case "PIS51": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS51 nfePis51 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS51) nfePis;
                    return PIS51.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis51))
                            .build();
                }
                case "PIS52": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS52 nfePis52 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS52) nfePis;
                    return PIS52.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis52))
                            .build();
                }
                case "PIS53": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS53 nfePis53 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS53) nfePis;
                    return PIS53.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis53))
                            .build();
                }
                case "PIS54": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS54 nfePis54 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS54) nfePis;
                    return PIS54.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis54))
                            .build();
                }
                case "PIS55": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS55 nfePis55 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS55) nfePis;
                    return PIS55.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis55))
                            .build();
                }
                case "PIS56": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS56 nfePis56 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS56) nfePis;
                    return PIS56.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis56))
                            .build();
                }
                case "PIS60": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS60 nfePis60 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS60) nfePis;
                    return PIS60.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis60))
                            .build();
                }
                case "PIS61": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS61 nfePis61 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS61) nfePis;
                    return PIS61.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis61))
                            .build();
                }
                case "PIS62": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS62 nfePis62 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS62) nfePis;
                    return PIS62.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis62))
                            .build();
                }
                case "PIS63": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS63 nfePis63 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS63) nfePis;
                    return PIS63.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis63))
                            .build();
                }
                case "PIS64": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS64 nfePis64 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS64) nfePis;
                    return PIS64.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis64))
                            .build();
                }
                case "PIS65": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS65 nfePis65 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS65) nfePis;
                    return PIS65.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis65))
                            .build();
                }
                case "PIS66": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS66 nfePis66 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS66) nfePis;
                    return PIS66.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis66))
                            .build();
                }
                case "PIS67": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS67 nfePis67 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS67) nfePis;
                    return PIS67.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis67))
                            .build();
                }
                case "PIS70": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS70 nfePis70 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS70) nfePis;
                    return PIS70.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis70))
                            .build();
                }
                case "PIS71": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS71 nfePis71 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS71) nfePis;
                    return PIS71.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis71))
                            .build();
                }
                case "PIS72": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS72 nfePis72 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS72) nfePis;
                    return PIS72.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis72))
                            .build();
                }
                case "PIS73": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS73 nfePis73 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS73) nfePis;
                    return PIS73.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis73))
                            .build();
                }
                case "PIS74": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS74 nfePis74 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS74) nfePis;
                    return PIS74.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis74))
                            .build();
                }
                case "PIS75": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS75 nfePis75 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS75) nfePis;
                    return PIS75.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis75))
                            .build();
                }
                case "PIS98": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS98 nfePis98 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS98) nfePis;
                    return PIS98.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis98))
                            .build();
                }
                case "PIS99": {
                    final eprecise.efiscal4j.nfe.v400.tax.pis.PIS99 nfePis99 = (eprecise.efiscal4j.nfe.v400.tax.pis.PIS99) nfePis;
                    return PIS99.builder()
                            .pis(this.buildPisValueWithAliquot(nfePis99))
                            .build();
                }
                
            }
        }
        // @formatter:on
        return null;
    }

    private PisValueWithAliquot buildPisValueWithAliquot(final eprecise.efiscal4j.nfe.v400.tax.pis.validation.BasePISOtherStandard nfePisOther) {
     // @formatter:off
        if(nfePisOther != null) {
            if(!StringUtils.isEmpty(nfePisOther.getBcValue()) && !StringUtils.isEmpty(nfePisOther.getPisAliquot())) {
                return PisValueWithAliquot.builder()
                        .aliquot(PisAliquotPercentWithBc.builder()
                                .calculationBasis(this.toBigDecimal(nfePisOther.getBcValue()))
                                .aliquot(this.toBigDecimal(nfePisOther.getPisAliquot()))
                                .build())
                        .value(this.toBigDecimal(nfePisOther.getPisValue()))
                        .build();
            } else if(!StringUtils.isEmpty(nfePisOther.getProductQuantity()) && !StringUtils.isEmpty(nfePisOther.getProductAliquot())) {
                return PisValueWithAliquot.builder()
                        .aliquot(PisAliquotValueWithQuantity.builder()
                                .aliquotValue(this.toBigDecimal(nfePisOther.getProductAliquot()))
                                .quantity(this.toBigDecimal(nfePisOther.getProductQuantity()))
                                .build())
                        .value(this.toBigDecimal(nfePisOther.getPisValue()))
                        .build();
            }
        }
        return null;
     // @formatter:on
    }

    private ICMS buildIcms(final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS nfeIcms) {
     // @formatter:off
        if(nfeIcms != null) {
            switch(nfeIcms.getClass().getSimpleName()) {
                case "ICMS00": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS00 nfeIcms00 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS00 ) nfeIcms;
                    return ICMS00.builder()
                            .origin(Optional.ofNullable(nfeIcms00.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icms(IcmsWithBcValue.builder()
                                    .aliquot(this.toBigDecimal(nfeIcms00.getIcmsAliquot()))
                                    .calculationBasis(this.buildIcmsBc(nfeIcms00.getBcModality(), this.toBigDecimal(nfeIcms00.getBcValue()), BigDecimal.ZERO))
                                    .value(this.toBigDecimal(nfeIcms00.getIcmsValue()))
                                    .build())
                            .build();
                }
                case "ICMS10": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS10 nfeIcms10 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS10 ) nfeIcms;
                    return ICMS10.builder()
                            .origin(Optional.ofNullable(nfeIcms10.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icms(IcmsWithBcValue.builder()
                                    .aliquot(this.toBigDecimal(nfeIcms10.getIcmsAliquot()))
                                    .calculationBasis(this.buildIcmsBc(nfeIcms10.getBcModality(), this.toBigDecimal(nfeIcms10.getBcValue()), BigDecimal.ZERO))
                                    .value(this.toBigDecimal(nfeIcms10.getIcmsValue()))
                                    .build())
                            .icmsSt(IcmsStWithBcReductionPercent.builder()
                                    .value(IcmsStWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcms10.getIcmsStAliquot()))
                                        .calculationBasis(this.buildIcmsStBc(nfeIcms10.getBcModalitySt(), this.toBigDecimal(nfeIcms10.getBcValueST()), this.toBigDecimal(nfeIcms10.getValueMarginAddedStPercent())))
                                        .value(this.toBigDecimal(nfeIcms10.getIcmsStValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcms10.getBcReductionStPercent()))
                                    .build())
                            .fcp(this.buildFcpWithBcValue(this.toBigDecimal(nfeIcms10.getBcFcpValue()), this.toBigDecimal(nfeIcms10.getFcpAliquot()), this.toBigDecimal(nfeIcms10.getFcpValue())))
                            .build();
                }
                
                case "ICMS20": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS20 nfeIcms20 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS20 ) nfeIcms;
                    return ICMS20.builder()
                            .origin(Optional.ofNullable(nfeIcms20.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icms(IcmsWithBcReductionPercent.builder()
                                    .value(IcmsWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcms20.getIcmsAliquot()))
                                        .calculationBasis(this.buildIcmsBc(nfeIcms20.getBcModality(), this.toBigDecimal(nfeIcms20.getBcValue()), BigDecimal.ZERO))
                                        .value(this.toBigDecimal(nfeIcms20.getIcmsValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcms20.getBcReductionPercent()))
                                    .build())
                            .fcp(this.buildFcpWithBcValue(this.toBigDecimal(nfeIcms20.getBcFcpValue()), this.toBigDecimal(nfeIcms20.getFcpAliquot()), this.toBigDecimal(nfeIcms20.getFcpValue())))
                            .desoneration(IcmsDesoneration.builder()
                                    .reason(Optional.ofNullable(nfeIcms20.getIcmsDesonerationReason()).map(r -> IcmsDesonerationReason.findByCode(r.getValue())).orElse(null))
                                    .value(this.toBigDecimal(nfeIcms20.getIcmsDesonerationValue()))
                                    .build())
                            .build();
                }
                case "ICMS30": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS30 nfeIcms30 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS30 ) nfeIcms;
                    return ICMS30.builder()
                            .origin(Optional.ofNullable(nfeIcms30.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icmsSt(IcmsStWithBcReductionPercent.builder()
                                    .value(IcmsStWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcms30.getIcmsStAliquot()))
                                        .calculationBasis(this.buildIcmsStBc(nfeIcms30.getBcModalityST(), this.toBigDecimal(nfeIcms30.getBcValueST()), this.toBigDecimal(nfeIcms30.getValueMarginAddedStPercent())))
                                        .value(this.toBigDecimal(nfeIcms30.getIcmsStValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcms30.getBcReductionStPercent()))
                                    .build())
                            .fcpSt(this.buildFcpStWithBcValue(this.toBigDecimal(nfeIcms30.getBcFcpValueST()), this.toBigDecimal(nfeIcms30.getFcpStAliquot()), this.toBigDecimal(nfeIcms30.getFcpStValue())))
                            .desoneration(IcmsDesoneration.builder()
                                    .reason(Optional.ofNullable(nfeIcms30.getIcmsDesonerationReason()).map(r -> IcmsDesonerationReason.findByCode(r.getValue())).orElse(null))
                                    .value(this.toBigDecimal(nfeIcms30.getIcmsDesonerationValue()))
                                    .build())
                            .build();
                }
                case "ICMS40": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS40 nfeIcms40 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS40 ) nfeIcms;
                    return ICMS40.builder()
                            .origin(Optional.ofNullable(nfeIcms40.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .desoneration(IcmsDesoneration.builder()
                                    .reason(Optional.ofNullable(nfeIcms40.getIcmsDesonerationReason()).map(r -> IcmsDesonerationReason.findByCode(r.getValue())).orElse(null))
                                    .value(this.toBigDecimal(nfeIcms40.getIcmsDesonerationValue()))
                                    .build())
                            .build();
                }
                case "ICMS41": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS41 nfeIcms41 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS41 ) nfeIcms;
                    return ICMS41.builder()
                            .origin(Optional.ofNullable(nfeIcms41.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .desoneration(IcmsDesoneration.builder()
                                    .reason(Optional.ofNullable(nfeIcms41.getIcmsDesonerationReason()).map(r -> IcmsDesonerationReason.findByCode(r.getValue())).orElse(null))
                                    .value(this.toBigDecimal(nfeIcms41.getIcmsDesonerationValue()))
                                    .build())
                            .build();
                }
                case "ICMS50": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS50 nfeIcms50 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS50 ) nfeIcms;
                    return ICMS50.builder()
                            .origin(Optional.ofNullable(nfeIcms50.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .desoneration(IcmsDesoneration.builder()
                                    .reason(Optional.ofNullable(nfeIcms50.getIcmsDesonerationReason()).map(r -> IcmsDesonerationReason.findByCode(r.getValue())).orElse(null))
                                    .value(this.toBigDecimal(nfeIcms50.getIcmsDesonerationValue()))
                                    .build())
                            .build();
                }
                case "ICMS51": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS51 nfeIcms51 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS51 ) nfeIcms;
                    return ICMS51.builder()
                            .origin(Optional.ofNullable(nfeIcms51.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icms(IcmsWithBcReductionPercent.builder()
                                    .value(IcmsWithBcValue.builder()
                                            .aliquot(this.toBigDecimal(nfeIcms51.getIcmsAliquot()))
                                            .calculationBasis(this.buildIcmsBc(nfeIcms51.getBcModality(), this.toBigDecimal(nfeIcms51.getBcValue()), BigDecimal.ZERO))
                                            .value(this.toBigDecimal(nfeIcms51.getIcmsValue()))
                                            .build())
                                        .bcReductionPercent(this.toBigDecimal(nfeIcms51.getBcReductionPercent()))
                                        .build())
                            .deferral(IcmsDeferral.builder()
                                    .percent(this.toBigDecimal(nfeIcms51.getDeferralPercent()))
                                    .value(this.toBigDecimal(nfeIcms51.getIcmsDeferralValue()))
                                    .build())
                            .operationValue(this.toBigDecimal(nfeIcms51.getIcmsOperationValue()))
                            .fcp(this.buildFcpWithBcValue(this.toBigDecimal(nfeIcms51.getBcFcpValue()), this.toBigDecimal(nfeIcms51.getFcpAliquot()), this.toBigDecimal(nfeIcms51.getFcpValue())))
                            .build();
                }
                case "ICMS60": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS60 nfeIcms60 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS60 ) nfeIcms;
                    return ICMS60.builder()
                            .origin(Optional.ofNullable(nfeIcms60.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icmsStRetained(IcmsStRetainedValue.builder()
                                    .calculationBasis(this.toBigDecimal(nfeIcms60.getBcRetainedValueST()))
                                    .value(this.toBigDecimal(nfeIcms60.getIcmsRetainedValueST()))
                                    .build())
                            .fcpStRetained(FcpStRetainedValue.builder()
                                    .calculationBasis(this.toBigDecimal(nfeIcms60.getBcFcpRetainedValueST()))
                                    .aliquot(this.toBigDecimal(nfeIcms60.getFcpRetainedAliquotST()))
                                    .value(this.toBigDecimal(nfeIcms60.getFcpRetainedValueST()))
                                    .build())
                            .endConsumerSupportedAliquot(this.toBigDecimal(nfeIcms60.getEndConsumerSupportedAliquot()))
                            .build();
                }
                case "ICMS70": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS70 nfeIcms70 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS70 ) nfeIcms;
                    return ICMS70.builder()
                            .origin(Optional.ofNullable(nfeIcms70.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icms(IcmsWithBcReductionPercent.builder()
                                    .value(IcmsWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcms70.getIcmsAliquot()))
                                        .calculationBasis(this.buildIcmsBc(nfeIcms70.getBcModality(), this.toBigDecimal(nfeIcms70.getBcValue()), BigDecimal.ZERO))
                                        .value(this.toBigDecimal(nfeIcms70.getIcmsValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcms70.getBcReductionPercent()))
                                    .build())
                            .fcp(this.buildFcpWithBcValue(this.toBigDecimal(nfeIcms70.getBcFcpValue()), this.toBigDecimal(nfeIcms70.getFcpAliquot()), this.toBigDecimal(nfeIcms70.getFcpValue())))
                            .icmsSt(IcmsStWithBcReductionPercent.builder()
                                    .value(IcmsStWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcms70.getIcmsStAliquot()))
                                        .calculationBasis(this.buildIcmsStBc(nfeIcms70.getBcModalitySt(), this.toBigDecimal(nfeIcms70.getBcValueST()), this.toBigDecimal(nfeIcms70.getValueMarginAddedStPercent())))
                                        .value(this.toBigDecimal(nfeIcms70.getIcmsStValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcms70.getBcReductionStPercent()))
                                    .build())
                            .fcpSt(this.buildFcpStWithBcValue(this.toBigDecimal(nfeIcms70.getBcFcpValueST()), this.toBigDecimal(nfeIcms70.getFcpStAliquot()), this.toBigDecimal(nfeIcms70.getFcpStValue())))
                            .desoneration(IcmsDesoneration.builder()
                                    .reason(Optional.ofNullable(nfeIcms70.getIcmsDesonerationReason()).map(r -> IcmsDesonerationReason.findByCode(r.getValue())).orElse(null))
                                    .value(this.toBigDecimal(nfeIcms70.getIcmsDesonerationValue()))
                                    .build())
                            .build();
                }
                case "ICMS90": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMS90 nfeIcms90 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMS90 ) nfeIcms;
                    return ICMS90.builder()
                            .origin(Optional.ofNullable(nfeIcms90.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icms(IcmsWithBcReductionPercent.builder()
                                    .value(IcmsWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcms90.getIcmsAliquot()))
                                        .calculationBasis(this.buildIcmsBc(nfeIcms90.getBcModality(), this.toBigDecimal(nfeIcms90.getBcValue()), BigDecimal.ZERO))
                                        .value(this.toBigDecimal(nfeIcms90.getIcmsValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcms90.getBcReductionPercent()))
                                    .build())
                            .fcp(this.buildFcpWithBcValue(this.toBigDecimal(nfeIcms90.getBcFcpValue()), this.toBigDecimal(nfeIcms90.getFcpAliquot()), this.toBigDecimal(nfeIcms90.getFcpValue())))
                            .icmsSt(IcmsStWithBcReductionPercent.builder()
                                    .value(IcmsStWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcms90.getIcmsStAliquot()))
                                        .calculationBasis(this.buildIcmsStBc(nfeIcms90.getBcModalitySt(), this.toBigDecimal(nfeIcms90.getBcValueST()), this.toBigDecimal(nfeIcms90.getValueMarginAddedStPercent())))
                                        .value(this.toBigDecimal(nfeIcms90.getIcmsStValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcms90.getBcReductionStPercent()))
                                    .build())
                            .fcpSt(this.buildFcpStWithBcValue(this.toBigDecimal(nfeIcms90.getBcFcpValueST()), this.toBigDecimal(nfeIcms90.getFcpStAliquot()), this.toBigDecimal(nfeIcms90.getFcpStValue())))
                            .desoneration(IcmsDesoneration.builder()
                                    .reason(Optional.ofNullable(nfeIcms90.getIcmsDesonerationReason()).map(r -> IcmsDesonerationReason.findByCode(r.getValue())).orElse(null))
                                    .value(this.toBigDecimal(nfeIcms90.getIcmsDesonerationValue()))
                                    .build())
                            .build();
                }
                case "ICMSPart10": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSPart10 nfeIcmsPart10 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSPart10) nfeIcms;
                    return ICMSPart10.builder()
                            .origin(Optional.ofNullable(nfeIcmsPart10.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icms(IcmsWithBcReductionPercent.builder()
                                    .value(IcmsWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcmsPart10.getIcmsAliquot()))
                                        .calculationBasis(this.buildIcmsBc(nfeIcmsPart10.getBcModality(), this.toBigDecimal(nfeIcmsPart10.getBcValue()), BigDecimal.ZERO))
                                        .value(this.toBigDecimal(nfeIcmsPart10.getIcmsValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcmsPart10.getBcReductionPercent()))
                                    .build())
                            .icmsSt(IcmsStWithBcReductionPercent.builder()
                                    .value(IcmsStWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcmsPart10.getIcmsStAliquot()))
                                        .calculationBasis(this.buildIcmsStBc(nfeIcmsPart10.getBcModalitySt(), this.toBigDecimal(nfeIcmsPart10.getBcValueST()), this.toBigDecimal(nfeIcmsPart10.getValueMarginAddedStPercent())))
                                        .value(this.toBigDecimal(nfeIcmsPart10.getIcmsStValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcmsPart10.getBcReductionStPercent()))
                                    .build())
                            .selfOperationBcPercent(this.toBigDecimal(nfeIcmsPart10.getSelfOperationBCPerc()))
                            .ufSt(nfeIcmsPart10.getUfST())
                            .build();
                }
                case "ICMSPart90": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSPart90 nfeIcmsPart90 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSPart90) nfeIcms;
                    return ICMSPart90.builder()
                            .origin(Optional.ofNullable(nfeIcmsPart90.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icms(IcmsWithBcReductionPercent.builder()
                                    .value(IcmsWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcmsPart90.getIcmsAliquot()))
                                        .calculationBasis(this.buildIcmsBc(nfeIcmsPart90.getBcModality(), this.toBigDecimal(nfeIcmsPart90.getBcValue()), BigDecimal.ZERO))
                                        .value(this.toBigDecimal(nfeIcmsPart90.getIcmsValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcmsPart90.getBcReductionPercent()))
                                    .build())
                            .icmsSt(IcmsStWithBcReductionPercent.builder()
                                    .value(IcmsStWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcmsPart90.getIcmsStAliquot()))
                                        .calculationBasis(this.buildIcmsStBc(nfeIcmsPart90.getBcModalitySt(), this.toBigDecimal(nfeIcmsPart90.getBcValueST()), this.toBigDecimal(nfeIcmsPart90.getValueMarginAddedStPercent())))
                                        .value(this.toBigDecimal(nfeIcmsPart90.getIcmsStValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcmsPart90.getBcReductionStPercent()))
                                    .build())
                            .selfOperationBcPercent(this.toBigDecimal(nfeIcmsPart90.getSelfOperationBCPerc()))
                            .ufSt(nfeIcmsPart90.getUfST())
                            .build();
                }
                case "ICMSSN101": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN101 nfeIcmsSn101 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN101 ) nfeIcms;
                    return ICMSSN101.builder()
                            .origin(Optional.ofNullable(nfeIcmsSn101.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .creditSn(CreditSnValue.builder()
                                    .aliquot(this.toBigDecimal(nfeIcmsSn101.getCreditSnAliquot()))
                                    .value(this.toBigDecimal(nfeIcmsSn101.getCreditSnIcmsValue()))
                                    .build())
                            .build();
                }
                case "ICMSSN102": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN102 nfeIcmsSn102 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN102 ) nfeIcms;
                    return ICMSSN102.builder()
                            .origin(Optional.ofNullable(nfeIcmsSn102.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .build();
                }
                case "ICMSSN103": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN103 nfeIcmsSn103 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN103 ) nfeIcms;
                    return ICMSSN103.builder()
                            .origin(Optional.ofNullable(nfeIcmsSn103.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .build();
                }
                case "ICMSSN201": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN201 nfeIcmsSn201 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN201 ) nfeIcms;
                    return ICMSSN201.builder()
                            .origin(Optional.ofNullable(nfeIcmsSn201.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icmsSt(IcmsStWithBcReductionPercent.builder()
                                    .value(IcmsStWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcmsSn201.getIcmsStAliquot()))
                                        .calculationBasis(this.buildIcmsStBc(nfeIcmsSn201.getBcModalitySt(), this.toBigDecimal(nfeIcmsSn201.getBcValueST()), this.toBigDecimal(nfeIcmsSn201.getValueMarginAddedStPercent())))
                                        .value(this.toBigDecimal(nfeIcmsSn201.getIcmsStValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcmsSn201.getBcReductionStPercent()))
                                    .build())
                            .fcpSt(this.buildFcpStWithBcValue(this.toBigDecimal(nfeIcmsSn201.getBcFcpValueST()), this.toBigDecimal(nfeIcmsSn201.getFcpStAliquot()), this.toBigDecimal(nfeIcmsSn201.getFcpStValue())))
                            .creditSn(CreditSnValue.builder()
                                    .aliquot(this.toBigDecimal(nfeIcmsSn201.getCreditSnAliquot()))
                                    .value(this.toBigDecimal(nfeIcmsSn201.getCreditSnIcmsValue()))
                                    .build())
                            .build();
                }
                case "ICMSSN202": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN202 nfeIcmsSn202 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN202 ) nfeIcms;
                    return ICMSSN202.builder()
                            .origin(Optional.ofNullable(nfeIcmsSn202.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icmsSt(IcmsStWithBcReductionPercent.builder()
                                    .value(IcmsStWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcmsSn202.getIcmsStAliquot()))
                                        .calculationBasis(this.buildIcmsStBc(nfeIcmsSn202.getBcModalitySt(), this.toBigDecimal(nfeIcmsSn202.getBcValueST()), this.toBigDecimal(nfeIcmsSn202.getValueMarginAddedStPercent())))
                                        .value(this.toBigDecimal(nfeIcmsSn202.getIcmsStValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcmsSn202.getBcReductionStPercent()))
                                    .build())
                            .fcpSt(this.buildFcpStWithBcValue(this.toBigDecimal(nfeIcmsSn202.getBcFcpValueST()), this.toBigDecimal(nfeIcmsSn202.getFcpStAliquot()), this.toBigDecimal(nfeIcmsSn202.getFcpStValue())))
                            .build();
                }
                case "ICMSSN203": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN203 nfeIcmsSn203 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN203 ) nfeIcms;
                    return ICMSSN203.builder()
                            .origin(Optional.ofNullable(nfeIcmsSn203.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icmsSt(IcmsStWithBcReductionPercent.builder()
                                    .value(IcmsStWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcmsSn203.getIcmsStAliquot()))
                                        .calculationBasis(this.buildIcmsStBc(nfeIcmsSn203.getBcModalitySt(), this.toBigDecimal(nfeIcmsSn203.getBcValueST()), this.toBigDecimal(nfeIcmsSn203.getValueMarginAddedStPercent())))
                                        .value(this.toBigDecimal(nfeIcmsSn203.getIcmsStValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcmsSn203.getBcReductionStPercent()))
                                    .build())
                            .fcpSt(this.buildFcpStWithBcValue(this.toBigDecimal(nfeIcmsSn203.getBcFcpValueST()), this.toBigDecimal(nfeIcmsSn203.getFcpStAliquot()), this.toBigDecimal(nfeIcmsSn203.getFcpStValue())))
                            .build();
                }
                case "ICMSSN300": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN300 nfeIcmsSn300 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN300 ) nfeIcms;
                    return ICMSSN300.builder()
                            .origin(Optional.ofNullable(nfeIcmsSn300.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .build();
                }
                case "ICMSSN400": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN400 nfeIcmsSn400 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN400 ) nfeIcms;
                    return ICMSSN400.builder()
                            .origin(Optional.ofNullable(nfeIcmsSn400.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .build();
                }
                case "ICMSSN500": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN500 nfeIcms500 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN500 ) nfeIcms;
                    return ICMSSN500.builder()
                            .origin(Optional.ofNullable(nfeIcms500.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icmsStRetained(IcmsStRetainedValue.builder()
                                    .calculationBasis(this.toBigDecimal(nfeIcms500.getBcRetainedValueST()))
                                    .value(this.toBigDecimal(nfeIcms500.getIcmsRetainedValueST()))
                                    .build())
                            .fcpStRetained(FcpStRetainedValue.builder()
                                    .calculationBasis(this.toBigDecimal(nfeIcms500.getBcFcpRetainedValueST()))
                                    .aliquot(this.toBigDecimal(nfeIcms500.getFcpRetainedAliquotST()))
                                    .value(this.toBigDecimal(nfeIcms500.getFcpRetainedValueST()))
                                    .build())
                            .endConsumerSupportedAliquot(this.toBigDecimal(nfeIcms500.getEndConsumerSupportedAliquot()))
                            .build();
                }
                case "ICMSSN900": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN900 nfeIcmsSn900 = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN900 ) nfeIcms;
                    return ICMSSN900.builder()
                            .origin(Optional.ofNullable(nfeIcmsSn900.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .icms(IcmsWithBcReductionPercent.builder()
                                    .value(IcmsWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcmsSn900.getIcmsAliquot()))
                                        .calculationBasis(this.buildIcmsBc(nfeIcmsSn900.getBcModality(), this.toBigDecimal(nfeIcmsSn900.getBcValue()), BigDecimal.ZERO))
                                        .value(this.toBigDecimal(nfeIcmsSn900.getIcmsValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcmsSn900.getBcReductionPercent()))
                                    .build())
                            .icmsSt(IcmsStWithBcReductionPercent.builder()
                                    .value(IcmsStWithBcValue.builder()
                                        .aliquot(this.toBigDecimal(nfeIcmsSn900.getIcmsStAliquot()))
                                        .calculationBasis(this.buildIcmsStBc(nfeIcmsSn900.getBcModalitySt(), this.toBigDecimal(nfeIcmsSn900.getBcValueST()), this.toBigDecimal(nfeIcmsSn900.getValueMarginAddedStPercent())))
                                        .value(this.toBigDecimal(nfeIcmsSn900.getIcmsStValue()))
                                        .build())
                                    .bcReductionPercent(this.toBigDecimal(nfeIcmsSn900.getBcReductionStPercent()))
                                    .build())
                            .fcpSt(this.buildFcpStWithBcValue(this.toBigDecimal(nfeIcmsSn900.getBcFcpValueST()), this.toBigDecimal(nfeIcmsSn900.getFcpStAliquot()), this.toBigDecimal(nfeIcmsSn900.getFcpStValue())))
                            .creditSn(CreditSnValue.builder()
                                    .aliquot(this.toBigDecimal(nfeIcmsSn900.getCreditSnAliquot()))
                                    .value(this.toBigDecimal(nfeIcmsSn900.getCreditSnIcmsValue()))
                                    .build())
                            .build();
                }
                case "ICMSST": {
                    final eprecise.efiscal4j.nfe.v400.tax.icms.ICMSST nfeIcmsSt = (eprecise.efiscal4j.nfe.v400.tax.icms.ICMSST ) nfeIcms;
                    return ICMSST.builder()
                            .origin(Optional.ofNullable(nfeIcmsSt.getOrigin()).map(po -> ProductOrigin.findByCode(po.getValue())).orElse(null))
                            .retainedSt(IcmsStRetainedValue.builder()
                                    .calculationBasis(this.toBigDecimal(nfeIcmsSt.getBcRetainedValueST()))
                                    .value(this.toBigDecimal(nfeIcmsSt.getIcmsRetainedValueST()))
                                    .build())
                            .destinationSt(IcmsStDestinationValue.builder()
                                    .calculationBasis(this.toBigDecimal(nfeIcmsSt.getBcIcmsStDestination()))
                                    .value(this.toBigDecimal(nfeIcmsSt.getIcmsStDestination()))
                                    .build())
                            .build();
                }
            }
        }
     // @formatter:on
        return null;
    }

    private FcpStWithBcValue buildFcpStWithBcValue(final BigDecimal bc, final BigDecimal aliquot, final BigDecimal value) {
     // @formatter:off
        return FcpStWithBcValue.builder()
                .calculationBasis(bc)
                .value(FcpStValue.builder()
                        .aliquot(aliquot)
                        .value(value)
                        .build())
                .build();
     // @formatter:on
    }

    private FcpWithBcValue buildFcpWithBcValue(final BigDecimal bc, final BigDecimal aliquot, final BigDecimal value) {
     // @formatter:off
        return FcpWithBcValue.builder()
                .calculationBasis(bc)
                .value(FcpValue.builder()
                        .aliquot(aliquot)
                        .value(value)
                        .build())
                .build();
     // @formatter:on
    }

    private IcmsBc buildIcmsBc(final BCModality bcModality, final BigDecimal bcValue, final BigDecimal marginAddedPercent) {
     // @formatter:off
        switch(bcModality) {
            case MARGEM_VALOR_AGREGADO: return IcmsBcMarginAddedValue.builder().calculationBasis(bcValue).marginAddedPercent(marginAddedPercent).build();
            case PAUTA: return IcmsBcDeterminedPautaValue.builder().calculationBasis(bcValue).build();
            case PRECO_TABELADO_MAX: return IcmsBcMaximumTabulatedPrice.builder().calculationBasis(bcValue).build();
            case VALOR_OPERACAO: return IcmsBcOperationValue.builder().calculationBasis(bcValue).build();
        }
        return null;
     // @formatter:on
    }

    private IcmsStBc buildIcmsStBc(final BCModalityST bcModalityST, final BigDecimal bcValue, final BigDecimal marginAddedPercent) {
        // @formatter:off
           switch(bcModalityST) {
               case PRECO_TABELADO_OU_MAX_SUGERIDO: return IcmsStBcMaximumTabulatedOrSuggestedPrice.builder().calculationBasis(bcValue).build();
               case LISTA_NEGATIVA: return IcmsStBcNegativeListValue.builder().calculationBasis(bcValue).build();
               case LISTA_POSITIVA: return IcmsStBcPositiveListValue.builder().calculationBasis(bcValue).build();
               case LISTA_NEUTRA: return IcmsStBcNeutralListValue.builder().calculationBasis(bcValue).build();
               case MARGEM_VALOR_AGREGADO: return IcmsStBcMarginAddedValue.builder().calculationBasis(bcValue).marginAddedPercent(marginAddedPercent).build();
               case PAUTA: return IcmsStBcDeterminedPautaValue.builder().calculationBasis(bcValue).build();
           }
           return null;
        // @formatter:on
    }

    private BigDecimal toBigDecimal(final String value) {
        return Optional.ofNullable(value).map(BigDecimal::new).orElse(null);
    }

    private Long toLong(final String value) {
        return Optional.ofNullable(value).map(Long::parseLong).orElse(null);
    }

}

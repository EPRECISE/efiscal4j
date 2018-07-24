
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocumentType;
import eprecise.efiscal4j.nfe.NFCe;
import eprecise.efiscal4j.nfe.charging.Charging;
import eprecise.efiscal4j.nfe.consumer.Consumer;
import eprecise.efiscal4j.nfe.consumer.SimpleConsumer;
import eprecise.efiscal4j.nfe.emitter.CRT;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddress;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterLegalEntityDocuments;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterNaturalPersonDocuments;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.item.Item.ItemEan;
import eprecise.efiscal4j.nfe.item.Item.ItemGrossValue;
import eprecise.efiscal4j.nfe.item.Item.ItemQuantity;
import eprecise.efiscal4j.nfe.item.Item.ItemUnitaryValue;
import eprecise.efiscal4j.nfe.item.Item.ItemUnity;
import eprecise.efiscal4j.nfe.item.Unity;
import eprecise.efiscal4j.nfe.item.tax.ApproximateTax;
import eprecise.efiscal4j.nfe.item.tax.TaxStructure;
import eprecise.efiscal4j.nfe.item.tax.icms.ICMS00;
import eprecise.efiscal4j.nfe.item.tax.scale.NoRelevantScale;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.receiver.Receiver;
import eprecise.efiscal4j.nfe.receiver.address.BrazillianReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ForeignReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCnpj;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCpf;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverForeignId;
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
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.NfpCnpj;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.NfpCpf;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.ProducerReferencedNFModel;
import eprecise.efiscal4j.nfe.references.ReferenceToNFe;
import eprecise.efiscal4j.nfe.serie.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.total.FiscalDocumentTotal;
import eprecise.efiscal4j.nfe.transport.Transport;
import eprecise.efiscal4j.nfe.transport.conveyor.Conveyor;
import eprecise.efiscal4j.nfe.transport.conveyor.cnp.ConveyorCnpj;
import eprecise.efiscal4j.nfe.transport.conveyor.cnp.ConveyorCpf;
import eprecise.efiscal4j.nfe.transport.mean.FerryTransportMean;
import eprecise.efiscal4j.nfe.transport.mean.VehicleTowingTransportMean;
import eprecise.efiscal4j.nfe.transport.mean.WagonTransportMean;
import eprecise.efiscal4j.nfe.v400.DANFEPrintFormat;
import eprecise.efiscal4j.nfe.v400.DestinationOperationIdentifier;
import eprecise.efiscal4j.nfe.v400.FinalCustomerOperation;
import eprecise.efiscal4j.nfe.v400.ItemValueComprisesTotal;
import eprecise.efiscal4j.nfe.v400.Medications;
import eprecise.efiscal4j.nfe.v400.NFe;
import eprecise.efiscal4j.nfe.v400.NFeDetail;
import eprecise.efiscal4j.nfe.v400.NFeIdentification;
import eprecise.efiscal4j.nfe.v400.NFeInfo;
import eprecise.efiscal4j.nfe.v400.NFeItem;
import eprecise.efiscal4j.nfe.v400.NFeItemScaleIndication;
import eprecise.efiscal4j.nfe.v400.NFeTransmissionMethod;
import eprecise.efiscal4j.nfe.v400.NFeTransmissionProcess;
import eprecise.efiscal4j.nfe.v400.PurchaserPresenceIndicator;
import eprecise.efiscal4j.nfe.v400.StateRegistrationReceiverIndicator;
import eprecise.efiscal4j.nfe.v400.Trace;
import eprecise.efiscal4j.nfe.v400.additionalinfo.AdditionalInfo;
import eprecise.efiscal4j.nfe.v400.additionalinfo.CustomizedObservation;
import eprecise.efiscal4j.nfe.v400.address.Address;
import eprecise.efiscal4j.nfe.v400.address.City;
import eprecise.efiscal4j.nfe.v400.charging.Duplicate;
import eprecise.efiscal4j.nfe.v400.charging.Invoice;
import eprecise.efiscal4j.nfe.v400.charging.NFeCharging;
import eprecise.efiscal4j.nfe.v400.item.di.Addition;
import eprecise.efiscal4j.nfe.v400.item.di.ImportDeclaration;
import eprecise.efiscal4j.nfe.v400.item.di.IntermediaryImportType;
import eprecise.efiscal4j.nfe.v400.item.di.InternationalTransportPathway;
import eprecise.efiscal4j.nfe.v400.nfce.CSC;
import eprecise.efiscal4j.nfe.v400.payment.CardFlag;
import eprecise.efiscal4j.nfe.v400.payment.CardSet;
import eprecise.efiscal4j.nfe.v400.payment.NFePayment;
import eprecise.efiscal4j.nfe.v400.payment.NFePaymentDetail;
import eprecise.efiscal4j.nfe.v400.payment.PaymentIntegrationType;
import eprecise.efiscal4j.nfe.v400.payment.PaymentMethod;
import eprecise.efiscal4j.nfe.v400.person.Emitter;
import eprecise.efiscal4j.nfe.v400.person.Emitter.Builder;
import eprecise.efiscal4j.nfe.v400.refdocuments.ProducerReferencedNF;
import eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedDocuments;
import eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedECF;
import eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedECF.ReferecedECFModel;
import eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedNF;
import eprecise.efiscal4j.nfe.v400.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.v400.sharing.SynchronousProcessing;
import eprecise.efiscal4j.nfe.v400.tax.Tax;
import eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS;
import eprecise.efiscal4j.nfe.v400.tax.cofins.COFINSST;
import eprecise.efiscal4j.nfe.v400.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.v400.tax.icms.ICMSUFReceiver;
import eprecise.efiscal4j.nfe.v400.tax.ii.II;
import eprecise.efiscal4j.nfe.v400.tax.ipi.IPI;
import eprecise.efiscal4j.nfe.v400.tax.pis.PIS;
import eprecise.efiscal4j.nfe.v400.tax.pis.PISST;
import eprecise.efiscal4j.nfe.v400.total.ICMSTotal;
import eprecise.efiscal4j.nfe.v400.total.NFeTotal;
import eprecise.efiscal4j.nfe.v400.transport.NFeTransport;
import eprecise.efiscal4j.nfe.v400.transport.ShippingModality;
import eprecise.efiscal4j.nfe.v400.transport.TransportICMSRetention;
import eprecise.efiscal4j.nfe.v400.transport.TransportedVolume;
import eprecise.efiscal4j.nfe.v400.transport.Vehicle;
import eprecise.efiscal4j.nfe.v400.transport.VolumeSeal;
import eprecise.efiscal4j.nfe.v400.types.NFeDate;
import eprecise.efiscal4j.nfe.version.NFeDispatchAdapterVersion;


public class DispatchFromFiscalDocumentAdapter implements NFeDispatchAdapterVersion {

    private static final String APP_VERSION = "2.0.0";

    private static final DateFormat NFE_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private static final DateFormat NFE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final DateTimeFormatter NFE_YEAR_MONTH_FORMAT = DateTimeFormatter.ofPattern("yyMM");

    private static final DecimalFormat NFE_TWO_DECIMALS_FORMAT = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));

    private static final DecimalFormat NFE_THREE_DECIMALS_FORMAT = new DecimalFormat("##0.000", new DecimalFormatSymbols(Locale.ENGLISH));

    private static final DecimalFormat NFE_FOUR_DECIMALS_FORMAT = new DecimalFormat("##0.0000", new DecimalFormatSymbols(Locale.ENGLISH));

    private static final DecimalFormat NFE_TEN_DECIMALS_FORMAT = new DecimalFormat("##0.0000000000", new DecimalFormatSymbols(Locale.ENGLISH));

    private static final String IBGE_CODE_DEFAULT = "9999999";

    private static final String DEFAULT_OPERATION_TYPE = "VENDA DE MERCADORIAS";

    private static final String COMPLEMENTARY_INFO_DETAIL_VALUE = "Valor aproximado dos tributos: %s";

    private static final String COMPLEMENTARY_INFO_DETAIL_MESSAGE = "LEI DA TRANSPARENCIA";

    private final FiscalDocument fiscalDocument;

    public DispatchFromFiscalDocumentAdapter(final FiscalDocument fiscalDocument) {
        this.fiscalDocument = fiscalDocument;
    }

    @Override
    public NFeDispatch buildNFeDispatch() {
     // @formatter:off
        try {
            return new NFeDispatch.Builder()
                    .withBatchId(this.fiscalDocument.getNumber().toString())
                    .withNFes(this.buildNFes())
                    .withSynchronousProcessing(SynchronousProcessing.SINCRONO)
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
        return null;
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
    	final NFCe nfce = (NFCe) this.fiscalDocument;
    	return new NFe.Builder()
                .withCSC(Optional.ofNullable(nfce.getCsc()).map(csc -> new CSC(csc.getIdentifier(), csc.getCscValue())).orElse(null))
                .withNFeInfo(this.buildNFeInfo())
                .withNFeSuplementaryInfo(null)
                .build(null);
     // @formatter:on
    }

    private NFeInfo buildNFeInfo() throws ParseException {
     //@formatter:off
    	return new NFeInfo.Builder()
    			.withNFeIdentification(this.buildNFeIdentification())
    			.withEmitter(this.buildEmitter())
    			.withReceiver(this.buildReceiver())
//    			.withWithdrawal(this.buildWithDrawal())
//    			.withDelivery(this.buildDelivery())
    			.withNFeDetail(this.buildNFeDetails())
    			.withNFeTotal(this.buildNFeTotal())
    			.withNFeTransport(this.buildNFeTransport())
    			.withNFeCharging(this.buildNFeCharging())
    			.withNFePayment(this.buildNFePayment())
    			.withAdditionalInfo(this.buildAdditionalInfo())
    			.build();   	
        //@formatter:on
    }

    private eprecise.efiscal4j.nfe.v400.person.Receiver buildReceiver() {
      //@formatter:off
        final Consumer consumer = Optional.ofNullable(this.fiscalDocument).filter(eprecise.efiscal4j.nfe.NFe.class::isInstance).map(eprecise.efiscal4j.nfe.NFe.class::cast).map(nfe -> nfe.getReceiver()).map(Consumer.class::cast).orElse(Optional.ofNullable(this.fiscalDocument).filter(NFCe.class::isInstance).map(NFCe.class::cast).map(NFCe::getConsumer).orElse(null));
        
        final eprecise.efiscal4j.nfe.v400.person.Receiver.Builder builder = new eprecise.efiscal4j.nfe.v400.person.Receiver.Builder();
        if(consumer instanceof Receiver) {
            final Receiver receiver = (Receiver) consumer;
            if(receiver.getDocuments().getCnp() instanceof ReceiverCnpj) {
                return builder.asLegalEntity()
                        .withCorporateName(receiver.getDocuments().getName())
                        .withCnpj(receiver.getDocuments().getCnp().getCnp())
                        .withMunicipalRegistration(receiver.getDocuments().getIm())
                        .withStateRegistration(this.buildStateRegistration(receiver))
                        .withStateRegistrationReceiverIndicator(this.buildStateRegistrationReceiverIndicator(receiver))
                        .withAdress(this.buildReceiverAddress(receiver))
                        .withEmail(receiver.getEmail())
                        .build();
            } else if(receiver.getDocuments().getCnp() instanceof ReceiverCpf) {
                return builder.asNaturalPerson()
                        .withName(receiver.getDocuments().getName())
                        .withCpf(receiver.getDocuments().getCnp().getCnp())
                        .withMunicipalRegistration(receiver.getDocuments().getIm())
                        .withStateRegistration(this.buildStateRegistration(receiver))
                        .withStateRegistrationReceiverIndicator(this.buildStateRegistrationReceiverIndicator(receiver))
                        .withAdress(this.buildReceiverAddress(receiver))
                        .withEmail(receiver.getEmail())
                        .build();
            } else if(receiver.getDocuments().getCnp() instanceof ReceiverForeignId) {
                return builder.asForeignPerson()
                        .withCorporateName(receiver.getDocuments().getName())
                        .withForeignId(receiver.getDocuments().getCnp().getCnp())
                        .withMunicipalRegistration(receiver.getDocuments().getIm())
                        .withStateRegistration(this.buildStateRegistration(receiver))
                        .withStateRegistrationReceiverIndicator(this.buildStateRegistrationReceiverIndicator(receiver))
                        .withAdress(this.buildReceiverAddress(receiver))
                        .withEmail(receiver.getEmail())
                        .build();
            }
        } else if(consumer instanceof SimpleConsumer) {
            final SimpleConsumer simpleConsumer = (SimpleConsumer) consumer;
            if(simpleConsumer.getCnp() instanceof ReceiverCnpj) {
                builder.asLegalEntity()
                    .withCnpj(simpleConsumer.getCnp().getCnp()).withCorporateName(simpleConsumer.getName())
                    .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE)
                    .build();
            } else if(simpleConsumer.getCnp() instanceof ReceiverCpf) {
                builder.asNaturalPerson()
                    .withCpf(simpleConsumer.getCnp().getCnp()).withName(simpleConsumer.getName())
                    .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE)
                    .build();
                
            } else if(simpleConsumer.getCnp() instanceof ReceiverForeignId) {
                builder.asForeignPerson()
                    .withForeignId(simpleConsumer.getCnp().getCnp()).withCorporateName(simpleConsumer.getName())
                    .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE)
                    .build();
            }
        }
      //@formatter:on
        return null;
    }

    private String buildStateRegistration(final Receiver receiver) {
        return this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe
                ? Optional.ofNullable(receiver.getDocuments().getIe()).filter(TaxpayerReceiverIE.class::isInstance).map(ReceiverIE::getIe).orElse(null)
                : null;
    }

    private StateRegistrationReceiverIndicator buildStateRegistrationReceiverIndicator(final Receiver receiver) {
        if (receiver.getDocuments().getIe() instanceof TaxpayerReceiverIE) {
            return StateRegistrationReceiverIndicator.CONTRIBUINTE_ICMS;
        } else if (receiver.getDocuments().getIe() instanceof FreeTaxpayerReceiverIE) {
            return StateRegistrationReceiverIndicator.ISENTO;
        } else if (receiver.getDocuments().getIe() instanceof NonTaxpayerReceiverIE) {
            return StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE;
        }
        return null;
    }

    private Address buildReceiverAddress(final Receiver receiver) {
      //@formatter:off
        final BrazillianReceiverAddress address = Optional.ofNullable(receiver.getAddress()).filter(BrazillianReceiverAddress.class::isInstance).map(BrazillianReceiverAddress.class::cast).orElse(null);
        if(address != null) {
            return new Address.Builder()
                    .withStreet(address.getStreet())
                    .withNumber(address.getNumber())
                    .withComplement(address.getComplement())
                    .withDistrict(address.getDistrict())
                    .withCep(address.getCep())
                    .withCity(Optional.ofNullable(address.getCity()).map(c -> new City.Builder()
                            .withIbgeCode(c.getIbgeCode())
                            .withDescription(c.getDescription())
                            .withUF(c.getUf())
                            .build()).orElse(null))
                    .withPhone(receiver.getPhone())
                    .build();
        }
      //@formatter:off
        return null;
    }

    private AdditionalInfo buildAdditionalInfo() {
     //@formatter:off
    	final AdditionalInfo.Builder additionalInfoBuilder = new AdditionalInfo.Builder();
        Optional.ofNullable(this.fiscalDocument.getTotal().getApproximateTaxTotalValue()).map(ApproximateTax::getTotal).filter(t -> t.compareTo(BigDecimal.ZERO) > 0).ifPresent(total -> {
        	 additionalInfoBuilder.withTaxpayerObservations(Arrays.asList(new CustomizedObservation.Builder().withField(COMPLEMENTARY_INFO_DETAIL_MESSAGE).withText(String.format(COMPLEMENTARY_INFO_DETAIL_VALUE, total.toString())).build()));
        });
        Optional.ofNullable(this.fiscalDocument.getDetails()).filter(d->!d.isEmpty()).ifPresent(d->additionalInfoBuilder.withComplementaryInfo(this.formatNFeString(d,5000)));
		return additionalInfoBuilder.build();
	 //@formatter:on
    }

    private NFePayment buildNFePayment() {
     //@formatter:off
		final Payment payment = this.fiscalDocument.getPayment();
		
		if(payment != null) {
			return new NFePayment.Builder()
					.withNFePaymentDetails(this.buildPaymentDetails())
					.withChangeValue(this.formatNFeDecimal1302(payment.getChangeValue()))
					.build();
			}
		return null;
	 //@formatter:on
    }

    private List<NFePaymentDetail> buildPaymentDetails() {
     //@formatter:off
		final Payment payment = this.fiscalDocument.getPayment();
		if((payment.getDetails() != null) && !payment.getDetails().isEmpty()) {
			return payment.getDetails().stream().map(pd -> {
				return new NFePaymentDetail.Builder()
						.withPaymentMethod(Optional.ofNullable(pd.getMethod()).map(pm -> PaymentMethod.findByCode(pm.getValue())).orElse(null))
						.withPaymentValue(this.formatNFeDecimal1302(pd.getValue()))
						.withCardSet(Optional.ofNullable(pd.getCardSet()).map(cs -> {
							return new CardSet.Builder()
									.withPaymentIntegrationType(Optional.ofNullable(cs.getIntegration()).map(csi -> PaymentIntegrationType.findByCode(csi.getValue())).orElse(null))
									.withCnpj(cs.getCnpj())
									.withAuthorizationNumber(cs.getAuthorizationNumber())
									.withCardFlag(Optional.ofNullable(cs.getCardFlag()).map(cf -> CardFlag.findByCode(cf.getValue())).orElse(null))
									.build();
						}).orElse(null))
						.build();
			}).collect(Collectors.toList());
		}
	 //@formatter:on
        return null;
    }

    private NFeCharging buildNFeCharging() {
        //@formatter:off
		final Charging charging = this.fiscalDocument.getCharging();
		
		if(charging != null) {
			
			return new NFeCharging.Builder()
					.withInvoice(Optional.ofNullable(charging.getInvoice()).map(inv -> {
						return new Invoice.Builder()
								.withNumber(inv.getNumber())
								.withOriginalValue(this.formatNFeDecimal1302Optional(inv.getOriginalValue()))
								.withDiscountValue(this.formatNFeDecimal1302Optional(inv.getDiscountValue()))
								.withNetValue(this.formatNFeDecimal1302Optional(inv.getNetValue()))
								.build();
					}).orElse(null))
					.withDuplicates(Optional.ofNullable(charging.getDuplicates()).map(dupList -> dupList.stream().map(dup -> {
						return new Duplicate.Builder()
								.withNumber(dup.getNumber())
								.withDueDate(Optional.ofNullable(dup.getDue()).map(NFE_DATE_FORMAT::format).orElse(null))
								.withValue(this.formatNFeDecimal1302Optional(dup.getValue()))
								.build();
					}).collect(Collectors.toList())).orElse(null))
					.build();
		}
		 //@formatter:on
        return null;
    }

    private NFeTransport buildNFeTransport() {
        //@formatter:off
		final Transport transport = this.fiscalDocument.getTransport();
		if(transport != null) {
				final NFeTransport.Builder builder = new NFeTransport.Builder()
					.withShippingModality(Optional.ofNullable(transport.getShippingModality()).map(sm -> ShippingModality.findByCode(sm.getValue())).orElse(null))
					.withConveyor(this.buildConveyor())
					.withtransportICMSRetention(Optional.ofNullable(transport.getIcmsRetention()).map(ir -> {
						return new TransportICMSRetention.Builder()
								.withServiceValue(this.formatNFeDecimal1302(ir.getServiceValue()))
								.withRetentionCalculationBasis(this.formatNFeDecimal1302(ir.getRetentionCalculationBasis()))
								.withRetentionAliquot(this.formatNFeDecimal0302a04(ir.getRetentionAliquot()))
								.withRetentionValue(this.formatNFeDecimal1302(ir.getRetentionValue()))
								.withCfop(ir.getCfop())
								.withGenFactIbgeCode(ir.getGenFactIbgeCode())
								.build();
					}).orElse(null))
					.withTransportedVolume(Optional.ofNullable(transport.getVolumes()).map(tvList -> tvList.stream().map(tv -> {
						return new TransportedVolume.Builder()
								.withVolumeQuantity(tv.getVolumeQuantity().toString())
								.withVolumeSpecies(tv.getVolumeSpecies())
								.withVolumeTrademark(tv.getVolumeTrademark())
								.withVolumeNumbering(tv.getVolumeNumbering())
								.withNetWeight(this.formatNFeDecimal1203(tv.getNetWeight()))
								.withGrossWeight(this.formatNFeDecimal1203(tv.getGrossWeight()))
								.withSeals(Optional.ofNullable(tv.getSeals()).map(sealsList -> sealsList.stream().map(s -> {
									return new VolumeSeal.Builder()
											.withSealNumber(s.getSealNumber())
											.build();
								}).collect(Collectors.toList())).orElse(null))
								.build();
					}).collect(Collectors.toList())).orElse(null));
				if(transport.getTransportMean() instanceof VehicleTowingTransportMean) {
					final VehicleTowingTransportMean vehicleTowingTransportMean = (VehicleTowingTransportMean) transport.getTransportMean();
					builder.withVehicle(this.buildVehicle(vehicleTowingTransportMean.getVehicle()));
					builder.withTowing(Optional.ofNullable(vehicleTowingTransportMean.getTowing()).map(towingList -> towingList.stream().map(this::buildVehicle).collect(Collectors.toList())).orElse(null));
				} else if(transport.getTransportMean() instanceof FerryTransportMean) {
					final FerryTransportMean ferryTransportMean = (FerryTransportMean) transport.getTransportMean();
					builder.withFerry(ferryTransportMean.getIdentifier());
				} else if(transport.getTransportMean() instanceof WagonTransportMean) {
					final WagonTransportMean wagonTransportMean = (WagonTransportMean) transport.getTransportMean();
					builder.withWagon(wagonTransportMean.getIdentifier());
				}
				return builder.build();
		}
		 //@formatter:on
        return null;
    }

    private Vehicle buildVehicle(final eprecise.efiscal4j.nfe.transport.Vehicle vehicle) {
        //@formatter:off
		if(vehicle != null) {
			return new Vehicle.Builder()
					.withLicensePlate(vehicle.getLicensePlate())
					.withUF(vehicle.getUf())
					.withRntc(vehicle.getRntc())
					.build();
		}
		//@formatter:on
        return null;
    }

    private eprecise.efiscal4j.nfe.v400.transport.Conveyor buildConveyor() {
        //@formatter:off
		final Conveyor conveyor = Optional.ofNullable(this.fiscalDocument.getTransport()).map(Transport::getConveyor).orElse(null);
		
		if(conveyor != null) {
			if(conveyor.getCnp() instanceof ConveyorCnpj) {
				return new eprecise.efiscal4j.nfe.v400.transport.Conveyor.Builder().asLegalEntity()
					.withCnpj(conveyor.getCnp().getCnp())
					.withCorporateName(conveyor.getName())
					.withStateRegistration(conveyor.getIe())
					.withFullAddress(conveyor.getFullAddress())
					.withCity(Optional.ofNullable(conveyor.getCityName()).map(cityName -> new City.Builder().withDescription(cityName).build()).orElse(null)).build();
			} else if(conveyor.getCnp() instanceof ConveyorCpf) {
				return new eprecise.efiscal4j.nfe.v400.transport.Conveyor.Builder().asNaturalPerson()
					.withCpf(conveyor.getCnp().getCnp())
					.withName(conveyor.getName())
					.withStateRegistration(conveyor.getIe())
					.withFullAddress(conveyor.getFullAddress())
					.withCity(Optional.ofNullable(conveyor.getCityName()).map(cityName -> new City.Builder().withDescription(cityName).build()).orElse(null)).build();
			}
		}
		
		//@formatter:on
        return null;
    }

    private NFeTotal buildNFeTotal() {
        //@formatter:off
        final FiscalDocumentTotal fiscalDocumentTotal = this.fiscalDocument.getTotal();
        if(fiscalDocumentTotal != null) {
            return new NFeTotal.Builder()
                    .withICMSTotal(new ICMSTotal.Builder()
                            .withDiscountTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getDiscountTotalValue()))
                            .withICMSCalculationBasis(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsBcValue()))
                            .withICMSSTCalculationBasis(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsStBcValue()))
                            .withICMSSTTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsStValue()))
                            .withICMSTotalDesoneration(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsDesonerationValue()))
                            .withICMSTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsValue()))
                            .withInsuranceTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getInsuranceTotalValue()))
                            .withItemsTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTaxableGrossTotalValue()))
                            .withNFeTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getFiscalDocumentTotalValue()))
                            .withOtherIncidentalCostsTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getOthersTotalValue()))
                            .withShippingTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getShippingTotalValue()))
                            .withTaxTotalValue(this.fiscalDocument.isEndConsumer() ? this.formatNFeDecimal1302(fiscalDocumentTotal.getApproximateTaxTotalValue().getTotal()) : null)
                            .withReceiverUfFCPTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalICMSUFReceiverFcpValue()))
                            .withReceiverUfIcmsShareTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsUfReceiverShareValue()))
                            .withEmitterUfIcmsShareTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsUfReceiverEmitterShareValue()))
                            .build())
                    .build();
        }
        //@formatter:on
        return null;
    }

    private NFeIdentification buildNFeIdentification() {
     //@formatter:off
        final NFeIdentification.Builder builder = new NFeIdentification.Builder();
        
        builder
            .withApplicationVersion(DispatchFromFiscalDocumentAdapter.APP_VERSION)
            .withDanfePrintFormat(this.buildDANFEPrintFormat())
            .withDestinationOperationIdentifier(this.buildDestinationOperationIdentifier())
            .withEmissionDateTime(DispatchFromFiscalDocumentAdapter.NFE_DATETIME_FORMAT.format(this.fiscalDocument.getEmission().getDate()))
            .withFinalCustomerOperation(this.fiscalDocument.isEndConsumer() ? FinalCustomerOperation.CONSUMIDOR_FINAL : FinalCustomerOperation.NAO)
            .withFiscalDocumentModel(this.fiscalDocument.getModel())
            .withFiscalDocumentSeries(this.fiscalDocument.getSerie().getNumber().toString())
            .withFiscalDocumentNumber(this.fiscalDocument.getNumber().toString())
            .withFiscalDocumentType(this.buildFiscalDocumentType())
            .withNFeCode(this.fiscalDocument.getCode())
            .withNFeFinality(this.buildNFeFinality())
            .withNFeTransmissionMethod(NFeTransmissionMethod.NORMAL)
            .withNFeTransmissionProcess(NFeTransmissionProcess.APLICATIVO_CONTRIBUINTE)
            .withOperationType(this.buildOperationTypeDescriptor())
            .withPurchaserPresenceIndicator(PurchaserPresenceIndicator.OPERACAO_PRESENCIAL)
            .withTaxableEventCityIbgeCode(Optional.ofNullable(this.fiscalDocument.getEmitter().getAddress()).map(ba -> ba.getCity().getIbgeCode().toString()).orElse(DispatchFromFiscalDocumentAdapter.IBGE_CODE_DEFAULT)) //TODO Revisar
            .withTransmissionEnvironment(this.buildTransmissionEnvironment())
            .withUFIbgeCode(Optional.ofNullable(this.fiscalDocument.getEmitter().getAddress()).map(ba -> UF.findByAcronym(ba.getCity().getUf().getAcronym())).orElse(UF.EX))
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

    private DestinationOperationIdentifier buildDestinationOperationIdentifier() {
      //@formatter:off
        final Receiver receiver = Optional.ofNullable(this.fiscalDocument).filter(eprecise.efiscal4j.nfe.NFe.class::isInstance).map(eprecise.efiscal4j.nfe.NFe.class::cast).map(nfe -> nfe.getReceiver()).orElse(Optional.ofNullable(this.fiscalDocument).filter(NFCe.class::isInstance).map(NFCe.class::cast).map(NFCe::getConsumer).filter(Receiver.class::isInstance).map(Receiver.class::cast).orElse(null));
        
        if ((receiver == null) || ((receiver != null) && !receiver.getAddress().isValid())) {
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
      //@formatter:on
    }

    public eprecise.efiscal4j.nfe.v400.FiscalDocumentType buildFiscalDocumentType() {
        if ((this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe) && FiscalDocumentType.IN.equals(((eprecise.efiscal4j.nfe.NFe) this.fiscalDocument).getType())) {
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
            throw new IllegalArgumentException(ref + " não é um tipo válido");
        }
    }

    public eprecise.efiscal4j.nfe.v400.refdocuments.ProducerReferencedNF.ProducerReferencedNFModel buildProducerReferencedNFModel(final ProducerReferencedNFModel model) {
        if (ProducerReferencedNFModel.PRODUCER_NF.equals(model)) {
            return eprecise.efiscal4j.nfe.v400.refdocuments.ProducerReferencedNF.ProducerReferencedNFModel.PRODUCER_NF;
        }
        return eprecise.efiscal4j.nfe.v400.refdocuments.ProducerReferencedNF.ProducerReferencedNFModel.SPARE_NF;
    }

    public ReferecedECFModel buildReferecedECFModel(final ReferencedECFModel model) {
        if (ReferencedECFModel.NON_ECF.equals(model)) {
            return ReferecedECFModel.NAO_ECF;
        } else if (ReferencedECFModel.ECF_PDV.equals(model)) {
            return ReferecedECFModel.ECF_PDV;
        }
        return ReferecedECFModel.ECF;
    }

    private Emitter buildEmitter() {
        final eprecise.efiscal4j.nfe.emitter.Emitter emitter = this.fiscalDocument.getEmitter();
        final Builder builder = new Emitter.Builder();
        return emitter.getDocuments() instanceof EmitterLegalEntityDocuments ? this.buildEmitterLegalEntity(builder, emitter) : this.buildEmitterNaturalPerson(builder, emitter);
    }

    private Emitter buildEmitterLegalEntity(final Builder builder, final eprecise.efiscal4j.nfe.emitter.Emitter emitter) {
     // @formatter:off
        final EmitterLegalEntityDocuments docs = (EmitterLegalEntityDocuments) emitter.getDocuments();
        return builder
                .asLegalEntity()
                .withCnpj(docs.getCnp())
                .withCorporateName(this.formatNFeString(docs.getName(), 60))
                .withCrt(this.buildCrt(emitter.getCrt()))
                .withStateRegistration(this.nullIfEmpty(docs.getIe()))
                .withStateRegistrationST(Optional.ofNullable(docs.getIeSt()).filter(s->!s.isEmpty()).orElse(null))
                .withMunicipalRegistration(this.nullIfEmpty(docs.getMunicipalDocuments().getIm()))
                .withAdress(this.buildEmitterAddress(emitter.getAddress(), Optional.ofNullable(emitter.getPhone())))
                .withFancyName(this.formatNFeString(docs.getFancyName(), 60))
                .build();
     //@formatter:on
    }

    private Address buildEmitterAddress(final EmitterAddress address, final Optional<String> phone) {
     // @formatter:off
        if(address != null) {
            return new Address.Builder()
                    .withStreet(this.formatNFeString(address.getStreet(), 60))
                    .withNumber(this.formatNFeString(address.getNumber(), 60))
                    .withComplement(this.formatNFeString(address.getComplement(), 60))
                    .withDistrict(this.formatNFeString(address.getDistrict(), 60))
                    .withCep(this.formatNFeString(address.getCep(), 8))
                    .withCity(Optional.ofNullable(address.getCity()).map(c -> new City.Builder()
                            .withIbgeCode(c.getIbgeCode())
                            .withDescription(c.getDescription())
                            .withUF(c.getUf())
                            .build()).orElse(null))
                    .withPhone(phone.orElse(null))
                    .build();
        }
        return null;
     // @formatter:on
    }

    private Emitter buildEmitterNaturalPerson(final Builder builder, final eprecise.efiscal4j.nfe.emitter.Emitter emitter) {
     // @formatter:off
        final EmitterNaturalPersonDocuments docs = (EmitterNaturalPersonDocuments) emitter.getDocuments();
        return builder
                .asNaturalPerson()
                .withCpf(emitter.getDocuments().getCnp())
                .withName(this.formatNFeString(docs.getName(), 60))
                .withStateRegistration(this.nullIfEmpty(docs.getIe()))
                .withMunicipalRegistration(this.nullIfEmpty(docs.getMunicipalDocuments().getIm()))
                .withAdress(this.buildEmitterAddress(emitter.getAddress(), Optional.ofNullable(emitter.getPhone())))
                .build();
     //@formatter:on
    }

    private String formatNFeString(final String input, final int size) {
        return Optional.ofNullable(StringUtils.upperCase(StringUtils.stripAccents(StringUtils.abbreviate(this.nullIfEmpty(input), size)))).map(string -> {
            return string.replaceAll("\n", "  ").replaceAll("\r", "  ").replace("\t", "  ");
        }).orElse(null);
    }

    private eprecise.efiscal4j.nfe.v400.CRT buildCrt(final CRT crt) {
        if (CRT.SIMPLE_NATIONAL.equals(crt)) {
            return eprecise.efiscal4j.nfe.v400.CRT.SIMPLES_NACIONAL;
        } else if (CRT.SIMPLE_NATIONAL_WITH_SUBLIME_EXCESS.equals(crt)) {
            return eprecise.efiscal4j.nfe.v400.CRT.SIMPLES_NACIONAL_EXCESSO_SUBLIMITE;
        }
        return eprecise.efiscal4j.nfe.v400.CRT.REGIME_NORMAL;
    }

    private List<NFeDetail> buildNFeDetails() {
        return this.fiscalDocument.getItems().stream().map(this::buildNFeDetail).collect(Collectors.toList());
    }

    private NFeDetail buildNFeDetail(final Item item) {
     // @formatter:off
        return new NFeDetail.Builder()
                .withItemOrder(Optional.ofNullable(this.fiscalDocument.getItemOrder(item)).map(Object::toString).orElse(null))
                .withNFeItem(this.buildNFeItem(item))
                .withTax(this.buildTax(item))
                .withReturnedTax(null) //TODO
                .build();
     // @formatter:on
    }

    private NFeItem buildNFeItem(final Item item) {
     // @formatter:off
        return new NFeItem.Builder()
                .withItemCode(this.formatNFeString(item.getCode(), 60))
                .withGlobalTradeItemNumber(Optional.ofNullable(item.getGlobalTradeItemNumber()).map(ItemEan::getGlobalTradeItemNumber).orElse(null))
                .withItemDescription(this.formatNFeString(item.getName(), 120))
                .withNCM(Optional.ofNullable(item.getTaxStructure()).map(TaxStructure::getNcm).orElse(null))
                .withCest(Optional.ofNullable(item.getTaxStructure()).map(TaxStructure::getCest).orElse(null))
                .withScaleIndication(Optional.ofNullable(item.getTaxStructure()).map(TaxStructure::getScaleIndication).map(si -> Optional.ofNullable(si).filter(NoRelevantScale.class::isInstance).map(wr -> NFeItemScaleIndication.NAO).orElse(NFeItemScaleIndication.SIM)).orElse(null))
                .withManufacturerCnpj(Optional.ofNullable(item.getTaxStructure()).map(TaxStructure::getScaleIndication).filter(NoRelevantScale.class::isInstance).map(NoRelevantScale.class::cast).map(NoRelevantScale::getManufacturerCnpj).orElse(null))
                .withBeneficiaryCode(null) //TODO
                .withExTipi(null) //TODO
                .withCFOP(Optional.ofNullable(item.getTaxStructure()).map(TaxStructure::getCfop).orElse(null))
                .withComercialUnit(Optional.ofNullable(item.getUnity()).map(ItemUnity::getComercialUnity).map(Unity::getAcronym).orElse(null))
                .withComercialQuantity(Optional.ofNullable(item.getQuantity()).map(ItemQuantity::getComercialQuantity).map(this::formatNFeDecimal1104Variable).orElse(null))
                .withComercialUnitaryValue(Optional.ofNullable(item.getUnitaryValue()).map(ItemUnitaryValue::getComercialUnitaryValue).map(this::formatNFeDecimal1110Variable).orElse(null))
                .withItemGrossValue(Optional.ofNullable(item.getGrossValue()).map(ItemGrossValue::getComercialGrossValue).map(this::formatNFeDecimal1110Variable).orElse(null))
                .withTaxableUnitGlobalTradeItemNumber(Optional.ofNullable(item.getGlobalTradeItemNumber()).map(ItemEan::getTaxableGlobalTradeItemNumber).orElse(null))
                .withTaxableUnit(Optional.ofNullable(item.getUnity()).map(ItemUnity::getTaxableUnity).map(Unity::getAcronym).orElse(null))
                .withTaxableQuantity(Optional.ofNullable(item.getQuantity()).map(ItemQuantity::getTaxableQuantity).map(this::formatNFeDecimal1104Variable).orElse(null))
                .withTaxationUnitaryValue(Optional.ofNullable(item.getUnitaryValue()).map(ItemUnitaryValue::getTaxableUnitaryValue).map(this::formatNFeDecimal1110Variable).orElse(null))
                .withFreightValue(this.formatNFeDecimal1302Optional(item.getFreight()))
                .withInsuranceValue(this.formatNFeDecimal1302Optional(item.getInsurance()))
                .withDiscountValue(this.formatNFeDecimal1302Optional(item.getDiscount()))
                .withOthersValue(this.formatNFeDecimal1302Optional(item.getOthersValue()))
                .withItemValueComprisesTotal(ItemValueComprisesTotal.COMPOE_TOTAL)
                .withMedications(this.buildMedications(item))
                .withGuns(null) //TODO
                .withFuel(null) //TODO
                .withImportDeclarations(this.buildImportDeclarations(item))
                .withPurchaseOrderDescription(null) //TODO
                .withPurchaseOrderNumber(null) // TODO
                .withFciNumber(null) //TODO
                .withTraces(this.buildTraces(item))
                .build();
     // @formatter:on
    }

    private List<Trace> buildTraces(final Item item) {
     // @formatter:off
        if((item.getTraces() != null) && !item.getTraces().isEmpty()) {
            return item.getTraces().stream().map(t -> {
                return new Trace.Builder()
                        .withBatchNumber(this.formatNFeString(t.getBatchNumber(), 20))
                        .withBatchQuantity(this.formatNFeDecimal0803Variable(t.getBatchQuantity()))
                        .withManufacturingDate(Optional.ofNullable(t.getManufacturing()).map(NFeDate.dateFormat::format).orElse(null))
                        .withExpirationDate(Optional.ofNullable(t.getExpiration()).map(NFeDate.dateFormat::format).orElse(null))
                        .withAggregationCode(t.getAggregationCode())
                        .build();
            }).collect(Collectors.toList());
        }
     // @formatter:on
        return null;
    }

    private List<ImportDeclaration> buildImportDeclarations(final Item item) {
     // @formatter:off
        final Collection<eprecise.efiscal4j.nfe.item.di.ImportDeclaration> importDeclarations = item.getImportDeclarations();
        if((importDeclarations != null) && !importDeclarations.isEmpty()) {
            return importDeclarations.stream().map(id -> {
                return new ImportDeclaration.Builder()
                        .withNumber(id.getNumber())
                        .withDate(Optional.ofNullable(id.getDate()).map(NFeDate.dateFormat::format).orElse(null))
                        .withClearanceSpot(id.getClearanceSpot())
                        .withClearanceDate(Optional.ofNullable(id.getClearanceDate()).map(NFeDate.dateFormat::format).orElse(null))
                        .withInternationalTransportPathway(Optional.ofNullable(id.getInternationalTransportPathway()).map(itpw -> InternationalTransportPathway.findByCode(itpw.getValue())).orElse(null))
                        .withAdditValShipMerchMarineRenovation(this.formatNFeDecimal1302(id.getAdditValShipMerchMarineRenovation()))
                        .withIntermediaryImportType(Optional.ofNullable(id.getIntermediaryImportType()).map(iit -> IntermediaryImportType.findByCode(iit.getValue())).orElse(null))
                        .withAcquirerOrOrderingPartyCnpj(id.getAcquirerOrOrderingPartyCnpj())
                        .withAcquirerOrOrderingPartyUf(Optional.ofNullable(id.getAcquirerOrOrderingPartyUf()).orElse(null))
                        .withExporterCode(id.getExporterCode())
                        .withAdditions(Optional.ofNullable(id.getAdditions()).map(additions -> additions.stream().map(addition -> {
                            return new Addition.Builder()
                                    .withNumber(Optional.ofNullable(addition.getNumber()).map(Object::toString).orElse(null))
                                    .withSequence(Optional.ofNullable(addition.getSequence()).map(Object::toString).orElse(null))
                                    .withManufacturerCode(this.formatNFeString(addition.getManufacturerCode(), 60))
                                    .withDiscountValue(this.formatNFeDecimal1302Optional(addition.getDiscountValue()))
                                    .withDrawbackNumber(addition.getDrawbackNumber())
                                    .build();
                        }).collect(Collectors.toList())).orElse(null))
                        .build();
            }).collect(Collectors.toList());
        }
        return null;
     // @formatter:on
    }

    private Medications buildMedications(final Item item) {
     // @formatter:off
        final eprecise.efiscal4j.nfe.item.medications.Medications medications = item.getMedications();
        if(medications != null) {
            return new Medications.Builder()
                    .withAnvisaProductCode(medications.getAnvisaProductCode())
                    .withMaxPriceConsumers(this.formatNFeDecimal1302(medications.getMaxPriceConsumers()))
                    .build();
        }
        return null;
     // @formatter:on
    }

    private Tax buildTax(final Item item) {
     // @formatter:off
        final TaxStructure taxStructure = item.getTaxStructure();
        if(taxStructure != null) {
            return new Tax.Builder()
                    .withTaxTotalValue(Optional.ofNullable(taxStructure).map(TaxStructure::getApproximateTax).map(ApproximateTax::getTotal).map(this::formatNFeDecimal1302).orElse(null))
                    .withIcms(this.buildIcms(taxStructure))
                    .withIpi(this.buildIpi(taxStructure))
                    .withIi(this.buildIi(taxStructure))
                    .withPis(this.buildPis(taxStructure))
                    .withPisSt(this.buildPisSt(taxStructure))
                    .withCofins(this.buildCofins(taxStructure))
                    .withCofinsSt(this.buildCofinsSt(taxStructure))
                    .withIcmsUfReceiver(this.buildIcmsUfReceiver(taxStructure))
                    .build();
            }
        return null;
     // @formatter:on
    }

    private ICMSUFReceiver buildIcmsUfReceiver(final TaxStructure taxStructure) {
        // TODO Auto-generated method stub
        return null;
    }

    private COFINSST buildCofinsSt(final TaxStructure taxStructure) {
        // TODO Auto-generated method stub
        return null;
    }

    private PISST buildPisSt(final TaxStructure taxStructure) {
        // TODO Auto-generated method stub
        return null;
    }

    private COFINS buildCofins(final TaxStructure taxStructure) {
        // TODO Auto-generated method stub
        return null;
    }

    private PIS buildPis(final TaxStructure taxStructure) {
        // TODO Auto-generated method stub
        return null;
    }

    private II buildIi(final TaxStructure taxStructure) {
        // TODO Auto-generated method stub
        return null;
    }

    private IPI buildIpi(final TaxStructure taxStructure) {
        // TODO Auto-generated method stub
        return null;
    }

    private ICMS buildIcms(final TaxStructure taxStructure) {
     // @formatter:off
        final eprecise.efiscal4j.nfe.item.tax.icms.ICMS icms = taxStructure.getTaxes().stream().filter(eprecise.efiscal4j.nfe.item.tax.icms.ICMS.class::isInstance).map(eprecise.efiscal4j.nfe.item.tax.icms.ICMS.class::cast).findFirst().orElse(null);
        if(icms != null) {
            switch(icms.getCst()) {
            
            case CST_00 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS00 icms00 = (ICMS00) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS00.Builder()
                        
                        .build();
            }
            default:
                break;
            }
        }
        return null;
     // @formatter:on
    }

    private String nullIfEmpty(final String v) {
        return StringUtils.isEmpty(v) ? null : v;
    }

    private String formatNFeDecimal1302(final BigDecimal value) {
        if (value == null) {
            return null;
        } else if ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0)) {
            return "0";
        } else {
            return NFE_TWO_DECIMALS_FORMAT.format(value);
        }

    }

    private String formatNFeDecimal0302a04(final BigDecimal value) {
        if (value == null) {
            return null;
        } else if ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0)) {
            return "0";
        } else {
            return NFE_FOUR_DECIMALS_FORMAT.format(value);
        }

    }

    private String formatNFeDecimal1302Optional(final BigDecimal value) {
        if ((value == null) || ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0))) {
            return null;
        } else {
            return NFE_TWO_DECIMALS_FORMAT.format(value);
        }

    }

    private String formatNFeDecimal0302a04Optional(final BigDecimal value) {
        if ((value == null) || ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0))) {
            return null;
        } else {
            return NFE_FOUR_DECIMALS_FORMAT.format(value);
        }

    }

    private String formatNFeDecimal1203(final BigDecimal value) {
        if (value == null) {
            return null;
        } else if ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0)) {
            return "0";
        } else {
            return NFE_THREE_DECIMALS_FORMAT.format(value);
        }
    }

    private String formatNFeDecimal1204Variable(final BigDecimal value) {
        if (value == null) {
            return null;
        } else if ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0)) {
            return "0";
        } else {
            return NFE_FOUR_DECIMALS_FORMAT.format(value);
        }
    }

    private String formatNFeDecimal1204(final BigDecimal value) {
        if (value == null) {
            return null;
        } else if ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0)) {
            return "0";
        } else {
            return NFE_FOUR_DECIMALS_FORMAT.format(value);
        }
    }

    private String formatNFeDecimal0302a04Max100(final BigDecimal value) {
        if ((value == null) || ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0))) {
            return null;
        } else {
            return NFE_FOUR_DECIMALS_FORMAT.format(value);
        }
    }

    private String formatNFeDecimal1104Variable(final BigDecimal value) {
        if (value == null) {
            return null;
        } else if ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0)) {
            return "0";
        } else {
            return NFE_FOUR_DECIMALS_FORMAT.format(value);
        }
    }

    private String formatNFeDecimal1110Variable(final BigDecimal value) {
        if (value == null) {
            return null;
        } else if ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0)) {
            return "0";
        } else {
            return NFE_TEN_DECIMALS_FORMAT.format(value);
        }
    }

    private String formatNFeDecimal0803Variable(final BigDecimal value) {
        if (value == null) {
            return null;
        } else if ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0)) {
            return "0";
        } else {
            return NFE_THREE_DECIMALS_FORMAT.format(value);
        }
    }

}


package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocumentType;
import eprecise.efiscal4j.nfe.NFCe;
import eprecise.efiscal4j.nfe.PresenceIndicator;
import eprecise.efiscal4j.nfe.broker.BrokerIndicator;
import eprecise.efiscal4j.nfe.broker.WithBrokerOperation;
import eprecise.efiscal4j.nfe.broker.WithoutBrokerOperation;
import eprecise.efiscal4j.nfe.charging.Charging;
import eprecise.efiscal4j.nfe.cnpAccessXml.CnpAccessXml;
import eprecise.efiscal4j.nfe.cnpAccessXml.CnpjAccessXml;
import eprecise.efiscal4j.nfe.cnpAccessXml.CpfAccessXml;
import eprecise.efiscal4j.nfe.consumer.Consumer;
import eprecise.efiscal4j.nfe.consumer.SimpleConsumer;
import eprecise.efiscal4j.nfe.emitter.CRT;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddress;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddressCity;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterLegalEntityDocuments;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterMunicipalDocuments;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterNaturalPersonDocuments;
import eprecise.efiscal4j.nfe.entranceOrExitDate.IODate;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.item.Item.*;
import eprecise.efiscal4j.nfe.item.Unity;
import eprecise.efiscal4j.nfe.item.export.ItemExportDetail;
import eprecise.efiscal4j.nfe.item.tax.ApproximateTax;
import eprecise.efiscal4j.nfe.item.tax.TaxStructure;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.CofinsAliquotPercentWithBc;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.CofinsAliquotValueWithQuantity;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquot;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquotPercent;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValueWithAliquotValue;
import eprecise.efiscal4j.nfe.item.tax.icms.deferral.IcmsDeferral;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpValue;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.singlephase.IcmsMonoRetWithValue;
import eprecise.efiscal4j.nfe.item.tax.icms.sn.credit.CreditSnValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.retained.FcpStRetainedValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc.*;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.destination.IcmsStDestinationValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.retained.IcmsStRetainedValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcReductionPercent;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.bc.*;
import eprecise.efiscal4j.nfe.item.tax.ipi.generalData.IPIGeneralData;
import eprecise.efiscal4j.nfe.item.tax.ipi.value.IpiValue;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.PisAliquotPercentWithBc;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.PisAliquotValueWithQuantity;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValueWithAliquot;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValueWithAliquotPercent;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValueWithAliquotValue;
import eprecise.efiscal4j.nfe.item.tax.scale.NoRelevantScale;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.receiver.Receiver;
import eprecise.efiscal4j.nfe.receiver.address.BrazillianReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ForeignReceiverAddress;
import eprecise.efiscal4j.nfe.receiver.address.ReceiverAddressCity;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCnpj;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverCpf;
import eprecise.efiscal4j.nfe.receiver.documents.cnp.ReceiverForeignId;
import eprecise.efiscal4j.nfe.receiver.documents.ie.FreeTaxpayerReceiverIE;
import eprecise.efiscal4j.nfe.receiver.documents.ie.NonTaxpayerReceiverIE;
import eprecise.efiscal4j.nfe.receiver.documents.ie.ReceiverIE;
import eprecise.efiscal4j.nfe.receiver.documents.ie.TaxpayerReceiverIE;
import eprecise.efiscal4j.nfe.references.*;
import eprecise.efiscal4j.nfe.references.ReferenceToECF.ReferencedECFModel;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.NfpCnpj;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.NfpCpf;
import eprecise.efiscal4j.nfe.references.ReferenceToNFP.ProducerReferencedNFModel;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.serie.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.technicalManager.CSRT;
import eprecise.efiscal4j.nfe.technicalManager.TechnicalManager;
import eprecise.efiscal4j.nfe.total.FiscalDocumentTotal;
import eprecise.efiscal4j.nfe.transport.Transport;
import eprecise.efiscal4j.nfe.transport.conveyor.Conveyor;
import eprecise.efiscal4j.nfe.transport.conveyor.cnp.ConveyorCnpj;
import eprecise.efiscal4j.nfe.transport.conveyor.cnp.ConveyorCpf;
import eprecise.efiscal4j.nfe.transport.mean.FerryTransportMean;
import eprecise.efiscal4j.nfe.transport.mean.VehicleTowingTransportMean;
import eprecise.efiscal4j.nfe.transport.mean.WagonTransportMean;
import eprecise.efiscal4j.nfe.transport.places.address.PlaceAddress;
import eprecise.efiscal4j.nfe.v400.*;
import eprecise.efiscal4j.nfe.v400.additionalinfo.AdditionalInfo;
import eprecise.efiscal4j.nfe.v400.additionalinfo.CustomizedObservation;
import eprecise.efiscal4j.nfe.v400.address.Address;
import eprecise.efiscal4j.nfe.v400.address.City;
import eprecise.efiscal4j.nfe.v400.address.Country;
import eprecise.efiscal4j.nfe.v400.autXml.NFeAutXml;
import eprecise.efiscal4j.nfe.v400.autXml.NFeAutXmlCnpj;
import eprecise.efiscal4j.nfe.v400.autXml.NFeAutXmlCpf;
import eprecise.efiscal4j.nfe.v400.charging.Duplicate;
import eprecise.efiscal4j.nfe.v400.charging.Invoice;
import eprecise.efiscal4j.nfe.v400.charging.NFeCharging;
import eprecise.efiscal4j.nfe.v400.export.NFeExport;
import eprecise.efiscal4j.nfe.v400.fuel.Fuel;
import eprecise.efiscal4j.nfe.v400.fuel.FuelCide;
import eprecise.efiscal4j.nfe.v400.fuel.FuelClosing;
import eprecise.efiscal4j.nfe.v400.fuel.FuelOrigin;
import eprecise.efiscal4j.nfe.v400.item.di.Addition;
import eprecise.efiscal4j.nfe.v400.item.di.ImportDeclaration;
import eprecise.efiscal4j.nfe.v400.item.di.IntermediaryImportType;
import eprecise.efiscal4j.nfe.v400.item.di.InternationalTransportPathway;
import eprecise.efiscal4j.nfe.v400.item.export.NFeItemExportDetail;
import eprecise.efiscal4j.nfe.v400.item.export.NFeItemIndirectExport;
import eprecise.efiscal4j.nfe.v400.nfce.CSC;
import eprecise.efiscal4j.nfe.v400.payment.*;
import eprecise.efiscal4j.nfe.v400.person.Emitter;
import eprecise.efiscal4j.nfe.v400.person.Emitter.Builder;
import eprecise.efiscal4j.nfe.v400.places.Place;
import eprecise.efiscal4j.nfe.v400.places.PlaceCnp;
import eprecise.efiscal4j.nfe.v400.places.PlaceCnpj;
import eprecise.efiscal4j.nfe.v400.places.PlaceCpf;
import eprecise.efiscal4j.nfe.v400.refdocuments.*;
import eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedECF.ReferecedECFModel;
import eprecise.efiscal4j.nfe.v400.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.v400.sharing.SynchronousProcessing;
import eprecise.efiscal4j.nfe.v400.tax.Tax;
import eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS;
import eprecise.efiscal4j.nfe.v400.tax.cofins.COFINSST;
import eprecise.efiscal4j.nfe.v400.tax.icms.*;
import eprecise.efiscal4j.nfe.v400.tax.icms.desoneration.ICMSDesonerationReason;
import eprecise.efiscal4j.nfe.v400.tax.ii.II;
import eprecise.efiscal4j.nfe.v400.tax.ipi.IPI;
import eprecise.efiscal4j.nfe.v400.tax.pis.PIS;
import eprecise.efiscal4j.nfe.v400.tax.pis.PISST;
import eprecise.efiscal4j.nfe.v400.technicalManager.NFeTechnicalManager;
import eprecise.efiscal4j.nfe.v400.total.ICMSTotal;
import eprecise.efiscal4j.nfe.v400.total.NFeTotal;
import eprecise.efiscal4j.nfe.v400.transport.*;
import eprecise.efiscal4j.nfe.v400.types.NFeDate;
import eprecise.efiscal4j.nfe.version.NFeDispatchAdapterVersion;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DispatchFromFiscalDocumentAdapter implements NFeDispatchAdapterVersion {

    private static final String RECEIVER_NAME_NFE_HOMOLOGATION = "NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL";

    private static final String ITEM_DESCRIPTION_NFCE_HOMOLOGATION = "NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL";

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

    private static final String COMPLEMENTARY_INFO_DIFAL_DETAIL_MESSAGE = "DIFAL";

    private static final String COMPLEMENTARY_INFO_DIFAL_DETAIL_VALUE = "DIFAL da Operacao: %s%%";

    private static final String COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_EMITTER_DETAIL_MESSAGE = "PARTILHA UF ORIGEM";

    private static final String COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_EMITTER_DETAIL_VALUE = "%s (%s%%): R$ %s.";

    private static final String COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_RECEIVER_DETAIL_MESSAGE = "PARTILHA UF DESTINO";

    private static final String COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_RECEIVER_DETAIL_VALUE = "%s (%s%%): R$ %s.";

    private static final String COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_FCP_DETAIL_MESSAGE = "FUNDO COMB POBREZA";

    private static final String COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_FCP_DETAIL_VALUE = "FCP: R$ %s.";

    private static final Collection<UF> UFS_ONLY_ASYNC = Stream.of(UF.BA, UF.SP).collect(Collectors.toSet());

    private static final String BA_DEFAULT_CNPJ_AUT_XML = "13937073000156";

    private static final String STRING_VALID_PATTERN = "[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}";

    private static final String LINE_BREAK_REPLACEMENT = "  ";

    private final FiscalDocument fiscalDocument;

    private final Certificate certificate;

    public DispatchFromFiscalDocumentAdapter(
            final FiscalDocument fiscalDocument,
            final Certificate certificate
    ) {
        this.fiscalDocument = fiscalDocument;
        this.certificate = certificate;
    }

    @Override
    public NFeDispatch buildNFeDispatch() {
     // @formatter:off
        try {
            return new NFeDispatch.Builder()
                    .withBatchId(this.fiscalDocument.getNumber().toString())
                    .withNFes(this.buildNFes())
                    .withSynchronousProcessing(this.buildSynchronousProcessing())
                    .build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
     // @formatter:on
    }

    private SynchronousProcessing buildSynchronousProcessing() {
        final UF emitterUf = this.getEmitterUf();
        if((this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe) && UFS_ONLY_ASYNC.contains(emitterUf)) {
            return SynchronousProcessing.ASSINCRONO;
        }
        return SynchronousProcessing.SINCRONO;
    }

    private List<NFe> buildNFes() throws Exception {
        if (this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe) {
            return Collections.singletonList(this.buildNFe());
        }

        if (this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFCe) {
            return Collections.singletonList(this.buildNFCe());
        }

        return Collections.emptyList();
    }

    private NFe buildNFe() throws Exception {
     // @formatter:off
        return new NFe.Builder()
                .withCSC(null)
                .withNFeInfo(this.buildNFeInfo())
//                .withNFeSuplementaryInfo(null)
                .build(new DefaultSigner(this.certificate));
     // @formatter:on
    }

    private NFe buildNFCe() throws Exception {
     // @formatter:off
        final NFCe nfce = (NFCe) this.fiscalDocument;
        return new NFe.Builder()
                .withCSC(Optional.ofNullable(nfce.getCsc()).map(csc -> new CSC(csc.getIdentifier(), csc.getCscValue())).orElse(null))
                .withNFeInfo(this.buildNFeInfo())
//                .withNFeSuplementaryInfo(null)
                .build(new DefaultSigner(this.certificate));
     // @formatter:on
    }

    private NFeInfo buildNFeInfo() throws ParseException {
     //@formatter:off
        return new NFeInfo.Builder()
                .withId(this.buildNFeId())
                .withNFeIdentification(this.buildNFeIdentification())
                .withEmitter(this.buildEmitter())
                .withReceiver(this.buildReceiver())
                .withAutXml(this.buildAuthXml())
                .withWithdrawal(this.buildWithdrawal())
                .withDelivery(this.buildDelivery())
                .withNFeDetail(this.buildNFeDetails())
                .withNFeTotal(this.buildNFeTotal())
                .withNFeTransport(this.buildNFeTransport())
                .withNFeCharging(this.buildNFeCharging())
                .withNFePayment(this.buildNFePayment())
                .withBrokerInfo(this.buildBrokerIndicatorInfo())
                .withAdditionalInfo(this.buildAdditionalInfo())
                .withNFeExport(this.buildNFeExport())
                .withTechnicalManager(this.buildNFeTechnicalManager())
                .build();
        //@formatter:on
    }

    private List<NFeAutXml> buildAuthXml() {
        final UF emitterUf = this.getEmitterUf();
        final List<NFeAutXml> listNfeAutXml = new ArrayList<>();
        final Optional<Collection<CnpAccessXml>> cnpAccessXmls = Optional.ofNullable(this.fiscalDocument.getCnpAccessXmls());

        cnpAccessXmls.ifPresent(x -> x.forEach(cnp -> {
            if (cnp instanceof CpfAccessXml) {
                listNfeAutXml.add(new NFeAutXmlCpf(cnp.getCnp()));
            } else if (cnp instanceof CnpjAccessXml) {
                listNfeAutXml.add(new NFeAutXmlCnpj(cnp.getCnp()));
            }
        }));
        if (emitterUf.equals(UF.BA) && listNfeAutXml.isEmpty()) {
            return Arrays.asList(new NFeAutXmlCnpj(BA_DEFAULT_CNPJ_AUT_XML));
        }
        return listNfeAutXml;
    }

    private NFeTechnicalManager buildNFeTechnicalManager() {
        final TechnicalManager technicalManager = this.fiscalDocument.getTechnicalManager();
        if (technicalManager != null) {
            return new NFeTechnicalManager.Builder().withCnpj(technicalManager.getCnpj()).withContactName(technicalManager.getContactName()).withEmail(technicalManager.getEmail())
                    .withPhone(technicalManager.getPhone()).withCsrtId(Optional.ofNullable(technicalManager.getCsrt()).map(CSRT::getId).orElse(null))
                    .withCsrtHash(Optional.ofNullable(technicalManager.getCsrt()).map(csrt -> this.buildCsrtHash(csrt, this.buildAccessKey())).orElse(null)).build();
        }
        return null;
    }

    private NFeExport buildNFeExport() {
        return Optional.ofNullable(this.fiscalDocument).filter(eprecise.efiscal4j.nfe.NFe.class::isInstance).map(eprecise.efiscal4j.nfe.NFe.class::cast).map(eprecise.efiscal4j.nfe.NFe::getExport)
                .map(export -> new NFeExport.Builder().withUfExitCountry(export.getUfExitCountry()).withExportLocation(this.formatNFeString(export.getExportLocation(), 60))
                        .withDispatchLocation(this.formatNFeString(export.getDispatchLocation(), 60)).build())
                .orElse(null);
    }

    public String buildCsrtHash(final CSRT csrt, final String accessKey) {
        if (csrt != null) {
            return csrt.getHash(accessKey);
        }

        return null;
    }

    private String buildNFeId() {
        final StringBuilder accessKey = new StringBuilder(this.buildAccessKey());
        accessKey.append(this.buildChecksum(accessKey.toString()));
        accessKey.insert(0, "NFe");
        return accessKey.toString();
    }

    /**
     * Gera a chave de acesso da NF-e, que é composto por:
     *
     * <ul>
     * <li>UF - Código da UF do emitente do Documento Fiscal</li>
     * <li>AAMM - Ano e Mês de emissão da NF-e</li>
     * <li>CNPJ - CNPJ do emitente</li>
     * <li>mod - Modelo do Documento Fiscal</li>
     * <li>serie - Série do Documento Fiscal</li>
     * <li>nNF - Número do Documento Fiscal</li>
     * <li>tpEmis - forma de emissão da NF-e</li>
     * <li>cNF - Código Numérico que compõe a Chave de Acesso</li>
     * <li>cDV - Dígito Verificador da Chave de Acesso</li>
     * </ul>
     *
     * @param builder
     * @throws ParseException
     */
    private String buildAccessKey() {
        final StringBuilder accessKey = new StringBuilder();
        final Date emissionDate = this.fiscalDocument.getEmission().getDate();

        accessKey.append(Optional.ofNullable(this.fiscalDocument.getEmitter().getAddress()).map(ba -> UF.findByAcronym(ba.getCity().getUf().getAcronym())).orElse(UF.EX).getIbgeCode());
        accessKey.append(new SimpleDateFormat("yy").format(emissionDate));
        accessKey.append(new SimpleDateFormat("MM").format(emissionDate));
        accessKey.append(this.fiscalDocument.getEmitter().getDocuments().getCnp());
        accessKey.append(this.fiscalDocument.getModel().getValue());
        accessKey.append(new DecimalFormat("000").format(this.fiscalDocument.getSerie().getNumber()));
        accessKey.append(new DecimalFormat("000000000").format(this.fiscalDocument.getNumber()));
        accessKey.append(NFeTransmissionMethod.NORMAL.getValue());
        accessKey.append(this.fiscalDocument.getCode());

        return accessKey.toString();

    }

    private int buildChecksum(final String key) {
        int total = 0;
        int weight = 2;

        for (int i = 0; i < key.length(); i++) {
            total += (key.charAt((key.length() - 1) - i) - '0') * weight;
            weight++;
            if (weight == 10) {
                weight = 2;
            }
        }

        final int remainder = total % 11;
        return ((remainder == 0) || (remainder == 1)) ? 0 : (11 - remainder);
    }

    private Place buildDelivery() {
        return Optional.ofNullable(this.fiscalDocument).filter(eprecise.efiscal4j.nfe.NFe.class::isInstance).map(eprecise.efiscal4j.nfe.NFe.class::cast).map(eprecise.efiscal4j.nfe.NFe::getTransport)
                .map(eprecise.efiscal4j.nfe.transport.Transport::getDelivery).map(this::buildPlace).orElse(null);
    }

    private Place buildWithdrawal() {
        return Optional.ofNullable(this.fiscalDocument).filter(eprecise.efiscal4j.nfe.NFe.class::isInstance).map(eprecise.efiscal4j.nfe.NFe.class::cast).map(eprecise.efiscal4j.nfe.NFe::getTransport)
                .map(eprecise.efiscal4j.nfe.transport.Transport::getWithdrawal).map(this::buildPlace).orElse(null);
    }

    private Place buildPlace(final eprecise.efiscal4j.nfe.transport.places.Place place) {
        if (place != null) {
            return new Place.Builder().withCnp(this.buildPlaceCnp(place.getCnp()))
                    .withStreet(Optional.ofNullable(place.getAddress()).map(address -> this.formatNFeString(address.getStreet(), 60)).orElse(null))
                    .withNumber(Optional.ofNullable(place.getAddress()).map(address -> this.formatNFeString(address.getNumber(), 60)).orElse(null))
                    .withComplement(Optional.ofNullable(place.getAddress()).map(address -> this.formatNFeString(address.getComplement(), 60)).orElse(null))
                    .withDistrict(Optional.ofNullable(place.getAddress()).map(address -> this.formatNFeString(address.getDistrict(), 60)).orElse(null))
                    .withCity(Optional.ofNullable(place.getAddress()).map(PlaceAddress::getCity)
                            .map(c -> new City.Builder().withIbgeCode(c.getIbgeCode()).withDescription(this.formatNFeString(c.getDescription(), 60)).withUF(c.getUf()).build()).orElse(null))
                    .build();
        }
        return null;
    }

    private PlaceCnp buildPlaceCnp(final eprecise.efiscal4j.nfe.transport.places.cnp.PlaceCnp cnp) {
        if (cnp instanceof eprecise.efiscal4j.nfe.transport.places.cnp.PlaceCnpj) {
            return new PlaceCnpj.Builder().withCnpj(cnp.getCnp()).build();
        } else if (cnp instanceof eprecise.efiscal4j.nfe.transport.places.cnp.PlaceCpf) {
            return new PlaceCpf.Builder().withCpf(cnp.getCnp()).build();
        }
        return null;
    }

    private eprecise.efiscal4j.nfe.v400.person.Receiver buildReceiver() {
      //@formatter:off
        final Consumer consumer = Optional.ofNullable(this.fiscalDocument).filter(eprecise.efiscal4j.nfe.NFe.class::isInstance).map(eprecise.efiscal4j.nfe.NFe.class::cast).map(eprecise.efiscal4j.nfe.NFe::getReceiver).map(Consumer.class::cast).orElse(Optional.ofNullable(this.fiscalDocument).filter(NFCe.class::isInstance).map(NFCe.class::cast).map(NFCe::getConsumer).orElse(null));

        final eprecise.efiscal4j.nfe.v400.person.Receiver.Builder builder = new eprecise.efiscal4j.nfe.v400.person.Receiver.Builder();
        if(consumer instanceof Receiver) {
            final Receiver receiver = (Receiver) consumer;
            if(receiver.getDocuments().getCnp() instanceof ReceiverCnpj) {
                return builder.asLegalEntity()
                        .withCorporateName(this.buildReceiverName(receiver))
                        .withCnpj(receiver.getDocuments().getCnp().getCnp())
                        .withMunicipalRegistration(receiver.getDocuments().getIm())
                        .withStateRegistration(this.buildStateRegistration(receiver))
                        .withStateRegistrationReceiverIndicator(this.buildStateRegistrationReceiverIndicator(receiver))
                        .withAdress(this.buildReceiverAddress(receiver))
                        .withEmail(receiver.getEmail())
                        .build();
            } else if(receiver.getDocuments().getCnp() instanceof ReceiverCpf) {
                return builder.asNaturalPerson()
                        .withName(this.buildReceiverName(receiver))
                        .withCpf(receiver.getDocuments().getCnp().getCnp())
                        .withMunicipalRegistration(receiver.getDocuments().getIm())
                        .withStateRegistration(this.buildStateRegistration(receiver))
                        .withStateRegistrationReceiverIndicator(this.buildStateRegistrationReceiverIndicator(receiver))
                        .withAdress(this.buildReceiverAddress(receiver))
                        .withEmail(receiver.getEmail())
                        .build();
            } else if(receiver.getDocuments().getCnp() instanceof ReceiverForeignId) {
                return builder.asForeignPerson()
                        .withCorporateName(this.buildReceiverName(receiver))
                        .withForeignId(receiver.getDocuments().getCnp().getCnp())
                        .withStateRegistrationReceiverIndicator(this.buildStateRegistrationReceiverIndicator(receiver))
                        .withAdress(this.buildReceiverAddress(receiver))
                        .withEmail(receiver.getEmail())
                        .build();
            }
        } else if(consumer instanceof SimpleConsumer) {
            final SimpleConsumer simpleConsumer = (SimpleConsumer) consumer;
            if(simpleConsumer.getCnp() instanceof ReceiverCnpj) {
                return builder.asLegalEntity()
                    .withCnpj(simpleConsumer.getCnp().getCnp()).withCorporateName(simpleConsumer.getName())
                    .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE)
                    .build();
            } else if(simpleConsumer.getCnp() instanceof ReceiverCpf) {
                return builder.asNaturalPerson()
                    .withCpf(simpleConsumer.getCnp().getCnp()).withName(simpleConsumer.getName())
                    .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE)
                    .build();

            } else if(simpleConsumer.getCnp() instanceof ReceiverForeignId) {
                return builder.asForeignPerson()
                    .withForeignId(simpleConsumer.getCnp().getCnp()).withCorporateName(simpleConsumer.getName())
                    .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE)
                    .build();
            }
        }
      //@formatter:on
        return null;
    }

    private String buildReceiverName(final Receiver receiver) {
        if (TransmissionEnvironment.HOMOLOGATION.equals(this.fiscalDocument.getSerie().getEnvironment())) {
            return RECEIVER_NAME_NFE_HOMOLOGATION;
        }
        return this.formatNFeString(receiver.getDocuments().getName(), 60);
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
        if(receiver.getAddress() instanceof BrazillianReceiverAddress) {
            final BrazillianReceiverAddress address = (BrazillianReceiverAddress) receiver.getAddress();
            return new Address.Builder()
                    .withStreet(this.formatNFeString(address.getStreet(),60))
                    .withNumber(this.formatNFeString(address.getNumber(),60))
                    .withComplement(this.formatNFeString(address.getComplement(),60))
                    .withDistrict(this.formatNFeString(address.getDistrict(),60))
                    .withCep(address.getCep())
                    .withCity(Optional.ofNullable(address.getCity()).map(c -> new City.Builder()
                            .withIbgeCode(c.getIbgeCode())
                            .withDescription(this.formatNFeString(c.getDescription(), 60))
                            .withUF(c.getUf())
                            .build()).orElse(null))
                    .withPhone(receiver.getPhone())
                    .build();
        } else if(receiver.getAddress() instanceof ForeignReceiverAddress) {
            final ForeignReceiverAddress address = (ForeignReceiverAddress) receiver.getAddress();
            return new Address.Builder()
                    .withStreet("EXTERIOR")
                    .withNumber("S/N")
                    .withDistrict("EXTERIOR")
                    .withCity(new City.Builder()
                            .withIbgeCode("9999999")
                            .withDescription("EXTERIOR")
                            .withUF(UF.EX)
                            .withCountry(new Country.Builder()
                                    .withIbgeCode(address.getCountry().getValue())
                                    .withDescription(address.getCountry().getDescription())
                                    .build())
                            .build()).build();
        }
      //@formatter:off
        return null;
    }

    private AdditionalInfo buildAdditionalInfo() {
     //@formatter:off
        final AdditionalInfo.Builder additionalInfoBuilder = new AdditionalInfo.Builder();
        final List<CustomizedObservation> observations = new ArrayList<>();
        Optional.ofNullable(this.fiscalDocument.getTotal().getApproximateTaxTotalValue()).map(ApproximateTax::getTotal).filter(t -> t.compareTo(BigDecimal.ZERO) > 0).ifPresent(total -> {
            observations.add(new CustomizedObservation.Builder().withField(COMPLEMENTARY_INFO_DETAIL_MESSAGE).withText(String.format(COMPLEMENTARY_INFO_DETAIL_VALUE, total.toString())).build());
        });
        this.fiscalDocument.getItems().stream().findFirst().ifPresent(item->{
            item.getTaxStructure().getTaxes().stream().filter(eprecise.efiscal4j.nfe.item.tax.ufReceiverIcms.ICMSUFReceiver.class::isInstance).map(eprecise.efiscal4j.nfe.item.tax.ufReceiverIcms.ICMSUFReceiver.class::cast).findAny().ifPresent(icmsUfReceiver->{
                final BigDecimal difal = icmsUfReceiver.getDifal();
                final BigDecimal receiverSharePercentual = icmsUfReceiver.getSharePercentual();
                final BigDecimal emitterSharePercentual = receiverSharePercentual != null ? new BigDecimal(100).subtract(receiverSharePercentual) : null;
                final BigDecimal emitterValue = this.fiscalDocument.getTotal().getTotalTaxes().getTotalIcmsUfReceiverEmitterShareValue();
                final BigDecimal receiverValue = this.fiscalDocument.getTotal().getTotalTaxes().getTotalIcmsUfReceiverShareValue();
                final UF emitterUf = this.getEmitterUf();
                final UF receiverUf = this.getReceiverUf();
                final BigDecimal fcpValue = this.fiscalDocument.getTotal().getTotalTaxes().getTotalICMSUFReceiverFcpValue();

                if((difal != null) && !difal.equals(BigDecimal.ZERO)) {
                    observations.add(new CustomizedObservation.Builder()
                                .withField(COMPLEMENTARY_INFO_DIFAL_DETAIL_MESSAGE)
                                .withText(String.format(COMPLEMENTARY_INFO_DIFAL_DETAIL_VALUE, difal.toString()))
                                .build());
                }

                if((emitterSharePercentual != null) && (emitterValue != null) && (emitterUf != null) && !emitterValue.equals(BigDecimal.ZERO)) {
                    observations.add(new CustomizedObservation.Builder()
                                .withField(COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_EMITTER_DETAIL_MESSAGE)
                                .withText(String.format(COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_EMITTER_DETAIL_VALUE, emitterUf.getDescription(), emitterSharePercentual.toString(), emitterValue.toString()))
                                .build());
                }

                if((receiverSharePercentual != null) && (receiverValue != null) && (receiverUf != null) && !receiverValue.equals(BigDecimal.ZERO)) {
                    observations.add(new CustomizedObservation.Builder()
                                .withField(COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_RECEIVER_DETAIL_MESSAGE)
                                .withText(String.format(COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_RECEIVER_DETAIL_VALUE, receiverUf.getDescription(), receiverSharePercentual.toString(), receiverValue.toString()))
                                .build());
                }

                if((fcpValue != null) && !fcpValue.equals(BigDecimal.ZERO)) {
                    observations.add(new CustomizedObservation.Builder()
                            .withField(COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_FCP_DETAIL_MESSAGE)
                            .withText(String.format(COMPLEMENTARY_INFO_ICMS_INTERSTATE_OPERATIONS_FCP_DETAIL_VALUE, fcpValue.toString()))
                            .build());
                }

            });
        });
        additionalInfoBuilder.withTaxpayerObservations(observations);
        Optional.ofNullable(this.fiscalDocument.getDetails()).filter(d->!d.isEmpty()).ifPresent(d->additionalInfoBuilder.withComplementaryInfo(this.formatNFeString(d,5000)));
        return additionalInfoBuilder.build();
     //@formatter:on
    }

    private UF getReceiverUf() {
        return Optional.ofNullable(this.fiscalDocument).filter(eprecise.efiscal4j.nfe.NFe.class::isInstance).map(eprecise.efiscal4j.nfe.NFe.class::cast).map(eprecise.efiscal4j.nfe.NFe::getReceiver)
                .map(Receiver::getAddress).filter(BrazillianReceiverAddress.class::isInstance).map(BrazillianReceiverAddress.class::cast).map(BrazillianReceiverAddress::getCity)
                .map(ReceiverAddressCity::getUf).orElse(null);
    }

    private UF getEmitterUf() {
        return this.fiscalDocument.getEmitter().getAddress().getCity().getUf();
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
            return payment.getDetails().stream().map(pd -> new NFePaymentDetail.Builder()
                    .withPaymentMethod(Optional.ofNullable(pd.getMethod()).map(pm -> PaymentMethod.findByCode(pm.getValue())).orElse(null))
                    .withPaymentMethodDescription(Optional.ofNullable(pd.getMethod()).filter(eprecise.efiscal4j.nfe.payment.PaymentMethod.OUTROS::equals).map(eprecise.efiscal4j.nfe.payment.PaymentMethod::getDescription).orElse(null))
                    .withPaymentValue(this.formatNFeDecimal1302(pd.getValue()))
                    .withCardSet(Optional.ofNullable(pd.getCardSet()).map(cs -> new CardSet.Builder()
                            .withPaymentIntegrationType(Optional.ofNullable(cs.getIntegration()).map(csi -> PaymentIntegrationType.findByCode(csi.getValue())).orElse(null))
                            .withCnpj(cs.getCnpj())
                            .withAuthorizationNumber(cs.getAuthorizationNumber())
                            .withCardFlag(Optional.ofNullable(cs.getCardFlag()).map(cf -> CardFlag.findByCode(cf.getValue())).orElse(null))
                            .build()).orElse(null))
                    .build()).collect(Collectors.toList());
        }
     //@formatter:on
        return null;
    }

    private NFeCharging buildNFeCharging() {
        //@formatter:off

        if(this.fiscalDocument instanceof eprecise.efiscal4j.nfe.NFe) {

            final Charging charging = this.fiscalDocument.getCharging();

            if(charging != null) {

                return new NFeCharging.Builder()
                        .withInvoice(Optional.ofNullable(charging.getInvoice()).map(inv -> new Invoice.Builder()
                                .withNumber(inv.getNumber())
                                .withOriginalValue(this.formatNFeDecimal1302(inv.getOriginalValue()))
                                .withDiscountValue(this.formatNFeDecimal1302(inv.getDiscountValue()))
                                .withNetValue(this.formatNFeDecimal1302(inv.getNetValue()))
                                .build()).orElse(null))
                        .withDuplicates(Optional.ofNullable(charging.getDuplicates()).map(dupList -> dupList.stream().map(dup -> new Duplicate.Builder()
                                .withNumber(Optional.ofNullable(dup.getNumber()).map(this::nullIfEmpty).map(n -> StringUtils.leftPad(n, 3, "0")).orElse(null))
                                .withDueDate(Optional.ofNullable(dup.getDue()).map(NFE_DATE_FORMAT::format).orElse(null))
                                .withValue(this.formatNFeDecimal1302Optional(dup.getValue()))
                                .build()).collect(Collectors.toList())).orElse(null))
                        .build();
            }
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
                    .withtransportICMSRetention(Optional.ofNullable(transport.getIcmsRetention()).map(ir -> new TransportICMSRetention.Builder()
                            .withServiceValue(this.formatNFeDecimal1302(ir.getServiceValue()))
                            .withRetentionCalculationBasis(this.formatNFeDecimal1302(ir.getRetentionCalculationBasis()))
                            .withRetentionAliquot(this.formatNFeDecimal0302a04(ir.getRetentionAliquot()))
                            .withRetentionValue(this.formatNFeDecimal1302(ir.getRetentionValue()))
                            .withCfop(ir.getCfop())
                            .withGenFactIbgeCode(ir.getGenFactIbgeCode())
                            .build()).orElse(null))
                    .withTransportedVolume(Optional.ofNullable(transport.getVolumes()).map(tvList -> tvList.stream().map(tv -> new TransportedVolume.Builder()
                            .withVolumeQuantity(Optional.ofNullable(tv.getVolumeQuantity()).map(vq->vq.toString()).orElse(null))
                            .withVolumeSpecies(tv.getVolumeSpecies())
                            .withVolumeTrademark(tv.getVolumeTrademark())
                            .withVolumeNumbering(tv.getVolumeNumbering())
                            .withNetWeight(this.formatNFeDecimal1203(tv.getNetWeight()))
                            .withGrossWeight(this.formatNFeDecimal1203(tv.getGrossWeight()))
                            .withSeals(Optional.ofNullable(tv.getSeals()).map(sealsList -> sealsList.stream().map(s -> new VolumeSeal.Builder()
                                    .withSealNumber(s.getSealNumber())
                                    .build()).collect(Collectors.toList())).orElse(null))
                            .build()).collect(Collectors.toList())).orElse(null));
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
                    .withCorporateName(this.formatNFeString(conveyor.getName(),60))
                    .withStateRegistration(conveyor.getIe())
                    .withFullAddress(this.formatNFeString(conveyor.getFullAddress() , 60))
                    .withCity(Optional.ofNullable(conveyor.getCityName()).map(cityName -> new City.Builder()
                            .withDescription(this.formatNFeString(cityName, 60))
                            .withUF(conveyor.getUf())
                            .build()).orElse(null)).build();
            } else if(conveyor.getCnp() instanceof ConveyorCpf) {
                return new eprecise.efiscal4j.nfe.v400.transport.Conveyor.Builder().asNaturalPerson()
                    .withCpf(conveyor.getCnp().getCnp())
                    .withName(this.formatNFeString(conveyor.getName(), 60))
                    .withStateRegistration(conveyor.getIe())
                    .withFullAddress(this.formatNFeString(conveyor.getFullAddress(), 60))
                    .withCity(Optional.ofNullable(conveyor.getCityName()).map(cityName -> new City.Builder()
                            .withDescription(this.formatNFeString(cityName,60))
                            .withUF(conveyor.getUf())
                            .build()).orElse(null)).build();
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
                            .withICMSCalculationBasis(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsBcValue()))
                            .withICMSTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsValue()))
                            .withICMSTotalDesoneration(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsDesonerationValue()))
                            .withReceiverUfFCPTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalICMSUFReceiverFcpValue()))
                            .withReceiverUfIcmsShareTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsUfReceiverShareValue()))
                            .withEmitterUfIcmsShareTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsUfReceiverEmitterShareValue()))
                            .withFcpTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalFcpValue()))
                            .withICMSSTCalculationBasis(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsStBcValue()))
                            .withICMSSTTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIcmsStValue()))
                            .withFcpStTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalFcpStValue()))
                            .withFcpStRetainedTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalFcpStRetainedValue()))
                            .withItemsTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTaxableGrossTotalValue()))
                            .withShippingTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getShippingTotalValue()))
                            .withInsuranceTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getInsuranceTotalValue()))
                            .withDiscountTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getDiscountTotalValue()))
                            .withIITotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIIValue()))
                            .withIPITotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalIPIValue()))
                            .withReturnedIpiTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalReturnedIpiValue()))
                            .withPISTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalPisValue()))
                            .withCOFINSTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getTotalTaxes().getTotalCofinsValue()))
                            .withOtherIncidentalCostsTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getOthersTotalValue()))
                            .withNFeTotalValue(this.formatNFeDecimal1302(fiscalDocumentTotal.getFiscalDocumentTotalValue()))
                            .withTaxTotalValue(this.fiscalDocument.isEndConsumer() ? Optional.ofNullable(fiscalDocumentTotal.getApproximateTaxTotalValue()).map(ApproximateTax::getTotal).map(this::formatNFeDecimal1302Optional).orElse(null) : null)
                            .build())
                    .build();
        }
        //@formatter:on
        return null;
    }

    private NFeIdentification buildNFeIdentification() {
        final NFeIdentification.Builder builder = new NFeIdentification.Builder();

        builder
                .withApplicationVersion(DispatchFromFiscalDocumentAdapter.APP_VERSION)
                .withDanfePrintFormat(this.buildDANFEPrintFormat())
                .withDestinationOperationIdentifier(this.buildDestinationOperationIdentifier())
                .withEmissionDateTime(
                        DispatchFromFiscalDocumentAdapter.NFE_DATETIME_FORMAT
                                .format(this.fiscalDocument.getEmission().getDate())
                )
                .withFinalCustomerOperation(
                        this.fiscalDocument.isEndConsumer() ?
                                FinalCustomerOperation.CONSUMIDOR_FINAL :
                                FinalCustomerOperation.NAO
                )
                .withFiscalDocumentModel(this.fiscalDocument.getModel())
                .withFiscalDocumentSeries(
                        Optional.ofNullable(this.fiscalDocument.getSerie())
                                .map(FiscalDocumentSerie::getNumber)
                                .map(String::valueOf)
                                .orElseThrow(() -> new IllegalArgumentException(
                                        "A série e/ou numeração da série não pode ser nula."
                                ))
                )
                .withFiscalDocumentNumber(this.fiscalDocument.getNumber().toString())
                .withFiscalDocumentType(this.buildFiscalDocumentType())
                .withNFeCode(this.fiscalDocument.getCode())
                .withNFeFinality(this.buildNFeFinality())
                .withNFeTransmissionMethod(NFeTransmissionMethod.NORMAL)
                .withChecksum(String.valueOf(this.buildChecksum(this.buildAccessKey())))
                .withNFeTransmissionProcess(NFeTransmissionProcess.APLICATIVO_CONTRIBUINTE)
                .withOperationType(
                        this.formatNFeString(
                                this.buildOperationTypeDescriptor(),
                                60
                        )
                )
                .withPurchaserPresenceIndicator(
                        Optional.ofNullable(this.fiscalDocument.getPresenceIndicator())
                                .map(PresenceIndicator::getValue)
                                .map(PurchaserPresenceIndicator::findByCode)
                                .orElse(PurchaserPresenceIndicator.OPERACAO_PRESENCIAL)
                )
                .withBrokerIndicator(this.buildBrokerIndicator())
                //TODO Revisar withTaxableEventCityIbgeCode
                .withTaxableEventCityIbgeCode(
                        Optional.ofNullable(this.fiscalDocument.getEmitter())
                                .map(eprecise.efiscal4j.nfe.emitter.Emitter::getAddress)
                                .map(EmitterAddress::getCity)
                                .map(EmitterAddressCity::getIbgeCode)
                                .orElse(DispatchFromFiscalDocumentAdapter.IBGE_CODE_DEFAULT)
                )
                .withTransmissionEnvironment(this.buildTransmissionEnvironment())
                .withUFIbgeCode(
                        Optional.ofNullable(this.fiscalDocument.getEmitter())
                                .map(eprecise.efiscal4j.nfe.emitter.Emitter::getAddress)
                                .map(EmitterAddress::getCity)
                                .map(EmitterAddressCity::getUf)
                                .map(UF::getAcronym)
                                .map(UF::findByAcronym)
                                .orElse(UF.EX)
                )
                .withReferencedDocuments(this.buildReferencedDocuments());

        Optional.of(this.fiscalDocument)
                .filter(eprecise.efiscal4j.nfe.NFe.class::isInstance)
                .map(eprecise.efiscal4j.nfe.NFe.class::cast)
                .map(eprecise.efiscal4j.nfe.NFe::getEntranceOrExit)
                .map(IODate::getDate)
                .ifPresent(entranceDate -> builder
                        .withEntranceOrExitDateTime(
                                DispatchFromFiscalDocumentAdapter.NFE_DATETIME_FORMAT
                                        .format(entranceDate)
                        )
                );

        return builder.build();
    }

    private NFeBrokerIndicator buildBrokerIndicator() {
        final BrokerIndicator brokerIndicator = this.fiscalDocument.getBrokerIndicator();
        if (brokerIndicator instanceof WithBrokerOperation) {
            return NFeBrokerIndicator.OPERACAO_SITE_PLATAFORMA_TERCEIRO;
        } else if (brokerIndicator instanceof WithoutBrokerOperation) {
            return NFeBrokerIndicator.OPERACAO_SEM_INTERMEDIADOR;
        }
        return null;
    }

    private NFeBrokerInfo buildBrokerIndicatorInfo() {
        return Optional.ofNullable(this.fiscalDocument.getBrokerIndicator())
                .filter(WithBrokerOperation.class::isInstance)
                .map(WithBrokerOperation.class::cast)
                .map(wbo -> new NFeBrokerInfo.Builder()
                        .withCnpj(wbo.getCnpj())
                        .withIdentifier(wbo.getIdentifier())
                        .build()
                )
                .orElse(null);
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
        final Receiver receiver = Optional.ofNullable(this.fiscalDocument).filter(eprecise.efiscal4j.nfe.NFe.class::isInstance).map(eprecise.efiscal4j.nfe.NFe.class::cast).map(eprecise.efiscal4j.nfe.NFe::getReceiver).orElse(Optional.ofNullable(this.fiscalDocument).filter(NFCe.class::isInstance).map(NFCe.class::cast).map(NFCe::getConsumer).filter(Receiver.class::isInstance).map(Receiver.class::cast).orElse(null));

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
        return Optional.ofNullable(this.fiscalDocument).filter(eprecise.efiscal4j.nfe.NFe.class::isInstance).map(eprecise.efiscal4j.nfe.NFe.class::cast)
                .map(eprecise.efiscal4j.nfe.NFe::getDocumentReferences).map(dr -> dr.stream().map(this::toReferencedDocument).collect(Collectors.toList())).orElse(null);
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
                                                            .withReferencedNFModel(ReferencedNFModel.MODELO_01_1A)
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
        if(docs != null) {
            return builder
                    .asLegalEntity()
                    .withCnpj(docs.getCnp())
                    .withCorporateName(this.formatNFeString(docs.getName(), 60))
                    .withCrt(this.buildCrt(emitter.getCrt()))
                    .withStateRegistration(this.nullIfEmpty(docs.getIe()))
                    .withStateRegistrationST(Optional.ofNullable(docs.getIeSt()).map(this::nullIfEmpty).orElse(null))
                    .withMunicipalRegistration(Optional.ofNullable(docs.getMunicipalDocuments()).map(EmitterMunicipalDocuments::getIm).map(this::nullIfEmpty).orElse(null))
                    .withAdress(this.buildEmitterAddress(emitter.getAddress(), Optional.ofNullable(emitter.getPhone())))
                    .withFancyName(this.formatNFeString(docs.getFancyName(), 60))
                    .build();
            }
        return null;
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
                            .withDescription(this.formatNFeString(c.getDescription(), 60))
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
     // @formatter:off
        return Optional.ofNullable(this.nullIfEmpty(input)).filter(Objects::nonNull)
                    .map(string -> StringUtils.trimToNull(string.trim()))
                    .map(StringUtils::stripAccents)
                    .map(string -> string.replaceAll("[\\s&&[^\\n]&&[^\\r&&[^\\t]]]+", " "))
                    .map(string -> string.replaceAll("\n", LINE_BREAK_REPLACEMENT).replaceAll("\r", LINE_BREAK_REPLACEMENT).replace("\t", LINE_BREAK_REPLACEMENT))
                    .map(this::keepValidStrings)
                    .map(string -> DispatchFromFiscalDocumentAdapter.abbreviate(string, size))
                    .map(StringUtils::upperCase)
                .orElse(null);
     // @formatter:on
    }

    private String keepValidStrings(final String input) {
        final Matcher matcher = Pattern.compile(STRING_VALID_PATTERN).matcher(input);
        final StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                result.append(matcher.group(i)).append(" ");
            }
        }
        return result.toString().trim();
    }

    private static String abbreviate(final String input, final int size) {
        if ((input != null) && !input.isEmpty()) {
            if (size >= 4) {
                return StringUtils.abbreviate(input, size);
            } else if (input.length() > size) {
                return input.substring(0, size);
            }
        }
        return input;

    }

    private eprecise.efiscal4j.nfe.v400.CRT buildCrt(final CRT crt) {
        return Optional.ofNullable(crt)
                .map(CRT::getValue)
                .map(eprecise.efiscal4j.nfe.v400.CRT::findByCode)
                .orElse(null);
    }

    private List<NFeDetail> buildNFeDetails() {
        return this.fiscalDocument.getItems().stream().map(this::buildNFeDetail).collect(Collectors.toList());
    }

    private NFeDetail buildNFeDetail(final Item item) {
     // @formatter:off
        final Integer itemOrder = this.fiscalDocument.getItemOrder(item);
        return new NFeDetail.Builder()
                .withItemOrder(Optional.ofNullable(itemOrder).map(String::valueOf).orElse(null))
                .withNFeItem(this.buildNFeItem(item, itemOrder))
                .withTax(this.buildTax(item))
                .withAdditionalProductInfo(item.getAdditionalInfo())
                .withReturnedTax(null) //TODO
                .build();
     // @formatter:on
    }

    private NFeItem buildNFeItem(final Item item, final Integer itemOrder) {
     // @formatter:off
        return new NFeItem.Builder()
                .withItemCode(this.formatNFeString(item.getCode(), 60))
                .withGlobalTradeItemNumber(Optional.ofNullable(item.getGlobalTradeItemNumber()).map(ItemEan::getGlobalTradeItemNumber).map(this::nullIfEmpty).orElse("SEM GTIN"))
                .withItemDescription(this.buildItemDescription(item, itemOrder))
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
                .withItemGrossValue(Optional.ofNullable(item.getGrossValue()).map(ItemGrossValue::getComercialGrossValue).map(this::formatNFeDecimal1302).orElse(null))
                .withTaxableUnitGlobalTradeItemNumber(Optional.ofNullable(item.getGlobalTradeItemNumber()).map(ItemEan::getTaxableGlobalTradeItemNumber).map(this::nullIfEmpty).orElse("SEM GTIN"))
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
                .withFuel(this.buildFuel(item.getFuel()))
                .withImportDeclarations(this.buildImportDeclarations(item))
                .withItemExportDetails(this.buildItemExportDetails(item))
                .withPurchaseOrderDescription(this.formatNFeString(item.getPurchaseOrderDescription(), 15))
                .withPurchaseOrderNumber(this.formatNFeString(item.getPurchaseOrderNumber(), 6))
                .withFciNumber(Optional.ofNullable(item.getFciNumber()).map(this::nullIfEmpty).orElse(null))
                .withBeneficiaryCode(this.formatNFeString(item.getBeneficiaryCode(), 10))
                .withTraces(this.buildTraces(item))
                .build();
     // @formatter:on
    }

    private String buildItemDescription(final Item item, final Integer itemOrder) {
        if (this.fiscalDocument.getModel().equals(FiscalDocumentModel.NFCE) && itemOrder.equals(1) && TransmissionEnvironment.HOMOLOGATION.equals(this.fiscalDocument.getSerie().getEnvironment())) {
            return ITEM_DESCRIPTION_NFCE_HOMOLOGATION;
        }
        return this.formatNFeString(item.getName(), 120);
    }

    private Fuel buildFuel(final eprecise.efiscal4j.nfe.item.fuel.Fuel fuel) {
        if (fuel != null) {
            return new Fuel.Builder()
                    .withAnpProductCode(fuel.getAnpProductCode())
                    .withAnpProductDescription(this.formatNFeString(fuel.getAnpProductDescription(), 95))
                    .withGlpPercent(this.formatNFeDecimal0302a04Max100(fuel.getGlpPercent()))
                    .withGnnPercent(this.formatNFeDecimal0302a04Max100(fuel.getGnnPercent()))
                    .withGniPercent(this.formatNFeDecimal0302a04Max100(fuel.getGniPercent()))
                    .withStartingValue(this.formatNFeDecimal1302(fuel.getStartingValue()))
                    .withCodifCode(fuel.getCodifCode())
                    .withTemperature(this.formatNFeDecimal1204Temperature(fuel.getTemperature()))
                    .withConsumerUF(fuel.getConsumerUF())
                    .withCide(this.buildFuelCide(fuel.getCide()))
                    .withClosing(this.buildFuelClosing(fuel.getClosing()))
                    .withFuelOrigin(this.buildFuelOrigins(fuel.getOrigins()))
                    .build();
        }
        return null;
    }

    private List<FuelOrigin> buildFuelOrigins(final List<eprecise.efiscal4j.nfe.item.fuel.FuelOrigin> origins) {
        return Optional.ofNullable(origins)
                .map(it -> it.stream()
                        .filter(Objects::nonNull)
                        .map(origin -> new FuelOrigin.Builder()
                                .withImportIndicator(origin.getImportIndicator())
                                .withOriginUf(origin.getOriginUf())
                                .withPercentageOriginatedFromUf(origin.getPercentageOriginatedFromUf())
                                .build()
                        )
                        .collect(Collectors.toList())
                )
                .orElse(null);
    }

    private FuelClosing buildFuelClosing(final eprecise.efiscal4j.nfe.item.fuel.FuelClosing closing) {
        if (closing != null) {
            return new FuelClosing.Builder().withNozzleNumber(closing.getNozzleNumber()).withPumpNumber(closing.getPumpNumber()).withTankNumber(closing.getTankNumber())
                    .withClosingBeginValue(this.formatNFeDecimal1203(closing.getClosingBeginValue())).withClosingEndValue(this.formatNFeDecimal1203(closing.getClosingEndValue())).build();
        }
        return null;
    }

    private FuelCide buildFuelCide(final eprecise.efiscal4j.nfe.item.fuel.FuelCide cide) {
        if (cide != null) {
            return new FuelCide.Builder().withBcCideQuantity(this.formatNFeDecimal1204Variable(cide.getBcCideQuantity())).withCideAliquotValue(this.formatNFeDecimal1104(cide.getCideAliquotValue()))
                    .withCideValue(this.formatNFeDecimal1302(cide.getCideValue())).build();
        }
        return null;
    }

    private List<Trace> buildTraces(final Item item) {
     // @formatter:off
        if((item.getTraces() != null) && !item.getTraces().isEmpty()) {
            return item.getTraces().stream().map(t -> new Trace.Builder()
                    .withBatchNumber(this.formatNFeString(t.getBatchNumber(), 20))
                    .withBatchQuantity(this.formatNFeDecimal0803Variable(t.getBatchQuantity()))
                    .withManufacturingDate(Optional.ofNullable(t.getManufacturing()).map(NFeDate.dateFormat::format).orElse(null))
                    .withExpirationDate(Optional.ofNullable(t.getExpiration()).map(NFeDate.dateFormat::format).orElse(null))
                    .withAggregationCode(t.getAggregationCode())
                    .build()).collect(Collectors.toList());
        }
     // @formatter:on
        return null;
    }

    private List<ImportDeclaration> buildImportDeclarations(final Item item) {
     // @formatter:off
        final Collection<eprecise.efiscal4j.nfe.item.di.ImportDeclaration> importDeclarations = item.getImportDeclarations();
        if((importDeclarations != null) && !importDeclarations.isEmpty()) {
            return importDeclarations.stream().map(id -> new ImportDeclaration.Builder()
                    .withNumber(id.getNumber())
                    .withDate(Optional.ofNullable(id.getDate()).map(NFeDate.dateFormat::format).orElse(null))
                    .withClearanceSpot(id.getClearanceSpot())
                    .withClearanceDate(Optional.ofNullable(id.getClearanceDate()).map(NFeDate.dateFormat::format).orElse(null))
                    .withClearanceUf(Optional.ofNullable(id.getClearanceUf()).orElse(null))
                    .withInternationalTransportPathway(Optional.ofNullable(id.getInternationalTransportPathway()).map(itpw -> InternationalTransportPathway.findByCode(itpw.getValue())).orElse(null))
                    .withAdditValShipMerchMarineRenovation(this.formatNFeDecimal1302(id.getAdditValShipMerchMarineRenovation()))
                    .withIntermediaryImportType(Optional.ofNullable(id.getIntermediaryImportType()).map(iit -> IntermediaryImportType.findByCode(iit.getValue())).orElse(null))
                    .withAcquirerOrOrderingPartyCnpj(id.getAcquirerOrOrderingPartyCnpj())
                    .withAcquirerOrOrderingPartyUf(Optional.ofNullable(id.getAcquirerOrOrderingPartyUf()).orElse(null))
                    .withExporterCode(id.getExporterCode())
                    .withAdditions(Optional.ofNullable(id.getAdditions()).map(additions -> additions.stream().map(addition -> new Addition.Builder()
                            .withNumber(Optional.ofNullable(addition.getNumber()).map(Object::toString).orElse(null))
                            .withSequence(Optional.ofNullable(addition.getSequence()).map(Object::toString).orElse(null))
                            .withManufacturerCode(this.formatNFeString(addition.getManufacturerCode(), 60))
                            .withDiscountValue(this.formatNFeDecimal1302Optional(addition.getDiscountValue()))
                            .withDrawbackNumber(addition.getDrawbackNumber())
                            .build()).collect(Collectors.toList())).orElse(null))
                    .build()).collect(Collectors.toList());
        }
        return null;
     // @formatter:on
    }

    private List<NFeItemExportDetail> buildItemExportDetails(final Item item) {
        final Collection<ItemExportDetail> itemExportDetails = item.getItemExportDetails();
        if ((itemExportDetails != null) && !itemExportDetails.isEmpty()) {
            return itemExportDetails.stream()
                    .map(ied -> new NFeItemExportDetail.Builder().withDrawbackNumber(ied.getDrawbackNumber())
                            .withIndirectExport(Optional.ofNullable(ied.getIndirectExport())
                                    .map(ie -> new NFeItemIndirectExport.Builder().withAccessKey(ie.getAccessKey()).withExportRegistrationNumber(ie.getExportRegistrationNumber())
                                            .withItemExportQuantity(Optional.ofNullable(ie.getItemExportQuantity()).map(this::formatNFeDecimal1104Variable).orElse(null)).build())
                                    .orElse(null))
                            .build())
                    .collect(Collectors.toList());
        }
        return null;
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
                    .withTaxTotalValue(Optional.ofNullable(taxStructure).map(TaxStructure::getApproximateTax).map(ApproximateTax::getTotal).map(this::formatNFeDecimal1302Optional).orElse(null))
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
     // @formatter:off
        final eprecise.efiscal4j.nfe.item.tax.ufReceiverIcms.ICMSUFReceiver icmsUfReceiver = taxStructure.getTaxes().stream().filter(eprecise.efiscal4j.nfe.item.tax.ufReceiverIcms.ICMSUFReceiver.class::isInstance).map(eprecise.efiscal4j.nfe.item.tax.ufReceiverIcms.ICMSUFReceiver.class::cast).findFirst().orElse(null);
        if(icmsUfReceiver != null) {
            return new ICMSUFReceiver.Builder()
                    .withReceiverUfBcValue(Optional.ofNullable(icmsUfReceiver.getCalculationBasis()).map(this::formatNFeDecimal1302).orElse(null))
                    .withReceiverUfBcFcpValue(Optional.ofNullable(icmsUfReceiver.getBcFcpValue()).map(this::formatNFeDecimal1302).orElse(null))
                    .withReceiverUfFCPPercentual(Optional.ofNullable(icmsUfReceiver.getFcpAditionalAliquot()).map(this::formatNFeDecimal0302a04).orElse(null))
                    .withReceiverUfIcmsAliquot(Optional.ofNullable(icmsUfReceiver.getAliquot()).map(this::formatNFeDecimal0302a04).orElse(null))
                    .withInterstateIcmsUfAliquot(Optional.ofNullable(icmsUfReceiver.getInterstateAliquot()).map(ia -> InterstateICMSUFAliquot.findByCode(ia.getValue())).orElse(null))
                    .withReceiverUfSharePercentual(Optional.ofNullable(icmsUfReceiver.getSharePercentual()).map(this::formatNFeDecimal0302a04).orElse(null))
                    .withReceiverUfFCPValue(Optional.ofNullable(icmsUfReceiver.getFcpValue()).map(this::formatNFeDecimal1302).orElse(null))
                    .withReceiverUfIcmsShareValue(Optional.ofNullable(icmsUfReceiver.getShareValue()).map(this::formatNFeDecimal1302).orElse(null))
                    .withEmitterUfIcmsShareValue(Optional.ofNullable(icmsUfReceiver.getEmitterShareValue()).map(this::formatNFeDecimal1302).orElse(null))
                    .build();
        }
     // @formatter:on
        return null;
    }

    private PIS buildPis(final TaxStructure taxStructure) {
     // @formatter:off
        final eprecise.efiscal4j.nfe.item.tax.pis.PIS pis = taxStructure.getTaxes().stream().filter(eprecise.efiscal4j.nfe.item.tax.pis.PIS.class::isInstance).map(eprecise.efiscal4j.nfe.item.tax.pis.PIS.class::cast).findFirst().orElse(null);
        if(pis != null) {
            switch(pis.getCst()) {
            case CST_01: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS01 pis01 = (eprecise.efiscal4j.nfe.item.tax.pis.PIS01) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS01.Builder()
                        .withBcValue(Optional.ofNullable(pis01.getPis()).map(PisValueWithAliquotPercent::getAliquot).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pis01.getPis()).map(PisValueWithAliquotPercent::getAliquot).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withPisValue(Optional.ofNullable(pis01.getPis()).map(PisValueWithAliquotPercent::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_02: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS02 pis02 = (eprecise.efiscal4j.nfe.item.tax.pis.PIS02) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS02.Builder()
                        .withBcValue(Optional.ofNullable(pis02.getPis()).map(PisValueWithAliquotPercent::getAliquot).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pis02.getPis()).map(PisValueWithAliquotPercent::getAliquot).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withPisValue(Optional.ofNullable(pis02.getPis()).map(PisValueWithAliquotPercent::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_03: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS03 pis03 = (eprecise.efiscal4j.nfe.item.tax.pis.PIS03) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS03.Builder()
                        .withProductQuantity(Optional.ofNullable(pis03.getPis()).map(PisValueWithAliquotValue::getAliquot).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pis03.getPis()).map(PisValueWithAliquotValue::getAliquot).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pis03.getPis()).map(PisValueWithAliquotValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_04: {
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS04.Builder().build();
            }
            case CST_05: {
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS05.Builder().build();
            }
            case CST_06: {
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS06.Builder().build();
            }
            case CST_07: {
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS07.Builder().build();
            }
            case CST_08: {
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS08.Builder().build();
            }
            case CST_09: {
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS09.Builder().build();
            }
            case CST_49: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS49 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS49) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS49.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_50: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS50 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS50) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS50.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_51: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS51 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS51) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS51.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_52: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS52 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS52) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS52.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_53: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS53 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS53) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS53.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_54: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS54 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS54) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS54.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_55: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS55 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS55) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS55.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_56: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS56 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS56) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS56.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_60: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS60 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS60) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS60.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_61: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS61 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS61) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS61.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_62: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS62 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS62) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS62.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_63: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS63 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS63) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS63.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_64: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS64 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS64) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS64.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_65: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS65 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS65) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS65.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_66: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS66 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS66) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS66.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_67: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS67 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS67) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS67.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_70: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS70 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS70) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS70.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_71: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS71 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS71) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS71.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_72: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS72 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS72) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS72.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_73: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS73 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS73) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS73.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_74: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS74 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS74) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS74.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_75: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS75 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS75) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS75.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_98: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS98 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS98) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS98.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_99: {
                final eprecise.efiscal4j.nfe.item.tax.pis.PIS99 pisOther = (eprecise.efiscal4j.nfe.item.tax.pis.PIS99) pis;
                return new eprecise.efiscal4j.nfe.v400.tax.pis.PIS99.Builder()
                        .withBcValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withPisAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withPisValue(Optional.ofNullable(pisOther.getPis()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            default:
                break;
            }
        }
     // @formatter:on
        return null;
    }

    private COFINS buildCofins(final TaxStructure taxStructure) {
        // @formatter:off
        final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS cofins = taxStructure.getTaxes().stream().filter(eprecise.efiscal4j.nfe.item.tax.cofins.COFINS.class::isInstance).map(eprecise.efiscal4j.nfe.item.tax.cofins.COFINS.class::cast).findFirst().orElse(null);
        if(cofins != null) {
            switch(cofins.getCst()) {
            case CST_01: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS01 cofins01 = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS01) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS01.Builder()
                        .withBcValue(Optional.ofNullable(cofins01.getCofins()).map(CofinsValueWithAliquotPercent::getAliquot).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofins01.getCofins()).map(CofinsValueWithAliquotPercent::getAliquot).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofins01.getCofins()).map(CofinsValueWithAliquotPercent::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_02: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS02 cofins02 = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS02) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS02.Builder()
                        .withBcValue(Optional.ofNullable(cofins02.getCofins()).map(CofinsValueWithAliquotPercent::getAliquot).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofins02.getCofins()).map(CofinsValueWithAliquotPercent::getAliquot).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofins02.getCofins()).map(CofinsValueWithAliquotPercent::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_03: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS03 cofins03 = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS03) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS03.Builder()
                        .withProductQuantity(Optional.ofNullable(cofins03.getCofins()).map(CofinsValueWithAliquotValue::getAliquot).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofins03.getCofins()).map(CofinsValueWithAliquotValue::getAliquot).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofins03.getCofins()).map(CofinsValueWithAliquotValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_04: {
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS04.Builder().build();
            }
            case CST_05: {
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS05.Builder().build();
            }
            case CST_06: {
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS06.Builder().build();
            }
            case CST_07: {
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS07.Builder().build();
            }
            case CST_08: {
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS08.Builder().build();
            }
            case CST_09: {
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS09.Builder().build();
            }
            case CST_49: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS49 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS49) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS49.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_50: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS50 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS50) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS50.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_51: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS51 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS51) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS51.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_52: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS52 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS52) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS52.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_53: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS53 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS53) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS53.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_54: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS54 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS54) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS54.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_55: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS55 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS55) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS55.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_56: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS56 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS56) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS56.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_60: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS60 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS60) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS60.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_61: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS61 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS61) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS61.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_62: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS62 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS62) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS62.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_63: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS63 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS63) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS63.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_64: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS64 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS64) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS64.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_65: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS65 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS65) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS65.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_66: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS66 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS66) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS66.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_67: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS67 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS67) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS67.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_70: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS70 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS70) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS70.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_71: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS71 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS71) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS71.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_72: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS72 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS72) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS72.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_73: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS73 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS73) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS73.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_74: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS74 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS74) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS74.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_75: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS75 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS75) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS75.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_98: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS98 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS98) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS98.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_99: {
                final eprecise.efiscal4j.nfe.item.tax.cofins.COFINS99 cofinsOther = (eprecise.efiscal4j.nfe.item.tax.cofins.COFINS99) cofins;
                return new eprecise.efiscal4j.nfe.v400.tax.cofins.COFINS99.Builder()
                        .withBcValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withCofinsAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withProductQuantity(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withProductAliquot(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104Variable).orElse(null))
                        .withCofinsValue(Optional.ofNullable(cofinsOther.getCofins()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            default:
                break;
            }
        }
     // @formatter:on
        return null;
    }

    private PISST buildPisSt(final TaxStructure taxStructure) {
     // @formatter:off
        final eprecise.efiscal4j.nfe.item.tax.pis.st.PISST pisSt = taxStructure.getTaxes().stream().filter(eprecise.efiscal4j.nfe.item.tax.pis.st.PISST.class::isInstance).map(eprecise.efiscal4j.nfe.item.tax.pis.st.PISST.class::cast).findFirst().orElse(null);
        if(pisSt != null) {
            return new PISST.Builder()
                    .withBcValue(Optional.ofNullable(pisSt.getValue()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302Optional).orElse(null))
                    .withPisAliquot(Optional.ofNullable(pisSt.getValue()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotPercentWithBc.class::isInstance).map(PisAliquotPercentWithBc.class::cast).map(PisAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                    .withProductQuantity(Optional.ofNullable(pisSt.getValue()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204).orElse(null))
                    .withProductAliquot(Optional.ofNullable(pisSt.getValue()).map(PisValueWithAliquot::getAliquot).filter(PisAliquotValueWithQuantity.class::isInstance).map(PisAliquotValueWithQuantity.class::cast).map(PisAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104).orElse(null))
                    .withPisValue(Optional.ofNullable(pisSt.getValue()).map(PisValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                    .build();
        }
     // @formatter:on
        return null;
    }

    private COFINSST buildCofinsSt(final TaxStructure taxStructure) {
     // @formatter:off
        final eprecise.efiscal4j.nfe.item.tax.cofins.st.COFINSST cofinsSt = taxStructure.getTaxes().stream().filter(eprecise.efiscal4j.nfe.item.tax.cofins.st.COFINSST.class::isInstance).map(eprecise.efiscal4j.nfe.item.tax.cofins.st.COFINSST.class::cast).findFirst().orElse(null);
        if(cofinsSt != null) {
            return new COFINSST.Builder()
                    .withBcValue(Optional.ofNullable(cofinsSt.getValue()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getCalculationBasis).map(this::formatNFeDecimal1302Optional).orElse(null))
                    .withCofinsAliquot(Optional.ofNullable(cofinsSt.getValue()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotPercentWithBc.class::isInstance).map(CofinsAliquotPercentWithBc.class::cast).map(CofinsAliquotPercentWithBc::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                    .withProductQuantity(Optional.ofNullable(cofinsSt.getValue()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getQuantity).map(this::formatNFeDecimal1204).orElse(null))
                    .withProductAliquot(Optional.ofNullable(cofinsSt.getValue()).map(CofinsValueWithAliquot::getAliquot).filter(CofinsAliquotValueWithQuantity.class::isInstance).map(CofinsAliquotValueWithQuantity.class::cast).map(CofinsAliquotValueWithQuantity::getAliquotValue).map(this::formatNFeDecimal1104).orElse(null))
                    .withCofinsValue(Optional.ofNullable(cofinsSt.getValue()).map(CofinsValueWithAliquot::getValue).map(this::formatNFeDecimal1302).orElse(null))
                    .build();
        }
     // @formatter:on
        return null;
    }

    private II buildIi(final TaxStructure taxStructure) {
     // @formatter:off
        final eprecise.efiscal4j.nfe.item.tax.ii.II ii = taxStructure.getTaxes().stream().filter(eprecise.efiscal4j.nfe.item.tax.ii.II.class::isInstance).map(eprecise.efiscal4j.nfe.item.tax.ii.II.class::cast).findFirst().orElse(null);
        if(ii != null) {
            return new II.Builder()
                    .withBcValue(Optional.ofNullable(ii.getCalculationBasis()).map(this::formatNFeDecimal1302).orElse(null))
                    .withCustomsCharge(Optional.ofNullable(ii.getCustomsCharge()).map(this::formatNFeDecimal1302).orElse(null))
                    .withIiValue(Optional.ofNullable(ii.getValue()).map(this::formatNFeDecimal1302).orElse(null))
                    .withIofValue(Optional.ofNullable(ii.getIof()).map(this::formatNFeDecimal1302).orElse(null))
                    .build();
        }
     // @formatter:on
        return null;
    }

    private IPI buildIpi(final TaxStructure taxStructure) {
     // @formatter:off
        final eprecise.efiscal4j.nfe.item.tax.ipi.IPI ipi = taxStructure.getTaxes().stream().filter(eprecise.efiscal4j.nfe.item.tax.ipi.IPI.class::isInstance).map(eprecise.efiscal4j.nfe.item.tax.ipi.IPI.class::cast).findFirst().orElse(null);
        if(ipi != null) {
            switch(ipi.getCst()) {
            case CST_00: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI00 ipi00 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI00) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI00.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi00.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi00.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi00.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi00.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .withBcValue(Optional.ofNullable(ipi00.getValue()).map(IpiValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIpiAliquot(Optional.ofNullable(ipi00.getValue()).map(IpiValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withUnityQuantity(Optional.ofNullable(ipi00.getValue()).map(IpiValue::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withUnityValue(Optional.ofNullable(ipi00.getValue()).map(IpiValue::getUnitaryValue).map(this::formatNFeDecimal1204).orElse(null))
                        .withIpiValue(Optional.ofNullable(ipi00.getValue()).map(IpiValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_01: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI01 ipi01 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI01) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI01.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi01.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi01.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi01.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi01.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .build();
            }
            case CST_02: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI02 ipi02 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI02) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI02.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi02.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi02.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi02.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi02.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .build();
            }
            case CST_03: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI03 ipi03 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI03) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI03.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi03.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi03.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi03.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi03.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .build();
            }
            case CST_04: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI04 ipi04 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI04) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI04.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi04.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi04.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi04.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi04.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .build();
            }
            case CST_05: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI05 ipi05 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI05) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI05.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi05.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi05.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi05.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi05.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .build();
            }
            case CST_49: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI49 ipi49 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI49) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI49.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi49.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi49.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi49.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi49.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .withBcValue(Optional.ofNullable(ipi49.getValue()).map(IpiValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIpiAliquot(Optional.ofNullable(ipi49.getValue()).map(IpiValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withUnityQuantity(Optional.ofNullable(ipi49.getValue()).map(IpiValue::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withUnityValue(Optional.ofNullable(ipi49.getValue()).map(IpiValue::getUnitaryValue).map(this::formatNFeDecimal1204).orElse(null))
                        .withIpiValue(Optional.ofNullable(ipi49.getValue()).map(IpiValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_50: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI50 ipi50 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI50) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI50.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi50.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi50.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi50.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi50.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .withBcValue(Optional.ofNullable(ipi50.getValue()).map(IpiValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIpiAliquot(Optional.ofNullable(ipi50.getValue()).map(IpiValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withUnityQuantity(Optional.ofNullable(ipi50.getValue()).map(IpiValue::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withUnityValue(Optional.ofNullable(ipi50.getValue()).map(IpiValue::getUnitaryValue).map(this::formatNFeDecimal1204).orElse(null))
                        .withIpiValue(Optional.ofNullable(ipi50.getValue()).map(IpiValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_51: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI51 ipi51 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI51) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI51.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi51.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi51.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi51.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi51.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .build();
            }
            case CST_52: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI52 ipi52 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI52) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI52.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi52.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi52.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi52.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi52.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .build();
            }
            case CST_53: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI53 ipi53 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI53) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI53.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi53.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi53.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi53.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi53.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .build();
            }
            case CST_54: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI54 ipi54 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI54) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI54.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi54.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi54.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi54.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi54.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .build();
            }
            case CST_55: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI55 ipi55 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI55) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI55.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi55.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi55.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi55.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi55.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .build();
            }
            case CST_99: {
                final eprecise.efiscal4j.nfe.item.tax.ipi.IPI99 ipi99 = (eprecise.efiscal4j.nfe.item.tax.ipi.IPI99) ipi;
                return new eprecise.efiscal4j.nfe.v400.tax.ipi.IPI99.Builder()
                        .withProducerCNPJ(Optional.ofNullable(ipi99.getGeneralData()).map(IPIGeneralData::getProducerCnpj).orElse(null))
                        .withIpiSealCode(Optional.ofNullable(ipi99.getGeneralData()).map(IPIGeneralData::getIpiSealCode).map(sc -> this.formatNFeString(sc, 60)).orElse(null))
                        .withIpiSealQuantity(Optional.ofNullable(ipi99.getGeneralData()).map(IPIGeneralData::getIpiSealQuantity).map(sq -> this.formatNFeString(sq, 12)).orElse(null))
                        .withLegalFramework(Optional.ofNullable(ipi99.getGeneralData()).map(IPIGeneralData::getLegalFramework).map(this::nullIfEmpty).orElse(null))
                        .withBcValue(Optional.ofNullable(ipi99.getValue()).map(IpiValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIpiAliquot(Optional.ofNullable(ipi99.getValue()).map(IpiValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withUnityQuantity(Optional.ofNullable(ipi99.getValue()).map(IpiValue::getQuantity).map(this::formatNFeDecimal1204Variable).orElse(null))
                        .withUnityValue(Optional.ofNullable(ipi99.getValue()).map(IpiValue::getUnitaryValue).map(this::formatNFeDecimal1204).orElse(null))
                        .withIpiValue(Optional.ofNullable(ipi99.getValue()).map(IpiValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            }
        }
     // @formatter:on
        return null;
    }

    private ICMS buildIcms(final TaxStructure taxStructure) {
     // @formatter:off
        final eprecise.efiscal4j.nfe.item.tax.icms.ICMS icms = taxStructure.getTaxes().stream().filter(eprecise.efiscal4j.nfe.item.tax.icms.ICMS.class::isInstance).map(eprecise.efiscal4j.nfe.item.tax.icms.ICMS.class::cast).findFirst().orElse(null);
        if(icms != null) {
            switch(icms.getCst()) {

            case CST_00 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS00 icms00 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS00) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS00.Builder()
                        .withOrigin(Optional.ofNullable(icms00.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModality(Optional.ofNullable(icms00.getIcms()).map(IcmsWithBcValue::getCalculationBasis).map(this::buildIcmsBcModality).orElse(null))
                        .withBcValue(Optional.ofNullable(icms00.getIcms()).map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsAliquot(Optional.ofNullable(icms00.getIcms()).map(IcmsWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsValue(Optional.ofNullable(icms00.getIcms()).map(IcmsWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpAliquot(Optional.ofNullable(icms00.getFcp()).map(FcpValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpValue(Optional.ofNullable(icms00.getFcp()).map(FcpValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_10 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS10 icms10 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS10) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS10.Builder()
                        .withOrigin(Optional.ofNullable(icms10.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModality(Optional.ofNullable(icms10.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(this::buildIcmsBcModality).orElse(null))
                        .withBcValue(Optional.ofNullable(icms10.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsAliquot(Optional.ofNullable(icms10.getIcmsWithBcValue()).map(IcmsWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsValue(Optional.ofNullable(icms10.getIcmsWithBcValue()).map(IcmsWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValue(Optional.ofNullable(icms10.getFcp()).map(FcpWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpAliquot(Optional.ofNullable(icms10.getFcp()).map(FcpWithBcValue::getValue).map(FcpValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpValue(Optional.ofNullable(icms10.getFcp()).map(FcpWithBcValue::getValue).map(FcpValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcModalityST(Optional.ofNullable(icms10.getIcmsSt()).map(IcmsStWithBcReductionPercent::getValue).map(IcmsStWithBcValue::getCalculationBasis).map(this::buildIcmsStBcModality).orElse(null))
                        .withValueMarginAddedStPercent(Optional.ofNullable(icms10.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).filter(IcmsStBcMarginAddedValue.class::isInstance).map(IcmsStBcMarginAddedValue.class::cast).map(IcmsStBcMarginAddedValue::getMarginAddedPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcReductionStPercent(Optional.ofNullable(icms10.getIcmsSt()).map(IcmsStWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValueST(Optional.ofNullable(icms10.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStAliquot(Optional.ofNullable(icms10.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsStValue(Optional.ofNullable(icms10.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValueST(Optional.ofNullable(icms10.getFcpSt()).map(FcpStWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpStAliquot(Optional.ofNullable(icms10.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpStValue(Optional.ofNullable(icms10.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_20 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS20 icms20 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS20) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS20.Builder()
                        .withOrigin(Optional.ofNullable(icms20.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModality(Optional.ofNullable(icms20.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(this::buildIcmsBcModality).orElse(null))
                        .withBcReductionPercent(Optional.ofNullable(icms20.getIcms()).map(IcmsWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValue(Optional.ofNullable(icms20.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsAliquot(Optional.ofNullable(icms20.getIcmsWithBcValue()).map(IcmsWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsValue(Optional.ofNullable(icms20.getIcmsWithBcValue()).map(IcmsWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValue(Optional.ofNullable(icms20.getFcp()).map(FcpWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpAliquot(Optional.ofNullable(icms20.getFcp()).map(FcpWithBcValue::getValue).map(FcpValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpValue(Optional.ofNullable(icms20.getFcp()).map(FcpWithBcValue::getValue).map(FcpValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationValue(Optional.ofNullable(icms20.getDesoneration()).map(IcmsDesoneration::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationReason(Optional.ofNullable(icms20.getDesoneration()).map(IcmsDesoneration::getReason).map(r -> ICMSDesonerationReason.findByCode(r.getValue())).orElse(null))
                        .build();
            }
            case CST_30 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS30 icms30 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS30) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS30.Builder()
                        .withOrigin(Optional.ofNullable(icms30.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModalityST(Optional.ofNullable(icms30.getIcmsSt()).map(IcmsStWithBcReductionPercent::getValue).map(IcmsStWithBcValue::getCalculationBasis).map(this::buildIcmsStBcModality).orElse(null))
                        .withValueMarginAddedStPercent(Optional.ofNullable(icms30.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).filter(IcmsStBcMarginAddedValue.class::isInstance).map(IcmsStBcMarginAddedValue.class::cast).map(IcmsStBcMarginAddedValue::getMarginAddedPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcReductionStPercent(Optional.ofNullable(icms30.getIcmsSt()).map(IcmsStWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValueST(Optional.ofNullable(icms30.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStAliquot(Optional.ofNullable(icms30.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsStValue(Optional.ofNullable(icms30.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValueST(Optional.ofNullable(icms30.getFcpSt()).map(FcpStWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpStAliquot(Optional.ofNullable(icms30.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpStValue(Optional.ofNullable(icms30.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationValue(Optional.ofNullable(icms30.getDesoneration()).map(IcmsDesoneration::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationReason(Optional.ofNullable(icms30.getDesoneration()).map(IcmsDesoneration::getReason).map(r -> ICMSDesonerationReason.findByCode(r.getValue())).orElse(null))
                        .build();
            }
            case CST_40 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS40 icms40 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS40) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS40.Builder()
                        .withOrigin(Optional.ofNullable(icms40.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withIcmsDesonerationValue(Optional.ofNullable(icms40.getDesoneration()).map(IcmsDesoneration::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationReason(Optional.ofNullable(icms40.getDesoneration()).map(IcmsDesoneration::getReason).map(r -> ICMSDesonerationReason.findByCode(r.getValue())).orElse(null))
                        .build();
            }
            case CST_41 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS41 icms41 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS41) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS41.Builder()
                        .withOrigin(Optional.ofNullable(icms41.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withIcmsDesonerationValue(Optional.ofNullable(icms41.getDesoneration()).map(IcmsDesoneration::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationReason(Optional.ofNullable(icms41.getDesoneration()).map(IcmsDesoneration::getReason).map(r -> ICMSDesonerationReason.findByCode(r.getValue())).orElse(null))
                        .build();
            }
            case CST_50 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS50 icms50 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS50) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS50.Builder()
                        .withOrigin(Optional.ofNullable(icms50.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withIcmsDesonerationValue(Optional.ofNullable(icms50.getDesoneration()).map(IcmsDesoneration::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationReason(Optional.ofNullable(icms50.getDesoneration()).map(IcmsDesoneration::getReason).map(r -> ICMSDesonerationReason.findByCode(r.getValue())).orElse(null))
                        .build();
            }
            case CST_51 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS51 icms51 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS51) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS51.Builder()
                        .withOrigin(Optional.ofNullable(icms51.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModality(Optional.ofNullable(icms51.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(this::buildIcmsBcModality).orElse(null))
                        .withBcReductionPercent(Optional.ofNullable(icms51.getIcms()).map(IcmsWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValue(Optional.ofNullable(icms51.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsAliquot(Optional.ofNullable(icms51.getIcmsWithBcValue()).map(IcmsWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsOperationValue(Optional.ofNullable(icms51.getOperationValue()).map(this::formatNFeDecimal1302).orElse(null))
                        .withDeferralPercent(Optional.ofNullable(icms51.getDeferral()).map(IcmsDeferral::getPercent).map(this::formatNFeDecimal0302a04Max100).orElse(null))
                        .withIcmsDeferralValue(Optional.ofNullable(icms51.getDeferral()).map(IcmsDeferral::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsValue(Optional.ofNullable(icms51.getIcmsWithBcValue()).map(IcmsWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValue(Optional.ofNullable(icms51.getFcp()).map(FcpWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpAliquot(Optional.ofNullable(icms51.getFcp()).map(FcpWithBcValue::getValue).map(FcpValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpValue(Optional.ofNullable(icms51.getFcp()).map(FcpWithBcValue::getValue).map(FcpValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_60 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS60 icms60 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS60) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS60.Builder()
                        .withOrigin(Optional.ofNullable(icms60.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcRetainedValueST(Optional.ofNullable(icms60.getIcmsStRetained()).map(IcmsStRetainedValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withEndConsumerSupportedAliquot(Optional.ofNullable(icms60.getEndConsumerSupportedAliquot()).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withIcmsSubstituteValue(Optional.ofNullable(icms60.getIcmsSubstituteValue()).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsRetainedValueST(Optional.ofNullable(icms60.getIcmsStRetained()).map(IcmsStRetainedValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpRetainedValueST(Optional.ofNullable(icms60.getFcpStRetained()).map(FcpStRetainedValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpRetainedAliquotST(Optional.ofNullable(icms60.getFcpStRetained()).map(FcpStRetainedValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpRetainedValueST(Optional.ofNullable(icms60.getFcpStRetained()).map(FcpStRetainedValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CST_61: {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS61 icms61 = Optional.ofNullable(icms)
                        .filter(eprecise.efiscal4j.nfe.item.tax.icms.ICMS61.class::isInstance)
                        .map(eprecise.efiscal4j.nfe.item.tax.icms.ICMS61.class::cast)
                        .orElseThrow(() -> new RuntimeException(
                                String.format(
                                        "O ICMS [ NAME: '%s' ] informado não é válido.",
                                        icms.getClass().getSimpleName()
                                )
                        ));

                final IcmsMonoRetWithValue icmsMonoRet = Optional.ofNullable(icms61.getIcmsMonoRetWithValue())
                        .orElseThrow(() -> new RuntimeException("O campo 'icms' do ICMS61 não pode ser nulo."));

                return new ICMS61.Builder()
                        .withOrigin(
                                Optional.of(icms61)
                                        .map(eprecise.efiscal4j.nfe.item.tax.icms.ICMS61::getOrigin)
                                        .map(eprecise.efiscal4j.nfe.item.tax.icms.ProductOrigin::getValue)
                                        .map(ProductOrigin::findByCode)
                                        .orElse(null)
                        )
                        .withQBCMonoRet(
                                Optional.ofNullable(icmsMonoRet)
                                        .map(IcmsMonoRetWithValue::getQBcValue)
                                        .map(this::formatNFeDecimal1104)
                                        .orElse(null)
                        )
                        .withAdRemICMSRet(
                                Optional.ofNullable(icmsMonoRet)
                                        .map(IcmsMonoRetWithValue::getAliquot)
                                        .map(this::formatNFeDecimal0302a04)
                                        .orElse(null)
                        )
                        .withVICMSMonoDif(
                                Optional.ofNullable(icmsMonoRet)
                                        .map(IcmsMonoRetWithValue::getIcmsValue)
                                        .map(this::formatNFeDecimal1302)
                                        .orElse(null)
                        )
                        .build();
            }
            case CST_70 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS70 icms70 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS70) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS70.Builder()
                        .withOrigin(Optional.ofNullable(icms70.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModality(Optional.ofNullable(icms70.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(this::buildIcmsBcModality).orElse(null))
                        .withBcReductionPercent(Optional.ofNullable(icms70.getIcms()).map(IcmsWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValue(Optional.ofNullable(icms70.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsAliquot(Optional.ofNullable(icms70.getIcmsWithBcValue()).map(IcmsWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsValue(Optional.ofNullable(icms70.getIcmsWithBcValue()).map(IcmsWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValue(Optional.ofNullable(icms70.getFcp()).map(FcpWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpAliquot(Optional.ofNullable(icms70.getFcp()).map(FcpWithBcValue::getValue).map(FcpValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpValue(Optional.ofNullable(icms70.getFcp()).map(FcpWithBcValue::getValue).map(FcpValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcModalityST(Optional.ofNullable(icms70.getIcmsSt()).map(IcmsStWithBcReductionPercent::getValue).map(IcmsStWithBcValue::getCalculationBasis).map(this::buildIcmsStBcModality).orElse(null))
                        .withValueMarginAddedStPercent(Optional.ofNullable(icms70.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).filter(IcmsStBcMarginAddedValue.class::isInstance).map(IcmsStBcMarginAddedValue.class::cast).map(IcmsStBcMarginAddedValue::getMarginAddedPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcReductionStPercent(Optional.ofNullable(icms70.getIcmsSt()).map(IcmsStWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValueST(Optional.ofNullable(icms70.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStAliquot(Optional.ofNullable(icms70.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsStValue(Optional.ofNullable(icms70.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValueST(Optional.ofNullable(icms70.getFcpSt()).map(FcpStWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpStAliquot(Optional.ofNullable(icms70.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpStValue(Optional.ofNullable(icms70.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationValue(Optional.ofNullable(icms70.getDesoneration()).map(IcmsDesoneration::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationReason(Optional.ofNullable(icms70.getDesoneration()).map(IcmsDesoneration::getReason).map(r -> ICMSDesonerationReason.findByCode(r.getValue())).orElse(null))
                        .build();
            }
            case CST_90 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMS90 icms90 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMS90) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMS90.Builder()
                        .withOrigin(Optional.ofNullable(icms90.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModality(Optional.ofNullable(icms90.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(this::buildIcmsBcModality).orElse(null))
                        .withBcReductionPercent(Optional.ofNullable(icms90.getIcms()).map(IcmsWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValue(Optional.ofNullable(icms90.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsAliquot(Optional.ofNullable(icms90.getIcmsWithBcValue()).map(IcmsWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsValue(Optional.ofNullable(icms90.getIcmsWithBcValue()).map(IcmsWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValue(Optional.ofNullable(icms90.getFcp()).map(FcpWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpAliquot(Optional.ofNullable(icms90.getFcp()).map(FcpWithBcValue::getValue).map(FcpValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpValue(Optional.ofNullable(icms90.getFcp()).map(FcpWithBcValue::getValue).map(FcpValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcModalityST(Optional.ofNullable(icms90.getIcmsSt()).map(IcmsStWithBcReductionPercent::getValue).map(IcmsStWithBcValue::getCalculationBasis).map(this::buildIcmsStBcModality).orElse(null))
                        .withValueMarginAddedStPercent(Optional.ofNullable(icms90.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).filter(IcmsStBcMarginAddedValue.class::isInstance).map(IcmsStBcMarginAddedValue.class::cast).map(IcmsStBcMarginAddedValue::getMarginAddedPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcReductionStPercent(Optional.ofNullable(icms90.getIcmsSt()).map(IcmsStWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValueST(Optional.ofNullable(icms90.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStAliquot(Optional.ofNullable(icms90.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsStValue(Optional.ofNullable(icms90.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValueST(Optional.ofNullable(icms90.getFcpSt()).map(FcpStWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpStAliquot(Optional.ofNullable(icms90.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpStValue(Optional.ofNullable(icms90.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationValue(Optional.ofNullable(icms90.getDesoneration()).map(IcmsDesoneration::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsDesonerationReason(Optional.ofNullable(icms90.getDesoneration()).map(IcmsDesoneration::getReason).map(r -> ICMSDesonerationReason.findByCode(r.getValue())).orElse(null))
                        .build();
            }
            case PART_CST_10 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSPart10 icmsPart10 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSPart10) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSPart10.Builder()
                        .withOrigin(Optional.ofNullable(icmsPart10.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModality(Optional.ofNullable(icmsPart10.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(this::buildIcmsBcModality).orElse(null))
                        .withBcReductionPercent(Optional.ofNullable(icmsPart10.getIcms()).map(IcmsWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValue(Optional.ofNullable(icmsPart10.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsAliquot(Optional.ofNullable(icmsPart10.getIcmsWithBcValue()).map(IcmsWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsValue(Optional.ofNullable(icmsPart10.getIcmsWithBcValue()).map(IcmsWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcModalityST(Optional.ofNullable(icmsPart10.getIcmsSt()).map(IcmsStWithBcReductionPercent::getValue).map(IcmsStWithBcValue::getCalculationBasis).map(this::buildIcmsStBcModality).orElse(null))
                        .withValueMarginAddedStPercent(Optional.ofNullable(icmsPart10.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).filter(IcmsStBcMarginAddedValue.class::isInstance).map(IcmsStBcMarginAddedValue.class::cast).map(IcmsStBcMarginAddedValue::getMarginAddedPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcReductionStPercent(Optional.ofNullable(icmsPart10.getIcmsSt()).map(IcmsStWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValueST(Optional.ofNullable(icmsPart10.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStAliquot(Optional.ofNullable(icmsPart10.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsStValue(Optional.ofNullable(icmsPart10.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withSelfOperationBCPerc(Optional.ofNullable(icmsPart10.getSelfOperationBcPercent()).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withUfST(icmsPart10.getUfSt())
                        .build();
            }
            case PART_CST_90 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSPart90 icmsPart90 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSPart90) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSPart90.Builder()
                        .withOrigin(Optional.ofNullable(icmsPart90.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModality(Optional.ofNullable(icmsPart90.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(this::buildIcmsBcModality).orElse(null))
                        .withBcReductionPercent(Optional.ofNullable(icmsPart90.getIcms()).map(IcmsWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValue(Optional.ofNullable(icmsPart90.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsAliquot(Optional.ofNullable(icmsPart90.getIcmsWithBcValue()).map(IcmsWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsValue(Optional.ofNullable(icmsPart90.getIcmsWithBcValue()).map(IcmsWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcModalityST(Optional.ofNullable(icmsPart90.getIcmsSt()).map(IcmsStWithBcReductionPercent::getValue).map(IcmsStWithBcValue::getCalculationBasis).map(this::buildIcmsStBcModality).orElse(null))
                        .withValueMarginAddedStPercent(Optional.ofNullable(icmsPart90.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).filter(IcmsStBcMarginAddedValue.class::isInstance).map(IcmsStBcMarginAddedValue.class::cast).map(IcmsStBcMarginAddedValue::getMarginAddedPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcReductionStPercent(Optional.ofNullable(icmsPart90.getIcmsSt()).map(IcmsStWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValueST(Optional.ofNullable(icmsPart90.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStAliquot(Optional.ofNullable(icmsPart90.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsStValue(Optional.ofNullable(icmsPart90.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withSelfOperationBCPerc(Optional.ofNullable(icmsPart90.getSelfOperationBcPercent()).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withUfST(icmsPart90.getUfSt())
                        .build();
            }
            case ST_CST_41 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSST icmsSt = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSST) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSST.Builder()
                        .withOrigin(Optional.ofNullable(icmsSt.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcRetainedValueST(Optional.ofNullable(icmsSt.getRetainedSt()).map(IcmsStRetainedValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withEndConsumerSupportedAliquot(Optional.ofNullable(icmsSt.getEndConsumerSupportedAliquot()).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withIcmsSubstituteValue(Optional.ofNullable(icmsSt.getIcmsSubstituteValue()).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsRetainedValueST(Optional.ofNullable(icmsSt.getRetainedSt()).map(IcmsStRetainedValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpRetainedValueST(Optional.ofNullable(icmsSt.getFcpStRetained()).map(FcpStRetainedValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpRetainedAliquotST(Optional.ofNullable(icmsSt.getFcpStRetained()).map(FcpStRetainedValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpRetainedValueST(Optional.ofNullable(icmsSt.getFcpStRetained()).map(FcpStRetainedValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcIcmsStDestination(Optional.ofNullable(icmsSt.getDestinationSt()).map(IcmsStDestinationValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStDestination(Optional.ofNullable(icmsSt.getDestinationSt()).map(IcmsStDestinationValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CSOSN_101 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN101 icmssn101 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN101) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN101.Builder()
                        .withOrigin(Optional.ofNullable(icmssn101.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withCreditSnAliquot(Optional.ofNullable(icmssn101.getCreditSn()).map(CreditSnValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withCreditSnIcmsValue(Optional.ofNullable(icmssn101.getCreditSn()).map(CreditSnValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CSOSN_102 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN102 icmssn102 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN102) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN102.Builder()
                        .withOrigin(Optional.ofNullable(icmssn102.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .build();
            }
            case CSOSN_103 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN103 icmssn103 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN103) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN103.Builder()
                        .withOrigin(Optional.ofNullable(icmssn103.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .build();
            }
            case CSOSN_300 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN300 icmssn300 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN300) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN300.Builder()
                        .withOrigin(Optional.ofNullable(icmssn300.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .build();
            }
            case CSOSN_400 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN400 icmssn400 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN400) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN400.Builder()
                        .withOrigin(Optional.ofNullable(icmssn400.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .build();
            }
            case CSOSN_201 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN201 icmssn201 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN201) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN201.Builder()
                        .withOrigin(Optional.ofNullable(icmssn201.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModalityST(Optional.ofNullable(icmssn201.getIcmsSt()).map(IcmsStWithBcReductionPercent::getValue).map(IcmsStWithBcValue::getCalculationBasis).map(this::buildIcmsStBcModality).orElse(null))
                        .withValueMarginAddedStPercent(Optional.ofNullable(icmssn201.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).filter(IcmsStBcMarginAddedValue.class::isInstance).map(IcmsStBcMarginAddedValue.class::cast).map(IcmsStBcMarginAddedValue::getMarginAddedPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcReductionStPercent(Optional.ofNullable(icmssn201.getIcmsSt()).map(IcmsStWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValueST(Optional.ofNullable(icmssn201.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStAliquot(Optional.ofNullable(icmssn201.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsStValue(Optional.ofNullable(icmssn201.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValueST(Optional.ofNullable(icmssn201.getFcpSt()).map(FcpStWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpStAliquot(Optional.ofNullable(icmssn201.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpStValue(Optional.ofNullable(icmssn201.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withCreditSnAliquot(Optional.ofNullable(icmssn201.getCreditSn()).map(CreditSnValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withCreditSnIcmsValue(Optional.ofNullable(icmssn201.getCreditSn()).map(CreditSnValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CSOSN_202 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN202 icmssn202 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN202) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN202.Builder()
                        .withOrigin(Optional.ofNullable(icmssn202.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModalityST(Optional.ofNullable(icmssn202.getIcmsSt()).map(IcmsStWithBcReductionPercent::getValue).map(IcmsStWithBcValue::getCalculationBasis).map(this::buildIcmsStBcModality).orElse(null))
                        .withValueMarginAddedStPercent(Optional.ofNullable(icmssn202.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).filter(IcmsStBcMarginAddedValue.class::isInstance).map(IcmsStBcMarginAddedValue.class::cast).map(IcmsStBcMarginAddedValue::getMarginAddedPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcReductionStPercent(Optional.ofNullable(icmssn202.getIcmsSt()).map(IcmsStWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValueST(Optional.ofNullable(icmssn202.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStAliquot(Optional.ofNullable(icmssn202.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsStValue(Optional.ofNullable(icmssn202.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValueST(Optional.ofNullable(icmssn202.getFcpSt()).map(FcpStWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpStAliquot(Optional.ofNullable(icmssn202.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpStValue(Optional.ofNullable(icmssn202.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CSOSN_203 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN203 icmssn203 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN203) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN203.Builder()
                        .withOrigin(Optional.ofNullable(icmssn203.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModalityST(Optional.ofNullable(icmssn203.getIcmsSt()).map(IcmsStWithBcReductionPercent::getValue).map(IcmsStWithBcValue::getCalculationBasis).map(this::buildIcmsStBcModality).orElse(null))
                        .withValueMarginAddedStPercent(Optional.ofNullable(icmssn203.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).filter(IcmsStBcMarginAddedValue.class::isInstance).map(IcmsStBcMarginAddedValue.class::cast).map(IcmsStBcMarginAddedValue::getMarginAddedPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcReductionStPercent(Optional.ofNullable(icmssn203.getIcmsSt()).map(IcmsStWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValueST(Optional.ofNullable(icmssn203.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStAliquot(Optional.ofNullable(icmssn203.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsStValue(Optional.ofNullable(icmssn203.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValueST(Optional.ofNullable(icmssn203.getFcpSt()).map(FcpStWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpStAliquot(Optional.ofNullable(icmssn203.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpStValue(Optional.ofNullable(icmssn203.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CSOSN_500 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN500 icmssn500 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN500) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN500.Builder()
                        .withOrigin(Optional.ofNullable(icmssn500.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcRetainedValueST(Optional.ofNullable(icmssn500.getIcmsStRetained()).map(IcmsStRetainedValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withEndConsumerSupportedAliquot(Optional.ofNullable(icmssn500.getEndConsumerSupportedAliquot()).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withIcmsSubstituteValue(Optional.ofNullable(icmssn500.getIcmsSubstituteValue()).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsRetainedValueST(Optional.ofNullable(icmssn500.getIcmsStRetained()).map(IcmsStRetainedValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpRetainedValueST(Optional.ofNullable(icmssn500.getFcpStRetained()).map(FcpStRetainedValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpRetainedAliquotST(Optional.ofNullable(icmssn500.getFcpStRetained()).map(FcpStRetainedValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpRetainedValueST(Optional.ofNullable(icmssn500.getFcpStRetained()).map(FcpStRetainedValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            case CSOSN_900 : {
                final eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN900 icmssn900 = (eprecise.efiscal4j.nfe.item.tax.icms.ICMSSN900) icms;
                return new eprecise.efiscal4j.nfe.v400.tax.icms.ICMSSN900.Builder()
                        .withOrigin(Optional.ofNullable(icmssn900.getOrigin()).map(o -> ProductOrigin.findByCode(o.getValue())).orElse(null))
                        .withBcModality(Optional.ofNullable(icmssn900.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(this::buildIcmsBcModality).orElse(null))
                        .withBcReductionPercent(Optional.ofNullable(icmssn900.getIcms()).map(IcmsWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValue(Optional.ofNullable(icmssn900.getIcmsWithBcValue()).map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsAliquot(Optional.ofNullable(icmssn900.getIcmsWithBcValue()).map(IcmsWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsValue(Optional.ofNullable(icmssn900.getIcmsWithBcValue()).map(IcmsWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcModalityST(Optional.ofNullable(icmssn900.getIcmsSt()).map(IcmsStWithBcReductionPercent::getValue).map(IcmsStWithBcValue::getCalculationBasis).map(this::buildIcmsStBcModality).orElse(null))
                        .withValueMarginAddedStPercent(Optional.ofNullable(icmssn900.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).filter(IcmsStBcMarginAddedValue.class::isInstance).map(IcmsStBcMarginAddedValue.class::cast).map(IcmsStBcMarginAddedValue::getMarginAddedPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcReductionStPercent(Optional.ofNullable(icmssn900.getIcmsSt()).map(IcmsStWithBcReductionPercent::getBcReductionPercent).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withBcValueST(Optional.ofNullable(icmssn900.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withIcmsStAliquot(Optional.ofNullable(icmssn900.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withIcmsStValue(Optional.ofNullable(icmssn900.getIcmsStWithBcValue()).map(IcmsStWithBcValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withBcFcpValueST(Optional.ofNullable(icmssn900.getFcpSt()).map(FcpStWithBcValue::getCalculationBasis).map(this::formatNFeDecimal1302).orElse(null))
                        .withFcpStAliquot(Optional.ofNullable(icmssn900.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getAliquot).map(this::formatNFeDecimal0302a04Optional).orElse(null))
                        .withFcpStValue(Optional.ofNullable(icmssn900.getFcpSt()).map(FcpStWithBcValue::getValue).map(FcpStValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .withCreditSnAliquot(Optional.ofNullable(icmssn900.getCreditSn()).map(CreditSnValue::getAliquot).map(this::formatNFeDecimal0302a04).orElse(null))
                        .withCreditSnIcmsValue(Optional.ofNullable(icmssn900.getCreditSn()).map(CreditSnValue::getValue).map(this::formatNFeDecimal1302).orElse(null))
                        .build();
            }
            default:
                break;
            }
        }
        return null;
     // @formatter:on
    }

    private BCModality buildIcmsBcModality(final IcmsBc calculationBasis) {
     // @formatter:off
        if(calculationBasis instanceof IcmsBcOperationValue) {
            return BCModality.VALOR_OPERACAO;
        } else if(calculationBasis instanceof IcmsBcDeterminedPautaValue) {
            return BCModality.PAUTA;
        } else if(calculationBasis instanceof IcmsBcMarginAddedValue) {
            return BCModality.MARGEM_VALOR_AGREGADO;
        } else if(calculationBasis instanceof IcmsBcMaximumTabulatedPrice) {
            return BCModality.PRECO_TABELADO_MAX;
        }
        return null;
     // @formatter:on
    }

    private BCModalityST buildIcmsStBcModality(final IcmsStBc calculationBasis) {
     // @formatter:off
        if(calculationBasis instanceof IcmsStBcMaximumTabulatedOrSuggestedPrice) {
            return BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO;
        } else if(calculationBasis instanceof IcmsStBcNegativeListValue) {
            return BCModalityST.LISTA_NEGATIVA;
        } if(calculationBasis instanceof IcmsStBcPositiveListValue) {
            return BCModalityST.LISTA_POSITIVA;
        } if(calculationBasis instanceof IcmsStBcNeutralListValue) {
            return BCModalityST.LISTA_NEUTRA;
        } if(calculationBasis instanceof IcmsStBcMarginAddedValue) {
            return BCModalityST.MARGEM_VALOR_AGREGADO;
        } if(calculationBasis instanceof IcmsStBcDeterminedPautaValue) {
            return BCModalityST.PAUTA;
        }
     // @formatter:on
        return null;
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

    private String formatNFeDecimal1104(final BigDecimal value) {
        if (value == null) {
            return null;
        } else if ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0)) {
            return "0";
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

    private String formatNFeDecimal1204Temperature(final BigDecimal value) {
        if (value == null) {
            return null;
        } else if ((value != null) && (value.compareTo(BigDecimal.ZERO) == 0)) {
            return "0";
        } else {
            return NFE_FOUR_DECIMALS_FORMAT.format(value);
        }
    }

}

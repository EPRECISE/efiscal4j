
package eprecise.efiscal4j.nfe.v310.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eprecise.efiscal4j.commons.domain.CFOP;
import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.nfe.v310.CRT;
import eprecise.efiscal4j.nfe.v310.DANFEPrintFormat;
import eprecise.efiscal4j.nfe.v310.DestinationOperationIdentifier;
import eprecise.efiscal4j.nfe.v310.FinalCustomerOperation;
import eprecise.efiscal4j.nfe.v310.FiscalDocumentType;
import eprecise.efiscal4j.nfe.v310.ItemValueComprisesTotal;
import eprecise.efiscal4j.nfe.v310.NFe;
import eprecise.efiscal4j.nfe.v310.NFeDetail;
import eprecise.efiscal4j.nfe.v310.NFeFinality;
import eprecise.efiscal4j.nfe.v310.NFeIdentification;
import eprecise.efiscal4j.nfe.v310.NFeInfo;
import eprecise.efiscal4j.nfe.v310.NFeItem;
import eprecise.efiscal4j.nfe.v310.NFeTransmissionMethod;
import eprecise.efiscal4j.nfe.v310.NFeTransmissionProcess;
import eprecise.efiscal4j.nfe.v310.PaymentMethodIndicator;
import eprecise.efiscal4j.nfe.v310.PurchaserPresenceIndicator;
import eprecise.efiscal4j.nfe.v310.StateRegistrationReceiverIndicator;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.additionalinfo.AdditionalInfo;
import eprecise.efiscal4j.nfe.v310.additionalinfo.CustomizedObservation;
import eprecise.efiscal4j.nfe.v310.additionalinfo.ProcessOrigin;
import eprecise.efiscal4j.nfe.v310.additionalinfo.ReferencedProcess;
import eprecise.efiscal4j.nfe.v310.address.Address;
import eprecise.efiscal4j.nfe.v310.address.City;
import eprecise.efiscal4j.nfe.v310.address.Country;
import eprecise.efiscal4j.nfe.v310.charging.Duplicate;
import eprecise.efiscal4j.nfe.v310.charging.Invoice;
import eprecise.efiscal4j.nfe.v310.charging.NFeCharging;
import eprecise.efiscal4j.nfe.v310.item.di.Addition;
import eprecise.efiscal4j.nfe.v310.item.di.ImportDeclaration;
import eprecise.efiscal4j.nfe.v310.item.di.IntermediaryImportType;
import eprecise.efiscal4j.nfe.v310.item.di.InternationalTransportPathway;
import eprecise.efiscal4j.nfe.v310.nfce.CSC;
import eprecise.efiscal4j.nfe.v310.payment.CardFlag;
import eprecise.efiscal4j.nfe.v310.payment.CardSet;
import eprecise.efiscal4j.nfe.v310.payment.NFePayment;
import eprecise.efiscal4j.nfe.v310.payment.PaymentIntegrationType;
import eprecise.efiscal4j.nfe.v310.payment.PaymentMethod;
import eprecise.efiscal4j.nfe.v310.person.Emitter;
import eprecise.efiscal4j.nfe.v310.person.Receiver;
import eprecise.efiscal4j.nfe.v310.refdocuments.ProducerReferencedNF;
import eprecise.efiscal4j.nfe.v310.refdocuments.ProducerReferencedNF.ProducerReferencedNFModel;
import eprecise.efiscal4j.nfe.v310.refdocuments.ReferencedDocuments;
import eprecise.efiscal4j.nfe.v310.refdocuments.ReferencedECF;
import eprecise.efiscal4j.nfe.v310.refdocuments.ReferencedECF.ReferecedECFModel;
import eprecise.efiscal4j.nfe.v310.refdocuments.ReferencedNF;
import eprecise.efiscal4j.nfe.v310.sharing.BatchReceipt;
import eprecise.efiscal4j.nfe.v310.sharing.BatchReceiptSearch;
import eprecise.efiscal4j.nfe.v310.sharing.BatchReceiptSearchResponse;
import eprecise.efiscal4j.nfe.v310.sharing.CancellationRequestResponse;
import eprecise.efiscal4j.nfe.v310.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.v310.sharing.EventProtocol;
import eprecise.efiscal4j.nfe.v310.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.v310.sharing.NFeDispatchResponse;
import eprecise.efiscal4j.nfe.v310.sharing.NFeNumberDisableDispatch;
import eprecise.efiscal4j.nfe.v310.sharing.NFeNumberDisableInfo;
import eprecise.efiscal4j.nfe.v310.sharing.NFeStatusSearch;
import eprecise.efiscal4j.nfe.v310.sharing.NFeStatusSearchResponse;
import eprecise.efiscal4j.nfe.v310.sharing.ProcessedNFe;
import eprecise.efiscal4j.nfe.v310.sharing.ProcessingStatusProtocol;
import eprecise.efiscal4j.nfe.v310.sharing.ProcessingStatusProtocolInfo;
import eprecise.efiscal4j.nfe.v310.sharing.ServiceStatusSearch;
import eprecise.efiscal4j.nfe.v310.sharing.ServiceStatusSearchResponse;
import eprecise.efiscal4j.nfe.v310.sharing.SynchronousProcessing;
import eprecise.efiscal4j.nfe.v310.tax.Tax;
import eprecise.efiscal4j.nfe.v310.tax.cofins.COFINS;
import eprecise.efiscal4j.nfe.v310.tax.cofins.COFINSST;
import eprecise.efiscal4j.nfe.v310.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.v310.tax.icms.ICMSUFReceiver;
import eprecise.efiscal4j.nfe.v310.tax.icms.InterstateICMSUFAliquot;
import eprecise.efiscal4j.nfe.v310.tax.icms.ProductOrigin;
import eprecise.efiscal4j.nfe.v310.tax.ii.II;
import eprecise.efiscal4j.nfe.v310.tax.ipi.IPI;
import eprecise.efiscal4j.nfe.v310.tax.pis.PIS;
import eprecise.efiscal4j.nfe.v310.total.ICMSTotal;
import eprecise.efiscal4j.nfe.v310.total.NFeTotal;
import eprecise.efiscal4j.nfe.v310.transmission.NFeBody;
import eprecise.efiscal4j.nfe.v310.transmission.NFeHeader;
import eprecise.efiscal4j.nfe.v310.transmission.ReceivableWithQName;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPBody;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPBodyResponse;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPEnvelope;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPEnvelopeResponse;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPHeader;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPHeaderResponse;
import eprecise.efiscal4j.nfe.v310.transmission.TransmissionChannel;
import eprecise.efiscal4j.nfe.v310.transport.NFeTransport;
import eprecise.efiscal4j.nfe.v310.transport.ShippingModality;
import eprecise.efiscal4j.nfe.v310.transport.TransportedVolume;
import eprecise.efiscal4j.nfe.v310.transport.VolumeSeal;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


public class TestDomain {

    private static final String EMITTER_CSC_CLDTOKEN_PROPERTY = "eprecise.efiscal4j.nfce.emitter.csc.cldtoken";

    private static final String EMITTER_CSC_VALUE_PROPERTY = "eprecise.efiscal4j.nfce.emitter.csc.value";

    private static final String EMITTER_CNPJ_PROPERTY = "eprecise.efiscal4j.nfe.emitter.cnpj";

    private static final String EMITTER_IE_PROPERTY = "eprecise.efiscal4j.nfe.emitter.ie";

    private static final String RECEIVER_LEGAL_ENTITY_CORPORATENAME_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.corporatename";

    private static final String RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.cnpj";

    private static final String RECEIVER_LEGAL_ENTITY_IE_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.ie";

    private static final String RECEIVER_NATURAL_PERSON_CPF_PROPERTY = "eprecise.efiscal4j.nfe.receiver.naturalperson.cpf";

    private static final String RECEIVER_NATURAL_PERSON_IE_PROPERTY = "eprecise.efiscal4j.nfe.receiver.naturalperson.ie";

    private static final String CERTIFICATE_PIN_PROPERTY = "eprecise.efiscal4j.commons.certificate.pin";

    private static final String CERTIFICATE_PATH_PROPERTY = "eprecise.efiscal4j.commons.certificate.path";

    private static final String CERTIFICATE_NOT_PRESENT_MESSAGE = "Certificado ou pin não estão presente";

    private static final String FIELD_NOT_PRESENT_MESSAGE = "O campo {0} não estão presente nos argumentos em \"Run Configurations\"";

    private final Logger logger = LoggerFactory.getLogger(TestDomain.class);

    private FiscalDocumentValidator validator;

    private final DefaultSigner signer;

    private final TransmissionChannel transmissionChannel;

    private final CSC emitterCsc;

    private final String emitterCnpj;

    private final String emitterIe;

    private final String receiverLegalEntityCorporateName;

    private final String receiverLegalEntityCnpj;

    private final String receiverLegalEntityIe;

    private final String receiverNaturalPersonCpf;

    private final String receiverNaturalPersonIe;

    public TestDomain() {
        try {
            this.emitterCnpj = System.getProperty(TestDomain.EMITTER_CNPJ_PROPERTY);
            this.emitterIe = System.getProperty(TestDomain.EMITTER_IE_PROPERTY);
            this.receiverLegalEntityCorporateName = System.getProperty(TestDomain.RECEIVER_LEGAL_ENTITY_CORPORATENAME_PROPERTY);
            this.receiverLegalEntityCnpj = System.getProperty(TestDomain.RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY);
            this.receiverLegalEntityIe = System.getProperty(TestDomain.RECEIVER_LEGAL_ENTITY_IE_PROPERTY);
            this.receiverNaturalPersonCpf = System.getProperty(TestDomain.RECEIVER_NATURAL_PERSON_CPF_PROPERTY);
            this.receiverNaturalPersonIe = System.getProperty(TestDomain.RECEIVER_NATURAL_PERSON_IE_PROPERTY);

            final String emitterCscCldToken = System.getProperty(TestDomain.EMITTER_CSC_CLDTOKEN_PROPERTY);
            final String emitterCscValue = System.getProperty(TestDomain.EMITTER_CSC_VALUE_PROPERTY);
            if (StringUtils.isEmpty(emitterCscCldToken) || StringUtils.isEmpty(emitterCscValue)) {
                this.emitterCsc = null;
            } else {
                this.emitterCsc = new CSC(emitterCscCldToken, emitterCscValue);
            }

            final String certificatePath = System.getProperty(TestDomain.CERTIFICATE_PATH_PROPERTY);
            final String certificatePin = System.getProperty(TestDomain.CERTIFICATE_PIN_PROPERTY);
            if (StringUtils.isEmpty(certificatePath) || StringUtils.isEmpty(certificatePin)) {
                this.signer = null;
                this.transmissionChannel = null;
            } else {
                final Certificate keyCertificate = new Certificate(() -> new FileInputStream(certificatePath), certificatePin);
                this.signer = new DefaultSigner(keyCertificate);
                this.transmissionChannel = new TransmissionChannel(keyCertificate);
            }
        } catch (final Exception ex) {
            this.getLogger().error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public TestDomain(final String xsdPath) {
        this();
        this.setXsdPath(xsdPath);
    }

    private boolean containsCertificate() {
        return (this.signer != null) && (this.transmissionChannel != null);
    }

    private void assertCertificate() {
        if (!this.containsCertificate()) {
            throw new IllegalStateException(TestDomain.CERTIFICATE_NOT_PRESENT_MESSAGE);
        }
    }

    public void setXsdPath(final String xsdPath) {
        try {
            this.validator = new FiscalDocumentValidator(this.getClass().getResource(xsdPath));
        } catch (final IOException ex) {
            this.getLogger().error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public SOAPEnvelope buildSoapEnvelope(final SOAPHeader soapHeader, final SOAPBody soapBody) {
        return SoapEnvelopeDomain.getInstance().buildSoapEnvelope(soapHeader, soapBody);
    }

    public SOAPHeader buildSoapHeader(final NFeHeader nFeHeader) {
        return SoapEnvelopeDomain.getInstance().buildSoapHeader(nFeHeader);
    }

    public SOAPBody buildSoapBody(final NFeBody nFeBody) {
        return SoapEnvelopeDomain.getInstance().buildSoapBody(nFeBody);
    }

    public NFeHeader buildNFeHeader(final String xmlns, final UF uf) {
        return SoapEnvelopeDomain.getInstance().buildNFeHeader(xmlns, uf);
    }

    public NFeHeader buildNFeHeader(final String xmlns, final UF uf, final FiscalDocumentVersion dataVersion) {
        return SoapEnvelopeDomain.getInstance().buildNFeHeader(xmlns, uf, dataVersion);
    }

    public NFeBody buildNFeBody(final String xmlns, final TransmissibleBodyImpl transmissible) {
        return SoapEnvelopeDomain.getInstance().buildNFeBody(xmlns, transmissible);
    }

    public SOAPEnvelopeResponse buildSOAPEnvelopeResponse(final SOAPHeaderResponse soapHeaderResponse, final SOAPBodyResponse soapBodyResponse) {
        return SoapEnvelopeDomain.getInstance().buildSOAPEnvelopeResponse(soapHeaderResponse, soapBodyResponse);
    }

    public SOAPHeaderResponse buildSoapHeaderResponse(final NFeHeader nFeHeader) {
        return SoapEnvelopeDomain.getInstance().buildSoapHeaderResponse(nFeHeader);
    }

    public SOAPBodyResponse buildSoapBodyResponse(final ReceivableWithQName receivable) {
        return SoapEnvelopeDomain.getInstance().buildSoapBodyResponse(receivable);
    }

    public NFe buildNFe() throws Exception {
        this.assertCertificate();
        //@formatter:off

        final List<Addition> additionList = new ArrayList<>();
        additionList.add(new Addition.Builder()
                        .withDiscountValue("3.00")
                        .withDrawbackNumber("4444")
                        .withManufacturerCode("2222")
                        .withNumber("111")
                        .withSequence("1")
                        .build());


        final List<ImportDeclaration> importDeclarationList = new ArrayList<>();
        importDeclarationList.add(new ImportDeclaration.Builder()
                                 .withNumber("001")
                                 .withDate("2016-02-01")
                                 .withClearanceSpot("Everywhere")
                                 .withClearanceUf(UF.AC)
                                 .withClearanceDate("2016-01-01")
                                 .withInternationalTransportPathway(InternationalTransportPathway.LACUSTRE)
                                 .withAdditValShipMerchMarineRenovation("10.00")
                                 .withIntermediaryImportType(IntermediaryImportType.POR_CONTA_PROPRIA)
                                 .withAcquirerOrOrderingPartyCnpj("88088688000154")
                                 .withAcquirerOrOrderingPartyUf(UF.AC)
                                 .withExporterCode("1234")
                                 .withAdditions(additionList)
                                 .build());


        final List<NFeDetail> nFeDetailList = new ArrayList<>();
        nFeDetailList.add(new NFeDetail.Builder()
                         .withItemOrder("1")
                         .withNFeItem(
                                 new NFeItem.Builder()
                                .withCFOP(CFOP.CFOP_5949)
                                .withComercialQuantity("1")
                                .withComercialUnit("UN")
                                .withComercialUnitaryValue("10.00")
                                .withGlobalTradeItemNumber("123456789012")
                                .withItemCode("999")
                                .withItemDescription("Produto de teste")
                                .withItemGrossValue("10.00")
                                .withItemValueComprisesTotal(ItemValueComprisesTotal.COMPOE_TOTAL)
                                .withNCM("99999999")
                                .withCest("0000001")
                                .withTaxableQuantity("1")
                                .withTaxableUnit("UN")
                                .withTaxableUnitGlobalTradeItemNumber("123456789012")
                                .withTaxationUnitaryValue("10.00")
                                .withImportDeclarations(importDeclarationList)
                                .build())
                         .withTax(
                             new Tax.Builder()
                             //ICMS00
//                          .withIcms(new ICMS.Builder()
//                                        .fromCode(ICMS.CST_00)
//                                        .withOrigin(ProductOrigin.NACIONAL)
//                                        .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                        .withBcValue("10.00")
//                                        .withIcmsAliquot("10.00")
//                                        .withIcmsValue("1.00")
//                                        .build())
                             //ICMS10
//                          .withIcms(new ICMS.Builder()
//                                        .fromCode(ICMS.CST_10)
//                                        .withOrigin(ProductOrigin.NACIONAL)
//                                        .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                        .withBcValue("10.00")
//                                        .withIcmsAliquot("1.00")
//                                        .withIcmsValue("10.00")
//                                        .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                        .withValueMarginAddedStPercent("10.00")
//                                        .withBcReductionStPercent("1.00")
//                                        .withBcValueST("10.00")
//                                        .withIcmsStAliquot("1.00")
//                                        .withIcmsStValue("10.00")
//                                        .build())
                             //ICMS20
//                          .withIcms(new ICMS.Builder()
//                                        .fromCode(ICMS.CST_20)
//                                        .withOrigin(ProductOrigin.NACIONAL)
//                                        .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                        .withBcReductionPercent("5.00")
//                                        .withBcValue("10.00")
//                                        .withIcmsAliquot("1.00")
//                                        .withIcmsValue("10.00")
//                                        .withIcmsDesonerationValue("2")
//                                        .withIcmsDesonerationReason(ICMSDesonerationReason.OUTROS)
//                                        .build())
                             //ICMS30
//                          .withIcms(new ICMS.Builder()
//                                        .fromCode(ICMS.CST_30)
//                                        .withOrigin(ProductOrigin.NACIONAL)
//                                        .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                        .withValueMarginAddedStPercent("10.00")
//                                        .withBcReductionStPercent("1.00")
//                                        .withBcValueST("10.00")
//                                        .withIcmsStAliquot("1.00")
//                                        .withIcmsStValue("10.00")
//                                        .withIcmsDesonerationValue("2")
//                                        .withIcmsDesonerationReason(ICMSDesonerationReason.SUFRAMA)
//                                        .build())
                             //ICMS40, ICMS41, ICMS50
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CST_40)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withIcmsDesonerationValue("2")
//                                          .withIcmsDesonerationReason(ICMSDesonerationReason.OLIMPIADAS_RIO_2016)
//                                          .build())
                             //ICMS51
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CST_51)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                            .withBcReductionPercent("5.00")
//                                          .withBcValue("10.00")
//                                          .withIcmsAliquot("1.00")
//                                          .withIcmsOperationValue("10")
//                                          .withDeferralPercent("5")
//                                          .withIcmsDeferralValue("0.50")
//                                          .withIcmsValue("10.00")
//                                          .build())
                             //ICMS60
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CST_60)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcRetainedValueST("2.00")
//                                            .withIcmsRetainedValueST("1")
//                                          .build())
                             //ICMS70
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CST_70)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                          .withBcReductionPercent("2")
//                                          .withBcValue("10.00")
//                                          .withIcmsAliquot("1.00")
//                                          .withIcmsValue("10.00")
//                                          .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                          .withValueMarginAddedStPercent("10.00")
//                                          .withBcReductionStPercent("1.00")
//                                          .withBcValueST("10.00")
//                                          .withIcmsStAliquot("1.00")
//                                          .withIcmsStValue("10.00")
//                                          .withIcmsDesonerationValue("2")
//                                          .withIcmsDesonerationReason(ICMSDesonerationReason.FOMENTO_AGROPECUARIO)
//                                          .build())
                             //ICMS90
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CST_90)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                          .withBcReductionPercent("2")
//                                          .withBcValue("10.00")
//                                          .withIcmsAliquot("1.00")
//                                          .withIcmsValue("10.00")
//                                          .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                          .withValueMarginAddedStPercent("10.00")
//                                          .withBcReductionStPercent("1.00")
//                                          .withBcValueST("10.00")
//                                          .withIcmsStAliquot("1.00")
//                                          .withIcmsStValue("10.00")
//                                          .withIcmsDesonerationValue("2")
//                                          .withIcmsDesonerationReason(ICMSDesonerationReason.FOMENTO_AGROPECUARIO)
//                                          .build())
                             //ICMSPart10 e ICMSPart90
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.PART_CST_90)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                          .withBcReductionPercent("2")
//                                          .withBcValue("10.00")
//                                          .withIcmsAliquot("1.00")
//                                          .withIcmsValue("10.00")
//                                          .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                          .withValueMarginAddedStPercent("10.00")
//                                          .withBcReductionStPercent("1.00")
//                                          .withBcValueST("10.00")
//                                          .withIcmsStAliquot("1.00")
//                                          .withIcmsStValue("10.00")
//                                          .withSelfOperationBCPerc("3")
//                                          .withUfST(UF.PR)
//                                          .build())
                             //ICMSST
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.ST_CST_41)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcRetainedValueST("2.00")
//                                            .withIcmsRetainedValueST("1")
//                                            .withBcIcmsStDestination("5.00")
//                                            .withIcmsStDestination("3.00")
//                                          .build())
                             //ICMSSN101
                              .withIcms(new ICMS.Builder()
                                           .fromCode(ICMS.CSOSN_101)
                                           .withOrigin(ProductOrigin.NACIONAL)
                                           .withCreditSnAliquot("10.00")
                                           .withCreditSnIcmsValue("100.00")
                                           .build())
                             //ICMSSN102, ICMSSN103, ICMSSN300 e ICMSSN400
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CSOSN_400)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .build())
                             //ICMSSN201
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CSOSN_201)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                          .withValueMarginAddedStPercent("10.00")
//                                          .withBcReductionStPercent("1.00")
//                                          .withBcValueST("10.00")
//                                          .withIcmsStAliquot("1.00")
//                                          .withIcmsStValue("10.00")
//                                          .withCreditSnAliquot("10.00")
//                                          .withCreditSnIcmsValue("100.00")
//                                          .build())
                             //ICMSSN202, ICMSSN203
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CSOSN_203)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                          .withValueMarginAddedStPercent("10.00")
//                                          .withBcReductionStPercent("1.00")
//                                          .withBcValueST("10.00")
//                                          .withIcmsStAliquot("1.00")
//                                          .withIcmsStValue("10.00")
//                                          .build())
                             //ICMSSN500
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CSOSN_500)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcRetainedValueST("2.00")
//                                            .withIcmsRetainedValueST("1")
//                                          .build())
                             //ICMSSN900
//                                .withIcms(new ICMS.Builder()
//                                        .fromCode(ICMS.CSOSN_900)
//                                        .withOrigin(ProductOrigin.NACIONAL)
//                                        .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                        .withBcReductionPercent("2")
//                                        .withBcValue("10.00")
//                                        .withIcmsAliquot("1.00")
//                                        .withIcmsValue("10.00")
//                                        .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                        .withValueMarginAddedStPercent("10.00")
//                                        .withBcReductionStPercent("1.00")
//                                        .withBcValueST("10.00")
//                                        .withIcmsStAliquot("1.00")
//                                        .withIcmsStValue("10.00")
//                                        .withCreditSnAliquot("10.00")
//                                        .withCreditSnIcmsValue("100.00")
//                                        .build())
                              //ICMSUFDest
                            .withIcmsUfReceiver(new ICMSUFReceiver.Builder()
                                                   .withReceiverUfBcValue("2.00")
                                                   .withReceiverUfFCPPercentual("1.00")
                                                   .withReceiverUfIcmsAliquot("5.00")
                                                   .withInterstateIcmsUfAliquot(InterstateICMSUFAliquot.PRODUTOS_IMPORTADOS)
                                                   .withReceiverUfSharePercentual("40.00")
                                                   .withReceiverUfFCPValue("0.10")
                                                   .withReceiverUfIcmsShareValue("2.00")
                                                   .withEmitterUfIcmsShareValue("2.00")
                                                   .build())

                             //IPI00, IPI49, IPI50, IPI99
                                .withIpi(new IPI.Builder()
                                            .fromCode(IPI.CST_99)
                                            .withLegalFramework("999")
                                            .withUnityQuantity("1")
                                            .withUnityValue("3")
                                            .withIpiValue("3")
                                            .build())
                             //IPI01, IPI02, IPI03, IPI04, IPI05, IPI51, IPI52, IPI53, IPI54, IPI55
//                                .withIpi(new IPI.Builder()
//                                            .fromCode(IPI.CST_01)
//                                            .withIpiFrameworkClass("2")
//                                            .withIpiSealCode("33")
//                                            .withIpiSealQuantity("4")
//                                            .withProducerCNPJ("12345678909876")
//                                            .withLegalFramework("999")
//                                            .build())

                             //II
                                .withIi(new II.Builder()
                                            .withBcValue("10")
                                            .withCustomsCharge("0")
                                            .withIiValue("2")
                                            .withIofValue("0")
                                            .build())

                             //PIS01, PIS02
//                                .withPis(new PIS.Builder()
//                                            .fromCode(PIS.CST_01)
//                                            .withBcValue("10.00")
//                                            .withPisAliquot("10")
//                                            .withPisValue("1")
//                                            .build())
                             //PIS03
//                                .withPis(new PIS.Builder()
//                                            .fromCode(PIS.CST_03)
//                                            .withProductQuantity("3.00")
//                                            .withProductAliquot("10")
//                                            .withPisValue("1")
//                                            .build())
                             //PIS04, PIS05, PIS06, PIS07, PIS08, PIS09
//                                .withPis(new PIS.Builder()
//                                            .fromCode(PIS.CST_09)
//                                            .build())
                             //PIS49, PIS50, PIS51, PIS52, PIS53, PIS54, PIS55, PIS56, PIS60, PIS61, PIS62, PIS63, PIS64, PIS65, PIS66, PIS67, PIS70, PIS71, PIS72, PIS73, PIS74, PIS75, PIS98, PIS99
                                .withPis(new PIS.Builder()
                                            .fromCode(PIS.CST_74)
//                                            .withProductQuantity("1.00")
//                                            .withProductAliquot("5.00")
                                            .withBcValue("10")
                                            .withPisAliquot("5")
                                            .withPisValue("0.50")
                                            .build())
                             //PISST
//                                .withPisSt(new PISST.Builder()
//                                            .withProductQuantity("1")
//                                            .withProductAliquot("5")
////                                            .withBcValue("4")
////                                            .withPisAliquot("5")
//                                            .withPisValue("0.50")
//                                            .build())

                             //COFINS01, COFINS02
//                                .withCofins(new COFINS.Builder()
//                                            .fromCode(COFINS.CST_02)
//                                            .withBcValue("10.00")
//                                            .withCofinsAliquot("10")
//                                            .withCofinsValue("1")
//                                            .build())
                             //COFINS03
//                                .withCofins(new COFINS.Builder()
//                                            .fromCode(COFINS.CST_03)
//                                            .withProductQuantity("3.00")
//                                            .withProductAliquot("10")
//                                            .withCofinsValue("1")
//                                            .build())
                             //COFINS04, COFINS05, COFINS06, COFINS07, COFINS08, COFINS09
//                                .withCofins(new COFINS.Builder()
//                                            .fromCode(COFINS.CST_08)
//                                            .build())
                             //COFINS49, COFINS50, COFINS51, COFINS52, COFINS53, COFINS54, COFINS55, COFINS56, COFINS60, COFINS61, COFINS62, COFINS63, COFINS64, COFINS65, COFINS66, COFINS67, COFINS70, COFINS71, COFINS72, COFINS73, COFINS74, COFINS75, COFINS98, COFINS99
                                .withCofins(new COFINS.Builder()
                                            .fromCode(COFINS.CST_74)
//                                            .withProductQuantity("3.00")
//                                            .withProductAliquot("5.00")
                                            .withBcValue("10")
                                            .withCofinsAliquot("5")
                                            .withCofinsValue("0.50")
                                            .build())
                             //COFINSST
                                .withCofinsSt(new COFINSST.Builder()
//                                            .withProductQuantity("3")
//                                            .withProductAliquot("5")
                                            .withBcValue("4")
                                            .withCofinsAliquot("5")
                                            .withCofinsValue("3")
                                            .build())
                            .build())
//                         .withReturnedTax(new ReturnedTax.Builder()
//                                         .withReturnedProductPerc("70")
//                                         .withReturnedIPI(new ReturnedIPI.Builder()
//                                                         .withReturnedIPIValue("7.00")
//                                                         .build())
//                                         .build())
                         .withAdditionalProductInfo("Informações adicionais do produto (norma referenciada, informações complementares, etc)")
                         .build());

        final List<VolumeSeal> seals = new ArrayList<>();
        seals.add(new VolumeSeal.Builder()
                 .withSealNumber("Número do Lacre 33")
                 .build());

        final List<TransportedVolume> transportedVolumes = new ArrayList<>();
        transportedVolumes.add(new TransportedVolume.Builder()
                              .withVolumeQuantity("3")
                              .withVolumeSpecies("Espécie teste")
                              .withVolumeTrademark("Marca Teste")
                              .withVolumeNumbering("numeração teste 01")
                              .withNetWeight("55.555")
                              .withGrossWeight("60.000")
                              .withSeals(seals)
                              .build());

        final List<Duplicate> duplicates = new ArrayList<>();
        duplicates.add(
                  new Duplicate.Builder()
                 .withNumber("1")
                 .withDueDate("2014-12-07")
                 .withValue("10")
                 .build());

        final List<CustomizedObservation> taxpayerObservations = new ArrayList<>();
        taxpayerObservations.add(
                            new CustomizedObservation.Builder()
                           .withText("Texto teste para observação customizada")
                           .withField("campo_teste")
                           .build());
        taxpayerObservations.add(
                new CustomizedObservation.Builder()
               .withText("Texto teste para observação customizada 2")
               .withField("campo_teste_2")
               .build());

        final List<CustomizedObservation> fiscoObservations = new ArrayList<>();
        fiscoObservations.add(
                            new CustomizedObservation.Builder()
                           .withText("Texto teste para observação customizada")
                           .withField("campo_teste")
                           .build());
        fiscoObservations.add(
                new CustomizedObservation.Builder()
               .withText("Texto teste para observação customizada 2")
               .withField("campo_teste_2")
               .build());

        final List<ReferencedProcess> referencedProcesses = new ArrayList<>();
        referencedProcesses.add(
                           new ReferencedProcess.Builder()
                          .withProcessNumber("123")
                          .withProcessOrigin(ProcessOrigin.JUSTICA_FEDERAL)
                          .build());

        final List<ReferencedDocuments> referencedDocuments = new ArrayList<>();

        referencedDocuments.add(
                new ReferencedDocuments.Builder()
               .withReferencedNF(new ReferencedNF.Builder()
                                .withEmissionDate("1512")
                                .withEmitterCnpj("02122908000101")
                                .withEmitterUf(UF.AC)
                                .withNumber("23232")
                                .withSeries("13")
                                .build())
               .build());
        referencedDocuments.add(
                new ReferencedDocuments.Builder()
               .withProducerReferencedNF(new ProducerReferencedNF.Builder()
                                            .withEmissionYearMonth("1601")
                                            .withEmitterCpf("51857496442")
                                            .withEmitterUf(UF.PR)
                                            .withModel(ProducerReferencedNFModel.PRODUCER_NF)
                                            .withNumber("111")
                                            .withSeries("1")
                                            .withStateRegistration("9989777111")
                                            .build())
               .build());

        referencedDocuments.add(
                new ReferencedDocuments.Builder()
               .withReferencedECF(new ReferencedECF.Builder()
                                 .withCooNumber("1231")
                                 .withEcfNumber("222")
                                 .withModel(ReferecedECFModel.NAO_ECF)
                                 .build())
               .build());

        return new NFe.Builder()
            .withNFeInfo(new NFeInfo.Builder()
                         .withNFeIdentification(
                                           new NFeIdentification.Builder()
                                          .withApplicationVersion("1.00")
                                          .withDanfePrintFormat(DANFEPrintFormat.DANFE_RETRATO)
                                          .withDestinationOperationIdentifier(DestinationOperationIdentifier.INTERNA)
                                          .withEmissionDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()))
                                          .withFinalCustomerOperation(FinalCustomerOperation.CONSUMIDOR_FINAL)
                                          .withFiscalDocumentModel(FiscalDocumentModel.NFE)
                                          .withFiscalDocumentNumber("3")
                                          .withFiscalDocumentSeries("0")
                                          .withFiscalDocumentType(FiscalDocumentType.SAIDA)
                                          .withNFeCode("76523280")
                                          .withNFeFinality(NFeFinality.NORMAL)
                                          .withNFeTransmissionMethod(NFeTransmissionMethod.NORMAL)
                                          .withNFeTransmissionProcess(NFeTransmissionProcess.APLICATIVO_CONTRIBUINTE)
                                          .withOperationType("1")
                                          .withPaymentMethod(PaymentMethodIndicator.PAGAMENTO_A_VISTA)
                                          .withPurchaserPresenceIndicator(PurchaserPresenceIndicator.OPERACAO_PRESENCIAL)
                                          .withTaxableEventCityIbgeCode("4104659")
                                          .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                          .withUFIbgeCode(UF.PR)
                                          .withReferencedDocuments(referencedDocuments)
                                          .build())
                         .withEmitter(
                                 new Emitter.Builder()
                                .asLegalEntity()

                                .withCnpj(this.getEmitterCnpj())
                                .withCorporateName("E-PRECISE SOLUCOES E CONSULTORIA EM WEB LTDA - ME")
                                .withCrt(CRT.SIMPLES_NACIONAL)
                                .withFancyName("E-PRECISE SOLUCOES E CONSULTORIA EM WEB")

                                .withStateRegistration(this.getEmitterIe())
                                .withAdress(
                                       new Address.Builder()
                                      .withStreet("Rua 10")
                                      .withNumber("Sem Número")
                                      .withDistrict("Centro")
                                      .withCep("84145000")
                                      .withCity(
                                           new City.Builder()
                                          .withCountry(
                                                  new Country.Builder()
                                                 .withIbgeCode("1058")
                                                 .withDescription("Brasil").build())
                                          .withIbgeCode("4119905")
                                          .withDescription("Ponta Grossa")
                                          .withUF(UF.PR)
                                          .build())
                                      .build())

                                .build())
                         .withReceiver(
//                                  new Receiver.Builder()
//                                 .asNaturalPerson()
//                                 .withCpf("14712931060")
//                                 .withName("Joao")
//                                 .withStateRegistration("2339165443")
////                                 .withMunicipalRegistration("123456789")
//                                 .withAdress(
//                                        new Address.Builder()
//                                       .withStreet("Rua 10")
//                                       .withNumber("Sem Número")
//                                       .withDistrict("Centro")
//                                       .withCep("84145000")
//                                       .withCity(
//                                            new City.Builder()
//                                           .withCountry(
//                                                   new Country.Builder()
//                                                  .withIbgeCode("1058")
//                                                  .withDescription("Brasil")
//                                                  .build())
//                                           .withIbgeCode("4104659")
//                                           .withDescription("Carambeí")
//                                           .withUF(UF.PR)
//                                           .build())
//                                       .build())
//                                 .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE)
//                                 .withEmail("teste")
                                 new Receiver.Builder()
                                .asLegalEntity()
                                .withCnpj(this.getReceiverLegalEntityCnpj())
                                .withCorporateName(this.getReceiverLegalEntityCorporateName())
                                .withStateRegistration(this.getReceiverLegalEntityIe())
//                                .withMunicipalRegistration("123456789")
                                .withAdress(
                                       new Address.Builder()
                                      .withStreet("Rua 10")
                                      .withNumber("Sem Número")
                                      .withDistrict("Centro")
                                      .withCep("83203270")
                                      .withCity(
                                           new City.Builder()
                                          .withCountry(
                                                  new Country.Builder()
                                                 .withIbgeCode("1058")
                                                 .withDescription("Brasil")
                                                 .build())
                                          .withIbgeCode("4118204")
                                          .withDescription("Paranaguá")
                                          .withUF(UF.PR)
                                          .build())
                                      .build())
                                .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE)
                                .withEmail("felipe@e-precise.com.br")
                                 .build())
                         .withNFeDetail(nFeDetailList)
                         .withNFeTotal(
                                  new NFeTotal.Builder()
//                                 .withFederalTaxRetention(
//                                                     new FederalTaxRetention.Builder()
//                                                    .withCOFINSRetainedValue("0.10")
//                                                    .withCSLLRetainedValue("0.10")
//                                                    .withIRRFCalculationBasis("10")
//                                                    .withIRRFRetainedValue("0.10")
//                                                    .withPISRetainedValue("0.10")
//                                                    .withSocialSecurityRetentionCalculationBasis("10")
//                                                    .withSocialSecurityRetentionValue("0.10")
//                                                    .build())
                                 .withICMSTotal(
                                           new ICMSTotal.Builder()
                                          .withDiscountTotalValue("0")
                                          .withICMSCalculationBasis("0")
                                          .withICMSSTCalculationBasis("0")
                                          .withICMSSTTotalValue("0")
                                          .withICMSTotalDesoneration("0")
                                          .withICMSTotalValue("0")
                                          .withReceiverUfFCPTotalValue("1.00")
                                          .withReceiverUfIcmsShareTotalValue("0.20")
                                          .withEmitterUfIcmsShareTotalValue("0.10")
                                          .withInsuranceTotalValue("0")
                                          .withItemsTotalValue("10.00")
                                          .withNFeTotalValue("15.00")
                                          .withOtherIncidentalCostsTotalValue("0")
                                          .withShippingTotalValue("0")
                                          .withTaxTotalValue("0")
                                          .build())
                                 .build())
                         .withNFeTransport(
                                      new NFeTransport.Builder()
                                     .withShippingModality(ShippingModality.SEM_FRETE)
//                                     .withConveyor(
//                                              new Conveyor.Builder()
//                                             .asNaturalPerson()
//                                             .withCpf("67315882537")
//                                             .withName("Transportador Teste")
//                                             .withStateRegistration("1346304347")
//                                             .withFullAddress("Rua 1, Bairro 1")
//                                             .withCity(
//                                                  new City.Builder()
//                                                 .withIbgeCode("4119905")
//                                                 .withDescription("Ponta Grossa")
//                                                 .withUF(UF.PR)
//                                                 .build())
//                                             .build())
//                                     .withtransportICMSRetention(
//                                                            new TransportICMSRetention.Builder()
//                                                           .withServiceValue("10.00")
//                                                           .withRetentionCalculationBasis("10.00")
//                                                           .withRetentionAliquot("10")
//                                                           .withRetentionValue("1.00")
//                                                           .withCfop(CFOP.CFOP_6931)
//                                                           .withGenFactIbgeCode("4119905")
//                                                           .build())
                                     .withTransportedVolume(transportedVolumes)
                                     .build())
                         .withNFeCharging(
                                     new NFeCharging.Builder()
                                    .withInvoice(
                                            new Invoice.Builder()
                                           .withNumber("C33")
                                           .withOriginalValue("10.00")
                                           .withDiscountValue("3.00")
                                           .withNumber("7.00")
                                           .build())
                                    .withDuplicates(duplicates)
                                    .build())
                         .withAdditionalInfo(
                                        new AdditionalInfo.Builder()
                                       .withAdditionalInfoFisco("Informação de uso do fisco")
                                       .withComplementaryInfo("Informação complementar do contribuinte")
                                       .withTaxpayerObservations(taxpayerObservations)
                                       .withFiscoObservations(fiscoObservations)
                                       .withReferencedProcesses(referencedProcesses)
                                       .build()
                                 )
                         .build())
        .build(this.signer);
        //@formatter:on
    }

    public NFe buildNFCe() throws Exception {
        this.assertCertificate();
        //@formatter:off

        final List<Addition> additionList = new ArrayList<>();
        additionList.add(new Addition.Builder()
                        .withDiscountValue("3.00")
                        .withDrawbackNumber("4444")
                        .withManufacturerCode("2222")
                        .withNumber("111")
                        .withSequence("1")
                        .build());


        final List<ImportDeclaration> importDeclarationList = new ArrayList<>();
        importDeclarationList.add(new ImportDeclaration.Builder()
                                 .withNumber("001")
                                 .withDate("2016-02-01")
                                 .withClearanceSpot("Everywhere")
                                 .withClearanceUf(UF.AC)
                                 .withClearanceDate("2016-01-01")
                                 .withInternationalTransportPathway(InternationalTransportPathway.LACUSTRE)
                                 .withAdditValShipMerchMarineRenovation("10.00")
                                 .withIntermediaryImportType(IntermediaryImportType.POR_CONTA_PROPRIA)
                                 .withAcquirerOrOrderingPartyCnpj("88088688000154")
                                 .withAcquirerOrOrderingPartyUf(UF.AC)
                                 .withExporterCode("1234")
                                 .withAdditions(additionList)
                                 .build());


        final List<NFeDetail> nFeDetailList = new ArrayList<>();
        nFeDetailList.add(new NFeDetail.Builder()
                         .withItemOrder("1")
                         .withNFeItem(
                                 new NFeItem.Builder()
                                .withCFOP(CFOP.CFOP_5949)
                                .withComercialQuantity("1")
                                .withComercialUnit("UN")
                                .withComercialUnitaryValue("10.00")
                                .withGlobalTradeItemNumber("123456789012")
                                .withItemCode("999")
                                .withItemDescription("Produto de teste")
                                .withItemGrossValue("10.00")
                                .withItemValueComprisesTotal(ItemValueComprisesTotal.COMPOE_TOTAL)
                                .withNCM("99999999")
                                .withCest("0000001")
                                .withTaxableQuantity("1")
                                .withTaxableUnit("UN")
                                .withTaxableUnitGlobalTradeItemNumber("123456789012")
                                .withTaxationUnitaryValue("10.00")
                                .withImportDeclarations(importDeclarationList)
                                .build())
                         .withTax(
                             new Tax.Builder()
                             //ICMS00
//                          .withIcms(new ICMS.Builder()
//                                        .fromCode(ICMS.CST_00)
//                                        .withOrigin(ProductOrigin.NACIONAL)
//                                        .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                        .withBcValue("10.00")
//                                        .withIcmsAliquot("10.00")
//                                        .withIcmsValue("1.00")
//                                        .build())
                             //ICMS10
//                          .withIcms(new ICMS.Builder()
//                                        .fromCode(ICMS.CST_10)
//                                        .withOrigin(ProductOrigin.NACIONAL)
//                                        .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                        .withBcValue("10.00")
//                                        .withIcmsAliquot("1.00")
//                                        .withIcmsValue("10.00")
//                                        .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                        .withValueMarginAddedStPercent("10.00")
//                                        .withBcReductionStPercent("1.00")
//                                        .withBcValueST("10.00")
//                                        .withIcmsStAliquot("1.00")
//                                        .withIcmsStValue("10.00")
//                                        .build())
                             //ICMS20
//                          .withIcms(new ICMS.Builder()
//                                        .fromCode(ICMS.CST_20)
//                                        .withOrigin(ProductOrigin.NACIONAL)
//                                        .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                        .withBcReductionPercent("5.00")
//                                        .withBcValue("10.00")
//                                        .withIcmsAliquot("1.00")
//                                        .withIcmsValue("10.00")
//                                        .withIcmsDesonerationValue("2")
//                                        .withIcmsDesonerationReason(ICMSDesonerationReason.OUTROS)
//                                        .build())
                             //ICMS30
//                          .withIcms(new ICMS.Builder()
//                                        .fromCode(ICMS.CST_30)
//                                        .withOrigin(ProductOrigin.NACIONAL)
//                                        .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                        .withValueMarginAddedStPercent("10.00")
//                                        .withBcReductionStPercent("1.00")
//                                        .withBcValueST("10.00")
//                                        .withIcmsStAliquot("1.00")
//                                        .withIcmsStValue("10.00")
//                                        .withIcmsDesonerationValue("2")
//                                        .withIcmsDesonerationReason(ICMSDesonerationReason.SUFRAMA)
//                                        .build())
                             //ICMS40, ICMS41, ICMS50
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CST_50)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withIcmsDesonerationValue("2")
//                                          .withIcmsDesonerationReason(ICMSDesonerationReason.OUTROS)
//                                          .build())
                             //ICMS51
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CST_51)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                            .withBcReductionPercent("5.00")
//                                          .withBcValue("10.00")
//                                          .withIcmsAliquot("1.00")
//                                          .withIcmsOperationValue("10")
//                                          .withDeferralPercent("5")
//                                          .withIcmsDeferralValue("0.50")
//                                          .withIcmsValue("10.00")
//                                          .build())
                             //ICMS60
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CST_60)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcRetainedValueST("2.00")
//                                            .withIcmsRetainedValueST("1")
//                                          .build())
                             //ICMS70
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CST_70)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                          .withBcReductionPercent("2")
//                                          .withBcValue("10.00")
//                                          .withIcmsAliquot("1.00")
//                                          .withIcmsValue("10.00")
//                                          .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                          .withValueMarginAddedStPercent("10.00")
//                                          .withBcReductionStPercent("1.00")
//                                          .withBcValueST("10.00")
//                                          .withIcmsStAliquot("1.00")
//                                          .withIcmsStValue("10.00")
//                                          .withIcmsDesonerationValue("2")
//                                          .withIcmsDesonerationReason(ICMSDesonerationReason.FOMENTO_AGROPECUARIO)
//                                          .build())
                             //ICMS90
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CST_90)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                          .withBcReductionPercent("2")
//                                          .withBcValue("10.00")
//                                          .withIcmsAliquot("1.00")
//                                          .withIcmsValue("10.00")
//                                          .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                          .withValueMarginAddedStPercent("10.00")
//                                          .withBcReductionStPercent("1.00")
//                                          .withBcValueST("10.00")
//                                          .withIcmsStAliquot("1.00")
//                                          .withIcmsStValue("10.00")
//                                          .withIcmsDesonerationValue("2")
//                                          .withIcmsDesonerationReason(ICMSDesonerationReason.FOMENTO_AGROPECUARIO)
//                                          .build())
                             //ICMSPart10 e ICMSPart90
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.PART_CST_90)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                          .withBcReductionPercent("2")
//                                          .withBcValue("10.00")
//                                          .withIcmsAliquot("1.00")
//                                          .withIcmsValue("10.00")
//                                          .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                          .withValueMarginAddedStPercent("10.00")
//                                          .withBcReductionStPercent("1.00")
//                                          .withBcValueST("10.00")
//                                          .withIcmsStAliquot("1.00")
//                                          .withIcmsStValue("10.00")
//                                          .withSelfOperationBCPerc("3")
//                                          .withUfST(UF.PR)
//                                          .build())
                             //ICMSST
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.ST_CST_41)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcRetainedValueST("2.00")
//                                            .withIcmsRetainedValueST("1")
//                                            .withBcIcmsStDestination("5.00")
//                                            .withIcmsStDestination("3.00")
//                                          .build())
                             //ICMSSN101
                              .withIcms(new ICMS.Builder()
                                          .fromCode(ICMS.CSOSN_101)
                                          .withOrigin(ProductOrigin.NACIONAL)
                                            .withCreditSnAliquot("10.00")
                                            .withCreditSnIcmsValue("100.00")
                                          .build())
                             //ICMSSN102, ICMSSN103, ICMSSN300 e ICMSSN400
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CSOSN_400)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .build())
                             //ICMSSN201
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CSOSN_201)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                          .withValueMarginAddedStPercent("10.00")
//                                          .withBcReductionStPercent("1.00")
//                                          .withBcValueST("10.00")
//                                          .withIcmsStAliquot("1.00")
//                                          .withIcmsStValue("10.00")
//                                          .withCreditSnAliquot("10.00")
//                                          .withCreditSnIcmsValue("100.00")
//                                          .build())
                             //ICMSSN202, ICMSSN203
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CSOSN_203)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                          .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                          .withValueMarginAddedStPercent("10.00")
//                                          .withBcReductionStPercent("1.00")
//                                          .withBcValueST("10.00")
//                                          .withIcmsStAliquot("1.00")
//                                          .withIcmsStValue("10.00")
//                                          .build())
                             //ICMSSN500
//                              .withIcms(new ICMS.Builder()
//                                          .fromCode(ICMS.CSOSN_500)
//                                          .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcRetainedValueST("2.00")
//                                            .withIcmsRetainedValueST("1")
//                                          .build())
                             //ICMSSN900
//                                .withIcms(new ICMS.Builder()
//                                        .fromCode(ICMS.CSOSN_900)
//                                        .withOrigin(ProductOrigin.NACIONAL)
//                                        .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                        .withBcReductionPercent("2")
//                                        .withBcValue("10.00")
//                                        .withIcmsAliquot("1.00")
//                                        .withIcmsValue("10.00")
//                                        .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                        .withValueMarginAddedStPercent("10.00")
//                                        .withBcReductionStPercent("1.00")
//                                        .withBcValueST("10.00")
//                                        .withIcmsStAliquot("1.00")
//                                        .withIcmsStValue("10.00")
//                                        .withCreditSnAliquot("10.00")
//                                        .withCreditSnIcmsValue("100.00")
//                                        .build())

                              //ICMSUFDest
//                            .withIcmsUfReceiver(new ICMSUFReceiver.Builder()
//                                               .withReceiverUfBcValue("2.00")
//                                               .withReceiverUfFCPPercentual("1.00")
//                                               .withReceiverUfIcmsAliquot("5.00")
//                                               .withInterstateIcmsUfAliquot(InterstateICMSUFAliquot.PRODUTOS_IMPORTADOS)
//                                               .withReceiverUfSharePercentual("40.00")
//                                               .withReceiverUfFCPValue("0.10")
//                                               .withReceiverUfIcmsShareValue("2.00")
//                                               .withEmitterUfIcmsShareValue("2.00")
//                                               .build())

                             //PIS01, PIS02
//                                .withPis(new PIS.Builder()
//                                            .fromCode(PIS.CST_01)
//                                            .withBcValue("10.00")
//                                            .withPisAliquot("10")
//                                            .withPisValue("1")
//                                            .build())
                             //PIS03
//                                .withPis(new PIS.Builder()
//                                            .fromCode(PIS.CST_03)
//                                            .withProductQuantity("3.00")
//                                            .withProductAliquot("10")
//                                            .withPisValue("1")
//                                            .build())
                             //PIS04, PIS05, PIS06, PIS07, PIS08, PIS09
//                                .withPis(new PIS.Builder()
//                                            .fromCode(PIS.CST_09)
//                                            .build())
                             //PIS49, PIS50, PIS51, PIS52, PIS53, PIS54, PIS55, PIS56, PIS60, PIS61, PIS62, PIS63, PIS64, PIS65, PIS66, PIS67, PIS70, PIS71, PIS72, PIS73, PIS74, PIS75, PIS98, PIS99
                                .withPis(new PIS.Builder()
                                            .fromCode(PIS.CST_74)
//                                            .withProductQuantity("1.00")
//                                            .withProductAliquot("5.00")
                                            .withBcValue("10")
                                            .withPisAliquot("5")
                                            .withPisValue("0.50")
                                            .build())
                             //PISST
//                                .withPisSt(new PISST.Builder()
//                                            .withProductQuantity("1")
//                                            .withProductAliquot("5")
////                                            .withBcValue("4")
////                                            .withPisAliquot("5")
//                                            .withPisValue("0.50")
//                                            .build())

                             //COFINS01, COFINS02
//                                .withCofins(new COFINS.Builder()
//                                            .fromCode(COFINS.CST_02)
//                                            .withBcValue("10.00")
//                                            .withCofinsAliquot("10")
//                                            .withCofinsValue("1")
//                                            .build())
                             //COFINS03
//                                .withCofins(new COFINS.Builder()
//                                            .fromCode(COFINS.CST_03)
//                                            .withProductQuantity("3.00")
//                                            .withProductAliquot("10")
//                                            .withCofinsValue("1")
//                                            .build())
                             //COFINS04, COFINS05, COFINS06, COFINS07, COFINS08, COFINS09
//                                .withCofins(new COFINS.Builder()
//                                            .fromCode(COFINS.CST_08)
//                                            .build())
                             //COFINS49, COFINS50, COFINS51, COFINS52, COFINS53, COFINS54, COFINS55, COFINS56, COFINS60, COFINS61, COFINS62, COFINS63, COFINS64, COFINS65, COFINS66, COFINS67, COFINS70, COFINS71, COFINS72, COFINS73, COFINS74, COFINS75, COFINS98, COFINS99
                                .withCofins(new COFINS.Builder()
                                            .fromCode(COFINS.CST_74)
//                                            .withProductQuantity("3.00")
//                                            .withProductAliquot("5.00")
                                            .withBcValue("10")
                                            .withCofinsAliquot("5")
                                            .withCofinsValue("0.50")
                                            .build())
                            .build())
//                         .withReturnedTax(new ReturnedTax.Builder()
//                                         .withReturnedProductPerc("70")
//                                         .withReturnedIPI(new ReturnedIPI.Builder()
//                                                         .withReturnedIPIValue("7.00")
//                                                         .build())
//                                         .build())
                         .withAdditionalProductInfo("Informações adicionais do produto (norma referenciada, informações complementares, etc)")
                         .build());

        final List<VolumeSeal> seals = new ArrayList<>();
        seals.add(new VolumeSeal.Builder()
                 .withSealNumber("Número do Lacre 33")
                 .build());

        final List<TransportedVolume> transportedVolumes = new ArrayList<>();
        transportedVolumes.add(new TransportedVolume.Builder()
                              .withVolumeQuantity("3")
                              .withVolumeSpecies("Espécie teste")
                              .withVolumeTrademark("Marca Teste")
                              .withVolumeNumbering("numeração teste 01")
                              .withNetWeight("55.555")
                              .withGrossWeight("60.000")
                              .withSeals(seals)
                              .build());

        final List<NFePayment> nFePayments = new ArrayList<>();
        nFePayments.add(
                 new NFePayment.Builder()
                .withPaymentMethod(PaymentMethod.DINHEIRO)
                .withPaymentValue("10.00")
                .withCardSet(
                        new CardSet.Builder()
                       .withPaymentIntegrationType(PaymentIntegrationType.INTEGRADO)
                       .withCnpj("01027058000191")
                       .withCardFlag(CardFlag.VISA)
                       .withAuthorizationNumber("123445665")
                       .build())
                .build());

        final List<CustomizedObservation> taxpayerObservations = new ArrayList<>();
        taxpayerObservations.add(
                            new CustomizedObservation.Builder()
                           .withText("Texto teste para observação customizada")
                           .withField("campo_teste")
                           .build());
        taxpayerObservations.add(
                new CustomizedObservation.Builder()
               .withText("Texto teste para observação customizada 2")
               .withField("campo_teste_2")
               .build());

        final List<CustomizedObservation> fiscoObservations = new ArrayList<>();
        fiscoObservations.add(
                            new CustomizedObservation.Builder()
                           .withText("Texto teste para observação customizada")
                           .withField("campo_teste")
                           .build());
        fiscoObservations.add(
                new CustomizedObservation.Builder()
               .withText("Texto teste para observação customizada 2")
               .withField("campo_teste_2")
               .build());

        final List<ReferencedProcess> referencedProcesses = new ArrayList<>();
        referencedProcesses.add(
                           new ReferencedProcess.Builder()
                          .withProcessNumber("123")
                          .withProcessOrigin(ProcessOrigin.JUSTICA_FEDERAL)
                          .build());


        return new NFe.Builder()
            .withNFeInfo(new NFeInfo.Builder()
                         .withNFeIdentification(
                                           new NFeIdentification.Builder()
                                          .withApplicationVersion("1.00")
                                          .withDanfePrintFormat(DANFEPrintFormat.DANFE_RETRATO)
                                          .withDestinationOperationIdentifier(DestinationOperationIdentifier.INTERNA)
                                          .withEmissionDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()))
                                          .withFinalCustomerOperation(FinalCustomerOperation.CONSUMIDOR_FINAL)
                                          .withFiscalDocumentModel(FiscalDocumentModel.NFCE)
                                          .withFiscalDocumentNumber("3")
                                          .withFiscalDocumentSeries("0")
                                          .withFiscalDocumentType(FiscalDocumentType.SAIDA)
                                          .withNFeCode("76523280")
                                          .withNFeFinality(NFeFinality.NORMAL)
                                          .withNFeTransmissionMethod(NFeTransmissionMethod.NORMAL)
                                          .withNFeTransmissionProcess(NFeTransmissionProcess.APLICATIVO_CONTRIBUINTE)
                                          .withOperationType("1")
                                          .withPaymentMethod(PaymentMethodIndicator.PAGAMENTO_A_VISTA)
                                          .withPurchaserPresenceIndicator(PurchaserPresenceIndicator.OPERACAO_PRESENCIAL)
                                          .withTaxableEventCityIbgeCode("4104659")
                                          .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                          .withUFIbgeCode(UF.PR)
                                          .build())
                         .withEmitter(
                                 new Emitter.Builder()
                                .asLegalEntity()

                                .withCnpj(this.getEmitterCnpj())
                                .withCorporateName("EMPRESA TESTE")
                                .withCrt(CRT.SIMPLES_NACIONAL)
                                .withFancyName("EMPRESA TESTE")

                                .withStateRegistration(this.getEmitterIe())
                                .withAdress(
                                       new Address.Builder()
                                      .withStreet("Rua 10")
                                      .withNumber("Sem Número")
                                      .withDistrict("Centro")
                                      .withCep("84145000")
                                      .withCity(
                                           new City.Builder()
                                          .withCountry(
                                                  new Country.Builder()
                                                 .withIbgeCode("1058")
                                                 .withDescription("Brasil").build())
                                          .withIbgeCode("4119905")
                                          .withDescription("Ponta Grossa")
                                          .withUF(UF.PR)
                                          .build())
                                      .build())

                                .build())
                         .withReceiver(
                                  new Receiver.Builder()
                                 .asNaturalPerson()
                                 .withCpf(this.getReceiverNaturalPersonCpf())
                                 .withName("Joao")
                                 .withMunicipalRegistration("123456789")
                                 .withAdress(
                                        new Address.Builder()
                                       .withStreet("Rua 10")
                                       .withNumber("Sem Número")
                                       .withDistrict("Centro")
                                       .withCep("84145000")
                                       .withCity(
                                            new City.Builder()
                                           .withCountry(
                                                   new Country.Builder()
                                                  .withIbgeCode("1058")
                                                  .withDescription("Brasil")
                                                  .build())
                                           .withIbgeCode("4104659")
                                           .withDescription("Carambeí")
                                           .withUF(UF.PR)
                                           .build())
                                       .build())
                                 .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE)
                                 .withEmail("teste")
                                 .build())
                         .withNFeDetail(nFeDetailList)
                         .withNFeTotal(
                                  new NFeTotal.Builder()
//                                 .withFederalTaxRetention(
//                                                     new FederalTaxRetention.Builder()
//                                                    .withCOFINSRetainedValue("0.10")
//                                                    .withCSLLRetainedValue("0.10")
//                                                    .withIRRFCalculationBasis("10")
//                                                    .withIRRFRetainedValue("0.10")
//                                                    .withPISRetainedValue("0.10")
//                                                    .withSocialSecurityRetentionCalculationBasis("10")
//                                                    .withSocialSecurityRetentionValue("0.10")
//                                                    .build())
                                 .withICMSTotal(
                                           new ICMSTotal.Builder()
                                          .withDiscountTotalValue("0")
                                          .withICMSCalculationBasis("0")
                                          .withICMSSTCalculationBasis("0")
                                          .withICMSSTTotalValue("0")
                                          .withICMSTotalDesoneration("0")
                                          .withICMSTotalValue("0")
                                          .withInsuranceTotalValue("0")
                                          .withItemsTotalValue("10.00")
                                          .withNFeTotalValue("15.00")
                                          .withOtherIncidentalCostsTotalValue("0")
                                          .withShippingTotalValue("0")
                                          .withTaxTotalValue("0")
                                          .build())
                                 .build())
                         .withNFeTransport(
                                      new NFeTransport.Builder()
                                     .withShippingModality(ShippingModality.SEM_FRETE)
//                                     .withConveyor(
//                                              new Conveyor.Builder()
//                                             .asNaturalPerson()
//                                             .withCpf("67315882537")
//                                             .withName("Transportador Teste")
//                                             .withStateRegistration("1346304347")
//                                             .withFullAddress("Rua 1, Bairro 1")
//                                             .withCity(
//                                                  new City.Builder()
//                                                 .withIbgeCode("4119905")
//                                                 .withDescription("Ponta Grossa")
//                                                 .withUF(UF.PR)
//                                                 .build())
//                                             .build())
//                                     .withtransportICMSRetention(
//                                                            new TransportICMSRetention.Builder()
//                                                           .withServiceValue("10.00")
//                                                           .withRetentionCalculationBasis("10.00")
//                                                           .withRetentionAliquot("10")
//                                                           .withRetentionValue("1.00")
//                                                           .withCfop(CFOP.CFOP_6931)
//                                                           .withGenFactIbgeCode("4119905")
//                                                           .build())
                                     .withTransportedVolume(transportedVolumes)
                                     .build())
                         .withNFePayments(nFePayments)
                         .withAdditionalInfo(
                                        new AdditionalInfo.Builder()
                                       .withAdditionalInfoFisco("Informação de uso do fisco")
                                       .withComplementaryInfo("Informação complementar do contribuinte")
                                       .withTaxpayerObservations(taxpayerObservations)
                                       .withFiscoObservations(fiscoObservations)
                                       .withReferencedProcesses(referencedProcesses)
                                       .build()
                                 )
                         .build())
           .withCSC(this.getEmitterCsc())
        .build(this.signer);
        //@formatter:on
    }

    public ProcessedNFe buildProcessedNFe() throws Exception {
        //@formatter:off
        return new ProcessedNFe.Builder()
                    .withNfe(this.buildNFe())
                    .withProcessingStatusProtocol(new ProcessingStatusProtocol.Builder()
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
                                              .build())
                    .build();
        //@formatter:on
    }

    public NFeDispatch buildNFeDispatch() throws Exception {
        final List<NFe> nFeList = new ArrayList<>();

        nFeList.add(this.buildNFe());

        //@formatter:off
        return new NFeDispatch.Builder()
                    .withBatchId("1")
                    .withSynchronousProcessing(SynchronousProcessing.SINCRONO)
                    .withNFes(nFeList)
                    .build();
        //@formatter:on
    }

    public NFeDispatchResponse buildNFeDispatchResponse() throws Exception {
        //@formatter:off
        return new NFeDispatchResponse.Builder()
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                     .withApplicationVersion("PR-v3_3_2")
                     .withStatusCode("103")
                     .withStatusDescription("Lote recebido com sucesso")
                     .withServiceUf(UF.PR)
                     .withReceptionDateTime("2013-02-06T14:51:09-02:00")
                     .withBatchReceipt(new BatchReceipt.Builder()
                                             .withReceiptNumber("431000015906453")
                                             .withAverageTime("1")
                                             .build())
                    .build();
        //@formatter:on
    }

    public ArrayList<EventProtocol> buildEventProtocolList() throws Exception {
        return TransmissionDomain.getInstance().buildEventProtocolList(this.getSigner());
    }

    public ProcessingStatusProtocol buildProcessingStatusProtocol() throws Exception {
        return TransmissionDomain.getInstance().buildProcessingStatusProtocol();
    }

    public CancellationRequestResponse buildCancellationRequestResponse() throws Exception {
        return TransmissionDomain.getInstance().buildCancellationRequestResponse();
    }

    public NFeStatusSearch buildNFeStatusSearch() throws Exception {
        return TransmissionDomain.getInstance().buildNFeStatusSearch();
    }

    public NFeStatusSearchResponse buildNFeStatusSearchResponse() throws Exception {
        return TransmissionDomain.getInstance().buildNFeStatusSearchResponse(this.getSigner());
    }

    public BatchReceiptSearch buildBatchReceiptSearch() throws Exception {
        return TransmissionDomain.getInstance().buildBatchReceiptSearch();
    }

    public BatchReceiptSearchResponse buildBatchReceiptSearchResponse() throws Exception {
        return TransmissionDomain.getInstance().buildBatchReceiptSearchResponse();
    }

    public ServiceStatusSearch buildServiceStatusSearch() throws Exception {
        return TransmissionDomain.getInstance().buildServiceStatusSearch();
    }

    public ServiceStatusSearchResponse buildServiceStatusSearchResponse() throws Exception {
        return TransmissionDomain.getInstance().buildServiceStatusSearchResponse();
    }

    public EventDispatch buildEventDispatchCancellation() throws Exception {
        this.assertCertificate();
        return EventDomain.getInstance().buildEventDispatchCancellation(this.getSigner());
    }

    public EventDispatch buildEventDispatchCCe() throws Exception {
        this.assertCertificate();
        return EventDomain.getInstance().buildEventDispatchCCe(this.getSigner());
    }

    public EventDispatch buildRecipientAwarenessEventDispatch(String cnpj, String accessKey) throws Exception {
        this.assertCertificate();
        return EventDomain.getInstance().buildRecipientAwarenessManifEventDispatch(this.getSigner(), cnpj, accessKey);
    }

    public EventDispatch buildRecipientConfirmationEventDispatch(String cnpj, String accessKey) throws Exception {
        this.assertCertificate();
        return EventDomain.getInstance().buildRecipientConfirmationManifEventDispatch(this.getSigner(), cnpj, accessKey);
    }

    public EventDispatch buildRecipientUnawarenessEventDispatch(String cnpj, String accessKey) throws Exception {
        this.assertCertificate();
        return EventDomain.getInstance().buildRecipientUnawarenessManifEventDispatch(this.getSigner(), cnpj, accessKey);
    }

    public EventDispatch buildRecipientDenialEventDispatch(String cnpj, String accessKey) throws Exception {
        this.assertCertificate();
        return EventDomain.getInstance().buildRecipientDenialManifEventDispatch(this.getSigner(), cnpj, accessKey);
    }

    public NFeNumberDisableDispatch buildNFeNumberDisable() throws Exception {
        this.assertCertificate();
      //@formatter:off
        return new NFeNumberDisableDispatch.Builder()
                .withInfo(new NFeNumberDisableInfo.Builder()
                        .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                        .withUfIbgeCode(UF.PR)
                        .withYear("17")
                        .withCnpj(this.emitterCnpj)
                        .withFiscalDocumentModel(FiscalDocumentModel.NFE)
                        .withFiscalDocumentSeries("333")
                        .withBeginNumber("1")
                        .withEndNumber("1")
                        .withJustification("Teste de inutilização de numeração de NF-e")
                        .build())
                .build(this.getSigner());
      //@formatter:on

    }

    public Logger getLogger() {
        return this.logger;
    }

    public FiscalDocumentValidator getValidator() {
        return this.validator;
    }

    public DefaultSigner getSigner() {
        this.assertCertificate();
        return this.signer;
    }

    public TransmissionChannel getTransmissionChannel() {
        this.assertCertificate();
        return this.transmissionChannel;
    }

    public CSC getEmitterCsc() {
        if (this.emitterCsc == null) {
            throw new IllegalStateException(MessageFormat.format(TestDomain.FIELD_NOT_PRESENT_MESSAGE, "CSC - Emitente"));
        }
        return this.emitterCsc;
    }

    public String getEmitterCnpj() {
        if (StringUtils.isEmpty(this.emitterCnpj)) {
            throw new IllegalStateException(MessageFormat.format(TestDomain.FIELD_NOT_PRESENT_MESSAGE, "CNPJ - Emitente"));
        }
        return this.emitterCnpj;
    }

    public String getEmitterIe() {
        if (StringUtils.isEmpty(this.emitterIe)) {
            throw new IllegalStateException(MessageFormat.format(TestDomain.FIELD_NOT_PRESENT_MESSAGE, "IE - Emitente"));
        }
        return this.emitterIe;
    }

    public String getReceiverLegalEntityCorporateName() {
        if (StringUtils.isEmpty(this.receiverLegalEntityCorporateName)) {
            throw new IllegalStateException(MessageFormat.format(TestDomain.FIELD_NOT_PRESENT_MESSAGE, "Razão Social - Destinatário"));
        }
        return this.receiverLegalEntityCorporateName;
    }

    public String getReceiverLegalEntityCnpj() {
        if (StringUtils.isEmpty(this.receiverLegalEntityCnpj)) {
            throw new IllegalStateException(MessageFormat.format(TestDomain.FIELD_NOT_PRESENT_MESSAGE, "CNPJ - Destinatário"));
        }
        return this.receiverLegalEntityCnpj;
    }

    public String getReceiverLegalEntityIe() {
        if (StringUtils.isEmpty(this.receiverLegalEntityIe)) {
            throw new IllegalStateException(MessageFormat.format(TestDomain.FIELD_NOT_PRESENT_MESSAGE, "IE - Destinatário (PJ)"));
        }
        return this.receiverLegalEntityIe;
    }

    public String getReceiverNaturalPersonCpf() {
        if (StringUtils.isEmpty(this.receiverNaturalPersonCpf)) {
            throw new IllegalStateException(MessageFormat.format(TestDomain.FIELD_NOT_PRESENT_MESSAGE, "CPF - Destinatário"));
        }
        return this.receiverNaturalPersonCpf;
    }

    public String getReceiverNaturalPersonIe() {
        if (StringUtils.isEmpty(this.receiverNaturalPersonIe)) {
            throw new IllegalStateException(MessageFormat.format(TestDomain.FIELD_NOT_PRESENT_MESSAGE, "IE - Destinatário (PF)"));
        }
        return this.receiverNaturalPersonIe;
    }

    public static String randomFixedSizeNumber(int size) {
        return new Random().ints(0, 9).limit(size).mapToObj(String::valueOf).collect(Collectors.joining());
    }
}

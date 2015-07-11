
package eprecise.efiscal4j.nfe.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.nfe.CFOP;
import eprecise.efiscal4j.nfe.CRT;
import eprecise.efiscal4j.nfe.DANFEPrintFormat;
import eprecise.efiscal4j.nfe.DestinationOperationIdentifier;
import eprecise.efiscal4j.nfe.Emitter;
import eprecise.efiscal4j.nfe.FinalCustomerOperation;
import eprecise.efiscal4j.nfe.FiscalDocumentType;
import eprecise.efiscal4j.nfe.ItemValueComprisesTotal;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.NFeDetail;
import eprecise.efiscal4j.nfe.NFeFinality;
import eprecise.efiscal4j.nfe.NFeIdentification;
import eprecise.efiscal4j.nfe.NFeInfo;
import eprecise.efiscal4j.nfe.NFeItem;
import eprecise.efiscal4j.nfe.NFeTransmissionMethod;
import eprecise.efiscal4j.nfe.NFeTransmissionProcess;
import eprecise.efiscal4j.nfe.PaymentMethodIndicator;
import eprecise.efiscal4j.nfe.PurchaserPresenceIndicator;
import eprecise.efiscal4j.nfe.Receiver;
import eprecise.efiscal4j.nfe.StateRegistrationReceiverIndicator;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.additionalinfo.AdditionalInfo;
import eprecise.efiscal4j.nfe.additionalinfo.CustomizedObservation;
import eprecise.efiscal4j.nfe.additionalinfo.ProcessOrigin;
import eprecise.efiscal4j.nfe.additionalinfo.ReferencedProcess;
import eprecise.efiscal4j.nfe.address.Address;
import eprecise.efiscal4j.nfe.address.City;
import eprecise.efiscal4j.nfe.address.Country;
import eprecise.efiscal4j.nfe.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.charging.Duplicate;
import eprecise.efiscal4j.nfe.charging.Invoice;
import eprecise.efiscal4j.nfe.charging.NFeCharging;
import eprecise.efiscal4j.nfe.sharing.BatchReceiptSearch;
import eprecise.efiscal4j.nfe.sharing.BatchReceiptSearchResponse;
import eprecise.efiscal4j.nfe.sharing.CancellationRequestResult;
import eprecise.efiscal4j.nfe.sharing.CancellationRequestResultInfo;
import eprecise.efiscal4j.nfe.sharing.Event;
import eprecise.efiscal4j.nfe.sharing.EventDetail;
import eprecise.efiscal4j.nfe.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.sharing.EventInfo;
import eprecise.efiscal4j.nfe.sharing.EventProtocol;
import eprecise.efiscal4j.nfe.sharing.EventResponse;
import eprecise.efiscal4j.nfe.sharing.EventResponseInfo;
import eprecise.efiscal4j.nfe.sharing.EventType;
import eprecise.efiscal4j.nfe.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.sharing.NFeStatusSearch;
import eprecise.efiscal4j.nfe.sharing.NFeStatusSearchResponse;
import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;
import eprecise.efiscal4j.nfe.sharing.ProcessingStatusProtocol;
import eprecise.efiscal4j.nfe.sharing.ProcessingStatusProtocolInfo;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearch;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearchResponse;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.SynchronousProcessing;
import eprecise.efiscal4j.nfe.tax.Tax;
import eprecise.efiscal4j.nfe.tax.cofins.COFINS;
import eprecise.efiscal4j.nfe.tax.cofins.COFINSST;
import eprecise.efiscal4j.nfe.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.tax.icms.ProductOrigin;
import eprecise.efiscal4j.nfe.tax.ii.II;
import eprecise.efiscal4j.nfe.tax.ipi.IPI;
import eprecise.efiscal4j.nfe.tax.pis.PIS;
import eprecise.efiscal4j.nfe.total.ICMSTotal;
import eprecise.efiscal4j.nfe.total.NFeTotal;
import eprecise.efiscal4j.nfe.transmission.NFeBody;
import eprecise.efiscal4j.nfe.transmission.NFeHeader;
import eprecise.efiscal4j.nfe.transmission.SOAPBody;
import eprecise.efiscal4j.nfe.transmission.SOAPBodyResponse;
import eprecise.efiscal4j.nfe.transmission.SOAPEnvelope;
import eprecise.efiscal4j.nfe.transmission.SOAPEnvelopeResponse;
import eprecise.efiscal4j.nfe.transmission.SOAPHeader;
import eprecise.efiscal4j.nfe.transmission.SOAPHeaderResponse;
import eprecise.efiscal4j.nfe.transport.Conveyor;
import eprecise.efiscal4j.nfe.transport.NFeTransport;
import eprecise.efiscal4j.nfe.transport.ShippingModality;
import eprecise.efiscal4j.nfe.transport.TransportICMSRetention;
import eprecise.efiscal4j.nfe.transport.TransportedVolume;
import eprecise.efiscal4j.nfe.transport.VolumeSeal;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.transmissor.Transmissor;


public class NFeDomain {

    private final Logger logger = LoggerFactory.getLogger(NFeDomain.class);

    private FiscalDocumentValidator validator;

    private Signer signer;

    private Transmissor transmissor;

    public NFeDomain() {
        try {
            final Certificate keyCertificate = new Certificate(() -> new FileInputStream("/home/felipe/Documentos/Desenvolvimento/e-Fiscal4j/Fonebras/FONEBRAS 0989Lu.pfx"), "0989Lu");
            this.signer = new Signer(keyCertificate);
            this.transmissor = new Transmissor(keyCertificate);
        } catch (final Exception ex) {
            this.getLogger().error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public NFeDomain(String xsdPath) {
        this();
        this.setXsdPath(xsdPath);
    }

    public void setXsdPath(String xsdPath) {
        try {
            this.validator = new FiscalDocumentValidator(this.getClass().getResource(xsdPath));
        } catch (final IOException ex) {
            this.getLogger().error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public SOAPEnvelope buildSoapEnvelope(SOAPHeader soapHeader, SOAPBody soapBody) {
        //@formatter:off 
        return new SOAPEnvelope.Builder()
              .withSoapHeader(soapHeader) 
              .withSoapBody(soapBody)
              .build();
        //@formatter:on                      
    }

    public SOAPHeader buildSoapHeader(NFeHeader nFeHeader) {
        //@formatter:off 
        return new SOAPHeader.Builder()
              .withNfeHeader(nFeHeader)
              .build();
        //@formatter:on                      
    }

    public SOAPBody buildSoapBody(NFeBody nFeBody) {
        //@formatter:off 
        return new SOAPBody.Builder()
              .withNfeBody(nFeBody)
              .build();
        //@formatter:on                      
    }

    public NFeHeader buildNFeHeader(String xmlns, UF uf) {
        //@formatter:off 
        return new NFeHeader.Builder()
              .withXmlns(xmlns)
              .withUf(uf)
              .build();
        //@formatter:on 
    }

    public NFeHeader buildNFeHeader(String xmlns, UF uf, FiscalDocumentVersion dataVersion) {
        //@formatter:off 
        return new NFeHeader.Builder()
              .withXmlns(xmlns)
              .withUf(uf)
              .withDataVersion(dataVersion)
              .build();
        //@formatter:on 
    }

    public NFeBody buildNFeBody(String xmlns, TransmissibleBodyImpl transmissible) {
        //@formatter:off 
        return new NFeBody.Builder()
              .withXmlns(xmlns)
              .withTransmissible(transmissible)
              .build();
        //@formatter:on 
    }

    public NFe buildNFe() throws Exception {
        //@formatter:off       
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
                                .withTaxableQuantity("1")
                                .withTaxableUnit("UN")
                                .withTaxableUnitGlobalTradeItemNumber("123456789012")
                                .withTaxationUnitaryValue("10.00")
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
        
//        final List<NFePayment> nFePayments = new ArrayList<>();
//        nFePayments.add(
//                 new NFePayment.Builder()
//                .withPaymentMethod(PaymentMethod.DINHEIRO)
//                .withPaymentValue("10.00")
//                .withCardSet(
//                        new CardSet.Builder()
//                       .withCnpj("01027058000191")
//                       .withCardFlag(CardFlag.VISA)
//                       .withAuthorizationNumber("123445665")
//                       .build())
//                .build());
        
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
                                          .withFiscalDocumentModel(FiscalDocumentModel.NFE)
                                          .withFiscalDocumentNumber("1")
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
                                
                                .withCnpj("01219338000100")                                
                                .withCorporateName("FONEBRAS")
                                .withCrt(CRT.SIMPLES_NACIONAL)
                                .withFancyName("FONEBRAS")                                
                                
//                                .withCnpj("14241297000191")                                
//                                .withCorporateName("E-PRECISE SOLUCOES E CONSULTORIA EM WEB LTDA - ME")
//                                .withCrt(CRT.SIMPLES_NACIONAL)
//                                .withFancyName("E-PRECISE SOLUCOES E CONSULTORIA EM WEB")
                                
                                .withStateRegistration("9010576218")
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
                                .withCnpj("18036571000141")
                                .withCorporateName("Martinkoski")
                                .withStateRegistration("9057109235")
//                                .withMunicipalRegistration("123456789")
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
                                     .withConveyor(
                                              new Conveyor.Builder()
                                             .asNaturalPerson()
                                             .withCpf("67315882537")
                                             .withName("Transportador Teste")
                                             .withStateRegistration("1346304347")
                                             .withFullAddress("Rua 1, Bairro 1")
                                             .withCity(
                                                  new City.Builder()
                                                 .withIbgeCode("4119905")
                                                 .withDescription("Ponta Grossa")
                                                 .withUF(UF.PR)
                                                 .build())
                                             .build())
                                     .withtransportICMSRetention(
                                                            new TransportICMSRetention.Builder()
                                                           .withServiceValue("10.00")
                                                           .withRetentionCalculationBasis("10.00")
                                                           .withRetentionAliquot("10")
                                                           .withRetentionValue("1.00")
                                                           .withCfop(CFOP.CFOP_6931)
                                                           .withGenFactIbgeCode("4119905")
                                                           .build())
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
//                         .withNFePayments(nFePayments)
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

    public NFeStatusSearchResponse buildNFeStatusSearchResponse() throws Exception {
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
                     .withCancellationRequestResult(this.buildCancellationRequestResult())
                     .withEventProtocols(this.buildEventProtocolList())
                     .build();
        //@formatter:on
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

    public CancellationRequestResult buildCancellationRequestResult() throws Exception {
        //@formatter:off       
        return new CancellationRequestResult.Builder()
                     .withCancellationRequestResultInfo(new CancellationRequestResultInfo.Builder()
                                                              .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                              .withApplicationVersion("RS20100311145427")
                                                              .withStatusCode("101")
                                                              .withStatusDescription("Cancelamento homologado com sucesso")
                                                              .withServiceUf(UF.RS)                                                                                                                           
                                                              .build())
                     .build();
        //@formatter:on                
    }

    public ArrayList<EventProtocol> buildEventProtocolList() throws Exception {
        final ArrayList<EventProtocol> eventProtocolList = new ArrayList<>();
        //@formatter:off       
        eventProtocolList.add(new EventProtocol.Builder()
                                    .withEvent(new Event.Builder()
                                                     .withEventInfo(new EventInfo.Builder()
                                                                          .withId("ID4306065910442200570455099000007008000705547000000000")
                                                                          .withIbgeOrgan(IBGEOrgan.AMB_NAC_90)
                                                                          .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                                          .withAuthorCpf("33462170279")
                                                                          .withAcessKey("43060659104422005704550990000070080007055470")
                                                                          .withEventDateTime("2013-02-06T14:51:15-02:00")
                                                                          .withEventType(EventType.OPERACAO_NAO_REALIZADA)
                                                                          .withEventSeqNumber("1")
                                                                          .withEventVersion("1.00")
                                                                          .withEventDetail(new EventDetail.Builder()
                                                                                                 .withEventDescription(EventType.CANC_NFE.getDescription())
                                                                                                 .withProtocolNumber("135120005426259")
                                                                                                 .withJustification("Teste de Cancelamento como Evento")
                                                                                                 .build())
                                                                          .build())
                                                     .build(this.getSigner()))
                                    .withEventResponse(new EventResponse.Builder()
                                                             .witheventResponseInfo(new EventResponseInfo.Builder()
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

    public BatchReceiptSearch buildBatchReceiptSearch() throws Exception {
        //@formatter:off
        return new BatchReceiptSearch.Builder()
                     
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)                           
                     .withReceiptNumber("411110212500268")
//                     .withReceiptNumber("411110212500418")                                          
                     .build();
        //@formatter:on
    }

    public NFeStatusSearch buildNFeStatusSearch() throws Exception {
        //@formatter:off
        return new NFeStatusSearch.Builder()
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                     .withAcessKey("43060659104422005704550990000070080007055470")                                                                                      
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

    public ServiceStatusSearch buildServiceStatusSearch() throws Exception {
        //@formatter:off
        return new ServiceStatusSearch.Builder()
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)                           
                     .withServiceUf(UF.PR)                                                                  
                     .build();
        //@formatter:on
    }

    public EventDispatch buildEventDispatch() throws Exception {
        final ArrayList<Event> eventList = new ArrayList<>();
        //@formatter:off        
        eventList.add(new Event.Builder()
                            .withEventInfo(new EventInfo.Builder()
                                                 .withId("ID1101115213040309084200157555001000028272100028307801")
                                                 .withIbgeOrgan(IBGEOrgan.PR)
                                                 .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                                                 .withAuthorCnpj("03090842001575")
                                                 .withAcessKey("52130403090842001575550010000282721000283079")
                                                 .withEventDateTime("2013-04-03T17:32:54-04:00")
                                                 .withEventType(EventType.CANC_NFE)
                                                 .withEventSeqNumber("1")
                                                 .withEventVersion(FiscalDocumentVersion.VERSION_1_00.getValue())                                                       
                                                 .withEventDetail(new EventDetail.Builder()
                                                                        .withEventDescription(EventType.CANC_NFE.getDescription())
                                                                        .withProtocolNumber("152130333272223")
                                                                        .withJustification("Teste Teste Teste Teste")
                                                                        .build())
                                                 .build())
                            .build(this.signer));
        
        
        return new EventDispatch.Builder()
                     .withBatchId("56")
                     .withEvents(eventList)                                                                 
                     .build();
        //@formatter:on
    }

    public SOAPEnvelopeResponse buildSOAPEnvelopeResponse(SOAPHeaderResponse soapHeaderResponse, SOAPBodyResponse soapBodyResponse) {
        //@formatter:off
        return new SOAPEnvelopeResponse.Builder()
                 .withSoapHeaderResponse(soapHeaderResponse)
                 .withSoapBodyResponse(soapBodyResponse)
                 .build();        
        //@formatter:on                
    }

    public SOAPHeaderResponse buildSoapHeaderResponse(NFeHeader nFeHeader) {
        //@formatter:off
        return new SOAPHeaderResponse.Builder()
                 .withNfeHeader(nFeHeader)
                 .build();
        //@formatter:on
    }

    public SOAPBodyResponse buildSoapBodyResponse(Receivable receivable) {
        //@formatter:off
        return new SOAPBodyResponse.Builder()
                 .withReceivable(receivable)
                 .build();
        //@formatter:on
    }

    public ServiceStatusSearchResponseMethod buildServiceStatusSearchResponseMethod(String xmlns, ServiceStatusSearchResponse serviceStatusSearchResponse) {
        //@formatter:off
        return new ServiceStatusSearchResponseMethod.Builder()
                 .withXmlns(xmlns)
                 .withServiceStatusSearchResponse(serviceStatusSearchResponse)
                 .build();
        //@formatter:on        
    }

    public Logger getLogger() {
        return this.logger;
    }

    public FiscalDocumentValidator getValidator() {
        return this.validator;
    }

    public Signer getSigner() {
        return this.signer;
    }

    public Transmissor getTransmissor() {
        return this.transmissor;
    }

}

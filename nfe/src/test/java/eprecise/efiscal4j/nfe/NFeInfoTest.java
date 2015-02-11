package eprecise.efiscal4j.nfe;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.additionalinfo.AdditionalInfo;
import eprecise.efiscal4j.nfe.additionalinfo.CustomizedObservation;
import eprecise.efiscal4j.nfe.additionalinfo.ProcessOrigin;
import eprecise.efiscal4j.nfe.additionalinfo.ReferencedProcess;
import eprecise.efiscal4j.nfe.address.Address;
import eprecise.efiscal4j.nfe.address.City;
import eprecise.efiscal4j.nfe.address.Country;
import eprecise.efiscal4j.nfe.address.UF;
import eprecise.efiscal4j.nfe.charging.Duplicate;
import eprecise.efiscal4j.nfe.charging.Invoice;
import eprecise.efiscal4j.nfe.charging.NFeCharging;
import eprecise.efiscal4j.nfe.payment.CardFlag;
import eprecise.efiscal4j.nfe.payment.CardSet;
import eprecise.efiscal4j.nfe.payment.NFePayment;
import eprecise.efiscal4j.nfe.payment.PaymentMethod;
import eprecise.efiscal4j.nfe.tax.ReturnedIPI;
import eprecise.efiscal4j.nfe.tax.ReturnedTax;
import eprecise.efiscal4j.nfe.tax.Tax;
import eprecise.efiscal4j.nfe.tax.cofins.COFINS;
import eprecise.efiscal4j.nfe.tax.cofins.COFINSST;
import eprecise.efiscal4j.nfe.tax.icms.BCModality;
import eprecise.efiscal4j.nfe.tax.icms.BCModalityST;
import eprecise.efiscal4j.nfe.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.tax.icms.ProductOrigin;
import eprecise.efiscal4j.nfe.tax.pis.PIS;
import eprecise.efiscal4j.nfe.tax.pis.PISST;
import eprecise.efiscal4j.nfe.total.ICMSTotal;
import eprecise.efiscal4j.nfe.total.NFeTotal;
import eprecise.efiscal4j.nfe.transport.Conveyor;
import eprecise.efiscal4j.nfe.transport.NFeTransport;
import eprecise.efiscal4j.nfe.transport.ShippingModality;
import eprecise.efiscal4j.nfe.transport.TransportICMSRetention;
import eprecise.efiscal4j.nfe.transport.TransportedVolume;
import eprecise.efiscal4j.nfe.transport.VolumeSeal;

public class NFeInfoTest {
    
    @Test
    public void test() {
	try {
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
//								.withIcms(new ICMS.Builder()
//                                            .fromCode(ICMS.CST_00)
//                                            .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                            .withBcValue("10.00")
//                                            .withIcmsAliquot("1.00")
//                                            .withIcmsValue("10.00")
//                                            .build())
								 //ICMS10
//							    .withIcms(new ICMS.Builder()
//                                            .fromCode(ICMS.CST_10)
//                                            .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                            .withBcValue("10.00")
//                                            .withIcmsAliquot("1.00")
//                                            .withIcmsValue("10.00")
//                                            .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                            .withValueMarginAddedStPercent("10.00")
//                                            .withBcReductionStPercent("1.00")
//                                            .withBcValueST("10.00")
//                                            .withIcmsStAliquot("1.00")
//                                            .withIcmsStValue("10.00")
//                                            .build())
								 //ICMS20
//							    .withIcms(new ICMS.Builder()
//                                            .fromCode(ICMS.CST_20)
//                                            .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                            .withBcReductionPercent("5.00")
//                                            .withBcValue("10.00")
//                                            .withIcmsAliquot("1.00")
//                                            .withIcmsValue("10.00")                                            
//                                            .withIcmsDesonerationValue("2")
//                                            .withIcmsDesonerationReason(ICMSDesonerationReason.OUTROS)                                            
//                                            .build())    
								 //ICMS30
//							    .withIcms(new ICMS.Builder()
//                                            .fromCode(ICMS.CST_30)
//                                            .withOrigin(ProductOrigin.NACIONAL)
//                                            .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//                                            .withValueMarginAddedStPercent("10.00")
//                                            .withBcReductionStPercent("1.00")
//                                            .withBcValueST("10.00")
//                                            .withIcmsStAliquot("1.00")
//                                            .withIcmsStValue("10.00")                                            
//                                            .withIcmsDesonerationValue("2")
//                                            .withIcmsDesonerationReason(ICMSDesonerationReason.SUFRAMA)                                            
//                                            .build())			
								 //ICMS40, ICMS41, ICMS50
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.CST_50)
//	                                            .withOrigin(ProductOrigin.NACIONAL)	                                                                                       
//	                                            .withIcmsDesonerationValue("2")
//	                                            .withIcmsDesonerationReason(ICMSDesonerationReason.OUTROS)                                            
//	                                            .build())
								 //ICMS51
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.CST_51)
//	                                            .withOrigin(ProductOrigin.NACIONAL)	                                                                                       
//                                                .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//                                                .withBcReductionPercent("5.00")
//	                                            .withBcValue("10.00")
//	                                            .withIcmsAliquot("1.00")
//	                                            .withIcmsOperationValue("10")
//	                                            .withDeferralPercent("5")
//	                                            .withIcmsDeferralValue("0.50")
//	                                            .withIcmsValue("10.00")	                                                                                      
//	                                            .build())
								 //ICMS60
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.CST_60)
//	                                            .withOrigin(ProductOrigin.NACIONAL)	                                                                                       
//                                                .withBcRetainedValueST("2.00")
//                                                .withIcmsRetainedValueST("1")
//	                                            .build())
								 //ICMS70
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.CST_70)
//	                                            .withOrigin(ProductOrigin.NACIONAL)
//	                                            .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//	                                            .withBcReductionPercent("2")
//	                                            .withBcValue("10.00")
//	                                            .withIcmsAliquot("1.00")
//	                                            .withIcmsValue("10.00")
//	                                            .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//	                                            .withValueMarginAddedStPercent("10.00")
//	                                            .withBcReductionStPercent("1.00")
//	                                            .withBcValueST("10.00")
//	                                            .withIcmsStAliquot("1.00")
//	                                            .withIcmsStValue("10.00")
//	                                            .withIcmsDesonerationValue("2")
//	                                            .withIcmsDesonerationReason(ICMSDesonerationReason.FOMENTO_AGROPECUARIO)	                                            
//	                                            .build())
								 //ICMS90
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.CST_90)
//	                                            .withOrigin(ProductOrigin.NACIONAL)
//	                                            .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//	                                            .withBcReductionPercent("2")
//	                                            .withBcValue("10.00")
//	                                            .withIcmsAliquot("1.00")
//	                                            .withIcmsValue("10.00")
//	                                            .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//	                                            .withValueMarginAddedStPercent("10.00")
//	                                            .withBcReductionStPercent("1.00")
//	                                            .withBcValueST("10.00")
//	                                            .withIcmsStAliquot("1.00")
//	                                            .withIcmsStValue("10.00")
//	                                            .withIcmsDesonerationValue("2")
//	                                            .withIcmsDesonerationReason(ICMSDesonerationReason.FOMENTO_AGROPECUARIO)	                                            
//	                                            .build())
								 //ICMSPart10 e ICMSPart90 
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.PART_CST_90)
//	                                            .withOrigin(ProductOrigin.NACIONAL)
//	                                            .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
//	                                            .withBcReductionPercent("2")
//	                                            .withBcValue("10.00")
//	                                            .withIcmsAliquot("1.00")
//	                                            .withIcmsValue("10.00")
//	                                            .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//	                                            .withValueMarginAddedStPercent("10.00")
//	                                            .withBcReductionStPercent("1.00")
//	                                            .withBcValueST("10.00")
//	                                            .withIcmsStAliquot("1.00")
//	                                            .withIcmsStValue("10.00")
//	                                            .withSelfOperationBCPerc("3")
//	                                            .withUfST(UF.PR)	                                            
//	                                            .build())
								 //ICMSST
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.ST_CST_41)
//	                                            .withOrigin(ProductOrigin.NACIONAL)	                                                                                       
//                                                .withBcRetainedValueST("2.00")
//                                                .withIcmsRetainedValueST("1")
//                                                .withBcIcmsStDestination("5.00")
//                                                .withIcmsStDestination("3.00")                                                
//	                                            .build())
								 //ICMSSN101
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.CSOSN_101)
//	                                            .withOrigin(ProductOrigin.NACIONAL)	                                                                                       
//                                                .withCreditSnAliquot("10.00")
//                                                .withCreditSnIcmsValue("100.00")
//	                                            .build())
								 //ICMSSN102, ICMSSN103, ICMSSN300 e ICMSSN400
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.CSOSN_400)
//	                                            .withOrigin(ProductOrigin.ESTRANGEIRA_IMPORTADA)	                                                                                       
//	                                            .build())
								 //ICMSSN201 
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.CSOSN_201)
//	                                            .withOrigin(ProductOrigin.NACIONAL)
//	                                            .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//	                                            .withValueMarginAddedStPercent("10.00")
//	                                            .withBcReductionStPercent("1.00")
//	                                            .withBcValueST("10.00")
//	                                            .withIcmsStAliquot("1.00")
//	                                            .withIcmsStValue("10.00")
//                                              .withCreditSnAliquot("10.00")
//                                              .withCreditSnIcmsValue("100.00")                                            
//	                                            .build())
								 //ICMSSN202, ICMSSN203 
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.CSOSN_203)
//	                                            .withOrigin(ProductOrigin.NACIONAL)
//	                                            .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
//	                                            .withValueMarginAddedStPercent("10.00")
//	                                            .withBcReductionStPercent("1.00")
//	                                            .withBcValueST("10.00")
//	                                            .withIcmsStAliquot("1.00")
//	                                            .withIcmsStValue("10.00")                                            
//	                                            .build())
								 //ICMSSN500
//								    .withIcms(new ICMS.Builder()
//	                                            .fromCode(ICMS.CSOSN_500)
//	                                            .withOrigin(ProductOrigin.NACIONAL)	                                                                                       
//                                                .withBcRetainedValueST("2.00")
//                                                .withIcmsRetainedValueST("1")
//	                                            .build())
								 //ICMSSN900
								    .withIcms(new ICMS.Builder()
                                            .fromCode(ICMS.CSOSN_900)
                                            .withOrigin(ProductOrigin.NACIONAL)
                                            .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
                                            .withBcReductionPercent("2")
                                            .withBcValue("10.00")
                                            .withIcmsAliquot("1.00")
                                            .withIcmsValue("10.00")
                                            .withBcModalityST(BCModalityST.PRECO_TABELADO_OU_MAX_SUGERIDO)
                                            .withValueMarginAddedStPercent("10.00")
                                            .withBcReductionStPercent("1.00")
                                            .withBcValueST("10.00")
                                            .withIcmsStAliquot("1.00")
                                            .withIcmsStValue("10.00")
                                            .withCreditSnAliquot("10.00")
                                            .withCreditSnIcmsValue("100.00")                                                
                                            .build())
								    
                                 //PIS01, PIS02      
//                                    .withPis(new PIS.Builder()
//                                                .fromCode(PIS.CST_01)
//                                                .withBcValue("10.00")
//                                                .withPisAliquot("10")
//                                                .withPisValue("1")                                                
//                                                .build())
                                 //PIS03      
                                    .withPis(new PIS.Builder()
                                                .fromCode(PIS.CST_03)
                                                .withProductQuantity("3.00")
                                                .withProductAliquot("10")
                                                .withPisValue("1")                                                
                                                .build())
                                 //PIS04, PIS05, PIS06, PIS07, PIS08, PIS09
//                                    .withPis(new PIS.Builder()
//                                                .fromCode(PIS.CST_09)                                                
//                                                .build())
                                 //PIS49, PIS50, PIS51, PIS52, PIS53, PIS54, PIS55, PIS56, PIS60, PIS61, PIS62, PIS63, PIS64, PIS65, PIS66, PIS67, PIS70, PIS71, PIS72, PIS73, PIS74, PIS75, PIS98, PIS99
//                                    .withPis(new PIS.Builder()
//                                                .fromCode(PIS.CST_74)
////                                                .withProductQuantity("3.00")
////                                                .withProductAliquot("5.00")
//                                                .withBcValue("4")
//                                                .withPisAliquot("5")
//                                                .withPisValue("3")
//                                                .build())
                                 //PISST
                                    .withPisSt(new PISST.Builder()                                                
//                                                .withProductQuantity("3")
//                                                .withProductAliquot("5")
                                                .withBcValue("4")
                                                .withPisAliquot("5")
                                                .withPisValue("3")
                                                .build())
                                               
                                 //COFINS01, COFINS02      
                                    .withCofins(new COFINS.Builder()
                                                .fromCode(COFINS.CST_02)
                                                .withBcValue("10.00")
                                                .withCofinsAliquot("10")
                                                .withCofinsValue("1")                                                
                                                .build())
                                 //COFINS03      
//                                    .withCofins(new COFINS.Builder()
//                                                .fromCode(COFINS.CST_03)
//                                                .withProductQuantity("3.00")
//                                                .withProductAliquot("10")
//                                                .withCofinsValue("1")                                                
//                                                .build())
                                 //COFINS04, COFINS05, COFINS06, COFINS07, COFINS08, COFINS09
//                                    .withCofins(new COFINS.Builder()
//                                                .fromCode(COFINS.CST_08)                                                
//                                                .build())
                                 //COFINS49, COFINS50, COFINS51, COFINS52, COFINS53, COFINS54, COFINS55, COFINS56, COFINS60, COFINS61, COFINS62, COFINS63, COFINS64, COFINS65, COFINS66, COFINS67, COFINS70, COFINS71, COFINS72, COFINS73, COFINS74, COFINS75, COFINS98, COFINS99
//                                    .withCofins(new PIS.Builder()
//                                                .fromCode(PIS.CST_74)
//                                                .withProductQuantity("3.00")
//                                                .withProductAliquot("5.00")
//                                                .withBcValue("4")
//                                                .withPisAliquot("5")
//                                                .withPisValue("3")
//                                                .build())
                                 //COFINSST
                                    .withCofinsSt(new COFINSST.Builder()                                                
//                                                .withProductQuantity("3")
//                                                .withProductAliquot("5")
                                                .withBcValue("4")
                                                .withCofinsAliquot("5")
                                                .withCofinsValue("3")
                                                .build())                                                
								.build())
							 .withReturnedTax(new ReturnedTax.Builder()
							                 .withReturnedProductPerc("70")
							                 .withReturnedIPI(new ReturnedIPI.Builder()
							                                 .withReturnedIPIValue("7.00")
							                                 .build())
							                 .build())
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
			
			final List<NFePayment> nFePayments = new ArrayList<>();
			nFePayments.add(
					 new NFePayment.Builder()
					.withPaymentMethod(PaymentMethod.DINHEIRO)
					.withPaymentValue("10.00")
					.withCardSet(
							new CardSet.Builder()
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
					          .build()
					);
						
            final NFeInfo nFeInfo = new NFeInfo.Builder()
				             .withNFeIdentification(
							     		       new NFeIdentification.Builder()
							     		      .withApplicationVersion("1.00")
							     		      .withChecksum("7")
							     		      .withDanfePrintFormat(DANFEPrintFormat.DANFE_NFCE)
							     		      .withDestinationOperationIdentifier(DestinationOperationIdentifier.INTERNA)
							     		      .withEmissionDateTime("2014-03-15T09:20:03-03:00")
							     		      .withFinalCustomerOperation(FinalCustomerOperation.CONSUMIDOR_FINAL)
							     		      .withFiscalDocumentModel(FiscalDocumentModel.NFCE)
							     		      .withFiscalDocumentNumber("1")
							     		      .withFiscalDocumentSeries("1")
							     		      .withFiscalDocumentType(FiscalDocumentType.SAIDA)
							     		      .withNFeCode("12345678")
							     		      .withNFeFinality(NFeFinality.NORMAL)
							     		      .withNFeTransmissionMethod(NFeTransmissionMethod.NORMAL)
							     		      .withNFeTransmissionProcess(NFeTransmissionProcess.APLICATIVO_CONTRIBUINTE)
							     		      .withOperationType("1")
							     		      .withPaymentMethod(PaymentMethodIndicator.PAGAMENTO_A_VISTA)
							     		      .withPurchaserPresenceIndicator(PurchaserPresenceIndicator.OPERACAO_PRESENCIAL)
							     		      .withTaxableEventCityIbgeCode("1234567")
							     		      .withTransmissionEnvironment(TransmissionEnvironmnent.HOMOLOGACAO)
							     		      .withUFIbgeCode(UF.SP)
							     		      .build())            
                             .withEmitter(
                                     new Emitter.Builder()
                                    .asLegalEntity()
                                    .withCnpj("44284107000184")
                                    .withCorporateName("e-precise")
                                    .withFancyName("e-Precise Systems")
                                    .withStateRegistration("123456789")
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
                                                     .withIbgeCode("0")
                                                     .withDescription("Brasil").build())
                                              .withIbgeCode("1234567")
                                              .withDescription("Recife")
                                              .withUF(UF.PR)
                                              .build())
                                          .build())
                                    .withCrt(CRT.REGIME_NORMAL)
                                    .build())
                             .withReceiver(
                                      new Receiver.Builder()
                                     .asNaturalPerson()
                                     .withCpf("14712931060")
                                     .withName("Joao")
                                     .withStateRegistration("123456789")
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
                                                      .withIbgeCode("0")
                                                      .withDescription("Brasil")
                                                      .build())
                                               .withIbgeCode("1234567")
                                               .withDescription("Recife")
                                               .withUF(UF.SP)
                                               .build())
                                           .build())
                                     .withStateRegistrationReceiverIndicator(StateRegistrationReceiverIndicator.NAO_CONTRIBUINTE)        
                                     .withEmail("teste")
                                     .build())                                    
                             .withNFeDetail(nFeDetailList) 
                             .withNFeTotal(
                        		      new NFeTotal.Builder()
                        		     .withFederalTaxRetention(
			                        		    		 new FederalTaxRetention.Builder()
			                        		    	    .withCOFINSRetainedValue("0.10")
			                        		            .withCSLLRetainedValue("0.10")
			                        		            .withIRRFCalculationBasis("0.10")
			                        		            .withIRRFRetainedValue("0.10")
			                        		            .withPISRetainedValue("0.10")
			                        		            .withSocialSecurityRetentionCalculationBasis("0.10")
			                        		            .withSocialSecurityRetentionValue("0.10")
			                        		            .build())
                        		     .withICMSTotal(
                        		    		   new ICMSTotal.Builder()
                        		    		  .withCOFINSTotalValue("0")
                        		    		  .withDiscountTotalValue("0")
                        		    		  .withICMSCalculationBasis("10.00")
                        		    		  .withICMSSTCalculationBasis("0")
                        		    		  .withICMSSTTotalValue("0")
                        		    		  .withICMSTotalDesoneration("0")
                        		    		  .withICMSTotalValue("10.00")
                        		    		  .withIITotalValue("0")
                        		    		  .withInsuranceTotalValue("0")
                        		    		  .withIPITotalValue("0")
                        		    		  .withItemsTotalValue("10.00")
                        		    		  .withNFeTotalValue("10.00")
                        		    		  .withOtherIncidentalCostsTotalValue("0")
                        		    		  .withPISTotalValue("0")
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
                                                 .withStateRegistration("123456")
                                                 .withFullAddress("Rua 1, Bairro 1")
                                                 .withCity(
                                                	  new City.Builder()
                                                     .withIbgeCode("1234567")
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
                                        		               .withGenFactIbgeCode("1234567")
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
                             .build();
            //@formatter:on
	    final String xml = new FiscalDocumentSerializer<>(nFeInfo).considering(NFeInfo.class, LegalEntityDocuments.class, NaturalPersonDocuments.class)
								      .serialize();
	    System.out.println(xml);
	} catch (final ConstraintViolationException e) {
	    final StringBuilder message = new StringBuilder("Erro de validação:");
	    for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
		message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
	    }
	    Assert.assertTrue(message.toString(), false);
	} catch (final Exception e) {
	    e.printStackTrace();
	    Assert.assertTrue(e.getMessage(), false);
	}
    }
}

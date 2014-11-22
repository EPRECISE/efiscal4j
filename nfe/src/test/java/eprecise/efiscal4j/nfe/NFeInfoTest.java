
package eprecise.efiscal4j.nfe;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.nfe.address.Address;
import eprecise.efiscal4j.nfe.address.City;
import eprecise.efiscal4j.nfe.address.Country;
import eprecise.efiscal4j.nfe.address.UF;
import eprecise.efiscal4j.nfe.tax.Tax;
import eprecise.efiscal4j.nfe.tax.icms.BCModality;
import eprecise.efiscal4j.nfe.tax.icms.ICMS;
import eprecise.efiscal4j.nfe.tax.icms.ProductOrigin;
import eprecise.efiscal4j.nfe.total.ICMSTotal;
import eprecise.efiscal4j.nfe.total.NFeTotal;
import eprecise.efiscal4j.nfe.transport.Conveyor;
import eprecise.efiscal4j.nfe.transport.NFeTransport;
import eprecise.efiscal4j.nfe.transport.ShippingModality;
import eprecise.efiscal4j.nfe.transport.TransportedVolume;
import eprecise.efiscal4j.nfe.transport.VolumeSeal;


public class NFeInfoTest {

	@Test
	public void test() {
		try {
			//@formatter:off       
			List<NFeDetail> nFeDetailList = new ArrayList<>();
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
							    .withMainTax(new ICMS.Builder()
							                 .fromCode(ICMS.CST_00)
							                 .withOrigin(ProductOrigin.NACIONAL)
							                 .withBcModality(BCModality.MARGEM_VALOR_AGREGADO)
							                 .withBcValue("10.00")
							                 .withIcmsAliquot("1.00")
							                 .withIcmsValue("10.00")
							                 .build())
								.build())
							 .build());
						
			List<VolumeSeal> seals = new ArrayList<>();
			seals.add(new VolumeSeal.Builder()
				     .withSealNumber("Número do Lacre 33")
				     .build());
			
			List<TransportedVolume> transportedVolumes = new ArrayList<>();
			transportedVolumes.add(new TransportedVolume.Builder()
					              .withVolumeQuantity("3")
					              .withVolumeSpecies("Espécie teste")
					              .withVolumeTrademark("Marca Teste")
					              .withVolumeNumbering("numeração teste 01")
					              .withNetWeight("55.555")
					              .withGrossWeight("60.000")
					              .withSeals(seals)
					              .build());					
						
            NFeInfo nFeInfo = new NFeInfo.Builder()
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
                             .withNFeDetail(nFeDetailList) 
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
		                        		      .withPaymentMethod(PaymentMethod.PAGAMENTO_A_VISTA)
		                        		      .withPurchaserPresenceIndicator(PurchaserPresenceIndicator.OPERACAO_PRESENCIAL)
		                        		      .withTaxableEventCityIbgeCode("1234567")
		                        		      .withTransmissionEnvironment(TransmissionEnvironmnent.HOMOLOGACAO)
		                        		      .withUFIbgeCode(UF.SP)
		                        		      .build())
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
                                        .withConveyor(new Conveyor.Builder()
                                                     .asNaturalPerson()
                                                     .withCpf("67315882537")
                                                     .withName("Transportador Teste")
                                                     .withStateRegistration("123456")
                                                     .withFullAddress("Rua 1, Bairro 1")
                                                     .withCity(new City.Builder()
                                                              .withIbgeCode("1234567")
                                                              .withDescription("Ponta Grossa")
                                                              .withUF(UF.PR)
                                                              .build())
                                                     .build())
                                        .withTransportedVolume(transportedVolumes)
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
                           .build();
            
            //@formatter:on
            JAXBContext jaxbContext = JAXBContext.newInstance(NFeInfo.class, LegalEntityDocuments.class, NaturalPersonDocuments.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(nFeInfo, System.out);

            Assert.assertTrue(true);
        } catch (ConstraintViolationException e) {
            for (ConstraintViolation<?> v : e.getConstraintViolations()) {
                System.err.println(v.getLeafBean().toString() + " " + v.getPropertyPath() + " " + v.getMessage());
            }
            Assert.assertTrue(false);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}

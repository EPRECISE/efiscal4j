
package eprecise.efiscal4j.nfe;

import java.util.ArrayList;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Assert;
import org.junit.Test;


public class NFeInfoTest {

    @Test
    public void test() {
        try {
            //@formatter:off       
			ArrayList<NFeDetail> nFeDetailList = new ArrayList<NFeDetail>();
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
									 .withMainTax(new MainTax())
									 .build())
							 .build()							 											
					         );
			
			
			
            NFeInfo nFeInfo = new NFeInfo.Builder().withEmitter(
                                                             new Emitter.Builder()
                                                            .asLegalEntity()
                                                            .withCnpj("44284107000184")
                                                            .withCorporateName("e-precise")
                                                            .withFancyName("e-Precise Systems")
                                                            .withStateRegistration("123456789")
                                                            .withAdress(
                                                                    new Adress.Builder()
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
                                                		   .withConveyor(new Conveyor.Builder()
                                                		   		   .build())
                                                		   .withShippingModality(ShippingModality.SEM_FRETE)
                                                		   .build())
                                                   .withReceiver(
                                                             new Receiver.Builder()
                                                            .asNaturalPerson()
                                                            .withCpf("06356272996")
                                                            .withName("Joao")
//                                                            .withStateRegistration("123456789")
//                                                            .withMunicipalRegistration("123456789")
                                                            .withAdress(
                                                                    new Adress.Builder()
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

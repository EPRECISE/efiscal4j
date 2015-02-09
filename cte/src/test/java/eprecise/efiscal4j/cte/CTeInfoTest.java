package eprecise.efiscal4j.cte;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.cte.address.Address;
import eprecise.efiscal4j.cte.address.UF;
import eprecise.efiscal4j.cte.payment.PaymentMethod;
import eprecise.efiscal4j.cte.person.Receiver;
import eprecise.efiscal4j.cte.person.Sender;
import eprecise.efiscal4j.cte.person.Shipper;
import eprecise.efiscal4j.cte.serviceTaker.ServiceTaker;

public class CTeInfoTest {
    @Test
    public void test() {
	try {
	    //@formatter:off
	    final CTeInfo cTeInfo = new CTeInfo.Builder()
	    			.withIdentification(new Identification.Builder()
	    					.withUF(UF.PR)	
	    					.withCTeCode("10101010")
	    					.withCFOP("5949")	    					
	    					.withOperationNature("10")
	    					.withPaymentMethod(PaymentMethod.PAGO)
	    					.withSerie("1")
	    					.withNumberCte("10030")
	    					.withDateAndTimeOfEmission("2015-02-06T10:10:10")
	    					.withPrintFormatDACTE(PrintFormatDACTE.PAISAGEM)
	    					.withCTeEmissionForm(CTeEmissionForm.NORMAL)
	    					.withCheckDigit("8")	    					
	    					.withTypeEnvironment(TypeEnvironment.PAISAGEM)
	    					.withCTeType(CTeType.NORMAL)
	    					.withIdentifierEmission(IdentifierEmission.APLICATIVO_CONTRIBUINTE)
	    					.withEmissionProcessVersion("1")
	    					.withAccessKey("42131084684182000157550010000000020108042108")
	    					.withCityCode("4119905")
	    					.withCityName("Ponta Grossa")
	    					.withUfSend(UF.PR)
	    					.withModal(Modal.RODOVIARIO)
	    					.withTypeService(TypeService.NORMAL)
	    					.withCodeCityBeginInstallment("4119905")
	    					.withNameCityBeginInstallment("Ponta Grossa")  
	    					.withUfBeginInstallment(UF.PR)
	    					.withNameCityEndInstallment("Ponta Grossa")
	    					.withUfEndInstallment(UF.PR)
	    					.withIndicatorWithdrawal(IndicatorWithdrawal.SIM)
	    					.withDetailsRemoved("Foi retirado") 
	    						.withServiceTaker(new ServiceTaker.Builder()
	    									.fromType(ServiceTaker.OTHERS)
	    									.withCNPJ("44.319.641/0001-89")
	    									.withCPF("08775530929")
	    									.withIE("ISENTO")
	    									.withCorporateName("Pedro Silva")
	    									.withFantasyName("Jose MM LTDA")	    									
	    									.withEmail("teste@cte.com.br")
	    										.withAddressData(new Address.Builder()
	    											.withStreet("RUA")
	    											.withNumber("320")
	    											.withComplement("CASA")
	    											.withDistrict("oficinas")
	    											.withCityCode("4119905")
	    											.withCityName("Ponta Grossa")
	    											.withZipCode("84035560")
	    											.withAcronymUf(UF.PR)
	    											.withCountryCode("1058")
	    											.withCountryName("BRASIL")
	    										.build())
	    									.withFone("4232295373")	    									
	    									.build())
	    					.withContingency("2015-02-06T10:10:10")
	    					.withJustificationContingency("Nao foi porque não justifiquei")  
	    					.build())
	    				.withEmitter(new Emitter.Builder()
	    					.withCNPJ("69.305.329/0001-67")
	    					.withIE("ISENTO")
	    					.withName("Emitente 1 LTDA")	    					
	    					.withFantasyName("Emitente 1")	    					
	    					.withAddress(new Address.Builder()
	    						.withStreet("RUA")
							.withNumber("320")
							.withComplement("CASA")
							.withDistrict("oficinas")
							.withCityCode("4119905")
							.withCityName("Ponta Grossa")
							.withZipCode("84035560")
							.withAcronymUf(UF.PR)
							.withCountryCode("1058")
							.withCountryName("BRASIL")
	    						.build()
	    					).build()
	    				).withSender(new Sender.Builder()
	    					.asLegalEntity()
	    						.withCNPJ("69305329000167")
	    						.withIE("ISENTO")
	    						.ok()
	    					.withName("teste validacao")
	    					.withFone("4299561913")
	    					.withEmail("gomesflu@hotmail.com")
	    					.withFantasyName("Nome De teste")
	    						.withLocationCollection(new Location.Builder()
	    						.withCNPJ("69.305.329/0001-67")
		    					.withCPF("08775530929")
		    					.withCorporateName("TESTE")
		    					.withStreet("RUA JOSE")
		    					.withNumber("322")
		    					.withComplement("casa")
		    					.withDistrict("Oficinas")
		    					.withCityCode("4119905")
		    					.withCityName("Ponta Grossa")
		    					.withUF(UF.PR)	    						
	    						.build()
	    					).build()
	    				).withShipper(new Shipper.Builder()
	    					.asNaturalPerson()
	    						.withCPF("08775530929")
	    						.ok()
	    					.withName("teste validacao")
	    					.withFone("4299561913")
	    					.withEmail("gomesflu@hotmail.com")
	    						.withAddress(new Address.Builder()
	    						.withStreet("RUA")
	    						.withNumber("320")
	    						.withComplement("CASA")
	    						.withDistrict("oficinas")
	    						.withCityCode("4119905")
	    						.withCityName("Ponta Grossa")
	    						.withZipCode("84035560")
	    						.withAcronymUf(UF.PR)
	    						.withCountryCode("1058")
	    						.withCountryName("BRASIL")
	    						.build()
	    					).build()
	    				).withReceiver(new Receiver.Builder()
	    					.asNaturalPerson()
	    						.withCPF("08775530929")
	    						.ok()
	    					.withName("teste validacao")
	    					.withFone("4299561913")
	    					.withEmail("gomesflu@hotmail.com")
	    					.withAddress(new Address.Builder()
	    						.withStreet("RUA")
	    						.withNumber("320")
	    						.withComplement("CASA")
	    						.withDistrict("oficinas")
	    						.withCityCode("4119905")
	    						.withCityName("Ponta Grossa")
	    						.withZipCode("84035560")
	    						.withAcronymUf(UF.PR)
	    						.withCountryCode("1058")
	    						.withCountryName("BRASIL")
	    						.build()
	    					).build()
	    				).withValuesServiceDelivery(new ValuesServiceDelivery.Builder()
	    					.withDelivertService("13")
	    					.withValueReceivable("13")
	    					.withInstallmentValueComponent(new InstallmentValueComponent.Builder()
	    							.withName("Teste")
	    							.withValue("10")
	    							.builder())
	    					.build()
	    				).build();	    						
	    				
	    //@formatter:oon
	    final JAXBContext jaxbContext = JAXBContext.newInstance(CTeInfo.class);
	    final Marshaller marshaller = jaxbContext.createMarshaller();
	    marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
	    marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    marshaller.marshal(cTeInfo, System.out);
	    
	    Assert.assertTrue(true);
	} catch (final ConstraintViolationException e) {
	    for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
		System.err.println(v.getLeafBean().toString() + " " + v.getPropertyPath() + " " + v.getMessage());
	    }
	    Assert.assertTrue(false);
	} catch (final Exception e) {
	    e.printStackTrace();
	    Assert.assertTrue(false);
	}
    }
}

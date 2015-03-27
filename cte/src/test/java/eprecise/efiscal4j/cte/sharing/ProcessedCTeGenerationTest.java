package eprecise.efiscal4j.cte.sharing;

import java.io.IOException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator.ValidationResult;
import eprecise.efiscal4j.cte.CTe;
import eprecise.efiscal4j.cte.CTeEmissionForm;
import eprecise.efiscal4j.cte.CTeInfo;
import eprecise.efiscal4j.cte.CTeType;
import eprecise.efiscal4j.cte.Emitter;
import eprecise.efiscal4j.cte.Identification;
import eprecise.efiscal4j.cte.IdentifierEmission;
import eprecise.efiscal4j.cte.IndicatorWithdrawal;
import eprecise.efiscal4j.cte.InstallmentValueComponent;
import eprecise.efiscal4j.cte.Location;
import eprecise.efiscal4j.cte.Modal;
import eprecise.efiscal4j.cte.PrintFormatDACTE;
import eprecise.efiscal4j.cte.TypeEnvironment;
import eprecise.efiscal4j.cte.TypeService;
import eprecise.efiscal4j.cte.ValuesServiceDelivery;
import eprecise.efiscal4j.cte.address.AddressEmitter;
import eprecise.efiscal4j.cte.address.AddressGeneral;
import eprecise.efiscal4j.cte.address.UF;
import eprecise.efiscal4j.cte.payment.PaymentMethod;
import eprecise.efiscal4j.cte.person.Addressee;
import eprecise.efiscal4j.cte.person.Receiver;
import eprecise.efiscal4j.cte.person.Sender;
import eprecise.efiscal4j.cte.person.Shipper;
import eprecise.efiscal4j.cte.serviceTaker.ServiceTaker;
import eprecise.efiscal4j.cte.sharing.ProcessedCTe;
import eprecise.efiscal4j.cte.sharing.StatusProtocolInfo;
import eprecise.efiscal4j.cte.sharing.StatusProtocol;

public class ProcessedCTeGenerationTest {
    
    @Test
    public void validateByBeanValidation() {
	final ProcessedCTe processedCTe = this.buildProcessedCTe();
	try {
	    ValidationBuilder.from(processedCTe).validate().throwIfViolate();
	} catch (final ConstraintViolationException e) {
	    final StringBuilder message = new StringBuilder("Erro de validação:");
	    for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
		message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
	    }
	    Assert.assertTrue(message.toString(), false);
	}
    }
    
    @Test
    public void validateByXSD() throws JAXBException, SAXException, IOException {
	final FiscalDocumentValidator validator = new FiscalDocumentValidator(this.getClass().getResource("/eprecise/efiscal4j/cte/procCTe_v2.00.xsd"));
	final ProcessedCTe cTeProc = this.buildProcessedCTe();
	final String serialize = new FiscalDocumentSerializer<>(cTeProc).serialize();
	System.out.println(serialize);
	final ValidationResult validate = validator.validate(serialize);
	Assert.assertTrue(validate.getError(), validate.isValid());
    }
    
    private ProcessedCTe buildProcessedCTe() {
	//@formatter:off
	return new ProcessedCTe.Builder()
		.withCTe(new CTe.Builder()
			.withInfo(new CTeInfo.Builder()
				.withID("CTe42131084684182000157550010000000020108042107")
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
				    .withTypeEnvironment(TypeEnvironment.PRODUCTION)
				    .withCTeType(CTeType.NORMAL)
				    .withIdentifierEmission(IdentifierEmission.APLICATIVO_CONTRIBUINTE)
				    .withEmissionProcessVersion("1")
				    .withRefAccessKey("42131084684182000157550010000000020108042108")
				    .withCityCode("4119905")
				    .withCityName("Ponta Grossa")
				    .withUfSend(UF.PR)
				    .withModal(Modal.RODOVIARIO)
				    .withTypeService(TypeService.NORMAL)
				    .withUfBeginInstallment(UF.PR)
				    .withCodeCityBeginInstallment("4119905")
				    .withNameCityBeginInstallment("Ponta Grossa")
				    .withUfEndInstallment(UF.PR)
				    .withCodeCityEndInstallment("4119905")
				    .withNameCityEndInstallment("Ponta Grossa")
				    .withIndicatorWithdrawal(IndicatorWithdrawal.SIM)
				    .withDetailsRemoved("Foi retirado")
				    .withServiceTaker(
					    new ServiceTaker.Builder().fromType(ServiceTaker.OTHERS)
					    			      .asNaturalPerson()				    			     
								      .withCPF("08775530929")								      
								      .withCorporateName("Pedro Silva")
								      .withFantasyName("Jose MM LTDA")
								      .withFone("4232295373")								      
								      .withAddressData(
									      new AddressGeneral.Builder().withStreet("RUA")
												   .withNumber("320")
												   .withComplement("CASA")
												   .withDistrict("oficinas")
												   .withCityCode("4119905")
												   .withCityName("Ponta Grossa")
												   .withZipCode("84035560")
												   .withUF(UF.PR)
												   .withCountryCode("1058")
												   .withCountryName("BRASIL")
												   .build())
								      .withEmail("teste@cte.com.br")
								      .build())
				    .withContingency("2015-02-06T10:10:10")
				    .withJustificationContingency("Nao foi porque não justifiquei")
				    .build())
				.withEmitter(new Emitter.Builder()
					.withCNPJ("69305329000167")
					.withIE("10101010101010")
        				.withName("Emitente 1 LTDA")
        				.withFantasyName("Emitente 1")
        				.withAddress(new AddressEmitter.Builder()
        						.withStreet("RUA")
        						.withNumber("320")
        					       	.withComplement("CASA")
        					       	.withDistrict("oficinas")
        					       	.withCityCode("4119905")
        					       	.withCityName("Ponta Grossa")
        					       	.withZipCode("84035560")
        					       	.withUF(UF.PR)
        					       	.withFone("4299561913")
        					       	.build())
        				.build())
        			.withSender(new Sender.Builder()
        				.asNaturalPerson()
        					.withCPF("08775530929")
        					.withIE("101010101010")
        					.ok()
        				.withName("teste validacao")
        				.withFantasyName("Nome De teste")         				     				
        				.withFone("4299561913")
        					.withAddress(new AddressGeneral.Builder()
        						.withStreet("RUA")
        						.withNumber("320")
        						.withComplement("CASA")
        						.withDistrict("oficinas")
        						.withCityCode("4119905")
        						.withCityName("Ponta Grossa")
        						.withZipCode("84035560")
        						.withUF(UF.PR)
        						.withCountryCode("1058")
        						.withCountryName("BRASIL")
        						.build())
        				.withEmail("JOSE@hotmail.com")	
        				.withLocationCollection(new Location.Builder()
        							.asLegalEntity()
        							.withCNPJ("69305329000167")
        							.withName("TESTE")
        							.withStreet("RUA JOSE")
        							.withNumber("322")
        							.withComplement("casa")
        							.withDistrict("Oficinas")
        							.withCityCode("4119905")
        							.withCityName("Ponta Grossa")
        							.withUF(UF.PR)
        							.build())
        				.build())
				.withShipper(new Shipper.Builder()
					.asLegalEntity()
						.withCNPJ("69305329000167")
						.withIE("10101010101010")
						.ok()
					.withName("teste validacao")
					.withFone("4299561913")					
					.withAddress(new AddressGeneral.Builder()
							.withStreet("RUA")
							.withNumber("320")
							.withComplement("CASA")
							.withDistrict("oficinas")
							.withCityCode("4119905")
							.withCityName("Ponta Grossa")
							.withZipCode("84035560")
							.withUF(UF.PR)
							.withCountryCode("1058")
							.withCountryName("BRASIL")
							.build())
					.withEmail("gomesflu@hotmail.com")
					.build())					
				.withReceiver(new Receiver.Builder()
					.asLegalEntity()
						.withCNPJ("69305329000167")
						.withIE("10101010101010")
						.ok()
					.withName("teste validacao")
					.withFone("4299561913")
					.withAddress(new AddressGeneral.Builder()
							.withStreet("RUA")
							.withNumber("320")
							.withComplement("CASA")
							.withDistrict("oficinas")
							.withCityCode("4119905")
							.withCityName("Ponta Grossa")
							.withZipCode("84035560")
							.withUF(UF.PR)
							.withCountryCode("1058")
							.withCountryName("BRASIL")
							.build())
					.withEmail("gomesflu@hotmail.com")					
					.build())
				.withAddressee(new Addressee.Builder()
					.asNaturalPerson()
					.withCPF("08775530929")
					.withIE("")
					.ok()
					.withName("teste validacao")
					.withFone("4299561913")
					.withRegistrationSUFRAMA("10101010")
						.withAddress(new AddressGeneral.Builder()
							.withStreet("RUA")
							.withNumber("320")
							.withComplement("CASA")
							.withDistrict("oficinas")
							.withCityCode("4119905")
							.withCityName("Ponta Grossa")
							.withZipCode("84035560")
							.withUF(UF.PR)
							.withCountryCode("1058")
							.withCountryName("BRASIL")
							.build())
				.withEmail("gomesflu@hotmail.com")
					.withLocationDelivery(new Location.Builder()
					.asLegalEntity()
					.withCNPJ("69305329000167")
					.withName("TESTE")
					.withStreet("RUA JOSE")
					.withNumber("322")
					.withComplement("casa")
					.withDistrict("Oficinas")
					.withCityCode("4119905")
					.withCityName("Ponta Grossa")
					.withUF(UF.PR)					
					.build())
				.build())
				.withValuesServiceDelivery(new ValuesServiceDelivery.Builder()
					.withDelivertService("13")
					.withValueReceivable("13")
					.withInstallmentValueComponent(new InstallmentValueComponent.Builder()
						.withName("Teste")
						.withValue("10")
						.builder())
					.build())
				.build())
			.build())
			.withTypeStatusProtocol(new StatusProtocol.Builder()
				.withStatusProtocolData(new StatusProtocolInfo.Builder()
				.withTypeEnvironment(TypeEnvironment.PRODUCTION)
				.withAapplicationVersion("1.00")
				.withAccessKeyCte("35150276302157001296570010002051661000280580")
				.withDateProcessing("2015-02-10T20:11:06")
				.withNumberProtocol("135150478724355")
				.withDigestValue("wGYp6x1yW2ehlRpHKDhCLbHAa3c")
				.withStatusCodeCte("100")
				.withLiteralDescription("Autorizado o uso do CT-e")
				.build())		
			
			.build())		
		.build();
//@formatter:on;
    }
}
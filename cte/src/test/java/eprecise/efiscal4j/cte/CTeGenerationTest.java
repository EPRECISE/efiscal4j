package eprecise.efiscal4j.cte;

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
import eprecise.efiscal4j.cte.address.Address;
import eprecise.efiscal4j.cte.address.UF;
import eprecise.efiscal4j.cte.payment.PaymentMethod;
import eprecise.efiscal4j.cte.person.Receiver;
import eprecise.efiscal4j.cte.person.Sender;
import eprecise.efiscal4j.cte.person.Shipper;
import eprecise.efiscal4j.cte.serviceTaker.ServiceTaker;

public class CTeGenerationTest {
    
    @Test
    public void validateByBeanValidation() {
	final CTe cte = this.buildCTe();
	try {
	    ValidationBuilder.from(cte).validate().throwIfViolate();
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
	final FiscalDocumentValidator validator = new FiscalDocumentValidator(this.getClass().getResource("/eprecise/efiscal4j/cte/cte_v2.00.xsd"));
	final CTe cte = this.buildCTe();
	final String serialize = new FiscalDocumentSerializer<>(cte).serialize();
	final ValidationResult validate = validator.validate(serialize);
	Assert.assertTrue(validate.getError(), validate.isValid());
    }
    
    private CTe buildCTe() {
	//@formatter:off
	return new CTe.Builder()
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
				    .withTypeEnvironment(TypeEnvironment.PAISAGEM)
				    .withCTeType(CTeType.NORMAL)
				    .withIdentifierEmission(IdentifierEmission.APLICATIVO_CONTRIBUINTE)
				    .withEmissionProcessVersion("1")
				    .withRefAccessKey("42131084684182000157550010000000020108042108")
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
				    .withServiceTaker(
					    new ServiceTaker.Builder().fromType(ServiceTaker.OTHERS)
								      .withCNPJ("44.319.641/0001-89")
								      .withCPF("08775530929")
								      .withIE("ISENTO")
								      .withCorporateName("Pedro Silva")
								      .withFantasyName("Jose MM LTDA")
								      .withEmail("teste@cte.com.br")
								      .withAddressData(
									      new Address.Builder().withStreet("RUA")
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
        					       	.withUF(UF.PR)
        					       	.withCountryCode("1058")
        					       	.withCountryName("BRASIL")
        					       	.build())
        				.build())
        			.withSender(new Sender.Builder()
        				.asLegalEntity()
        					.withCNPJ("69305329000167")
        					.withIE("ISENTO")
        					.ok()
        				.withName("teste validacao")
        				.withFone("4299561913")
        				.withEmail("gomesflu@hotmail.com")
        				.withFantasyName("Nome De teste")
        				.withLocationCollection(new Location.Builder()
        							.asLegalEntity()
        							.withCNPJ("69.305.329/0001-67")
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
							.withUF(UF.PR)
							.withCountryCode("1058")
							.withCountryName("BRASIL")
							.build())
					.build())
				.withReceiver(new Receiver.Builder()
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
							.withUF(UF.PR)
							.withCountryCode("1058")
							.withCountryName("BRASIL")
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
			.build();
//@formatter:on;
    }
}
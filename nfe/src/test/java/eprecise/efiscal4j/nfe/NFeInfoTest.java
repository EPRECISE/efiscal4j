
package eprecise.efiscal4j.nfe;

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

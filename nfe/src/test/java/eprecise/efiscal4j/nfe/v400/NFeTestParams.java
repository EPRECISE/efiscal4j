
package eprecise.efiscal4j.nfe.v400;

import eprecise.efiscal4j.nfe.v310.domain.TestDomain;


public class NFeTestParams {

    private static final String EMITTER_CNPJ_PROPERTY = "eprecise.efiscal4j.nfe.emitter.cnpj";

    private static final String EMITTER_IE_PROPERTY = "eprecise.efiscal4j.nfe.emitter.ie";

    // public static final String RECEIVER_LEGAL_ENTITY_CORPORATENAME_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.corporatename";

    private static final String RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.cnpj";

    private static final String RECEIVER_LEGAL_ENTITY_IE_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.ie";

    private static final String RECEIVER_NATURAL_PERSON_CPF_PROPERTY = "eprecise.efiscal4j.nfe.receiver.naturalperson.cpf";

    private static final String RECEIVER_NATURAL_PERSON_IE_PROPERTY = "eprecise.efiscal4j.nfe.receiver.naturalperson.ie";

    private static final String CERTIFICATE_PIN_PROPERTY = "eprecise.efiscal4j.commons.certificate.pin";

    private static final String CERTIFICATE_PATH_PROPERTY = "eprecise.efiscal4j.commons.certificate.path";

    private static final String EMITTER_CSC_CLDTOKEN_PROPERTY = "eprecise.efiscal4j.nfce.emitter.csc.cldtoken";

    private static final String EMITTER_CSC_VALUE_PROPERTY = "eprecise.efiscal4j.nfce.emitter.csc.value";

    public static String getEmitterCnpj() {
        return System.getProperty(EMITTER_CNPJ_PROPERTY);
    }

    public static String getEmitterIe() {
        return System.getProperty(EMITTER_IE_PROPERTY);
    }

    public static String getReceiverCnpj() {
        return System.getProperty(RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY);
    }

    public static String getReceiverIe() {
        return System.getProperty(RECEIVER_LEGAL_ENTITY_IE_PROPERTY);
    }

    public static String getCertificatePath() {
        return System.getProperty(CERTIFICATE_PATH_PROPERTY);
    }

    public static String getCertificatePin() {
        return System.getProperty(CERTIFICATE_PIN_PROPERTY);
    }

}

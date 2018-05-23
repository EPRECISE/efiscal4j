
package eprecise.efiscal4j.nfe.v400;

import java.util.Optional;

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

    public static Optional<String> getEmitterCnpj() {
        return Optional.ofNullable(System.getProperty(EMITTER_CNPJ_PROPERTY));
    }

    public static Optional<String> getEmitterIe() {
        return Optional.ofNullable(System.getProperty(EMITTER_IE_PROPERTY));
    }

    public static Optional<String> getReceiverCnpj() {
        return Optional.ofNullable(System.getProperty(RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY));
    }

    public static Optional<String> getReceiverIe() {
        return Optional.ofNullable(System.getProperty(RECEIVER_LEGAL_ENTITY_IE_PROPERTY));
    }

    public static Optional<String> getCertificatePath() {
        return Optional.ofNullable(System.getProperty(CERTIFICATE_PATH_PROPERTY));
    }

    public static Optional<String> getCertificatePin() {
        return Optional.ofNullable(System.getProperty(CERTIFICATE_PIN_PROPERTY));
    }

}

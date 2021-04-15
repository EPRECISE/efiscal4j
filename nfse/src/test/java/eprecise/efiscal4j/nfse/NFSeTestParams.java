
package eprecise.efiscal4j.nfse;

import java.util.Optional;

public class NFSeTestParams {

    private static final String EMITTER_CNPJ_PROPERTY = "eprecise.efiscal4j.nfe.emitter.cnpj";

    private static final String EMITTER_IM_PROPERTY = "eprecise.efiscal4j.nfe.emitter.im";

    private static final String EMITTER_PASSWORD_PROPERTY = "eprecise.efiscal4j.nfe.emitter.password";

    private static final String EMITTER_IE_PROPERTY = "eprecise.efiscal4j.nfe.emitter.ie";

    public static final String RECEIVER_LEGAL_ENTITY_CORPORATENAME_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.corporatename";

    private static final String RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.cnpj";

    private static final String RECEIVER_LEGAL_ENTITY_IE_PROPERTY = "eprecise.efiscal4j.nfe.receiver.legalentity.ie";

    private static final String RECEIVER_NATURAL_PERSON_CPF_PROPERTY = "eprecise.efiscal4j.nfe.receiver.naturalperson.cpf";

    private static final String CERTIFICATE_PIN_PROPERTY = "eprecise.efiscal4j.commons.certificate.pin";

    private static final String CERTIFICATE_PATH_PROPERTY = "eprecise.efiscal4j.commons.certificate.path";

    private static final String NFE_IMPORT_XML_PATH_PROPERTY = "eprecise.efiscal4j.nfe.import.xml.path";

    public static Optional<String> getEmitterCnpj() {
        return Optional.ofNullable(System.getProperty(EMITTER_CNPJ_PROPERTY));
    }

    public static Optional<String> getEmitterIm() {
        return Optional.ofNullable(System.getProperty(EMITTER_IM_PROPERTY));
    }

    public static Optional<String> getEmitterPassword() {
        return Optional.ofNullable(System.getProperty(EMITTER_PASSWORD_PROPERTY));
    }

    public static Optional<String> getEmitterIe() {
        return Optional.ofNullable(System.getProperty(EMITTER_IE_PROPERTY));
    }

    public static Optional<String> getReceiverLegalEntityCorporateName() {
        return Optional.ofNullable(System.getProperty(RECEIVER_LEGAL_ENTITY_CORPORATENAME_PROPERTY));
    }

    public static Optional<String> getReceiverCorporateName() {
        return Optional.ofNullable(System.getProperty(RECEIVER_NATURAL_PERSON_CPF_PROPERTY));
    }

    public static Optional<String> getReceiverCnpj() {
        return Optional.ofNullable(System.getProperty(RECEIVER_LEGAL_ENTITY_CNPJ_PROPERTY));
    }

    public static Optional<String> getReceiverCpf() {
        return Optional.ofNullable(System.getProperty(RECEIVER_NATURAL_PERSON_CPF_PROPERTY));
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

    public static Optional<String> getNFeImportXmlPath() {
        return Optional.ofNullable(System.getProperty(NFE_IMPORT_XML_PATH_PROPERTY));
    }

}

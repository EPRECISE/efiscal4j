
package eprecise.efiscal4j.nfe.transmission;

import java.io.Serializable;

import eprecise.efiscal4j.commons.domain.FiscalDocumentService;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.properties.PropertiesLoader;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;


/**
 * Identificação dos Web Services de NFC-e
 *
 * @author Felipe Bueno
 * @author Fernando Glizt
 *
 */

public enum NFCeService implements FiscalDocumentService, Serializable {

    AUTHORIZATION("/eprecise/efiscal4j/nfe/transmission/nfce/authorizationHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/nfce/authorizationProduction.properties"),
    AUTHORIZATION_RESULT("/eprecise/efiscal4j/nfe/transmission/nfce/authorizationResultHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/nfce/authorizationResultProduction.properties"),
    EVENT_RECEPTION("/eprecise/efiscal4j/nfe/transmission/nfce/eventReceptionHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/nfce/eventReceptionProduction.properties"),
    PROTOCOL_SEARCH("/eprecise/efiscal4j/nfe/transmission/nfce/protocolSearchHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/nfce/protocolSearchProduction.properties"),
    SERVICE_STATUS("/eprecise/efiscal4j/nfe/transmission/nfce/serviceStatusHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/nfce/serviceStatusProduction.properties"),
    DISABILITY("/eprecise/efiscal4j/nfe/transmission/nfce/disabilityHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/nfce/disabilityProduction.properties");

    private static final long serialVersionUID = 1L;

    private FiscalDocumentVersion supportedVersion;

    private final PropertiesLoader nFeServiceHomologMap;

    private final PropertiesLoader nFeServiceProductionMap;

    private NFCeService(String propertiesHomologPath, String propertiesProductionPath) {
        this.nFeServiceHomologMap = new PropertiesLoader.Builder().resourceLoader(NFCeService.class).from(propertiesHomologPath).create();
        this.nFeServiceProductionMap = new PropertiesLoader.Builder().resourceLoader(NFCeService.class).from(propertiesProductionPath).create();
    }

    public String getHomologUrl(NFCeServiceDomain serviceDomain) {
        return this.getUrl(serviceDomain, TransmissionEnvironment.HOMOLOGACAO);
    }

    public String getHomologUrl(UF uf) {
        return this.getUrl(this.getNFCeServiceDomainByUf(uf), TransmissionEnvironment.HOMOLOGACAO);
    }

    public String getProductionUrl(NFCeServiceDomain serviceDomain) {
        return this.getUrl(serviceDomain, TransmissionEnvironment.PRODUCAO);
    }

    public String getProductionUrl(UF uf) {
        return this.getUrl(this.getNFCeServiceDomainByUf(uf), TransmissionEnvironment.PRODUCAO);
    }

    /**
     * Valida a estrutura da Sefaz, que define qual UF utiliza qual ambiente de serviço
     *
     * @return
     */
    public NFCeServiceDomain getNFCeServiceDomainByUf(UF uf) {
        // TODO Verificar como retornaria ambiente de contingência
        //@formatter:off
        switch (uf) {
        default:
            return NFCeServiceDomain.findByAcronym(uf.getAcronym());
        }
        //@formatter:on
    }

    private String getUrl(NFCeServiceDomain serviceDomain, TransmissionEnvironment environment) {
        if (!serviceDomain.getServices().contains(this)) {
            throw new UnsupportedOperationException("O serviço " + this.toString() + " não está implementado para a Domínio " + serviceDomain.getDescription());
        }

        FiscalDocumentVersion version = null;

        for (final FiscalDocumentService fiscalDocumentService : serviceDomain.getServices()) {
            if (fiscalDocumentService instanceof NFCeService && fiscalDocumentService == this) {
                version = ((NFCeService) fiscalDocumentService).getVersion();
                break;
            }
        }

        String url = "";
        switch (environment) {
        case HOMOLOGACAO:
            url = this.nFeServiceHomologMap.valueFrom(serviceDomain.toString() + ":" + version.getValue());
            break;
        case PRODUCAO:
            url = this.nFeServiceProductionMap.valueFrom(serviceDomain.toString() + ":" + version.getValue());
        }

        if (url == null) {
            throw new UnsupportedOperationException("O serviço " + this.toString() + " não está implementado para a Domínio " + serviceDomain.getDescription() + ", na versão " + version.getValue()
                    + ", no ambiente de " + environment.getDescription());
        }

        return url;
    }

    public FiscalDocumentVersion getVersion() {
        return this.supportedVersion;
    }

    public NFCeService withSupportedVersion(FiscalDocumentVersion supportedVersion) {
        this.supportedVersion = supportedVersion;
        return this;
    }

    class NFCeServiceDomainVersion {

        private final NFCeServiceDomain serviceDomain;

        private final FiscalDocumentVersion fiscalDocumentVersion;

        public NFCeServiceDomainVersion(NFCeServiceDomain serviceDomain, FiscalDocumentVersion fiscalDocumentVersion) {
            this.serviceDomain = serviceDomain;
            this.fiscalDocumentVersion = fiscalDocumentVersion;
        }

        public NFCeServiceDomain getNFCeServiceDomain() {
            return this.serviceDomain;
        }

        public FiscalDocumentVersion getFiscalDocumentVersion() {
            return this.fiscalDocumentVersion;
        }

    }
}

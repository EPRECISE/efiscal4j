
package eprecise.efiscal4j.nfe.v400.transmission;

import java.io.Serializable;

import eprecise.efiscal4j.commons.domain.FiscalDocumentService;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.properties.PropertiesLoader;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;


/**
 * Identificação dos Web Services de NF-e
 *
 * @author Felipe Bueno
 *
 */

public enum NFeService implements FiscalDocumentService, Serializable {

                                                                       AUTHORIZATION("/eprecise/efiscal4j/nfe/v400/transmission/authorizationHomolog.properties",
                                                                               "/eprecise/efiscal4j/nfe/v400/transmission/authorizationProduction.properties"),
                                                                       AUTHORIZATION_RESULT("/eprecise/efiscal4j/nfe/v400/transmission/authorizationResultHomolog.properties",
                                                                               "/eprecise/efiscal4j/nfe/v400/transmission/authorizationResultProduction.properties"),
                                                                       EVENT_RECEPTION("/eprecise/efiscal4j/nfe/v400/transmission/eventReceptionHomolog.properties",
                                                                               "/eprecise/efiscal4j/nfe/v400/transmission/eventReceptionProduction.properties"),
                                                                       PROTOCOL_SEARCH("/eprecise/efiscal4j/nfe/v400/transmission/protocolSearchHomolog.properties",
                                                                               "/eprecise/efiscal4j/nfe/v400/transmission/protocolSearchProduction.properties"),
                                                                       SERVICE_STATUS("/eprecise/efiscal4j/nfe/v400/transmission/serviceStatusHomolog.properties",
                                                                               "/eprecise/efiscal4j/nfe/v400/transmission/serviceStatusProduction.properties"),
                                                                       DISABILITY("/eprecise/efiscal4j/nfe/v400/transmission/disabilityHomolog.properties",
                                                                               "/eprecise/efiscal4j/nfe/v400/transmission/disabilityProduction.properties"),
                                                                       DELIVERY_DFE("/eprecise/efiscal4j/nfe/v400/transmission/deliveryDFeHomolog.properties",
                                                                               "/eprecise/efiscal4j/nfe/v400/transmission/deliveryDFeProduction.properties");

    private static final long serialVersionUID = 1L;

    private FiscalDocumentVersion supportedVersion;

    private final PropertiesLoader nFeServiceHomologMap;

    private final PropertiesLoader nFeServiceProductionMap;

    private NFeService(final String propertiesHomologPath, final String propertiesProductionPath) {
        this.nFeServiceHomologMap = new PropertiesLoader.Builder().resourceLoader(NFeService.class).from(propertiesHomologPath).create();
        this.nFeServiceProductionMap = new PropertiesLoader.Builder().resourceLoader(NFeService.class).from(propertiesProductionPath).create();
    }

    public String getHomologUrl(final ServiceDomain serviceDomain) {
        return this.getUrl(serviceDomain, TransmissionEnvironment.HOMOLOGACAO);
    }

    public String getHomologUrl(final UF uf) {
        return this.getUrl(this.getServiceDomainByUf(uf), TransmissionEnvironment.HOMOLOGACAO);
    }

    public String getProductionUrl(final ServiceDomain serviceDomain) {
        return this.getUrl(serviceDomain, TransmissionEnvironment.PRODUCAO);
    }

    public String getProductionUrl(final UF uf) {
        return this.getUrl(this.getServiceDomainByUf(uf), TransmissionEnvironment.PRODUCAO);
    }

    /**
     * Valida a estrutura da Sefaz, que define qual UF utiliza qual ambiente de serviço
     *
     * @return
     */
    public ServiceDomain getServiceDomainByUf(final UF uf) {
        // TODO Verificar como retornaria ambiente de contingência
        //@formatter:off
        switch (uf) {
            case MA:
            case PA:
                return ServiceDomain.SVRS;
            case PI:
            case AC:
            case AL:
            case AP:
            case DF:
            case PB:
            case RJ:
            case RN:
            case RO:
            case RR:
            case SC:
            case SE:
            case TO:
            case ES:
                return ServiceDomain.SVRS;
            default:
                return ServiceDomain.findByAcronym(uf.getAcronym());
        }
        //@formatter:on
    }

    private String getUrl(final ServiceDomain serviceDomain, final TransmissionEnvironment environment) {
        if (!serviceDomain.getServices().contains(this)) {
            throw new UnsupportedOperationException("O serviço " + this.toString() + " não está implementado para a Domínio " + serviceDomain.getDescription());
        }

        FiscalDocumentVersion version = null;

        for (final FiscalDocumentService fiscalDocumentService : serviceDomain.getServices()) {
            if ((fiscalDocumentService instanceof NFeService) && (fiscalDocumentService == this)) {
                version = ((NFeService) fiscalDocumentService).getVersion();
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

    public NFeService withSupportedVersion(final FiscalDocumentVersion supportedVersion) {
        this.supportedVersion = supportedVersion;
        return this;
    }

    class ServiceDomainVersion {

        private final ServiceDomain serviceDomain;

        private final FiscalDocumentVersion fiscalDocumentVersion;

        public ServiceDomainVersion(final ServiceDomain serviceDomain, final FiscalDocumentVersion fiscalDocumentVersion) {
            this.serviceDomain = serviceDomain;
            this.fiscalDocumentVersion = fiscalDocumentVersion;
        }

        public ServiceDomain getServiceDomain() {
            return this.serviceDomain;
        }

        public FiscalDocumentVersion getFiscalDocumentVersion() {
            return this.fiscalDocumentVersion;
        }

    }
}

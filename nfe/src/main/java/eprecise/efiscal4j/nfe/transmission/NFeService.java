
package eprecise.efiscal4j.nfe.transmission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import eprecise.efiscal4j.commons.domain.FiscalDocumentService;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.properties.PropertiesLoader;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;


/**
 * Identificação dos Web Services de NF-e
 * 
 * @author Felipe Bueno
 *
 */

public enum NFeService implements FiscalDocumentService, Serializable {

    AUTHORIZATION("/eprecise/efiscal4j/nfe/transmission/authorizationHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/authorizationProduction.properties"),
    AUTHORIZATION_RESULT("/eprecise/efiscal4j/nfe/transmission/authorizationResultHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/authorizationResultProduction.properties"),
    EVENT_RECEPTION("/eprecise/efiscal4j/nfe/transmission/eventReceptionHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/eventReceptionProduction.properties"),
    PROTOCOL_SEARCH("/eprecise/efiscal4j/nfe/transmission/protocolSearchHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/protocolSearchProduction.properties"),
    SERVICE_STATUS("/eprecise/efiscal4j/nfe/transmission/serviceStatusHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/serviceStatusProduction.properties"),
    DISABILITY("/eprecise/efiscal4j/nfe/transmission/disabilityHomolog.properties", "/eprecise/efiscal4j/nfe/transmission/disabilityProduction.properties");

    private static final long serialVersionUID = 1L;

    private List<ServiceDomainVersion> serviceDomainVersion;

    private FiscalDocumentVersion supportedVersion;

    private final PropertiesLoader nFeServiceHomologMap;

    private final PropertiesLoader nFeServiceProductionMap;

    private NFeService(String propertiesHomologPath, String propertiesProductionPath) {
        this.nFeServiceHomologMap = new PropertiesLoader.Builder().resourceLoader(NFeService.class).from(propertiesHomologPath).create();
        this.nFeServiceProductionMap = new PropertiesLoader.Builder().resourceLoader(NFeService.class).from(propertiesProductionPath).create();
        // this.initServiceDomainMap();
    }

    private void initServiceDomainMap() {
        this.serviceDomainVersion = new ArrayList<ServiceDomainVersion>();
        //@formatter:off
        switch(this){
            case AUTHORIZATION:
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.AM, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.BA, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.CE, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.GO, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.MG, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.MS, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.MT, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.PE, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.PR, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.RS, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.SP, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.SVAN, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.SVRS, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.SVC_AN, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.SVC_RS, FiscalDocumentVersion.VERSION_3_10));
                break;                
            case AUTHORIZATION_RESULT:
                break;
            case EVENT_RECEPTION:
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.AM, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.BA, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.CE, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.GO, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.MG, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.MS, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.MT, FiscalDocumentVersion.VERSION_2_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.PE, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.PR, FiscalDocumentVersion.VERSION_3_10));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.RS, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.SP, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.SVAN, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.SVRS, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.SVC_AN, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.SVC_RS, FiscalDocumentVersion.VERSION_1_00));
                this.serviceDomainVersion.add(new ServiceDomainVersion(ServiceDomain.AN, FiscalDocumentVersion.VERSION_3_10));
                break;                                
            case PROTOCOL_SEARCH:
                break;
            case DISABILITY:
                break;
            case SERVICE_STATUS:
                break;

//            AM(UF.AM.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            BA(UF.BA.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            CE(UF.CE.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            GO(UF.GO.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),    
//            MG(UF.MG.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            MA(UF.MA.getDescription()),            
//            MS(UF.MS.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            MT(UF.MT.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_2_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_2_00), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_2_00), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_2_00)),
//            PE(UF.PE.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            PR(UF.PR.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            RS(UF.RS.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00),
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            SP(UF.SP.getDescription(), 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            SVAN("Sefaz Virtual - Ambiente Nacional", 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            SVRS("Sefaz Virtual - Rio Grande do Sul", 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            SVC_AN("Sefaz Virtual Contingência - Ambiente Nacional", 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            SVC_RS("Sefaz Virtual Contingência - Rio Grande do Sul", 
//                    NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
//                    NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
//                    NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
//            AN("Ambiente Nacional",              
//                    NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10));                  
        //@formatter:on        
        }

    }

    public String getHomologUrl(ServiceDomain serviceDomain) {
        return this.getUrl(serviceDomain, TransmissionEnvironment.HOMOLOGACAO);
    }

    public String getHomologUrl(UF uf) {
        return this.getUrl(this.getServiceDomainByUf(uf), TransmissionEnvironment.HOMOLOGACAO);
    }

    public String getProductionUrl(ServiceDomain serviceDomain) {
        return this.getUrl(serviceDomain, TransmissionEnvironment.PRODUCAO);
    }

    public String getProductionUrl(UF uf) {
        return this.getUrl(this.getServiceDomainByUf(uf), TransmissionEnvironment.PRODUCAO);
    }

    /**
     * Valida a estrutura da Sefaz, que define qual UF utiliza qual ambiente de serviço
     * 
     * @return
     */
    public ServiceDomain getServiceDomainByUf(UF uf) {
        // TODO Verificar como retornaria ambiente de contingência
        //@formatter:off
        switch (uf) {
            case MA:
            case PA:
            case PI:
                return ServiceDomain.SVAN;
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
                return ServiceDomain.SVRS;
            default:
                return ServiceDomain.findByAcronym(uf.getAcronym());
        }
        //@formatter:on
    }

    private String getUrl(ServiceDomain serviceDomain, TransmissionEnvironment environment) {
        if (!serviceDomain.getServices().contains(this)) {
            throw new UnsupportedOperationException("O serviço " + this.toString() + " não está implementado para a Domínio " + serviceDomain.getDescription());
        }

        FiscalDocumentVersion version = null;

        for (final FiscalDocumentService fiscalDocumentService : serviceDomain.getServices()) {
            if (fiscalDocumentService instanceof NFeService && fiscalDocumentService == this) {
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

    public NFeService withSupportedVersion(FiscalDocumentVersion supportedVersion) {
        this.supportedVersion = supportedVersion;
        return this;
    }

    class ServiceDomainVersion {

        private final ServiceDomain serviceDomain;

        private final FiscalDocumentVersion fiscalDocumentVersion;

        public ServiceDomainVersion(ServiceDomain serviceDomain, FiscalDocumentVersion fiscalDocumentVersion) {
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

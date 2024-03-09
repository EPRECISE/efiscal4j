
package eprecise.efiscal4j.nfe.v400.transmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eprecise.efiscal4j.commons.domain.FiscalDocumentService;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.transmission.NFeServiceDomain;


/**
 * Domínio de Serviço (UFs + ambientes nacionais e de contingência)
 * 
 * @author Felipe Bueno
 *
 */

public enum ServiceDomain implements NFeServiceDomain {

//@formatter:off    
    AM(UF.AM.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    BA(UF.BA.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    CE(UF.CE.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    GO(UF.GO.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),    
    MG(UF.MG.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    MA(UF.MA.getDescription()),            
    MS(UF.MS.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    MT(UF.MT.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    PE(UF.PE.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    PR(UF.PR.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    RS(UF.RS.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00),
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00),
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00),
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00),
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    SP(UF.SP.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00),
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    SVAN("Sefaz Virtual - Ambiente Nacional", 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    SVRS("Sefaz Virtual - Rio Grande do Sul", 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    SVC_AN("Sefaz Virtual Contingência - Ambiente Nacional", 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    SVC_RS("Sefaz Virtual Contingência - Rio Grande do Sul", 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00)),
    AN("Ambiente Nacional",
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00),
            NFeService.DELIVERY_DFE.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00));    
//@formatter:on

    private static final long serialVersionUID = 1L;

    private String description;

    private final List<FiscalDocumentService> services = new ArrayList<>();

    private ServiceDomain(String description, FiscalDocumentService... services) {
        this.description = description;
        this.services.addAll(Arrays.asList(services));
    }
    
    private ServiceDomain(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public List<FiscalDocumentService> getServices() {
        return this.services;
    }

    public String getAcronym() {
        return this.toString();
    }

    public static NFeServiceDomain findRelationByUF(UF uf) {
        switch (uf) {
            case AC:
            case AL:
            case AP:
            case CE:
            case DF:
            case ES:
            case PB:
            case PI:
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
    }

    public static ServiceDomain findByAcronym(String acronym) {
        for (final ServiceDomain serviceDomain : values()) {
            if (serviceDomain.getAcronym().equals(acronym)) {
                return serviceDomain;
            }
        }
        return null;
    }
}

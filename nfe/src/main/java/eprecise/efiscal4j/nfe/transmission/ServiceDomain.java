
package eprecise.efiscal4j.nfe.transmission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eprecise.efiscal4j.commons.domain.FiscalDocumentService;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;


/**
 * Domínio de Serviço (UFs + ambientes nacionais de de contingência)
 * 
 * @author Felipe Bueno
 *
 */

public enum ServiceDomain implements Serializable {

//@formatter:off    
    AM(UF.AM.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    BA(UF.BA.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    CE(UF.CE.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    GO(UF.GO.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),    
    MG(UF.MG.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    MA(UF.MA.getDescription()),            
    MS(UF.MS.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    MT(UF.MT.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_2_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_2_00), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_2_00), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_2_00)),
    PE(UF.PE.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    PR(UF.PR.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    RS(UF.RS.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00),
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    SP(UF.SP.getDescription(), 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    SVAN("Sefaz Virtual - Ambiente Nacional", 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    SVRS("Sefaz Virtual - Rio Grande do Sul", 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    SVC_AN("Sefaz Virtual Contingência - Ambiente Nacional", 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    SVC_RS("Sefaz Virtual Contingência - Rio Grande do Sul", 
            NFeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00), 
            NFeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10), 
            NFeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10)),
    AN("Ambiente Nacional",              
            NFeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_1_00));    
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

    public static ServiceDomain findByAcronym(String acronym) {
        for (final ServiceDomain serviceDomain : values()) {
            if (serviceDomain.getAcronym().equals(acronym)) {
                return serviceDomain;
            }
        }
        return null;
    }
}

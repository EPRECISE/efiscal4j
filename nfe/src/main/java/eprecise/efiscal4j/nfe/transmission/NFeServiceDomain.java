package eprecise.efiscal4j.nfe.transmission;

import java.util.List;

import eprecise.efiscal4j.commons.domain.FiscalDocumentService;

public interface NFeServiceDomain {
    
    String getAcronym();
    
    List<FiscalDocumentService> getServices();

}

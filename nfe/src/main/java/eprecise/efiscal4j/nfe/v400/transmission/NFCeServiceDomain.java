
package eprecise.efiscal4j.nfe.v400.transmission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eprecise.efiscal4j.commons.domain.FiscalDocumentService;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;


/**
 * Domínio de Serviço NFC-e
 *
 * @author Felipe Bueno
 * @author Fernando Glizt
 *
 */

public enum NFCeServiceDomain implements Serializable {

    //@formatter:off
    PR(UF.PR.getDescription(),
            NFCeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
            NFCeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
            NFCeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
            NFCeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
            NFCeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10),
            NFCeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_3_10));
    //@formatter:on

    private static final long serialVersionUID = 1L;

    private String description;

    private final List<FiscalDocumentService> services = new ArrayList<>();

    private NFCeServiceDomain(String description, FiscalDocumentService... services) {
        this.description = description;
        this.services.addAll(Arrays.asList(services));
    }

    private NFCeServiceDomain(String description) {
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

    public static NFCeServiceDomain findByAcronym(String acronym) {
        for (final NFCeServiceDomain serviceDomain : NFCeServiceDomain.values()) {
            if (serviceDomain.getAcronym().equals(acronym)) {
                return serviceDomain;
            }
        }
        return null;
    }
}

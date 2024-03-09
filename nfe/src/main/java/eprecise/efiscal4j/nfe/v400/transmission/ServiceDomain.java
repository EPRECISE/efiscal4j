
package eprecise.efiscal4j.nfe.v400.transmission;

import eprecise.efiscal4j.commons.domain.FiscalDocumentService;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.transmission.NFeServiceDomain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static eprecise.efiscal4j.commons.domain.FiscalDocumentVersion.VERSION_1_00;
import static eprecise.efiscal4j.nfe.v400.transmission.NFeService.*;


/**
 * Domínio de Serviço (UFs + ambientes nacionais e de contingência)
 *
 * @author Felipe Bueno
 */

public enum ServiceDomain implements NFeServiceDomain {

    AM(UF.AM.getDescription()),
    BA(UF.BA.getDescription()),
    GO(UF.GO.getDescription()),
    MG(UF.MG.getDescription()),
    MS(UF.MS.getDescription()),
    MT(UF.MT.getDescription()),
    PE(UF.PE.getDescription()),
    PR(UF.PR.getDescription()),
    RS(UF.RS.getDescription()),
    SP(UF.SP.getDescription()),
    SVAN("Sefaz Virtual - Ambiente Nacional"),
    SVRS("Sefaz Virtual - Rio Grande do Sul"),
    SVC_AN(
        "Sefaz Virtual Contingência - Ambiente Nacional",
        Arrays.asList(
            AUTHORIZATION,
            AUTHORIZATION_RESULT,
            EVENT_RECEPTION,
            PROTOCOL_SEARCH,
            SERVICE_STATUS
        )
    ),
    SVC_RS(
        "Sefaz Virtual Contingência - Rio Grande do Sul",
        Arrays.asList(
            AUTHORIZATION,
            AUTHORIZATION_RESULT,
            EVENT_RECEPTION,
            PROTOCOL_SEARCH,
            SERVICE_STATUS
        )
    ),
    AN(
        "Ambiente Nacional",
        Arrays.asList(
            EVENT_RECEPTION,
            DELIVERY_DFE.withSupportedVersion(VERSION_1_00)
        )
    );

    private static final long serialVersionUID = 1L;

    private final String description;

    private final List<FiscalDocumentService> services;

    ServiceDomain(String description, List<FiscalDocumentService> services) {
        this.description = description;
        this.services = services.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    ServiceDomain(String description) {
        this(
            description,
            Arrays.asList(
                AUTHORIZATION,
                AUTHORIZATION_RESULT,
                EVENT_RECEPTION,
                PROTOCOL_SEARCH,
                SERVICE_STATUS,
                DISABILITY
            )
        );
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

    public static ServiceDomain findBy(UF uf) {
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
                return Stream.of(values())
                    .filter(it -> it.getAcronym().equals(uf.getAcronym()))
                    .findFirst()
                    .orElse(null);
        }
    }

}

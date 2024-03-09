
package eprecise.efiscal4j.nfe.v400.transmission;

import eprecise.efiscal4j.commons.domain.FiscalDocumentService;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.transmission.NFeServiceDomain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static eprecise.efiscal4j.nfe.v400.transmission.NFCeService.*;


/**
 * Domínio de Serviço NFC-e
 *
 * @author Felipe Bueno
 * @author Fernando Glizt
 *
 */

public enum NFCeServiceDomain implements NFeServiceDomain, Serializable {

    AC(UF.AC.getDescription()),
    AL(UF.AL.getDescription()),
    AM(UF.AM.getDescription()),
    AP(UF.AP.getDescription()),
    BA(UF.BA.getDescription()),
    CE(UF.CE.getDescription()),
    DF(UF.DF.getDescription()),
    ES(UF.ES.getDescription()),
    GO(UF.GO.getDescription()),
    MA(UF.MA.getDescription()),
    MG(UF.MG.getDescription()),
    MT(UF.MT.getDescription()),
    MS(UF.AC.getDescription()),
    PA(UF.PA.getDescription()),
    PB(UF.PB.getDescription()),
    PE(UF.PE.getDescription()),
    PI(UF.PI.getDescription()),
    PR(UF.PR.getDescription()),
    RJ(UF.RJ.getDescription()),
    RN(UF.RN.getDescription()),
    RO(UF.RO.getDescription()),
    RR(UF.RR.getDescription()),
    RS(UF.RS.getDescription()),
    SC(UF.AC.getDescription(), Collections.emptyList()),
    SE(UF.SE.getDescription()),
    SP(UF.SP.getDescription()),
    TO(UF.TO.getDescription()),
    SVRS("Sefaz Virtual - Rio Grande do Sul");

    private static final long serialVersionUID = 1L;

    private final String description;

    private final List<FiscalDocumentService> services;

    NFCeServiceDomain(final String description, final List<FiscalDocumentService> services) {
        this.description = description;
        this.services = services.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    NFCeServiceDomain(final String description) {
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

    public static NFCeServiceDomain findBy(UF uf) {
        switch (uf) {
            case MA:
            case BA:
            case PA:
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
            case SE:
            case TO:
            case ES:
                return NFCeServiceDomain.SVRS;
            default:
                return Stream.of(values())
                    .filter(it -> it.getAcronym().equals(uf.getAcronym()))
                    .findFirst()
                    .orElse(null);
        }
    }
}

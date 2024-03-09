
package eprecise.efiscal4j.nfe.v400.transmission;

import eprecise.efiscal4j.commons.domain.FiscalDocumentService;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.transmission.NFeServiceDomain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * Domínio de Serviço NFC-e
 *
 * @author Felipe Bueno
 * @author Fernando Glizt
 *
 */

public enum NFCeServiceDomain implements NFeServiceDomain, Serializable {

    //@formatter:off
    AC(UF.AC.getDescription(), NFCeServiceDomain.getDefaultServices()),
    AL(UF.AL.getDescription(), NFCeServiceDomain.getDefaultServices()),
    AM(UF.AM.getDescription(), NFCeServiceDomain.getDefaultServices()),
    AP(UF.AP.getDescription(), NFCeServiceDomain.getDefaultServices()),
    BA(UF.BA.getDescription(), NFCeServiceDomain.getDefaultServices()),
    CE(UF.CE.getDescription(), NFCeServiceDomain.getDefaultServices()),
    DF(UF.DF.getDescription(), NFCeServiceDomain.getDefaultServices()),
    ES(UF.ES.getDescription(), NFCeServiceDomain.getDefaultServices()),
    GO(UF.GO.getDescription(), NFCeServiceDomain.getDefaultServices()),
    MA(UF.MA.getDescription(), NFCeServiceDomain.getDefaultServices()),
    MG(UF.MG.getDescription(), NFCeServiceDomain.getDefaultServices()),
    MT(UF.MT.getDescription(), NFCeServiceDomain.getDefaultServices()),
    MS(UF.AC.getDescription(), NFCeServiceDomain.getDefaultServices()),
    PA(UF.PA.getDescription(), NFCeServiceDomain.getDefaultServices()),
    PB(UF.PB.getDescription(), NFCeServiceDomain.getDefaultServices()),
    PE(UF.PE.getDescription(), NFCeServiceDomain.getDefaultServices()),
    PI(UF.PI.getDescription(), NFCeServiceDomain.getDefaultServices()),
    PR(UF.PR.getDescription(), NFCeServiceDomain.getDefaultServices()),
    RJ(UF.RJ.getDescription(), NFCeServiceDomain.getDefaultServices()),
    RN(UF.RN.getDescription(), NFCeServiceDomain.getDefaultServices()),
    RO(UF.RO.getDescription(), NFCeServiceDomain.getDefaultServices()),
    RR(UF.RR.getDescription(), NFCeServiceDomain.getDefaultServices()),
    RS(UF.RS.getDescription(), NFCeServiceDomain.getDefaultServices()),
    SC(UF.AC.getDescription()),
    SE(UF.SE.getDescription(), NFCeServiceDomain.getDefaultServices()),
    SP(UF.SP.getDescription(), NFCeServiceDomain.getDefaultServices()),
    TO(UF.TO.getDescription(), NFCeServiceDomain.getDefaultServices()),
    SVRS("Sefaz Virtual - Rio Grande do Sul", NFCeServiceDomain.getDefaultServices());
    //@formatter:on

    private static final long serialVersionUID = 1L;

    private static List<FiscalDocumentService> getDefaultServices() {
        return Arrays.asList(NFCeService.AUTHORIZATION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00),
                NFCeService.AUTHORIZATION_RESULT.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), NFCeService.EVENT_RECEPTION.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00),
                NFCeService.PROTOCOL_SEARCH.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00), NFCeService.SERVICE_STATUS.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00),
                NFCeService.DISABILITY.withSupportedVersion(FiscalDocumentVersion.VERSION_4_00));
    }

    private String description;

    private List<FiscalDocumentService> services = new ArrayList<>();

    private NFCeServiceDomain(final String description, final List<FiscalDocumentService> services) {
        this.description = description;
        this.services = services;
    }

    private NFCeServiceDomain(final String description, final FiscalDocumentService... services) {
        this(description, Arrays.asList(services));
    }

    private NFCeServiceDomain(final String description) {
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

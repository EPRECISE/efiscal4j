
package eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.rps.GovbrRps;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.services.GovbrService;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlRootElement(name = "DeclaracaoPrestacaoServico")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrStatementProvisionService implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "InfDeclaracaoPrestacaoServico") GovbrStatementProvisionServiceInfo info;

    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    @XmlRootElement(name = "InfDeclaracaoPrestacaoServico")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GovbrStatementProvisionServiceInfo {

        private @Getter final @XmlElement(name = "Rps") @NotNull GovbrRps rps;

        private @Getter final @NotNull @XmlElement(name = "Competencia") @NFSeDate String competence;

        private @Getter final @NotNull @XmlElement(name = "Servico") GovbrService service;

        private @Getter final @NotNull @XmlElement(name = "Prestador") GovbrIdentifier serviceProviderIdentifier;

        private @Getter final @XmlElement(name = "Tomador") GovbrServiceTaker serviceTaker;

        private @Getter final @XmlElement(name = "Intermediario") GovbrServiceIntermediary serviceIntermediary;

        private @Getter final @XmlElement(name = "ConstrucaoCivil") GovbrServiceConstruction construction;

        private @Getter final @XmlElement(name = "RegimeEspecialTributacao") GovbrSpecialTaxationRegime specialTaxationRegime;

        private @Getter final @NotNull @XmlElement(name = "OptanteSimplesNacional") CommonsNFSeBoolean nationalSimple;

        private @Getter final @NotNull @XmlElement(name = "IncentivoFiscal") CommonsNFSeBoolean taxIncentive;
    }
}

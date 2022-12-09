
package eprecise.efiscal4j.nfse.tc.goiania.lot.statements;

import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsServiceConstruction;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.rps.GoianiaRps;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.services.GoianiaService;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;
import eprecise.efiscal4j.signer.domain.SignatureType;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlRootElement(name = "Rps")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaStatementProvisionService implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "InfDeclaracaoPrestacaoServico") GoianiaStatementProvisionServiceInfo info;

    public @XmlElement(name = "Signature") SignatureType signature;

    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    @XmlRootElement(name = "InfDeclaracaoPrestacaoServico")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GoianiaStatementProvisionServiceInfo {

    	private @Getter final @XmlElement(name = "Rps") @NotNull GoianiaRps rps;

        private @Getter final @XmlElement(name = "Competencia") @NFSeDate String competence;

        private @Getter final @NotNull @XmlElement(name = "Servico") GoianiaService service;

        private @Getter final @NotNull @XmlElement(name = "Prestador") GoianiaIdentifier serviceProviderIdentifier;

        private @Getter final @XmlElement(name = "Tomador") GoianiaServiceTaker serviceTaker;

        private @Getter final @XmlElement(name = "Intermediario") GoianiaServiceIntermediary serviceIntermediary;

        private @Getter final @XmlElement(name = "ConstrucaoCivil") CommonsServiceConstruction construction;

        private @Getter final @XmlElement(name = "RegimeEspecialTributacao") GoianiaSpecialTaxationRegime specialTaxationRegime;

        private @Getter final @XmlElement(name = "OptanteSimplesNacional") CommonsNFSeBoolean nationalSimple;

        private @Getter final @XmlElement(name = "IncentivoFiscal") CommonsNFSeBoolean taxIncentive;

    }

}


package eprecise.efiscal4j.nfse.tc.goiania.lot.statements;

import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsServiceConstruction;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.services.GoianiaService;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class GoianiaNFSeStatementProvisionService implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "IdentificacaoRps") CommonsRpsIdentifier identifier;

    private @Getter final @NotNull @XmlElement(name = "DataEmissao") @NFSeDateTimeUTC String emissionDate;

    private @Getter final @XmlElement(name = "Competencia") @NFSeDate String competence;

    private @Getter final @NotNull @XmlElement(name = "Servico") GoianiaService service;

    private @Getter final @NotNull @XmlElement(name = "Prestador") GoianiaProvider serviceProviderIdentifier;

    private @Getter final @XmlElement(name = "Tomador") GoianiaServiceTaker serviceTaker;

    private @Getter final @XmlElement(name = "Intermediario") GoianiaServiceIntermediary serviceIntermediary;

    private @Getter final @XmlElement(name = "ConstrucaoCivil") CommonsServiceConstruction construction;

    private @Getter final @XmlElement(name = "RegimeEspecialTributacao") GoianiaSpecialTaxationRegime specialTaxationRegime;

    private @Getter final @XmlElement(name = "OptanteSimplesNacional") CommonsNFSeBoolean nationalSimple;

    private @Getter final @XmlElement(name = "IncentivoFiscal") CommonsNFSeBoolean taxIncentive;

}

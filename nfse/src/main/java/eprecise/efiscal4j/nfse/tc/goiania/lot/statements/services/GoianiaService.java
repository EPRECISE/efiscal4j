
package eprecise.efiscal4j.nfse.tc.goiania.lot.statements.services;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsIssRequirement;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeResponsibleRetention;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.govbr.types.NFSeItemServiceList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaService {

    private @Getter final @NotNull @XmlElement(name = "Valores") GoianiaValues values;

    private @Getter final @XmlElement(name = "IssRetido") CommonsNFSeBoolean issWithHeld;

    private @Getter final @XmlElement(name = "ResponsavelRetencao") CommonsNFSeResponsibleRetention responsibleRetention;

    private @Getter final @XmlElement(name = "ItemListaServico") @NFSeItemServiceList String itemServiceList;

    private @Getter final @XmlElement(name = "CodigoCnae") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cnaeCode;

    private @Getter final @XmlElement(name = "CodigoTributacaoMunicipio") @Size(min = 1, max = 20) String assessmentCityCode;

    private @Getter final @NotNull @XmlElement(name = "Discriminacao") @Size(min = 1, max = 1000) String discrimination;

    private @Getter final @NotNull @XmlElement(name = "CodigoMunicipio") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityCode;

    private @Getter final @XmlElement(name = "CodigoPais") @Size(min = 4, max = 4) String countryCode;

    private @Getter final @XmlElement(name = "ExigibilidadeISS") CommonsIssRequirement issRequirement;

    private @Getter final @XmlElement(name = "MunicipioIncidencia") @NFSeNonNegativeInteger @Size(
            min = 1, max = 7) String cityIncidenceCode;

    private @Getter final @XmlElement(name = "NumeroProcesso") @Size(min = 1, max = 30) String processNumber;

}

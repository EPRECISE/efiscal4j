
package eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.services;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaService {

    private @Getter final @NotNull @XmlElement(name = "Valores") CuritibaValues values;

    private @Getter final @NotNull @XmlElement(name = "ItemListaServico") @Size(min = 1, max = 5) String itemServiceList;

    private @Getter final @XmlElement(name = "CodigoCnae") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cnaeCode;

    private @Getter final @XmlElement(name = "CodigoTributacaoMunicipio") @Size(min = 1, max = 20) String assessmentCityCode;

    private @Getter final @NotNull @XmlElement(name = "Discriminacao") @Size(min = 1, max = 2000) String discrimination;

    private @Getter final @NotNull @XmlElement(name = "CodigoMunicipio") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityCode;

}

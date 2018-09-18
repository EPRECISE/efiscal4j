
package eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.services;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.ts.govbr.types.NFSeAliquot;
import eprecise.efiscal4j.nfse.ts.govbr.types.NFSeValue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrValues {

    private @Getter final @NotNull @XmlElement(name = "ValorServicos") @NFSeValue String serviceValue;

    private @Getter final @XmlElement(name = "ValorDeducoes") @NFSeValue String deductionValue;

    private @Getter final @XmlElement(name = "ValorPis") @NFSeValue String pisValue;

    private @Getter final @XmlElement(name = "ValorCofins") @NFSeValue String cofinsValue;

    private @Getter final @XmlElement(name = "ValorInss") @NFSeValue String inssValue;

    private @Getter final @XmlElement(name = "ValorIr") @NFSeValue String irValue;

    private @Getter final @XmlElement(name = "ValorCsll") @NFSeValue String csllValue;

    private @Getter final @XmlElement(name = "OutrasRetencoes") @NFSeValue String otherRetentionsValue;

    private @Getter final @XmlElement(name = "ValTotTributos") @NFSeValue String totalTaxValue;

    private @Getter final @XmlElement(name = "ValorIss") @NFSeValue String issValue;

    private @Getter final @XmlElement(name = "Aliquota") @NFSeAliquot String issAliquot;

    private @Getter final @XmlElement(name = "DescontoIncondicionado") @NFSeValue String discountUnconditionedValue;

    private @Getter final @XmlElement(name = "DescontoCondicionado") @NFSeValue String discountConditionedValue;

}

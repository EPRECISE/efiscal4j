
package eprecise.efiscal4j.nfse.tc.goiania.lot.statements.services;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.ts.commons.types.NFSeAliquot;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeValue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaValues {

    private final @Getter @XmlElement(name = "ValorServicos") @NotNull @NFSeValue String serviceValue;

    private final @Getter @XmlElement(name = "ValorDeducoes") @NFSeValue String deductionValue;

    private final @Getter @XmlElement(name = "ValorPis") @NFSeValue String pisValue;

    private final @Getter @XmlElement(name = "ValorCofins") @NFSeValue String cofinsValue;

    private final @Getter @XmlElement(name = "ValorInss") @NFSeValue String inssValue;

    private final @Getter @XmlElement(name = "ValorIr") @NFSeValue String irValue;

    private final @Getter @XmlElement(name = "ValorCsll") @NFSeValue String csllValue;

    private final @Getter @XmlElement(name = "OutrasRetencoes") @NFSeValue String otherRetentionsValue;

    private final @Getter @XmlElement(name = "ValorIss") @NFSeValue String issValue;

    private final @Getter @XmlElement(name = "Aliquota") @NFSeAliquot String issAliquot;

    private final @Getter @XmlElement(name = "DescontoIncondicionado") @NFSeValue String discountUnconditionedValue;

    private final @Getter @XmlElement(name = "DescontoCondicionado") @NFSeValue String discountConditionedValue;

}

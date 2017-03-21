
package eprecise.efiscal4j.nfse.statements.services;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.types.NFSeUnitaryValue;
import eprecise.efiscal4j.nfse.types.NFSeValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceItem {

    private final @XmlElement(name = "ItemListaServico") @NotNull @Size(min = 1, max = 6) String itemServiceList;

    private final @XmlElement(name = "CodigoCnae") @NotNull @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cnaeCode;

    private final @XmlElement(name = "Descricao") @NotNull @Size(min = 1, max = 20) String description;

    private final @XmlElement(name = "Tributavel") @NotNull ServiceItemTaxable taxable;

    private final @XmlElement(name = "Quantidade") @NotNull @NFSeValue String quantity;

    private final @XmlElement(name = "ValorUnitario") @NotNull @NFSeUnitaryValue String unitaryValue;

    private final @XmlElement(name = "ValorDesconto") @NFSeValue String discountValue;

    private final @XmlElement(name = "ValorLiquido") @NotNull @NFSeValue String netValue;

}


package eprecise.efiscal4j.nfse.tc.govbr.v203.lot;

import java.io.Serializable;
import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.GovbrStatementProvisionService;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.govbr.types.GovbrVersion;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrLotRps implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter @Builder.Default final @XmlAttribute(name = "versao") GovbrVersion version = GovbrVersion.VERSION_2_03;

    private @Getter final @NotNull @XmlElement(name = "NumeroLote") @NFSeNonNegativeInteger @Size(min = 1, max = 15) String lotNumber;

    private @Getter final @NotNull @XmlElement(name = "CpfCnpj") CommonsNFSeCnp cnp;

    private @Getter final @XmlElement(name = "InscricaoMunicipal") @Size(min = 1, max = 15) String municipalRegistration;

    private @Getter final @NotNull @XmlElement(name = "QuantidadeRps") Integer quantity;

    private @Getter final @NotNull @XmlElementWrapper(name = "ListaRps") @XmlElement(
            name = "Rps") Collection<GovbrStatementProvisionService> statementProvisionServices;

}

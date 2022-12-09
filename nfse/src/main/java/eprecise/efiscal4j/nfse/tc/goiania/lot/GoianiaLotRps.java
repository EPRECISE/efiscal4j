
package eprecise.efiscal4j.nfse.tc.goiania.lot;

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
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.GoianiaStatementProvisionService;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaLotRps implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlAttribute(name = "versao") String version = "2.03";

    private @Getter final @NotNull @XmlElement(name = "NumeroLote") @NFSeNonNegativeInteger @Size(min = 1, max = 15) String lotNumber;

    private @Getter final @NotNull @XmlElement(name = "CpfCnpj") CommonsNFSeCnp cnp;

    private @Getter final @XmlElement(name = "InscricaoMunicipal") @Size(min = 1, max = 15) String municipalRegistration;

    private @Getter final @NotNull @XmlElement(name = "QuantidadeRps") Integer quantity;

    private @Getter final @NotNull @XmlElementWrapper(name = "ListaRps") @XmlElement(
            name = "Rps") Collection<GoianiaStatementProvisionService> statementProvisionServices;

}

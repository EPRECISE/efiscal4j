
package eprecise.efiscal4j.nfse.tc.curitiba.lot;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.CuritibaRps;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaLotRps implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final @XmlAttribute(name = "id") String id = UUID.randomUUID().toString().replaceAll("-", "");

    private final @NotNull @XmlElement(name = "NumeroLote") @NFSeNonNegativeInteger @Size(min = 1, max = 15) String lotNumber;

    private final @NotNull @XmlElement(name = "Cnpj") String cnpj;

    private final @XmlElement(name = "InscricaoMunicipal") @Size(min = 1, max = 15) String municipalRegistration;

    private final @NotNull @XmlElement(name = "QuantidadeRps") Integer quantity;

    private final @NotNull @XmlElementWrapper(name = "ListaRps") @XmlElement(
            name = "Rps") Collection<CuritibaRps> rpsList;

}

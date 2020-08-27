
package eprecise.efiscal4j.nfse.tc.curitiba;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@XmlRootElement(name = "IdentificacaoNfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaNFSeIdentifier implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @NotNull @XmlElement(name = "Numero") @NFSeNonNegativeInteger @Size(max = 15) String number;

    private final @NotNull @XmlElement(name = "Cnpj") @CNPJ(formatted = false) String cnpj;

    private final @XmlElement(name = "InscricaoMunicipal") String municipalRegistration;

    private final @NotNull @XmlElement(name = "CodigoMunicipio") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityCode;

}

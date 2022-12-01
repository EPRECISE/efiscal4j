
package eprecise.efiscal4j.nfse.tc.goiania.lot.statements.rps;

import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaRps implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "IdentificacaoRps") CommonsRpsIdentifier identifier;

    private @Getter final @NotNull @XmlElement(name = "DataEmissao") @NFSeDateTimeUTC String emissionDate;

    private @Getter final @NotNull @XmlElement(name = "Status") CommonsRpsStatus status;

    private @Getter final @XmlElement(name = "RpsSubstituido") CommonsRpsIdentifier substitutedIdentifier;

}

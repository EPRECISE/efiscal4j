
package eprecise.efiscal4j.nfse.tc.govbr.v203.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeReplacement implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "SubstituicaoNfse") Info info;

    @Builder
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Info {

        private @Getter final @NotNull @XmlElement(name = "NfseSubstituidora") @NFSeNonNegativeInteger @Size(
                min = 1, max = 15) String nfseNumber;
    }

}

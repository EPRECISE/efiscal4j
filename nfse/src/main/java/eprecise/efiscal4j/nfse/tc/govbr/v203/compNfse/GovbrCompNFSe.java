
package eprecise.efiscal4j.nfse.tc.govbr.v203.compNfse;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;
import eprecise.efiscal4j.nfse.tc.govbr.v203.GovbrNFSe;
import eprecise.efiscal4j.nfse.tc.govbr.v203.cancel.GovbrNFSeCancel;
import eprecise.efiscal4j.nfse.tc.govbr.v203.cancel.GovbrNFSeReplacement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrCompNFSe implements CompNFSe {

    private static final long serialVersionUID = 1L;

    private final @NotNull @XmlElement(name = "Nfse") GovbrNFSe nfse;

    private @Getter final @XmlElement(name = "NfseCancelamento") GovbrNFSeCancel cancelNFSe;

    private @Getter final @XmlElement(name = "NfseSubstituicao") GovbrNFSeReplacement nfseReplacement;

    @Override
    public GovbrNFSe getProcessedNfse() {
        return this.nfse;
    }

}

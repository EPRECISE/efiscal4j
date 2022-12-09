
package eprecise.efiscal4j.nfse.tc.goiania.compNfse;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;
import eprecise.efiscal4j.nfse.tc.goiania.GoianiaNFSe;
import eprecise.efiscal4j.nfse.tc.goiania.cancel.GoianiaNFSeCancel;
import eprecise.efiscal4j.nfse.tc.goiania.cancel.GoianiaNFSeReplacement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CompNfse")
public class GoianiaCompNFSe implements CompNFSe {

    private static final long serialVersionUID = 1L;

    private final @NotNull @XmlElement(name = "Nfse") GoianiaNFSe nfse;

    private @Getter final @XmlElement(name = "NfseCancelamento") GoianiaNFSeCancel cancelNFSe;

    private @Getter final @XmlElement(name = "NfseSubstituicao") GoianiaNFSeReplacement nfseReplacement;

    @Override
    public GoianiaNFSe getProcessedNfse() {
        return this.nfse;
    }

}

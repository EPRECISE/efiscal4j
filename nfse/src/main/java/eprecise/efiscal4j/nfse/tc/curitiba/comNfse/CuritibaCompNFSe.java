
package eprecise.efiscal4j.nfse.tc.curitiba.comNfse;

import java.util.Collection;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;
import eprecise.efiscal4j.nfse.tc.curitiba.CuritibaNFSe;


@XmlRootElement(name = "CompNfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaCompNFSe implements CompNFSe {

    private static final long serialVersionUID = 1L;

    private final @XmlElementWrapper(name = "tcCompNfse") @XmlElement(name = "Nfse") Collection<CuritibaNFSe> nfse;

    public static class Builder {

        private Collection<CuritibaNFSe> nfse;

        /**
         *
         * @param nfse
         * @return
         */
        public Builder withNFSe(final Collection<CuritibaNFSe> nfse) {
            this.nfse = nfse;
            return this;
        }

        public CuritibaCompNFSe build() {
            final CuritibaCompNFSe entity = new CuritibaCompNFSe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CuritibaCompNFSe() {
        nfse = null;
    }

    public CuritibaCompNFSe(final Builder builder) {
        nfse = builder.nfse;
    }

    @Override
    public CuritibaNFSe getProcessedNfse() {
        return Optional.ofNullable(nfse).map(nfse -> nfse.stream().findFirst().orElse(null)).orElse(null);
    }

}

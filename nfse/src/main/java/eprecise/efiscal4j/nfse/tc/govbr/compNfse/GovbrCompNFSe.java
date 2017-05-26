
package eprecise.efiscal4j.nfse.tc.govbr.compNfse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;
import eprecise.efiscal4j.nfse.domain.comp.ProcessedNFSe;


@XmlRootElement(name = "CompNfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrCompNFSe implements CompNFSe {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "Nfse") ProcessedNFSe nfse;

    public static class Builder {

        private GovbrNFSe nfse;

        /**
         *
         * @param nfse
         * @return
         */
        public Builder withNFSe(final GovbrNFSe nfse) {
            this.nfse = nfse;
            return this;
        }

        public GovbrCompNFSe build() {
            final GovbrCompNFSe entity = new GovbrCompNFSe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrCompNFSe() {
        nfse = null;
    }

    public GovbrCompNFSe(final Builder builder) {
        nfse = builder.nfse;
    }

    @Override
    public GovbrNFSe getProcessedNfse() {
        return (GovbrNFSe) nfse;
    }

}

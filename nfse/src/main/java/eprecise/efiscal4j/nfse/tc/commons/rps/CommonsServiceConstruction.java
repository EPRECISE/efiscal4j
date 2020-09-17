
package eprecise.efiscal4j.nfse.tc.commons.rps;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class CommonsServiceConstruction implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "CodigoObra") @Size(min = 1, max = 15) String workCode;

    private final @XmlElement(name = "Art") @NotNull @Size(min = 1, max = 15) String art;

    public static class Builder {

        private String workCode;

        private String art;

        /**
         * @param workCode
         * @return
         */
        public Builder withWorkCode(final String workCode) {
            this.workCode = workCode;
            return this;
        }

        /**
         * @param art
         * @return
         */
        public Builder withArt(final String art) {
            this.art = art;
            return this;
        }

        public CommonsServiceConstruction build() throws Exception {
            final CommonsServiceConstruction entity = new CommonsServiceConstruction(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CommonsServiceConstruction() {
        workCode = null;
        art = null;
    }

    public CommonsServiceConstruction(final Builder builder) {
        workCode = builder.workCode;
        art = builder.art;
    }

    public String getWorkCode() {
        return workCode;
    }

    public String getArt() {
        return art;
    }
}


package eprecise.efiscal4j.nfse.tc.elotech.statements;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceConstruction implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "CodigoObra") @Size(min = 1, max = 15) String workCode;

    private final @XmlElement(name = "Art") @NotNull @Size(min = 1, max = 15) String art;

    private final @XmlElement(name = "Incorporacao") ConstructionIncorporation incorporation;

    public static class Builder {

        private String workCode;

        private String art;

        private ConstructionIncorporation incorporation;

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

        /**
         * @param incorporation
         * @return
         */
        public Builder withIncorporation(final ConstructionIncorporation incorporation) {
            this.incorporation = incorporation;
            return this;
        }

        public ServiceConstruction build() throws Exception {
            final ServiceConstruction entity = new ServiceConstruction(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ServiceConstruction() {
        workCode = null;
        art = null;
        incorporation = null;
    }

    public ServiceConstruction(final Builder builder) {
        workCode = builder.workCode;
        art = builder.art;
        incorporation = builder.incorporation;
    }

    public String getWorkCode() {
        return workCode;
    }

    public String getArt() {
        return art;
    }

    public ConstructionIncorporation getIncorporation() {
        return incorporation;
    }

}

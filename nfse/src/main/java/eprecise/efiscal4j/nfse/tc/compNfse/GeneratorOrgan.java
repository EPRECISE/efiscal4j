
package eprecise.efiscal4j.nfse.tc.compNfse;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.person.address.NFSeUF;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeNonNegativeInteger;


@XmlAccessorType(XmlAccessType.FIELD)
public class GeneratorOrgan implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @NotNull @XmlElement(name = "CodigoMunicipio") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityCode;

    private final @NotNull @XmlElement(name = "Uf") NFSeUF uf;

    public static class Builder {

        private String cityCode;

        private NFSeUF uf;

        /**
         * @param cityCode
         * @return
         */
        public Builder withCityCode(final String cityCode) {
            this.cityCode = cityCode;
            return this;
        }

        /**
         * @param uf
         * @return
         */
        public Builder withUf(final NFSeUF uf) {
            this.uf = uf;
            return this;
        }

        public GeneratorOrgan build() throws Exception {
            final GeneratorOrgan entity = new GeneratorOrgan(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GeneratorOrgan() {
        cityCode = null;
        uf = null;
    }

    public GeneratorOrgan(final Builder builder) {
        cityCode = builder.cityCode;
        uf = builder.uf;
    }

    public String getCityCode() {
        return cityCode;
    }

}

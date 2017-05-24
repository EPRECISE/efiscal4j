
package eprecise.efiscal4j.nfse.tc.commons.compNfse;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeUF;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;


@XmlAccessorType(XmlAccessType.FIELD)
public class CommonsGeneratorOrgan implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @NotNull @XmlElement(name = "CodigoMunicipio") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityCode;

    private final @NotNull @XmlElement(name = "Uf") CommonsNFSeUF uf;

    public static class Builder {

        private String cityCode;

        private CommonsNFSeUF uf;

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
        public Builder withUf(final CommonsNFSeUF uf) {
            this.uf = uf;
            return this;
        }

        public CommonsGeneratorOrgan build() throws Exception {
            final CommonsGeneratorOrgan entity = new CommonsGeneratorOrgan(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CommonsGeneratorOrgan() {
        cityCode = null;
        uf = null;
    }

    public CommonsGeneratorOrgan(final Builder builder) {
        cityCode = builder.cityCode;
        uf = builder.uf;
    }

    public String getCityCode() {
        return cityCode;
    }

}

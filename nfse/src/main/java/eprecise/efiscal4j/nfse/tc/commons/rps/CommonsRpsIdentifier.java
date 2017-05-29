
package eprecise.efiscal4j.nfse.tc.commons.rps;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.comp.rps.RpsIdentifier;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsType;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;


@XmlAccessorType(XmlAccessType.FIELD)
public class CommonsRpsIdentifier implements RpsIdentifier {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "Numero") @NotNull @NFSeNonNegativeInteger @Size(max = 15) String number;

    private final @XmlElement(name = "Serie") @NotNull @Size(min = 1, max = 5) String serie;

    private final @XmlElement(name = "Tipo") @NotNull CommonsRpsType type;

    public static class Builder {

        private String number;

        private String serie;

        private CommonsRpsType type;

        /**
         * @param number
         * @return
         */
        public Builder withNumber(final String number) {
            this.number = number;
            return this;
        }

        /**
         * @param serie
         * @return
         */
        public Builder withSerie(final String serie) {
            this.serie = serie;
            return this;
        }

        /**
         * @param type
         * @return
         */
        public Builder withType(final CommonsRpsType type) {
            this.type = type;
            return this;
        }

        public CommonsRpsIdentifier build() {
            final CommonsRpsIdentifier entity = new CommonsRpsIdentifier(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CommonsRpsIdentifier() {
        number = null;
        serie = null;
        type = null;
    }

    public CommonsRpsIdentifier(final Builder builder) {
        number = builder.number;
        serie = builder.serie;
        type = builder.type;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getSerie() {
        return serie;
    }

    @Override
    public CommonsRpsType getType() {
        return type;
    }

}

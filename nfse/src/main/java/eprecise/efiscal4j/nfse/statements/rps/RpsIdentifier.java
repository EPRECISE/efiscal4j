
package eprecise.efiscal4j.nfse.statements.rps;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.types.NFSeNonNegativeInteger;


@XmlAccessorType(XmlAccessType.FIELD)
public class RpsIdentifier {

    private final @XmlElement(name = "Numero") @NotNull @NFSeNonNegativeInteger @Size(max = 15) String number;

    private final @XmlElement(name = "Serie") @NotNull @Size(min = 1, max = 5) String serie;

    private final @XmlElement(name = "Tipo") @NotNull RpsType type;

    public static class Builder {

        private String number;

        private String serie;

        private RpsType type;

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
        public Builder withType(final RpsType type) {
            this.type = type;
            return this;
        }

        public RpsIdentifier build() throws Exception {
            final RpsIdentifier entity = new RpsIdentifier(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public RpsIdentifier(final Builder builder) {
        this.number = builder.number;
        this.serie = builder.serie;
        this.type = builder.type;
    }

    public String getNumber() {
        return number;
    }

    public String getSerie() {
        return serie;
    }

    public RpsType getType() {
        return type;
    }

}


package eprecise.efiscal4j.nfse.domain.serie;

import java.io.Serializable;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeSerie implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String serie;

    private final String lotNumber;

    private final String rpsNumber;

    public static class Builder {

        private String serie;

        private String lotNumber;

        private String rpsNumber;

        public Builder withSerie(final String serie) {
            this.serie = serie;
            return this;
        }

        public Builder withLotNumber(final String lotNumber) {
            this.lotNumber = lotNumber;
            return this;
        }

        public Builder withRpsNumber(final String rpsNumber) {
            this.rpsNumber = rpsNumber;
            return this;
        }

        public NFSeSerie build() {
            final NFSeSerie entity = new NFSeSerie(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSeSerie() {
        serie = null;
        lotNumber = null;
        rpsNumber = null;
    }

    public NFSeSerie(final Builder builder) {
        super();
        serie = builder.serie;
        lotNumber = builder.lotNumber;
        rpsNumber = builder.rpsNumber;
    }

    public String getSerie() {
        return serie;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public String getRpsNumber() {
        return rpsNumber;
    }

}

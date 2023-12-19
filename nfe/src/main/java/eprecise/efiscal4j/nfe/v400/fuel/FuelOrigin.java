package eprecise.efiscal4j.nfe.v400.fuel;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;


/**
 * Origem do Combustível
 *
 * @author Pedro H. Rodrigues
 */

@Getter
@NoArgsConstructor(force = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class FuelOrigin implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "indImport")
    @NotNull
    private final String indImport;

    @XmlElement(name = "cUFOrig")
    @NotNull
    private final String cUFOrig;

    @XmlElement(name = "vCIDE")
    @NotNull
    private final String pOrig;

    public static class Builder {

        private String indImport;

        private String cUFOrig;

        private String pOrig;

        /**
         * BC do CIDE (Quantidade comercializada)
         */
        public Builder withIndImport(final String indImport) {
            this.indImport = indImport;
            return this;
        }

        /**
         * BC do CIDE (Quantidade comercializada)
         */
        public Builder withCUFOrig(final String cUFOrig) {
            this.cUFOrig = cUFOrig;
            return this;
        }

        /**
         * BC do CIDE (Quantidade comercializada)
         */
        public Builder withPOrig(final String pOrig) {
            this.pOrig = pOrig;
            return this;
        }

        public FuelOrigin build() {
            return new FuelOrigin(this);
        }
    }

    public FuelOrigin(final Builder builder){
        this.indImport = builder.indImport;
        this.cUFOrig = builder.cUFOrig;
        this.pOrig = builder.pOrig;
    }

}

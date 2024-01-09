package eprecise.efiscal4j.nfe.v400.fuel;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.v400.tax.icms.ProductOrigin;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal01100;
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
    private final ProductOrigin indImport;

    @XmlElement(name = "cUFOrig")
    @NotNull
    private final UF cUFOrig;

    @XmlElement(name = "pOrig")
    @NotNull
    @NFeDecimal01100
    private final String pOrig;

    public static class Builder {

        private ProductOrigin indImport;

        private UF cUFOrig;

        private String pOrig;

        /**
         * Indicador de importação.
         */
        public Builder withIndImport(final ProductOrigin indImport) {
            this.indImport = indImport;
            return this;
        }

        /**
         * Código da UF de origem do produtor ou importador.
         */
        public Builder withCUFOrig(final UF cUFOrig) {
            this.cUFOrig = cUFOrig;
            return this;
        }

        /**
         * Percentual originário para a UF.
         */
        public Builder withPOrig(final String pOrig) {
            this.pOrig = pOrig;
            return this;
        }

        public FuelOrigin build() {
            return new FuelOrigin(this);
        }
    }

    public FuelOrigin(final Builder builder) {
        this.indImport = builder.indImport;
        this.cUFOrig = builder.cUFOrig;
        this.pOrig = builder.pOrig;
    }

}

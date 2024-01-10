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
    private final ProductOrigin importIndicator;

    @XmlElement(name = "cUFOrig")
    @NotNull
    private final UF originUf;

    @XmlElement(name = "pOrig")
    @NotNull
    @NFeDecimal01100
    private final String percentageOriginatingFromUf;

    public static class Builder {

        private ProductOrigin importIndicator;

        private UF originUf;

        private String percentageOriginatingFromUf;

        /**
         * Indicador de importação.
         */
        public Builder withImportIndicator(final ProductOrigin importIndicator) {
            this.importIndicator = importIndicator;
            return this;
        }

        /**
         * Código da UF de origem do produtor ou importador.
         */
        public Builder withOriginUf(final UF originUf) {
            this.originUf = originUf;
            return this;
        }

        /**
         * Percentual originário para a UF.
         */
        public Builder withPercentageOriginatingFromUf(final String percentageOriginatingFromUf) {
            this.percentageOriginatingFromUf = percentageOriginatingFromUf;
            return this;
        }

        public FuelOrigin build() {
            return new FuelOrigin(this);
        }
    }

    public FuelOrigin(final Builder builder) {
        this.importIndicator = builder.importIndicator;
        this.originUf = builder.originUf;
        this.percentageOriginatingFromUf = builder.percentageOriginatingFromUf;
    }

}

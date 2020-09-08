
package eprecise.efiscal4j.nfe.v400.export;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Informações de exportação
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeExport implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "UFSaidaPais") @NotNull String ufExitCountry;

    private final @XmlElement(name = "xLocExporta") @NotNull @Size(min = 1, max = 60) @NFeString String exportLocation;

    private final @XmlElement(name = "xLocDespacho") @Size(min = 1, max = 60) @NFeString String dispatchLocation;

    public static class Builder {

        private UF ufExitCountry;

        private String exportLocation;

        private String dispatchLocation;

        /**
         * Sigla da UF de Embarque ou de transposição de fronteira
         * 
         * @param ufExitCountry
         * @see UF
         * @return
         */
        public Builder withUfExitCountry(final UF ufExitCountry) {
            this.ufExitCountry = ufExitCountry;
            return this;
        }

        /**
         * Local de Embarque ou de transposição de fronteira
         * 
         * @param exportLocation
         * @return
         */
        public Builder withExportLocation(final String exportLocation) {
            this.exportLocation = exportLocation;
            return this;
        }

        /**
         * Descrição do local de despacho
         * 
         * @param dispatchLocation
         * @return
         */
        public Builder withDispatchLocation(final String dispatchLocation) {
            this.dispatchLocation = dispatchLocation;
            return this;
        }
        
        public NFeExport build() {
            final NFeExport entity = new NFeExport(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeExport() {
        this.ufExitCountry = null;
        this.exportLocation = null;
        this.dispatchLocation = null;
    }

    public NFeExport(final Builder builder) {
        this.ufExitCountry = Optional.ofNullable(builder.ufExitCountry).map(UF::getAcronym).orElse(null);
        this.exportLocation = builder.exportLocation;
        this.dispatchLocation = builder.dispatchLocation;
    }

    public UF getUfExitCountry() {
        return UF.findByAcronym(ufExitCountry);
    }

    public String getExportLocation() {
        return exportLocation;
    }

    public String getDispatchLocation() {
        return dispatchLocation;
    }

}

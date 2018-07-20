
package eprecise.efiscal4j.nfe.v310.transport;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1203;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Dados dos volumes transportados
 * 
 * @author Felipe Bueno
 * 
 */
public class TransportedVolume implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "qVol") @Pattern(regexp = "[0-9]{1,15}") final String volumeQuantity;

    private @XmlElement(name = "esp") @Size(min = 1, max = 60) @NFeString final String volumeSpecies;

    private @XmlElement(name = "marca") @Size(min = 1, max = 60) @NFeString final String volumeTrademark;

    private @XmlElement(name = "nVol") @Size(min = 1, max = 60) @NFeString final String volumeNumbering;

    private @XmlElement(name = "pesoL") @NFeDecimal1203 final String netWeight;

    private @XmlElement(name = "pesoB") @NFeDecimal1203 final String grossWeight;

    private @XmlElement(name = "lacres") @Size(max = 5000) final List<VolumeSeal> seals;

    public static class Builder {

        private String volumeQuantity;

        private String volumeSpecies;

        private String volumeTrademark;

        private String volumeNumbering;

        private String netWeight;

        private String grossWeight;

        private List<VolumeSeal> seals;

        /**
         * Quantidade de volumes transportados
         * 
         * @param volumeQuantity
         * @return
         */
        public Builder withVolumeQuantity(final String volumeQuantity) {
            this.volumeQuantity = volumeQuantity;
            return this;
        }

        /**
         * Espécie dos volumes transportados
         * 
         * @param volumeSpecies
         * @return
         */
        public Builder withVolumeSpecies(final String volumeSpecies) {
            this.volumeSpecies = volumeSpecies;
            return this;
        }

        /**
         * Marca dos volumes transportados
         * 
         * @param volumeTrademark
         * @return
         */
        public Builder withVolumeTrademark(final String volumeTrademark) {
            this.volumeTrademark = volumeTrademark;
            return this;
        }

        /**
         * Numeração dos volumes transportados
         * 
         * @param volumeNumbering
         * @return
         */
        public Builder withVolumeNumbering(final String volumeNumbering) {
            this.volumeNumbering = volumeNumbering;
            return this;
        }

        /**
         * Peso líquido (em kg)
         * 
         * @param netWeight
         * @return
         */
        public Builder withNetWeight(final String netWeight) {
            this.netWeight = netWeight;
            return this;
        }

        /**
         * Peso bruto (em kg)
         * 
         * @param grossWeight
         * @return
         */
        public Builder withGrossWeight(final String grossWeight) {
            this.grossWeight = grossWeight;
            return this;
        }

        /**
         * @see VolumeSeal
         * 
         * @param seals
         * @return
         */
        public Builder withSeals(final List<VolumeSeal> seals) {
            this.seals = seals;
            return this;
        }

        public TransportedVolume build() {
            final TransportedVolume entity = new TransportedVolume(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public TransportedVolume() {
        this.volumeQuantity = null;
        this.volumeSpecies = null;
        this.volumeTrademark = null;
        this.volumeNumbering = null;
        this.netWeight = null;
        this.grossWeight = null;
        this.seals = null;
    }

    public TransportedVolume(final Builder builder) {
        this.volumeQuantity = builder.volumeQuantity;
        this.volumeSpecies = builder.volumeSpecies;
        this.volumeTrademark = builder.volumeTrademark;
        this.volumeNumbering = builder.volumeNumbering;
        this.netWeight = builder.netWeight;
        this.grossWeight = builder.grossWeight;
        this.seals = builder.seals;
    }

    public String getVolumeQuantity() {
        return this.volumeQuantity;
    }

    public String getVolumeSpecies() {
        return this.volumeSpecies;
    }

    public String getVolumeTrademark() {
        return this.volumeTrademark;
    }

    public String getVolumeNumbering() {
        return this.volumeNumbering;
    }

    public String getNetWeight() {
        return this.netWeight;
    }

    public String getGrossWeight() {
        return this.grossWeight;
    }

    public List<VolumeSeal> getSeals() {
        return this.seals;
    }

}

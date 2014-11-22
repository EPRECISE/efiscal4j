
package eprecise.efiscal4j.nfe.transport;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Dados dos volumes transportados
 * 
 * @author Felipe Bueno
 * 
 */
public class TransportedVolume implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "qVol") @Pattern(regexp = "[0-9]{1,15}") String volumeQuantity;

	private @XmlElement(name = "esp") @Size(min = 1, max = 60) @Pattern(regexp = "[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}") String volumeSpecies;

	private @XmlElement(name = "marca") @Size(min = 1, max = 60) @Pattern(regexp = "[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}") String volumeTrademark;

	private @XmlElement(name = "nVol") @Size(min = 1, max = 60) @Pattern(regexp = "[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}") String volumeNumbering;

	private @XmlElement(name = "pesoL") @Pattern(regexp = "0|0\\.[0-9]{3}|[1-9]{1}[0-9]{0,11}(\\.[0-9]{3})?") String netWeight;

	private @XmlElement(name = "pesoB") @Pattern(regexp = "0|0\\.[0-9]{3}|[1-9]{1}[0-9]{0,11}(\\.[0-9]{3})?") String grossWeight;

	private @XmlElement(name = "lacres") @Size(max = 5000) List<VolumeSeal> seals;

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
		public Builder withVolumeQuantity(String volumeQuantity) {
			this.volumeQuantity = volumeQuantity;
			return this;
		}

		/**
		 * Espécie dos volumes transportados
		 * 
		 * @param volumeSpecies
		 * @return
		 */
		public Builder withVolumeSpecies(String volumeSpecies) {
			this.volumeSpecies = volumeSpecies;
			return this;
		}

		/**
		 * Marca dos volumes transportados
		 * 
		 * @param volumeTrademark
		 * @return
		 */
		public Builder withVolumeTrademark(String volumeTrademark) {
			this.volumeTrademark = volumeTrademark;
			return this;
		}

		/**
		 * Numeração dos volumes transportados
		 * 
		 * @param volumeNumbering
		 * @return
		 */
		public Builder withVolumeNumbering(String volumeNumbering) {
			this.volumeNumbering = volumeNumbering;
			return this;
		}

		/**
		 * Peso líquido (em kg)
		 * 
		 * @param netWeight
		 * @return
		 */
		public Builder withNetWeight(String netWeight) {
			this.netWeight = netWeight;
			return this;
		}

		/**
		 * Peso bruto (em kg)
		 * 
		 * @param grossWeight
		 * @return
		 */
		public Builder withGrossWeight(String grossWeight) {
			this.grossWeight = grossWeight;
			return this;
		}

		/**
		 * @see VolumeSeal
		 * 
		 * @param seals
		 * @return
		 */
		public Builder withSeals(List<VolumeSeal> seals) {
			this.seals = seals;
			return this;
		}

		public TransportedVolume build() {
			TransportedVolume entity = new TransportedVolume(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public TransportedVolume() {
	}

	public TransportedVolume(Builder builder) {
		this.volumeQuantity = builder.volumeQuantity;
		this.volumeSpecies = builder.volumeSpecies;
		this.volumeTrademark = builder.volumeTrademark;
		this.volumeNumbering = builder.volumeNumbering;
		this.netWeight = builder.netWeight;
		this.grossWeight = builder.grossWeight;
		this.seals = builder.seals;
	}
}

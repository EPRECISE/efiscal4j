
package eprecise.efiscal4j.nfe.transport;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Lacre dos volumes transportados
 * 
 * @author Felipe Bueno
 * 
 */
public class VolumeSeal implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "nLacre") @Size(min = 1, max = 60) @Pattern(regexp = "[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}") String sealNumber;

	public static class Builder {

		private String sealNumber;

		/**
		 * Número dos Lacres
		 * 
		 * @param sealNumber
		 * @return
		 */
		public Builder withSealNumber(String sealNumber) {
			this.sealNumber = sealNumber;
			return this;
		}

		public VolumeSeal build() {
			VolumeSeal entity = new VolumeSeal(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public VolumeSeal() {
	}

	public VolumeSeal(Builder builder) {
		this.sealNumber = builder.sealNumber;
	}

}


package eprecise.efiscal4j.nfe.v310.transport;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Lacre dos volumes transportados
 * 
 * @author Felipe Bueno
 * 
 */
public class VolumeSeal implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "nLacre") @Size(min = 1, max = 60) @NFeString String sealNumber;

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

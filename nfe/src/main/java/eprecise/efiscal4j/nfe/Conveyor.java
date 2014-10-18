
package eprecise.efiscal4j.nfe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Dados do transportador
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Conveyor {

	public static class Builder {

		public Conveyor build() {
			Conveyor entity = new Conveyor(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public Conveyor() {

	}

	public Conveyor(Builder builder) {

	}
}

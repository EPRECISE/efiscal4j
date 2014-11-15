
package eprecise.efiscal4j.nfe.transport;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Dados dos transportes da NF-e
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeTransport implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "modFrete") @NotNull final ShippingModality shippingModality;

	private @XmlElement(name = "transporta") Conveyor conveyor;

	public static class Builder {

		private ShippingModality shippingModality;

		private Conveyor conveyor;

		/**
		 * @see ShippingModality
		 * @param shippingModality
		 * @return
		 */
		public Builder withShippingModality(ShippingModality shippingModality) {
			this.shippingModality = shippingModality;
			return this;
		}

		/**
		 * @see Conveyor
		 * @param conveyor
		 * @return
		 */
		public Builder withConveyor(Conveyor conveyor) {
			this.conveyor = conveyor;
			return this;
		}

		public NFeTransport build() {
			NFeTransport entity = new NFeTransport(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public NFeTransport() {
		this.shippingModality = null;
	}

	public NFeTransport(Builder builder) {
		this.shippingModality = builder.shippingModality;
		this.conveyor = builder.conveyor;
	}
}

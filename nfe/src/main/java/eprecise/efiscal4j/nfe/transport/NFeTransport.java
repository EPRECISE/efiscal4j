
package eprecise.efiscal4j.nfe.transport;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


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

	private @XmlElement(name = "retTransp") TransportICMSRetention transportICMSRetention;

	private @XmlElement(name = "vol") @Size(max = 5000) List<TransportedVolume> transportedVolume;

	public static class Builder {

		private ShippingModality shippingModality;

		private Conveyor conveyor;

		private TransportICMSRetention transportICMSRetention;

		private List<TransportedVolume> transportedVolume;

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

		/**
		 * @see TransportICMSRetention
		 * @param transportICMSRetention
		 * @return
		 */
		public Builder withtransportICMSRetention(TransportICMSRetention transportICMSRetention) {
			this.transportICMSRetention = transportICMSRetention;
			return this;
		}

		/**
		 * @see TransportedVolume
		 * @param transportedVolume
		 * @return
		 */
		public Builder withTransportedVolume(List<TransportedVolume> transportedVolume) {
			this.transportedVolume = transportedVolume;
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
		this.transportICMSRetention = builder.transportICMSRetention;
		this.transportedVolume = builder.transportedVolume;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ShippingModality getShippingModality() {
		return this.shippingModality;
	}

	public Conveyor getConveyor() {
		return this.conveyor;
	}

	public TransportICMSRetention getTransportICMSRetention() {
		return this.transportICMSRetention;
	}

	public List<TransportedVolume> getTransportedVolume() {
		return this.transportedVolume;
	}

}

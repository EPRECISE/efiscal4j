
package eprecise.efiscal4j.nfe;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "ide") @NotNull final NFeIdentification nFeIdentification;

	private @XmlElement(name = "emit") @NotNull final Emitter emitter;

	private @XmlElement(name = "dest") @NotNull final Receiver receiver;

	private @XmlElement(name = "det") @NotNull final List<NFeDetail> nFeDetails;

	private @XmlElement(name = "total") @NotNull final NFeTotal nFeTotal;

	private @XmlElement(name = "transp") @NotNull final NFeTransport nFeTransport;

	public static class Builder {

		private NFeIdentification nFeIdentification;

		private Emitter emitter;

		private Receiver receiver;

		private List<NFeDetail> nFeDetails;

		private NFeTotal nFeTotal;

		private NFeTransport nFeTransport;

		/**
		 * @see NFeIdentification
		 * @param nFeIdentification
		 * @return
		 */
		public Builder withNFeIdentification(NFeIdentification nFeIdentification) {
			this.nFeIdentification = nFeIdentification;
			return this;
		}

		/**
		 * @see Emitter
		 * @param emitter
		 * @return
		 */
		public Builder withEmitter(Emitter emitter) {
			this.emitter = emitter;
			return this;
		}

		/**
		 * @see Receiver
		 * @param receiver
		 * @return
		 */
		public Builder withReceiver(Receiver receiver) {
			this.receiver = receiver;
			return this;
		}

		/**
		 * List of NFeDetail
		 * 
		 * @see NFeDetail
		 * @param nFeDetails
		 * @return
		 */
		public Builder withNFeDetail(List<NFeDetail> nFeDetails) {
			this.nFeDetails = nFeDetails;
			return this;
		}

		/**
		 * @see NFeTotal
		 * @param nFeTotal
		 * @return
		 */
		public Builder withNFeTotal(NFeTotal nFeTotal) {
			this.nFeTotal = nFeTotal;
			return this;
		}

		/**
		 * @see NFeTransport
		 * @param nFeTransport
		 * @return
		 */
		public Builder withNFeTransport(NFeTransport nFeTransport) {
			this.nFeTransport = nFeTransport;
			return this;
		}

		public NFeInfo build() {
			NFeInfo entity = new NFeInfo(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public NFeInfo() {
		this.nFeIdentification = null;
		this.emitter = null;
		this.receiver = null;
		this.nFeDetails = null;
		this.nFeTotal = null;
		this.nFeTransport = null;
	}

	public NFeInfo(Builder builder) {
		this.nFeIdentification = builder.nFeIdentification;
		this.emitter = builder.emitter;
		this.receiver = builder.receiver;
		this.nFeDetails = builder.nFeDetails;
		this.nFeTotal = builder.nFeTotal;
		this.nFeTransport = builder.nFeTransport;
	}

}

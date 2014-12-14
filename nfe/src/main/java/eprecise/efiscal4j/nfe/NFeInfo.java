
package eprecise.efiscal4j.nfe;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfe.additionalinfo.AdditionalInfo;
import eprecise.efiscal4j.nfe.charging.NFeCharging;
import eprecise.efiscal4j.nfe.payment.NFePayment;
import eprecise.efiscal4j.nfe.total.NFeTotal;
import eprecise.efiscal4j.nfe.transport.NFeTransport;
import eprecise.efiscal4j.nfe.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.validation.NFePaymentValidation;


/**
 * Informações da Nota Fiscal eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@NFePaymentValidation
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "ide") @NotNull final NFeIdentification nFeIdentification;

	private @XmlElement(name = "emit") @NotNull final Emitter emitter;

	private @XmlElement(name = "dest") @NotNull final Receiver receiver;

	private @XmlElement(name = "det") @NotNull @Size(max = 990) final List<NFeDetail> nFeDetails;

	private @XmlElement(name = "total") @NotNull final NFeTotal nFeTotal;

	private @XmlElement(name = "transp") @NotNull final NFeTransport nFeTransport;

	private @XmlElement(name = "cobr") NFeCharging nFeCharging;

	private @XmlElement(name = "pag") @Size(min = 0, max = 100) List<NFePayment> nFePayments;

	private @XmlElement(name = "infAdic") AdditionalInfo additionalInfo;

	public static class Builder {

		private NFeIdentification nFeIdentification;

		private Emitter emitter;

		private Receiver receiver;

		private List<NFeDetail> nFeDetails;

		private NFeTotal nFeTotal;

		private NFeTransport nFeTransport;

		private NFeCharging nFeCharging;

		private List<NFePayment> nFePayments;

		private AdditionalInfo additionalInfo;

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

		/**
		 * @see NFeCharging
		 * @param nFeCharging
		 * @return
		 */
		public Builder withNFeCharging(NFeCharging nFeCharging) {
			this.nFeCharging = nFeCharging;
			return this;
		}

		/**
		 * List of NFePayments
		 * 
		 * @see NFePayment
		 * @param nFePayments
		 * @return
		 */
		public Builder withNFePayments(List<NFePayment> nFePayments) {
			this.nFePayments = nFePayments;
			return this;
		}

		/**
		 * @see AdditionalInfo
		 * @param additionalInfo
		 * @return
		 */
		public Builder withAdditionalInfo(AdditionalInfo additionalInfo) {
			this.additionalInfo = additionalInfo;
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
		this.nFeCharging = builder.nFeCharging;
		this.nFePayments = builder.nFePayments;
		this.additionalInfo = builder.additionalInfo;
	}

	public NFeIdentification getnFeIdentification() {
		return this.nFeIdentification;
	}

	public Emitter getEmitter() {
		return this.emitter;
	}

	public Receiver getReceiver() {
		return this.receiver;
	}

	public List<NFeDetail> getnFeDetails() {
		return this.nFeDetails;
	}

	public NFeTotal getnFeTotal() {
		return this.nFeTotal;
	}

	public NFeTransport getnFeTransport() {
		return this.nFeTransport;
	}

	public NFeCharging getnFeCharging() {
		return this.nFeCharging;
	}

	public List<NFePayment> getnFePayments() {
		return this.nFePayments;
	}

	public AdditionalInfo getAdditionalInfo() {
		return this.additionalInfo;
	}

}

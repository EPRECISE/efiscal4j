
package eprecise.efiscal4j.nfe.v400;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.additionalinfo.AdditionalInfo;
import eprecise.efiscal4j.nfe.v400.charging.NFeCharging;
import eprecise.efiscal4j.nfe.v400.export.NFeExport;
import eprecise.efiscal4j.nfe.v400.payment.NFePayment;
import eprecise.efiscal4j.nfe.v400.person.Emitter;
import eprecise.efiscal4j.nfe.v400.person.Receiver;
import eprecise.efiscal4j.nfe.v400.places.Place;
import eprecise.efiscal4j.nfe.v400.technicalManager.NFeTechnicalManager;
import eprecise.efiscal4j.nfe.v400.total.NFeTotal;
import eprecise.efiscal4j.nfe.v400.transport.NFeTransport;


/**
 * Informações da Nota Fiscal eletrônica
 *
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") @Pattern(regexp = "NFe[0-9]{44}") final String id;

    private @XmlAttribute(name = "versao") final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_4_00;

    private @XmlElement(name = "ide") @NotNull @Valid final NFeIdentification nFeIdentification;

    private @XmlElement(name = "emit") @NotNull final Emitter emitter;

    private @XmlElement(name = "dest") final Receiver receiver;

    private @XmlElement(name = "retirada") final Place withdrawal;

    private @XmlElement(name = "entrega") final Place delivery;

    private @XmlElement(name = "det") @Size(max = 990) @NotNull @Valid final List<NFeDetail> nFeDetails;

    private @XmlElement(name = "total") @NotNull @Valid final NFeTotal nFeTotal;

    private @XmlElement(name = "transp") @NotNull final NFeTransport nFeTransport;

    private @XmlElement(name = "cobr") final NFeCharging nFeCharging;

    private @XmlElement(name = "pag") @NotNull final NFePayment nFePayment;

    private @XmlElement(name = "infAdic") final AdditionalInfo additionalInfo;
    
    private @XmlElement(name = "exporta") final NFeExport export;
    
    private @XmlElement(name = "infRespTec") final NFeTechnicalManager technicalManager;

    public static class Builder {
        
        private String id;

        private NFeIdentification nFeIdentification;

        private Emitter emitter;

        private Receiver receiver;

        private Place withdrawal;

        private Place delivery;

        private List<NFeDetail> nFeDetails;

        private NFeTotal nFeTotal;

        private NFeTransport nFeTransport;

        private NFeCharging nFeCharging;

        private NFePayment nFePayment;

        private AdditionalInfo additionalInfo;
        
        private NFeExport export;
        
        private NFeTechnicalManager technicalManager;
        
        /**
         * ID no padrão PL_005d - 11/08/09
         * 
         * @return
         */
        public Builder withId(final String id) {
            this.id = id;
            return this;
        }

        /**
         * @see NFeIdentification
         * @param nFeIdentification
         * @return
         */
        public Builder withNFeIdentification(final NFeIdentification nFeIdentification) {
            this.nFeIdentification = nFeIdentification;
            return this;
        }

        /**
         * @see Emitter
         * @param emitter
         * @return
         */
        public Builder withEmitter(final Emitter emitter) {
            this.emitter = emitter;
            return this;
        }

        /**
         * @see Receiver
         * @param receiver
         * @return
         */
        public Builder withReceiver(final Receiver receiver) {
            this.receiver = receiver;
            return this;
        }

        /**
         * @see Place
         * @param withdrawal
         * @return
         */
        public Builder withWithdrawal(final Place withdrawal) {
            this.withdrawal = withdrawal;
            return this;
        }

        /**
         * @see Place
         * @param delivery
         * @return
         */
        public Builder withDelivery(final Place delivery) {
            this.delivery = delivery;
            return this;
        }

        /**
         * List of NFeDetail
         *
         * @see NFeDetail
         * @param nFeDetails
         * @return
         */
        public Builder withNFeDetail(final List<NFeDetail> nFeDetails) {
            this.nFeDetails = nFeDetails;
            return this;
        }

        /**
         * @see NFeTotal
         * @param nFeTotal
         * @return
         */
        public Builder withNFeTotal(final NFeTotal nFeTotal) {
            this.nFeTotal = nFeTotal;
            return this;
        }

        /**
         * @see NFeTransport
         * @param nFeTransport
         * @return
         */
        public Builder withNFeTransport(final NFeTransport nFeTransport) {
            this.nFeTransport = nFeTransport;
            return this;
        }

        /**
         * @see NFeCharging
         * @param nFeCharging
         * @return
         */
        public Builder withNFeCharging(final NFeCharging nFeCharging) {
            this.nFeCharging = nFeCharging;
            return this;
        }

        /**
         * List of NFePayment
         *
         * @see NFePayment
         * @param nFePayment
         * @return
         */
        public Builder withNFePayment(final NFePayment nFePayment) {
            this.nFePayment = nFePayment;
            return this;
        }

        /**
         * @see AdditionalInfo
         * @param additionalInfo
         * @return
         */
        public Builder withAdditionalInfo(final AdditionalInfo additionalInfo) {
            this.additionalInfo = additionalInfo;
            return this;
        }
        
        /**
         * @see NFeExport
         * @param export
         * @return
         */
        public Builder withNFeExport(final NFeExport export) {
            this.export = export;
            return this;
        }
        
        /**
         * @see NFeTechnicalManager
         * @param technicalManager
         * @return
         */
        public Builder withTechnicalManager(final NFeTechnicalManager technicalManager) {
            this.technicalManager = technicalManager;
            return this;
        }

        public NFeInfo build() throws ParseException {
            final NFeInfo entity = new NFeInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    protected NFeInfo() {
        this.id = null;
        this.nFeIdentification = null;
        this.emitter = null;
        this.receiver = null;
        this.withdrawal = null;
        this.delivery = null;
        this.nFeDetails = null;
        this.nFeTotal = null;
        this.nFeTransport = null;
        this.nFeCharging = null;
        this.nFePayment = null;
        this.additionalInfo = null;
        this.export = null;
        this.technicalManager = null;
    }

    protected NFeInfo(final Builder builder) throws ParseException {
        this.id = builder.id;
        this.nFeIdentification = builder.nFeIdentification;
        this.emitter = builder.emitter;
        this.receiver = builder.receiver;
        this.withdrawal = builder.withdrawal;
        this.delivery = builder.delivery;
        this.nFeDetails = builder.nFeDetails;
        this.nFeTotal = builder.nFeTotal;
        this.nFeTransport = builder.nFeTransport;
        this.nFeCharging = builder.nFeCharging;
        this.nFePayment = builder.nFePayment;
        this.additionalInfo = builder.additionalInfo;
        this.export = builder.export;
        this.technicalManager = builder.technicalManager;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getId() {
        return this.id;
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

    public Place getWithdrawal() {
        return withdrawal;
    }

    public Place getDelivery() {
        return delivery;
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

    public NFePayment getnFePayment() {
        return nFePayment;
    }

    public AdditionalInfo getAdditionalInfo() {
        return this.additionalInfo;
    }
    
    public NFeExport getExport() {
        return export;
    }
    
    public NFeTechnicalManager getTechnicalManager() {
        return technicalManager;
    }
}

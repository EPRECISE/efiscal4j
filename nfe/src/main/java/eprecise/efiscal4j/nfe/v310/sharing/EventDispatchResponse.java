
package eprecise.efiscal4j.nfe.v310.sharing;

import java.io.Serializable;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Tipo Retorno de Lote de Envio
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.RET_ENV_EVENTO)
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDispatchResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_00;

    private @XmlElement(name = "idLote") @NotNull @Pattern(regexp = "[0-9]{1,15}") final String batchId;

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "verAplic") @NotNull @Size(min = 1, max = 20) @NFeString final String applicationVersion;

    private @XmlElement(name = "cOrgao") @NotNull final IBGEOrgan ibgeOrgan;

    private @XmlElement(name = "cStat") @NotNull @Size(max = 3) @Pattern(regexp = "[0-9]{3}") final String statusCode;

    private @XmlElement(name = "xMotivo") @NotNull @Size(min = 1, max = 255) @NFeString final String statusDescription;

    private @XmlElement(name = "retEvento") @Size(min = 0, max = 20) @Valid final ArrayList<EventResponse> eventResponses;

    public static class Builder {

        private String batchId;

        private TransmissionEnvironment transmissionEnvironment;

        private String applicationVersion;

        private IBGEOrgan ibgeOrgan;

        private String statusCode;

        private String statusDescription;

        private ArrayList<EventResponse> eventResponses;

        /**
         * 
         * @param batchId
         * @return
         */
        public Builder withBatchId(String batchId) {
            this.batchId = batchId;
            return this;
        }

        /**
         * @see TransmissionEnvironment
         * @param transmissionEnvironment
         * @return
         */
        public Builder withTransmissionEnvironment(TransmissionEnvironment transmissionEnvironment) {
            this.transmissionEnvironment = transmissionEnvironment;
            return this;
        }

        /**
         * Versão do Aplicativo que recebeu o Evento
         * 
         * @param applicationVersion
         * @return
         */
        public Builder withApplicationVersion(String applicationVersion) {
            this.applicationVersion = applicationVersion;
            return this;
        }

        /**
         * Código do orgao que registrou o Evento
         * 
         * @see IBGEOrgan
         * @param ibgeOrgan
         * @return
         */
        public Builder withIbgeOrgan(IBGEOrgan ibgeOrgan) {
            this.ibgeOrgan = ibgeOrgan;
            return this;
        }

        /**
         * Código do status da registro do Evento
         * 
         * @param statusCode
         * @return
         */
        public Builder withStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        /**
         * Descrição literal do status do registro do Evento
         * 
         * @param statusDescription
         * @return
         */
        public Builder withStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
            return this;
        }

        /**
         * List of {@link EventResponse}
         * 
         * @see EventResponse
         * @param eventResponses
         * @return
         */
        public Builder withEventResponses(ArrayList<EventResponse> eventResponses) {
            this.eventResponses = eventResponses;
            return this;
        }

        public EventDispatchResponse build() {
            final EventDispatchResponse entity = new EventDispatchResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public EventDispatchResponse() {
        this.batchId = null;
        this.transmissionEnvironment = null;
        this.applicationVersion = null;
        this.ibgeOrgan = null;
        this.statusCode = null;
        this.statusDescription = null;
        this.eventResponses = null;
    }

    public EventDispatchResponse(Builder builder) {
        this.batchId = builder.batchId;
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.applicationVersion = builder.applicationVersion;
        this.ibgeOrgan = builder.ibgeOrgan;
        this.statusCode = builder.statusCode;
        this.statusDescription = builder.statusDescription;
        this.eventResponses = builder.eventResponses;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getBatchId() {
        return this.batchId;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public IBGEOrgan getIbgeOrgan() {
        return this.ibgeOrgan;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public ArrayList<EventResponse> getEventResponses() {
        return this.eventResponses;
    }
}

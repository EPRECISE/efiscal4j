
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v400.types.NFeAccessKey;
import eprecise.efiscal4j.nfe.v400.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * 
 * Dados do protocolo de status
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessingStatusProtocolInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") final String id;

    private @XmlElement(name = "tpAmb") @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "verAplic") @NotNull @Size(min = 1, max = 20) @NFeString final String applicationVersion;

    private @XmlElement(name = "chNFe") @NotNull @NFeAccessKey final String acessKey;

    private @XmlElement(name = "dhRecbto") @NotNull @NFeDateTimeUTC final String processingDateTime;

    private @XmlElement(name = "nProt") @Size(max = 15) @Pattern(regexp = "[0-9]{15}") final String protocolNumber;

    private @XmlElement(name = "digVal") final String digestValue;

    private @XmlElement(name = "cStat") @NotNull @Size(max = 3) @Pattern(regexp = "[0-9]{3}") final String statusCode;

    private @XmlElement(name = "xMotivo") @NotNull @Size(min = 1, max = 255) @NFeString final String statusDescription;

    public static class Builder {

        private String id;

        private TransmissionEnvironment transmissionEnvironment;

        private String applicationVersion;

        private String acessKey;

        private String processingDateTime;

        private String protocolNumber;

        private String digestValue;

        private String statusCode;

        private String statusDescription;

        /**
         * 
         * @param id
         * @return
         */
        public Builder withId(String id) {
            this.id = id;
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
         * Versão do Aplicativo que processou a NF-e
         * 
         * @param applicationVersion
         * @return
         */
        public Builder withApplicationVersion(String applicationVersion) {
            this.applicationVersion = applicationVersion;
            return this;
        }

        /**
         * @see NFeAccessKey
         * @param acessKey
         * @return
         */
        public Builder withAcessKey(String acessKey) {
            this.acessKey = acessKey;
            return this;
        }

        /**
         * 
         * @param processingDateTime
         * @return
         */
        public Builder withProcessingDateTime(String processingDateTime) {
            this.processingDateTime = processingDateTime;
            return this;
        }

        /**
         * Número do Protocolo de Status da NF-e:
         * <ul>
         * <li>1 - (1 – Secretaria de Fazenda Estadual 2 – Receita Federal)</li>
         * <li>2 - código da UF</li>
         * <li>2 - ano</li>
         * <li>10 - sequencial no ano</li>
         * </ul>
         * 
         * @param protocolNumber
         * @return
         */
        public Builder withProtocolNumber(String protocolNumber) {
            this.protocolNumber = protocolNumber;
            return this;
        }

        /**
         * Digest Value da NF-e processada. Utilizado para conferir a integridade da NF-e original
         * 
         * @param digestValue
         * @return
         */
        public Builder withDigestValue(String digestValue) {
            this.digestValue = digestValue;
            return this;
        }

        /**
         * Código do status da mensagem enviada.
         * 
         * @param statusCode
         * @return
         */
        public Builder withStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        /**
         * Descrição literal do status do serviço solicitado
         * 
         * @param statusDescription
         * @return
         */
        public Builder withStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
            return this;
        }

        public ProcessingStatusProtocolInfo build() {
            final ProcessingStatusProtocolInfo entity = new ProcessingStatusProtocolInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ProcessingStatusProtocolInfo() {
        this.transmissionEnvironment = null;
        this.applicationVersion = null;
        this.acessKey = null;
        this.processingDateTime = null;
        this.protocolNumber = null;
        this.digestValue = null;
        this.statusCode = null;
        this.statusDescription = null;
        this.id = null;
    }

    public ProcessingStatusProtocolInfo(Builder builder) {
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.applicationVersion = builder.applicationVersion;
        this.acessKey = builder.acessKey;
        this.processingDateTime = builder.processingDateTime;
        this.protocolNumber = builder.protocolNumber;
        this.digestValue = builder.digestValue;
        this.statusCode = builder.statusCode;
        this.statusDescription = builder.statusDescription;
        this.id = builder.id;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public String getAcessKey() {
        return this.acessKey;
    }

    public String getProcessingDateTime() {
        return this.processingDateTime;
    }

    public String getProtocolNumber() {
        return this.protocolNumber;
    }

    public String getDigestValue() {
        return this.digestValue;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public String getId() {
        return this.id;
    }
}

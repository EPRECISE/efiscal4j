
package eprecise.efiscal4j.nfe.v310.sharing;

import java.io.Serializable;

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
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v310.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Tipo Resultado da Consulta do Status do Serviço
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.RET_CONS_STAT_SERV)
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceStatusSearchResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/xsd/retConsStatServ_v3.10.xsd";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "verAplic") @NotNull @Size(min = 1, max = 20) @NFeString final String applicationVersion;

    private @XmlElement(name = "cStat") @NotNull @Size(max = 3) @Pattern(regexp = "[0-9]{3}") final String statusCode;

    private @XmlElement(name = "xMotivo") @NotNull @Size(min = 1, max = 255) @NFeString final String statusDescription;

    private @XmlElement(name = "cUF") @NotNull final UF serviceUf;

    private @XmlElement(name = "dhRecbto") @NotNull @NFeDateTimeUTC final String receptionDateTime;

    private @XmlElement(name = "tMed") @Pattern(regexp = "[0-9]{1,4}") final String averageTime;

    private @XmlElement(name = "dhRetorno") @NFeDateTimeUTC final String returnDateTime;

    private @XmlElement(name = "xObs") @Size(min = 1, max = 255) @NFeString final String observation;

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private String applicationVersion;

        private String statusCode;

        private String statusDescription;

        private UF serviceUf;

        private String receptionDateTime;

        private String averageTime;

        private String returnDateTime;

        private String observation;

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

        /**
         * Código da UF responsável pelo serviço
         * 
         * @param serviceUf
         * @return
         */
        public Builder withServiceUf(UF serviceUf) {
            this.serviceUf = serviceUf;
            return this;
        }

        /**
         * Data e hora do recebimento da consulta
         * 
         * @param receptionDateTime
         * @return
         */
        public Builder withReceptionDateTime(String receptionDateTime) {
            this.receptionDateTime = receptionDateTime;
            return this;
        }

        /**
         * Tempo médio de resposta do serviço (em segundos) dos últimos 5 minutos
         * 
         * @param averageTime
         * @return
         */
        public Builder withAverageTime(String averageTime) {
            this.averageTime = averageTime;
            return this;
        }

        /**
         * Data e hora previstas para o retorno dos serviços prestados
         * 
         * @param returnDateTime
         * @return
         */
        public Builder withReturnDateTime(String returnDateTime) {
            this.returnDateTime = returnDateTime;
            return this;
        }

        /**
         * Informações ao contribuinte
         * 
         * @param observation
         * @return
         */
        public Builder withObservation(String observation) {
            this.observation = observation;
            return this;
        }

        public ServiceStatusSearchResponse build() {
            final ServiceStatusSearchResponse entity = new ServiceStatusSearchResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ServiceStatusSearchResponse() {
        this.transmissionEnvironment = null;
        this.applicationVersion = null;
        this.statusCode = null;
        this.statusDescription = null;
        this.serviceUf = null;
        this.receptionDateTime = null;
        this.averageTime = null;
        this.returnDateTime = null;
        this.observation = null;
    }

    public ServiceStatusSearchResponse(Builder builder) {
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.applicationVersion = builder.applicationVersion;
        this.statusCode = builder.statusCode;
        this.statusDescription = builder.statusDescription;
        this.serviceUf = builder.serviceUf;
        this.receptionDateTime = builder.receptionDateTime;
        this.averageTime = builder.averageTime;
        this.returnDateTime = builder.returnDateTime;
        this.observation = builder.observation;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public UF getServiceUf() {
        return this.serviceUf;
    }

    public String getReceptionDateTime() {
        return this.receptionDateTime;
    }

    public String getAverageTime() {
        return this.averageTime;
    }

    public String getReturnDateTime() {
        return this.returnDateTime;
    }

    public String getObservation() {
        return this.observation;
    }

}

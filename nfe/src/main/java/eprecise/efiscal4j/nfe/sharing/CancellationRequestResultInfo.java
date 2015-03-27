
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.address.UF;
import eprecise.efiscal4j.nfe.types.NFeAccessKey;
import eprecise.efiscal4j.nfe.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Dados do Resultado do Pedido de Cancelamento da Nota Fiscal Eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CancellationRequestResultInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") final String id;

    private @XmlElement(name = "tpAmb") @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "verAplic") @NotNull @Size(min = 1, max = 20) @NFeString final String applicationVersion;

    private @XmlElement(name = "cStat") @NotNull @Size(max = 3) @Pattern(regexp = "[0-9]{3}") final String statusCode;

    private @XmlElement(name = "xMotivo") @NotNull @Size(min = 1, max = 255) @NFeString final String statusDescription;

    private @XmlElement(name = "cUF") @NotNull final UF serviceUf;

    private @XmlElement(name = "chNFe") @NFeAccessKey final String acessKey;

    private @XmlElement(name = "dhRecbto") @NFeDateTimeUTC final String receptionDateTime;

    private @XmlElement(name = "nProt") @Size(max = 15) @Pattern(regexp = "[0-9]{15}") final String protocolNumber;

    public static class Builder {

        private String id;

        private TransmissionEnvironment transmissionEnvironment;

        private String applicationVersion;

        private String statusCode;

        private String statusDescription;

        private UF serviceUf;

        private String acessKey;

        private String receptionDateTime;

        private String protocolNumber;

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
         * Versão do Aplicativo que processou o pedido de cancelamento
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
         * Código da UF de atendimento
         * 
         * @param serviceUf
         * @return
         */
        public Builder withServiceUf(UF serviceUf) {
            this.serviceUf = serviceUf;
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
         * Data e hora de recebimento da consulta
         * 
         * @param receptionDateTime
         * @return
         */
        public Builder withReceptionDateTime(String receptionDateTime) {
            this.receptionDateTime = receptionDateTime;
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

        public CancellationRequestResultInfo build() {
            final CancellationRequestResultInfo entity = new CancellationRequestResultInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CancellationRequestResultInfo() {
        this.id = null;
        this.transmissionEnvironment = null;
        this.applicationVersion = null;
        this.statusCode = null;
        this.statusDescription = null;
        this.serviceUf = null;
        this.acessKey = null;
        this.receptionDateTime = null;
        this.protocolNumber = null;
    }

    public CancellationRequestResultInfo(Builder builder) {
        this.id = builder.id;
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.applicationVersion = builder.applicationVersion;
        this.statusCode = builder.statusCode;
        this.statusDescription = builder.statusDescription;
        this.serviceUf = builder.serviceUf;
        this.acessKey = builder.acessKey;
        this.receptionDateTime = builder.receptionDateTime;
        this.protocolNumber = builder.protocolNumber;
    }

    public String getId() {
        return this.id;
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

    public String getAcessKey() {
        return this.acessKey;
    }

    public String getReceptionDateTime() {
        return this.receptionDateTime;
    }

    public String getProtocolNumber() {
        return this.protocolNumber;
    }

}

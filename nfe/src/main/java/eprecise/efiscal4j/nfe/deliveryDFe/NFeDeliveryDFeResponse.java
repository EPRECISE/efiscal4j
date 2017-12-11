
package eprecise.efiscal4j.nfe.deliveryDFe;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.types.NFeDeliveryDFeNSU;
import eprecise.efiscal4j.nfe.types.NFeStatus;


/**
 * Método retornado após consumo do WS de inutilização de numeração da NFe
 *
 * @author Fernando C Glizt
 *
 */
@XmlRootElement(name = ObjectFactory.RET_DIST_DFE_INT)
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDeliveryDFeResponse extends Receivable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/xsd/deliveryDFe/retDistDFeInt_v1.01.xsd";

    private @XmlTransient final NFeDateTimeUTC.Converter dateTimeConverter = new NFeDateTimeUTC.Converter();

    private @XmlTransient final NFeDeliveryDFeNSU.Converter nsuConverter = new NFeDeliveryDFeNSU.Converter();

    private @XmlTransient final NFeStatus.Converter statusConverter = new NFeStatus.Converter();

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlAttribute(name = "versao") final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_01;

    private @XmlElement(name = "tpAmb") @NotNull final TransmissionEnvironment enviroment;

    private @XmlElement(name = "verAplic") @NotNull final String appVersion;

    private @XmlElement(name = "cStat") @NotNull final String status;

    private @XmlElement(name = "xMotivo") @NotNull final String statusDescription;

    private @XmlElement(name = "dhResp") @NotNull @NFeDateTimeUTC final String responseDateTime;

    private @XmlElement(name = "ultNSU") @NotNull @NFeDeliveryDFeNSU final String lastNsu;

    private @XmlElement(name = "maxNSU") @NotNull @NFeDeliveryDFeNSU final String maxNsu;

    private @XmlTransient QName qName = new QName(ObjectFactory.RET_DIST_DFE_INT);

    public static class Builder {

        private TransmissionEnvironment enviroment;

        private String appVersion;

        private int status;

        private String statusDescription;

        private ZonedDateTime response;

        private long lastNsu;

        private long maxNsu;

        /**
         * Identificação do Ambiente
         */
        public Builder withEnviroment(TransmissionEnvironment env) {
            this.enviroment = env;
            return this;
        }

        /**
         * Versão do Web Service NFeDistribuicaoDFe
         */
        public Builder withAppVersion(String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        /**
         * Código do status de processamento da requisição
         */
        public Builder withStatusCode(int stat) {
            this.status = stat;
            return this;
        }

        /**
         * Descrição literal do status do processamento da requisição
         */
        public Builder withStatusDescription(String statDesc) {
            this.statusDescription = statDesc;
            return this;
        }

        /**
         * Data e Hora de processamento da requisição
         */
        public Builder withResponse(ZonedDateTime response) {
            this.response = response;
            return this;
        }

        /**
         * Último NSU pesquisado no Ambiente Nacional. Se for o caso, o solicitante pode continuar a consulta a partir deste NSU para obter novos resultados.
         */
        public Builder withLastNsu(long nsu) {
            this.lastNsu = nsu;
            return this;
        }

        /**
         * Maior NSU existente no Ambiente Nacional para o CNPJ/CPF informado
         */
        public Builder withMaxNsu(long nsu) {
            this.maxNsu = nsu;
            return this;
        }

        public NFeDeliveryDFeResponse build() {
            final NFeDeliveryDFeResponse entity = new NFeDeliveryDFeResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeDeliveryDFeResponse() {
        this.enviroment = null;
        this.appVersion = null;
        this.status = null;
        this.statusDescription = null;
        this.responseDateTime = null;
        this.lastNsu = null;
        this.maxNsu = null;
    }

    public NFeDeliveryDFeResponse(Builder builder) {
        this.enviroment = builder.enviroment;
        this.appVersion = builder.appVersion;
        this.status = this.statusConverter.serialize(builder.status);
        this.statusDescription = builder.statusDescription;
        this.responseDateTime = this.dateTimeConverter.serialize(builder.response);
        this.lastNsu = this.nsuConverter.serialize(builder.lastNsu);
        this.maxNsu = this.nsuConverter.serialize(builder.maxNsu);
    }

    /**
     * Identificação do Ambiente
     */
    public TransmissionEnvironment getEnviroment() {
        return this.enviroment;
    }

    /**
     * Versão do Web Service NFeDistribuicaoDFe
     */
    public String getAppVersion() {
        return this.appVersion;
    }

    /**
     * Código do status de processamento da requisição
     */
    public int getStatus() {
        return this.statusConverter.parse(this.status);
    }

    /**
     * Descrição literal do status do processamento da requisição
     */
    public String getStatusDescription() {
        return this.statusDescription;
    }

    /**
     * Data e Hora de processamento da requisição
     */
    public ZonedDateTime getResponseDateTime() {
        return this.dateTimeConverter.parse(this.responseDateTime);
    }

    /**
     * Último NSU pesquisado no Ambiente Nacional. Se for o caso, o solicitante pode continuar a consulta a partir deste NSU para obter novos resultados.
     */
    public long getLastNsu() {
        return this.nsuConverter.parse(this.lastNsu);
    }

    /**
     * Maior NSU existente no Ambiente Nacional para o CNPJ/CPF informado
     */
    public long getMaxNsu() {
        return this.nsuConverter.parse(this.maxNsu);
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }
}


package eprecise.efiscal4j.nfe.recipientManifestDfe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.builder.EqualsBuilder;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.types.NFeRecipientManifNSU;


/**
 * Schema de validação XML dp Pedido de Consulta de NF-e
 * 
 * Tipo Pedido de Consulta de Nota Fiscal Eletrônica
 *
 * @author Luan Bukowitz Beluzzo
 *
 */
@XmlRootElement(name = ObjectFactory.CONS_NFE_DEST)
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeRecipientManifQueryRequest implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/xsd/recipientManifestDfe/query/consNFeDest_v1.01.xsd";

    private @XmlTransient final NFeRecipientManifNSU.Converter nsuConverter = new NFeRecipientManifNSU.Converter();

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlAttribute(name = "versao") final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_01;

    private @XmlElement(name = "tpAmb") @NotNull final TransmissionEnvironment enviroment;

    private @XmlElement(name = "xServ") @NotNull final NFeRecipientManifService service;

    private @XmlElement(name = "CNPJ") @NotNull @CNPJ(formatted = false) @Size(max = 14) @NFeCNPJ final String cnpj; // Campo é uma xs:choice com CPF, não mapeado

    private @XmlElement(name = "indNFe") @NotNull final NFeRecipientManifQueryIndicator queryIndicator;

    private @XmlElement(name = "indEmi") @NotNull final NFeRecipientManifSenderIndicator senderIndicator;

    private @XmlAttribute(name = "ultNSU") @NotNull @Size(max = 15) @NFeRecipientManifNSU final String lastNsuReceived;

    private @XmlTransient QName qName = new QName(ObjectFactory.CONS_NFE_DEST);

    public static class Builder {

        private TransmissionEnvironment enviroment;

        private NFeRecipientManifService service;

        private String cnpj;

        private NFeRecipientManifQueryIndicator queryIndicator;

        private NFeRecipientManifSenderIndicator senderIndicator;

        private long lastNsuReceived;

        /**
         * Identificação do Ambiente: 1 - Produção / 2 - Homologação
         */
        public Builder withEnviroment(TransmissionEnvironment enviroment) {
            this.enviroment = enviroment;
            return this;
        }

        /**
         * Serviço Solicitado
         */
        public Builder setService(NFeRecipientManifService service) {
            this.service = service;
            return this;
        }

        /**
         * CNPJ do destinatário da NF-e
         */
        public Builder setCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        /**
         * Indicador de NF-e consultada: 0 – Todas as NF-e; 1 – Somente as NF-e ainda não confirmadas. 2 – Somente as NF-e ainda não confirmadas (inclusive Ciência)
         */
        public Builder setQueryIndicator(NFeRecipientManifQueryIndicator queryIndicator) {
            this.queryIndicator = queryIndicator;
            return this;
        }

        /**
         * Indicador do Emissor da NF-e: 0 – Todos os Emitentes; 1 – Somente as NF-e emitidas por emissores que não tenham a mesma raiz do CNPJ (para excluir as notas fiscais de transferência entre
         * filiais)
         */
        public Builder setSenderIndicator(NFeRecipientManifSenderIndicator senderIndicator) {
            this.senderIndicator = senderIndicator;
            return this;
        }

        /**
         * Último NSU recebido. Caso seja informado com zero, a consulta deve ser realizada no universo das notas fiscais tenham sido recepcionadas nos últimos 30 dias
         */
        public Builder withLastNsuReceived(long lastNsuReceived) {
            this.lastNsuReceived = lastNsuReceived;
            return this;
        }

        public NFeRecipientManifQueryRequest build() {
            final NFeRecipientManifQueryRequest entity = new NFeRecipientManifQueryRequest(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeRecipientManifQueryRequest() {
        this.enviroment = null;
        this.service = null;
        this.cnpj = null;
        this.queryIndicator = null;
        this.senderIndicator = null;
        this.lastNsuReceived = null;
    }

    private NFeRecipientManifQueryRequest(Builder builder) {
        this.enviroment = builder.enviroment;
        this.service = builder.service;
        this.cnpj = builder.cnpj;
        this.queryIndicator = builder.queryIndicator;
        this.senderIndicator = builder.senderIndicator;
        this.lastNsuReceived = this.nsuConverter.serialize(builder.lastNsuReceived);
    }

    @Override
    public void setQName(QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

    public TransmissionEnvironment getEnviroment() {
        return this.enviroment;
    }

    public NFeRecipientManifService getService() {
        return this.service;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public NFeRecipientManifQueryIndicator getQueryIndicator() {
        return this.queryIndicator;
    }

    public NFeRecipientManifSenderIndicator getSenderIndicator() {
        return this.senderIndicator;
    }

    public long getLastNsuReceived() {
        return this.nsuConverter.parse(this.lastNsuReceived);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NFeRecipientManifQueryRequest) {
            final NFeRecipientManifQueryRequest o = (NFeRecipientManifQueryRequest) obj;
            return new EqualsBuilder().append(this.enviroment, o.enviroment).append(this.cnpj, o.cnpj).append(this.queryIndicator, o.queryIndicator).append(this.senderIndicator, o.senderIndicator)
                    .isEquals();
        }
        return false;
    }

}

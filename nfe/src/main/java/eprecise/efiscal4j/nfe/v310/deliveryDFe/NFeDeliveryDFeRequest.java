
package eprecise.efiscal4j.nfe.v310.deliveryDFe;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.builder.EqualsBuilder;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v310.types.NFeCNPJ;


/**
 * Schema de pedido de distribuição de DF-e de interesse
 *
 * @author Clécius J. Martinkoski
 *
 */
@XmlRootElement(name = ObjectFactory.DIST_DFE_INT)
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDeliveryDFeRequest implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/v310/xsd/deliveryDFe/distDFeInt_v1.01.xsd";

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlAttribute(name = "versao") final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_01;

    private @XmlElement(name = "tpAmb") @NotNull final TransmissionEnvironment enviroment;

    private @XmlElement(name = "cUFAutor") final UF authorUf;

    private @XmlElement(name = "CNPJ") @NotNull @CNPJ(formatted = false) @NFeCNPJ final String cnpj; // Campo é uma xs:choice com CPF, não mapeado

    @XmlElements(
            value = { @XmlElement(name = "distNSU", type = NFeDeliveryNSU.class), @XmlElement(name = "consNSU", type = NFeQueryNSU.class),
                    @XmlElement(name = "consChNFe", type = NFeQueryByAccessKey.class) }) @NotNull private final NFeDeliveryDFeRequestType deliveryType;

    private @XmlTransient QName qName = new QName(ObjectFactory.DIST_DFE_INT);

    public static class Builder {

        private TransmissionEnvironment enviroment;

        private UF authorUf;

        private String cnpj;

        private NFeDeliveryDFeRequestType type;

        /**
         * Identificação do Ambiente
         */
        public Builder withEnviroment(TransmissionEnvironment env) {
            this.enviroment = env;
            return this;
        }

        /**
         * Código da UF do Autor
         */
        public Builder withAuthorUf(UF uf) {
            this.authorUf = uf;
            return this;
        }

        /**
         * CNPJ do interessado no DF-e
         */
        public Builder withCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        /**
         * Tipo da requisição:
         * 
         * @see NFeDeliveryNSU: Grupo para distribuir DF-e de interesse
         * @see NFeQueryNSU: Grupo para consultar um DF-e a partir de um NSU específico
         * @see NFeQueryByKey: Grupo para consultar uma NF-e a partir da chave de acesso
         * 
         * @return
         */
        public Builder withType(NFeDeliveryDFeRequestType type) {
            this.type = type;
            return this;
        }

        public NFeDeliveryDFeRequest build() {
            final NFeDeliveryDFeRequest entity = new NFeDeliveryDFeRequest(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeDeliveryDFeRequest() {
        this.enviroment = null;
        this.authorUf = null;
        this.cnpj = null;
        this.deliveryType = null;
    }

    public NFeDeliveryDFeRequest(Builder builder) {
        this.enviroment = builder.enviroment;
        this.authorUf = builder.authorUf;
        this.cnpj = builder.cnpj;
        this.deliveryType = builder.type;
    }

    @Override
    public void setQName(QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.enviroment;
    }

    public UF getAuthorUf() {
        return this.authorUf;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public NFeDeliveryDFeRequestType getDeliveryType() {
        return this.deliveryType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NFeDeliveryDFeRequest) {
            final NFeDeliveryDFeRequest o = (NFeDeliveryDFeRequest) obj;
            return new EqualsBuilder().append(this.enviroment, o.enviroment).append(this.authorUf, o.authorUf).append(this.cnpj, o.cnpj).isEquals();
        }
        return false;
    }

}


package eprecise.efiscal4j.nfe.v310.sharing;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v310.types.NFeAccessKey;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Tipo Pedido de Consulta da Situação Atual da Nota Fiscal Eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.CONS_SIT_NFE)
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeStatusSearch implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/v310/xsd/consSitNFe_v3.10.xsd";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "xServ") @NotNull @NFeString final String requestedService = "CONSULTAR";

    private @XmlElement(name = "chNFe") @NotNull @NFeAccessKey final String acessKey;

    private @XmlTransient QName qName = new QName(ObjectFactory.CONS_SIT_NFE);

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private String acessKey;

        /**
         * @see TransmissionEnvironment
         * @param transmissionEnvironment
         * @return
         */
        public Builder withTransmissionEnvironment(final TransmissionEnvironment transmissionEnvironment) {
            this.transmissionEnvironment = transmissionEnvironment;
            return this;
        }

        /**
         * @see NFeAccessKey
         * @param acessKey
         * @return
         */
        public Builder withAcessKey(final String acessKey) {
            this.acessKey = acessKey;
            return this;
        }

        public NFeStatusSearch build() {
            final NFeStatusSearch entity = new NFeStatusSearch(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeStatusSearch() {
        transmissionEnvironment = null;
        acessKey = null;
    }

    public NFeStatusSearch(final Builder builder) {
        transmissionEnvironment = builder.transmissionEnvironment;
        acessKey = builder.acessKey;
    }

    public FiscalDocumentVersion getVersion() {
        return version;
    }

    public String getXmlns() {
        return xmlns;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return transmissionEnvironment;
    }

    public String getRequestedService() {
        return requestedService;
    }

    public String getAcessKey() {
        return acessKey;
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return qName;
    }
}

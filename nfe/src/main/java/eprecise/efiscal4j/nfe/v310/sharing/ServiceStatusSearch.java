
package eprecise.efiscal4j.nfe.v310.sharing;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.transmission.request.NFeServiceStatusSearchRequest;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Tipo Pedido de Consulta do Status do Serviço
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "consStatServ")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "version", "xmlns", "transmissionEnvironment", "serviceUf", "requestedService" })
public class ServiceStatusSearch implements TransmissibleBodyImpl, NFeServiceStatusSearchRequest {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/v310/xsd/consStatServ_v3.10.xsd";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "cUF") @NotNull final UF serviceUf;

    private @XmlElement(name = "xServ") @NotNull @NFeString final String requestedService = "STATUS";

    private @XmlTransient QName qName = new QName(ObjectFactory.CONS_STAT_SERV);

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private UF serviceUf;

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
         * Código da UF consultada
         * 
         * @param serviceUf
         * @return
         */
        public Builder withServiceUf(final UF serviceUf) {
            this.serviceUf = serviceUf;
            return this;
        }

        public ServiceStatusSearch build() {
            final ServiceStatusSearch entity = new ServiceStatusSearch(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ServiceStatusSearch() {
        transmissionEnvironment = null;
        serviceUf = null;
    }

    public ServiceStatusSearch(final Builder builder) {
        transmissionEnvironment = builder.transmissionEnvironment;
        serviceUf = builder.serviceUf;
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

    public UF getServiceUf() {
        return serviceUf;
    }

    public String getRequestedService() {
        return requestedService;
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

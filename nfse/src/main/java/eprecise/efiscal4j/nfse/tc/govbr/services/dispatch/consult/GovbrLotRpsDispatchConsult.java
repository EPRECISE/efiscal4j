
package eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.consult;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrServiceProvider;


@XmlRootElement(name = "ConsultarLoteRpsEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrLotRpsDispatchConsult implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/servico_consultar_lote_rps_envio.xsd";

    private final @XmlElement(name = "Prestador") @NotNull GovbrServiceProvider.GovbrServiceProviderIdentifier serviceProviderIdentifier;

    private final @XmlElement(name = "Protocolo") @Size(max = 50) String protocol;

    private @XmlTransient QName qName = new QName("ConsultarLoteRpsEnvio");

    public static class Builder {

        private GovbrServiceProvider.GovbrServiceProviderIdentifier serviceProviderIdentifier;

        private String protocol;

        /**
         * @param serviceProviderIdentifier
         * @return
         */
        public Builder withServiceProviderIdentifier(final GovbrServiceProvider.GovbrServiceProviderIdentifier serviceProviderIdentifier) {
            this.serviceProviderIdentifier = serviceProviderIdentifier;
            return this;
        }

        /**
         * @param protocol
         * @return
         */
        public Builder withProtocol(final String protocol) {
            this.protocol = protocol;
            return this;
        }

        public GovbrLotRpsDispatchConsult build() {
            final GovbrLotRpsDispatchConsult entity = new GovbrLotRpsDispatchConsult(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrLotRpsDispatchConsult() {
        serviceProviderIdentifier = null;
        protocol = null;
    }

    public GovbrLotRpsDispatchConsult(final Builder builder) {
        serviceProviderIdentifier = builder.serviceProviderIdentifier;
        protocol = builder.protocol;
    }

    public GovbrServiceProvider.GovbrServiceProviderIdentifier getServiceProviderIdentifier() {
        return serviceProviderIdentifier;
    }

    public String getProtocol() {
        return protocol;
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

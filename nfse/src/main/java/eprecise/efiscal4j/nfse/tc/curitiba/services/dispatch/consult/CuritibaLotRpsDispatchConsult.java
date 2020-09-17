
package eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult;

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
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.curitiba.provider.CuritibaServiceProviderIdentifier;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;


@XmlRootElement(name = "ConsultarLoteRpsEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaLotRpsDispatchConsult implements TransmissibleBodyImpl, NFSeRequest {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/curitiba/nfse.xsd";

    private final @XmlElement(name = "Prestador") @NotNull CuritibaServiceProviderIdentifier serviceProviderIdentifier;

    private final @XmlElement(name = "Protocolo") @Size(max = 50) String protocol;

    private @XmlTransient QName qName = new QName("ConsultarLoteRpsEnvio");

    public static class Builder {

        private CuritibaServiceProviderIdentifier serviceProviderIdentifier;

        private String protocol;

        /**
         * @param serviceProviderIdentifier
         * @return
         */
        public Builder withServiceProviderIdentifier(final CuritibaServiceProviderIdentifier serviceProviderIdentifier) {
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

        public CuritibaLotRpsDispatchConsult build() {
            final CuritibaLotRpsDispatchConsult entity = new CuritibaLotRpsDispatchConsult(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CuritibaLotRpsDispatchConsult() {
        serviceProviderIdentifier = null;
        protocol = null;
    }

    public CuritibaLotRpsDispatchConsult(final Builder builder) {
        serviceProviderIdentifier = builder.serviceProviderIdentifier;
        protocol = builder.protocol;
    }

    public CuritibaServiceProviderIdentifier getServiceProviderIdentifier() {
        return serviceProviderIdentifier;
    }

    public String getProtocol() {
        return protocol;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
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

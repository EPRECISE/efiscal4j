
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.address.UF;
import eprecise.efiscal4j.nfe.types.NFeString;
import eprecise.efiscal4j.nfe.types.NFeVersion;


/**
 * Tipo Pedido de Consulta do Status do Serviço
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "consStatServ")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceStatusSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final String version = NFeVersion.NFE_VERSION;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "cUF") @NotNull final UF serviceUf;

    private @XmlElement(name = "xServ") @NotNull @NFeString final String requestedService = "STATUS";

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private UF serviceUf;

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
         * Código da UF consultada
         * 
         * @param serviceUf
         * @return
         */
        public Builder withServiceUf(UF serviceUf) {
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
        this.transmissionEnvironment = null;
        this.serviceUf = null;
    }

    public ServiceStatusSearch(Builder builder) {
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.serviceUf = builder.serviceUf;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public UF getServiceUf() {
        return this.serviceUf;
    }

    public String getRequestedService() {
        return this.requestedService;
    }
}
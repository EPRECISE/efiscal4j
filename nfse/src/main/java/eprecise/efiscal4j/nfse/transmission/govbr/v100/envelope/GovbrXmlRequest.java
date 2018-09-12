
package eprecise.efiscal4j.nfse.transmission.govbr.v100.envelope;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.GovbrLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.cancel.GovbrNfseDispatchCancel;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult.GovbrLotRpsDispatchConsult;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult.state.GovbrLotRpsDispatchConsultState;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;


@XmlRootElement(name = "xmlEnvio", namespace = "http://tempuri.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrXmlRequest {

    //@formatter:off
    @XmlElementRefs({
        @XmlElementRef(name = "EnviarLoteRpsEnvio", type=GovbrLotRpsDispatchAsync.class),
        @XmlElementRef(name = "ConsultarLoteRpsEnvio", type=GovbrLotRpsDispatchConsult.class),
        @XmlElementRef(name = "ConsultarSituacaoLoteRpsEnvio", type=GovbrLotRpsDispatchConsultState.class),
        @XmlElementRef(name = "CancelarNfseEnvio", type=GovbrNfseDispatchCancel.class)
    })
    //@formatter:on
    private final NFSeRequest nfseRequest;

    public static class Builder {

        private NFSeRequest nfseRequest;

        /**
         *
         * @param nfseRequest
         * @return
         */
        public Builder withNfseRequest(final NFSeRequest nfseRequest) {
            this.nfseRequest = nfseRequest;
            return this;
        }

        public GovbrXmlRequest build() {
            final GovbrXmlRequest entity = new GovbrXmlRequest(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrXmlRequest() {
        nfseRequest = null;
    }

    public GovbrXmlRequest(final Builder builder) {
        nfseRequest = builder.nfseRequest;
    }

    public NFSeRequest getNfseRequest() {
        return nfseRequest;
    }

}

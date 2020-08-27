
package eprecise.efiscal4j.nfse.transmission.curitiba.envelope;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.CuritibaLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.cancel.CuritibaNfseDispatchCancel;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.CuritibaLotRpsDispatchConsult;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.state.CuritibaLotRpsDispatchConsultState;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;


@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaXmlRequest {

    //@formatter:off
    @XmlElementRefs({
        @XmlElementRef(name = "EnviarLoteRpsEnvio", type=CuritibaLotRpsDispatchAsync.class),
        @XmlElementRef(name = "ConsultarLoteRpsEnvio", type=CuritibaLotRpsDispatchConsult.class),
        @XmlElementRef(name = "ConsultarSituacaoLoteRps", type=CuritibaLotRpsDispatchConsultState.class),
        @XmlElementRef(name = "CancelarNfseEnvio", type=CuritibaNfseDispatchCancel.class)
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

        public CuritibaXmlRequest build() {
            final CuritibaXmlRequest entity = new CuritibaXmlRequest(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CuritibaXmlRequest() {
        nfseRequest = null;
    }

    public CuritibaXmlRequest(final Builder builder) {
        nfseRequest = builder.nfseRequest;
    }

    public NFSeRequest getNfseRequest() {
        return nfseRequest;
    }

}

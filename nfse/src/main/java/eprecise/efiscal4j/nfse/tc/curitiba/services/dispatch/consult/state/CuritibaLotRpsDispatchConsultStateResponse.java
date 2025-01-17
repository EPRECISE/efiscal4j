
package eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.state;

import java.util.Collection;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchStateResponse;
import eprecise.efiscal4j.signer.domain.SignatureType;


@XmlRootElement(name = "ConsultarSituacaoLoteRpsResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaLotRpsDispatchConsultStateResponse extends Receivable implements NFSeDispatchStateResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/curitiba/nfse.xsd";

    private final @XmlElement(name = "NumeroLote") @Size(max = 15) String lotNumber;

    private final @XmlElement(name = "Situacao") CuritibaConsultState state;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessageList;

    public @XmlElement(name = "Signature") SignatureType signature;

    public static class Builder {

        private String lotNumber;

        private CuritibaConsultState state;

        private Collection<CommonsNFSeReturnMessage> returnMessageList;

        /**
         * @param lotNumber
         * @return
         */
        public Builder withLotNumber(final String lotNumber) {
            this.lotNumber = lotNumber;
            return this;
        }

        /**
         * @param state
         * @return
         */
        public Builder withState(final CuritibaConsultState state) {
            this.state = state;
            return this;
        }

        /**
         * @param returnMessageList
         * @return
         */
        public Builder withReturnMessageList(final Collection<CommonsNFSeReturnMessage> returnMessageList) {
            this.returnMessageList = returnMessageList;
            return this;
        }

        public CuritibaLotRpsDispatchConsultStateResponse build() throws Exception {
            final CuritibaLotRpsDispatchConsultStateResponse entity = new CuritibaLotRpsDispatchConsultStateResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CuritibaLotRpsDispatchConsultStateResponse() {
        lotNumber = null;
        state = null;
        returnMessageList = null;
    }

    public CuritibaLotRpsDispatchConsultStateResponse(final Builder builder) {
        lotNumber = builder.lotNumber;
        state = builder.state;
        returnMessageList = builder.returnMessageList;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public CuritibaConsultState getState() {
        return state;
    }

    @Override
    public boolean isProcessed() {
        return state.equals(CuritibaConsultState.PROCESSED_SUCESSFULLY) || state.equals(CuritibaConsultState.PROCESSED_WITH_ERRORS);
    }

    @Override
    public Collection<CommonsNFSeReturnMessage> getReturnMessageList() {
        return returnMessageList;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

}


package eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.consult.state;

import java.util.Collection;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchStateResponse;
import eprecise.efiscal4j.nfse.transmission.response.state.NFSeConsultState;
import eprecise.efiscal4j.signer.domain.SignatureType;


@XmlRootElement(name = "ConsultarSituacaoLoteRpsResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrLotRpsDispatchConsultStateResponse extends Receivable implements NFSeDispatchStateResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/servico_consultar_lote_rps_resposta.xsd";

    private final @XmlElement(name = "NumeroLote") @Size(max = 15) String lotNumber;

    private final @XmlElement(name = "Situacao") NFSeConsultState state;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessageList;

    public @XmlElement(name = "Signature") SignatureType signature;

    private @XmlTransient QName qName = new QName("ConsultarLoteRpsResposta");

    public static class Builder {

        private String lotNumber;

        private NFSeConsultState state;

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
        public Builder withState(final NFSeConsultState state) {
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

        public GovbrLotRpsDispatchConsultStateResponse build() throws Exception {
            final GovbrLotRpsDispatchConsultStateResponse entity = new GovbrLotRpsDispatchConsultStateResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrLotRpsDispatchConsultStateResponse() {
        lotNumber = null;
        state = null;
        returnMessageList = null;
    }

    public GovbrLotRpsDispatchConsultStateResponse(final Builder builder) {
        lotNumber = builder.lotNumber;
        state = builder.state;
        returnMessageList = builder.returnMessageList;
    }

    public QName getqName() {
        return qName;
    }

    public void setqName(final QName qName) {
        this.qName = qName;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    @Override
    public NFSeConsultState getState() {
        return state;
    }

    @Override
    public Collection<CommonsNFSeReturnMessage> getReturnMessageList() {
        return returnMessageList;
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;

    }

    @Override
    public QName getQName() {
        return qName;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

}

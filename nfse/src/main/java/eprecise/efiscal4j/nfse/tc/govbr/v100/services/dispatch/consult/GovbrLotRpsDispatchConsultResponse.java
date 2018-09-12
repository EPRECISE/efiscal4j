
package eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult;

import java.util.Collection;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.govbr.v100.compNfse.GovbrCompNFSe;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchAutorizedResponse;
import eprecise.efiscal4j.signer.domain.SignatureType;


@XmlRootElement(name = "ConsultarLoteRpsResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrLotRpsDispatchConsultResponse extends Receivable implements NFSeDispatchAutorizedResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/servico_consultar_lote_rps_resposta.xsd";

    private final @XmlElementWrapper(name = "ListaNfse") @XmlElement(name = "CompNfse") Collection<GovbrCompNFSe> compNFSeList;

    public @XmlElement(name = "Signature") SignatureType signature;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessageList;

    public static class Builder {

        private Collection<GovbrCompNFSe> compNFSeList;

        private Collection<CommonsNFSeReturnMessage> returnMessageList;

        /**
         * @param compNFSeList
         * @return
         */
        public Builder withCompNFSeList(final Collection<GovbrCompNFSe> compNFSeList) {
            this.compNFSeList = compNFSeList;
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

        public GovbrLotRpsDispatchConsultResponse build() throws Exception {
            final GovbrLotRpsDispatchConsultResponse entity = new GovbrLotRpsDispatchConsultResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrLotRpsDispatchConsultResponse() {
        compNFSeList = null;
        returnMessageList = null;
    }

    public GovbrLotRpsDispatchConsultResponse(final Builder builder) {
        compNFSeList = builder.compNFSeList;
        returnMessageList = builder.returnMessageList;
    }

    public Collection<GovbrCompNFSe> getCompNFSeList() {
        return compNFSeList;
    }

    @Override
    public Collection<CommonsNFSeReturnMessage> getReturnMessageList() {
        return returnMessageList;
    }

    @Override
    public Optional<CompNFSe> getCompNFSe() {
        return compNFSeList.stream().findAny().map(GovbrCompNFSe.class::cast);
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

}

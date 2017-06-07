
package eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.consult;

import java.util.Collection;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.govbr.compNfse.GovbrCompNFSe;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchSyncResponse;


@XmlRootElement(name = "ConsultarLoteRpsResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrLotRpsDispatchConsultResponse extends Receivable implements NFSeDispatchSyncResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/servico_consultar_lote_rps_resposta.xsd";

    private final @XmlElementWrapper(name = "ListaNfse") @XmlElement(name = "CompNfse") Collection<GovbrCompNFSe> compNFSeList;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessageList;

    private @XmlTransient QName qName = new QName("ConsultarLoteRpsResposta");

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

    public QName getqName() {
        return qName;
    }

    public void setqName(final QName qName) {
        this.qName = qName;
    }

    public Collection<GovbrCompNFSe> getCompNFSeList() {
        return compNFSeList;
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
    public Optional<GovbrCompNFSe> getCompNFSe() {
        return compNFSeList.stream().findAny().map(GovbrCompNFSe.class::cast);
    }

}

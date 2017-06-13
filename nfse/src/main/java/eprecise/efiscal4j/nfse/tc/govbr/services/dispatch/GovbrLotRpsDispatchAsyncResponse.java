
package eprecise.efiscal4j.nfse.tc.govbr.services.dispatch;

import java.util.Collection;

import javax.validation.constraints.NotNull;
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
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchAsyncResponse;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;
import eprecise.efiscal4j.signer.domain.SignatureType;


@XmlRootElement(name = "EnviarLoteRpsResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrLotRpsDispatchAsyncResponse extends Receivable implements NFSeDispatchAsyncResponse {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "NumeroLote") @NFSeNonNegativeInteger @Size(max = 15) String lotNumber;

    private final @XmlElement(name = "DataRecebimento") @NotNull @NFSeDate String receiptDate;

    private final @XmlElement(name = "Protocolo") @Size(max = 50) String protocol;

    private @XmlElement(name = "Signature") SignatureType signature;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessageList;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsResposta");

    public static class Builder {

        private String lotNumber;

        private String receiptDate;

        private String protocol;

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
         * @param receiptDate
         * @return
         */
        public Builder withReceiptDate(final String receiptDate) {
            this.receiptDate = receiptDate;
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

        /**
         * @param returnMessageList
         * @return
         */
        public Builder withReturnMessageList(final Collection<CommonsNFSeReturnMessage> returnMessageList) {
            this.returnMessageList = returnMessageList;
            return this;
        }

        public GovbrLotRpsDispatchAsyncResponse build() throws Exception {
            final GovbrLotRpsDispatchAsyncResponse entity = new GovbrLotRpsDispatchAsyncResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrLotRpsDispatchAsyncResponse() {
        lotNumber = null;
        receiptDate = null;
        protocol = null;
        returnMessageList = null;
    }

    public GovbrLotRpsDispatchAsyncResponse(final Builder builder) {
        lotNumber = builder.lotNumber;
        receiptDate = builder.receiptDate;
        protocol = builder.protocol;
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

    public String getReceiptDate() {
        return receiptDate;
    }

    @Override
    public String getProtocol() {
        return protocol;
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

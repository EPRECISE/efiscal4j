
package eprecise.efiscal4j.nfse.tc.elotech.services.dispatch;

import java.io.Serializable;
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
import eprecise.efiscal4j.nfse.tc.elotech.compNfse.ElotechCompNFSe;
import eprecise.efiscal4j.nfse.tc.messages.NFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.messages.NFSeReturnMessageLot;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeDate;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeNonNegativeInteger;


@XmlRootElement(name = "EnviarLoteRpsSincronoResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class LotRpsDispatchSyncResponse extends Receivable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/nfse_v1_2.xsd";

    private final @XmlElement(name = "NumeroLote") @NFSeNonNegativeInteger @Size(max = 15) String lotNumber;

    private final @XmlElement(name = "DataRecebimento") @NotNull @NFSeDate String receiptDate;

    private final @XmlElementWrapper(name = "ListaNfse") @XmlElement(name = "CompNfse") Collection<ElotechCompNFSe> compNFSeList;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<NFSeReturnMessage> returnMessageList;

    private final @XmlElementWrapper(name = "ListaMensagemRetornoLote") @XmlElement(name = "MensagemRetornoLote") Collection<NFSeReturnMessageLot> returnMessageLotList;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsSincronoResposta");

    public static class Builder {

        private String lotNumber;

        private String receiptDate;

        private Collection<ElotechCompNFSe> compNFSeList;

        private Collection<NFSeReturnMessage> returnMessageList;

        private Collection<NFSeReturnMessageLot> returnMessageLotList;

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
         * @param compNFSeList
         * @return
         */
        public Builder withCompNFSeList(final Collection<ElotechCompNFSe> compNFSeList) {
            this.compNFSeList = compNFSeList;
            return this;
        }

        /**
         * @param returnMessageList
         * @return
         */
        public Builder withReturnMessageList(final Collection<NFSeReturnMessage> returnMessageList) {
            this.returnMessageList = returnMessageList;
            return this;
        }

        /**
         * @param returnMessageLotList
         * @return
         */
        public Builder withReturnMessageLotList(final Collection<NFSeReturnMessageLot> returnMessageLotList) {
            this.returnMessageLotList = returnMessageLotList;
            return this;
        }

        public LotRpsDispatchSyncResponse build() throws Exception {
            final LotRpsDispatchSyncResponse entity = new LotRpsDispatchSyncResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public LotRpsDispatchSyncResponse() {
        lotNumber = null;
        receiptDate = null;
        compNFSeList = null;
        returnMessageList = null;
        returnMessageLotList = null;
    }

    public LotRpsDispatchSyncResponse(final Builder builder) {
        lotNumber = builder.lotNumber;
        receiptDate = builder.receiptDate;
        compNFSeList = builder.compNFSeList;
        returnMessageList = builder.returnMessageList;
        returnMessageLotList = builder.returnMessageLotList;
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

    public Collection<ElotechCompNFSe> getCompNFSeList() {
        return compNFSeList;
    }

    public Collection<NFSeReturnMessage> getReturnMessageList() {
        return returnMessageList;
    }

    public Collection<NFSeReturnMessageLot> getReturnMessageLotList() {
        return returnMessageLotList;
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

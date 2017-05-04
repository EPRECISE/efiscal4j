
package eprecise.efiscal4j.nfse.sharing;

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
import eprecise.efiscal4j.nfse.CompNFSe;
import eprecise.efiscal4j.nfse.types.NFSeDate;
import eprecise.efiscal4j.nfse.types.NFSeNonNegativeInteger;


@XmlRootElement(name = "EnviarLoteRpsSincronoResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class LotRpsDispatchResponse extends Receivable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/nfse_v1_2.xsd";

    private final @XmlElement(name = "NumeroLote") @NFSeNonNegativeInteger @Size(max = 15) String lotNumber;

    private final @XmlElement(name = "DataRecebimento") @NotNull @NFSeDate String receiptDate;

    private final @XmlElementWrapper(name = "ListaNfse") @XmlElement(name = "CompNfse") Collection<CompNFSe> compNFSeList;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<LotRpsDispatchReturnMessage> returnMessageList;

    private final @XmlElementWrapper(name = "ListaMensagemRetornoLote") @XmlElement(name = "MensagemRetornoLote") Collection<LotRpsDispatchReturnMessageLot> returnMessageLotList;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsSincronoResposta");

    public static class Builder {

        private String lotNumber;

        private String receiptDate;

        private Collection<CompNFSe> compNFSeList;

        private Collection<LotRpsDispatchReturnMessage> returnMessageList;

        private Collection<LotRpsDispatchReturnMessageLot> returnMessageLotList;

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
        public Builder withCompNFSeList(final Collection<CompNFSe> compNFSeList) {
            this.compNFSeList = compNFSeList;
            return this;
        }

        /**
         * @param returnMessageList
         * @return
         */
        public Builder withReturnMessageList(final Collection<LotRpsDispatchReturnMessage> returnMessageList) {
            this.returnMessageList = returnMessageList;
            return this;
        }

        /**
         * @param returnMessageLotList
         * @return
         */
        public Builder withReturnMessageLotList(final Collection<LotRpsDispatchReturnMessageLot> returnMessageLotList) {
            this.returnMessageLotList = returnMessageLotList;
            return this;
        }

        public LotRpsDispatchResponse build() throws Exception {
            final LotRpsDispatchResponse entity = new LotRpsDispatchResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public LotRpsDispatchResponse() {
        lotNumber = null;
        receiptDate = null;
        compNFSeList = null;
        returnMessageList = null;
        returnMessageLotList = null;
    }

    public LotRpsDispatchResponse(final Builder builder) {
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

    public Collection<CompNFSe> getCompNFSeList() {
        return compNFSeList;
    }

    public Collection<LotRpsDispatchReturnMessage> getReturnMessageList() {
        return returnMessageList;
    }

    public Collection<LotRpsDispatchReturnMessageLot> getReturnMessageLotList() {
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

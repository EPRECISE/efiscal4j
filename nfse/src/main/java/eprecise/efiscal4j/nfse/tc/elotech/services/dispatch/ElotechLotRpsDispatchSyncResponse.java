
package eprecise.efiscal4j.nfse.tc.elotech.services.dispatch;

import java.util.Collection;
import java.util.Optional;

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
import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessageLot;
import eprecise.efiscal4j.nfse.tc.elotech.compNfse.ElotechCompNFSe;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchSyncResponse;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;


@XmlRootElement(name = "EnviarLoteRpsSincronoResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechLotRpsDispatchSyncResponse extends Receivable implements NFSeDispatchSyncResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/elotech/nfse_v1_2.xsd";

    private final @XmlElement(name = "NumeroLote") @NFSeNonNegativeInteger @Size(max = 15) String lotNumber;

    private final @XmlElement(name = "DataRecebimento") @NotNull @NFSeDate String receiptDate;

    private final @XmlElementWrapper(name = "ListaNfse") @XmlElement(name = "CompNfse") Collection<ElotechCompNFSe> compNFSeList;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessageList;

    private final @XmlElementWrapper(name = "ListaMensagemRetornoLote") @XmlElement(name = "MensagemRetornoLote") Collection<CommonsNFSeReturnMessageLot> returnMessageLotList;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsSincronoResposta");

    public static class Builder {

        private String lotNumber;

        private String receiptDate;

        private Collection<ElotechCompNFSe> compNFSeList;

        private Collection<CommonsNFSeReturnMessage> returnMessageList;

        private Collection<CommonsNFSeReturnMessageLot> returnMessageLotList;

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
        public Builder withReturnMessageList(final Collection<CommonsNFSeReturnMessage> returnMessageList) {
            this.returnMessageList = returnMessageList;
            return this;
        }

        /**
         * @param returnMessageLotList
         * @return
         */
        public Builder withReturnMessageLotList(final Collection<CommonsNFSeReturnMessageLot> returnMessageLotList) {
            this.returnMessageLotList = returnMessageLotList;
            return this;
        }

        public ElotechLotRpsDispatchSyncResponse build() throws Exception {
            final ElotechLotRpsDispatchSyncResponse entity = new ElotechLotRpsDispatchSyncResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechLotRpsDispatchSyncResponse() {
        lotNumber = null;
        receiptDate = null;
        compNFSeList = null;
        returnMessageList = null;
        returnMessageLotList = null;
    }

    public ElotechLotRpsDispatchSyncResponse(final Builder builder) {
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

    @Override
    public Collection<CommonsNFSeReturnMessage> getReturnMessageList() {
        return returnMessageList;
    }

    @Override
    public Collection<CommonsNFSeReturnMessageLot> getReturnMessageLotList() {
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

    @Override
    public Optional<CompNFSe> getCompNFSe() {
        return compNFSeList.stream().findAny().map(ElotechCompNFSe.class::cast);
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

}

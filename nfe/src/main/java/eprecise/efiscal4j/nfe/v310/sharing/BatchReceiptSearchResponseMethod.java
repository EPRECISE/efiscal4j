
package eprecise.efiscal4j.nfe.v310.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v310.transmission.ReceivableWithQName;


/**
 * Método retornado após consumo do WS de retorno de autorização
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.NFE_RET_AUT_RESULT)
@XmlAccessorType(XmlAccessType.FIELD)
public class BatchReceiptSearchResponseMethod extends ReceivableWithQName implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = ObjectFactory.RET_CONS_RECI_NFE) @NotNull final BatchReceiptSearchResponse batchReceiptSearchResponse;

    private @XmlTransient QName qName = new QName(ObjectFactory.NFE_RET_AUT_RESULT);

    public static class Builder {

        private BatchReceiptSearchResponse batchReceiptSearchResponse;

        /**
         * 
         * @param batchReceiptSearchResponse
         * @return
         */
        public Builder withNfeDispatchResponse(BatchReceiptSearchResponse batchReceiptSearchResponse) {
            this.batchReceiptSearchResponse = batchReceiptSearchResponse;
            return this;
        }

        public BatchReceiptSearchResponseMethod build() {
            final BatchReceiptSearchResponseMethod entity = new BatchReceiptSearchResponseMethod(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public BatchReceiptSearchResponseMethod() {
        this.batchReceiptSearchResponse = null;
    }

    public BatchReceiptSearchResponseMethod(Builder builder) {
        this.batchReceiptSearchResponse = builder.batchReceiptSearchResponse;
    }

    @Override
    public void setQName(QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

}

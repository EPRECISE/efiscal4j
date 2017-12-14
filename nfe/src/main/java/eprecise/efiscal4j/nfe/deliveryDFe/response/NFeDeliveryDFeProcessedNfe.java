
package eprecise.efiscal4j.nfe.deliveryDFe.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;
import eprecise.efiscal4j.nfe.sharing.ProcessingStatusProtocol;


@XmlRootElement(name = "nfeProc")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDeliveryDFeProcessedNfe extends ProcessedNFe implements NFeDeliveryDfeDocumentContent {

    private static final long serialVersionUID = 1L;

    public NFeDeliveryDFeProcessedNfe() {
        super();
    }

    private NFeDeliveryDFeProcessedNfe(Builder builder) {
        super(builder);
    }

    public static class Builder extends ProcessedNFe.Builder {

        @Override
        public Builder withNfe(NFe nfe) {
            return (Builder) super.withNfe(nfe);
        }

        @Override
        public Builder withProcessingStatusProtocol(ProcessingStatusProtocol processingStatusProtocol) {
            return (Builder) super.withProcessingStatusProtocol(processingStatusProtocol);
        }

        @Override
        public NFeDeliveryDFeProcessedNfe build() {
            final NFeDeliveryDFeProcessedNfe entity = new NFeDeliveryDFeProcessedNfe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

}

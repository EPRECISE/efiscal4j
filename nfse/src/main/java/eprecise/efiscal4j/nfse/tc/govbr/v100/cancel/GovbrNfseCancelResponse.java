
package eprecise.efiscal4j.nfse.tc.govbr.v100.cancel;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;


@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNfseCancelResponse extends DefaultAssignable implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "Confirmacao") @NotNull Confirmation confirmation;

    public static class Builder {

        private Confirmation confirmation;

        /**
         * @param confirmation
         * @return
         */
        public Builder withConfirmation(final Confirmation confirmation) {
            this.confirmation = confirmation;
            return this;
        }

        public GovbrNfseCancelResponse build() {
            final GovbrNfseCancelResponse entity = new GovbrNfseCancelResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrNfseCancelResponse() {
        confirmation = null;
    }

    public GovbrNfseCancelResponse(final Builder builder) {
        confirmation = builder.confirmation;
    }

    public Confirmation getConfirmation() {
        return confirmation;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Confirmation {

        private @XmlAttribute(name = "id") final String id = UUID.randomUUID().toString().replaceAll("-", "");

        private final @XmlElement(name = "Pedido") @NotNull GovbrNfseCancelRequest request;

        private final @XmlElement(name = "DataHoraCancelamento") @NFSeDateTimeUTC @NotNull String date;

        public static class Builder {

            private GovbrNfseCancelRequest request;

            private String date;

            /**
             * @param request
             * @return
             */
            public Builder withRequest(final GovbrNfseCancelRequest request) {
                this.request = request;
                return this;
            }

            public Builder withDate(final String date) {
                this.date = date;
                return this;
            }

            public Confirmation build() {
                final Confirmation entity = new Confirmation(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public Confirmation() {
            request = null;
            date = null;
        }

        public Confirmation(final Builder builder) {
            request = builder.request;
            date = builder.date;
        }

        public GovbrNfseCancelRequest getRequest() {
            return request;
        }

    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, GovbrNfseCancelResponse.class).deserialize();
    }

    @Override
    public String getRootTagName() {
        return "CancelarNfseEnvio";
    }

    @Override
    public String getAssignableTagName() {
        return "InfPedidoCancelamento";
    }

    @Override
    public String getIdAttributeTagName() {
        return "id";
    }

}

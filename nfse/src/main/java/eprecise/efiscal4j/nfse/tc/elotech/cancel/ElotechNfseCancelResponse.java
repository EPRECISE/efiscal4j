
package eprecise.efiscal4j.nfse.tc.elotech.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechNfseCancelResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "ConfirmacaoCancelamento") @NotNull Confirmation confirmation;

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

        public ElotechNfseCancelResponse build() {
            final ElotechNfseCancelResponse entity = new ElotechNfseCancelResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechNfseCancelResponse() {
        confirmation = null;
    }

    public ElotechNfseCancelResponse(final Builder builder) {
        confirmation = builder.confirmation;
    }

    public Confirmation getConfirmation() {
        return confirmation;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Confirmation {

        private final @XmlElement(name = "Pedido") @NotNull ElotechNfseCancelRequest request;

        private final @XmlElement(name = "DataHora") @NFSeDateTimeUTC @NotNull String date;

        public static class Builder {

            private ElotechNfseCancelRequest request;

            private String date;

            /**
             * @param request
             * @return
             */
            public Builder withRequest(final ElotechNfseCancelRequest request) {
                this.request = request;
                return this;
            }

            /**
             * @param date
             * @return
             */
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

        public ElotechNfseCancelRequest getRequest() {
            return request;
        }

        public String getDate() {
            return date;
        }

    }

}

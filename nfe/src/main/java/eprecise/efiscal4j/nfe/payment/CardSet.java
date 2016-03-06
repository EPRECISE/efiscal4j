/**
 * 
 */

package eprecise.efiscal4j.nfe.payment;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Grupo de Cartões
 * 
 * @author Felipe Bueno
 * 
 */
public class CardSet implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "tpIntegra") final PaymentIntegrationType paymentIntegrationType;

    private @XmlElement(name = "CNPJ") @NFeCNPJ final String cnpj;

    private @XmlElement(name = "tBand") final CardFlag cardFlag;

    private @XmlElement(name = "cAut") @Size(min = 1, max = 20) @NFeString final String authorizationNumber;

    public static class Builder {

        private PaymentIntegrationType paymentIntegrationType;

        private String cnpj;

        private CardFlag cardFlag;

        private String authorizationNumber;

        /**
         * @see PaymentIntegrationType
         * @param paymentIntegrationType
         * @return
         */
        public Builder withPaymentIntegrationType(PaymentIntegrationType paymentIntegrationType) {
            this.paymentIntegrationType = paymentIntegrationType;
            return this;
        }

        /**
         * CNPJ da credenciadora de cartão de crédito/débito
         * 
         * @param cnpj
         * @return
         */
        public Builder withCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        /**
         * @see CardFlag
         * @param cardFlag
         * @return
         */
        public Builder withCardFlag(CardFlag cardFlag) {
            this.cardFlag = cardFlag;
            return this;
        }

        /**
         * Número de autorização da operação cartão de crédito/débito
         * 
         * @param authorizationNumber
         * @return
         */
        public Builder withAuthorizationNumber(String authorizationNumber) {
            this.authorizationNumber = authorizationNumber;
            return this;
        }

        public CardSet build() {
            final CardSet entity = new CardSet(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public CardSet() {
        this.paymentIntegrationType = null;
        this.cnpj = null;
        this.cardFlag = null;
        this.authorizationNumber = null;
    }

    public CardSet(Builder builder) {
        this.paymentIntegrationType = builder.paymentIntegrationType;
        this.cnpj = builder.cnpj;
        this.cardFlag = builder.cardFlag;
        this.authorizationNumber = builder.authorizationNumber;

    }

    public PaymentIntegrationType getPaymentIntegrationType() {
        return this.paymentIntegrationType;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public CardFlag getCardFlag() {
        return this.cardFlag;
    }

    public String getAuthorizationNumber() {
        return this.authorizationNumber;
    }

}

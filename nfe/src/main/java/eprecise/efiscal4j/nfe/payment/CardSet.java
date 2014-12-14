/**
 * 
 */

package eprecise.efiscal4j.nfe.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.types.NFeString;
import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Grupo de Cartões
 * 
 * @author Felipe Bueno
 * 
 */
public class CardSet implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "CNPJ") @NFeCNPJ @NotNull final String cnpj;

	private @XmlElement(name = "tBand") @NotNull final CardFlag cardFlag;

	private @XmlElement(name = "cAut") @Size(min = 1, max = 20) @NFeString @NotNull final String authorizationNumber;

	public static class Builder {

		private String cnpj;

		private CardFlag cardFlag;

		private String authorizationNumber;

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
			CardSet entity = new CardSet(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}

	}

	public CardSet() {
		this.cnpj = null;
		this.cardFlag = null;
		this.authorizationNumber = null;
	}

	public CardSet(Builder builder) {
		this.cnpj = builder.cnpj;
		this.cardFlag = builder.cardFlag;
		this.authorizationNumber = builder.authorizationNumber;

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

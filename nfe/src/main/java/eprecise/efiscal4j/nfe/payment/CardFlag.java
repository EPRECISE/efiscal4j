/**
 * 
 */

package eprecise.efiscal4j.nfe.payment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Bandeira da operadora de cartão de crédito/débito
 * 
 * @author Felipe Bueno
 * 
 */

@XmlType
@XmlEnum(String.class)
public enum CardFlag implements Serializable {

	@XmlEnumValue("01") VISA("01", "Visa"),
	@XmlEnumValue("02") MASTERCARD("02", "Mastercard"),
	@XmlEnumValue("03") AMERICAN_EXPRESS("03", "American Express"),
	@XmlEnumValue("04") SOROCRED("04", "Sorocred"),
	@XmlEnumValue("99") OUTROS("99", "Outros");

	private static final long serialVersionUID = 1L;

	private final String value;

	private final String description;

	private CardFlag(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return this.value;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return this.getDescription();
	}

}

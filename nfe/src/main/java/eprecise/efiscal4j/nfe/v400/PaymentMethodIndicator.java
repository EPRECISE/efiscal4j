
package eprecise.efiscal4j.nfe.v400;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(Integer.class)
public enum PaymentMethodIndicator {
	@XmlEnumValue("0") PAGAMENTO_A_VISTA(0, "Pagamento à vista"),
	@XmlEnumValue("1") PAGAMENTO_A_PRAZO(1, "Pagamento à prazo"),
	@XmlEnumValue("2") OUTROS(2, "Outros");

	private static final long serialVersionUID = 1L;

	private final int value;

	private final String description;

	private PaymentMethodIndicator(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
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

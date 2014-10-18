
package eprecise.efiscal4j.nfe;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Modalidade do frete
 * 
 * @author Felipe Bueno
 * 
 */
@XmlType
@XmlEnum(Integer.class)
public enum ShippingModality {

	@XmlEnumValue("0") POR_CONTA_EMITENTE(0, "Por conta do emitente"),
	@XmlEnumValue("1") POR_CONTA_DESTINATARIO_REMETENTE(1, "Por conta do destinatário/remetente"),
	@XmlEnumValue("2") POR_CONTA_TERCEIROS(2, "Por conta de terceiros"),
	@XmlEnumValue("9") SEM_FRETE(9, "Sem frete");

	private static final long serialVersionUID = 1L;

	private final int value;

	private final String description;

	private ShippingModality(int value, String description) {
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
